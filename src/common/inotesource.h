#ifndef INOTESOURCE_H
#define INOTESOURCE_H

#include "note.h"
#include <QString>

typedef void(*NoteListener)(Note, long);

struct INoteSource {
    virtual QString GetName() const = 0;
    virtual void AddListener(NoteListener listener) = 0;
    virtual void RemoveListener(NoteListener listener) = 0;
    virtual ~INoteSource() {}
};

#endif // INOTESOURCE_H


