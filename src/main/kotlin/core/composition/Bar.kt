package core.composition

import core.notion.Note
import core.notion.Tonality
import org.jscience.mathematics.number.Rational

class Bar(val tonality: Tonality, val timeSignature: Rational, notes: List<StaffNote>) {
    private val _staffNotes = notes
    val staffNotes: List<StaffNote>
        get() {
            return _staffNotes
        }

    fun getNotes(): List<Note> {
        return staffNotes.map {
            if (it.spnNote.note == Note.Pause) null
            else tonality.transformNote(it.spnNote.note)
        }.filterNotNull()
    }
}