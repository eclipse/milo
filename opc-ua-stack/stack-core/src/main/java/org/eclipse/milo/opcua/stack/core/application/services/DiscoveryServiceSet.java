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

package org.eclipse.milo.opcua.stack.core.application.services;

import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.structured.FindServersRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.FindServersResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.GetEndpointsRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.GetEndpointsResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.RegisterServerRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.RegisterServerResponse;

public interface DiscoveryServiceSet {

    default void onFindServers(
        ServiceRequest<FindServersRequest, FindServersResponse> serviceRequest) throws UaException {

        serviceRequest.setServiceFault(StatusCodes.Bad_ServiceUnsupported);
    }

    default void onGetEndpoints(
        ServiceRequest<GetEndpointsRequest, GetEndpointsResponse> serviceRequest) throws UaException {

        serviceRequest.setServiceFault(StatusCodes.Bad_ServiceUnsupported);
    }

    default void onRegisterServer(
        ServiceRequest<RegisterServerRequest, RegisterServerResponse> serviceRequest) throws UaException {

        serviceRequest.setServiceFault(StatusCodes.Bad_ServiceUnsupported);
    }

}
