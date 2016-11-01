package desktop.recorder

import core.SimpleSuffixTreeCompositionsIndex
import core.composition.cMajorScale
import core.composition.forEliseExcerpt
import core.notion.AlteratedNote
import core.notion.Alteration
import core.notion.Note
import core.notion.SpnNote
import java.io.Closeable
import javax.sound.midi.MidiMessage
import javax.sound.midi.MidiSystem

fun getSpnNoteFromMidiCode(byte: Byte): SpnNote {
    val A0 = 21.toByte()
    val C0 = A0 + 3 - 12

    val notes = listOf(
            AlteratedNote(Note.A),
            AlteratedNote(Note.A, Alteration.Sharp),
            AlteratedNote(Note.B),
            AlteratedNote(Note.C),
            AlteratedNote(Note.C, Alteration.Sharp),
            AlteratedNote(Note.D),
            AlteratedNote(Note.D, Alteration.Sharp),
            AlteratedNote(Note.E),
            AlteratedNote(Note.F),
            AlteratedNote(Note.F, Alteration.Sharp),
            AlteratedNote(Note.G),
            AlteratedNote(Note.G, Alteration.Sharp))

    val noteIndex = (byte - A0) % notes.size
    val octave = (byte - C0) / notes.size

    return SpnNote(notes[noteIndex], octave)
}


class MidiReader : Closeable {
    override fun close() {
        for (close in openHandles) {
            close()
        }
    }

    fun read() {
        val inputDevices = MidiSystem.getMidiDeviceInfo().map {
            val device = MidiSystem.getMidiDevice(it)
            if (device.maxReceivers >= 0) device
            else null
        }.filterNotNull()

        if (inputDevices.count() != 1) throw Exception("I was never prepared for this!")

        val device = inputDevices.single()
        device.transmitter.receiver = MyReceiver()
        device.open()

        openHandles.add({ device.close() })
    }

    private val openHandles = mutableListOf<() -> Unit>()
}

class MyReceiver : javax.sound.midi.Receiver {
    val index = SimpleSuffixTreeCompositionsIndex()
    val lastNotes = mutableListOf<AlteratedNote>()

    init {
        index.add(forEliseExcerpt())
        index.add(cMajorScale())
    }

    override fun send(message: MidiMessage, timeStamp: Long) {
        if (message.message.size >= 3) {
            if (message.message[0].toInt() == -112) {
                val note = getSpnNoteFromMidiCode(message.message[1])
                lastNotes.add(AlteratedNote(note.note, note.alteration))
                println("$note ${message.message[2]}")

                val findResult = index.find(lastNotes, 2)
                if (findResult.size == 1) {
                    println("Composition detected: " + findResult.single().title)
                }

                return
            }
        }
    }


    override fun close() {

    }
}
