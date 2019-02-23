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

import org.eclipse.milo.opcua.sdk.server.events.FilterContext;
import org.eclipse.milo.opcua.sdk.server.events.OperatorContext;
import org.eclipse.milo.opcua.sdk.server.events.ValidationException;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.BaseEventNode;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.structured.FilterOperand;

public class Not implements Operator<Boolean> {

    Not() {}

    @Override
    public void validate(FilterContext context, FilterOperand[] operands) throws ValidationException {
        if (operands.length < 1) {
            throw new ValidationException(StatusCodes.Bad_FilterOperandCountMismatch);
        }
    }

    @Nullable
    @Override
    public Boolean apply(
        OperatorContext context,
        BaseEventNode eventNode,
        FilterOperand[] operands) throws UaException {

        validate(context, operands);

        FilterOperand op0 = operands[0];

        Object value0 = context.resolve(op0, eventNode);

        if (value0 instanceof Boolean) {
            return !(Boolean) value0;
        } else {
            return null;
        }
    }

}
