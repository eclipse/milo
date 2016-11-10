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

package org.eclipse.milo.opcua.sdk.server.services;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.server.DiagnosticsContext;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.Session;
import org.eclipse.milo.opcua.sdk.server.api.AttributeManager.ReadContext;
import org.eclipse.milo.opcua.sdk.server.api.AttributeManager.WriteContext;
import org.eclipse.milo.opcua.sdk.server.api.Namespace;
import org.eclipse.milo.opcua.sdk.server.util.PendingRead;
import org.eclipse.milo.opcua.sdk.server.util.PendingWrite;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.application.services.AttributeServiceSet;
import org.eclipse.milo.opcua.stack.core.application.services.ServiceRequest;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.DiagnosticInfo;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadValueId;
import org.eclipse.milo.opcua.stack.core.types.structured.ResponseHeader;
import org.eclipse.milo.opcua.stack.core.types.structured.WriteRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.WriteResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.WriteValue;
import org.eclipse.milo.opcua.stack.core.util.FutureUtils;

import static com.google.common.collect.Lists.newArrayListWithCapacity;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
import static org.eclipse.milo.opcua.stack.core.util.ConversionUtil.a;
import static org.eclipse.milo.opcua.stack.core.util.ConversionUtil.l;

public class AttributeServices implements AttributeServiceSet {

    private final ServiceMetric readMetric = new ServiceMetric();
    private final ServiceMetric writeMetric = new ServiceMetric();

    @Override
    public void onRead(ServiceRequest<ReadRequest, ReadResponse> service) {
        readMetric.record(service);

        ReadRequest request = service.getRequest();

        DiagnosticsContext<ReadValueId> diagnosticsContext = new DiagnosticsContext<>();

        OpcUaServer server = service.attr(ServiceAttributes.SERVER_KEY).get();
        Session session = service.attr(ServiceAttributes.SESSION_KEY).get();

        List<ReadValueId> nodesToRead = l(request.getNodesToRead());

        if (nodesToRead.isEmpty()) {
            service.setServiceFault(StatusCodes.Bad_NothingToDo);
            return;
        }

        if (nodesToRead.size() > server.getConfig().getLimits().getMaxNodesPerRead().longValue()) {
            service.setServiceFault(StatusCodes.Bad_TooManyOperations);
            return;
        }

        if (request.getMaxAge() < 0d) {
            service.setServiceFault(StatusCodes.Bad_MaxAgeInvalid);
            return;
        }

        if (request.getTimestampsToReturn() == null) {
            service.setServiceFault(StatusCodes.Bad_TimestampsToReturnInvalid);
            return;
        }


        List<PendingRead> pendingReads = newArrayListWithCapacity(nodesToRead.size());
        List<CompletableFuture<DataValue>> futures = newArrayListWithCapacity(nodesToRead.size());

        for (ReadValueId id : nodesToRead) {
            PendingRead pending = new PendingRead(id);

            pendingReads.add(pending);
            futures.add(pending.getFuture());
        }

        // Group PendingReads by namespace and call read for each.

        Map<UShort, List<PendingRead>> byNamespace = pendingReads.stream()
            .collect(groupingBy(pending -> pending.getInput().getNodeId().getNamespaceIndex()));

        byNamespace.keySet().forEach(index -> {
            List<PendingRead> pending = byNamespace.get(index);

            CompletableFuture<List<DataValue>> future = new CompletableFuture<>();

            ReadContext context = new ReadContext(
                server, session, future, diagnosticsContext);

            server.getExecutorService().execute(() -> {
                Namespace namespace = server.getNamespaceManager().getNamespace(index);

                List<ReadValueId> readValueIds = pending.stream()
                    .map(PendingRead::getInput)
                    .collect(toList());

                namespace.read(
                    context,
                    request.getMaxAge(),
                    request.getTimestampsToReturn(),
                    readValueIds);
            });

            future.thenAccept(values -> {
                for (int i = 0; i < values.size(); i++) {
                    pending.get(i).getFuture().complete(values.get(i));
                }
            });
        });

        // When all PendingReads have been completed send a ReadResponse with the values.

        FutureUtils.sequence(futures).thenAcceptAsync(values -> {
            ResponseHeader header = service.createResponseHeader();

            DiagnosticInfo[] diagnosticInfos =
                diagnosticsContext.getDiagnosticInfos(nodesToRead);

            ReadResponse response = new ReadResponse(
                header, a(values, DataValue.class), diagnosticInfos);

            service.setResponse(response);
        }, server.getExecutorService());
    }

    @Override
    public void onWrite(ServiceRequest<WriteRequest, WriteResponse> service) {
        writeMetric.record(service);

        WriteRequest request = service.getRequest();

        DiagnosticsContext<WriteValue> diagnosticsContext = new DiagnosticsContext<>();

        OpcUaServer server = service.attr(ServiceAttributes.SERVER_KEY).get();
        Session session = service.attr(ServiceAttributes.SESSION_KEY).get();

        List<WriteValue> nodesToWrite = l(request.getNodesToWrite());

        if (nodesToWrite.isEmpty()) {
            service.setServiceFault(StatusCodes.Bad_NothingToDo);
            return;
        }

        if (nodesToWrite.size() > server.getConfig().getLimits().getMaxNodesPerWrite().intValue()) {
            service.setServiceFault(StatusCodes.Bad_TooManyOperations);
            return;
        }

        List<PendingWrite> pendingWrites = newArrayListWithCapacity(nodesToWrite.size());
        List<CompletableFuture<StatusCode>> futures = newArrayListWithCapacity(nodesToWrite.size());

        for (WriteValue value : nodesToWrite) {
            PendingWrite pending = new PendingWrite(value);

            pendingWrites.add(pending);
            futures.add(pending.getFuture());
        }

        Map<UShort, List<PendingWrite>> byNamespace = pendingWrites.stream()
            .collect(groupingBy(pending -> pending.getInput().getNodeId().getNamespaceIndex()));

        byNamespace.keySet().forEach(index -> {
            List<PendingWrite> pending = byNamespace.get(index);

            CompletableFuture<List<StatusCode>> future = new CompletableFuture<>();

            WriteContext context = new WriteContext(
                server, session, future, diagnosticsContext);

            server.getExecutorService().execute(() -> {
                Namespace namespace = server.getNamespaceManager().getNamespace(index);

                List<WriteValue> writeValues = pending.stream()
                    .map(PendingWrite::getInput)
                    .collect(toList());

                namespace.write(context, writeValues);
            });

            future.thenAccept(statusCodes -> {
                for (int i = 0; i < statusCodes.size(); i++) {
                    pending.get(i).getFuture().complete(statusCodes.get(i));
                }
            });
        });

        FutureUtils.sequence(futures).thenAcceptAsync(values -> {
            ResponseHeader header = service.createResponseHeader();

            DiagnosticInfo[] diagnosticInfos =
                diagnosticsContext.getDiagnosticInfos(nodesToWrite);

            WriteResponse response = new WriteResponse(
                header, a(values, StatusCode.class), diagnosticInfos);

            service.setResponse(response);
        }, server.getExecutorService());
    }

}
