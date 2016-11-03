package core.notion

enum class Note {
    Pause,
    C, Cs,
    D, Ds,
    E,
    F, Fs,
    G, Gs,
    A, As,
    B

    ;

    override fun toString(): String {
        val result = super.toString()
        if (result.length == 2) return result.replace('s', '#')
        return result
    }
}