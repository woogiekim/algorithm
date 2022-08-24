package algorithm.programmers.level1

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CraneBoardTest {

    @Test
    fun `크레인보드 생성`() {
        val col1 = intArrayOf(0, 0, 0, 0, 0)
        val col2 = intArrayOf(0, 0, 1, 0, 3)
        val col3 = intArrayOf(0, 2, 5, 0, 1)
        val col4 = intArrayOf(4, 2, 4, 4, 2)
        val col5 = intArrayOf(3, 5, 1, 3, 1)

        val craneBoard = CraneBoard(arrayOf(col1, col2, col3, col4, col5))

        assertThat(craneBoard.draw(1)).isEqualTo(0)
        assertThat(craneBoard.draw(1)).isEqualTo(0)
        assertThat(craneBoard.draw(1)).isEqualTo(0)
        assertThat(craneBoard.draw(1)).isEqualTo(4)
        assertThat(craneBoard.draw(1)).isEqualTo(3)

        assertThat(craneBoard.draw(2)).isEqualTo(0)
        assertThat(craneBoard.draw(2)).isEqualTo(0)
        assertThat(craneBoard.draw(2)).isEqualTo(2)
        assertThat(craneBoard.draw(2)).isEqualTo(2)
        assertThat(craneBoard.draw(2)).isEqualTo(5)

        assertThat(craneBoard.draw(3)).isEqualTo(0)
        assertThat(craneBoard.draw(3)).isEqualTo(1)
        assertThat(craneBoard.draw(3)).isEqualTo(5)
        assertThat(craneBoard.draw(3)).isEqualTo(4)
        assertThat(craneBoard.draw(3)).isEqualTo(1)

        assertThat(craneBoard.draw(4)).isEqualTo(0)
        assertThat(craneBoard.draw(4)).isEqualTo(0)
        assertThat(craneBoard.draw(4)).isEqualTo(0)
        assertThat(craneBoard.draw(4)).isEqualTo(4)
        assertThat(craneBoard.draw(4)).isEqualTo(3)

        assertThat(craneBoard.draw(5)).isEqualTo(0)
        assertThat(craneBoard.draw(5)).isEqualTo(3)
        assertThat(craneBoard.draw(5)).isEqualTo(1)
        assertThat(craneBoard.draw(5)).isEqualTo(2)
        assertThat(craneBoard.draw(5)).isEqualTo(1)
    }

    @Test
    fun `크레인게임 시작`() {
        val boards = arrayOf(
            intArrayOf(0, 0, 0, 0, 0),
            intArrayOf(0, 0, 1, 0, 3),
            intArrayOf(0, 2, 5, 0, 1),
            intArrayOf(4, 2, 4, 4, 2),
            intArrayOf(3, 5, 1, 3, 1)
        )

        val moves = intArrayOf(1,5,3,5,1,2,1,4)

        val craneBoard = CraneBoard(boards)

        val burstCount: Int = craneBoard.draws(moves)

        assertThat(burstCount).isEqualTo(4)
    }
}