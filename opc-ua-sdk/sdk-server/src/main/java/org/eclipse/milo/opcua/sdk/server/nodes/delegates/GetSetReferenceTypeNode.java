/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.nodes.delegates;

import org.eclipse.milo.opcua.sdk.server.nodes.AttributeContext;
import org.eclipse.milo.opcua.sdk.server.nodes.UaReferenceTypeNode;
import org.eclipse.milo.opcua.sdk.server.util.AttributeUtil;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;

import static org.eclipse.milo.opcua.sdk.server.util.AttributeUtil.dv;

public interface GetSetReferenceTypeNode extends GetSetBase {

    default DataValue getReferenceTypeAttribute(
        AttributeContext context,
        UaReferenceTypeNode node,
        AttributeId attributeId) throws UaException {

        switch (attributeId) {
            case IsAbstract:
                return dv(getIsAbstract(context, node));

            case Symmetric:
                return dv(getSymmetric(context, node));

            case InverseName:
                return dv(getInverseName(context, node));

            default:
                return getBaseAttribute(context, node, attributeId);
        }
    }

    default void setReferenceTypeAttribute(
        AttributeContext context,
        UaReferenceTypeNode node,
        AttributeId attributeId,
        DataValue value) throws UaException {

        switch (attributeId) {
            case IsAbstract:
                setIsAbstract(context, node, AttributeUtil.extract(value));
                break;
            case Symmetric:
                setSymmetric(context, node, AttributeUtil.extract(value));
                break;
            case InverseName:
                setInverseName(context, node, AttributeUtil.extract(value));
                break;

            default:
                setBaseAttribute(context, node, attributeId, value);
        }
    }

    default Boolean getIsAbstract(AttributeContext context, UaReferenceTypeNode node) throws UaException {
        return (Boolean) node.getFilterChain().getAttribute(
            context.getSession().orElse(null),
            node,
            AttributeId.IsAbstract
        );
    }

    default Boolean getSymmetric(AttributeContext context, UaReferenceTypeNode node) throws UaException {
        return (Boolean) node.getFilterChain().getAttribute(
            context.getSession().orElse(null),
            node,
            AttributeId.Symmetric
        );
    }

    default LocalizedText getInverseName(AttributeContext context, UaReferenceTypeNode node) throws UaException {
        return (LocalizedText) node.getFilterChain().getAttribute(
            context.getSession().orElse(null),
            node,
            AttributeId.InverseName
        );
    }

    default void setIsAbstract(
        AttributeContext context,
        UaReferenceTypeNode node,
        Boolean isAbstract
    ) throws UaException {

        node.getFilterChain().setAttribute(
            context.getSession().orElse(null),
            node,
            AttributeId.IsAbstract,
            isAbstract
        );
    }

    default void setSymmetric(
        AttributeContext context,
        UaReferenceTypeNode node,
        Boolean symmetric
    ) throws UaException {

        node.getFilterChain().setAttribute(
            context.getSession().orElse(null),
            node,
            AttributeId.Symmetric,
            symmetric
        );
    }

    default void setInverseName(
        AttributeContext context,
        UaReferenceTypeNode node,
        LocalizedText inverseName
    ) throws UaException {

        node.getFilterChain().setAttribute(
            context.getSession().orElse(null),
            node,
            AttributeId.InverseName,
            inverseName
        );
    }

}
