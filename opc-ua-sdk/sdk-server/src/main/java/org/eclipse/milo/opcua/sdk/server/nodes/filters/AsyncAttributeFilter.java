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

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.eclipse.milo.opcua.sdk.server.nodes.filters.AttributeFilterContext.GetAttributeContext;
import org.eclipse.milo.opcua.sdk.server.nodes.filters.AttributeFilterContext.SetAttributeContext;
import org.eclipse.milo.opcua.sdk.server.util.Pending;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.util.Unit;

public abstract class AsyncAttributeFilter implements AttributeFilter {

    @Override
    public final boolean isAsync() {
        return true;
    }

    @Override
    public void getAttributeAsync(GetAttributeContext ctx, AttributeId attributeId, Pending<Unit, Object> pending) {
        ctx.getAttributeAsync(attributeId, pending);
    }

    @Override
    public void setAttributeAsync(SetAttributeContext ctx, AttributeId attributeId, Pending<Object, Unit> pending) {
        ctx.setAttributeAsync(attributeId, pending);
    }

    @Override
    public final Object getAttributeBlocking(GetAttributeContext ctx, AttributeId attributeId) {
        Pending<Unit, Object> pending = newPendingGet();

        getAttributeAsync(ctx, attributeId, pending);

        try {
            return pending.getOutputFuture().get();
        } catch (InterruptedException | ExecutionException e) {
            return new DataValue(StatusCodes.Bad_InternalError);
        }
    }

    @Override
    public final void setAttributeBlocking(SetAttributeContext ctx, AttributeId attributeId, Object value) {
        Pending<Object, Unit> pending = newPendingSet(value);

        setAttributeAsync(ctx, attributeId, pending);

        try {
            pending.getOutputFuture().get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    protected static Pending<Unit, Object> newPendingGet() {
        return new PendingGet();
    }

    protected static Pending<Object, Unit> newPendingSet(Object value) {
        return new PendingSet();
    }

    private static class PendingGet implements Pending<Unit, Object> {
        @Override
        public Unit getInput() {
            return null; // TODO
        }

        @Override
        public CompletableFuture<Object> getOutputFuture() {
            return null; // TODO
        }
    }

    private static class PendingSet implements Pending<Object, Unit> {
        @Override
        public Object getInput() {
            return null; // TODO
        }

        @Override
        public CompletableFuture<Unit> getOutputFuture() {
            return null; // TODO
        }
    }

}
