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
import org.eclipse.milo.opcua.stack.core.types.structured.SignatureData;
import org.eclipse.milo.opcua.stack.core.types.structured.UserIdentityToken;
import org.eclipse.milo.opcua.stack.core.types.structured.UserTokenPolicy;
import org.jooq.lambda.tuple.Tuple2;
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

        Tuple2<UserIdentityToken, SignatureData> t2 =
            p.getIdentityToken(endpoint, ByteString.NULL_VALUE);

        assertEquals(t2.v1().getPolicyId(), "anonymous");
        assertTrue(t2.v1() instanceof AnonymousIdentityToken);
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

        Tuple2<UserIdentityToken, SignatureData> t2 =
            p.getIdentityToken(endpoint, ByteString.NULL_VALUE);

        assertEquals(t2.v1().getPolicyId(), "");
        assertTrue(t2.v1() instanceof AnonymousIdentityToken);
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

        Tuple2<UserIdentityToken, SignatureData> t2 =
            p.getIdentityToken(endpoint, ByteString.NULL_VALUE);

        assertNull(t2.v1().getPolicyId());
        assertTrue(t2.v1() instanceof AnonymousIdentityToken);
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
