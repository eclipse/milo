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
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@UaDataType("ViewDescription")
public class ViewDescription implements UaStructure {

    public static final NodeId TypeId = Identifiers.ViewDescription;
    public static final NodeId BinaryEncodingId = Identifiers.ViewDescription_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.ViewDescription_Encoding_DefaultXml;

    protected final NodeId _viewId;
    protected final DateTime _timestamp;
    protected final UInteger _viewVersion;

    public ViewDescription() {
        this._viewId = null;
        this._timestamp = null;
        this._viewVersion = null;
    }

    public ViewDescription(NodeId _viewId, DateTime _timestamp, UInteger _viewVersion) {
        this._viewId = _viewId;
        this._timestamp = _timestamp;
        this._viewVersion = _viewVersion;
    }

    public NodeId getViewId() { return _viewId; }

    public DateTime getTimestamp() { return _timestamp; }

    public UInteger getViewVersion() { return _viewVersion; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(ViewDescription viewDescription, UaEncoder encoder) {
        encoder.encodeNodeId("ViewId", viewDescription._viewId);
        encoder.encodeDateTime("Timestamp", viewDescription._timestamp);
        encoder.encodeUInt32("ViewVersion", viewDescription._viewVersion);
    }

    public static ViewDescription decode(UaDecoder decoder) {
        NodeId _viewId = decoder.decodeNodeId("ViewId");
        DateTime _timestamp = decoder.decodeDateTime("Timestamp");
        UInteger _viewVersion = decoder.decodeUInt32("ViewVersion");

        return new ViewDescription(_viewId, _timestamp, _viewVersion);
    }

    static {
        DelegateRegistry.registerEncoder(ViewDescription::encode, ViewDescription.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(ViewDescription::decode, ViewDescription.class, BinaryEncodingId, XmlEncodingId);
    }

}
