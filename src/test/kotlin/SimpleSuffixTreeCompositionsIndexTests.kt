import core.SimpleSuffixTreeCompositionsIndex
import core.composition.cMajorScale
import core.composition.forEliseExcerpt
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

        Assert.assertTrue(index.find(listOf(Note.F), 1).isEmpty())
        Assert.assertEquals(forElise, index.find(listOf(Note.E, Note.Ds), 2).single())
        Assert.assertTrue(index.find(listOf(Note.E, Note.E), 2).isEmpty())
    }

    @Test
    fun testDistinction() {
        val forElise = forEliseExcerpt()
        val cMajScale = cMajorScale()

        val index = SimpleSuffixTreeCompositionsIndex()

        index.add(forElise)
        index.add(cMajScale)

        Assert.assertEquals(2, index.find(listOf(Note.E), 1).count())
        Assert.assertEquals(cMajScale, index.find(listOf(Note.F), 1).single())

        Assert.assertEquals(cMajScale, index.find(listOf(Note.D, Note.E), 2).single())
        Assert.assertEquals(forElise, index.find(listOf(Note.Ds, Note.E), 2).single())

    }
}