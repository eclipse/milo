/*
 * Copyright (c) 2018 Kevin Herron
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v1.0 which accompany this distribution.
 *
 * The Eclipse Public License is available at
 *   http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 *   http://www.eclipse.org/org/documents/edl-v10.html.
 */

package org.eclipse.milo.opcua.sdk.server.events.operators;

import javax.annotation.Nullable;

import org.eclipse.milo.opcua.sdk.server.events.FilterContext;
import org.eclipse.milo.opcua.sdk.server.events.OperatorContext;
import org.eclipse.milo.opcua.sdk.server.events.ValidationException;
import org.eclipse.milo.opcua.sdk.server.events.conversions.ImplicitConversions;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.BaseEventNode;
import org.eclipse.milo.opcua.stack.core.BuiltinDataType;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.structured.FilterOperand;


public class Cast implements Operator<Object> {

    Cast() {}

    @Override
    public void validate(FilterContext context, FilterOperand[] operands) throws ValidationException {
        if (operands.length < 2) {
            throw new ValidationException(StatusCodes.Bad_FilterOperandCountMismatch);
        }
    }

    @Nullable
    @Override
    public Object apply(
        OperatorContext context,
        BaseEventNode eventNode,
        FilterOperand[] operands) throws UaException {

        validate(context, operands);

        FilterOperand op0 = operands[0];
        FilterOperand op1 = operands[1];

        Object sourceValue = context.resolve(op0, eventNode);

        Object dataTypeIdObject = context.resolve(op1, eventNode);

        if (dataTypeIdObject instanceof NodeId) {
            NodeId dataTypeId = (NodeId) dataTypeIdObject;

            BuiltinDataType dataType = BuiltinDataType.fromNodeId(dataTypeId);

            if (dataType != null) {
                return ImplicitConversions.convert(sourceValue, dataType);
            } else {
                return null;
            }
        } else if (dataTypeIdObject instanceof ExpandedNodeId) {
            ExpandedNodeId dataTypeId = (ExpandedNodeId) dataTypeIdObject;

            BuiltinDataType dataType = BuiltinDataType.fromNodeId(dataTypeId);

            if (dataType != null) {
                return ImplicitConversions.convert(sourceValue, dataType);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

}
