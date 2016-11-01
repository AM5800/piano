QT -= core

TARGET = ..\..\..\bin\core_app
TEMPLATE = lib
CONFIG += staticlib

SOURCES += \
    NoteSourceHolder.cpp \
    CompositionsLoader.cpp \
    CompositionsFinder.cpp \
    Composition.cpp

HEADERS += \
    NoteSourceHolder.h \
    CompositionsLoader.h \
    CompositionsFinder.h \
    Composition.h

