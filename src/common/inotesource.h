#ifndef INOTESOURCE_H
#define INOTESOURCE_H

#include "note.h"
#include <QString>

struct INoteListener {
    virtual void OnNote(Note const & note) = 0;
    virtual void OnError(QString const & error) = 0;
    ~INoteListener() {}
};

struct INoteSource {
    virtual QString GetName() const = 0;
    virtual void AddListener(INoteListener * listener) = 0;
    virtual void RemoveListener(INoteListener * listener) = 0;
    virtual ~INoteSource() {}
};

#endif // INOTESOURCE_H


