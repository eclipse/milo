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
import org.eclipse.milo.opcua.stack.core.types.UaDataType;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.enumerated.PerformUpdateType;

@UaDataType("UpdateStructureDataDetails")
public class UpdateStructureDataDetails extends HistoryUpdateDetails {

    public static final NodeId TypeId = Identifiers.UpdateStructureDataDetails;
    public static final NodeId BinaryEncodingId = Identifiers.UpdateStructureDataDetails_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.UpdateStructureDataDetails_Encoding_DefaultXml;

    protected final PerformUpdateType _performInsertReplace;
    protected final DataValue[] _updateValues;

    public UpdateStructureDataDetails() {
        super(null);
        this._performInsertReplace = null;
        this._updateValues = null;
    }

    public UpdateStructureDataDetails(NodeId _nodeId, PerformUpdateType _performInsertReplace, DataValue[] _updateValues) {
        super(_nodeId);
        this._performInsertReplace = _performInsertReplace;
        this._updateValues = _updateValues;
    }

    public PerformUpdateType getPerformInsertReplace() { return _performInsertReplace; }

    public DataValue[] getUpdateValues() { return _updateValues; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(UpdateStructureDataDetails updateStructureDataDetails, UaEncoder encoder) {
        encoder.encodeNodeId("NodeId", updateStructureDataDetails._nodeId);
        encoder.encodeEnumeration("PerformInsertReplace", updateStructureDataDetails._performInsertReplace);
        encoder.encodeArray("UpdateValues", updateStructureDataDetails._updateValues, encoder::encodeDataValue);
    }

    public static UpdateStructureDataDetails decode(UaDecoder decoder) {
        NodeId _nodeId = decoder.decodeNodeId("NodeId");
        PerformUpdateType _performInsertReplace = decoder.decodeEnumeration("PerformInsertReplace", PerformUpdateType.class);
        DataValue[] _updateValues = decoder.decodeArray("UpdateValues", decoder::decodeDataValue, DataValue.class);

        return new UpdateStructureDataDetails(_nodeId, _performInsertReplace, _updateValues);
    }

    static {
        DelegateRegistry.registerEncoder(UpdateStructureDataDetails::encode, UpdateStructureDataDetails.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(UpdateStructureDataDetails::decode, UpdateStructureDataDetails.class, BinaryEncodingId, XmlEncodingId);
    }

}
