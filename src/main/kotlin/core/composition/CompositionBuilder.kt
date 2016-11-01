package core.composition

import core.notion.Tonality
import org.jscience.mathematics.number.Rational

class CompositionBuilder(val title: String) {
    private var currentTonality: Tonality? = null
    private var currentTimeSignature: Rational? = null
    private val bars = mutableListOf<Bar>()

    fun tonality(tonality: Tonality) {
        currentTonality = tonality
    }

    fun timeSignature(duration: Rational) {
        currentTimeSignature = duration
    }

    fun bar(init: BarBuilder.() -> Unit) {
        val tonality = currentTonality ?: throw Exception("Tonality not set")
        val timeSignature = currentTimeSignature ?: throw Exception("Time signature not set")

        val builder = BarBuilder(tonality, timeSignature)
        builder.init()
        bars.add(builder.createBar())
    }

    fun createComposition(): Composition {
        return Composition(title, bars)
    }
}