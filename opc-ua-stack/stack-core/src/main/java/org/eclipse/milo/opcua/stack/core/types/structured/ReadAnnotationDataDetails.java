/*
 * Copyright (c) 2022 the Eclipse Milo Authors
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
import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.serialization.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.types.UaStructuredType;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v104/Core/docs/Part11/6.4.6/#6.4.6.1">https://reference.opcfoundation.org/v104/Core/docs/Part11/6.4.6/#6.4.6.1</a>
 */
@EqualsAndHashCode(
    callSuper = true
)
@SuperBuilder
@ToString
public class ReadAnnotationDataDetails extends HistoryReadDetails implements UaStructuredType {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=23497");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=23500");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=23506");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=23512");

    private final DateTime[] reqTimes;

    public ReadAnnotationDataDetails(DateTime[] reqTimes) {
        this.reqTimes = reqTimes;
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

    public DateTime[] getReqTimes() {
        return reqTimes;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 23500),
            new NodeId(0, 641),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("ReqTimes", LocalizedText.NULL_VALUE, new NodeId(0, 294), 1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<ReadAnnotationDataDetails> {
        @Override
        public Class<ReadAnnotationDataDetails> getType() {
            return ReadAnnotationDataDetails.class;
        }

        @Override
        public ReadAnnotationDataDetails decodeType(SerializationContext context, UaDecoder decoder) {
            DateTime[] reqTimes = decoder.readDateTimeArray("ReqTimes");
            return new ReadAnnotationDataDetails(reqTimes);
        }

        @Override
        public void encodeType(SerializationContext context, UaEncoder encoder,
                               ReadAnnotationDataDetails value) {
            encoder.writeDateTimeArray("ReqTimes", value.getReqTimes());
        }
    }
}
