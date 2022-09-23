/*
 * Copyright (c) 2022 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.services2;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.server.DiagnosticsContext;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.Session;
import org.eclipse.milo.opcua.sdk.server.api.services.AttributeServices.ReadContext;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.DiagnosticInfo;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadValueId;
import org.eclipse.milo.opcua.stack.core.types.structured.ResponseHeader;
import org.eclipse.milo.opcua.stack.core.types.structured.WriteRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.WriteResponse;

import static org.eclipse.milo.opcua.stack.core.util.ConversionUtil.l;
import static org.eclipse.milo.opcua.stack.core.util.FutureUtils.failedUaFuture;

public class DefaultAttributeServiceSet implements AttributeServiceSet {

    @Override
    public CompletableFuture<ReadResponse> onRead(ServiceContext context, ReadRequest request) {
        OpcUaServer server = context.getServer();
        Session session = context.getSession().orElse(null);

        List<ReadValueId> nodesToRead = l(request.getNodesToRead());

        if (nodesToRead.isEmpty()) {
            return failedUaFuture(StatusCodes.Bad_NothingToDo);
        }

        if (nodesToRead.size() > server.getConfig().getLimits().getMaxNodesPerRead().longValue()) {
            return failedUaFuture(StatusCodes.Bad_TooManyOperations);
        }

        if (request.getMaxAge() < 0d) {
            return failedUaFuture(StatusCodes.Bad_MaxAgeInvalid);
        }

        if (request.getTimestampsToReturn() == null) {
            return failedUaFuture(StatusCodes.Bad_TimestampsToReturnInvalid);
        }

        var diagnosticsContext = new DiagnosticsContext<ReadValueId>();

        var readContext = new ReadContext(server, session, diagnosticsContext);

        server.getAddressSpaceManager().read(
            readContext,
            request.getMaxAge(),
            request.getTimestampsToReturn(),
            nodesToRead
        );

        return readContext.getFuture().thenApply(values -> {
            DiagnosticInfo[] diagnosticInfos =
                diagnosticsContext.getDiagnosticInfos(nodesToRead);

            ResponseHeader header = ServiceContext.createResponseHeader(request);

            return new ReadResponse(header, values.toArray(new DataValue[0]), diagnosticInfos);
        });
    }

    @Override
    public CompletableFuture<WriteResponse> onWrite(ServiceContext context, WriteRequest request) {
        return null;
    }


}
