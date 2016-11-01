import desktop.recorder.MidiReader

fun main(args: Array<String>) {
    println("Hit enter to close program")
    MidiReader().use {
        it.read()
        readLine()
    }
}