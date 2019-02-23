/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.examples.server;

import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

import org.eclipse.milo.opcua.sdk.core.AccessLevel;
import org.eclipse.milo.opcua.sdk.server.Session;
import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.server.nodes.AttributeContext;
import org.eclipse.milo.opcua.sdk.server.nodes.delegates.AttributeDelegate;
import org.eclipse.milo.opcua.sdk.server.nodes.delegates.DelegatingAttributeDelegate;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ubyte;

public class RestrictedAccessDelegate extends DelegatingAttributeDelegate {

    private static final Set<AccessLevel> INTERNAL_ACCESS = AccessLevel.READ_WRITE;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final Function<Object, Set<AccessLevel>> accessLevelsFn;

    public RestrictedAccessDelegate(Function<Object, Set<AccessLevel>> accessLevelsFn) {
        this(null, accessLevelsFn);
    }

    public RestrictedAccessDelegate(AttributeDelegate parent, Function<Object, Set<AccessLevel>> accessLevelsFn) {
        super(parent);

        this.accessLevelsFn = accessLevelsFn;
    }

    @Override
    public UByte getUserAccessLevel(AttributeContext context, VariableNode node) throws UaException {
        Optional<Object> identity = context.getSession().map(Session::getIdentityObject);

        Set<AccessLevel> accessLevels = identity.map(accessLevelsFn).orElse(INTERNAL_ACCESS);

        return ubyte(AccessLevel.getMask(accessLevels));
    }

}
