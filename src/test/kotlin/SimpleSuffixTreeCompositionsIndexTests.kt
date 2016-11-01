import core.SimpleSuffixTreeCompositionsIndex
import core.composition.cMajorScale
import core.composition.forEliseExcerpt
import core.notion.AlteratedNote
import core.notion.Alteration
import core.notion.Note
import org.junit.Assert
import org.junit.Test

class SimpleSuffixTreeCompositionsIndexTests {
    @Test
    fun testForElise() {
        val forElise = forEliseExcerpt()

        val index = SimpleSuffixTreeCompositionsIndex()
        index.add(forElise)

        val F = AlteratedNote(Note.F)
        val E = AlteratedNote(Note.E)
        val Ds = AlteratedNote(Note.D, Alteration.Sharp)

        Assert.assertTrue(index.find(listOf(F), 1).isEmpty())
        Assert.assertEquals(forElise, index.find(listOf(E, Ds), 2).single())
        Assert.assertTrue(index.find(listOf(E, E), 2).isEmpty())
    }

    @Test
    fun testDistinction() {
        val forElise = forEliseExcerpt()
        val cMajScale = cMajorScale()

        val index = SimpleSuffixTreeCompositionsIndex()

        index.add(forElise)
        index.add(cMajScale)

        val F = AlteratedNote(Note.F)
        val E = AlteratedNote(Note.E)
        val Ds = AlteratedNote(Note.D, Alteration.Sharp)
        val D = AlteratedNote(Note.D)

        Assert.assertEquals(2, index.find(listOf(E), 1).count())
        Assert.assertEquals(cMajScale, index.find(listOf(F), 1).single())

        Assert.assertEquals(cMajScale, index.find(listOf(D, E), 2).single())
        Assert.assertEquals(forElise, index.find(listOf(Ds, E), 2).single())

    }
}