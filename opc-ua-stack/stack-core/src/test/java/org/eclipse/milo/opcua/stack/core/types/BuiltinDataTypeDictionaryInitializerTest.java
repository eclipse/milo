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

import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.ClassPath;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.OpcUaBinaryDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.OpcUaXmlDataTypeCodec;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertNotNull;

public class BuiltinDataTypeDictionaryInitializerTest {

    @Test
    public void testInitialize() throws Exception {
        BuiltinDataTypeDictionaryInitializer.initialize();

        ClassLoader classLoader = getClass().getClassLoader();
        ClassPath classPath = ClassPath.from(classLoader);

        ImmutableSet<ClassPath.ClassInfo> structures =
            classPath.getTopLevelClasses("org.eclipse.milo.opcua.stack.core.types.structured");

        assertNotEquals(structures.size(), 0);

        for (ClassPath.ClassInfo classInfo : structures) {
            Class<?> clazz = classInfo.load();

            OpcUaBinaryDataTypeCodec<?> binaryCodec = OpcUaDataTypeManager.getInstance().getBinaryCodec(
                OpcUaDataTypeManager.BINARY_NAMESPACE_URI,
                clazz.getSimpleName()
            );

            assertNotNull(binaryCodec, "no binary codec found for " + clazz.getSimpleName());

            OpcUaXmlDataTypeCodec<?> xmlCodec = OpcUaDataTypeManager.getInstance().getXmlCodec(
                OpcUaDataTypeManager.XML_NAMESPACE_URI,
                xpathify(clazz.getSimpleName())
            );

            assertNotNull(xmlCodec, "no xml codec found for " + clazz.getSimpleName());
        }
    }

    private static String xpathify(String typeName) {
        return String.format("//xs:element[@name='%s']", typeName);
    }

}
