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
import org.eclipse.milo.opcua.stack.core.types.enumerated.UserTokenType;
import org.eclipse.milo.opcua.stack.core.types.structured.AnonymousIdentityToken;
import org.eclipse.milo.opcua.stack.core.types.structured.EndpointDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.UserTokenPolicy;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertThrows;
import static org.testng.Assert.assertTrue;

public class AnonymousProviderTest {

    @Test
    public void testGetIdentityToken() throws Exception {
        EndpointDescription endpoint = new EndpointDescription(
            null, null, null, null, null,
            new UserTokenPolicy[]{
                new UserTokenPolicy(
                    "anonymous",
                    UserTokenType.Anonymous,
                    null, null, null)
            },
            null, null
        );

        AnonymousProvider p = new AnonymousProvider();

        SignedIdentityToken signedIdentityToken =
            p.getIdentityToken(endpoint, ByteString.NULL_VALUE);

        assertEquals(signedIdentityToken.getToken().getPolicyId(), "anonymous");
        assertTrue(signedIdentityToken.getToken() instanceof AnonymousIdentityToken);
    }

    @Test
    public void testGetIdentityToken_EmptyPolicyId() throws Exception {
        EndpointDescription endpoint = new EndpointDescription(
            null, null, null, null, null,
            new UserTokenPolicy[]{
                new UserTokenPolicy(
                    "",
                    UserTokenType.Anonymous,
                    null, null, null)
            },
            null, null
        );

        AnonymousProvider p = new AnonymousProvider();

        SignedIdentityToken signedIdentityToken =
            p.getIdentityToken(endpoint, ByteString.NULL_VALUE);

        assertEquals(signedIdentityToken.getToken().getPolicyId(), "");
        assertTrue(signedIdentityToken.getToken() instanceof AnonymousIdentityToken);
    }

    @Test
    public void testGetIdentityToken_NullPolicyId() throws Exception {
        EndpointDescription endpoint = new EndpointDescription(
            null, null, null, null, null,
            new UserTokenPolicy[]{
                new UserTokenPolicy(
                    null,
                    UserTokenType.Anonymous,
                    null, null, null)
            },
            null, null
        );

        AnonymousProvider p = new AnonymousProvider();

        SignedIdentityToken signedIdentityToken =
            p.getIdentityToken(endpoint, ByteString.NULL_VALUE);

        assertNull(signedIdentityToken.getToken().getPolicyId());
        assertTrue(signedIdentityToken.getToken() instanceof AnonymousIdentityToken);
    }

    @Test
    public void testGetIdentityToken_NoMatch_Throws() {
        EndpointDescription endpoint = new EndpointDescription(
            null, null, null, null, null,
            new UserTokenPolicy[]{
                new UserTokenPolicy(
                    "username",
                    UserTokenType.UserName,
                    null, null, null)
            },
            null, null
        );

        AnonymousProvider p = new AnonymousProvider();

        assertThrows(() -> p.getIdentityToken(endpoint, ByteString.NULL_VALUE));
    }

}
