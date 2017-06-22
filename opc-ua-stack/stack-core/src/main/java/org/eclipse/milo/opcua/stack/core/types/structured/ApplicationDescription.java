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
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.SerializationContext;
import org.eclipse.milo.opcua.stack.core.types.UaDataType;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.enumerated.ApplicationType;

@UaDataType("ApplicationDescription")
public class ApplicationDescription implements UaStructure {

    public static final NodeId TypeId = Identifiers.ApplicationDescription;
    public static final NodeId BinaryEncodingId = Identifiers.ApplicationDescription_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.ApplicationDescription_Encoding_DefaultXml;

    protected final String _applicationUri;
    protected final String _productUri;
    protected final LocalizedText _applicationName;
    protected final ApplicationType _applicationType;
    protected final String _gatewayServerUri;
    protected final String _discoveryProfileUri;
    protected final String[] _discoveryUrls;

    public ApplicationDescription() {
        this._applicationUri = null;
        this._productUri = null;
        this._applicationName = null;
        this._applicationType = null;
        this._gatewayServerUri = null;
        this._discoveryProfileUri = null;
        this._discoveryUrls = null;
    }

    public ApplicationDescription(String _applicationUri, String _productUri, LocalizedText _applicationName, ApplicationType _applicationType, String _gatewayServerUri, String _discoveryProfileUri, String[] _discoveryUrls) {
        this._applicationUri = _applicationUri;
        this._productUri = _productUri;
        this._applicationName = _applicationName;
        this._applicationType = _applicationType;
        this._gatewayServerUri = _gatewayServerUri;
        this._discoveryProfileUri = _discoveryProfileUri;
        this._discoveryUrls = _discoveryUrls;
    }

    public String getApplicationUri() { return _applicationUri; }

    public String getProductUri() { return _productUri; }

    public LocalizedText getApplicationName() { return _applicationName; }

    public ApplicationType getApplicationType() { return _applicationType; }

    public String getGatewayServerUri() { return _gatewayServerUri; }

    public String getDiscoveryProfileUri() { return _discoveryProfileUri; }

    @Nullable
    public String[] getDiscoveryUrls() { return _discoveryUrls; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("ApplicationUri", _applicationUri)
            .add("ProductUri", _productUri)
            .add("ApplicationName", _applicationName)
            .add("ApplicationType", _applicationType)
            .add("GatewayServerUri", _gatewayServerUri)
            .add("DiscoveryProfileUri", _discoveryProfileUri)
            .add("DiscoveryUrls", _discoveryUrls)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<ApplicationDescription> {
        @Override
        public ApplicationDescription decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            String _applicationUri = reader.readString();
            String _productUri = reader.readString();
            LocalizedText _applicationName = reader.readLocalizedText();
            ApplicationType _applicationType = ApplicationType.from(reader.readInt32());
            String _gatewayServerUri = reader.readString();
            String _discoveryProfileUri = reader.readString();
            String[] _discoveryUrls = reader.readArray(reader::readString, String.class);

            return new ApplicationDescription(_applicationUri, _productUri, _applicationName, _applicationType, _gatewayServerUri, _discoveryProfileUri, _discoveryUrls);
        }

        @Override
        public void encode(SerializationContext context, ApplicationDescription value, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeString(value._applicationUri);
            writer.writeString(value._productUri);
            writer.writeLocalizedText(value._applicationName);
            writer.writeInt32(value._applicationType != null ? value._applicationType.getValue() : 0);
            writer.writeString(value._gatewayServerUri);
            writer.writeString(value._discoveryProfileUri);
            writer.writeArray(value._discoveryUrls, writer::writeString);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<ApplicationDescription> {
        @Override
        public ApplicationDescription decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            String _applicationUri = reader.readString("ApplicationUri");
            String _productUri = reader.readString("ProductUri");
            LocalizedText _applicationName = reader.readLocalizedText("ApplicationName");
            ApplicationType _applicationType = ApplicationType.from(reader.readInt32("ApplicationType"));
            String _gatewayServerUri = reader.readString("GatewayServerUri");
            String _discoveryProfileUri = reader.readString("DiscoveryProfileUri");
            String[] _discoveryUrls = reader.readArray("DiscoveryUrls", reader::readString, String.class);

            return new ApplicationDescription(_applicationUri, _productUri, _applicationName, _applicationType, _gatewayServerUri, _discoveryProfileUri, _discoveryUrls);
        }

        @Override
        public void encode(SerializationContext context, ApplicationDescription encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeString("ApplicationUri", encodable._applicationUri);
            writer.writeString("ProductUri", encodable._productUri);
            writer.writeLocalizedText("ApplicationName", encodable._applicationName);
            writer.writeInt32("ApplicationType", encodable._applicationType != null ? encodable._applicationType.getValue() : 0);
            writer.writeString("GatewayServerUri", encodable._gatewayServerUri);
            writer.writeString("DiscoveryProfileUri", encodable._discoveryProfileUri);
            writer.writeArray("DiscoveryUrls", encodable._discoveryUrls, writer::writeString);
        }
    }

}
