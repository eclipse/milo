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

import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.server.services.NodeManagementServiceSet;
import org.eclipse.milo.opcua.stack.server.services.ServiceRequest;

import static org.eclipse.milo.opcua.stack.core.StatusCodes.Bad_ServiceUnsupported;

public class DefaultNodeManagementServiceSet implements NodeManagementServiceSet {

    private final ServiceCounter addNodesMetric = new ServiceCounter();
    private final ServiceCounter deleteNodesMetric = new ServiceCounter();
    private final ServiceCounter addReferencesMetric = new ServiceCounter();
    private final ServiceCounter deleteReferencesMetric = new ServiceCounter();

    @Override
    public void onAddNodes(ServiceRequest service) throws UaException {
        addNodesMetric.record(service);

        service.setServiceFault(Bad_ServiceUnsupported);
    }

    @Override
    public void onDeleteNodes(ServiceRequest service) throws UaException {
        deleteNodesMetric.record(service);

        service.setServiceFault(Bad_ServiceUnsupported);
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
