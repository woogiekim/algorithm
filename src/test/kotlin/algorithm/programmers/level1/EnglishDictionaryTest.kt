package algorithm.programmers.level1

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class EnglishDictionaryTest {

    @Test
    fun `숫자로만 되어있으면 그냥 숫자가 나온다`() {
        val dictionary = EnglishDictionary("1234")

        val result: Int = dictionary.translate()

        assertThat(result).isEqualTo(1234)
    }

    @ParameterizedTest
    @CsvSource(
        value = [
            "one4seveneight, 1478",
            "23four5six7, 234567",
            "2three45sixseven, 234567"
        ]
    )
    fun `영문 숫자를 숫자로 치환한다`(word: String, expect: Int) {
        val dictionary = EnglishDictionary(word)

        val result: Int = dictionary.translate()

        assertThat(result).isEqualTo(expect)
    }
}