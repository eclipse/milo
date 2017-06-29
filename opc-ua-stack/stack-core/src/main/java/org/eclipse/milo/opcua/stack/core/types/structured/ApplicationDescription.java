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

import javax.annotation.Nullable;

import com.google.common.base.MoreObjects;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.BuiltinDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.enumerated.ApplicationType;

public class ApplicationDescription implements UaStructure {

    public static final NodeId TypeId = Identifiers.ApplicationDescription;
    public static final NodeId BinaryEncodingId = Identifiers.ApplicationDescription_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.ApplicationDescription_Encoding_DefaultXml;

    protected final String applicationUri;
    protected final String productUri;
    protected final LocalizedText applicationName;
    protected final ApplicationType applicationType;
    protected final String gatewayServerUri;
    protected final String discoveryProfileUri;
    protected final String[] discoveryUrls;

    public ApplicationDescription() {
        this.applicationUri = null;
        this.productUri = null;
        this.applicationName = null;
        this.applicationType = null;
        this.gatewayServerUri = null;
        this.discoveryProfileUri = null;
        this.discoveryUrls = null;
    }

    public ApplicationDescription(String applicationUri, String productUri, LocalizedText applicationName, ApplicationType applicationType, String gatewayServerUri, String discoveryProfileUri, String[] discoveryUrls) {
        this.applicationUri = applicationUri;
        this.productUri = productUri;
        this.applicationName = applicationName;
        this.applicationType = applicationType;
        this.gatewayServerUri = gatewayServerUri;
        this.discoveryProfileUri = discoveryProfileUri;
        this.discoveryUrls = discoveryUrls;
    }

    public String getApplicationUri() { return applicationUri; }

    public String getProductUri() { return productUri; }

    public LocalizedText getApplicationName() { return applicationName; }

    public ApplicationType getApplicationType() { return applicationType; }

    public String getGatewayServerUri() { return gatewayServerUri; }

    public String getDiscoveryProfileUri() { return discoveryProfileUri; }

    @Nullable
    public String[] getDiscoveryUrls() { return discoveryUrls; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("ApplicationUri", applicationUri)
            .add("ProductUri", productUri)
            .add("ApplicationName", applicationName)
            .add("ApplicationType", applicationType)
            .add("GatewayServerUri", gatewayServerUri)
            .add("DiscoveryProfileUri", discoveryProfileUri)
            .add("DiscoveryUrls", discoveryUrls)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<ApplicationDescription> {

        @Override
        public Class<ApplicationDescription> getType() {
            return ApplicationDescription.class;
        }

        @Override
        public ApplicationDescription decode(UaDecoder decoder) throws UaSerializationException {
            String applicationUri = decoder.readString("ApplicationUri");
            String productUri = decoder.readString("ProductUri");
            LocalizedText applicationName = decoder.readLocalizedText("ApplicationName");
            ApplicationType applicationType = ApplicationType.from(decoder.readInt32("ApplicationType"));
            String gatewayServerUri = decoder.readString("GatewayServerUri");
            String discoveryProfileUri = decoder.readString("DiscoveryProfileUri");
            String[] discoveryUrls = decoder.readArray("DiscoveryUrls", decoder::readString, String.class);

            return new ApplicationDescription(applicationUri, productUri, applicationName, applicationType, gatewayServerUri, discoveryProfileUri, discoveryUrls);
        }

        @Override
        public void encode(ApplicationDescription value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeString("ApplicationUri", value.applicationUri);
            encoder.writeString("ProductUri", value.productUri);
            encoder.writeLocalizedText("ApplicationName", value.applicationName);
            encoder.writeInt32("ApplicationType", value.applicationType != null ? value.applicationType.getValue() : 0);
            encoder.writeString("GatewayServerUri", value.gatewayServerUri);
            encoder.writeString("DiscoveryProfileUri", value.discoveryProfileUri);
            encoder.writeArray("DiscoveryUrls", value.discoveryUrls, encoder::writeString);
        }
    }

}
