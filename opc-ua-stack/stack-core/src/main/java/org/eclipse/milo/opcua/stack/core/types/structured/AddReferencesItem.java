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

package org.eclipse.milo.opcua.stack.core.types.structured;

import com.google.common.base.MoreObjects;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.BuiltinDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;

public class AddReferencesItem implements UaStructure {

    public static final NodeId TypeId = Identifiers.AddReferencesItem;
    public static final NodeId BinaryEncodingId = Identifiers.AddReferencesItem_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.AddReferencesItem_Encoding_DefaultXml;

    protected final NodeId sourceNodeId;
    protected final NodeId referenceTypeId;
    protected final Boolean isForward;
    protected final String targetServerUri;
    protected final ExpandedNodeId targetNodeId;
    protected final NodeClass targetNodeClass;

    public AddReferencesItem() {
        this.sourceNodeId = null;
        this.referenceTypeId = null;
        this.isForward = null;
        this.targetServerUri = null;
        this.targetNodeId = null;
        this.targetNodeClass = null;
    }

    public AddReferencesItem(NodeId sourceNodeId, NodeId referenceTypeId, Boolean isForward, String targetServerUri, ExpandedNodeId targetNodeId, NodeClass targetNodeClass) {
        this.sourceNodeId = sourceNodeId;
        this.referenceTypeId = referenceTypeId;
        this.isForward = isForward;
        this.targetServerUri = targetServerUri;
        this.targetNodeId = targetNodeId;
        this.targetNodeClass = targetNodeClass;
    }

    public NodeId getSourceNodeId() { return sourceNodeId; }

    public NodeId getReferenceTypeId() { return referenceTypeId; }

    public Boolean getIsForward() { return isForward; }

    public String getTargetServerUri() { return targetServerUri; }

    public ExpandedNodeId getTargetNodeId() { return targetNodeId; }

    public NodeClass getTargetNodeClass() { return targetNodeClass; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("SourceNodeId", sourceNodeId)
            .add("ReferenceTypeId", referenceTypeId)
            .add("IsForward", isForward)
            .add("TargetServerUri", targetServerUri)
            .add("TargetNodeId", targetNodeId)
            .add("TargetNodeClass", targetNodeClass)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<AddReferencesItem> {

        @Override
        public Class<AddReferencesItem> getType() {
            return AddReferencesItem.class;
        }

        @Override
        public AddReferencesItem decode(UaDecoder decoder) throws UaSerializationException {
            NodeId sourceNodeId = decoder.readNodeId("SourceNodeId");
            NodeId referenceTypeId = decoder.readNodeId("ReferenceTypeId");
            Boolean isForward = decoder.readBoolean("IsForward");
            String targetServerUri = decoder.readString("TargetServerUri");
            ExpandedNodeId targetNodeId = decoder.readExpandedNodeId("TargetNodeId");
            NodeClass targetNodeClass = NodeClass.from(decoder.readInt32("TargetNodeClass"));

            return new AddReferencesItem(sourceNodeId, referenceTypeId, isForward, targetServerUri, targetNodeId, targetNodeClass);
        }

        @Override
        public void encode(AddReferencesItem value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeNodeId("SourceNodeId", value.sourceNodeId);
            encoder.writeNodeId("ReferenceTypeId", value.referenceTypeId);
            encoder.writeBoolean("IsForward", value.isForward);
            encoder.writeString("TargetServerUri", value.targetServerUri);
            encoder.writeExpandedNodeId("TargetNodeId", value.targetNodeId);
            encoder.writeInt32("TargetNodeClass", value.targetNodeClass != null ? value.targetNodeClass.getValue() : 0);
        }
    }

}
