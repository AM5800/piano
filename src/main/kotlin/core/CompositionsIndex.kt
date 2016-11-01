package core

import core.composition.Composition
import core.notion.AlteratedNote

interface CompositionsIndex {
    fun add(composition: Composition)
    fun find(notes: List<AlteratedNote>, minMatchedSymbols: Int): List<Composition>
}

