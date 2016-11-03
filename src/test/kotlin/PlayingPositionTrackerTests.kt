import core.PlayingPositionTracker
import core.SimpleSuffixTreeCompositionsIndex
import core.composition.forEliseExcerpt
import core.notion.SpnNote
import org.junit.Test

class PlayingPositionTrackerTests {
    val index = SimpleSuffixTreeCompositionsIndex()
    val tracker = PlayingPositionTracker(index)
    var offset = 0L

    init {
        index.add(forEliseExcerpt())
    }

    @Test
    fun testMistake() {
        tracker.reset()
        press("E")
        press("Ds")


    }

    fun press(note: String) {
        val spn = SpnNote.parse(note)
        tracker.onNotePressed(spn, offset)
        offset += 100
        tracker.onNoteReleased(spn, offset)
        offset += 200
    }
}