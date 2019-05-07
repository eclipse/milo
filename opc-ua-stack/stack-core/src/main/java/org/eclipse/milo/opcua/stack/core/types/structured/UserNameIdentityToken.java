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

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;

@EqualsAndHashCode(
    callSuper = true
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class UserNameIdentityToken extends UserIdentityToken implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=322");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=323");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=324");

    private final String userName;

    private final ByteString password;

    private final String encryptionAlgorithm;

    public UserNameIdentityToken(String policyId, String userName, ByteString password,
                                 String encryptionAlgorithm) {
        super(policyId);
        this.userName = userName;
        this.password = password;
        this.encryptionAlgorithm = encryptionAlgorithm;
    }

    @Override
    public ExpandedNodeId getTypeId() {
        return TYPE_ID;
    }

    @Override
    public ExpandedNodeId getXmlEncodingId() {
        return XML_ENCODING_ID;
    }

    @Override
    public ExpandedNodeId getBinaryEncodingId() {
        return BINARY_ENCODING_ID;
    }

    public String getUserName() {
        return userName;
    }

    public ByteString getPassword() {
        return password;
    }

    public String getEncryptionAlgorithm() {
        return encryptionAlgorithm;
    }

    public static final class Codec extends GenericDataTypeCodec<UserNameIdentityToken> {
        @Override
        public Class<UserNameIdentityToken> getType() {
            return UserNameIdentityToken.class;
        }

        @Override
        public UserNameIdentityToken decode(SerializationContext context, UaDecoder decoder) {
            String policyId = decoder.readString("PolicyId");
            String userName = decoder.readString("UserName");
            ByteString password = decoder.readByteString("Password");
            String encryptionAlgorithm = decoder.readString("EncryptionAlgorithm");
            return new UserNameIdentityToken(policyId, userName, password, encryptionAlgorithm);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           UserNameIdentityToken value) {
            encoder.writeString("PolicyId", value.getPolicyId());
            encoder.writeString("UserName", value.getUserName());
            encoder.writeByteString("Password", value.getPassword());
            encoder.writeString("EncryptionAlgorithm", value.getEncryptionAlgorithm());
        }
    }
}
