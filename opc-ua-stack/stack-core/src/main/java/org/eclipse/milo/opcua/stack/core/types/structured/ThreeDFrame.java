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

@EqualsAndHashCode(
    callSuper = true
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class ThreeDFrame extends Frame implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=18814");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=18823");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=18859");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=19072");

    private final ThreeDCartesianCoordinates cartesianCoordinates;

    private final ThreeDOrientation orientation;

    public ThreeDFrame(ThreeDCartesianCoordinates cartesianCoordinates,
                       ThreeDOrientation orientation) {
        this.cartesianCoordinates = cartesianCoordinates;
        this.orientation = orientation;
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

    public ThreeDCartesianCoordinates getCartesianCoordinates() {
        return cartesianCoordinates;
    }

    public ThreeDOrientation getOrientation() {
        return orientation;
    }

    public static final class Codec extends GenericDataTypeCodec<ThreeDFrame> {
        @Override
        public Class<ThreeDFrame> getType() {
            return ThreeDFrame.class;
        }

        @Override
        public ThreeDFrame decode(SerializationContext context, UaDecoder decoder) {
            ThreeDCartesianCoordinates cartesianCoordinates = (ThreeDCartesianCoordinates) decoder.readStruct("CartesianCoordinates", ThreeDCartesianCoordinates.TYPE_ID);
            ThreeDOrientation orientation = (ThreeDOrientation) decoder.readStruct("Orientation", ThreeDOrientation.TYPE_ID);
            return new ThreeDFrame(cartesianCoordinates, orientation);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, ThreeDFrame value) {
            encoder.writeStruct("CartesianCoordinates", value.getCartesianCoordinates(), ThreeDCartesianCoordinates.TYPE_ID);
            encoder.writeStruct("Orientation", value.getOrientation(), ThreeDOrientation.TYPE_ID);
        }
    }
}
