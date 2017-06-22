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

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GuidSerializationTest extends BinarySerializationFixture {

    @DataProvider(name = "GuidProvider")
    public Object[][] getGuids() {
        return new Object[][]{
            {UUID.fromString("C496578A-0DFE-4b8f-870A-745238C6AEAE")},
            {UUID.fromString("72962B91-FA75-4ae6-8D28-B404DC7DAF63")},
            {UUID.randomUUID()},
            {UUID.randomUUID()},
            {UUID.randomUUID()},
        };
    }

    @Test(dataProvider = "GuidProvider", description = "Guid is round-trip serializable.")
    public void testGuidRoundTrip(UUID uuid) throws Exception {
        writer.writeGuid(uuid);
        UUID decoded = reader.readGuid();

        assertEquals(decoded, uuid);
    }

}
