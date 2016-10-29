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
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.XmlElement;

public interface DataTypeEncoding {

    public static final DataTypeEncoding OPC_UA = new OpcUaDataTypeEncoding();

    ByteString encodeToByteString(Object object, NodeId encodingTypeId) throws UaSerializationException;

    Object decodeFromByteString(ByteString encoded, NodeId encodingTypeId) throws UaSerializationException;

    XmlElement encodeToXmlElement(Object object, NodeId encodingTypeId) throws UaSerializationException;

    Object decodeFromXmlElement(XmlElement encoded, NodeId encodingTypeId) throws UaSerializationException;

}
