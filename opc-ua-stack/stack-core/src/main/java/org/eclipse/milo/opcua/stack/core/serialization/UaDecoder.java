/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.core.serialization;

import java.util.UUID;
import java.util.function.Function;

import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.DiagnosticInfo;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.XmlElement;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.ULong;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;

public interface UaDecoder {

    Boolean readBoolean(String field) throws UaSerializationException;

    Byte readSByte(String field) throws UaSerializationException;

    Short readInt16(String field) throws UaSerializationException;

    Integer readInt32(String field) throws UaSerializationException;

    Long readInt64(String field) throws UaSerializationException;

    UByte readByte(String field) throws UaSerializationException;

    UShort readUInt16(String field) throws UaSerializationException;

    UInteger readUInt32(String field) throws UaSerializationException;

    ULong readUInt64(String field) throws UaSerializationException;

    Float readFloat(String field) throws UaSerializationException;

    Double readDouble(String field) throws UaSerializationException;

    String readString(String field) throws UaSerializationException;

    DateTime readDateTime(String field) throws UaSerializationException;

    UUID readGuid(String field) throws UaSerializationException;

    ByteString readByteString(String field) throws UaSerializationException;

    XmlElement readXmlElement(String field) throws UaSerializationException;

    NodeId readNodeId(String field) throws UaSerializationException;

    ExpandedNodeId readExpandedNodeId(String field) throws UaSerializationException;

    StatusCode readStatusCode(String field) throws UaSerializationException;

    QualifiedName readQualifiedName(String field) throws UaSerializationException;

    LocalizedText readLocalizedText(String field) throws UaSerializationException;

    ExtensionObject readExtensionObject(String field) throws UaSerializationException;

    DataValue readDataValue(String field) throws UaSerializationException;

    Variant readVariant(String field) throws UaSerializationException;

    DiagnosticInfo readDiagnosticInfo(String field) throws UaSerializationException;

    <T> T[] readArray(String field, Function<String, T> decoder, Class<T> clazz) throws UaSerializationException;

    <T extends UaStructure> T readBuiltinStruct(String field, Class<T> typeClass) throws UaSerializationException;

    <T extends UaStructure> T[] readBuiltinStructArray(
        String field,
        Class<T> clazz
    ) throws UaSerializationException;

    Object readStruct(String field, NodeId encodingId) throws UaSerializationException;

    Object[] readStructArray(String field, NodeId encodingId) throws UaSerializationException;

    UaMessage readMessage(String field) throws UaSerializationException;

}
