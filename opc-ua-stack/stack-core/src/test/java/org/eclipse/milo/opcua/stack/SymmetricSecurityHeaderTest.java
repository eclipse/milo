/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack;

import org.eclipse.milo.opcua.stack.core.channel.headers.SymmetricSecurityHeader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import com.google.common.primitives.UnsignedInteger;

public class SymmetricSecurityHeaderTest extends SerializationFixture2 {

    public static Object[][] getParameters() {
        return new Object[][]{
                {0},
                {Integer.MAX_VALUE - 1},
                {Integer.MAX_VALUE},
                {Integer.MAX_VALUE + 1L},
                {UnsignedInteger.MAX_VALUE.longValue()}
        };
    }

    @ParameterizedTest
    @MethodSource("getParameters")
    @DisplayName("SymmetricSecurityHeader is serializable.")
    public void testSerialization(long value) {
        SymmetricSecurityHeader header = new SymmetricSecurityHeader(value);

        assertSerializable(header, SymmetricSecurityHeader::encode, SymmetricSecurityHeader::decode);
    }

}
