/*
 * Copyright (c) 2016 Kevin Herron
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

package org.eclipse.milo.opcua.stack.core.serialization;

import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.serialization.codec.DataTypeManager;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.XmlElement;

public interface DataTypeEncoding {

    DataTypeEncoding OPC_UA = new OpcUaDataTypeEncoding();

    ByteString encodeToByteString(
        Object object,
        NodeId encodingTypeId,
        DataTypeManager dataTypeManager) throws UaSerializationException;

    XmlElement encodeToXmlElement(
        Object object,
        NodeId encodingTypeId,
        DataTypeManager dataTypeManager) throws UaSerializationException;

    Object decodeFromByteString(
        ByteString encoded,
        NodeId encodingTypeId,
        DataTypeManager dataTypeManager) throws UaSerializationException;

    Object decodeFromXmlElement(
        XmlElement encoded,
        NodeId encodingTypeId,
        DataTypeManager dataTypeManager) throws UaSerializationException;

    default ByteString encodeToByteString(
        Object object,
        NodeId encodingTypeId) throws UaSerializationException {

        return encodeToByteString(object, encodingTypeId, OpcUaDataTypeManager.getInstance());
    }

    default XmlElement encodeToXmlElement(
        Object object,
        NodeId encodingTypeId) throws UaSerializationException {

        return encodeToXmlElement(object, encodingTypeId, OpcUaDataTypeManager.getInstance());
    }

    default Object decodeFromByteString(
        ByteString encoded,
        NodeId encodingTypeId) throws UaSerializationException {

        return decodeFromByteString(encoded, encodingTypeId, OpcUaDataTypeManager.getInstance());
    }

    default Object decodeFromXmlElement(
        XmlElement encoded,
        NodeId encodingTypeId) throws UaSerializationException {

        return decodeFromXmlElement(encoded, encodingTypeId, OpcUaDataTypeManager.getInstance());
    }

}
