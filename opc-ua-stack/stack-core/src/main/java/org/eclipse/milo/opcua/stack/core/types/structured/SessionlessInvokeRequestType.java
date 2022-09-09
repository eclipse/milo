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
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part4/6.3.2">https://reference.opcfoundation.org/v105/Core/docs/Part4/6.3.2</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class SessionlessInvokeRequestType extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=15901");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=15903");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=15902");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15091");

    private final UInteger urisVersion;

    private final String[] namespaceUris;

    private final String[] serverUris;

    private final String[] localeIds;

    private final UInteger serviceId;

    public SessionlessInvokeRequestType(UInteger urisVersion, String[] namespaceUris,
                                        String[] serverUris, String[] localeIds, UInteger serviceId) {
        this.urisVersion = urisVersion;
        this.namespaceUris = namespaceUris;
        this.serverUris = serverUris;
        this.localeIds = localeIds;
        this.serviceId = serviceId;
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

    public UInteger getUrisVersion() {
        return urisVersion;
    }

    public String[] getNamespaceUris() {
        return namespaceUris;
    }

    public String[] getServerUris() {
        return serverUris;
    }

    public String[] getLocaleIds() {
        return localeIds;
    }

    public UInteger getServiceId() {
        return serviceId;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 15903),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("UrisVersion", LocalizedText.NULL_VALUE, new NodeId(0, 20998), -1, null, UInteger.valueOf(0), false),
                new StructureField("NamespaceUris", LocalizedText.NULL_VALUE, new NodeId(0, 12), 1, null, UInteger.valueOf(0), false),
                new StructureField("ServerUris", LocalizedText.NULL_VALUE, new NodeId(0, 12), 1, null, UInteger.valueOf(0), false),
                new StructureField("LocaleIds", LocalizedText.NULL_VALUE, new NodeId(0, 295), 1, null, UInteger.valueOf(0), false),
                new StructureField("ServiceId", LocalizedText.NULL_VALUE, new NodeId(0, 7), -1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<SessionlessInvokeRequestType> {
        @Override
        public Class<SessionlessInvokeRequestType> getType() {
            return SessionlessInvokeRequestType.class;
        }

        @Override
        public SessionlessInvokeRequestType decodeType(SerializationContext context,
                                                       UaDecoder decoder) {
            UInteger urisVersion = decoder.readUInt32("UrisVersion");
            String[] namespaceUris = decoder.readStringArray("NamespaceUris");
            String[] serverUris = decoder.readStringArray("ServerUris");
            String[] localeIds = decoder.readStringArray("LocaleIds");
            UInteger serviceId = decoder.readUInt32("ServiceId");
            return new SessionlessInvokeRequestType(urisVersion, namespaceUris, serverUris, localeIds, serviceId);
        }

        @Override
        public void encodeType(SerializationContext context, UaEncoder encoder,
                               SessionlessInvokeRequestType value) {
            encoder.writeUInt32("UrisVersion", value.getUrisVersion());
            encoder.writeStringArray("NamespaceUris", value.getNamespaceUris());
            encoder.writeStringArray("ServerUris", value.getServerUris());
            encoder.writeStringArray("LocaleIds", value.getLocaleIds());
            encoder.writeUInt32("ServiceId", value.getServiceId());
        }
    }
}
