package core.notion

enum class Tonality {
    C {
        override fun getAlteratedNote(note: Note, alteration: Alteration): AlteratedNote {
            return AlteratedNote(note, alteration)
        }
    },
    Am {
        override fun getAlteratedNote(note: Note, alteration: Alteration): AlteratedNote {
            return AlteratedNote(note, alteration)
        }
    } // TODO
    ;
    abstract fun getAlteratedNote(note: Note, alteration: Alteration): AlteratedNote
}