/*
 * Copyright (c) 2017 Kevin Herron
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v1.0 which accompany this distribution.
 *
 * The Eclipse Public License is available at
 *   http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 *   http://www.eclipse.org/org/documents/edl-v10.html.
 */

package org.eclipse.milo.opcua.stack.core.serialization

import com.google.common.io.CharStreams
import org.eclipse.milo.opcua.stack.core.StatusCodes
import org.eclipse.milo.opcua.stack.core.UaSerializationException
import org.eclipse.milo.opcua.stack.core.serialization.codecs.BuiltinDataTypeCodec
import org.eclipse.milo.opcua.stack.core.serialization.codecs.OpcUaXmlDataTypeCodec
import org.eclipse.milo.opcua.stack.core.serialization.codecs.SerializationContext
import org.eclipse.milo.opcua.stack.core.types.BuiltinDataTypeDictionary
import org.eclipse.milo.opcua.stack.core.types.OpcUaDataTypeManager
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime
import org.eclipse.milo.opcua.stack.core.types.builtin.DiagnosticInfo
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant
import org.eclipse.milo.opcua.stack.core.types.builtin.XmlElement
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.ULong
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort
import org.eclipse.milo.opcua.stack.core.util.ArrayUtil
import org.eclipse.milo.opcua.stack.core.util.Namespaces
import org.w3c.dom.Document
import org.w3c.dom.Node
import org.w3c.dom.NodeList
import java.io.IOException
import java.io.InputStream
import java.io.Reader
import java.io.StringWriter
import java.util.UUID
import java.util.function.Function
import javax.xml.bind.DatatypeConverter
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.transform.TransformerException
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult


class OpcUaXmlStreamDecoder() : UaDecoder {

    companion object {
        private val SERIALIZATION_CONTEXT = SerializationContext {
            OpcUaDataTypeManager.getInstance()
        }
    }

    private val factory = DocumentBuilderFactory.newInstance().apply {
        isCoalescing = true
        isNamespaceAware = true
    }

    private val builder = factory.newDocumentBuilder()

    private lateinit var document: Document
    var currentNode: Node? = null


    @Throws(IOException::class)
    constructor(reader: Reader) : this() {
        setInput(reader)
    }

    @Throws(IOException::class)
    constructor(inputStream: InputStream) : this() {
        setInput(inputStream)
    }

    @Throws(IOException::class)
    fun setInput(reader: Reader): OpcUaXmlStreamDecoder {
        return setInput(CharStreams.toString(reader).byteInputStream())
    }

    @Throws(IOException::class)
    fun setInput(inputStream: InputStream): OpcUaXmlStreamDecoder {
        document = builder.parse(inputStream)
        currentNode = document.firstChild

        return this
    }

    private fun currentNode(field: String?): Node {
        val node: Node = currentNode ?:
            throw UaSerializationException(StatusCodes.Bad_DecodingError, "node == null")

        if (field != null && field != node.localName) {
            throw UaSerializationException(
                StatusCodes.Bad_DecodingError,
                "expected element: $field found: ${node.localName}"
            )
        }

        return node
    }

    override fun readBoolean(field: String?): Boolean {
        val node: Node = currentNode ?:
            throw UaSerializationException(StatusCodes.Bad_DecodingError, "node == null")

        if (field != null && field != node.localName) {
            throw UaSerializationException(
                StatusCodes.Bad_DecodingError,
                "expected element: $field found: ${node.localName}"
            )
        }

        try {
            return DatatypeConverter.parseBoolean(node.textContent)
        } finally {
            currentNode = node.nextSibling
        }
    }

    override fun readSByte(field: String?): Byte? {
        val node: Node = currentNode(field)

        try {
            return DatatypeConverter.parseByte(node.textContent)
        } finally {
            currentNode = node.nextSibling
        }
    }

    override fun readInt16(field: String?): Short? {
        val node: Node = currentNode(field)

        try {
            return DatatypeConverter.parseShort(node.textContent)
        } finally {
            currentNode = node.nextSibling
        }
    }

