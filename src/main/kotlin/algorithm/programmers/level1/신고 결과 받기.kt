package algorithm.programmers.level1

/**
 * 신고 결과 받기
 *
 * @see <a href="https://school.programmers.co.kr/learn/courses/30/lessons/92334">https://school.programmers.co.kr/learn/courses/30/lessons/92334</a>
 */
fun `신고 결과 받기`(idList: Array<String>, report: Array<String>, k: Int): IntArray {
    return BlockList(idList, report, k).report()
}

class BlockList(
    /** 유저 ID 목록 **/
    val ids: Array<String>,

    /** 신고 목록 **/
    val reports: Array<String>,

    /** 신고 횟수 (횟수에 따라 블럭) **/
    val k: Int
) {

    val distinctReport: List<String>
        get() = this.reports.distinct()

    fun report(): IntArray {
        val reportees = collectReportees()
        val reporters = collectReporters(reportees)

        return collectReportCount(reporters)
    }

    fun collectReportees(): Set<String> {
        val reportees = mutableMapOf<String, Int>()

        this.distinctReport.forEach {
            reportees.merge(it.split(" ")[1], 1) { acc, curr -> acc + curr }
        }

        return reportees.filterValues { it >= this.k }.keys
    }

    fun collectReporters(reportees: Set<String>): List<String> {
        return this.distinctReport
            .filter { reportees.contains(it.split(" ")[1]) }
            .map { it.split(" ")[0] }
    }

    fun collectReportCount(reporters: List<String>): IntArray {
        return this.ids.map { id ->
            reporters.count { it == id }
        }.toIntArray()
    }
}