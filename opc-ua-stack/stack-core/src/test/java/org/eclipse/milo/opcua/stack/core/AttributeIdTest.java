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

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

public class AttributeIdTest {

    @Test
    public void testFrom() throws Exception {
        for (AttributeId attributeId : AttributeId.values()) {
            int id = attributeId.id();

            assertEquals(attributeId, AttributeId.from(id).get());
        }

        assertFalse(AttributeId.from(-1).isPresent());
        assertFalse(AttributeId.from(0).isPresent());
        assertFalse(AttributeId.from(23).isPresent());
    }

}
