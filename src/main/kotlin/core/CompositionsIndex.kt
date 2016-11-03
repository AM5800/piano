package core

import core.composition.Composition
import core.notion.Note

interface CompositionsIndex {
    fun add(composition: Composition)
    fun find(notes: List<Note>, minMatchedSymbols: Int): List<Composition>
}

