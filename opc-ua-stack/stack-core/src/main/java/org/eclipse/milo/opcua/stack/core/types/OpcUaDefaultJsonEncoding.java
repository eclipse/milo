/*
 * Copyright (c) 2022 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.core.types;

import java.io.StringWriter;

import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.serialization.OpcUaJsonDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.OpcUaJsonEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.OpcUaJsonDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;

public class OpcUaDefaultJsonEncoding implements DataTypeEncoding {

    public static final QualifiedName ENCODING_NAME =
        new QualifiedName(0, "Default JSON");

    public static OpcUaDefaultJsonEncoding getInstance() {
        return InstanceHolder.INSTANCE;
    }

    private static class InstanceHolder {
        private static final OpcUaDefaultJsonEncoding INSTANCE = new OpcUaDefaultJsonEncoding();
    }

    private OpcUaDefaultJsonEncoding() {}

    @Override
    public QualifiedName getName() {
        return ENCODING_NAME;
    }

    @Override
    public ExtensionObject.BodyType getBodyType() {
        return ExtensionObject.BodyType.JsonString;
    }

    @Override
    public Object encode(SerializationContext context, Object decodedBody, NodeId encodingId) {
        try {
            @SuppressWarnings("unchecked")
            OpcUaJsonDataTypeCodec<Object> codec =
                (OpcUaJsonDataTypeCodec<Object>) context.getDataTypeManager().getCodec(encodingId);

            if (codec == null) {
                throw new UaSerializationException(
                    StatusCodes.Bad_EncodingError,
                    "no codec registered for encodingId=" + encodingId);
            }

            var stringWriter = new StringWriter();
            var encoder = new OpcUaJsonEncoder(context, stringWriter);
            encoder.writeStruct(null, decodedBody, codec);

            return stringWriter.toString();
        } catch (ClassCastException e) {
            throw new UaSerializationException(StatusCodes.Bad_EncodingError, e);
        }
    }

    @Override
    public Object decode(SerializationContext context, Object encodedBody, NodeId encodingId) {
        try {
            @SuppressWarnings("unchecked")
            OpcUaJsonDataTypeCodec<Object> codec =
                (OpcUaJsonDataTypeCodec<Object>) context.getDataTypeManager().getCodec(encodingId);

            if (codec == null) {
                throw new UaSerializationException(
                    StatusCodes.Bad_DecodingError,
                    "no codec registered for encodingId=" + encodingId);
            }

            var decoder = new OpcUaJsonDecoder(context, (String) encodedBody);

            return decoder.readStruct(null, codec);
        } catch (ClassCastException e) {
            throw new UaSerializationException(StatusCodes.Bad_DecodingError, e);
        }
    }

}
