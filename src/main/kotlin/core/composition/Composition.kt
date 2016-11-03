package core.composition

import core.notion.Note

class Composition(val title: String, val bars: List<Bar>) {
    companion object {
        fun build(title: String, init: CompositionBuilder.() -> Unit): Composition {
            val builder = CompositionBuilder(title)
            builder.init()
            return builder.createComposition()
        }
    }

    fun getNotes(): List<Note> {
        return bars.flatMap(Bar::getNotes)
    }
}