    override fun readInt32(field: String?): Int? {
        val node: Node = currentNode ?:
            throw UaSerializationException(StatusCodes.Bad_DecodingError, "node == null")

        if (field != null && field != node.localName) {
            throw UaSerializationException(
                StatusCodes.Bad_DecodingError,
                "expected element: $field found: ${node.localName}"
            )
        }

        try {
            return DatatypeConverter.parseInt(node.textContent)
        } finally {
            currentNode = node.nextSibling
        }
    }

    override fun readInt64(field: String?): Long? {
        val node: Node = currentNode(field)

        try {
            return DatatypeConverter.parseLong(node.textContent)
        } finally {
            currentNode = node.nextSibling
        }
    }

    override fun readByte(field: String?): UByte {
        val node: Node = currentNode(field)

        try {
            return UByte.valueOf(DatatypeConverter.parseShort(node.textContent))
        } finally {
            currentNode = node.nextSibling
        }
    }

    override fun readUInt16(field: String?): UShort {
        val node: Node = currentNode(field)

        try {
            return UShort.valueOf(DatatypeConverter.parseInt(node.textContent))
        } finally {
            currentNode = node.nextSibling
        }
    }

    override fun readUInt32(field: String?): UInteger {
        val node: Node = currentNode ?:
            throw UaSerializationException(StatusCodes.Bad_DecodingError, "node == null")

        if (field != null && field != node.localName) {
            throw UaSerializationException(
                StatusCodes.Bad_DecodingError,
                "expected element: $field found: ${node.localName}"
            )
        }

        try {
            return UInteger.valueOf(DatatypeConverter.parseLong(node.textContent))
        } finally {
            currentNode = node.nextSibling
        }
    }

    override fun readUInt64(field: String?): ULong {
        val node: Node = currentNode(field)

        try {
            return ULong.valueOf(DatatypeConverter.parseInteger(node.textContent))
        } finally {
            currentNode = node.nextSibling
        }
    }

    override fun readFloat(field: String?): Float? {
        val node: Node = currentNode(field)

        try {
            return DatatypeConverter.parseFloat(node.textContent)
        } finally {
            currentNode = node.nextSibling
        }
    }

    override fun readDouble(field: String?): Double? {
        val node: Node = currentNode(field)

        try {
            return DatatypeConverter.parseDouble(node.textContent)
        } finally {
            currentNode = node.nextSibling
        }
    }

    override fun readString(field: String?): String {
        val node: Node = currentNode ?:
            throw UaSerializationException(StatusCodes.Bad_DecodingError, "node == null")

        if (field != null && field != node.localName) {
            throw UaSerializationException(
                StatusCodes.Bad_DecodingError,
                "expected element: $field found: ${node.localName}"
            )
        }

        try {
            return node.textContent
        } finally {
            currentNode = node.nextSibling
        }
    }

    override fun readDateTime(field: String?): DateTime {
        val node: Node = currentNode ?:
            throw UaSerializationException(StatusCodes.Bad_DecodingError, "node == null")

        if (field != null && field != node.localName) {
            throw UaSerializationException(
                StatusCodes.Bad_DecodingError,
                "expected element: $field found: ${node.localName}"
            )
        }

        try {
            return DateTime(DatatypeConverter.parseDateTime(node.textContent).time)
        } finally {
            currentNode = node.nextSibling
        }
    }

    override fun readGuid(field: String?): UUID {
        val node: Node = currentNode(field)

        try {
            return UUID.fromString(node.textContent)
        } finally {
            currentNode = node.nextSibling
        }
    }

    override fun readByteString(field: String?): ByteString {
        val node: Node = currentNode(field)

        try {
            val bs = DatatypeConverter.parseBase64Binary(node.textContent)
            return ByteString.of(bs)
        } finally {
            currentNode = node.nextSibling
        }
    }

    override fun readXmlElement(field: String?): XmlElement {
        val node: Node = currentNode(field)

        try {
            return node.toXmlElement()
        } finally {
            currentNode = node.nextSibling
        }
    }

