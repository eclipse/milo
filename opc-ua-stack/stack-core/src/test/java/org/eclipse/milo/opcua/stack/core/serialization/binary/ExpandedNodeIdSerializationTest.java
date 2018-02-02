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

package org.eclipse.milo.opcua.stack.core.serialization.binary;

import java.util.UUID;

import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ExpandedNodeIdSerializationTest extends BinarySerializationFixture {

    @DataProvider
    public Object[][] getExpandedNodeIds() {
        return new Object[][]{
            {new ExpandedNodeId(0, 0, null, 0)},
            {new ExpandedNodeId(0, 1, null, 0)},
            {new ExpandedNodeId(0, 255, null, 0)},
            {new ExpandedNodeId(0, 256, null, 0)},
            {new ExpandedNodeId(1, 65535, null, 0)},
            {new ExpandedNodeId(1, 65536, null, 0)},
            {new ExpandedNodeId(255, 65535, null, 0)},
            {new ExpandedNodeId(255, 65536, null, 0)},
            {new ExpandedNodeId(1234, 567890, null, 0)},
            {new ExpandedNodeId(0, "hello, world", null, 0)},
            {new ExpandedNodeId(1, "hello, world", null, 0)},
            {new ExpandedNodeId(0, UUID.randomUUID(), null, 0)},
            {new ExpandedNodeId(1, UUID.randomUUID(), null, 0)},
            {new ExpandedNodeId(0, new ByteString(new byte[]{1, 2, 3, 4}), null, 0)},
            {new ExpandedNodeId(1, new ByteString(new byte[]{1, 2, 3, 4}), null, 0)},
            {new ExpandedNodeId(0, 0, "test", 0)},
            {new ExpandedNodeId(0, 0, "test", 0)},
            {new ExpandedNodeId(0, 0, null, 1)},
            {new ExpandedNodeId(0, 0, null, 1)},
            {new ExpandedNodeId(0, 0, "test", 1)},
        };
    }

    @Test(dataProvider = "getExpandedNodeIds", description = "ExpandedNodeId is round-trip serializable.")
    public void testExpandedNodeIdRoundTrip(ExpandedNodeId nodeId) throws Exception {
        writer.writeExpandedNodeId(nodeId);
        ExpandedNodeId decoded = reader.readExpandedNodeId();

        assertEquals(decoded, nodeId);
    }

}
