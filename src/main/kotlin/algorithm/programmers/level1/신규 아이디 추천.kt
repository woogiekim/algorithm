package algorithm.programmers.level1

/**
 * 신규 아이디 추천
 *
 * @see <a href="https://school.programmers.co.kr/learn/courses/30/lessons/72410">https://school.programmers.co.kr/learn/courses/30/lessons/72410</a>
 */
fun `신규 아이디 추천`(newId: String): String {
    return IdRecommender(newId).recommend()
}

class IdRecommender(
    private val newId: String
) {

    private var recommendId: String = this.newId

    fun recommend(): String {
        toLowercase()
        removeSpecialSymbol()
        replaceSingleDot()
        removeDotInEdge()
        initializeIfBlank()
        scale()
        min()

        return this.recommendId
    }

    fun toLowercase(): String {
        this.recommendId = this.recommendId.lowercase()

        return this.recommendId
    }

    fun removeSpecialSymbol(): String {
        this.recommendId = this.recommendId.replace(Regex("[^\\w|.-]"), "")

        return this.recommendId
    }

    fun replaceSingleDot(): String {
        this.recommendId = this.recommendId.replace(Regex("(\\.{2,})"), ".")

        return this.recommendId
    }

    fun removeDotInEdge(): String {
        this.recommendId = this.recommendId.removePrefix(".").removeSuffix(".")

        return this.recommendId
    }

    fun initializeIfBlank(): String {
        this.recommendId = this.recommendId.ifBlank { "a" }

        return this.recommendId
    }

    fun scale(place: Int = 15): String {
        this.recommendId = if (this.recommendId.length > place) {
            this.recommendId.substring(0 until place).removeSuffix(".")
        } else {
            this.recommendId
        }

        return this.recommendId
    }

    fun min(number: Int = 3): String {
        this.recommendId = this.recommendId.padEnd(number, this.recommendId.last())

        return this.recommendId
    }
}
