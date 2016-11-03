import core.composition.forEliseExcerpt
import core.notion.Alteration
import core.notion.Note
import org.junit.Assert
import org.junit.Test

class CompositionTests {
    @Test
    fun testGetNotes() {
        val composition = forEliseExcerpt()
        val notes = composition.getNotes()
        Assert.assertArrayEquals(
                arrayOf(Note.E, Note.Ds, Note.E, Note.Ds, Note.E, Note.B, Note.D, Note.C
                ), notes.toTypedArray())
    }
}