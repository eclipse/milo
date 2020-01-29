/*
 * Copyright (c) 2020 the Eclipse Milo Authors
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

public abstract class BlockingAttributeFilter implements AttributeFilter {

    @Override
    public final boolean isAsync() {
        return false;
    }

    @Override
    public final void getAttributeAsync(
        GetAttributeContext ctx,
        AttributeId attributeId,
        Pending<Unit, Object> pending
    ) {

        Object value = ctx.getAttribute(attributeId);

        pending.getOutputFuture().complete(value);
    }

    @Override
    public final void setAttributeAsync(
        SetAttributeContext ctx,
        AttributeId attributeId,
        Pending<Object, Unit> pending
    ) {

        ctx.setAttribute(attributeId, pending.getInput());

        pending.getOutputFuture().complete(Unit.VALUE);
    }

    @Override
    public Object getAttributeBlocking(GetAttributeContext ctx, AttributeId attributeId) {
        return ctx.getAttribute(attributeId);
    }

    @Override
    public void setAttributeBlocking(SetAttributeContext ctx, AttributeId attributeId, Object value) {
        ctx.setAttribute(attributeId, value);
    }

}
