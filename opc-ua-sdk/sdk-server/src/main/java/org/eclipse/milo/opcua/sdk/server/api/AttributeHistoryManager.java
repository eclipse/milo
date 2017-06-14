/*
 * Copyright (c) 2017 Ari Suutari
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v1.0 which accompany this distribution.
 *
 * The Eclipse Public License is available at
 *   http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 *   http://www.eclipse.org/org/documents/edl-v10.html.
 */

package org.eclipse.milo.opcua.sdk.server.api;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import javax.annotation.Nullable;

import com.google.common.collect.Lists;

import org.eclipse.milo.opcua.sdk.server.DiagnosticsContext;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.Session;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;
import org.eclipse.milo.opcua.stack.core.types.structured.HistoryReadDetails;
import org.eclipse.milo.opcua.stack.core.types.structured.HistoryReadResult;
import org.eclipse.milo.opcua.stack.core.types.structured.HistoryReadValueId;
import org.eclipse.milo.opcua.stack.core.types.structured.HistoryUpdateDetails;
import org.eclipse.milo.opcua.stack.core.types.structured.HistoryUpdateResult;

public interface AttributeHistoryManager {

    /**
     * Read history values from nodes belonging to this {@link AttributeHistoryManager}.
     * <p>
     * Complete the operation with {@link HistoryReadContext#complete(List)}.
     *
     * @param context      the {@link HistoryReadContext}.
     * @param timestamps   requested timestamp values.
     * @param readValueIds the values to read.
     */
    default void historyRead(HistoryReadContext context,
                             HistoryReadDetails readDetails,
                             TimestampsToReturn timestamps,
                             List<HistoryReadValueId> readValueIds) {

        List<HistoryReadResult> results = Lists.newArrayListWithCapacity(readValueIds.size());

        for (HistoryReadValueId readValueId : readValueIds) {

            results.add(new HistoryReadResult(new StatusCode(StatusCodes.Bad_NotSupported), null, null));
        }

        context.complete(results);
    }

    /**
     * Update history values in nodes belonging to this {@link AttributeHistoryManager}.
     * <p>
     * Complete the operation with {@link HistoryUpdateContext#complete(List)}.
     *
     * @param context       the {@link HistoryUpdateContext}.
     * @param updateDetails the values to read.
     */
    default void historyUpdate(HistoryUpdateContext context,
                               List<HistoryUpdateDetails> updateDetails) {

        List<HistoryUpdateResult> results = Lists.newArrayListWithCapacity(updateDetails.size());

        for (HistoryUpdateDetails details : updateDetails) {

            results.add(new HistoryUpdateResult(new StatusCode(StatusCodes.Bad_NotSupported), null, null));
        }

        context.complete(results);
    }

    final class HistoryReadContext extends OperationContext<HistoryReadValueId, HistoryReadResult> {
        public HistoryReadContext(OpcUaServer server,
                                  @Nullable Session session,
                                  DiagnosticsContext<HistoryReadValueId> diagnosticsContext) {

            super(server, session, diagnosticsContext);
        }

        public HistoryReadContext(OpcUaServer server,
                                  @Nullable Session session,
                                  CompletableFuture<List<HistoryReadResult>> future,
                                  DiagnosticsContext<HistoryReadValueId> diagnosticsContext) {

            super(server, session, future, diagnosticsContext);
        }
    }

    final class HistoryUpdateContext extends OperationContext<HistoryUpdateDetails, HistoryUpdateResult> {
        public HistoryUpdateContext(OpcUaServer server,
                                    @Nullable Session session,
                                    DiagnosticsContext<HistoryUpdateDetails> diagnosticsContext) {

            super(server, session, diagnosticsContext);
        }

        public HistoryUpdateContext(OpcUaServer server,
                                    @Nullable Session session,
                                    CompletableFuture<List<HistoryUpdateResult>> future,
                                    DiagnosticsContext<HistoryUpdateDetails> diagnosticsContext) {

            super(server, session, future, diagnosticsContext);
        }
    }
}
