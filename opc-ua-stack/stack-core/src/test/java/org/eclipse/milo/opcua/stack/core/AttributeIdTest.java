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
