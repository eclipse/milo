/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
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
