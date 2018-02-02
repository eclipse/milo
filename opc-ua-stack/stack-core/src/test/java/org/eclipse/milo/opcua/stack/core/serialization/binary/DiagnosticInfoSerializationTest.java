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

import org.eclipse.milo.opcua.stack.core.types.builtin.DiagnosticInfo;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class DiagnosticInfoSerializationTest extends BinarySerializationFixture {

    @DataProvider(name = "DiagnosticInfoProvider")
    public Object[][] getDiagnosticInfos() {
        return new Object[][]{
            {null},
            {new DiagnosticInfo(1, -1, -1, -1, null, null, null)},
            {new DiagnosticInfo(-1, 1, -1, -1, null, null, null)},
            {new DiagnosticInfo(-1, -1, 1, -1, null, null, null)},
            {new DiagnosticInfo(-1, -1, -1, 1, null, null, null)},
            {new DiagnosticInfo(-1, -1, -1, -1, "hello, world", null, null)},
            {new DiagnosticInfo(-1, -1, -1, -1, null, StatusCode.GOOD, null)},
            {new DiagnosticInfo(-1, -1, -1, -1, null, null, new DiagnosticInfo(1, 2, 3, 4, "abc", StatusCode.GOOD, null))},
            {new DiagnosticInfo(1, 2, 3, 4, "abc", StatusCode.GOOD, null)},
        };
    }

    @Test(dataProvider = "DiagnosticInfoProvider")
    public void testDiagnosticInfoRoundTrip(DiagnosticInfo diagnosticInfo) {
        writer.writeDiagnosticInfo(diagnosticInfo);
        DiagnosticInfo decoded = reader.readDiagnosticInfo();

        assertEquals(decoded, diagnosticInfo);
    }

}
