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

package org.eclipse.milo.opcua.stack.core.types.builtin;

import org.testng.annotations.Test;

public class VariantTest {

    @Test
    public void testVariantCanContainVariantArray() {
        new Variant(new Variant[] {new Variant(0), new Variant(1), new Variant(2)});
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void variantCannotContainVariant() {
        new Variant(new Variant(null));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void variantCannotContainDataValue() {
        new Variant(new DataValue(Variant.NULL_VALUE));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void variantCannotContainDiagnosticInfo() {
        new Variant(DiagnosticInfo.NULL_VALUE);
    }

}
