/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
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

import static org.eclipse.milo.opcua.stack.core.util.ConversionUtil.l;

/**
 * An {@link IdentityProvider} that will choose the first available anonymous {@link UserTokenPolicy}.
 */
public class AnonymousProvider implements IdentityProvider {

    public static final IdentityProvider INSTANCE = new AnonymousProvider(); 

    @Override
    public SignedIdentityToken getIdentityToken(
        EndpointDescription endpoint,
        ByteString serverNonce) throws Exception {

        List<UserTokenPolicy> userIdentityTokens = l(endpoint.getUserIdentityTokens());

        return userIdentityTokens.stream()
            .filter(t -> t.getTokenType() == UserTokenType.Anonymous)
            .findFirst()
            .map(policy -> {
                UserIdentityToken token = new AnonymousIdentityToken(policy.getPolicyId());

                return new SignedIdentityToken(token, new SignatureData());
            })
            .orElseThrow(() -> new Exception("no anonymous token policy found"));
    }

    @Override
    public String toString() {
        return "AnonymousProvider{}";
    }

}
