package core.notion

// Scientific pitch notation
// https://en.wikipedia.org/wiki/Scientific_pitch_notation
data class SpnNote(val note: Note, val octave: Int) {
    override fun toString(): String {
        return note.toString() + octave.toString()
    }

    companion object {
        private val mapping = mutableMapOf<String, SpnNote>()

        init {
            for (note in Note.values()) {
                for (octave in 0..8) {
                    val spn = SpnNote(note, octave)
                    mapping[spn.toString()] = spn
                }
            }
        }

        fun parse(note: String): SpnNote {
            return mapping[note] ?: throw Exception("Can't parse spnNote: " + note)
        }

        val pause = SpnNote(Note.Pause, -1)
    }

    fun alterate(alteration: Alteration): SpnNote {
        if (alteration == Alteration.Normal) return this.copy()
        val alterationResult = alteration.alterate(note)

        return SpnNote(alterationResult.first, octave + alterationResult.second)
    }
}
