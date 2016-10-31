#include "input_win32.h"
#include "Win32MidiInputSource.h"

INoteSource* CreateNoteSource() {
    return new Win32MidiInputSource();
}
