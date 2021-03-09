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
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;

@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class EUInformation extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=887");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=888");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=889");

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
    public ExpandedNodeId getXmlEncodingId() {
        return XML_ENCODING_ID;
    }

    @Override
    public ExpandedNodeId getBinaryEncodingId() {
        return BINARY_ENCODING_ID;
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

    public static final class Codec extends GenericDataTypeCodec<EUInformation> {
        @Override
        public Class<EUInformation> getType() {
            return EUInformation.class;
        }

        @Override
        public EUInformation decode(SerializationContext context, UaDecoder decoder) {
            String namespaceUri = decoder.readString("NamespaceUri");
            Integer unitId = decoder.readInt32("UnitId");
            LocalizedText displayName = decoder.readLocalizedText("DisplayName");
            LocalizedText description = decoder.readLocalizedText("Description");
            return new EUInformation(namespaceUri, unitId, displayName, description);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, EUInformation value) {
            encoder.writeString("NamespaceUri", value.getNamespaceUri());
            encoder.writeInt32("UnitId", value.getUnitId());
            encoder.writeLocalizedText("DisplayName", value.getDisplayName());
            encoder.writeLocalizedText("Description", value.getDescription());
        }
    }
}
