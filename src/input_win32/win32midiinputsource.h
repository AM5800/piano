#ifndef WIN32MIDIINPUTSOURCE_H
#define WIN32MIDIINPUTSOURCE_H

#include "../common/inotesource.h"
#include <vector>
#include <algorithm>

struct Win32MidiInputSource : INoteSource
{
    QString GetName() const override {
        return "WIN32 MIDI";
	}
	void AddListener(NoteListener listener) override {
        myListeners.push_back(listener);
	}
	void RemoveListener(NoteListener listener) override {
        myListeners.erase(std::remove(myListeners.begin(), myListeners.end(), listener));
	}

	~Win32MidiInputSource()
	{
		
	}

private:
    std::vector<NoteListener> myListeners;
};

#endif // WIN32MIDIINPUTSOURCE_H
