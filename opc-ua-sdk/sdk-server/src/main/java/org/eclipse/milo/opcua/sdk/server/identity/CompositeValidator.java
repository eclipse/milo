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

import java.util.Iterator;
import java.util.List;

import org.eclipse.milo.opcua.sdk.server.Session;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.structured.SignatureData;
import org.eclipse.milo.opcua.stack.core.types.structured.UserIdentityToken;
import org.eclipse.milo.opcua.stack.core.types.structured.UserTokenPolicy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A composite {@link IdentityValidator} that tries its component {@link IdentityValidator}s in the order provided.
 */
public class CompositeValidator<T> implements IdentityValidator<T> {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final List<IdentityValidator<T>> validators;

    public CompositeValidator(IdentityValidator<T>... validators) {
        this.validators = List.of(validators);
    }

    public CompositeValidator(List<IdentityValidator<T>> validators) {
        this.validators = List.copyOf(validators);
    }

    @Override
    public T validateIdentityToken(
        Session session,
        UserIdentityToken token,
        UserTokenPolicy tokenPolicy,
        SignatureData tokenSignature) throws UaException {

        Iterator<IdentityValidator<T>> iterator = validators.iterator();

        while (iterator.hasNext()) {
            IdentityValidator<T> validator = iterator.next();

            try {
                return validator.validateIdentityToken(session, token, tokenPolicy, tokenSignature);
            } catch (Exception e) {
                if (!iterator.hasNext()) {
                    throw e;
                }

                logger.debug("IdentityValidator={} failed, trying next...", validator.toString());
            }
        }

        throw new UaException(StatusCodes.Bad_IdentityTokenInvalid);
    }

}
