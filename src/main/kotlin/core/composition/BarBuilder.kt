package core.composition

import core.notion.Alteration
import core.notion.Note
import core.notion.SpnNote
import core.notion.Tonality
import org.jscience.mathematics.number.Rational
import java.util.*

class BarBuilder(val tonality: Tonality, val timeSignature: Rational) {

    private val notes = ArrayList<StaffNote>()
    private val alts = mutableMapOf<Note, Alteration>()

    fun pause(duration: Rational) {
        notes.add(StaffNote.pause(duration))
    }

    fun note(note: Note, octave: Int, duration: Rational) {
        val result = tonality.transformNote(SpnNote(note, octave), alts[note] ?: Alteration.Normal)
        notes.add(StaffNote(result, duration, emptyList()))
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