#ifndef NOTE_H
#define NOTE_H

enum NoteTag {
    Pause,
		C, D, E, F, G, A, B,
    Cs, Ds, Es, Fs, Gs, As, Bs
};

enum NoteDuration {
    WHOLE, HALF, QUARTER, EIGHT, SIXTEEN // TODO
};

struct Note {
    NoteTag const Tag;
    int const Octave;
    NoteDuration const Duration;

    Note(NoteTag tag, int octave, NoteDuration duration) : Tag(tag), Octave(octave), Duration(duration) {}
};

#endif // NOTE_H
