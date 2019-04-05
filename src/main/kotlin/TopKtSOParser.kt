import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import java.net.URL

private fun Element.hasChildTag(tagName: String) = children().any { it.tagName() == tagName }

private fun Element.findAllTimeTitle(): Element? {
    if (this.ownText() == "All Time" && parent().hasChildTag("table")) {
        return this
    } else {
        children().forEach { child ->
            val node = child.findAllTimeTitle()
            if (node != null) {
                return node
            }
        }
    }
    return null
}

data class ScoreEntry(val name: String, val score: Int)

private fun Document.getScoreEntries(): List<ScoreEntry> {
    val allTimeTitle = this.findAllTimeTitle() ?: throw IllegalStateException("No title found in document $this")

    val table = allTimeTitle.parent().child(1)
    val tableBody = table.child(0)

    val rows = tableBody.children()

    return rows.map { row ->
        val score = row.child(0).child(0).attr("title").substringAfterLast(' ').toInt()
        val name = row.child(2).selectFirst(".user-details").child(0).text()
        ScoreEntry(name, score)
    }
}

fun main() {

    data class Archive(val date: String, val url: String)

    URL("http://web.archive.org/cdx/search/cdx?url=https://stackoverflow.com/tags/kotlin/topusers")
        .readText()
        .lineSequence()
        .filter { it.isNotBlank() }
        .map { archiveStr ->
            val split = archiveStr.split(' ')
            Archive(date = split[1], url = "http://web.archive.org/web/${split[1]}/${split[2]}/")
        }
        .map { archive ->
            println("Fetching URL ${archive.url}")
            val document: Document = Jsoup.parse(URL(archive.url), 60 * 1000)
            document.getScoreEntries()
        }
        .forEach {
            println(it)
        }
}