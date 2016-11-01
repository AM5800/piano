package core.notion

data class AlteratedNote(val note: Note, val alt: Alteration = Alteration.Normal)