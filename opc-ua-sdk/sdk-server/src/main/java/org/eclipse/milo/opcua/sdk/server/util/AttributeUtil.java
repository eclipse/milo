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

package org.eclipse.milo.opcua.sdk.server.util;

import java.util.Set;
import java.util.function.Supplier;

import org.eclipse.milo.opcua.sdk.core.AccessLevel;
import org.eclipse.milo.opcua.sdk.core.WriteMask;
import org.eclipse.milo.opcua.sdk.server.nodes.AttributeContext;
import org.eclipse.milo.opcua.sdk.server.nodes.ServerNode;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public class AttributeUtil {

    public static final Supplier<UaException> ATTRIBUTE_ID_INVALID_EXCEPTION =
        () -> new UaException(StatusCodes.Bad_AttributeIdInvalid);

    /**
     * DataValue for a non-value attribute; no source timestamp included.
     */
    public static DataValue dv(Object o) {
        return new DataValue(new Variant(o), StatusCode.GOOD, null, DateTime.now());
    }

    @SuppressWarnings("unchecked")
    public static <T> T extract(DataValue value) throws UaException {
        Variant variant = value.getValue();
        if (variant == null) return null;

        Object o = variant.getValue();
        if (o == null) return null;

        try {
            return (T) o;
        } catch (ClassCastException e) {
            throw new UaException(StatusCodes.Bad_TypeMismatch);
        }
    }

    public static Set<AccessLevel> getAccessLevels(
        ServerNode node,
        AttributeContext context) throws UaException {

        UByte accessLevel = extract(
            node.getAttribute(
                context,
                AttributeId.AccessLevel)
        );

        return AccessLevel.fromMask(accessLevel);
    }

    public static Set<AccessLevel> getUserAccessLevels(
        ServerNode node,
        AttributeContext context) throws UaException {

        UByte userAccessLevel = extract(
            node.getAttribute(
                context,
                AttributeId.UserAccessLevel)
        );

        return AccessLevel.fromMask(userAccessLevel);
    }

    public static Set<WriteMask> getWriteMasks(
        ServerNode node,
        AttributeContext internalContext) throws UaException {

        UInteger writeMask = extract(
            node.getAttribute(
                internalContext,
                AttributeId.WriteMask
            )
        );

        return WriteMask.fromMask(writeMask);
    }

    public static Set<WriteMask> getUserWriteMasks(
        ServerNode node,
        AttributeContext internalContext) throws UaException {

        UInteger userWriteMask = extract(
            node.getAttribute(
                internalContext,
                AttributeId.UserWriteMask
            )
        );

        return WriteMask.fromMask(userWriteMask);
    }

}
