/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.google.common.collect.Lists;

public class AttributeIdTest {

    @Test
    public void testFrom() {
        for (AttributeId attributeId : AttributeId.values()) {
            int id = attributeId.id();

            assertEquals(attributeId, AttributeId.from(id).get());
        }

        assertFalse(AttributeId.from(-1).isPresent());
        assertFalse(AttributeId.from(0).isPresent());
        assertFalse(AttributeId.from(23).isPresent());
    }

    @Test
    public void testOrdering() {
        List<AttributeId> attributes = Lists.newArrayList(AttributeId.VARIABLE_TYPE_ATTRIBUTES);

        assertEquals(AttributeId.NodeId, attributes.get(0));
        assertEquals(AttributeId.NodeClass, attributes.get(1));
        assertEquals(AttributeId.BrowseName, attributes.get(2));
        assertEquals(AttributeId.DisplayName, attributes.get(3));
        assertEquals(AttributeId.Description, attributes.get(4));
        assertEquals(AttributeId.WriteMask, attributes.get(5));
        assertEquals(AttributeId.UserWriteMask, attributes.get(6));

        assertEquals(AttributeId.Value, attributes.get(7));
        assertEquals(AttributeId.DataType, attributes.get(8));
        assertEquals(AttributeId.ValueRank, attributes.get(9));
        assertEquals(AttributeId.ArrayDimensions, attributes.get(10));
        assertEquals(AttributeId.IsAbstract, attributes.get(11));
    }

}
