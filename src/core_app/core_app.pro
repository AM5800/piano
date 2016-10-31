QT += core

TARGET = ..\..\..\bin\core_app
TEMPLATE = lib
CONFIG += staticlib

SOURCES += \
    NoteSourceHolder.cpp \
    CompositionsLoader.cpp \
    CompositionsFinder.cpp

HEADERS += \
    NoteSourceHolder.h \
    CompositionsLoader.h \
    CompositionsFinder.h

