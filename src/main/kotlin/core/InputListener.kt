package core

import core.notion.SpnNote

interface InputListener {
    fun onNotePressed(note: SpnNote, timeOffset: Long)

    fun onNoteReleased(note: SpnNote, timeOffset: Long)
}