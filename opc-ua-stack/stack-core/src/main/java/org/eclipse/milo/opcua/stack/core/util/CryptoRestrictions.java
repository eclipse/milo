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

package org.eclipse.milo.opcua.stack.core.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.security.Permission;
import java.security.PermissionCollection;
import java.util.Map;

import org.eclipse.milo.opcua.stack.core.Stack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CryptoRestrictions {

    private static volatile boolean removed = false;

    static {
        removed = removeCryptographyRestrictions();
    }

    /**
     * Attempt to remove cryptography restrictions.
     *
     * @return {@code true} if this is an Oracle JRE/JDK and restrictions were removed.
     */
    public static synchronized boolean remove() {
        return removed;
    }

    /**
     * Do the following, but with reflection to bypass access checks:
     * <p>
     * JceSecurity.isRestricted = false;
     * JceSecurity.defaultPolicy.perms.clear();
     * JceSecurity.defaultPolicy.add(CryptoAllPermission.INSTANCE);
     */
    private static boolean removeCryptographyRestrictions() {
        Logger logger = LoggerFactory.getLogger(Stack.class);

        if (isRestrictedCryptography()) {
            try {
                final Class<?> jceSecurity = Class.forName("javax.crypto.JceSecurity");
                final Class<?> cryptoPermissions = Class.forName("javax.crypto.CryptoPermissions");
                final Class<?> cryptoAllPermission = Class.forName("javax.crypto.CryptoAllPermission");

                // Private? No big deal...
                final Field isRestrictedField = jceSecurity.getDeclaredField("isRestricted");
                isRestrictedField.setAccessible(true);

                // Final? Let's remove that...
                final Field modifiersField = Field.class.getDeclaredField("modifiers");
                modifiersField.setAccessible(true);
                modifiersField.setInt(isRestrictedField, isRestrictedField.getModifiers() & ~Modifier.FINAL);

                // isRestricted = false
                isRestrictedField.set(null, false);

                final Field defaultPolicyField = jceSecurity.getDeclaredField("defaultPolicy");
                defaultPolicyField.setAccessible(true);
                final PermissionCollection defaultPolicy = (PermissionCollection) defaultPolicyField.get(null);

                final Field perms = cryptoPermissions.getDeclaredField("perms");
                perms.setAccessible(true);
                ((Map<?, ?>) perms.get(defaultPolicy)).clear();

                final Field instance = cryptoAllPermission.getDeclaredField("INSTANCE");
                instance.setAccessible(true);
                defaultPolicy.add((Permission) instance.get(null));

                logger.info("Successfully removed cryptography restrictions.");
                return true;
            } catch (final Exception e) {
                logger.warn("Failed to remove cryptography restrictions.", e);
                return false;
            }
        }

        return true;
    }

    private static boolean isRestrictedCryptography() {
        // This simply matches the Oracle JRE, but not OpenJDK.
        return "Java(TM) SE Runtime Environment".equals(System.getProperty("java.runtime.name"));
    }

}
