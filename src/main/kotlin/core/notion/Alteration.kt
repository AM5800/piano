package core.notion

enum class Alteration {
    Normal {
        override fun alterate(note: Note): Pair<Note, Int> {
            return Pair(note, 0)
        }
    },
    Sharp {
        override fun alterate(note: Note): Pair<Note, Int> {
            if (note == Note.Pause) return Pair(note, 0)
            if (note == Note.B) return Pair(Note.C, 1)
            val index = Note.values().indexOf(note)

            return Pair(Note.values()[index + 1], 0)
        }
    },
    Flat {
        override fun alterate(note: Note): Pair<Note, Int> {
            if (note == Note.Pause) return Pair(note, 0)
            if (note == Note.C) return Pair(Note.B, -1)
            val index = Note.values().indexOf(note)

            return Pair(Note.values()[index - 1], 0)
        }
    }

    ;

    abstract fun alterate(note: Note): Pair<Note, Int>
}