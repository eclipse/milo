/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.nodes;

import org.eclipse.milo.opcua.sdk.server.nodes.filters.AttributeFilter;
import org.eclipse.milo.opcua.sdk.server.nodes.filters.AttributeFilterContext.GetAttributeContext;
import org.eclipse.milo.opcua.sdk.server.nodes.filters.AttributeFilterContext.SetAttributeContext;
import org.eclipse.milo.opcua.sdk.server.util.Pending;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.util.Unit;

/**
 * An {@link AttributeFilter} that gets or sets the actual attribute value from the backing field of a {@link UaNode}.
 * <p>
 * {@link DefaultAttributeFilter} does not invoke further attribute filters in the chain.
 */
public final class DefaultAttributeFilter implements AttributeFilter {

    /**
     * A shared instance of {@link DefaultAttributeFilter}.
     */
    public static final DefaultAttributeFilter INSTANCE = new DefaultAttributeFilter();

    @Override
    public void getAttributeAsync(
        GetAttributeContext ctx,
        AttributeId attributeId,
        Pending<Unit, Object> pending
    ) {

        Object value = ctx.getNode().getAttribute(attributeId);

        pending.getOutputFuture().complete(value);
    }

    @Override
    public void setAttributeAsync(
        SetAttributeContext ctx,
        AttributeId attributeId,
        Pending<Object, Unit> pending
    ) {

        ctx.getNode().setAttribute(attributeId, pending.getInput());

        pending.getOutputFuture().complete(Unit.VALUE);
    }

    @Override
    public Object getAttribute(GetAttributeContext ctx, AttributeId attributeId) {
        return ctx.getNode().getAttribute(attributeId);
    }

    @Override
    public void setAttribute(SetAttributeContext ctx, AttributeId attributeId, Object value) {
        ctx.getNode().setAttribute(attributeId, value);
    }

}
