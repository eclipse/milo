/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.core;

import java.util.UUID;

import org.eclipse.milo.opcua.stack.core.NodeIds;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ReferenceTest {

    @Test
    public void testEquality() {
        Reference reference1 = new Reference(
            new NodeId(1, UUID.randomUUID()),
            NodeIds.HasComponent,
            new NodeId(1, UUID.randomUUID()).expanded(),
            Reference.Direction.FORWARD
        );

        Reference reference2 = new Reference(
            reference1.getSourceNodeId(),
            reference1.getReferenceTypeId(),
            reference1.getTargetNodeId(),
            reference1.getDirection()
        );

        assertEquals(reference1, reference2);
    }

}
