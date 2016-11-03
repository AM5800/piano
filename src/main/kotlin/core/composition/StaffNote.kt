package core.composition

import core.notion.Note
import core.notion.SpnNote
import org.jscience.mathematics.number.Rational

interface StaffNoteAttribute {

}

data class StaffNote(val spnNote: SpnNote, val duration: Rational, val attributes: List<StaffNoteAttribute>) {
    constructor(note: Note, octave: Int, duration: Rational, attributes: List<StaffNoteAttribute>) : this(SpnNote(note, octave), duration, attributes)

    companion object {
        fun pause(duration: Rational) = StaffNote(SpnNote.pause, duration, emptyList())
    }
}