package desktop.recorder

import java.io.Closeable
import javax.sound.midi.MidiMessage
import javax.sound.midi.MidiSystem

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
    override fun send(message: MidiMessage, timeStamp: Long) {
        println(message.message)
    }

    override fun close() {

    }
}
