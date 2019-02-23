/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.core.types.structured;

import com.google.common.base.MoreObjects;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.BuiltinDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public class UserNameIdentityToken extends UserIdentityToken {

    public static final NodeId TypeId = Identifiers.UserNameIdentityToken;
    public static final NodeId BinaryEncodingId = Identifiers.UserNameIdentityToken_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.UserNameIdentityToken_Encoding_DefaultXml;

    protected final String userName;
    protected final ByteString password;
    protected final String encryptionAlgorithm;

    public UserNameIdentityToken() {
        super(null);
        this.userName = null;
        this.password = null;
        this.encryptionAlgorithm = null;
    }

    public UserNameIdentityToken(String policyId, String userName, ByteString password, String encryptionAlgorithm) {
        super(policyId);
        this.userName = userName;
        this.password = password;
        this.encryptionAlgorithm = encryptionAlgorithm;
    }

    public String getUserName() { return userName; }

    public ByteString getPassword() { return password; }

    public String getEncryptionAlgorithm() { return encryptionAlgorithm; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("PolicyId", policyId)
            .add("UserName", userName)
            .add("Password", password)
            .add("EncryptionAlgorithm", encryptionAlgorithm)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<UserNameIdentityToken> {

        @Override
        public Class<UserNameIdentityToken> getType() {
            return UserNameIdentityToken.class;
        }

        @Override
        public UserNameIdentityToken decode(UaDecoder decoder) throws UaSerializationException {
            String policyId = decoder.readString("PolicyId");
            String userName = decoder.readString("UserName");
            ByteString password = decoder.readByteString("Password");
            String encryptionAlgorithm = decoder.readString("EncryptionAlgorithm");

            return new UserNameIdentityToken(policyId, userName, password, encryptionAlgorithm);
        }

        @Override
        public void encode(UserNameIdentityToken value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeString("PolicyId", value.policyId);
            encoder.writeString("UserName", value.userName);
            encoder.writeByteString("Password", value.password);
            encoder.writeString("EncryptionAlgorithm", value.encryptionAlgorithm);
        }
    }

}
