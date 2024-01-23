/*
 * Copyright (c) 2024 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.core.types.structured;

import java.util.StringJoiner;

import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.encoding.EncodingContext;
import org.eclipse.milo.opcua.stack.core.encoding.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.encoding.UaDecoder;
import org.eclipse.milo.opcua.stack.core.encoding.UaEncoder;
import org.eclipse.milo.opcua.stack.core.types.UaRequestMessageType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;
import org.jetbrains.annotations.Nullable;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part4/5.8.2/#5.8.2.2">https://reference.opcfoundation.org/v105/Core/docs/Part4/5.8.2/#5.8.2.2</a>
 */
public class BrowseRequest extends Structure implements UaRequestMessageType {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=525");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=527");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=526");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15184");

    private final RequestHeader requestHeader;

    private final ViewDescription view;

    private final UInteger requestedMaxReferencesPerNode;

    private final BrowseDescription @Nullable [] nodesToBrowse;

    public BrowseRequest(RequestHeader requestHeader, ViewDescription view,
                         UInteger requestedMaxReferencesPerNode, BrowseDescription @Nullable [] nodesToBrowse) {
        this.requestHeader = requestHeader;
        this.view = view;
        this.requestedMaxReferencesPerNode = requestedMaxReferencesPerNode;
        this.nodesToBrowse = nodesToBrowse;
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

    public ViewDescription getView() {
        return view;
    }

    public UInteger getRequestedMaxReferencesPerNode() {
        return requestedMaxReferencesPerNode;
    }

    public BrowseDescription @Nullable [] getNodesToBrowse() {
        return nodesToBrowse;
    }

    @Override
    public String toString() {
        var joiner = new StringJoiner(", ", BrowseRequest.class.getSimpleName() + "[", "]");
        joiner.add("requestHeader=" + getRequestHeader());
        joiner.add("view=" + getView());
        joiner.add("requestedMaxReferencesPerNode=" + getRequestedMaxReferencesPerNode());
        joiner.add("nodesToBrowse=" + java.util.Arrays.toString(getNodesToBrowse()));
        return joiner.toString();
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 527),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("RequestHeader", LocalizedText.NULL_VALUE, new NodeId(0, 389), -1, null, UInteger.valueOf(0), false),
                new StructureField("View", LocalizedText.NULL_VALUE, new NodeId(0, 511), -1, null, UInteger.valueOf(0), false),
                new StructureField("RequestedMaxReferencesPerNode", LocalizedText.NULL_VALUE, new NodeId(0, 289), -1, null, UInteger.valueOf(0), false),
                new StructureField("NodesToBrowse", LocalizedText.NULL_VALUE, new NodeId(0, 514), 1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<BrowseRequest> {
        @Override
        public Class<BrowseRequest> getType() {
            return BrowseRequest.class;
        }

        @Override
        public BrowseRequest decodeType(EncodingContext context, UaDecoder decoder) {
            RequestHeader requestHeader = (RequestHeader) decoder.decodeStruct("RequestHeader", RequestHeader.TYPE_ID);
            ViewDescription view = (ViewDescription) decoder.decodeStruct("View", ViewDescription.TYPE_ID);
            UInteger requestedMaxReferencesPerNode = decoder.decodeUInt32("RequestedMaxReferencesPerNode");
            BrowseDescription[] nodesToBrowse = (BrowseDescription[]) decoder.decodeStructArray("NodesToBrowse", BrowseDescription.TYPE_ID);
            return new BrowseRequest(requestHeader, view, requestedMaxReferencesPerNode, nodesToBrowse);
        }

        @Override
        public void encodeType(EncodingContext context, UaEncoder encoder, BrowseRequest value) {
            encoder.encodeStruct("RequestHeader", value.getRequestHeader(), RequestHeader.TYPE_ID);
            encoder.encodeStruct("View", value.getView(), ViewDescription.TYPE_ID);
            encoder.encodeUInt32("RequestedMaxReferencesPerNode", value.getRequestedMaxReferencesPerNode());
            encoder.encodeStructArray("NodesToBrowse", value.getNodesToBrowse(), BrowseDescription.TYPE_ID);
        }
    }
}
