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
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.ServerState;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part5/12.7">https://reference.opcfoundation.org/v105/Core/docs/Part5/12.7</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class RedundantServerDataType extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=853");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=855");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=854");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15362");

    private final String serverId;

    private final UByte serviceLevel;

    private final ServerState serverState;

    public RedundantServerDataType(String serverId, UByte serviceLevel, ServerState serverState) {
        this.serverId = serverId;
        this.serviceLevel = serviceLevel;
        this.serverState = serverState;
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

    public String getServerId() {
        return serverId;
    }

    public UByte getServiceLevel() {
        return serviceLevel;
    }

    public ServerState getServerState() {
        return serverState;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 855),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("ServerId", LocalizedText.NULL_VALUE, new NodeId(0, 12), -1, null, UInteger.valueOf(0), false),
                new StructureField("ServiceLevel", LocalizedText.NULL_VALUE, new NodeId(0, 3), -1, null, UInteger.valueOf(0), false),
                new StructureField("ServerState", LocalizedText.NULL_VALUE, new NodeId(0, 852), -1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<RedundantServerDataType> {
        @Override
        public Class<RedundantServerDataType> getType() {
            return RedundantServerDataType.class;
        }

        @Override
        public RedundantServerDataType decode(SerializationContext context, UaDecoder decoder) {
            String serverId = decoder.readString("ServerId");
            UByte serviceLevel = decoder.readByte("ServiceLevel");
            ServerState serverState = (ServerState) decoder.readEnum("ServerState", ServerState.class);
            return new RedundantServerDataType(serverId, serviceLevel, serverState);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           RedundantServerDataType value) {
            encoder.writeString("ServerId", value.getServerId());
            encoder.writeByte("ServiceLevel", value.getServiceLevel());
            encoder.writeEnum("ServerState", value.getServerState());
        }
    }
}
