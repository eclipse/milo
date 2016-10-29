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

package org.eclipse.milo.opcua.stack.core;

import org.eclipse.milo.opcua.stack.core.types.structured.ServiceFault;

public class UaServiceFaultException extends UaException {

    private final ServiceFault serviceFault;

    public UaServiceFaultException(ServiceFault serviceFault) {
        super(serviceFault.getResponseHeader().getServiceResult());

        this.serviceFault = serviceFault;
    }

    public UaServiceFaultException(ServiceFault serviceFault, String message) {
        super(serviceFault.getResponseHeader().getServiceResult(), message);

        this.serviceFault = serviceFault;
    }

    public ServiceFault getServiceFault() {
        return serviceFault;
    }

}
