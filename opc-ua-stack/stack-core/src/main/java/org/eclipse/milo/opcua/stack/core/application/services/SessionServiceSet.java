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
import org.eclipse.milo.opcua.stack.core.types.structured.ActivateSessionRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.ActivateSessionResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.CancelRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.CancelResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.CloseSessionRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.CloseSessionResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.CreateSessionRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.CreateSessionResponse;

public interface SessionServiceSet {

    default void onCreateSession(
        ServiceRequest<CreateSessionRequest, CreateSessionResponse> serviceRequest) throws UaException {

        serviceRequest.setServiceFault(StatusCodes.Bad_ServiceUnsupported);
    }

    default void onActivateSession(
        ServiceRequest<ActivateSessionRequest, ActivateSessionResponse> serviceRequest) throws UaException {

        serviceRequest.setServiceFault(StatusCodes.Bad_ServiceUnsupported);
    }

    default void onCloseSession(
        ServiceRequest<CloseSessionRequest, CloseSessionResponse> serviceRequest) throws UaException {

        serviceRequest.setServiceFault(StatusCodes.Bad_ServiceUnsupported);
    }

    default void onCancel(ServiceRequest<CancelRequest, CancelResponse> serviceRequest) throws UaException {
        serviceRequest.setServiceFault(StatusCodes.Bad_ServiceUnsupported);
    }

}
