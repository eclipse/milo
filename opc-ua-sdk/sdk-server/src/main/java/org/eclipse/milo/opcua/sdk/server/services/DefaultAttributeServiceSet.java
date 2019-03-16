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
import org.eclipse.milo.opcua.sdk.server.api.AddressSpace;
import org.eclipse.milo.opcua.sdk.server.api.AttributeServices.ReadContext;
import org.eclipse.milo.opcua.sdk.server.api.AttributeServices.WriteContext;
import org.eclipse.milo.opcua.sdk.server.util.Pending;
import org.eclipse.milo.opcua.sdk.server.util.PendingRead;
import org.eclipse.milo.opcua.sdk.server.util.PendingWrite;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.DiagnosticInfo;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
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

        CompletableFuture<List<DataValue>> readFuture = addressSpaceRead(
            server,
            session,
            request.getMaxAge(),
            request.getTimestampsToReturn(),
            nodesToRead
        );

        readFuture.thenAccept(values -> {
            ResponseHeader header = service.createResponseHeader();

            DiagnosticInfo[] diagnosticInfos =
                diagnosticsContext.getDiagnosticInfos(nodesToRead);

            ReadResponse response = new ReadResponse(
                header,
                values.toArray(new DataValue[0]),
                diagnosticInfos
            );

            service.setResponse(response);
        });
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

        CompletableFuture<List<StatusCode>> writeFuture = addressSpaceWrite(server, session, nodesToWrite);

        writeFuture.thenAccept(values -> {
            ResponseHeader header = service.createResponseHeader();

            DiagnosticInfo[] diagnosticInfos =
                diagnosticsContext.getDiagnosticInfos(nodesToWrite);

            WriteResponse response = new WriteResponse(
                header,
                values.toArray(new StatusCode[0]),
                diagnosticInfos
            );

            service.setResponse(response);
        });
    }

    public static CompletableFuture<List<DataValue>> addressSpaceRead(
        OpcUaServer server,
        Session session,
        double maxAge,
        TimestampsToReturn timestamps,
        List<ReadValueId> nodesToRead
    ) {

        List<PendingRead> pendingReads = newArrayListWithCapacity(nodesToRead.size());
        List<CompletableFuture<DataValue>> futures = newArrayListWithCapacity(nodesToRead.size());

        for (ReadValueId readValueId : nodesToRead) {
            PendingRead pendingRead = new PendingRead(readValueId);

            pendingReads.add(pendingRead);
            futures.add(pendingRead.getFuture());
        }

        // Group PendingReads by AddressSpace and call read for each.

        Map<AddressSpace, List<PendingRead>> byAddressSpace = pendingReads
            .stream()
            .collect(groupingBy(pending -> {
                NodeId nodeId = pending.getInput().getNodeId();

                return server.getAddressSpaceManager().getAddressSpace(nodeId);
            }));

        byAddressSpace.keySet().forEach(addressSpace -> {
            List<PendingRead> pending = byAddressSpace.get(addressSpace);

            ReadContext context = new ReadContext(
                server,
                session,
                Pending.callback(pending),
                new DiagnosticsContext<>()
            );

            server.getExecutorService().execute(() -> {
                List<ReadValueId> readValueIds = pending.stream()
                    .map(PendingRead::getInput)
                    .collect(toList());

                addressSpace.read(
                    context,
                    maxAge,
                    timestamps,
                    readValueIds
                );
            });
        });

        return FutureUtils.sequence(futures);
    }

    public static CompletableFuture<List<StatusCode>> addressSpaceWrite(
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

        // Group PendingWrites by AddressSpace and call write for each.

        Map<AddressSpace, List<PendingWrite>> byAddressSpace = pendingWrites
            .stream()
            .collect(groupingBy(pending -> {
                NodeId nodeId = pending.getInput().getNodeId();

                return server.getAddressSpaceManager().getAddressSpace(nodeId);
            }));

        byAddressSpace.keySet().forEach(addressSpace -> {
            List<PendingWrite> pending = byAddressSpace.get(addressSpace);

            WriteContext context = new WriteContext(
                server,
                session,
                Pending.callback(pending),
                new DiagnosticsContext<>()
            );

            server.getExecutorService().execute(() -> {
                List<WriteValue> writeValues = pending.stream()
                    .map(PendingWrite::getInput)
                    .collect(toList());

                addressSpace.write(context, writeValues);
            });
        });

        return FutureUtils.sequence(futures);
    }

}
