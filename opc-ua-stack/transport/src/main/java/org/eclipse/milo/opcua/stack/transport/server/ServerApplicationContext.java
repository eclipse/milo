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

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.stack.core.encoding.EncodingContext;
import org.eclipse.milo.opcua.stack.core.security.CertificateManager;
import org.eclipse.milo.opcua.stack.core.security.CertificateValidator;
import org.eclipse.milo.opcua.stack.core.types.UaRequestMessageType;
import org.eclipse.milo.opcua.stack.core.types.UaResponseMessageType;
import org.eclipse.milo.opcua.stack.core.types.structured.EndpointDescription;

public interface ServerApplicationContext {

    List<EndpointDescription> getEndpointDescriptions();

    EncodingContext getEncodingContext();

    CertificateManager getCertificateManager();

    CertificateValidator getCertificateValidator();

    Long getNextSecureChannelId();

    Long getNextSecureChannelTokenId();

    CompletableFuture<UaResponseMessageType> handleServiceRequest(
        ServiceRequestContext context,
        UaRequestMessageType requestMessage
    );

}
