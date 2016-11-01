import core.composition.forEliseExcerpt
import core.notion.Alteration
import core.notion.AlteratedNote
import core.notion.Note
import org.junit.Assert
import org.junit.Test

class CompositionTests {
    @Test
    fun testGetAlteratedNotes() {
        val composition = forEliseExcerpt()
        val notes = composition.getAlteratedNotes()
        Assert.assertArrayEquals(
                arrayOf(AlteratedNote(Note.E),
                        AlteratedNote(Note.D, Alteration.Sharp),
                        AlteratedNote(Note.E),
                        AlteratedNote(Note.D, Alteration.Sharp),
                        AlteratedNote(Note.E),
                        AlteratedNote(Note.B),
                        AlteratedNote(Note.D),
                        AlteratedNote(Note.C)
                ), notes.toTypedArray())
    }
}