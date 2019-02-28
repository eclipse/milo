/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.util;

import java.util.Set;
import javax.annotation.Nullable;

import org.eclipse.milo.opcua.sdk.core.AccessLevel;
import org.eclipse.milo.opcua.sdk.core.WriteMask;
import org.eclipse.milo.opcua.sdk.server.nodes.AttributeContext;
import org.eclipse.milo.opcua.sdk.server.nodes.UaServerNode;
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

    /**
     * DataValue for a non-value attribute; no source timestamp included.
     */
    public static DataValue dv(Object o) {
        return new DataValue(new Variant(o), StatusCode.GOOD, null, DateTime.now());
    }

    @Nullable
    @SuppressWarnings("unchecked")
    public static <T> T extract(DataValue value) throws UaException {
        Variant variant = value.getValue();
        if (variant == null) return null;

        Object o = variant.getValue();
        if (o == null) return null;

        try {
            return (T) o;
        } catch (ClassCastException e) {
            throw new UaException(
                StatusCodes.Bad_TypeMismatch,
                "attribute value was not of the expected type: " + o
            );
        }
    }

    public static Set<AccessLevel> getAccessLevels(
        UaServerNode node,
        AttributeContext context) throws UaException {

        UByte accessLevel = extract(
            node.getAttribute(
                context,
                AttributeId.AccessLevel)
        );

        if (accessLevel != null) {
            return AccessLevel.fromMask(accessLevel);
        } else {
            return AccessLevel.NONE;
        }
    }

    public static Set<AccessLevel> getUserAccessLevels(
        UaServerNode node,
        AttributeContext context) throws UaException {

        UByte userAccessLevel = extract(
            node.getAttribute(
                context,
                AttributeId.UserAccessLevel)
        );

        if (userAccessLevel != null) {
            return AccessLevel.fromMask(userAccessLevel);
        } else {
            return AccessLevel.NONE;
        }
    }

    public static Set<WriteMask> getWriteMasks(
        UaServerNode node,
        AttributeContext internalContext) throws UaException {

        UInteger writeMask = extract(
            node.getAttribute(
                internalContext,
                AttributeId.WriteMask
            )
        );

        if (writeMask != null) {
            return WriteMask.fromMask(writeMask);
        } else {
            return WriteMask.NONE;
        }
    }

    public static Set<WriteMask> getUserWriteMasks(
        UaServerNode node,
        AttributeContext internalContext) throws UaException {

        UInteger userWriteMask = extract(
            node.getAttribute(
                internalContext,
                AttributeId.UserWriteMask
            )
        );

        if (userWriteMask != null) {
            return WriteMask.fromMask(userWriteMask);
        } else {
            return WriteMask.NONE;
        }
    }

}
