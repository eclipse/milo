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

import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.server.services.NodeManagementServiceSet;
import org.eclipse.milo.opcua.stack.server.services.ServiceRequest;

import static org.eclipse.milo.opcua.stack.core.StatusCodes.Bad_ServiceUnsupported;

public class DefaultNodeManagementServiceSet implements NodeManagementServiceSet {

    private final ServiceMetric addNodesMetric = new ServiceMetric();
    private final ServiceMetric deleteNodesMetric = new ServiceMetric();
    private final ServiceMetric addReferencesMetric = new ServiceMetric();
    private final ServiceMetric deleteReferencesMetric = new ServiceMetric();

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
