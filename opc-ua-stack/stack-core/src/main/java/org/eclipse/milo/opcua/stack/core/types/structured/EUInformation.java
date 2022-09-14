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
import org.eclipse.milo.opcua.stack.core.encoding.EncodingContext;
import org.eclipse.milo.opcua.stack.core.encoding.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.encoding.UaDecoder;
import org.eclipse.milo.opcua.stack.core.encoding.UaEncoder;
import org.eclipse.milo.opcua.stack.core.types.UaStructuredType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part8/5.6.3/#5.6.3.2">https://reference.opcfoundation.org/v105/Core/docs/Part8/5.6.3/#5.6.3.2</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class EUInformation extends Structure implements UaStructuredType {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=887");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=889");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=888");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15376");

    private final String namespaceUri;

    private final Integer unitId;

    private final LocalizedText displayName;

    private final LocalizedText description;

    public EUInformation(String namespaceUri, Integer unitId, LocalizedText displayName,
                         LocalizedText description) {
        this.namespaceUri = namespaceUri;
        this.unitId = unitId;
        this.displayName = displayName;
        this.description = description;
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

    public String getNamespaceUri() {
        return namespaceUri;
    }

    public Integer getUnitId() {
        return unitId;
    }

    public LocalizedText getDisplayName() {
        return displayName;
    }

    public LocalizedText getDescription() {
        return description;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 889),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("NamespaceUri", LocalizedText.NULL_VALUE, new NodeId(0, 12), -1, null, UInteger.valueOf(0), false),
                new StructureField("UnitId", LocalizedText.NULL_VALUE, new NodeId(0, 6), -1, null, UInteger.valueOf(0), false),
                new StructureField("DisplayName", LocalizedText.NULL_VALUE, new NodeId(0, 21), -1, null, UInteger.valueOf(0), false),
                new StructureField("Description", LocalizedText.NULL_VALUE, new NodeId(0, 21), -1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<EUInformation> {
        @Override
        public Class<EUInformation> getType() {
            return EUInformation.class;
        }

        @Override
        public EUInformation decodeType(EncodingContext context, UaDecoder decoder) {
            String namespaceUri = decoder.decodeString("NamespaceUri");
            Integer unitId = decoder.decodeInt32("UnitId");
            LocalizedText displayName = decoder.decodeLocalizedText("DisplayName");
            LocalizedText description = decoder.decodeLocalizedText("Description");
            return new EUInformation(namespaceUri, unitId, displayName, description);
        }

        @Override
        public void encodeType(EncodingContext context, UaEncoder encoder, EUInformation value) {
            encoder.encodeString("NamespaceUri", value.getNamespaceUri());
            encoder.encodeInt32("UnitId", value.getUnitId());
            encoder.encodeLocalizedText("DisplayName", value.getDisplayName());
            encoder.encodeLocalizedText("Description", value.getDescription());
        }
    }
}
