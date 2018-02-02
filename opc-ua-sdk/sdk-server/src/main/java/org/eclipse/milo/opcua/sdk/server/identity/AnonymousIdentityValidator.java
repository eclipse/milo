/*
 * Copyright (c) 2016 Kevin Herron and others
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

package org.eclipse.milo.opcua.sdk.server.identity;

import org.eclipse.milo.opcua.sdk.server.Session;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.channel.ServerSecureChannel;
import org.eclipse.milo.opcua.stack.core.types.structured.AnonymousIdentityToken;
import org.eclipse.milo.opcua.stack.core.types.structured.SignatureData;
import org.eclipse.milo.opcua.stack.core.types.structured.UserTokenPolicy;

public final class AnonymousIdentityValidator extends AbstractIdentityValidator {

    /**
     * A static instance implementing AnonymousIdentityValidator
     */
    public static final IdentityValidator INSTANCE = new AnonymousIdentityValidator();
    
    /**
     * @deprecated Use {@link #INSTANCE} instead 
     */
    @Deprecated
    public AnonymousIdentityValidator() {
    }
    
    @Override
    public Object validateAnonymousToken(
        ServerSecureChannel channel,
        Session session,
        AnonymousIdentityToken token,
        UserTokenPolicy tokenPolicy,
        SignatureData tokenSignature) throws UaException {

        return String.format("anonymous_%s_%s",
            session.getSessionName(), session.getSessionId().toParseableString());
    }

}
