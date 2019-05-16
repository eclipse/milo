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

import org.eclipse.milo.opcua.sdk.server.nodes.AttributeFilter;
import org.eclipse.milo.opcua.sdk.server.nodes.AttributeFilterContext;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;

public abstract class ValueFilter implements AttributeFilter {

    @Override
    public Object getAttribute(AttributeFilterContext ctx, AttributeId attributeId) {
        if (attributeId == AttributeId.Value) {
            return onGetValue(ctx);
        } else {
            return ctx.getAttribute(attributeId);
        }
    }

    @Override
    public void setAttribute(AttributeFilterContext ctx, AttributeId attributeId, Object value) {
        if (attributeId == AttributeId.Value) {
            onSetValue(ctx, value);
        } else {
            ctx.setAttribute(attributeId, value);
        }
    }

    protected abstract DataValue onGetValue(AttributeFilterContext ctx);

    protected abstract void onSetValue(AttributeFilterContext ctx, Object value);

}
