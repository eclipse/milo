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
import org.eclipse.milo.opcua.stack.server.services.QueryServiceSet;
import org.eclipse.milo.opcua.stack.server.services.ServiceRequest;

import static org.eclipse.milo.opcua.stack.core.StatusCodes.Bad_ServiceUnsupported;

public class DefaultQueryServiceSet implements QueryServiceSet {

    private final ServiceCounter queryFirstMetric = new ServiceCounter();
    private final ServiceCounter queryNextMetric = new ServiceCounter();

    @Override
    public void onQueryFirst(ServiceRequest service) throws UaException {
        queryFirstMetric.record(service);

        service.setServiceFault(Bad_ServiceUnsupported);
    }

    @Override
    public void onQueryNext(ServiceRequest service) throws UaException {
        queryNextMetric.record(service);

        service.setServiceFault(Bad_ServiceUnsupported);
    }

}
