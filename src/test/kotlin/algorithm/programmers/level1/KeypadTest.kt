package algorithm.programmers.level1

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class KeypadTest {

    @ParameterizedTest
    @ValueSource(ints = [1, 4, 7])
    fun `왼쪽 열의 3개의 숫자 1, 4, 7을 입력할 때는 왼손 엄지손가락을 사용합니다`(number: Int) {
        val keypad = Keypad("right")

        val result: String = keypad.touch(number)

        assertThat(result).isEqualTo("L")
    }

    @ParameterizedTest
    @ValueSource(ints = [3, 6, 9])
    fun `오른쪽 열의 3개의 숫자 3, 6, 9를 입력할 때는 오른손 엄지손가락을 사용합니다`(number: Int) {
        val keypad = Keypad("left")

        val result: String = keypad.touch(number)

        assertThat(result).isEqualTo("R")
    }

    @Test
    fun `가운데 열의 4개의 숫자 2, 5, 8, 0을 입력할 때는 두 엄지손가락의 현재 키패드의 위치에서 더 가까운 엄지손가락을 사용합니다`() {
        val keypad = Keypad("left")

        keypad.touch(1)
        keypad.touch(6)

        assertThat(keypad.touch(2)).isEqualTo("L")
        assertThat(keypad.touch(5)).isEqualTo("L")
        assertThat(keypad.touch(8)).isEqualTo("L")
        assertThat(keypad.touch(0)).isEqualTo("L")
    }

    @Test
    fun `키패드 누르기`() {
        val ints = intArrayOf(1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5)

        val keypad = Keypad("right")

        val result = keypad.touch(ints)

        assertThat(result).isEqualTo("LRLLLRLLRRL")
    }
}