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

import com.google.common.collect.ImmutableSet;
import org.eclipse.milo.opcua.sdk.server.events.FilterContext;
import org.eclipse.milo.opcua.sdk.server.events.OperatorContext;
import org.eclipse.milo.opcua.sdk.server.events.ValidationException;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.BaseEventNode;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.enumerated.FilterOperator;
import org.eclipse.milo.opcua.stack.core.types.structured.FilterOperand;

public class Operators {

    public static final Equals EQUALS = new Equals();
    public static final IsNull IS_NULL = new IsNull();
    public static final GreaterThan GREATER_THAN = new GreaterThan();
    public static final LessThan LESS_THAN = new LessThan();
    public static final GreaterThanOrEqual GREATER_THAN_OR_EQUAL = new GreaterThanOrEqual();
    public static final LessThanOrEqual LESS_THAN_OR_EQUAL = new LessThanOrEqual();
    public static final Not NOT = new Not();
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

    public static final ImmutableSet<FilterOperator> SUPPORTED_OPERATORS = ImmutableSet.<FilterOperator>builder()
        .add(FilterOperator.Equals)
        .add(FilterOperator.IsNull)
        .add(FilterOperator.GreaterThan)
        .add(FilterOperator.LessThan)
        .add(FilterOperator.GreaterThanOrEqual)
        .add(FilterOperator.LessThanOrEqual)
        .add(FilterOperator.Not)
        .add(FilterOperator.Cast)
        .build();

}
