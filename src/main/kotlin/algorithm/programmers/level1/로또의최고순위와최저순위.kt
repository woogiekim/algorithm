package algorithm.programmers.level1

/**
 * 로또의 최고 순위와 최저순위
 *
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/77484">https://school.programmers.co.kr/learn/courses/30/lessons/77484</a>
 */
class Solution {

    fun solution(lottos: IntArray, winNums: IntArray): IntArray {
        return Lotto(lottos, winNums).solve()
    }
}

class Lotto(
    val lottos: IntArray,
    val winNums: IntArray
) {
    fun solve(): IntArray {
        var correctCount = 0

        lottos.forEach {
            correctCount += if (winNums.contains(it)) 1 else 0
        }

        val unknown = lottos.count { it == 0 }

        val minimum = Prize.valueOf(correctCount).ranking
        val maximum = Prize.valueOf(correctCount + unknown).ranking

        return intArrayOf(maximum, minimum)
    }
}

enum class Prize(
    val ranking: Int,
    vararg val correct: Int
) {
    FIRST(1, 6),
    SECOND(2, 5),
    THIRD(3, 4),
    FORTH(4, 3),
    FIFTH(5, 2),
    OTHER(6, 1, 0);

    companion object {
        fun valueOf(correct: Int): Prize {
            return values().first { correct in it.correct }
        }
    }
}