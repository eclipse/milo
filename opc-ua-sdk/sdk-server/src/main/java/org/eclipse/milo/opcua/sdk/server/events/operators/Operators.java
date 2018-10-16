/*
 * Copyright (c) 2017 Kevin Herron
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
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.BaseEventNode;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.structured.FilterOperand;

public class Operators {

    public static final Equals EQUALS = new Equals();
    public static final IsNull IS_NULL = new IsNull();
    public static final GreaterThan GREATER_THAN = new GreaterThan();
    public static final LessThan LESS_THAN = new LessThan();
    public static final GreaterThanOrEqual GREATER_THAN_OR_EQUAL = new GreaterThanOrEqual();
    public static final LessThanOrEqual LESS_THAN_OR_EQUAL = new LessThanOrEqual();
    public static final Not NOT = new Not();
    public static final OfType OF_TYPE = new OfType();
    public static final Cast CAST = new Cast();

    public static final Operator<Object> UNSUPPORTED = new Operator<Object>() {
        @Nullable
        @Override
        public Object apply(
            OperatorContext context,
            BaseEventNode eventNode,
            FilterOperand[] operands) throws UaException {

            throw new UaException(StatusCodes.Bad_FilterOperatorUnsupported);
        }

        @Override
        public void validate(FilterContext context, FilterOperand[] operands) throws ValidationException {
            throw new ValidationException(StatusCodes.Bad_FilterOperatorUnsupported);
        }
    };

}
