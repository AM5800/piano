package core.composition

import core.notion.AlteratedNote
import core.notion.Note
import core.notion.Tonality
import org.jscience.mathematics.number.Rational

class Bar(val tonality: Tonality, val timeSignature: Rational, notes: List<PlayableNote>) {
    private val _notes = notes
    val notes: List<PlayableNote>
        get() {
            return _notes
        }

    fun getAlteratedNotes(): List<AlteratedNote> {
        return notes.map {
            if (it.note == Note.Pause) null
            else tonality.getAlteratedNote(it.note, it.alteration)
        }.filterNotNull()
    }
}