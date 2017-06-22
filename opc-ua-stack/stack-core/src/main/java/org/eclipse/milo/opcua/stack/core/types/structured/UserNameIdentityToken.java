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

@UaDataType("UserNameIdentityToken")
public class UserNameIdentityToken extends UserIdentityToken {

    public static final NodeId TypeId = Identifiers.UserNameIdentityToken;
    public static final NodeId BinaryEncodingId = Identifiers.UserNameIdentityToken_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.UserNameIdentityToken_Encoding_DefaultXml;

    protected final String _userName;
    protected final ByteString _password;
    protected final String _encryptionAlgorithm;

    public UserNameIdentityToken() {
        super(null);
        this._userName = null;
        this._password = null;
        this._encryptionAlgorithm = null;
    }

    public UserNameIdentityToken(String _policyId, String _userName, ByteString _password, String _encryptionAlgorithm) {
        super(_policyId);
        this._userName = _userName;
        this._password = _password;
        this._encryptionAlgorithm = _encryptionAlgorithm;
    }

    public String getUserName() { return _userName; }

    public ByteString getPassword() { return _password; }

    public String getEncryptionAlgorithm() { return _encryptionAlgorithm; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("PolicyId", _policyId)
            .add("UserName", _userName)
            .add("Password", _password)
            .add("EncryptionAlgorithm", _encryptionAlgorithm)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<UserNameIdentityToken> {
        @Override
        public UserNameIdentityToken decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            String _policyId = reader.readString();
            String _userName = reader.readString();
            ByteString _password = reader.readByteString();
            String _encryptionAlgorithm = reader.readString();

            return new UserNameIdentityToken(_policyId, _userName, _password, _encryptionAlgorithm);
        }

        @Override
        public void encode(SerializationContext context, UserNameIdentityToken value, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeString(value._policyId);
            writer.writeString(value._userName);
            writer.writeByteString(value._password);
            writer.writeString(value._encryptionAlgorithm);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<UserNameIdentityToken> {
        @Override
        public UserNameIdentityToken decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            String _policyId = reader.readString("PolicyId");
            String _userName = reader.readString("UserName");
            ByteString _password = reader.readByteString("Password");
            String _encryptionAlgorithm = reader.readString("EncryptionAlgorithm");

            return new UserNameIdentityToken(_policyId, _userName, _password, _encryptionAlgorithm);
        }

        @Override
        public void encode(SerializationContext context, UserNameIdentityToken encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeString("PolicyId", encodable._policyId);
            writer.writeString("UserName", encodable._userName);
            writer.writeByteString("Password", encodable._password);
            writer.writeString("EncryptionAlgorithm", encodable._encryptionAlgorithm);
        }
    }

}
