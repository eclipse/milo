/*
 * Copyright (c) 2021 the Eclipse Milo Authors
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
    callSuper = false
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class EphemeralKeyType extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=17548");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=17549");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=17553");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=17557");

    private final ByteString publicKey;

    private final ByteString signature;

    public EphemeralKeyType(ByteString publicKey, ByteString signature) {
        this.publicKey = publicKey;
        this.signature = signature;
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

    public ByteString getPublicKey() {
        return publicKey;
    }

    public ByteString getSignature() {
        return signature;
    }

    public static final class Codec extends GenericDataTypeCodec<EphemeralKeyType> {
        @Override
        public Class<EphemeralKeyType> getType() {
            return EphemeralKeyType.class;
        }

        @Override
        public EphemeralKeyType decode(SerializationContext context, UaDecoder decoder) {
            ByteString publicKey = decoder.readByteString("PublicKey");
            ByteString signature = decoder.readByteString("Signature");
            return new EphemeralKeyType(publicKey, signature);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, EphemeralKeyType value) {
            encoder.writeByteString("PublicKey", value.getPublicKey());
            encoder.writeByteString("Signature", value.getSignature());
        }
    }
}
