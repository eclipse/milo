/*
 * Copyright (c) 2019 the Eclipse Milo Authors
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

import com.google.common.base.Charsets;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.serialization.EncodingLimits;
import org.eclipse.milo.opcua.stack.core.serialization.OpcUaXmlStreamDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.OpcUaXmlStreamEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.OpcUaXmlDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
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
    public ExtensionObject.BodyType getBodyType() {
        return ExtensionObject.BodyType.XmlElement;
    }

    @Override
    public Object encode(
        Object struct,
        NodeId encodingId,
        EncodingLimits encodingLimits,
        DataTypeManager dataTypeManager) {

        try {
            @SuppressWarnings("unchecked")
            OpcUaXmlDataTypeCodec<Object> codec =
                (OpcUaXmlDataTypeCodec<Object>) dataTypeManager.getXmlCodec(encodingId);

            if (codec == null) {
                throw new UaSerializationException(
                    StatusCodes.Bad_EncodingError,
                    "no codec registered for encodingId=" + encodingId);
            }

            OpcUaXmlStreamEncoder writer = new OpcUaXmlStreamEncoder(encodingLimits);

            codec.encode(() -> dataTypeManager, struct, writer);

            return new XmlElement(writer.getDocumentXml());
        } catch (ClassCastException e) {
            throw new UaSerializationException(StatusCodes.Bad_EncodingError, e);
        }
    }

    @Override
    public Object decode(
        Object body,
        NodeId encodingId,
        EncodingLimits encodingLimits,
        DataTypeManager dataTypeManager) {

        try {
            @SuppressWarnings("unchecked")
            OpcUaXmlDataTypeCodec<Object> codec =
                (OpcUaXmlDataTypeCodec<Object>) dataTypeManager.getXmlCodec(encodingId);

            if (codec == null) {
                throw new UaSerializationException(
                    StatusCodes.Bad_DecodingError,
                    "no codec registered for encodingId=" + encodingId);
            }

            XmlElement xmlBody = (XmlElement) body;
            String xml = xmlBody.getFragmentOrEmpty();

            OpcUaXmlStreamDecoder reader = new OpcUaXmlStreamDecoder(encodingLimits);
            reader.setInput(new ByteArrayInputStream(xml.getBytes(Charsets.UTF_8)));

            return reader.readStruct(null, encodingId);
        } catch (IOException | SAXException e) {
            throw new UaSerializationException(StatusCodes.Bad_DecodingError, e);
        }
    }

}
