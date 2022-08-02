package algorithm.programmers.level1

fun `숫자 문자열과 영단어`(s: String): Int {
    return EnglishDictionary(s).translate()
}

class EnglishDictionary(val number: String) {

    fun translate(): Int {
        var word = this.number

        DigitTable.values().forEach {
            word = word.replace(it.word, it.digit)
        }

        return word.toInt()
    }

    enum class DigitTable(
        val word: String,
        val digit: String
    ) {
        ZERO("zero", "0"),
        ONE("one", "1"),
        TWO("two", "2"),
        THREE("three", "3"),
        FOUR("four", "4"),
        FIVE("five", "5"),
        SIX("six", "6"),
        SEVEN("seven", "7"),
        EIGHT("eight", "8"),
        NINE("nine", "9")
    }
}