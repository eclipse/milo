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

package org.eclipse.milo.opcua.stack;

import org.eclipse.milo.opcua.stack.core.channel.headers.SequenceHeader;
import com.google.common.primitives.UnsignedInteger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SequenceHeaderTest extends SerializationFixture2 {

    @DataProvider(name = "parameters")
    public Object[][] getParameters() {
        return new Object[][]{
                {0, 0},
                {Integer.MAX_VALUE - 1, Integer.MAX_VALUE - 1},
                {Integer.MAX_VALUE, Integer.MAX_VALUE},
                {Integer.MAX_VALUE + 1L, Integer.MAX_VALUE + 1L},
                {UnsignedInteger.MAX_VALUE.longValue(), UnsignedInteger.MAX_VALUE.longValue()}
        };
    }

    @Test(dataProvider = "parameters", description = "SequenceHeader is serializable.")
    public void testSerialization(long sequenceNumber, long requestId) {
        SequenceHeader header = new SequenceHeader(sequenceNumber, requestId);

        assertSerializable(header, SequenceHeader::encode, SequenceHeader::decode);
    }

}
