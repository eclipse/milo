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
import java.util.function.BiFunction;
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
        return new BlockingAttributeFilter() {
            @Override
            public boolean filterGet(AttributeId attributeId) {
                return attributeId == AttributeId.Value;
            }

            @Override
            public Object getAttributeBlocking(GetAttributeContext ctx, AttributeId attributeId) {
                return get.apply(ctx);
            }
        };
    }

    public static AttributeFilter setValue(BiConsumer<SetAttributeContext, DataValue> set) {
        return new BlockingAttributeFilter() {
            @Override
            public boolean filterSet(AttributeId attributeId) {
                return attributeId == AttributeId.Value;
            }

            @Override
            public void setAttributeBlocking(SetAttributeContext ctx, AttributeId attributeId, Object value) {
                set.accept(ctx, (DataValue) value);
            }
        };
    }

    public static AttributeFilter getValueAsync(Function<GetAttributeContext, CompletableFuture<DataValue>> get) {
        return new AsyncAttributeFilter() {
            @Override
            public boolean filterGet(AttributeId attributeId) {
                return attributeId == AttributeId.Value;
            }

            @Override
            public void getAttributeAsync(GetAttributeContext ctx, AttributeId attributeId, Pending<Unit, Object> pending) {
                get.apply(ctx).thenAccept(pending.getOutputFuture()::complete);
            }
        };
    }

    public static AttributeFilter setValueAsync(
        BiFunction<SetAttributeContext, DataValue, CompletableFuture<Unit>> set
    ) {

        return new AsyncAttributeFilter() {
            @Override
            public boolean filterSet(AttributeId attributeId) {
                return attributeId == AttributeId.Value;
            }

            @Override
            public void setAttributeAsync(
                SetAttributeContext ctx,
                AttributeId attributeId,
                Pending<Object, Unit> pending
            ) {

                CompletableFuture<Unit> future =
                    set.apply(ctx, (DataValue) pending.getInput());

                future.thenAccept(pending.getOutputFuture()::complete);
            }
        };
    }

}
