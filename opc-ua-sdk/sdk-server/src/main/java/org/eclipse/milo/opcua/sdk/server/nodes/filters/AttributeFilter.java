/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.nodes.filters;

import org.eclipse.milo.opcua.sdk.server.nodes.filters.AttributeFilterContext.GetAttributeContext;
import org.eclipse.milo.opcua.sdk.server.nodes.filters.AttributeFilterContext.SetAttributeContext;
import org.eclipse.milo.opcua.sdk.server.util.Pending;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.util.Unit;

public interface AttributeFilter {

    default void getAttributeAsync(
        GetAttributeContext ctx,
        AttributeId attributeId,
        Pending<Unit, Object> pending
    ) {

        ctx.getAttributeAsync(attributeId, pending);
    }

    default void setAttributeAsync(
        SetAttributeContext ctx,
        AttributeId attributeId,
        Pending<Object, Unit> pending
    ) {

        ctx.setAttributeAsync(attributeId, pending);
    }

    /**
     * Get the value for the attribute identified by {@code attributeId} or delegate to the next filter in the chain.
     *
     * @param ctx         the {@link AttributeFilterContext}.
     * @param attributeId the {@link AttributeId} of the attribute to get the value of.
     * @return the value for the attribute identified by {@code attributeId}.
     * @see GetAttributeContext#getAttribute(AttributeId)
     */
    default Object getAttribute(GetAttributeContext ctx, AttributeId attributeId) {
        return ctx.getAttribute(attributeId);
    }

    /**
     * Set the value for the attribute identified by {@code attributeId} or delegate to the next filter in the chain.
     *
     * @param ctx         the {@link AttributeFilterContext}.
     * @param attributeId the {@link AttributeId} of the attribute to set the value of.
     * @param value       the value to set.
     * @see SetAttributeContext#setAttribute(AttributeId, Object)
     */
    default void setAttribute(SetAttributeContext ctx, AttributeId attributeId, Object value) {
        ctx.setAttribute(attributeId, value);
    }

}
