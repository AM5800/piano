package core

import core.composition.Composition
import core.notion.Note

class SimpleSuffixTreeCompositionsIndex : CompositionsIndex {
    private class Node {
        val children = mutableMapOf<Note, Node>()
        val payload = mutableSetOf<Composition>()

        fun getChild(note: Note): Node {
            val child = children[note]
            if (child == null) {
                val newChild = Node()
                children[note] = newChild
                return newChild
            } else return child
        }

        fun findNode(note: Note): Node? {
            return children[note]
        }
    }

    private val root = Node()

    override fun add(composition: Composition) {
        var notes = composition.getNotes()
        notes = notes.reversed()
        while (notes.isNotEmpty()) {
            add(notes, composition)
            notes = notes.drop(1)
        }
    }

    private fun add(notes: List<Note>, composition: Composition) {
        var node = root
        for (note in notes) {
            val child = node.getChild(note)
            child.payload.add(composition)
            node = child
        }
    }

    override fun find(notes: List<Note>, minMatchedSymbols: Int): List<Composition> {
        val reversed = notes.reversed()
        var node = root

        var matchedSymbols = 0
        for (note in reversed) {
            val child = node.findNode(note) ?: break
            node = child
            ++matchedSymbols
        }

        if (matchedSymbols < minMatchedSymbols) return emptyList()
        return node.payload.toList()
    }
}