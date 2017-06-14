/*
 * Copyright (c) 2016 Kevin Herron
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

import org.eclipse.milo.opcua.sdk.server.DiagnosticsContext;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.Session;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadValueId;
import org.eclipse.milo.opcua.stack.core.types.structured.WriteValue;

public interface AttributeManager {

    /**
     * Read one or more values from nodes belonging to this {@link AttributeManager}.
     * <p>
     * Complete the operation with {@link ReadContext#complete(List)}.
     *
     * @param context      the {@link ReadContext}.
     * @param maxAge       requested max age.
     * @param timestamps   requested timestamp values.
     * @param readValueIds the values to read.
     */
    void read(ReadContext context,
              Double maxAge,
              TimestampsToReturn timestamps,
              List<ReadValueId> readValueIds);

    /**
     * Write one or more values to nodes belonging to this {@link AttributeManager}.
     * <p>
     * Complete the operation with {@link WriteContext#complete(List)}.
     *
     * @param context     the {@link WriteContext}.
     * @param writeValues the values to write.
     */
    void write(WriteContext context, List<WriteValue> writeValues);

    final class ReadContext extends OperationContext<ReadValueId, DataValue> {
        public ReadContext(OpcUaServer server,
                           @Nullable Session session,
                           DiagnosticsContext<ReadValueId> diagnosticsContext) {

            super(server, session, diagnosticsContext);
        }

        public ReadContext(OpcUaServer server,
                           @Nullable Session session,
                           CompletableFuture<List<DataValue>> future,
                           DiagnosticsContext<ReadValueId> diagnosticsContext) {

            super(server, session, future, diagnosticsContext);
        }
    }

    final class WriteContext extends OperationContext<WriteValue, StatusCode> {
        public WriteContext(OpcUaServer server,
                            @Nullable Session session,
                            DiagnosticsContext<WriteValue> diagnosticsContext) {

            super(server, session, diagnosticsContext);
        }

        public WriteContext(OpcUaServer server,
                            @Nullable Session session,
                            CompletableFuture<List<StatusCode>> future,
                            DiagnosticsContext<WriteValue> diagnosticsContext) {

            super(server, session, future, diagnosticsContext);
        }
    }

}
