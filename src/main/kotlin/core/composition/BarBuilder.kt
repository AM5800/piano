package core.composition

import core.notion.Alteration
import core.notion.Note
import core.notion.Tonality
import org.jscience.mathematics.number.Rational
import java.util.*

class BarBuilder(val tonality: Tonality, val timeSignature: Rational) {

    private val notes = ArrayList<PlayableNote>()
    private val alts = mutableMapOf<Note, Alteration>()

    fun pause(duration: Rational) {
        notes.add(PlayableNote(Note.Pause, duration, Alteration.Normal, 0))
    }

    fun note(note: Note, octave: Int, duration: Rational) {
        notes.add(PlayableNote(note, duration, alts[note] ?: Alteration.Normal, octave))
    }

    fun sharp(note: Note) {
        alts[note] = Alteration.Sharp
    }

    fun natural(note: Note) {
        alts[note] = Alteration.Normal
    }

    fun flat(note: Note) {
        alts[note] = Alteration.Flat
    }

    fun createBar(): Bar {
        val durationSum = notes.fold(Rational.ZERO, { rational, playableNote -> rational + playableNote.duration })

        if (durationSum != timeSignature) throw Exception("Bar duration mismatch: $durationSum, expected: $timeSignature")

        return Bar(tonality, timeSignature, notes)
    }
}