    override fun readNodeId(field: String?): NodeId {
        val node: Node = currentNode ?:
            throw UaSerializationException(StatusCodes.Bad_DecodingError, "node == null")

        if (field != null && field != node.localName) {
            throw UaSerializationException(
                StatusCodes.Bad_DecodingError,
                "expected element: $field found: ${node.localName}"
            )
        }

        try {
            val idNode: Node? = node.firstChild

            return idNode?.let { NodeId.parse(it.textContent) } ?: NodeId.NULL_VALUE
        } finally {
            currentNode = node.nextSibling
        }
    }

    override fun readExpandedNodeId(field: String?): ExpandedNodeId {
        val node: Node = currentNode(field)

        try {
            val idNode: Node? = node.firstChild

            return idNode?.let { ExpandedNodeId.parse(it.textContent) } ?: ExpandedNodeId.NULL_VALUE
        } finally {
            currentNode = node.nextSibling
        }
    }

    override fun readStatusCode(field: String?): StatusCode {
        val node: Node = currentNode(field)

        try {
            val code: Long? = node.childNodes["Code"]?.let {
                DatatypeConverter.parseUnsignedInt(it.textContent)
            }

            return StatusCode(code ?: 0L)
        } finally {
            currentNode = node.nextSibling
        }
    }

    override fun readQualifiedName(field: String?): QualifiedName {
        val node: Node = currentNode ?:
            throw UaSerializationException(StatusCodes.Bad_DecodingError, "node == null")

        if (field != null && field != node.localName) {
            throw UaSerializationException(
                StatusCodes.Bad_DecodingError,
                "expected element: $field found: ${node.localName}"
            )
        }

        val nodeMap = node.childNodes.toMap()

        val namespaceIndex: Int? = nodeMap["NamespaceIndex"]?.let {
            DatatypeConverter.parseInt(it.textContent)
        }

        val name: String? = nodeMap["Name"]?.textContent

        try {
            return QualifiedName(namespaceIndex ?: 0, name)
        } finally {
            currentNode = node.nextSibling
        }
    }

    override fun readLocalizedText(field: String?): LocalizedText {
        val node: Node = currentNode ?:
            throw UaSerializationException(StatusCodes.Bad_DecodingError, "node == null")

        if (field != null && field != node.localName) {
            throw UaSerializationException(
                StatusCodes.Bad_DecodingError,
                "expected element: $field found: ${node.localName}"
            )
        }

        val nodeMap = node.childNodes.toMap()

        val locale: String? = nodeMap["Locale"]?.textContent
        val text: String? = nodeMap["Text"]?.textContent

        try {
            return LocalizedText(locale, text)
        } finally {
            currentNode = node.nextSibling
        }
    }

    override fun readExtensionObject(field: String?): ExtensionObject {
        val node: Node = currentNode(field)

        val nodeMap = node.childNodes.toMap()

        val typeId: NodeId = nodeMap["TypeId"]?.run {
            currentNode = this

            readNodeId("TypeId")
        } ?: NodeId.NULL_VALUE

        val extensionObject: ExtensionObject? = nodeMap["Body"]?.run {
            if (localName == "ByteString" && namespaceURI == Namespaces.OPC_UA_XSD) {
                currentNode = this
                ExtensionObject(readByteString(localName), typeId)
            } else {
                ExtensionObject(this.toXmlElement(), typeId)
            }
        }

        try {
            return extensionObject ?: ExtensionObject(XmlElement(""), typeId)
        } finally {
            currentNode = node.nextSibling
        }
    }

    override fun readDataValue(field: String?): DataValue {
        val node: Node = currentNode(field)

        val nodeMap = node.childNodes.toMap()

        val value: Variant = nodeMap["Value"]?.let {
            currentNode = it
            readVariant("Value")
        } ?: Variant.NULL_VALUE

        val statusCode: StatusCode = nodeMap["StatusCode"]?.let {
            currentNode = it
            readStatusCode("StatusCode")
        } ?: StatusCode.GOOD

        val sourceTimestamp = nodeMap["SourceTimestamp"]?.let {
            currentNode = it
            readDateTime("SourceTimestamp")
        }

        val sourcePicoseconds = nodeMap["SourcePicoseconds"]?.let {
            currentNode = it
            readUInt16("SourcePicoseconds")
        }

        val serverTimestamp = nodeMap["ServerTimestamp"]?.let {
            currentNode = it
            readDateTime("ServerTimestamp")
        }

        val serverPicoseconds = nodeMap["ServerPicoseconds"]?.let {
            currentNode = it
            readUInt16("ServerPicoseconds")
        }

        try {
            return DataValue(
                value,
                statusCode,
                sourceTimestamp,
                sourcePicoseconds,
                serverTimestamp,
                serverPicoseconds
            )
        } finally {
            currentNode = node.nextSibling
        }
    }

