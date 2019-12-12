package com.psob96.bsi_33

import com.psob96.bsi_33.database.entity.Grade
import com.psob96.bsi_33.database.entity.Lesson
import com.psob96.bsi_33.database.entity.Presence
import com.psob96.bsi_33.database.entity.Student
import org.w3c.dom.Document
import org.w3c.dom.Element
import org.w3c.dom.NodeList
import java.io.StringWriter
import java.lang.reflect.Constructor
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.transform.OutputKeys
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult
import kotlin.reflect.KMutableProperty
import kotlin.reflect.KProperty
import kotlin.reflect.full.*


class XmlMapper {

    fun export(
        students: List<Student>,
        grads: List<Grade>,
        lessons: List<Lesson>,
        presences: List<Presence>
    ): Document {

        val builder = DocumentBuilderFactory.newInstance().newDocumentBuilder()
        val doc = builder.newDocument()
        val root = doc.createElement("data")

        students.forEach { root.appendChild(toXML(doc, it)) }
        grads.forEach { root.appendChild(toXML(doc, it)) }
        lessons.forEach { root.appendChild(toXML(doc, it)) }
        presences.forEach { root.appendChild(toXML(doc, it)) }

        doc.appendChild(root)

        return doc
    }

    private inline fun <reified T : Any> toXML(doc: Document, t: T): Element {
        val root = doc.createElement(T::class.simpleName)

        for (prop in T::class.memberProperties) {
            val value = prop.get(t).toString()
            root.appendChild(doc.createElement(prop.name).apply { textContent = value })
        }
        return root
    }

    inline fun <reified T : Any> fromXML(doc: Document): List<T> {

        val elements: NodeList = doc.getElementsByTagName(T::class.simpleName)

        val list = mutableListOf<T>()

        for (i in (0 until elements.length)) {

            val node = elements.item(i) as Element
            val obj = T::class.java.newInstance()

            for (prop in T::class.memberProperties) {

                val name = prop.name
                val value = node.getElementsByTagName(name).item(0).textContent
                val property = T::class.memberProperties.find { it.name == name }

                if (property is KMutableProperty<*>) {

                    when {
                        prop.returnType.isSubtypeOf(Int::class.createType()) ->
                            property.setter.call(obj, value.toInt())

                        prop.returnType.isSubtypeOf(Boolean::class.createType()) ->
                            property.setter.call(obj, value?.toBoolean())

                        prop.returnType.isSubtypeOf(Float::class.createType()) ->
                            property.setter.call(obj, value.toFloat())

                        prop.returnType.isSubtypeOf(Long::class.createType()) ->
                            property.setter.call(obj, value.toLong())

                        prop.returnType.isSubtypeOf(String::class.createType()) ->
                            property.setter.call(obj, value)
                    }
                }
            }

            list.add(obj)
        }

        return list
    }
}

fun Document.toString2(): String {
    try {
        val sw = StringWriter()
        val tf = TransformerFactory.newInstance()
        val transformer = tf.newTransformer()
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no")
        transformer.setOutputProperty(OutputKeys.METHOD, "xml")
        transformer.setOutputProperty(OutputKeys.INDENT, "yes")
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8")
        transformer.transform(DOMSource(this), StreamResult(sw))

        return sw.toString()
    } catch (ex: Exception) {
        throw RuntimeException("Error converting to String", ex)
    }
}






