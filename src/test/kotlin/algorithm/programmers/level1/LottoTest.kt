package algorithm.programmers.level1

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class LottoTest {

    @Test
    fun `순서와 상관없이 구매한 로또에 당첨번호와 일치하는 번호가 있으면 맞는다`() {
        val lottos = intArrayOf(1, 0, 3, 0, 5, 6)
        val winNums = intArrayOf(1, 2, 3, 4, 5, 6)

        val lotto = Lotto(lottos, winNums)

        val (result, _) = lotto.solve()

        assertThat(result).isEqualTo(3)
    }

    @Test
    fun `누락된 번호에 임의의수를 대입한다`() {
        val lottos = intArrayOf(1, 0, 3, 0, 5, 6)
        val winNums = intArrayOf(1, 2, 3, 4, 5, 6)

        val lotto = Lotto(lottos, winNums)

        val (minimum, maximum) = lotto.solve()

        println(minimum)
        println(maximum)

        assertThat(minimum).isEqualTo(3)
        assertThat(maximum).isEqualTo(1)
    }
}