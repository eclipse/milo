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
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class ConfigurationVersionDataType extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=14593");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=14847");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=14803");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15049");

    private final UInteger majorVersion;

    private final UInteger minorVersion;

    public ConfigurationVersionDataType(UInteger majorVersion, UInteger minorVersion) {
        this.majorVersion = majorVersion;
        this.minorVersion = minorVersion;
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

    public UInteger getMajorVersion() {
        return majorVersion;
    }

    public UInteger getMinorVersion() {
        return minorVersion;
    }

    public static final class Codec extends GenericDataTypeCodec<ConfigurationVersionDataType> {
        @Override
        public Class<ConfigurationVersionDataType> getType() {
            return ConfigurationVersionDataType.class;
        }

        @Override
        public ConfigurationVersionDataType decode(SerializationContext context, UaDecoder decoder) {
            UInteger majorVersion = decoder.readUInt32("MajorVersion");
            UInteger minorVersion = decoder.readUInt32("MinorVersion");
            return new ConfigurationVersionDataType(majorVersion, minorVersion);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           ConfigurationVersionDataType value) {
            encoder.writeUInt32("MajorVersion", value.getMajorVersion());
            encoder.writeUInt32("MinorVersion", value.getMinorVersion());
        }
    }
}
