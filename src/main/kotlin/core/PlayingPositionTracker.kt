package core

import core.composition.Composition
import core.notion.SpnNote

data class PlayedNote(val note: SpnNote, val volume: Int)

class MatchedExcerpt(val composition: Composition, val excerptStart: Int, val playedNotes: List<PlayedNote>)

class PlayingPositionTracker(private val index: CompositionsIndex) : InputListener {
    private var currentComposition: Composition? = null
    private var currentPosition = -1

    private val recentNotes = mutableListOf<PlayedNote>()

    override fun onNotePressed(note: SpnNote, timeOffset: Long) {

    }

    override fun onNoteReleased(note: SpnNote, timeOffset: Long) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun reset() {
    }
}