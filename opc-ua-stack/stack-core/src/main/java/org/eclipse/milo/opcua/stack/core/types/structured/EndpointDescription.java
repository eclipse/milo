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
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MessageSecurityMode;

@UaDataType("EndpointDescription")
public class EndpointDescription implements UaStructure {

    public static final NodeId TypeId = Identifiers.EndpointDescription;
    public static final NodeId BinaryEncodingId = Identifiers.EndpointDescription_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.EndpointDescription_Encoding_DefaultXml;

    protected final String _endpointUrl;
    protected final ApplicationDescription _server;
    protected final ByteString _serverCertificate;
    protected final MessageSecurityMode _securityMode;
    protected final String _securityPolicyUri;
    protected final UserTokenPolicy[] _userIdentityTokens;
    protected final String _transportProfileUri;
    protected final UByte _securityLevel;

    public EndpointDescription() {
        this._endpointUrl = null;
        this._server = null;
        this._serverCertificate = null;
        this._securityMode = null;
        this._securityPolicyUri = null;
        this._userIdentityTokens = null;
        this._transportProfileUri = null;
        this._securityLevel = null;
    }

    public EndpointDescription(String _endpointUrl, ApplicationDescription _server, ByteString _serverCertificate, MessageSecurityMode _securityMode, String _securityPolicyUri, UserTokenPolicy[] _userIdentityTokens, String _transportProfileUri, UByte _securityLevel) {
        this._endpointUrl = _endpointUrl;
        this._server = _server;
        this._serverCertificate = _serverCertificate;
        this._securityMode = _securityMode;
        this._securityPolicyUri = _securityPolicyUri;
        this._userIdentityTokens = _userIdentityTokens;
        this._transportProfileUri = _transportProfileUri;
        this._securityLevel = _securityLevel;
    }

    public String getEndpointUrl() { return _endpointUrl; }

    public ApplicationDescription getServer() { return _server; }

    public ByteString getServerCertificate() { return _serverCertificate; }

    public MessageSecurityMode getSecurityMode() { return _securityMode; }

    public String getSecurityPolicyUri() { return _securityPolicyUri; }

    @Nullable
    public UserTokenPolicy[] getUserIdentityTokens() { return _userIdentityTokens; }

    public String getTransportProfileUri() { return _transportProfileUri; }

    public UByte getSecurityLevel() { return _securityLevel; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("EndpointUrl", _endpointUrl)
            .add("Server", _server)
            .add("ServerCertificate", _serverCertificate)
            .add("SecurityMode", _securityMode)
            .add("SecurityPolicyUri", _securityPolicyUri)
            .add("UserIdentityTokens", _userIdentityTokens)
            .add("TransportProfileUri", _transportProfileUri)
            .add("SecurityLevel", _securityLevel)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<EndpointDescription> {
        @Override
        public EndpointDescription decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            String _endpointUrl = reader.readString();
            ApplicationDescription _server = (ApplicationDescription) context.decode(ApplicationDescription.BinaryEncodingId, reader);
            ByteString _serverCertificate = reader.readByteString();
            MessageSecurityMode _securityMode = MessageSecurityMode.from(reader.readInt32());
            String _securityPolicyUri = reader.readString();
            UserTokenPolicy[] _userIdentityTokens =
                reader.readArray(
                    () -> (UserTokenPolicy) context.decode(
                        UserTokenPolicy.BinaryEncodingId, reader),
                    UserTokenPolicy.class
                );
            String _transportProfileUri = reader.readString();
            UByte _securityLevel = reader.readByte();

            return new EndpointDescription(_endpointUrl, _server, _serverCertificate, _securityMode, _securityPolicyUri, _userIdentityTokens, _transportProfileUri, _securityLevel);
        }

        @Override
        public void encode(SerializationContext context, EndpointDescription encodable, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeString(encodable._endpointUrl);
            context.encode(ApplicationDescription.BinaryEncodingId, encodable._server, writer);
            writer.writeByteString(encodable._serverCertificate);
            writer.writeInt32(encodable._securityMode != null ? encodable._securityMode.getValue() : 0);
            writer.writeString(encodable._securityPolicyUri);
            writer.writeArray(
                encodable._userIdentityTokens,
                e -> context.encode(UserTokenPolicy.BinaryEncodingId, e, writer)
            );
            writer.writeString(encodable._transportProfileUri);
            writer.writeByte(encodable._securityLevel);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<EndpointDescription> {
        @Override
        public EndpointDescription decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            String _endpointUrl = reader.readString("EndpointUrl");
            ApplicationDescription _server = (ApplicationDescription) context.decode(ApplicationDescription.XmlEncodingId, reader);
            ByteString _serverCertificate = reader.readByteString("ServerCertificate");
            MessageSecurityMode _securityMode = MessageSecurityMode.from(reader.readInt32("SecurityMode"));
            String _securityPolicyUri = reader.readString("SecurityPolicyUri");
            UserTokenPolicy[] _userIdentityTokens =
                reader.readArray(
                    "UserIdentityTokens",
                    f -> (UserTokenPolicy) context.decode(
                        UserTokenPolicy.XmlEncodingId, reader),
                    UserTokenPolicy.class
                );
            String _transportProfileUri = reader.readString("TransportProfileUri");
            UByte _securityLevel = reader.readByte("SecurityLevel");

            return new EndpointDescription(_endpointUrl, _server, _serverCertificate, _securityMode, _securityPolicyUri, _userIdentityTokens, _transportProfileUri, _securityLevel);
        }

        @Override
        public void encode(SerializationContext context, EndpointDescription encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeString("EndpointUrl", encodable._endpointUrl);
            context.encode(ApplicationDescription.XmlEncodingId, encodable._server, writer);
            writer.writeByteString("ServerCertificate", encodable._serverCertificate);
            writer.writeInt32("SecurityMode", encodable._securityMode != null ? encodable._securityMode.getValue() : 0);
            writer.writeString("SecurityPolicyUri", encodable._securityPolicyUri);
            writer.writeArray(
                "UserIdentityTokens",
                encodable._userIdentityTokens,
                (f, e) -> context.encode(UserTokenPolicy.XmlEncodingId, e, writer)
            );
            writer.writeString("TransportProfileUri", encodable._transportProfileUri);
            writer.writeByte("SecurityLevel", encodable._securityLevel);
        }
    }

}
