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

package org.eclipse.milo.opcua.stack.core.serialization;

import java.io.StringReader;

import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;

public class OpcUaXmlStreamDecoderTest {
    @Test
    public void testReadVariantValue() throws Exception {
        String xml =
            "<ListOfExtensionObject xmlns=\"http://opcfoundation.org/UA/2008/02/Types.xsd\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">" +
                "<ExtensionObject><TypeId><Identifier>i=297</Identifier>\n" +
                "          </TypeId><Body><Argument><Name>BreakLockStatus</Name><DataType><Identifier>i=6</Identifier>\n" +
                "              </DataType><ValueRank>-1</ValueRank><ArrayDimensions/><Description xmlns:p5=\"http://www.w3.org/2001/XMLSchema-instance\" p5:nil=\"true\"/>\n" +
                "            </Argument>\n" +
                "          </Body>\n" +
                "        </ExtensionObject>\n" +
                "      </ListOfExtensionObject>\n";

        OpcUaXmlStreamDecoder decoder = new OpcUaXmlStreamDecoder(new StringReader(xml));

        assertNotNull(decoder.readVariantValue());
    }

}