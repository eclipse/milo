/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.events.operators;

import javax.annotation.Nullable;

import com.google.common.primitives.UnsignedLongs;
import org.eclipse.milo.opcua.sdk.server.events.OperatorContext;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.BaseEventNode;
import org.eclipse.milo.opcua.stack.core.BuiltinDataType;

public class LessThan extends ImplicitConversionBinaryOperator<Boolean> {

    LessThan() {}

    @Nullable
    @Override
    protected Boolean apply(
        OperatorContext context,
        BaseEventNode eventNode,
        BuiltinDataType dataType,
        @Nullable Object operand0,
        @Nullable Object operand1) {

        if (operand0 instanceof Number && operand1 instanceof Number) {
            Number n0 = (Number) operand0;
            Number n1 = (Number) operand1;

            switch (dataType) {
                case SByte:
                case Int16:
                case Int32:
                case Int64:
                case Byte:
                case UInt16:
                case UInt32:
                    return n0.longValue() < n1.longValue();

                case UInt64:
                    return UnsignedLongs.compare(n0.longValue(), n1.longValue()) < 0;

                case Float:
                    return n0.floatValue() < n1.floatValue();

                case Double:
                    return n0.doubleValue() < n1.doubleValue();

                default:
                    return false;
            }
        } else {
            return false;
        }
    }

}
