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

package org.eclipse.milo.opcua.stack.core.serialization;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import com.google.common.reflect.ClassPath;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotEquals;

public class DelegateRegistryInitializerTest {

    @Test
    public void testInitialize() throws Exception {
        DelegateRegistryInitializer.initialize();

        ClassLoader classLoader = getClass().getClassLoader();
        ClassPath classPath = ClassPath.from(classLoader);

        ImmutableSet<ClassPath.ClassInfo> structures =
            classPath.getTopLevelClasses("org.eclipse.milo.opcua.stack.core.types.structured");

        ImmutableSet<ClassPath.ClassInfo> enumerations =
            classPath.getTopLevelClasses("org.eclipse.milo.opcua.stack.core.types.enumerated");

        assertNotEquals(structures.size(), 0);
        assertNotEquals(enumerations.size(), 0);

        for (ClassPath.ClassInfo classInfo : Sets.union(structures, enumerations)) {
            Class<?> clazz = classInfo.load();

            DelegateRegistry.getInstance().getEncoder(clazz);
            DelegateRegistry.getInstance().getDecoder(clazz);
        }
    }

}
