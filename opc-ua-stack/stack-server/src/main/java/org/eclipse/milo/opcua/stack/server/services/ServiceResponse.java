/*
 * Copyright (c) 2022 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.server.services;

import com.google.common.base.MoreObjects;
import com.google.common.base.MoreObjects.ToStringHelper;
import org.eclipse.milo.opcua.stack.core.types.UaRequestMessageType;
import org.eclipse.milo.opcua.stack.core.types.UaResponseMessageType;
import org.eclipse.milo.opcua.stack.core.types.structured.ServiceFault;

public class ServiceResponse {

    private final UaRequestMessageType request;
    private final UaResponseMessageType response;
    private final long requestId;
    private final boolean serviceFault;

    public ServiceResponse(UaRequestMessageType request, long requestId, UaResponseMessageType response) {
        this.request = request;
        this.requestId = requestId;
        this.response = response;
        this.serviceFault = false;
    }

    public ServiceResponse(UaRequestMessageType request, long requestId, ServiceFault serviceFault) {
        this.request = request;
        this.requestId = requestId;
        this.response = serviceFault;
        this.serviceFault = true;
    }

    public UaRequestMessageType getRequest() {
        return request;
    }

    public long getRequestId() {
        return requestId;
    }

    public UaResponseMessageType getResponse() {
        return response;
    }

    public boolean isServiceFault() {
        return serviceFault;
    }

    @Override
    public String toString() {
        ToStringHelper helper = MoreObjects.toStringHelper(this)
            .add("requestId", requestId)
            .add("request", request.getClass().getSimpleName())
            .add("response", response.getClass().getSimpleName());

        if (serviceFault) {
            helper.add("result", response.getResponseHeader().getServiceResult());
        }

        return helper.toString();
    }

}
