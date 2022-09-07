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
import org.eclipse.milo.opcua.stack.core.types.enumerated.ApplicationType;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part4/7.32">https://reference.opcfoundation.org/v105/Core/docs/Part4/7.32</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class RegisteredServer extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=432");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=434");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=433");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15102");

    private final String serverUri;

    private final String productUri;

    private final LocalizedText[] serverNames;

    private final ApplicationType serverType;

    private final String gatewayServerUri;

    private final String[] discoveryUrls;

    private final String semaphoreFilePath;

    private final Boolean isOnline;

    public RegisteredServer(String serverUri, String productUri, LocalizedText[] serverNames,
                            ApplicationType serverType, String gatewayServerUri, String[] discoveryUrls,
                            String semaphoreFilePath, Boolean isOnline) {
        this.serverUri = serverUri;
        this.productUri = productUri;
        this.serverNames = serverNames;
        this.serverType = serverType;
        this.gatewayServerUri = gatewayServerUri;
        this.discoveryUrls = discoveryUrls;
        this.semaphoreFilePath = semaphoreFilePath;
        this.isOnline = isOnline;
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

    public String getServerUri() {
        return serverUri;
    }

    public String getProductUri() {
        return productUri;
    }

    public LocalizedText[] getServerNames() {
        return serverNames;
    }

    public ApplicationType getServerType() {
        return serverType;
    }

    public String getGatewayServerUri() {
        return gatewayServerUri;
    }

    public String[] getDiscoveryUrls() {
        return discoveryUrls;
    }

    public String getSemaphoreFilePath() {
        return semaphoreFilePath;
    }

    public Boolean getIsOnline() {
        return isOnline;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 434),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("ServerUri", LocalizedText.NULL_VALUE, new NodeId(0, 12), -1, null, UInteger.valueOf(0), false),
                new StructureField("ProductUri", LocalizedText.NULL_VALUE, new NodeId(0, 12), -1, null, UInteger.valueOf(0), false),
                new StructureField("ServerNames", LocalizedText.NULL_VALUE, new NodeId(0, 21), 1, null, UInteger.valueOf(0), false),
                new StructureField("ServerType", LocalizedText.NULL_VALUE, new NodeId(0, 307), -1, null, UInteger.valueOf(0), false),
                new StructureField("GatewayServerUri", LocalizedText.NULL_VALUE, new NodeId(0, 12), -1, null, UInteger.valueOf(0), false),
                new StructureField("DiscoveryUrls", LocalizedText.NULL_VALUE, new NodeId(0, 12), 1, null, UInteger.valueOf(0), false),
                new StructureField("SemaphoreFilePath", LocalizedText.NULL_VALUE, new NodeId(0, 12), -1, null, UInteger.valueOf(0), false),
                new StructureField("IsOnline", LocalizedText.NULL_VALUE, new NodeId(0, 1), -1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<RegisteredServer> {
        @Override
        public Class<RegisteredServer> getType() {
            return RegisteredServer.class;
        }

        @Override
        public RegisteredServer decode(SerializationContext context, UaDecoder decoder) {
            String serverUri = decoder.readString("ServerUri");
            String productUri = decoder.readString("ProductUri");
            LocalizedText[] serverNames = decoder.readLocalizedTextArray("ServerNames");
            ApplicationType serverType = (ApplicationType) decoder.readEnum("ServerType", ApplicationType.class);
            String gatewayServerUri = decoder.readString("GatewayServerUri");
            String[] discoveryUrls = decoder.readStringArray("DiscoveryUrls");
            String semaphoreFilePath = decoder.readString("SemaphoreFilePath");
            Boolean isOnline = decoder.readBoolean("IsOnline");
            return new RegisteredServer(serverUri, productUri, serverNames, serverType, gatewayServerUri, discoveryUrls, semaphoreFilePath, isOnline);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, RegisteredServer value) {
            encoder.writeString("ServerUri", value.getServerUri());
            encoder.writeString("ProductUri", value.getProductUri());
            encoder.writeLocalizedTextArray("ServerNames", value.getServerNames());
            encoder.writeEnum("ServerType", value.getServerType());
            encoder.writeString("GatewayServerUri", value.getGatewayServerUri());
            encoder.writeStringArray("DiscoveryUrls", value.getDiscoveryUrls());
            encoder.writeString("SemaphoreFilePath", value.getSemaphoreFilePath());
            encoder.writeBoolean("IsOnline", value.getIsOnline());
        }
    }
}
