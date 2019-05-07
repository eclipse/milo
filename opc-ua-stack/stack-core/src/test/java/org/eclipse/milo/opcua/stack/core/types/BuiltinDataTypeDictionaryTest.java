/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.core.types;

import org.eclipse.milo.opcua.stack.core.serialization.codecs.DataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.structured.EndpointDescription;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;

public class BuiltinDataTypeDictionaryTest {

    @Test
    public void testGetBuiltinCodec() {
        DataTypeCodec codec = BuiltinDataTypeDictionary.getBinaryInstance().getCodec(
            EndpointDescription.TYPE_ID.local()
                .orElseThrow(IllegalStateException::new)
        );

        assertNotNull(codec);
    }

}