    override fun readVariant(field: String?): Variant {
        val node: Node = currentNode ?:
            throw UaSerializationException(StatusCodes.Bad_DecodingError, "node == null")

        if (field != null && field != node.localName) {
            throw UaSerializationException(
                StatusCodes.Bad_DecodingError,
                "expected element: $field found: ${node.localName}"
            )
        }

        currentNode = node.firstChild?.firstChild

        val value: Any? = readVariantValue()

        try {
            return Variant(value)
        } finally {
            currentNode = node.nextSibling
        }
    }

    fun readVariantValue(): Any? {
        val valueNode = currentNode ?:
            throw UaSerializationException(StatusCodes.Bad_DecodingError, "node == null")

        val valueNodeName = valueNode.localName

        if (valueNodeName.startsWith("ListOf")) {
            val type = valueNodeName.substringAfter("ListOf")

            val values = mutableListOf<Any?>()
            val childNodes = valueNode.childNodes

            for (i in 0 until childNodes.length) {
                currentNode = childNodes.item(i)
                values.add(readBuiltinType(type, type))
            }

            val array = java.lang.reflect.Array.newInstance(builtinTypeClass(type), values.size).apply {
                values.forEachIndexed { index, value ->
                    java.lang.reflect.Array.set(this, index, value)
                }
            }

            return array
        } else if (valueNodeName == "Matrix") {
            val dimensions = mutableListOf<Int>()
            val dimensionsNode = valueNode.firstChild
            for (i in 0 until dimensionsNode.childNodes.length) {
                currentNode = dimensionsNode.childNodes.item(i)
                dimensions.add(readInt32("Int32") ?: 0)
            }

            val elements = mutableListOf<Any?>()
            val elementsNode = dimensionsNode.nextSibling
            for (i in 0 until elementsNode.childNodes.length) {
                currentNode = elementsNode.childNodes.item(i)
                val type = currentNode?.localName!!
                elements.add(readBuiltinType(type, type))
            }

            val clazz = elements.first()?.javaClass
            val array = java.lang.reflect.Array.newInstance(clazz, elements.size).apply {
                elements.forEachIndexed { index, value ->
                    java.lang.reflect.Array.set(this, index, value)
                }
            }

            return ArrayUtil.unflatten(array, IntArray(dimensions.size, { i -> dimensions[i] }))
        } else {
            return readBuiltinType(valueNodeName, valueNodeName)
        }
    }

    private fun readBuiltinType(field: String?, type: String): Any? {
        return when (type) {
            "Boolean" -> readBoolean(field)
            "SByte" -> readSByte(field)
            "Int16" -> readInt16(field)
            "Int32" -> readInt32(field)
            "Int64" -> readInt64(field)
            "Byte" -> readByte(field)
            "UInt16" -> readUInt16(field)
            "UInt32" -> readUInt32(field)
            "UInt64" -> readUInt64(field)
            "Float" -> readFloat(field)
            "Double" -> readDouble(field)
            "String" -> readString(field)
            "DateTime" -> readDateTime(field)
            "Guid" -> readGuid(field)
            "ByteString" -> readByteString(field)
            "XmlElement" -> readXmlElement(field)
            "NodeId" -> readNodeId(field)
            "ExpandedNodeId" -> readExpandedNodeId(field)
            "StatusCode" -> readStatusCode(field)
            "QualifiedName" -> readQualifiedName(field)
            "LocalizedText" -> readLocalizedText(field)
            "ExtensionObject" -> readExtensionObject(field)
            "DataValue" -> readDataValue(field)
            "Variant" -> readVariant(field)
            "DiagnosticInfo" -> readDiagnosticInfo(field)
            else -> throw UaSerializationException(StatusCodes.Bad_DecodingError, "not builtin type: $type")
        }
    }

