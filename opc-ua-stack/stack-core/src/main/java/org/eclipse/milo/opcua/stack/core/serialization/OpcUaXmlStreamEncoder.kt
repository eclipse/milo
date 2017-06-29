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

import org.eclipse.milo.opcua.stack.core.StatusCodes
import org.eclipse.milo.opcua.stack.core.UaSerializationException
import org.eclipse.milo.opcua.stack.core.serialization.codecs.BuiltinDataTypeCodec
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
import org.eclipse.milo.opcua.stack.core.util.Namespaces
import org.w3c.dom.Document
import org.w3c.dom.Node
import java.util.UUID
import java.util.function.BiConsumer
import javax.xml.parsers.DocumentBuilderFactory

class OpcUaXmlStreamEncoder : UaEncoder {

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

    val document: Document = builder.newDocument()

    private var currentNode: Node = document

    fun getDocumentXml(): String {
        TODO("not implemented")
    }

    override fun writeBoolean(field: String?, value: Boolean?) {
        val element = document.createElementNS(Namespaces.OPC_UA_XSD, field)

        element.appendChild(document.createTextNode(value.toString()))

        currentNode.appendChild(element)
    }

    override fun writeSByte(field: String?, value: Byte?) {
        TODO("not implemented")
    }

    override fun writeInt16(field: String?, value: Short?) {
        TODO("not implemented")
    }

    override fun writeInt32(field: String?, value: Int?) {
        TODO("not implemented")
    }

    override fun writeInt64(field: String?, value: Long?) {
        TODO("not implemented")
    }

    override fun writeByte(field: String?, value: UByte?) {
        TODO("not implemented")
    }

    override fun writeUInt16(field: String?, value: UShort?) {
        TODO("not implemented")
    }

    override fun writeUInt32(field: String?, value: UInteger?) {
        TODO("not implemented")
    }

    override fun writeUInt64(field: String?, value: ULong?) {
        TODO("not implemented")
    }

    override fun writeFloat(field: String?, value: Float?) {
        TODO("not implemented")
    }

    override fun writeDouble(field: String?, value: Double?) {
        TODO("not implemented")
    }

    override fun writeString(field: String?, value: String?) {
        TODO("not implemented")
    }

    override fun writeDateTime(field: String?, value: DateTime?) {
        TODO("not implemented")
    }

    override fun writeGuid(field: String?, value: UUID?) {
        TODO("not implemented")
    }

    override fun writeByteString(field: String?, value: ByteString?) {
        TODO("not implemented")
    }

    override fun writeXmlElement(field: String?, value: XmlElement?) {
        TODO("not implemented")
    }

    override fun writeNodeId(field: String?, value: NodeId?) {
        TODO("not implemented")
    }

    override fun writeExpandedNodeId(field: String?, value: ExpandedNodeId?) {
        TODO("not implemented")
    }

    override fun writeStatusCode(field: String?, value: StatusCode?) {
        TODO("not implemented")
    }

    override fun writeQualifiedName(field: String?, value: QualifiedName?) {
        TODO("not implemented")
    }

    override fun writeLocalizedText(field: String?, value: LocalizedText?) {
        TODO("not implemented")
    }

    override fun writeExtensionObject(field: String?, value: ExtensionObject?) {
        TODO("not implemented")
    }

    override fun writeDataValue(field: String?, value: DataValue?) {
        TODO("not implemented")
    }

    override fun writeVariant(field: String?, value: Variant?) {
        val element = document.createElementNS(Namespaces.OPC_UA_XSD, field)

        val valueElement = document.createElement("Value")
        element.appendChild(valueElement)

        val v: Any? = value?.value
        TODO("not implemented")
    }

    override fun writeDiagnosticInfo(field: String?, value: DiagnosticInfo?) {
        TODO("not implemented")
    }

    override fun <T : Any?> writeArray(field: String?, values: Array<out T>?, encoder: BiConsumer<String, T>?) {
        TODO("not implemented")
    }

    override fun <T : UaStructure> writeBuiltinStruct(field: String?, value: T, clazz: Class<T>) {
        val node: Node = currentNode

        @Suppress("UNCHECKED_CAST")
        val codec: BuiltinDataTypeCodec<T> =
            BuiltinDataTypeDictionary.getBuiltinCodec(clazz) as? BuiltinDataTypeCodec<T> ?:
                throw UaSerializationException(StatusCodes.Bad_DecodingError, "no codec found: $clazz")

        val element = document.createElementNS(Namespaces.OPC_UA_XSD, field)

        currentNode = element
        codec.encode(SERIALIZATION_CONTEXT, value, this)

        currentNode = node
    }

    override fun <T : UaStructure?> writeBuiltinStructArray(field: String?, values: Array<out T>?, clazz: Class<T>?) {
        TODO("not implemented")
    }

    override fun writeStruct(field: String?, value: Any?, encodingId: NodeId?) {
        TODO("not implemented")
    }

    override fun writeStructArray(field: String?, value: Array<out Any>?, encodingId: NodeId?) {
        TODO("not implemented")
    }

    override fun writeMessage(field: String?, message: UaMessage?) {
        TODO("not implemented")
    }
}