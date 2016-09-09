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
