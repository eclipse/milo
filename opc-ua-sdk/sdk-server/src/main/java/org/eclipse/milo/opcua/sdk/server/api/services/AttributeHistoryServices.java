/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.api.services;

import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;

import org.eclipse.milo.opcua.sdk.server.DiagnosticsContext;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.Session;
import org.eclipse.milo.opcua.sdk.server.api.ServiceOperationContext;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;
import org.eclipse.milo.opcua.stack.core.types.structured.HistoryReadDetails;
import org.eclipse.milo.opcua.stack.core.types.structured.HistoryReadResult;
import org.eclipse.milo.opcua.stack.core.types.structured.HistoryReadValueId;
import org.eclipse.milo.opcua.stack.core.types.structured.HistoryUpdateDetails;
import org.eclipse.milo.opcua.stack.core.types.structured.HistoryUpdateResult;

public interface AttributeHistoryServices {

    /**
     * Read history values from nodes belonging to this {@link AttributeHistoryServices}.
     * <p>
     * Complete the operation with {@link HistoryReadContext#success(Object)}.
     *
     * @param context      the {@link HistoryReadContext}.
     * @param timestamps   requested timestamp values.
     * @param readValueIds the values to read.
     */
    default void historyRead(HistoryReadContext context,
                             HistoryReadDetails readDetails,
                             TimestampsToReturn timestamps,
                             List<HistoryReadValueId> readValueIds) {

        HistoryReadResult result = new HistoryReadResult(
            new StatusCode(StatusCodes.Bad_HistoryOperationUnsupported),
            null,
            null
        );

        context.success(Collections.nCopies(readValueIds.size(), result));
    }

    /**
     * Update history values in nodes belonging to this {@link AttributeHistoryServices}.
     * <p>
     * Complete the operation with {@link HistoryUpdateContext#success(Object)}.
     *
     * @param context       the {@link HistoryUpdateContext}.
     * @param updateDetails the values to read.
     */
    default void historyUpdate(HistoryUpdateContext context,
                               List<HistoryUpdateDetails> updateDetails) {

        HistoryUpdateResult result = new HistoryUpdateResult(
            new StatusCode(StatusCodes.Bad_HistoryOperationUnsupported),
            null,
            null
        );

        context.success(Collections.nCopies(updateDetails.size(), result));
    }

    final class HistoryReadContext extends ServiceOperationContext<HistoryReadValueId, HistoryReadResult> {

        public HistoryReadContext(OpcUaServer server, @Nullable Session session) {
            super(server, session);
        }

        public HistoryReadContext(
            OpcUaServer server,
            @Nullable Session session,
            DiagnosticsContext<HistoryReadValueId> diagnosticsContext) {

            super(server, session, diagnosticsContext);
        }

    }

    final class HistoryUpdateContext extends ServiceOperationContext<HistoryUpdateDetails, HistoryUpdateResult> {

        public HistoryUpdateContext(OpcUaServer server, @Nullable Session session) {
            super(server, session);
        }

        public HistoryUpdateContext(
            OpcUaServer server,
            @Nullable Session session,
            DiagnosticsContext<HistoryUpdateDetails> diagnosticsContext
        ) {

            super(server, session, diagnosticsContext);
        }

    }
}
