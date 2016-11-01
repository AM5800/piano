package core.composition

import core.notion.Note
import core.notion.Tonality
import org.jscience.mathematics.number.Rational

fun forEliseExcerpt(): Composition {
    return Composition.build("For Elise excerpt") {
        tonality(Tonality.Am)
        timeSignature(Rational.valueOf(3, 8))
        bar {
            pause(Rational.valueOf(2, 8))
            note(Note.E, 1, Rational.valueOf(1, 16))
            sharp(Note.D)
            note(Note.D, 1, Rational.valueOf(1, 16))
        }

        bar {
            note(Note.E, 1, Rational.valueOf(1, 16))
            sharp(Note.D)
            note(Note.D, 1, Rational.valueOf(1, 16))
            note(Note.E, 1, Rational.valueOf(1, 16))
            note(Note.B, 0, Rational.valueOf(1, 16))
            natural(Note.D)
            note(Note.D, 1, Rational.valueOf(1, 16))
            note(Note.C, 1, Rational.valueOf(1, 16))
        }
    }
}

fun cMajorScale(): Composition {
    return Composition.build("C major scale") {
        tonality(Tonality.C)
        timeSignature(Rational.valueOf(4, 4))
        bar {
            note(Note.C, 0, Rational.valueOf(1, 4))
            note(Note.D, 0, Rational.valueOf(1, 4))
            note(Note.E, 0, Rational.valueOf(1, 4))
            note(Note.F, 0, Rational.valueOf(1, 4))
        }
        bar {
            note(Note.G, 0, Rational.valueOf(1, 4))
            note(Note.A, 0, Rational.valueOf(1, 4))
            note(Note.B, 0, Rational.valueOf(1, 4))
            pause(Rational.valueOf(1, 4))
        }
    }
}

