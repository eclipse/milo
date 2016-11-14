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

package org.eclipse.milo.examples.server;

import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

import org.eclipse.milo.opcua.sdk.core.AccessLevel;
import org.eclipse.milo.opcua.sdk.server.Session;
import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.server.nodes.AttributeContext;
import org.eclipse.milo.opcua.sdk.server.nodes.delegates.VariableNodeDelegate;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ubyte;

public class RestrictedAccessDelegate extends VariableNodeDelegate {

    private static final Set<AccessLevel> INTERNAL_ACCESS = AccessLevel.READ_WRITE;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final Function<Object, Set<AccessLevel>> accessLevelsFn;

    public RestrictedAccessDelegate(Function<Object, Set<AccessLevel>> accessLevelsFn) {
        this.accessLevelsFn = accessLevelsFn;
    }

    @Override
    protected UByte getUserAccessLevel(AttributeContext context, VariableNode node) throws UaException {
        Optional<Object> identity = context.getSession().map(Session::getIdentityObject);

        Set<AccessLevel> accessLevels = identity.map(accessLevelsFn).orElse(INTERNAL_ACCESS);

        return ubyte(AccessLevel.getMask(accessLevels));
    }

    @Override
    protected DataValue getValue(AttributeContext context, VariableNode node) throws UaException {
        Optional<Object> identity = context.getSession().map(Session::getIdentityObject);

        Set<AccessLevel> accessLevels = identity.map(accessLevelsFn).orElse(INTERNAL_ACCESS);

        if (accessLevels.contains(AccessLevel.CurrentRead)) {
            logger.info(
                "Allowing user '{}' access reading Value of {}",
                identity, node.getNodeId());

            return node.getValue();
        } else {
            logger.info(
                "Denying user '{}' access reading Value of {}",
                identity, node.getNodeId());

            throw new UaException(StatusCodes.Bad_UserAccessDenied);
        }
    }

    @Override
    protected void setValue(AttributeContext context, VariableNode node, DataValue value) throws UaException {
        Optional<Object> identity = context.getSession().map(Session::getIdentityObject);

        Set<AccessLevel> accessLevels = identity.map(accessLevelsFn).orElse(INTERNAL_ACCESS);

        if (accessLevels.contains(AccessLevel.CurrentWrite)) {
            logger.info(
                "Allowing user '{}' access writing to Value of {}",
                identity, node.getNodeId());

            node.setValue(value);
        } else {
            logger.info(
                "Denying user '{}' access writing to Value of {}",
                identity, node.getNodeId());

            throw new UaException(StatusCodes.Bad_UserAccessDenied);
        }
    }

}
