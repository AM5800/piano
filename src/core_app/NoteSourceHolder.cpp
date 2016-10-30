#include "NoteSourceHolder.h"
#include <QLibrary>

NoteSourceHolder::NoteSourceHolder(const QString &libPath) : myInstance(nullptr), myFreeFunction(nullptr)
{
    QLibrary lib(libPath);
    auto createFunction = reinterpret_cast<CreateFunction>(lib.resolve("CreateNoteSource"));
    myFreeFunction = reinterpret_cast<FreeFunction>(lib.resolve("FreeNoteSource"));

    myInstance = createFunction();
}
