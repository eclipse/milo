/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.events.conversions;

import javax.annotation.Nonnull;

import org.eclipse.milo.opcua.stack.core.BuiltinDataType;
import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

final class ExpandedNodeIdConversions {

    private ExpandedNodeIdConversions() {}

    static NodeId expandedNodeIdToNodeId(ExpandedNodeId e) throws ConversionFailedException {
        // TODO need a real NamespaceTable here
        return e.local(new NamespaceTable())
            .orElseThrow(() -> new ConversionFailedException(BuiltinDataType.ExpandedNodeId, BuiltinDataType.NodeId));
    }

    static String expandedNodeIdToString(@Nonnull ExpandedNodeId e) {
        return e.toParseableString();
    }

    static Object convert(
        Object value,
        BuiltinDataType targetType,
        boolean implicit
    ) throws ConversionNotDefinedException, ConversionFailedException {

        if (value instanceof ExpandedNodeId) {
            ExpandedNodeId eni = (ExpandedNodeId) value;

            return implicit ?
                implicitConversion(eni, targetType) :
                explicitConversion(eni, targetType);
        } else {
            throw new IllegalArgumentException("value: " + value);
        }
    }

    static Object explicitConversion(
        ExpandedNodeId eni,
        BuiltinDataType targetType
    ) throws ConversionFailedException, ConversionNotDefinedException {

        //@formatter:off
        switch (targetType) {
            case NodeId:    return expandedNodeIdToNodeId(eni);
            default:        return implicitConversion(eni, targetType);
        }
        //@formatter:on
    }

    static Object implicitConversion(
        ExpandedNodeId eni,
        BuiltinDataType targetType
    ) throws ConversionNotDefinedException {

        //@formatter:off
        switch (targetType) {
            case String:    return expandedNodeIdToString(eni);
            default:        throw new ConversionNotDefinedException(BuiltinDataType.ExpandedNodeId, targetType);
        }
        //@formatter:on
    }

}
