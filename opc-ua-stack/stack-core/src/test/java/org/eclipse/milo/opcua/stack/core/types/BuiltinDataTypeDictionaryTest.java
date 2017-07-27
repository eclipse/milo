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