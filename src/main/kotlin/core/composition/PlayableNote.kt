package core.composition

import core.notion.Alteration
import core.notion.Note
import org.jscience.mathematics.number.Rational

class PlayableNote(val note: Note, val duration: Rational, val alteration: Alteration, val octave: Int) {

}