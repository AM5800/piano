import desktop.recorder.getSpnNoteFromMidiCode
import org.junit.Assert
import org.junit.Test

class GetSpnNoteFromMidiCodeTests {
    @Test
    fun testA0() {
        Assert.assertEquals("A0", getSpnNoteFromMidiCode(21).toString())
    }

    @Test
    fun testC1() {
        Assert.assertEquals("C1", getSpnNoteFromMidiCode(24).toString())
    }

    @Test
    fun testC8() {
        Assert.assertEquals("C8", getSpnNoteFromMidiCode(108).toString())
    }
}