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

import org.eclipse.milo.opcua.sdk.server.DiagnosticsContext;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.Session;
import org.eclipse.milo.opcua.sdk.server.api.services.NodeManagementServices.AddNodesContext;
import org.eclipse.milo.opcua.sdk.server.api.services.NodeManagementServices.DeleteNodesContext;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DiagnosticInfo;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.structured.AddNodesItem;
import org.eclipse.milo.opcua.stack.core.types.structured.AddNodesRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.AddNodesResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.AddNodesResult;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteNodesItem;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteNodesRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteNodesResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.ResponseHeader;
import org.eclipse.milo.opcua.stack.server.services.NodeManagementServiceSet;
import org.eclipse.milo.opcua.stack.server.services.ServiceRequest;

import static org.eclipse.milo.opcua.stack.core.StatusCodes.Bad_ServiceUnsupported;
import static org.eclipse.milo.opcua.stack.core.util.ConversionUtil.a;
import static org.eclipse.milo.opcua.stack.core.util.ConversionUtil.l;

public class DefaultNodeManagementServiceSet implements NodeManagementServiceSet {

    private final ServiceCounter addNodesMetric = new ServiceCounter();
    private final ServiceCounter deleteNodesMetric = new ServiceCounter();
    private final ServiceCounter addReferencesMetric = new ServiceCounter();
    private final ServiceCounter deleteReferencesMetric = new ServiceCounter();

    @Override
    public void onAddNodes(ServiceRequest service) {
        addNodesMetric.record(service);

        OpcUaServer server = service.attr(ServiceAttributes.SERVER_KEY).get();
        Session session = service.attr(ServiceAttributes.SESSION_KEY).get();

        AddNodesRequest request = (AddNodesRequest) service.getRequest();

        List<AddNodesItem> nodesToAdd = l(request.getNodesToAdd());

        if (nodesToAdd.isEmpty()) {
            service.setServiceFault(StatusCodes.Bad_NothingToDo);
            return;
        }

        if (nodesToAdd.size() > server.getConfig().getLimits().getMaxNodesPerNodeManagement().longValue()) {
            service.setServiceFault(StatusCodes.Bad_TooManyOperations);
            return;
        }

        AddNodesContext context = new AddNodesContext(
            server,
            session,
            new DiagnosticsContext<>()
        );

        server.getAddressSpaceManager().addNodes(context, nodesToAdd);

        context.getFuture().thenAccept(results -> {
            ResponseHeader header = service.createResponseHeader();

            AddNodesResponse response = new AddNodesResponse(
                header,
                a(results, AddNodesResult.class),
                new DiagnosticInfo[0]
            );

            service.setResponse(response);
        });
    }

    @Override
    public void onDeleteNodes(ServiceRequest service) throws UaException {
        deleteNodesMetric.record(service);

        OpcUaServer server = service.attr(ServiceAttributes.SERVER_KEY).get();
        Session session = service.attr(ServiceAttributes.SESSION_KEY).get();

        DeleteNodesRequest request = (DeleteNodesRequest) service.getRequest();

        List<DeleteNodesItem> nodesToDelete = l(request.getNodesToDelete());

        if (nodesToDelete.isEmpty()) {
            service.setServiceFault(StatusCodes.Bad_NothingToDo);
            return;
        }

        if (nodesToDelete.size() > server.getConfig().getLimits().getMaxNodesPerNodeManagement().longValue()) {
            service.setServiceFault(StatusCodes.Bad_TooManyOperations);
            return;
        }

        DeleteNodesContext context = new DeleteNodesContext(
            server,
            session,
            new DiagnosticsContext<>()
        );

        server.getAddressSpaceManager().deleteNodes(context, nodesToDelete);

        context.getFuture().thenAccept(results -> {
            ResponseHeader header = service.createResponseHeader();

            DeleteNodesResponse response = new DeleteNodesResponse(
                header,
                a(results, StatusCode.class),
                new DiagnosticInfo[0]
            );

            service.setResponse(response);
        });
    }

    @Override
    public void onAddReferences(ServiceRequest service) throws UaException {
        addReferencesMetric.record(service);

        service.setServiceFault(Bad_ServiceUnsupported);
    }

    @Override
    public void onDeleteReferences(ServiceRequest service) throws UaException {
        deleteReferencesMetric.record(service);

        service.setServiceFault(Bad_ServiceUnsupported);
    }

}
