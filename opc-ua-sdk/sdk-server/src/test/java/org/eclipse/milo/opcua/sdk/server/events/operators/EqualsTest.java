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

import org.eclipse.milo.opcua.sdk.server.events.OperatorContext;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.BaseEventNode;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.structured.FilterOperand;
import org.eclipse.milo.opcua.stack.core.types.structured.LiteralOperand;
import org.testng.annotations.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

public class EqualsTest {

    @Test
    public void testArray() throws Exception {
        OperatorContext context = mock(OperatorContext.class);
        BaseEventNode eventNode = mock(BaseEventNode.class);

        FilterOperand op0 = new LiteralOperand(new Variant(new int[]{1, 2, 3}));
        FilterOperand op1 = new LiteralOperand(new Variant(new int[]{1, 2, 3}));

        when(context.resolve(op0, eventNode))
            .thenReturn(new int[]{1, 2, 3});

        when(context.resolve(op1, eventNode))
            .thenReturn(new int[]{1, 2, 3});

        Boolean result = Operators.EQUALS.apply(
            context,
            eventNode,
            new FilterOperand[]{op0, op1}
        );

        assertNotNull(result);
        assertTrue(result);
    }

    @Test
    public void testScalar() throws Exception {
        OperatorContext context = mock(OperatorContext.class);
        BaseEventNode eventNode = mock(BaseEventNode.class);

        FilterOperand op0 = new LiteralOperand(new Variant(42));
        FilterOperand op1 = new LiteralOperand(new Variant(42));

        when(context.resolve(op0, eventNode))
            .thenReturn(42);

        when(context.resolve(op1, eventNode))
            .thenReturn(42);

        Boolean result = Operators.EQUALS.apply(
            context,
            eventNode,
            new FilterOperand[]{op0, op1}
        );

        assertNotNull(result);
        assertTrue(result);
    }

    @Test
    public void testArrayWithImplicitConversion() throws Exception {
        OperatorContext context = mock(OperatorContext.class);
        BaseEventNode eventNode = mock(BaseEventNode.class);

        FilterOperand op0 = new LiteralOperand(new Variant(new int[]{1, 2, 3}));
        FilterOperand op1 = new LiteralOperand(new Variant(new long[]{1L, 2L, 3L}));

        when(context.resolve(op0, eventNode))
            .thenReturn(new int[]{1, 2, 3});

        when(context.resolve(op1, eventNode))
            .thenReturn(new Long[]{1L, 2L, 3L});

        Boolean result = Operators.EQUALS.apply(
            context,
            eventNode,
            new FilterOperand[]{op0, op1}
        );

        assertNotNull(result);
        assertTrue(result);
    }

    @Test
    public void testScalarWithImplicitConversion() throws Exception {
        OperatorContext context = mock(OperatorContext.class);
        BaseEventNode eventNode = mock(BaseEventNode.class);

        FilterOperand op0 = new LiteralOperand(new Variant(42));
        FilterOperand op1 = new LiteralOperand(new Variant(42L));

        when(context.resolve(op0, eventNode))
            .thenReturn(42);
        when(context.resolve(op1, eventNode))
            .thenReturn(42L);

        Boolean result = Operators.EQUALS.apply(
            context,
            eventNode,
            new FilterOperand[]{op0, op1}
        );

        assertNotNull(result);
        assertTrue(result);
    }

    @Test
    public void testArrayNotEqual() throws Exception {
        OperatorContext context = mock(OperatorContext.class);
        BaseEventNode eventNode = mock(BaseEventNode.class);

        FilterOperand op0 = new LiteralOperand(new Variant(new int[]{1, 2, 3}));
        FilterOperand op1 = new LiteralOperand(new Variant(new int[]{3, 2, 1}));

        when(context.resolve(op0, eventNode))
            .thenReturn(new int[]{1, 2, 3});

        when(context.resolve(op1, eventNode))
            .thenReturn(new int[]{3, 2, 1});

        Boolean result = Operators.EQUALS.apply(
            context,
            eventNode,
            new FilterOperand[]{op0, op1}
        );

        assertNotNull(result);
        assertFalse(result);
    }

    @Test
    public void testScalarNotEqual() throws Exception {
        OperatorContext context = mock(OperatorContext.class);
        BaseEventNode eventNode = mock(BaseEventNode.class);

        FilterOperand op0 = new LiteralOperand(new Variant(1));
        FilterOperand op1 = new LiteralOperand(new Variant(2));

        when(context.resolve(op0, eventNode))
            .thenReturn(1);

        when(context.resolve(op1, eventNode))
            .thenReturn(2);

        Boolean result = Operators.EQUALS.apply(
            context,
            eventNode,
            new FilterOperand[]{op0, op1}
        );

        assertNotNull(result);
        assertFalse(result);
    }

}
