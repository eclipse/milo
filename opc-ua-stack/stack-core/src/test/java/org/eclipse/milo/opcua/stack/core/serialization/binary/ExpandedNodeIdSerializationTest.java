/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
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
