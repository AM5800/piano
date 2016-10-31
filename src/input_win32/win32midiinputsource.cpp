#include "win32midiinputsource.h"
#include <QSettings>
#include <QDebug>

#define ADD_ERROR_CODE(code) myErrorCodes[code] = #code

Win32MidiInputSource::Win32MidiInputSource()
{
    ADD_ERROR_CODE(MIDIERR_NODEVICE);
    ADD_ERROR_CODE(MMSYSERR_ALLOCATED);
    ADD_ERROR_CODE(MMSYSERR_BADDEVICEID);
    ADD_ERROR_CODE(MMSYSERR_INVALPARAM);
    ADD_ERROR_CODE(MMSYSERR_NOMEM);
    ADD_ERROR_CODE(MMSYSERR_NOERROR);
    ADD_ERROR_CODE(MIDIERR_BADOPENMODE);
    ADD_ERROR_CODE(MIDIERR_NOTREADY);
    ADD_ERROR_CODE(MMSYSERR_INVALHANDLE);
    ADD_ERROR_CODE(MIDIERR_STILLPLAYING);
    ADD_ERROR_CODE(MMSYSERR_ERROR);
}

void Win32MidiInputSource::AddListener(INoteListener * listener) {
    myListeners.push_back(listener);
    if (myListeners.size() > 0) {
        Start();
    }
}

void Win32MidiInputSource::RemoveListener(INoteListener * listener) {
    myListeners.erase(std::remove(myListeners.begin(), myListeners.end(), listener));
    if (myListeners.size() == 0) {
        Stop();
    }
}

Win32MidiInputSource::~Win32MidiInputSource()
{
    Stop();
}

void Win32MidiInputSource::Start()
{
    QSettings settings("./win32_midi.ini", QSettings::IniFormat);
    auto inputDeviceNameVariant = settings.value("InputDevice");

    if (inputDeviceNameVariant.isNull()) {
        OnError("Not configured");
        return;
    }

    auto inputDeviceName = inputDeviceNameVariant.value<QString>();

    auto devicesNum = midiInGetNumDevs();

    int selectedDevice = -1;

    for (size_t i = 0; i < devicesNum; ++i) {
        MIDIINCAPS caps;
        auto ec = midiInGetDevCaps(i, &caps, sizeof(caps));
        if (ec != MMSYSERR_NOERROR) {
            OnError(myErrorCodes[ec]);
            return;
        }

        QString deviceName = QString::fromWCharArray(caps.szPname);
        if (deviceName == inputDeviceName) {
            selectedDevice = (int)i;
            break;
        }
    }

    if (selectedDevice == -1) {
        OnError("Device not found: " + inputDeviceName);
    }

    auto ec = midiInOpen(&myHandle, selectedDevice, NULL, NULL, CALLBACK_FUNCTION);
    if (ec != MMSYSERR_NOERROR) {
        OnError(myErrorCodes[ec]);
        return;
    }

    ec = midiInStart(myHandle);
    if (ec != MMSYSERR_NOERROR) {
        OnError(myErrorCodes[ec]);
        return;
    }
}

void Win32MidiInputSource::Stop()
{
    midiInStop(myHandle);
    midiInClose(myHandle);
}

void Win32MidiInputSource::OnError(QString const & message)
{
    qDebug() << message;
    for (auto listener : myListeners) {
        listener->OnError(message);
    }
}
