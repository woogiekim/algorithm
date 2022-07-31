package algorithm.programmers.level1

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class IdRecommenderTest {

    @Test
    fun `1단계 - newId에서 모든 대문자를 소문자로 치환한다`() {
        val recommender = IdRecommender("...!@BaT#*..y.abcdefghijklm")

        val result: String = recommender.toLowercase()

        assertThat(result).isEqualTo("...!@bat#*..y.abcdefghijklm")
    }

    @Test
    fun `2단계 - newId에서 알파벳 소문자, 숫자 빼기, 밑줄, 마침표를 제외한 모든 문자를 제거합니다`() {
        val recommender = IdRecommender("...!@bat#*..y.abcdefghijklm")

        val result: String = recommender.removeSpecialSymbol()

        assertThat(result).isEqualTo("...bat..y.abcdefghijklm")
    }

    @Test
    fun `3단계 - newId에서 마침표가 2번 이상 연속된 부분을 하나의 마침표로 치환합니다`() {
        val recommender = IdRecommender("...bat..y.abcdefghijklm")

        val result: String = recommender.replaceSingleDot()

        assertThat(result).isEqualTo(".bat.y.abcdefghijklm")
    }

    @Test
    fun `4단계 - newId에서 마침표가 처음이나 끝에 위치한다면 제거합니다`() {
        val recommender = IdRecommender(".bat.y.abcdefghijklm.")

        val result: String = recommender.removeDotInEdge()

        assertThat(result).isEqualTo("bat.y.abcdefghijklm")
    }

    @Test
    fun `5단계 - newId가 빈 문자열이라면, newId에 "a"를 대입합니다`() {
        val recommender = IdRecommender(" ")

        val result: String = recommender.initializeIfBlank()

        assertThat(result).isEqualTo("a")
    }

    @Test
    fun `5단계 - newId가 빈 문자열이 아니라면 변화가 없습니다`() {
        val recommender = IdRecommender(".bat.y.abcdefghijklm")

        val result: String = recommender.initializeIfBlank()

        assertThat(result).isEqualTo(".bat.y.abcdefghijklm")
    }

    @Test
    fun `6단계 - newId의 길이가 16자 이상이면, newId의 첫 15개의 문자를 제외한 나머지 문자들을 모두 제거합니다`() {
        val recommender = IdRecommender("bat.y.abcdefghijklm")

        val result: String = recommender.scale()

        assertThat(result).isEqualTo("bat.y.abcdefghi")
    }

    @Test
    fun `6단계 - newId의 길이가 16자 이하면, 변화 없습니다`() {
        val recommender = IdRecommender("bat.y.abcdefgh")

        val result: String = recommender.scale()

        assertThat(result).isEqualTo("bat.y.abcdefgh")
    }

    @Test
    fun `6단계 - 만약 제거 후 마침표가 newId의 끝에 위치한다면 끝에 위치한 마침표 문자를 제거합니다`() {
        val recommender = IdRecommender("bat.y.abcdefgh.jklm")

        val result: String = recommender.scale()

        assertThat(result).isEqualTo("bat.y.abcdefgh")
    }

    @Test
    fun `7단계 - newId의 길이가 2자 이하라면, newId의 마지막 문자를 newId의 길이가 3이 될 때까지 반복해서 끝에 붙입니다`() {
        val recommender = IdRecommender("ba")

        val result: String = recommender.min()

        assertThat(result).isEqualTo("baa")
    }

    @Test
    fun `7단계 - newId의 길이가 2자 이하가 아니면, 변화 없습니다`() {
        val recommender = IdRecommender("bat.y.abcdefgh.jklm")

        val result: String = recommender.min()

        assertThat(result).isEqualTo("bat.y.abcdefgh.jklm")
    }

    @ParameterizedTest
    @CsvSource(
        value = [
            "...!@BaT#*..y.abcdefghijklm, bat.y.abcdefghi",
            "z-+.^., z--",
            "=.=, aaa",
            "123_.def, 123_.def",
            "abcdefghijklmn.p, abcdefghijklmn"
        ]
    )
    fun `신규 아이디 추천`(id: String, newId: String) {
        val recommender = IdRecommender(id)

        val result = recommender.recommend()

        assertThat(result).isEqualTo(newId)
    }
}
