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
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part14/9.1.3/#9.1.3.7.3">https://reference.opcfoundation.org/v105/Core/docs/Part14/9.1.3/#9.1.3.7.3</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class PubSubConfigurationRefDataType extends Structure implements UaStructuredType {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=25519");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=25531");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=25547");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=25563");

    private final PubSubConfigurationRefMask configurationMask;

    private final UShort elementIndex;

    private final UShort connectionIndex;

    private final UShort groupIndex;

    public PubSubConfigurationRefDataType(PubSubConfigurationRefMask configurationMask,
                                          UShort elementIndex, UShort connectionIndex, UShort groupIndex) {
        this.configurationMask = configurationMask;
        this.elementIndex = elementIndex;
        this.connectionIndex = connectionIndex;
        this.groupIndex = groupIndex;
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

    public PubSubConfigurationRefMask getConfigurationMask() {
        return configurationMask;
    }

    public UShort getElementIndex() {
        return elementIndex;
    }

    public UShort getConnectionIndex() {
        return connectionIndex;
    }

    public UShort getGroupIndex() {
        return groupIndex;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 25531),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("ConfigurationMask", LocalizedText.NULL_VALUE, new NodeId(0, 25517), -1, null, UInteger.valueOf(0), false),
                new StructureField("ElementIndex", LocalizedText.NULL_VALUE, new NodeId(0, 5), -1, null, UInteger.valueOf(0), false),
                new StructureField("ConnectionIndex", LocalizedText.NULL_VALUE, new NodeId(0, 5), -1, null, UInteger.valueOf(0), false),
                new StructureField("GroupIndex", LocalizedText.NULL_VALUE, new NodeId(0, 5), -1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<PubSubConfigurationRefDataType> {
        @Override
        public Class<PubSubConfigurationRefDataType> getType() {
            return PubSubConfigurationRefDataType.class;
        }

        @Override
        public PubSubConfigurationRefDataType decodeType(SerializationContext context,
                                                         UaDecoder decoder) {
            PubSubConfigurationRefMask configurationMask = new PubSubConfigurationRefMask(decoder.readUInt32("ConfigurationMask"));
            UShort elementIndex = decoder.readUInt16("ElementIndex");
            UShort connectionIndex = decoder.readUInt16("ConnectionIndex");
            UShort groupIndex = decoder.readUInt16("GroupIndex");
            return new PubSubConfigurationRefDataType(configurationMask, elementIndex, connectionIndex, groupIndex);
        }

        @Override
        public void encodeType(SerializationContext context, UaEncoder encoder,
                               PubSubConfigurationRefDataType value) {
            encoder.writeUInt32("ConfigurationMask", value.getConfigurationMask().getValue());
            encoder.writeUInt16("ElementIndex", value.getElementIndex());
            encoder.writeUInt16("ConnectionIndex", value.getConnectionIndex());
            encoder.writeUInt16("GroupIndex", value.getGroupIndex());
        }
    }
}
