package algorithm.programmers.level1


/**
 * 키패드 누르기
 *
 * @see <a href="https://school.programmers.co.kr/learn/courses/30/lessons/67256">https://school.programmers.co.kr/learn/courses/30/lessons/67256</a>
 */
fun `키패드 누르기`(numbers: IntArray, hand: String): String {
    val keypad = Keypad(hand)
    return keypad.touch(numbers)
}

class Keypad(
    val main: String,
    val left: Thumb = Thumb.LEFT,
    val right: Thumb = Thumb.RIGHT
) {

    fun touch(numbers: IntArray): String {
        return numbers.joinToString("") {
            touch(it)
        }
    }

    fun touch(number: Int): String {
        return when {
            isOnlyLeftHand(number) -> moveByLeft(number)
            isOnlyRightHand(number) -> moveByRight(number)
            isLeftCloser(number) -> moveByLeft(number)
            isRightCloser(number) -> moveByRight(number)
            else -> moveByMainHand(number)
        }
    }

    private fun moveByMainHand(number: Int): String {
        return if (left.isMain(this.main)) {
            moveByLeft(number)
        } else {
            moveByRight(number)
        }
    }

    private fun isOnlyLeftHand(number: Int): Boolean {
        return number in listOf(Position.ONE, Position.FOUR, Position.SEVEN).map { it.number }
    }

    private fun isOnlyRightHand(number: Int): Boolean {
        return number in listOf(Position.THREE, Position.SIX, Position.NINE).map { it.number }
    }

    private fun isLeftCloser(number: Int): Boolean {
        return left.estimate(number) < right.estimate(number)
    }

    private fun isRightCloser(number: Int): Boolean {
        return right.estimate(number) < left.estimate(number)
    }

    private fun moveByRight(number: Int): String {
        right.move(number)

        return right.hand
    }

    private fun moveByLeft(number: Int): String {
        left.move(number)

        return left.hand
    }
}

enum class Thumb(
    val hand: String,
    var position: Position
) {
    LEFT("L", Position.STAR), RIGHT("R", Position.SARP);

    fun move(number: Int) {
        this.position = Position.valueOf(number)
    }

    fun estimate(number: Int): Int {
        val target = Position.valueOf(number)

        return Math.abs(this.position.x - target.x) + Math.abs(this.position.y - target.y)
    }

    fun isMain(hand: String): Boolean {
        return this.hand == hand.first().uppercase()
    }
}

enum class Position(
    val number: Int?,
    val x: Int,
    val y: Int
) {
    ONE(1, 3, 0), TWO(2, 3, 1), THREE(3, 3, 2),
    FOUR(4, 2, 0), FIVE(5, 2, 1), SIX(6, 2, 2),
    SEVEN(7, 1, 0), EIGHT(8, 1, 1), NINE(9, 1, 2),
    STAR(null, 0, 0), ZERO(0, 0, 1), SARP(null, 0, 2);

    companion object {
        fun valueOf(number: Int): Position {
            return values().first { it.number == number }
        }
    }
}