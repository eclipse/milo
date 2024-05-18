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

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class VariantTest {

    @Test
    public void variantCanContainDataValue() {
        new Variant(new DataValue(Variant.NULL_VALUE));
    }

    @Test
    public void variantCanContainVariantArray() {
        new Variant(new Variant[]{new Variant(0), new Variant(1), new Variant(2)});
    }

    @Test
    public void variantCannotContainVariant() {
        assertThrows(IllegalArgumentException.class, () -> new Variant(new Variant(null)));
    }

    @Test
    public void variantCannotContainDiagnosticInfo() {
    	 assertThrows(IllegalArgumentException.class, () -> new Variant(DiagnosticInfo.NULL_VALUE));
    }

}
