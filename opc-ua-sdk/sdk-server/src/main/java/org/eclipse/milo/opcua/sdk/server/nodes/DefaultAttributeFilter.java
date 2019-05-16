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
import org.eclipse.milo.opcua.sdk.server.nodes.filters.AttributeFilterContext;
import org.eclipse.milo.opcua.stack.core.AttributeId;

/**
 * An {@link AttributeFilter} that gets or sets the actual attribute value from the backing field of a {@link UaNode}.
 * <p>
 * {@link DefaultAttributeFilter} does not invoke further attribute filters in the chain.
 */
public final class DefaultAttributeFilter implements AttributeFilter {

    @Override
    public Object getAttribute(AttributeFilterContext ctx, AttributeId attributeId) {
        return ctx.getNode().getAttribute(attributeId);
    }

    @Override
    public void setAttribute(AttributeFilterContext ctx, AttributeId attributeId, Object value) {
        ctx.getNode().setAttribute(attributeId, value);
    }

}
