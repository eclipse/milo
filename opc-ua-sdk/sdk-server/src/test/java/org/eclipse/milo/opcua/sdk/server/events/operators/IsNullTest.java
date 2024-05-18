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

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.eclipse.milo.opcua.sdk.server.events.OperatorContext;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.BaseEventTypeNode;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.structured.FilterOperand;
import org.eclipse.milo.opcua.stack.core.types.structured.LiteralOperand;
import org.junit.jupiter.api.Test;


public class IsNullTest {

    @Test
    public void testNullValue() throws Exception {
        OperatorContext context = mock(OperatorContext.class);
        BaseEventTypeNode eventNode = mock(BaseEventTypeNode.class);

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
        BaseEventTypeNode eventNode = mock(BaseEventTypeNode.class);

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
