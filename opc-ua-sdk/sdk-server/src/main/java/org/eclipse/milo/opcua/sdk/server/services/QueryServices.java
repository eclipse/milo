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
import org.eclipse.milo.opcua.stack.core.application.services.QueryServiceSet;
import org.eclipse.milo.opcua.stack.core.application.services.ServiceRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.QueryFirstRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.QueryFirstResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.QueryNextRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.QueryNextResponse;

import static org.eclipse.milo.opcua.stack.core.StatusCodes.Bad_ServiceUnsupported;

public class QueryServices implements QueryServiceSet {

    private final ServiceMetric queryFirstMetric = new ServiceMetric();
    private final ServiceMetric queryNextMetric = new ServiceMetric();

    @Override
    public void onQueryFirst(ServiceRequest<QueryFirstRequest, QueryFirstResponse> service) throws UaException {
        queryFirstMetric.record(service);

        service.setServiceFault(Bad_ServiceUnsupported);
    }

    @Override
    public void onQueryNext(ServiceRequest<QueryNextRequest, QueryNextResponse> service) throws UaException {
        queryNextMetric.record(service);

        service.setServiceFault(Bad_ServiceUnsupported);
    }

}
