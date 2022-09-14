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
import org.eclipse.milo.opcua.stack.core.types.UaStructuredType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.ApplicationType;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part5/12.3.3">https://reference.opcfoundation.org/v105/Core/docs/Part5/12.3.3</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class ApplicationDescription extends Structure implements UaStructuredType {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=308");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=310");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=309");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15087");

    private final String applicationUri;

    private final String productUri;

    private final LocalizedText applicationName;

    private final ApplicationType applicationType;

    private final String gatewayServerUri;

    private final String discoveryProfileUri;

    private final String[] discoveryUrls;

    public ApplicationDescription(String applicationUri, String productUri,
                                  LocalizedText applicationName, ApplicationType applicationType, String gatewayServerUri,
                                  String discoveryProfileUri, String[] discoveryUrls) {
        this.applicationUri = applicationUri;
        this.productUri = productUri;
        this.applicationName = applicationName;
        this.applicationType = applicationType;
        this.gatewayServerUri = gatewayServerUri;
        this.discoveryProfileUri = discoveryProfileUri;
        this.discoveryUrls = discoveryUrls;
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

    public String getApplicationUri() {
        return applicationUri;
    }

    public String getProductUri() {
        return productUri;
    }

    public LocalizedText getApplicationName() {
        return applicationName;
    }

    public ApplicationType getApplicationType() {
        return applicationType;
    }

    public String getGatewayServerUri() {
        return gatewayServerUri;
    }

    public String getDiscoveryProfileUri() {
        return discoveryProfileUri;
    }

    public String[] getDiscoveryUrls() {
        return discoveryUrls;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 310),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("ApplicationUri", LocalizedText.NULL_VALUE, new NodeId(0, 12), -1, null, UInteger.valueOf(0), false),
                new StructureField("ProductUri", LocalizedText.NULL_VALUE, new NodeId(0, 12), -1, null, UInteger.valueOf(0), false),
                new StructureField("ApplicationName", LocalizedText.NULL_VALUE, new NodeId(0, 21), -1, null, UInteger.valueOf(0), false),
                new StructureField("ApplicationType", LocalizedText.NULL_VALUE, new NodeId(0, 307), -1, null, UInteger.valueOf(0), false),
                new StructureField("GatewayServerUri", LocalizedText.NULL_VALUE, new NodeId(0, 12), -1, null, UInteger.valueOf(0), false),
                new StructureField("DiscoveryProfileUri", LocalizedText.NULL_VALUE, new NodeId(0, 12), -1, null, UInteger.valueOf(0), false),
                new StructureField("DiscoveryUrls", LocalizedText.NULL_VALUE, new NodeId(0, 12), 1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<ApplicationDescription> {
        @Override
        public Class<ApplicationDescription> getType() {
            return ApplicationDescription.class;
        }

        @Override
        public ApplicationDescription decodeType(SerializationContext context, UaDecoder decoder) {
            String applicationUri = decoder.readString("ApplicationUri");
            String productUri = decoder.readString("ProductUri");
            LocalizedText applicationName = decoder.readLocalizedText("ApplicationName");
            ApplicationType applicationType = ApplicationType.from(decoder.readEnum("ApplicationType"));
            String gatewayServerUri = decoder.readString("GatewayServerUri");
            String discoveryProfileUri = decoder.readString("DiscoveryProfileUri");
            String[] discoveryUrls = decoder.readStringArray("DiscoveryUrls");
            return new ApplicationDescription(applicationUri, productUri, applicationName, applicationType, gatewayServerUri, discoveryProfileUri, discoveryUrls);
        }

        @Override
        public void encodeType(SerializationContext context, UaEncoder encoder,
                               ApplicationDescription value) {
            encoder.writeString("ApplicationUri", value.getApplicationUri());
            encoder.writeString("ProductUri", value.getProductUri());
            encoder.writeLocalizedText("ApplicationName", value.getApplicationName());
            encoder.writeEnum("ApplicationType", value.getApplicationType());
            encoder.writeString("GatewayServerUri", value.getGatewayServerUri());
            encoder.writeString("DiscoveryProfileUri", value.getDiscoveryProfileUri());
            encoder.writeStringArray("DiscoveryUrls", value.getDiscoveryUrls());
        }
    }
}
