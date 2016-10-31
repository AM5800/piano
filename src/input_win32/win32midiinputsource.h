#ifndef WIN32MIDIINPUTSOURCE_H
#define WIN32MIDIINPUTSOURCE_H

#include "../common/inotesource.h"
#include <vector>
#include <algorithm>
#include <Windows.h>
#include <Mmsystem.h>
#include <unordered_map>

#pragma comment(lib, "Winmm.lib")

struct Win32MidiInputSource : INoteSource
{
    Win32MidiInputSource();

    QString GetName() const override {
        return "WIN32 MIDI";
	}
    void AddListener(INoteListener * listener) override;
    void RemoveListener(INoteListener * listener) override;
    ~Win32MidiInputSource();
private:
    void Start();
    void Stop();
    void OnError(QString const & message);

    std::vector<INoteListener*> myListeners;
    std::unordered_map<int, char const *> myErrorCodes;
    HMIDIIN myHandle;
};



#endif // WIN32MIDIINPUTSOURCE_H
