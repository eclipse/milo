/*
 * Copyright (c) 2017 Red Hat Inc
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
