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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.serialization.DataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.xml.OpcUaXmlStreamDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.xml.OpcUaXmlStreamEncoder;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.XmlElement;
import org.xml.sax.SAXException;

public class OpcUaDefaultXmlEncoding implements DataTypeEncoding {

    public static final QualifiedName ENCODING_NAME =
        new QualifiedName(0, "Default XML");

    public static OpcUaDefaultXmlEncoding getInstance() {
        return InstanceHolder.INSTANCE;
    }

    private static class InstanceHolder {
        private static final OpcUaDefaultXmlEncoding INSTANCE = new OpcUaDefaultXmlEncoding();
    }

    @Override
    public QualifiedName getName() {
        return ENCODING_NAME;
    }

    @Override
    public Object encode(
        SerializationContext context,
        Object struct,
        NodeId encodingId
    ) {

        DataTypeCodec codec = context.getDataTypeManager().getCodec(encodingId);

        if (codec != null) {
            // We have to use encoder.writeStruct() instead of codec.encode() because
            // XML-encoded structs are wrapped in a container element with the struct name.
            OpcUaXmlStreamEncoder encoder = new OpcUaXmlStreamEncoder(context);
            encoder.writeStruct(null, struct, codec);

            return new XmlElement(encoder.getDocumentXml());
        } else {
            throw new UaSerializationException(
                StatusCodes.Bad_EncodingError,
                "no codec registered for encodingId=" + encodingId);
        }
    }

    @Override
    public Object decode(
        SerializationContext context,
        Object body,
        NodeId encodingId
    ) {

        DataTypeCodec codec = context.getDataTypeManager().getCodec(encodingId);

        if (codec != null) {
            XmlElement xmlBody = (XmlElement) body;
            String xml = xmlBody.getFragmentOrEmpty();

            OpcUaXmlStreamDecoder decoder = new OpcUaXmlStreamDecoder(context);
            try {
                decoder.setInput(new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8)));
            } catch (IOException | SAXException e) {
                throw new UaSerializationException(StatusCodes.Bad_DecodingError, e);
            }

            // We have to use decoder.readStruct() instead of codec.decode() because
            // XML-encoded structs are wrapped in a container element with the struct name.
            return decoder.readStruct(null, codec);
        } else {
            throw new UaSerializationException(
                StatusCodes.Bad_DecodingError,
                "no codec registered for encodingId=" + encodingId);
        }
    }

}
