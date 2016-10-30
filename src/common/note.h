#ifndef NOTE_H
#define NOTE_H

enum NoteTag {
    C, D, E, F, G, A, B
};

enum NoteDuration {
    WHOLE, HALF, QUARTER // TODO
};

struct Note {
    NoteTag const Tag;
    int const Octave;
    NoteDuration const Duration;
};

#endif // NOTE_H
