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

import java.util.concurrent.atomic.LongAdder;

import org.eclipse.milo.opcua.stack.core.types.structured.ServiceCounterDataType;
import org.eclipse.milo.opcua.stack.server.services.ServiceRequest;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;

public class ServiceCounter {

    private final LongAdder totalCount = new LongAdder();
    private final LongAdder errorCount = new LongAdder();

    public void record(ServiceRequest service) {
        service.getFuture().whenComplete((r, ex) -> {
            totalCount.increment();

            if (ex != null) {
                errorCount.increment();
            }
        });
    }

    public ServiceCounterDataType getServiceCounter() {
        return new ServiceCounterDataType(
            uint(totalCount.sum()),
            uint(errorCount.sum())
        );
    }

}
