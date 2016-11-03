import core.PlayingPositionTracker
import core.SimpleSuffixTreeCompositionsIndex
import core.composition.forEliseExcerpt
import desktop.midi.MidiReader

fun main(args: Array<String>) {
    println("Hit enter to close program")
    val index = SimpleSuffixTreeCompositionsIndex()
    index.add(forEliseExcerpt())
    val tracker = PlayingPositionTracker(index)
    MidiReader(tracker).use {
        it.read()
        readLine()
    }
}