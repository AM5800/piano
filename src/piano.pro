TARGET=core
TEMPLATE = subdirs
SUBDIRS = input_win32 ui \
    core_app
ui.depends = input_win32
ui.depends = core_app
