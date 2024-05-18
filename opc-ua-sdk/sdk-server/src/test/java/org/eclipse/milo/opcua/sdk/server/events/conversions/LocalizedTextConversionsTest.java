/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.events.conversions;

import static org.eclipse.milo.opcua.sdk.server.events.conversions.LocalizedTextConversions.localizedTextToString;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.junit.jupiter.api.Test;

public class LocalizedTextConversionsTest {

    @Test
    public void testLocalizedTextToString() {
        LocalizedText text = new LocalizedText("en-us", "foo");

        assertEquals("foo", localizedTextToString(text));
    }

}
