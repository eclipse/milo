/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.services;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.server.DiagnosticsContext;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.Session;
import org.eclipse.milo.opcua.sdk.server.api.AttributeServices.ReadContext;
import org.eclipse.milo.opcua.sdk.server.api.AttributeServices.WriteContext;
import org.eclipse.milo.opcua.sdk.server.api.Namespace;
import org.eclipse.milo.opcua.sdk.server.util.Pending;
import org.eclipse.milo.opcua.sdk.server.util.PendingRead;
import org.eclipse.milo.opcua.sdk.server.util.PendingWrite;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.DiagnosticInfo;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadValueId;
import org.eclipse.milo.opcua.stack.core.types.structured.ResponseHeader;
import org.eclipse.milo.opcua.stack.core.types.structured.WriteRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.WriteResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.WriteValue;
import org.eclipse.milo.opcua.stack.core.util.FutureUtils;
import org.eclipse.milo.opcua.stack.server.services.AttributeServiceSet;
import org.eclipse.milo.opcua.stack.server.services.ServiceRequest;

import static com.google.common.collect.Lists.newArrayListWithCapacity;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
import static org.eclipse.milo.opcua.stack.core.util.ConversionUtil.a;
import static org.eclipse.milo.opcua.stack.core.util.ConversionUtil.l;

public class DefaultAttributeServiceSet implements AttributeServiceSet {

    private final ServiceCounter readMetric = new ServiceCounter();
    private final ServiceCounter writeMetric = new ServiceCounter();

    @Override
    public void onRead(ServiceRequest service) {
        readMetric.record(service);

        ReadRequest request = (ReadRequest) service.getRequest();

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

        CompletableFuture<List<DataValue>> readFuture = namespaceCollatedRead(
            server,
            session,
            request.getMaxAge(),
            request.getTimestampsToReturn(),
            nodesToRead
        );

        readFuture.thenAcceptAsync(values -> {
            ResponseHeader header = service.createResponseHeader();

            DiagnosticInfo[] diagnosticInfos =
                diagnosticsContext.getDiagnosticInfos(nodesToRead);

            ReadResponse response = new ReadResponse(
                header,
                a(values, DataValue.class),
                diagnosticInfos
            );

            service.setResponse(response);
        }, server.getExecutorService());
    }

    public static CompletableFuture<List<DataValue>> namespaceCollatedRead(
        OpcUaServer server,
        Session session,
        double maxAge,
        TimestampsToReturn timestamps,
        List<ReadValueId> nodesToRead
    ) {

        List<PendingRead> pendingReads = nodesToRead.stream()
            .map(PendingRead::new)
            .collect(toList());

        List<CompletableFuture<DataValue>> futures = pendingReads.stream()
            .map(PendingRead::getFuture)
            .collect(toList());

        // Group PendingReads by namespace and call read for each.

        Map<UShort, List<PendingRead>> byNamespace = pendingReads.stream()
            .collect(groupingBy(pending -> pending.getInput().getNodeId().getNamespaceIndex()));

        byNamespace.keySet().forEach(index -> {
            List<PendingRead> pending = byNamespace.get(index);

            ReadContext context = new ReadContext(
                server,
                session,
                Pending.callback(pending),
                new DiagnosticsContext<>()
            );

            server.getExecutorService().execute(() -> {
                Namespace namespace = server.getNamespaceManager().getNamespace(index);

                List<ReadValueId> readValueIds = pending.stream()
                    .map(PendingRead::getInput)
                    .collect(toList());

                namespace.read(
                    context,
                    maxAge,
                    timestamps,
                    readValueIds
                );
            });
        });

        return FutureUtils.sequence(futures);
    }

    @Override
    public void onWrite(ServiceRequest service) {
        writeMetric.record(service);

        WriteRequest request = (WriteRequest) service.getRequest();

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

        CompletableFuture<List<StatusCode>> writeFuture = namespaceCollatedWrite(server, session, nodesToWrite);

        writeFuture.thenAcceptAsync(values -> {
            ResponseHeader header = service.createResponseHeader();

            DiagnosticInfo[] diagnosticInfos =
                diagnosticsContext.getDiagnosticInfos(nodesToWrite);

            WriteResponse response = new WriteResponse(
                header,
                a(values, StatusCode.class),
                diagnosticInfos
            );

            service.setResponse(response);
        }, server.getExecutorService());
    }

    public static CompletableFuture<List<StatusCode>> namespaceCollatedWrite(
        OpcUaServer server,
        Session session,
        List<WriteValue> nodesToWrite
    ) {

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

            WriteContext context = new WriteContext(
                server,
                session,
                Pending.callback(pending),
                new DiagnosticsContext<>()
            );

            server.getExecutorService().execute(() -> {
                Namespace namespace = server.getNamespaceManager().getNamespace(index);

                List<WriteValue> writeValues = pending.stream()
                    .map(PendingWrite::getInput)
                    .collect(toList());

                namespace.write(context, writeValues);
            });
        });

        return FutureUtils.sequence(futures);
    }

}
