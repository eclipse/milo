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

public class IsNullTest {

    @Test
    public void testNullValue() throws Exception {
        OperatorContext context = mock(OperatorContext.class);
        BaseEventNode eventNode = mock(BaseEventNode.class);

        FilterOperand op0 = new LiteralOperand(new Variant(null));

        when(context.resolve(op0, eventNode)).thenReturn(null);

        Boolean result = Operators.IS_NULL.apply(
            context,
            eventNode,
            new FilterOperand[]{op0}
        );

        assertNotNull(result);
        assertTrue(result);
    }

    @Test
    public void testNonNullValue() throws Exception {
        OperatorContext context = mock(OperatorContext.class);
        BaseEventNode eventNode = mock(BaseEventNode.class);

        FilterOperand op0 = new LiteralOperand(new Variant(42));

        when(context.resolve(op0, eventNode)).thenReturn(42);

        Boolean result = Operators.IS_NULL.apply(
            context,
            eventNode,
            new FilterOperand[]{op0}
        );

        assertNotNull(result);
        assertFalse(result);
    }

}
