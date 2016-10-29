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

import com.codahale.metrics.Counter;
import com.codahale.metrics.Timer;
import org.eclipse.milo.opcua.stack.core.application.services.ServiceRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.ServiceCounterDataType;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;

public class ServiceMetric {

    private final Timer requestTimer = new Timer();
    private final Counter errorCounter = new Counter();

    public void record(ServiceRequest<?, ?> service) {
        Timer.Context context = requestTimer.time();

        service.getFuture().whenComplete((r, ex) -> {
            context.stop();
            if (ex != null) errorCounter.inc();
        });
    }

    public Timer getRequestTimer() {
        return requestTimer;
    }

    public Counter getErrorCounter() {
        return errorCounter;
    }

    public ServiceCounterDataType getServiceCounter() {
        return new ServiceCounterDataType(
            uint(requestTimer.getCount()),
            uint(errorCounter.getCount()));
    }

}
