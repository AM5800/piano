package desktop.midi

import core.InputListener
import core.SimpleSuffixTreeCompositionsIndex
import core.composition.cMajorScale
import core.composition.forEliseExcerpt
import core.notion.Note
import core.notion.SpnNote
import java.io.Closeable
import javax.sound.midi.MidiMessage
import javax.sound.midi.MidiSystem

fun getSpnNoteFromMidiCode(byte: Byte): SpnNote {
    val A0 = 21.toByte()
    val C0 = A0 + 3 - 12

    val notes = listOf(Note.A, Note.As, Note.B, Note.C, Note.Cs,
            Note.D, Note.Ds, Note.E, Note.F, Note.Fs, Note.G, Note.Gs)

    val noteIndex = (byte - A0) % notes.size
    val octave = (byte - C0) / notes.size

    return SpnNote(notes[noteIndex], octave)
}

class MidiReader(private val listener: InputListener) : Closeable {
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
        device.transmitter.receiver = MyReceiver(listener)
        device.open()

        openHandles.add({ device.close() })
    }

    private val openHandles = mutableListOf<() -> Unit>()
}

class MyReceiver(private val listener: InputListener) : javax.sound.midi.Receiver {
    val index = SimpleSuffixTreeCompositionsIndex()
    val lastNotes = mutableListOf<Note>()

    init {
        index.add(forEliseExcerpt())
        index.add(cMajorScale())
    }

    override fun send(message: MidiMessage, timeStamp: Long) {
        if (message.message.size >= 3) {
            if (message.message[0].toInt() == -112) {
                val spnNote = getSpnNoteFromMidiCode(message.message[1])
                lastNotes.add(spnNote.note)
                println("$spnNote ${message.message[2]}")

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
