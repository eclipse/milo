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

import org.testng.Assert;
import org.testng.annotations.Test;

public class QualifiedNameTest {

    @Test
    public void testToStringAndBack1() {
        assertSymmetry("0:foo");
    }

    @Test
    public void testToStringAndBack2() {
        assertSymmetry("0:foo:bar");
    }

    private void assertSymmetry(String string) {
        String reString = QualifiedName.parse(string).toParseableString();
        Assert.assertEquals(reString, string);
    }

}
