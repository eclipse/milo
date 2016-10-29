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
import org.eclipse.milo.opcua.stack.core.types.structured.AddNodesRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.AddNodesResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.AddReferencesRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.AddReferencesResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteNodesRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteNodesResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteReferencesRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteReferencesResponse;

public interface NodeManagementServiceSet {

    default void onAddNodes(ServiceRequest<AddNodesRequest, AddNodesResponse> serviceRequest) throws UaException {
        serviceRequest.setServiceFault(StatusCodes.Bad_ServiceUnsupported);
    }

    default void onDeleteNodes(
        ServiceRequest<DeleteNodesRequest, DeleteNodesResponse> serviceRequest) throws UaException {

        serviceRequest.setServiceFault(StatusCodes.Bad_ServiceUnsupported);
    }

    default void onAddReferences(
        ServiceRequest<AddReferencesRequest, AddReferencesResponse> serviceRequest) throws UaException {

        serviceRequest.setServiceFault(StatusCodes.Bad_ServiceUnsupported);
    }

    default void onDeleteReferences(
        ServiceRequest<DeleteReferencesRequest, DeleteReferencesResponse> serviceRequest) throws UaException {

        serviceRequest.setServiceFault(StatusCodes.Bad_ServiceUnsupported);
    }

}
