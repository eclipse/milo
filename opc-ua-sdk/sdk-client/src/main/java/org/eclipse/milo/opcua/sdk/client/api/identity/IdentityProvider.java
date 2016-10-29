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

package org.eclipse.milo.opcua.sdk.client.api.identity;

import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.structured.EndpointDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.SignatureData;
import org.eclipse.milo.opcua.stack.core.types.structured.UserIdentityToken;
import org.jooq.lambda.tuple.Tuple2;

public interface IdentityProvider {

    /**
     * Return the {@link UserIdentityToken} and {@link SignatureData} (if applicable for the token) to use when
     * activating a session.
     *
     * @param endpoint the {@link EndpointDescription} being connected to.
     * @return a {@link Tuple2} containing the {@link UserIdentityToken} and {@link SignatureData}.
     */
    Tuple2<UserIdentityToken, SignatureData> getIdentityToken(EndpointDescription endpoint,
                                                              ByteString serverNonce) throws Exception;

}
