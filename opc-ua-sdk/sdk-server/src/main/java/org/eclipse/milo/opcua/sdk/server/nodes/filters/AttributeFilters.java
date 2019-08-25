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

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import java.util.function.Function;

import org.eclipse.milo.opcua.sdk.server.nodes.filters.AttributeFilterContext.GetAttributeContext;
import org.eclipse.milo.opcua.sdk.server.nodes.filters.AttributeFilterContext.SetAttributeContext;
import org.eclipse.milo.opcua.sdk.server.util.Pending;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.util.Unit;

public final class AttributeFilters {

    private AttributeFilters() {}

    public static AttributeFilter getValue(Function<GetAttributeContext, DataValue> get) {
        return new AttributeFilter() {
            @Override
            public void getAttributeAsync(
                GetAttributeContext ctx,
                AttributeId attributeId,
                Pending<Unit, Object> pending
            ) {

                if (attributeId == AttributeId.Value) {
                    pending.getOutputFuture().complete(get.apply(ctx));
                } else {
                    ctx.getAttributeAsync(attributeId, pending);
                }
            }
        };
    }

    public static AttributeFilter getValueAsync(Function<GetAttributeContext, CompletableFuture<DataValue>> get) {
        return new AttributeFilter() {
            @Override
            public void getAttributeAsync(
                GetAttributeContext ctx,
                AttributeId attributeId,
                Pending<Unit, Object> pending
            ) {

                if (attributeId == AttributeId.Value) {
                    get.apply(ctx).thenAccept(pending.getOutputFuture()::complete);
                } else {
                    ctx.getAttributeAsync(attributeId, pending);
                }
            }
        };
    }

    public static AttributeFilter setValue(BiConsumer<SetAttributeContext, DataValue> set) {
        return new AttributeFilter() {
            @Override
            public void setAttribute(SetAttributeContext ctx, AttributeId attributeId, Object value) {
                if (attributeId == AttributeId.Value) {
                    set.accept(ctx, (DataValue) value);
                } else {
                    ctx.setAttribute(attributeId, value);
                }
            }
        };
    }

}
