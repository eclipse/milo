/*
 * Copyright (c) 2022 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.transport.server;

import org.eclipse.milo.opcua.stack.core.channel.SecureChannel;
import org.eclipse.milo.opcua.stack.core.types.UaRequestMessageType;

public class ServiceRequest {

    private final String endpointUrl;
    private final SecureChannel secureChannel;
    private final long requestId;
    private final UaRequestMessageType requestMessage;

    public ServiceRequest(
        String endpointUrl,
        SecureChannel secureChannel,
        long requestId,
        UaRequestMessageType requestMessage
    ) {

        this.endpointUrl = endpointUrl;
        this.secureChannel = secureChannel;
        this.requestId = requestId;
        this.requestMessage = requestMessage;
    }

}
