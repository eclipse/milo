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
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part4/7.25.1">https://reference.opcfoundation.org/v105/Core/docs/Part4/7.25.1</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class NotificationData extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=945");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=947");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=946");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15344");

    public NotificationData() {
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

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 947),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{

            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<NotificationData> {
        @Override
        public Class<NotificationData> getType() {
            return NotificationData.class;
        }

        @Override
        public NotificationData decode(SerializationContext context, UaDecoder decoder) {
            return new NotificationData();
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, NotificationData value) {
        }
    }
}
