/*
 * Copyright (c) 2017 Kevin Herron
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

package org.eclipse.milo.opcua.sdk.core;

import org.eclipse.milo.opcua.stack.core.types.structured.EUInformation;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class CefactEngineeringUnitsTest {

    @Test
    public void test() {
        for (EUInformation eu : CefactEngineeringUnits.getAll()) {
            assertEquals(CefactEngineeringUnits.getByUnitId(eu.getUnitId()), eu);
        }
    }

}