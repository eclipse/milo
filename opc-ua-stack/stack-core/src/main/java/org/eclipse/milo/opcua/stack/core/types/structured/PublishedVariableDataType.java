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
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part14/6.2.3/#6.2.3.7.1">https://reference.opcfoundation.org/v105/Core/docs/Part14/6.2.3/#6.2.3.7.1</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class PublishedVariableDataType extends Structure implements UaStructuredType {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=14273");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=14323");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=14319");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15060");

    private final NodeId publishedVariable;

    private final UInteger attributeId;

    private final Double samplingIntervalHint;

    private final UInteger deadbandType;

    private final Double deadbandValue;

    private final String indexRange;

    private final Variant substituteValue;

    private final QualifiedName[] metaDataProperties;

    public PublishedVariableDataType(NodeId publishedVariable, UInteger attributeId,
                                     Double samplingIntervalHint, UInteger deadbandType, Double deadbandValue, String indexRange,
                                     Variant substituteValue, QualifiedName[] metaDataProperties) {
        this.publishedVariable = publishedVariable;
        this.attributeId = attributeId;
        this.samplingIntervalHint = samplingIntervalHint;
        this.deadbandType = deadbandType;
        this.deadbandValue = deadbandValue;
        this.indexRange = indexRange;
        this.substituteValue = substituteValue;
        this.metaDataProperties = metaDataProperties;
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

    public NodeId getPublishedVariable() {
        return publishedVariable;
    }

    public UInteger getAttributeId() {
        return attributeId;
    }

    public Double getSamplingIntervalHint() {
        return samplingIntervalHint;
    }

    public UInteger getDeadbandType() {
        return deadbandType;
    }

    public Double getDeadbandValue() {
        return deadbandValue;
    }

    public String getIndexRange() {
        return indexRange;
    }

    public Variant getSubstituteValue() {
        return substituteValue;
    }

    public QualifiedName[] getMetaDataProperties() {
        return metaDataProperties;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 14323),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("PublishedVariable", LocalizedText.NULL_VALUE, new NodeId(0, 17), -1, null, UInteger.valueOf(0), false),
                new StructureField("AttributeId", LocalizedText.NULL_VALUE, new NodeId(0, 288), -1, null, UInteger.valueOf(0), false),
                new StructureField("SamplingIntervalHint", LocalizedText.NULL_VALUE, new NodeId(0, 290), -1, null, UInteger.valueOf(0), false),
                new StructureField("DeadbandType", LocalizedText.NULL_VALUE, new NodeId(0, 7), -1, null, UInteger.valueOf(0), false),
                new StructureField("DeadbandValue", LocalizedText.NULL_VALUE, new NodeId(0, 11), -1, null, UInteger.valueOf(0), false),
                new StructureField("IndexRange", LocalizedText.NULL_VALUE, new NodeId(0, 291), -1, null, UInteger.valueOf(0), false),
                new StructureField("SubstituteValue", LocalizedText.NULL_VALUE, new NodeId(0, 24), -1, null, UInteger.valueOf(0), false),
                new StructureField("MetaDataProperties", LocalizedText.NULL_VALUE, new NodeId(0, 20), 1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<PublishedVariableDataType> {
        @Override
        public Class<PublishedVariableDataType> getType() {
            return PublishedVariableDataType.class;
        }

        @Override
        public PublishedVariableDataType decodeType(SerializationContext context, UaDecoder decoder) {
            NodeId publishedVariable = decoder.readNodeId("PublishedVariable");
            UInteger attributeId = decoder.readUInt32("AttributeId");
            Double samplingIntervalHint = decoder.readDouble("SamplingIntervalHint");
            UInteger deadbandType = decoder.readUInt32("DeadbandType");
            Double deadbandValue = decoder.readDouble("DeadbandValue");
            String indexRange = decoder.readString("IndexRange");
            Variant substituteValue = decoder.readVariant("SubstituteValue");
            QualifiedName[] metaDataProperties = decoder.readQualifiedNameArray("MetaDataProperties");
            return new PublishedVariableDataType(publishedVariable, attributeId, samplingIntervalHint, deadbandType, deadbandValue, indexRange, substituteValue, metaDataProperties);
        }

        @Override
        public void encodeType(SerializationContext context, UaEncoder encoder,
                               PublishedVariableDataType value) {
            encoder.writeNodeId("PublishedVariable", value.getPublishedVariable());
            encoder.writeUInt32("AttributeId", value.getAttributeId());
            encoder.writeDouble("SamplingIntervalHint", value.getSamplingIntervalHint());
            encoder.writeUInt32("DeadbandType", value.getDeadbandType());
            encoder.writeDouble("DeadbandValue", value.getDeadbandValue());
            encoder.writeString("IndexRange", value.getIndexRange());
            encoder.writeVariant("SubstituteValue", value.getSubstituteValue());
            encoder.writeQualifiedNameArray("MetaDataProperties", value.getMetaDataProperties());
        }
    }
}
