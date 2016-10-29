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

package org.eclipse.milo.opcua.stack.core.types.structured;

import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.serialization.DelegateRegistry;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.types.UaDataType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.enumerated.ServerState;

@UaDataType("RedundantServerDataType")
public class RedundantServerDataType implements UaStructure {

    public static final NodeId TypeId = Identifiers.RedundantServerDataType;
    public static final NodeId BinaryEncodingId = Identifiers.RedundantServerDataType_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.RedundantServerDataType_Encoding_DefaultXml;

    protected final String _serverId;
    protected final UByte _serviceLevel;
    protected final ServerState _serverState;

    public RedundantServerDataType() {
        this._serverId = null;
        this._serviceLevel = null;
        this._serverState = null;
    }

    public RedundantServerDataType(String _serverId, UByte _serviceLevel, ServerState _serverState) {
        this._serverId = _serverId;
        this._serviceLevel = _serviceLevel;
        this._serverState = _serverState;
    }

    public String getServerId() { return _serverId; }

    public UByte getServiceLevel() { return _serviceLevel; }

    public ServerState getServerState() { return _serverState; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(RedundantServerDataType redundantServerDataType, UaEncoder encoder) {
        encoder.encodeString("ServerId", redundantServerDataType._serverId);
        encoder.encodeByte("ServiceLevel", redundantServerDataType._serviceLevel);
        encoder.encodeEnumeration("ServerState", redundantServerDataType._serverState);
    }

    public static RedundantServerDataType decode(UaDecoder decoder) {
        String _serverId = decoder.decodeString("ServerId");
        UByte _serviceLevel = decoder.decodeByte("ServiceLevel");
        ServerState _serverState = decoder.decodeEnumeration("ServerState", ServerState.class);

        return new RedundantServerDataType(_serverId, _serviceLevel, _serverState);
    }

    static {
        DelegateRegistry.registerEncoder(RedundantServerDataType::encode, RedundantServerDataType.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(RedundantServerDataType::decode, RedundantServerDataType.class, BinaryEncodingId, XmlEncodingId);
    }

}
