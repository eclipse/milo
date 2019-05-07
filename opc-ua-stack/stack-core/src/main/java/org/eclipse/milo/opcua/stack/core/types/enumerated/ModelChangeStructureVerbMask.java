/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.core.types.enumerated;

import javax.annotation.Nullable;

import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEnumeration;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;

public enum ModelChangeStructureVerbMask implements UaEnumeration {
    NodeAdded(1),

    NodeDeleted(2),

    ReferenceAdded(4),

    ReferenceDeleted(8),

    DataTypeChanged(16);

    private final int value;

    ModelChangeStructureVerbMask(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Nullable
    public static ModelChangeStructureVerbMask from(int value) {
        switch (value) {
            case 1:
                return NodeAdded;
            case 2:
                return NodeDeleted;
            case 4:
                return ReferenceAdded;
            case 8:
                return ReferenceDeleted;
            case 16:
                return DataTypeChanged;
            default:
                return null;
        }
    }

    public static ExpandedNodeId getTypeId() {
        return ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=11941");
    }

    public static class Codec extends GenericDataTypeCodec<ModelChangeStructureVerbMask> {
        @Override
        public Class<ModelChangeStructureVerbMask> getType() {
            return ModelChangeStructureVerbMask.class;
        }

        @Override
        public ModelChangeStructureVerbMask decode(SerializationContext context, UaDecoder decoder) {
            return ModelChangeStructureVerbMask.from(decoder.readInt32(null));
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           ModelChangeStructureVerbMask value) {
            encoder.writeInt32(null, value.getValue());
        }
    }
}