    private fun builtinTypeClass(type: String): Class<*> {
        return when (type) {
            "Boolean" -> Boolean::class.javaObjectType
            "SByte" -> Byte::class.javaObjectType
            "Int16" -> Short::class.javaObjectType
            "Int32" -> Int::class.javaObjectType
            "Int64" -> Long::class.javaObjectType
            "Byte" -> UByte::class.java
            "UInt16" -> UShort::class.java
            "UInt32" -> UInteger::class.java
            "UInt64" -> ULong::class.java
            "Float" -> Float::class.javaObjectType
            "Double" -> Double::class.javaObjectType
            "String" -> String::class.javaObjectType
            "DateTime" -> DateTime::class.java
            "Guid" -> UUID::class.java
            "ByteString" -> ByteString::class.java
            "XmlElement" -> XmlElement::class.java
            "NodeId" -> NodeId::class.java
            "ExpandedNodeId" -> ExpandedNodeId::class.java
            "StatusCode" -> StatusCode::class.java
            "QualifiedName" -> QualifiedName::class.java
            "LocalizedText" -> LocalizedText::class.java
            "ExtensionObject" -> ExtensionObject::class.java
            "DataValue" -> DataValue::class.java
            "Variant" -> Variant::class.java
            "DiagnosticInfo" -> DiagnosticInfo::class.java
            else -> throw UaSerializationException(StatusCodes.Bad_DecodingError, "not builtin type: $type")
        }
    }

    override fun readDiagnosticInfo(field: String?): DiagnosticInfo {
        val node: Node = currentNode(field)

        val nodeMap = node.childNodes.toMap()

        val symbolicId: Int = nodeMap["SymbolicId"]?.let {
            currentNode = it
            readInt32("SymbolicId")
        } ?: -1

        val namespaceUri: Int = nodeMap["NamespaceUri"]?.let {
            currentNode = it
            readInt32("NamespaceUri")
        } ?: -1

        val locale: Int = nodeMap["Locale"]?.let {
            currentNode = it
            readInt32("Locale")
        } ?: -1

        val localizedText: Int = nodeMap["LocalizedText"]?.let {
            currentNode = it
            readInt32("LocalizedText")
        } ?: -1

        val additionalInfo: String? = nodeMap["AdditionalInfo"]?.let {
            currentNode = it
            readString("AdditionalInfo")
        }

        val innerStatusCode: StatusCode? = nodeMap["InnerStatusCode"]?.let {
            currentNode = it
            readStatusCode("InnerStatusCode")
        }

        val innerDiagnosticInfo: DiagnosticInfo? = nodeMap["InnerDiagnosticInfo"]?.let {
            currentNode = it
            readDiagnosticInfo("InnerDiagnosticInfo")
        }


        try {
            return DiagnosticInfo(
                namespaceUri,
                symbolicId,
                locale,
                localizedText,
                additionalInfo,
                innerStatusCode,
                innerDiagnosticInfo
            )
        } finally {
            currentNode = node.nextSibling
        }
    }

