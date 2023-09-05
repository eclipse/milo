/*
 * Copyright (c) 2023 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.core.security;

import java.security.PrivateKey;
import java.security.cert.X509Certificate;

import org.jetbrains.annotations.Nullable;

public interface KeyManager {

    /**
     * Check if this {@link KeyManager} is managing an entry for {@code alias}.
     *
     * @param alias the alias to check.
     * @return {@code true} if this {@link KeyManager} is managing an entry for {@code alias}.
     * @throws Exception if an error occurs while checking.
     */
    boolean contains(String alias) throws Exception;

    /**
     * Get the {@link KeyRecord} for {@code alias}.
     *
     * @param alias the alias to get the {@link KeyRecord} for.
     * @return the {@link KeyRecord} for {@code alias}, or {@code null} if no entry exists for
     *     {@code alias}.
     * @throws Exception if an error occurs while getting the {@link KeyRecord}.
     */
    @Nullable KeyRecord get(String alias) throws Exception;

    /**
     * Remove the {@link KeyRecord} for {@code alias}.
     *
     * @param alias the alias to remove the {@link KeyRecord} for.
     * @return the {@link KeyRecord} that was removed, or {@code null} if no entry exists for
     *     {@code alias}.
     * @throws Exception if an error occurs while removing the {@link KeyRecord}.
     */
    @Nullable KeyRecord remove(String alias) throws Exception;

    /**
     * Set the {@link KeyRecord} for {@code alias}.
     *
     * @param alias the alias to set the {@link KeyRecord} for.
     * @param keyRecord the {@link KeyRecord} to set.
     * @throws Exception if an error occurs while setting the {@link KeyRecord}.
     */
    void set(String alias, KeyRecord keyRecord) throws Exception;

    class KeyRecord {
        public final PrivateKey privateKey;
        public final X509Certificate[] certificateChain;

        public KeyRecord(PrivateKey privateKey, X509Certificate[] certificateChain) {
            this.privateKey = privateKey;
            this.certificateChain = certificateChain;
        }
    }

}
