package core.notion


// Scientific pitch notation
// https://en.wikipedia.org/wiki/Scientific_pitch_notation
data class SpnNote(val note: Note, val alteration: Alteration, val octave: Int) {
    constructor(note: AlteratedNote, octave: Int) : this(note.note, note.alt, octave)

    override fun toString(): String {
        val builder = StringBuilder()
        builder.append(note)
        when(alteration) {
            Alteration.Sharp -> builder.append("#")
            Alteration.Flat -> builder.append("♭")
            Alteration.Normal -> {}
        }
        builder.append(octave)
        return builder.toString()
    }
}