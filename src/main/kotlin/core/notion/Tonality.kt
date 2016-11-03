package core.notion

enum class Tonality {
    C {
        override fun transformNote(spnNote: SpnNote, alteration: Alteration): SpnNote {
            return spnNote.alterate(alteration)
        }

        override fun transformNote(note: Note): Note {
            return note
        }
    },
    Am {
        override fun transformNote(spnNote: SpnNote, alteration: Alteration): SpnNote {
            return C.transformNote(spnNote, alteration)
        }

        override fun transformNote(note: Note): Note {
            return C.transformNote(note)
        }
    } // TODO
    ;

    abstract fun transformNote(note: Note): Note
    abstract fun transformNote(spnNote: SpnNote, alteration: Alteration): SpnNote
}