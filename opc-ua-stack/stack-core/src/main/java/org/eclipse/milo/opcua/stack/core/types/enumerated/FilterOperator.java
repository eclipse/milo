/*
 * Copyright (c) 2021 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.core.types.enumerated;

import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEnumeration;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.jetbrains.annotations.Nullable;

public enum FilterOperator implements UaEnumeration {
    Equals(0),

    IsNull(1),

    GreaterThan(2),

    LessThan(3),

    GreaterThanOrEqual(4),

    LessThanOrEqual(5),

    Like(6),

    Not(7),

    Between(8),

    InList(9),

    And(10),

    Or(11),

    Cast(12),

    InView(13),

    OfType(14),

    RelatedTo(15),

    BitwiseAnd(16),

    BitwiseOr(17);

    private final int value;

    FilterOperator(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Nullable
    public static FilterOperator from(int value) {
        switch (value) {
            case 0:
                return Equals;
            case 1:
                return IsNull;
            case 2:
                return GreaterThan;
            case 3:
                return LessThan;
            case 4:
                return GreaterThanOrEqual;
            case 5:
                return LessThanOrEqual;
            case 6:
                return Like;
            case 7:
                return Not;
            case 8:
                return Between;
            case 9:
                return InList;
            case 10:
                return And;
            case 11:
                return Or;
            case 12:
                return Cast;
            case 13:
                return InView;
            case 14:
                return OfType;
            case 15:
                return RelatedTo;
            case 16:
                return BitwiseAnd;
            case 17:
                return BitwiseOr;
            default:
                return null;
        }
    }

    public static ExpandedNodeId getTypeId() {
        return ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=576");
    }

    public static class Codec extends GenericDataTypeCodec<FilterOperator> {
        @Override
        public Class<FilterOperator> getType() {
            return FilterOperator.class;
        }

        @Override
        public FilterOperator decode(SerializationContext context, UaDecoder decoder) {
            return decoder.readEnum(null, FilterOperator.class);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, FilterOperator value) {
            encoder.writeEnum(null, value);
        }
    }
}
