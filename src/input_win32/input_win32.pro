TARGET = ..\..\..\bin\input_win32
TEMPLATE = lib

DEFINES += INPUT_WIN32_LIBRARY

SOURCES += input_win32.cpp Win32MidiInputSource.cpp

HEADERS += input_win32.h \
    Win32MidiInputSource.h

