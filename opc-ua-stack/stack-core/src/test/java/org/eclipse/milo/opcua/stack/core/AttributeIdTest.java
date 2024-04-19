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

            assertEquals(AttributeId.from(id).get(), attributeId);
        }

        assertFalse(AttributeId.from(-1).isPresent());
        assertFalse(AttributeId.from(0).isPresent());
        assertFalse(AttributeId.from(23).isPresent());
    }

    @Test
    public void testOrdering() {
        List<AttributeId> attributes = Lists.newArrayList(AttributeId.VARIABLE_TYPE_ATTRIBUTES);

        assertEquals(attributes.get(0), AttributeId.NodeId);
        assertEquals(attributes.get(1), AttributeId.NodeClass);
        assertEquals(attributes.get(2), AttributeId.BrowseName);
        assertEquals(attributes.get(3), AttributeId.DisplayName);
        assertEquals(attributes.get(4), AttributeId.Description);
        assertEquals(attributes.get(5), AttributeId.WriteMask);
        assertEquals(attributes.get(6), AttributeId.UserWriteMask);

        assertEquals(attributes.get(7), AttributeId.Value);
        assertEquals(attributes.get(8), AttributeId.DataType);
        assertEquals(attributes.get(9), AttributeId.ValueRank);
        assertEquals(attributes.get(10), AttributeId.ArrayDimensions);
        assertEquals(attributes.get(11), AttributeId.IsAbstract);
    }

}
