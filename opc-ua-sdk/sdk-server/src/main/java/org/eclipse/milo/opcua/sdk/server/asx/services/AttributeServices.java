/*
 * Copyright (c) 2022 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.asx.services;

import java.util.Collections;
import java.util.List;

import org.eclipse.milo.opcua.sdk.server.DiagnosticsContext;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.ServiceOperationContext;
import org.eclipse.milo.opcua.sdk.server.Session;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;
import org.eclipse.milo.opcua.stack.core.types.structured.HistoryReadDetails;
import org.eclipse.milo.opcua.stack.core.types.structured.HistoryReadResult;
import org.eclipse.milo.opcua.stack.core.types.structured.HistoryReadValueId;
import org.eclipse.milo.opcua.stack.core.types.structured.HistoryUpdateDetails;
import org.eclipse.milo.opcua.stack.core.types.structured.HistoryUpdateResult;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadValueId;
import org.eclipse.milo.opcua.stack.core.types.structured.WriteValue;
import org.jetbrains.annotations.Nullable;

public interface AttributeServices {

    /**
     * Read one or more values from nodes belonging to this {@link AttributeServices}.
     * <p>
     * Complete the operation with {@link ReadContext#success(Object)}.
     *
     * @param context      the {@link ReadContext}.
     * @param maxAge       requested max age.
     * @param timestamps   requested timestamp values.
     * @param readValueIds the values to read.
     */
    void read(ReadContext context, Double maxAge, TimestampsToReturn timestamps, List<ReadValueId> readValueIds);

    /**
     * Write one or more values to nodes belonging to this {@link AttributeServices}.
     * <p>
     * Complete the operation with {@link WriteContext#success(Object)}.
     *
     * @param context     the {@link WriteContext}.
     * @param writeValues the values to write.
     */
    void write(WriteContext context, List<WriteValue> writeValues);

    /**
     * Read history values from nodes belonging to this {@link AttributeServices}.
     * <p>
     * Complete the operation with {@link HistoryReadContext#success(Object)}.
     *
     * @param context      the {@link HistoryReadContext}.
     * @param timestamps   requested timestamp values.
     * @param readValueIds the values to read.
     */
    default void historyRead(
        HistoryReadContext context,
        HistoryReadDetails readDetails,
        TimestampsToReturn timestamps,
        List<HistoryReadValueId> readValueIds
    ) {

        HistoryReadResult result = new HistoryReadResult(
            new StatusCode(StatusCodes.Bad_HistoryOperationUnsupported),
            null,
            null
        );

        context.success(Collections.nCopies(readValueIds.size(), result));
    }

    /**
     * Update history values in nodes belonging to this {@link AttributeServices}.
     * <p>
     * Complete the operation with {@link HistoryUpdateContext#success(Object)}.
     *
     * @param context       the {@link HistoryUpdateContext}.
     * @param updateDetails the values to read.
     */
    default void historyUpdate(
        HistoryUpdateContext context,
        List<HistoryUpdateDetails> updateDetails
    ) {

        HistoryUpdateResult result = new HistoryUpdateResult(
            new StatusCode(StatusCodes.Bad_HistoryOperationUnsupported),
            null,
            null
        );

        context.success(Collections.nCopies(updateDetails.size(), result));
    }

    final class ReadContext extends ServiceOperationContext<ReadValueId, DataValue> {

        public ReadContext(OpcUaServer server, @Nullable Session session) {
            super(server, session);
        }

        public ReadContext(
            OpcUaServer server,
            @Nullable Session session,
            DiagnosticsContext<ReadValueId> diagnosticsContext,
            @Nullable String auditEntryId,
            UInteger timeoutHint,
            ExtensionObject additionalHeader
        ) {

            super(server, session, diagnosticsContext, auditEntryId, timeoutHint, additionalHeader);
        }

    }

    final class WriteContext extends ServiceOperationContext<WriteValue, StatusCode> {

        public WriteContext(OpcUaServer server, @Nullable Session session) {
            super(server, session);
        }

        public WriteContext(
            OpcUaServer server,
            @Nullable Session session,
            DiagnosticsContext<WriteValue> diagnosticsContext,
            @Nullable String auditEntryId,
            UInteger timeoutHint,
            ExtensionObject additionalHeader
        ) {

            super(server, session, diagnosticsContext, auditEntryId, timeoutHint, additionalHeader);
        }

    }

    final class HistoryReadContext extends ServiceOperationContext<HistoryReadValueId, HistoryReadResult> {

        public HistoryReadContext(OpcUaServer server, @Nullable Session session) {
            super(server, session);
        }

        public HistoryReadContext(
            OpcUaServer server,
            @Nullable Session session,
            DiagnosticsContext<HistoryReadValueId> diagnosticsContext,
            @Nullable String auditEntryId,
            UInteger timeoutHint,
            ExtensionObject additionalHeader
        ) {

            super(server, session, diagnosticsContext, auditEntryId, timeoutHint, additionalHeader);
        }

    }

    final class HistoryUpdateContext extends ServiceOperationContext<HistoryUpdateDetails, HistoryUpdateResult> {

        public HistoryUpdateContext(OpcUaServer server, @Nullable Session session) {
            super(server, session);
        }

        public HistoryUpdateContext(
            OpcUaServer server,
            @Nullable Session session,
            DiagnosticsContext<HistoryUpdateDetails> diagnosticsContext,
            @Nullable String auditEntryId,
            UInteger timeoutHint,
            ExtensionObject additionalHeader
        ) {

            super(server, session, diagnosticsContext, auditEntryId, timeoutHint, additionalHeader);
        }

    }

}
