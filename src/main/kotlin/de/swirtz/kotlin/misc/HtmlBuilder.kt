package de.swirtz.kotlin.misc

import kotlinx.html.*
import kotlinx.html.dom.create
import org.w3c.dom.Element
import java.io.OutputStream
import java.io.OutputStreamWriter
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.transform.OutputKeys
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult

fun main(args: Array<String>) {
    val document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument()
    val html = document.create.html {
        head {
            title("Hello world")
        }
        body {
            h1("h1Class") {
                style = "background-color:red"
                +"My header1"
            }
            p("pClass") {
                +"paragraph1"
            }
        }
    }

    html.intoStream(System.out)
}

fun Element.intoStream(out: OutputStream) {
    val dom = DOMSource(this)
    with(TransformerFactory.newInstance().newTransformer()) {
        setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no")
        setOutputProperty(OutputKeys.METHOD, "xml")
        setOutputProperty(OutputKeys.INDENT, "yes")
        setOutputProperty(OutputKeys.ENCODING, "UTF-8")
        setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4")
        transform(
            dom, StreamResult(OutputStreamWriter(out, "UTF-8"))
        )
    }
}