    override fun <T> readArray(field: String?, decoder: Function<String?, T?>, clazz: Class<T>): Array<T> {
        val node: Node = currentNode ?:
            throw UaSerializationException(StatusCodes.Bad_DecodingError, "node == null")

        if (field != null && field != node.localName) {
            throw UaSerializationException(
                StatusCodes.Bad_DecodingError,
                "expected element: $field found: ${node.localName}"
            )
        }

        val values = mutableListOf<Any?>()
        val listNode: Node = node.firstChild
        val childNodes = listNode.childNodes

        for (i in 0 until childNodes.length) {
            currentNode = childNodes.item(i)
            values.add(decoder.apply(currentNode?.localName))
        }

        try {
            val array = java.lang.reflect.Array.newInstance(clazz, values.size).apply {
                values.forEachIndexed { index, value ->
                    java.lang.reflect.Array.set(this, index, value)
                }
            }
            @Suppress("UNCHECKED_CAST")
            return array as Array<T>
        } finally {
            currentNode = node.nextSibling
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : UaStructure> readBuiltinStruct(field: String?, clazz: Class<T>): T {
        val node: Node = currentNode ?:
            throw UaSerializationException(StatusCodes.Bad_DecodingError, "node == null")

        if (field != null && field != node.localName) {
            throw UaSerializationException(
                StatusCodes.Bad_DecodingError,
                "expected element: $field found: ${node.localName}"
            )
        }

        val codec = BuiltinDataTypeDictionary.getBuiltinCodec(clazz) as BuiltinDataTypeCodec<UaStructure>?
        codec ?: throw UaSerializationException(StatusCodes.Bad_DecodingError, "no codec registered: $clazz")

        try {
            return codec.decode(SERIALIZATION_CONTEXT, this) as T
        } finally {
            currentNode = node.nextSibling
        }
    }

    override fun <T : UaStructure> readBuiltinStructArray(field: String?, clazz: Class<T>): Array<T> {
        return readArray(field, { _ -> readBuiltinStruct(null, clazz) }, clazz)
    }

    override fun readStruct(field: String?, encodingId: NodeId): Any {
        val node: Node = currentNode(field)

        val codec: OpcUaXmlDataTypeCodec<*> = xmlCodec(encodingId)

        currentNode = node.firstChild

        try {
            return codec.decode(SERIALIZATION_CONTEXT, this).also { currentNode = node.nextSibling }
        } finally {
            currentNode = node.nextSibling
        }
    }

    override fun readStructArray(field: String?, encodingId: NodeId): Array<Any> {
        val node: Node = currentNode(field)

        val codec: OpcUaXmlDataTypeCodec<*> = xmlCodec(encodingId)

        val values = mutableListOf<Any?>()
        val listNode: Node = node.firstChild
        val childNodes = listNode.childNodes

        for (i in 0 until childNodes.length) {
            currentNode = childNodes.item(i)

            values.add(readStruct(currentNode?.localName, encodingId))
        }

        try {
            val array = java.lang.reflect.Array.newInstance(codec.type, values.size).apply {
                values.forEachIndexed { index, value ->
                    java.lang.reflect.Array.set(this, index, value)
                }
            }
            @Suppress("UNCHECKED_CAST")
            return array as Array<Any>
        } finally {
            currentNode = node.nextSibling
        }
    }

    private fun xmlCodec(encodingId: NodeId): OpcUaXmlDataTypeCodec<*> {
        val codec = OpcUaDataTypeManager.getInstance().getXmlCodec(encodingId)
        codec ?: throw UaSerializationException(StatusCodes.Bad_DecodingError, "no codec registered: $encodingId")
        return codec
    }

    override fun readMessage(field: String?): UaMessage {
        val node: Node = currentNode(field)

        val typeName = node.localName
        val codec = BuiltinDataTypeDictionary.getBuiltinCodec(typeName)
        codec ?: throw UaSerializationException(StatusCodes.Bad_DecodingError, "no codec registered: $typeName")

        currentNode = node.firstChild

        try {
            return codec.decode(SERIALIZATION_CONTEXT, this) as UaMessage
        } finally {
            currentNode = node.nextSibling
        }
    }

    private operator fun NodeList.get(name: String): Node? = node(name)

    private fun NodeList.node(name: String): Node? {
        for (i in 0 until this.length) {
            val node: Node = item(i)

            if (node.localName == name) {
                return node
            }
        }
        return null
    }

    private fun NodeList.toMap(): Map<String, Node> {
        val map = mutableMapOf<String, Node>()

        for (i in 0 until this.length) {
            val node: Node = item(i)
            map[node.localName] = node
        }

        return map
    }

    private fun Node.toXmlElement(): XmlElement {
        try {
            val sw = StringWriter()

            val transformer = TransformerFactory.newInstance().newTransformer()
            transformer.setOutputProperty("omit-xml-declaration", "yes")
            transformer.transform(DOMSource(this), StreamResult(sw))

            return XmlElement(sw.toString())
        } catch (e: TransformerException) {
            throw UaSerializationException(StatusCodes.Bad_DecodingError, e)
        }
    }

}