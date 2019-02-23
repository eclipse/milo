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

import org.eclipse.milo.opcua.stack.core.serialization.codecs.BuiltinDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.structured.EndpointDescription;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class BuiltinDataTypeDictionaryTest {

    @Test
    public void testGetBuiltinCodec() throws Exception {
        BuiltinDataTypeCodec<?> codec = BuiltinDataTypeDictionary.getBuiltinCodec(EndpointDescription.class);

        assertNotNull(codec);
    }

}