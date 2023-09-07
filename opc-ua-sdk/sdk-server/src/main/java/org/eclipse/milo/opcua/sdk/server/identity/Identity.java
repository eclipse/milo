/*
 * Copyright (c) 2023 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.identity;

import java.security.cert.X509Certificate;

import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.enumerated.UserTokenType;
import org.jetbrains.annotations.Nullable;

public interface Identity {

    /**
     * @return the {@link UserTokenType} for this identity.
     */
    UserTokenType getUserTokenType();

    /**
     * @return the user data object associated with this identity.
     */
    @Nullable Object getUserData();

    /**
     * Associate an arbitrary user data object on this identity.
     *
     * @param userData the user data object.
     */
    void setUserData(Object userData);

    interface AnonymousIdentity extends Identity {

    }

    interface UsernameIdentity extends Identity {


        /**
         * @return the username for this identity.
         */
        String getUsername();

    }

    interface X509UserIdentity extends Identity {


        /**
         * @return the {@link X509Certificate} for this identity.
         */
        X509Certificate getCertificate();

    }

    interface IssuedUserIdentity extends Identity {

        /**
         * @return a {@link ByteString} containing opaque issued token data.
         */
        ByteString getTokenData();

    }

}
