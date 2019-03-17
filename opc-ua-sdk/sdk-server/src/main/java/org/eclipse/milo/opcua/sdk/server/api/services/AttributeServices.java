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

import java.util.List;
import javax.annotation.Nullable;

import org.eclipse.milo.opcua.sdk.server.DiagnosticsContext;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.Session;
import org.eclipse.milo.opcua.sdk.server.api.ServiceOperationContext;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadValueId;
import org.eclipse.milo.opcua.stack.core.types.structured.WriteValue;

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
    void read(ReadContext context,
              Double maxAge,
              TimestampsToReturn timestamps,
              List<ReadValueId> readValueIds);

    /**
     * Write one or more values to nodes belonging to this {@link AttributeServices}.
     * <p>
     * Complete the operation with {@link WriteContext#success(Object)}.
     *
     * @param context     the {@link WriteContext}.
     * @param writeValues the values to write.
     */
    void write(WriteContext context, List<WriteValue> writeValues);

    final class ReadContext extends ServiceOperationContext<ReadValueId, DataValue> {

        public ReadContext(OpcUaServer server, @Nullable Session session) {
            super(server, session);
        }

        public ReadContext(
            OpcUaServer server,
            @Nullable Session session,
            DiagnosticsContext<ReadValueId> diagnosticsContext
        ) {

            super(server, session, diagnosticsContext);
        }

    }

    final class WriteContext extends ServiceOperationContext<WriteValue, StatusCode> {

        public WriteContext(OpcUaServer server, @Nullable Session session) {
            super(server, session);
        }

        public WriteContext(
            OpcUaServer server,
            @Nullable Session session,
            DiagnosticsContext<WriteValue> diagnosticsContext
        ) {

            super(server, session, diagnosticsContext);
        }

    }

}
