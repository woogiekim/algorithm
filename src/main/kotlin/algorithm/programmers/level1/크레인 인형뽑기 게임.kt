package algorithm.programmers.level1

import java.util.*

/**
 * 크레인 인형뽑기 게임
 *
 * @see <a href="https://school.programmers.co.kr/learn/courses/30/lessons/64061">https://school.programmers.co.kr/learn/courses/30/lessons/64061</a>
 */
fun `크레인 인형뽑기 게임`(board: Array<IntArray>, moves: IntArray): Int {
    val craneBoard = CraneBoard(board)

    return craneBoard.draws(moves)
}

class CraneBoard(
    board: Array<IntArray>,
    val bucket: Bucket = Bucket()
) {
    private val boards: MutableMap<Int, Cannes> = mutableMapOf()

    init {
        board.reversed().forEach { ints ->
            ints.forEachIndexed { y, doll ->
                if (doll == 0) return@forEachIndexed

                boards.merge(y + 1, Cannes().apply { add(doll) }) { origin, new ->
                    origin.add(new.pop())

                    origin
                }
            }
        }
    }

    fun draw(position: Int): Int {
        var doll: Int

        do {
            val cannes = boards[position]

            doll = cannes.pop()

            if (doll != 0) {
                bucket.add(doll)
                break
            }
        } while (!cannes.isLast())

        return doll
    }

    fun draws(moves: IntArray): Int {
        moves.forEach { draw(it) }

        return bucket.burstCount
    }
}

data class Cannes(
    val dolls: Stack<Int> = Stack()
) {
    fun add(doll: Int) {
        dolls.add(doll)
    }

    fun pop(): Int {
        if (this.dolls.isEmpty()) return 0

        return dolls.pop()
    }
}

fun Cannes?.isLast(): Boolean {
    return this?.dolls.isNullOrEmpty()
}

fun Cannes?.pop(): Int {
    if (this == null) return 0

    return pop()
}

data class Bucket(
    val dolls: Stack<Int> = Stack(),
    var burstCount: Int = 0
) {
    fun add(doll: Int) {
        when {
            isNotSamePrevious(doll) -> dolls.add(doll)
            else -> burst()
        }
    }

    private fun burst() {
        dolls.pop()
        burstCount += 2
    }

    private fun isNotSamePrevious(doll: Int) = this.dolls.isEmpty() || doll != this.dolls.peek()
}