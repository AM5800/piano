#include "input_win32.h"
#include "win32midiinputsource.h"

INoteSource* CreateNoteSource() {
    return new Win32MidiInputSource();
}
