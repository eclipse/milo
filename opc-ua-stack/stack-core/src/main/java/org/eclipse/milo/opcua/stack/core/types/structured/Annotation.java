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
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;

@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class Annotation extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=891");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=892");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=893");

    private final String message;

    private final String userName;

    private final DateTime annotationTime;

    public Annotation(String message, String userName, DateTime annotationTime) {
        this.message = message;
        this.userName = userName;
        this.annotationTime = annotationTime;
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

    public String getMessage() {
        return message;
    }

    public String getUserName() {
        return userName;
    }

    public DateTime getAnnotationTime() {
        return annotationTime;
    }

    public static final class Codec extends GenericDataTypeCodec<Annotation> {
        @Override
        public Class<Annotation> getType() {
            return Annotation.class;
        }

        @Override
        public Annotation decode(SerializationContext context, UaDecoder decoder) {
            String message = decoder.readString("Message");
            String userName = decoder.readString("UserName");
            DateTime annotationTime = decoder.readDateTime("AnnotationTime");
            return new Annotation(message, userName, annotationTime);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, Annotation value) {
            encoder.writeString("Message", value.getMessage());
            encoder.writeString("UserName", value.getUserName());
            encoder.writeDateTime("AnnotationTime", value.getAnnotationTime());
        }
    }
}
