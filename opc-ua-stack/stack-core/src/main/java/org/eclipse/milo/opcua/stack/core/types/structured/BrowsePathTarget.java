package org.eclipse.milo.opcua.stack.core.types.structured;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part4/5.8.4/#5.8.4.2">https://reference.opcfoundation.org/v105/Core/docs/Part4/5.8.4/#5.8.4.2</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class BrowsePathTarget extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=546");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=548");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=547");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15191");

    private final ExpandedNodeId targetId;

    private final UInteger remainingPathIndex;

    public BrowsePathTarget(ExpandedNodeId targetId, UInteger remainingPathIndex) {
        this.targetId = targetId;
        this.remainingPathIndex = remainingPathIndex;
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

    public ExpandedNodeId getTargetId() {
        return targetId;
    }

    public UInteger getRemainingPathIndex() {
        return remainingPathIndex;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 548),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("TargetId", LocalizedText.NULL_VALUE, new NodeId(0, 18), -1, null, UInteger.valueOf(0), false),
                new StructureField("RemainingPathIndex", LocalizedText.NULL_VALUE, new NodeId(0, 17588), -1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<BrowsePathTarget> {
        @Override
        public Class<BrowsePathTarget> getType() {
            return BrowsePathTarget.class;
        }

        @Override
        public BrowsePathTarget decode(SerializationContext context, UaDecoder decoder) {
            ExpandedNodeId targetId = decoder.readExpandedNodeId("TargetId");
            UInteger remainingPathIndex = decoder.readUInt32("RemainingPathIndex");
            return new BrowsePathTarget(targetId, remainingPathIndex);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, BrowsePathTarget value) {
            encoder.writeExpandedNodeId("TargetId", value.getTargetId());
            encoder.writeUInt32("RemainingPathIndex", value.getRemainingPathIndex());
        }
    }
}
