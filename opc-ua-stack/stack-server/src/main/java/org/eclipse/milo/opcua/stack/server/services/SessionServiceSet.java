/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.server.services;

import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;

public interface SessionServiceSet {

    default void onCreateSession(ServiceRequest serviceRequest) throws UaException {
        serviceRequest.setServiceFault(StatusCodes.Bad_ServiceUnsupported);
    }

    default void onActivateSession(ServiceRequest serviceRequest) throws UaException {

        serviceRequest.setServiceFault(StatusCodes.Bad_ServiceUnsupported);
    }

    default void onCloseSession(ServiceRequest serviceRequest) throws UaException {

        serviceRequest.setServiceFault(StatusCodes.Bad_ServiceUnsupported);
    }

    default void onCancel(ServiceRequest serviceRequest) throws UaException {
        serviceRequest.setServiceFault(StatusCodes.Bad_ServiceUnsupported);
    }

}
