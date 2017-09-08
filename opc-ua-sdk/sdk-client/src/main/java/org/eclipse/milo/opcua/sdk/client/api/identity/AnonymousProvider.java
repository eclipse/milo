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

import java.util.List;

import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.enumerated.UserTokenType;
import org.eclipse.milo.opcua.stack.core.types.structured.AnonymousIdentityToken;
import org.eclipse.milo.opcua.stack.core.types.structured.EndpointDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.SignatureData;
import org.eclipse.milo.opcua.stack.core.types.structured.UserIdentityToken;
import org.eclipse.milo.opcua.stack.core.types.structured.UserTokenPolicy;
import org.jooq.lambda.tuple.Tuple2;

import static org.eclipse.milo.opcua.stack.core.util.ConversionUtil.l;

/**
 * An {@link IdentityProvider} that will choose the first available anonymous {@link UserTokenPolicy}.
 */
public class AnonymousProvider implements IdentityProvider {

    public static final IdentityProvider INSTANCE = new AnonymousProvider(); 

    @Override
    public Tuple2<UserIdentityToken, SignatureData> getIdentityToken(
        EndpointDescription endpoint,
        ByteString serverNonce) throws Exception {

        List<UserTokenPolicy> userIdentityTokens = l(endpoint.getUserIdentityTokens());

        return userIdentityTokens.stream()
            .filter(t -> t.getTokenType() == UserTokenType.Anonymous)
            .findFirst()
            .map(policy -> {
                UserIdentityToken token = new AnonymousIdentityToken(policy.getPolicyId());

                return new Tuple2<>(token, new SignatureData());
            })
            .orElseThrow(() -> new Exception("no anonymous token policy found"));
    }

    @Override
    public String toString() {
        return "AnonymousProvider{}";
    }

}
