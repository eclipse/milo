/*
 * Copyright (c) 2022 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.core.types.structured;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.serialization.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.types.UaRequestMessageType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part4/5.10.5/#5.10.5.2">https://reference.opcfoundation.org/v105/Core/docs/Part4/5.10.5/#5.10.5.2</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class HistoryUpdateRequest extends Structure implements UaRequestMessageType {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=698");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=700");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=699");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15287");

    private final RequestHeader requestHeader;

    private final ExtensionObject[] historyUpdateDetails;

    public HistoryUpdateRequest(RequestHeader requestHeader, ExtensionObject[] historyUpdateDetails) {
        this.requestHeader = requestHeader;
        this.historyUpdateDetails = historyUpdateDetails;
    }

    @Override
    public ExpandedNodeId getTypeId() {
        return TYPE_ID;
    }

    @Override
    public ExpandedNodeId getBinaryEncodingId() {
        return BINARY_ENCODING_ID;
    }

    @Override
    public ExpandedNodeId getXmlEncodingId() {
        return XML_ENCODING_ID;
    }

    @Override
    public ExpandedNodeId getJsonEncodingId() {
        return JSON_ENCODING_ID;
    }

    public RequestHeader getRequestHeader() {
        return requestHeader;
    }

    public ExtensionObject[] getHistoryUpdateDetails() {
        return historyUpdateDetails;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 700),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("RequestHeader", LocalizedText.NULL_VALUE, new NodeId(0, 389), -1, null, UInteger.valueOf(0), false),
                new StructureField("HistoryUpdateDetails", LocalizedText.NULL_VALUE, new NodeId(0, 22), 1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<HistoryUpdateRequest> {
        @Override
        public Class<HistoryUpdateRequest> getType() {
            return HistoryUpdateRequest.class;
        }

        @Override
        public HistoryUpdateRequest decodeType(SerializationContext context, UaDecoder decoder) {
            RequestHeader requestHeader = (RequestHeader) decoder.decodeStruct("RequestHeader", RequestHeader.TYPE_ID);
            ExtensionObject[] historyUpdateDetails = decoder.decodeExtensionObjectArray("HistoryUpdateDetails");
            return new HistoryUpdateRequest(requestHeader, historyUpdateDetails);
        }

        @Override
        public void encodeType(SerializationContext context, UaEncoder encoder,
                               HistoryUpdateRequest value) {
            encoder.encodeStruct("RequestHeader", value.getRequestHeader(), RequestHeader.TYPE_ID);
            encoder.encodeExtensionObjectArray("HistoryUpdateDetails", value.getHistoryUpdateDetails());
        }
    }
}
