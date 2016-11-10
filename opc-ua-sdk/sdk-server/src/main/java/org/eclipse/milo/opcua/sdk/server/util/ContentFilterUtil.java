/*
 * Copyright (c) 2016 Kevin Herron
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

package org.eclipse.milo.opcua.sdk.server.util;

import java.util.Arrays;
import java.util.List;

import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.enumerated.FilterOperator;
import org.eclipse.milo.opcua.stack.core.types.structured.AttributeOperand;
import org.eclipse.milo.opcua.stack.core.types.structured.ContentFilter;
import org.eclipse.milo.opcua.stack.core.types.structured.ContentFilterElement;
import org.eclipse.milo.opcua.stack.core.types.structured.FilterOperand;
import org.eclipse.milo.opcua.stack.core.types.structured.SimpleAttributeOperand;

import static org.eclipse.milo.opcua.stack.core.util.ConversionUtil.l;

public class ContentFilterUtil {

    /*
     * Operands: SimpleAttributeOperand
     * Operators: Equals, IsNull, GreaterThan, LessThan, GreaterThanOrEqual, LessThanOrEqual, Like, Not, Between,
     * InList, And, Or, Cast, BitwiseAnd, BitwiseOr and OfType.
     */

    public void apply(ContentFilter filter) throws UaException {
        List<ContentFilterElement> elements = l(filter.getElements());

        for (int i = 0; i < elements.size(); i++) {
            ContentFilterElement element = elements.get(i);

            FilterOperator operator = element.getFilterOperator();
            SimpleAttributeOperand[] operands = extract(element.getFilterOperands());

            throw new UaException(StatusCodes.Bad_FilterOperandInvalid);
        }
    }

    private boolean apply(FilterOperator operator, FilterOperand[] operands) {
        if (operator == FilterOperator.Equals) {
            SimpleAttributeOperand op0 = (SimpleAttributeOperand) operands[0];
            AttributeOperand op1 = (AttributeOperand) operands[1];

            op0.getAttributeId();
            op0.getBrowsePath();
            op0.getIndexRange();
            op0.getTypeDefinitionId();
        }

        return false;
    }

    private SimpleAttributeOperand[] extract(ExtensionObject[] operandXos) {
        return Arrays.stream(operandXos)
            .map(xo -> (SimpleAttributeOperand) xo.decode())
            .toArray(SimpleAttributeOperand[]::new);
    }

    private static class EqualsOperator {

        public void apply(FilterOperand[] operands) throws UaException {

        }

    }

}
