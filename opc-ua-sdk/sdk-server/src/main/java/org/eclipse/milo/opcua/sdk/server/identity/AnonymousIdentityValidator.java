/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.identity;

import org.eclipse.milo.opcua.sdk.server.Session;
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
        Session session,
        AnonymousIdentityToken token,
        UserTokenPolicy tokenPolicy,
        SignatureData tokenSignature) {

        return String.format("anonymous_%s_%s",
            session.getSessionName(), session.getSessionId().toParseableString());
    }

}
