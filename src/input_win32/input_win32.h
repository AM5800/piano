#ifndef INPUT_WIN32_H
#define INPUT_WIN32_H

#include <QtCore/QtGlobal>
#include "..\common\INoteSource.h"

extern "C" Q_DECL_EXPORT INoteSource* CreateNoteSource();

extern "C" Q_DECL_EXPORT void FreeNoteSource(INoteSource*);

#endif // INPUT_WIN32_H
