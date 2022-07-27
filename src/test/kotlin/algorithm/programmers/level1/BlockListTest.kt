package algorithm.programmers.level1

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BlockListTest {

    @Test
    fun `중복된 신고는 허용하지 않는다`() {
        val idList = arrayOf("muzi", "frodo", "apeach", "neo")
        val report = arrayOf("muzi frodo", "muzi frodo", "muzi neo", "muzi neo", "frodo neo", "frodo neo")
        val k = 2

        val blockList = BlockList(idList, report, k)

        assertThat(blockList.distinctReport).containsExactly("muzi frodo", "muzi neo", "frodo neo")
    }

    @Test
    fun `제재되는 횟수만큼 신고받은 유저를 가져온다`() {
        val idList = arrayOf("muzi", "frodo", "apeach", "neo")
        val report = arrayOf("muzi frodo", "muzi frodo", "neo frodo", "muzi neo", "frodo neo", "frodo muzi")
        val k = 2

        val blockList = BlockList(idList, report, k)

        val reportees: Set<String> = blockList.collectReportees()

        assertThat(reportees).containsExactly("frodo", "neo")
    }

    @Test
    fun `제제되는 횟수만큼 신고한 유저를 가져온다`() {
        val idList = arrayOf("muzi", "frodo", "apeach", "neo")
        val report = arrayOf("muzi frodo", "muzi frodo", "neo frodo", "muzi neo", "frodo neo", "frodo muzi")
        val k = 2

        val blockList = BlockList(idList, report, k)
        val reportees = blockList.collectReportees()

        val reporters: List<String> = blockList.collectReporters(reportees)

        assertThat(reporters).containsExactly("muzi", "neo", "muzi", "frodo")
    }

    @Test
    fun `제재 받은 유저의 신고 횟수를 가져온다`() {
        val idList = arrayOf("muzi", "frodo", "apeach", "neo")
        val report = arrayOf("muzi frodo", "apeach frodo", "muzi neo", "apeach neo")
        val k = 2

        val blockList = BlockList(idList, report, k)
        val reportees = blockList.collectReportees()
        val reporters = blockList.collectReporters(reportees)

        val reportCount = blockList.collectReportCount(reporters)

        assertThat(reportCount).containsExactly(2, 0, 2, 0)
    }

    @Test
    fun `유저를 신고한다`() {
        val idList = arrayOf("muzi", "frodo", "apeach", "neo")
        val report = arrayOf("muzi frodo", "apeach frodo", "muzi neo", "apeach neo")
        val k = 2

        val blockList = BlockList(idList, report, k)

        val result: IntArray = blockList.report()

        assertThat(result).containsExactly(2, 0, 2, 0)
    }
}