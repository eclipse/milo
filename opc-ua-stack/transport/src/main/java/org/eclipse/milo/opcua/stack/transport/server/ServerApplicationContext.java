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

    /**
     * Get the {@link EndpointDescription}s the server is providing.
     *
     * @return the {@link EndpointDescription}s the server is providing.
     */
    List<EndpointDescription> getEndpointDescriptions();

    /**
     * Get the server's {@link CertificateManager}.
     *
     * @return the server's {@link CertificateManager}.
     */
    CertificateManager getCertificateManager();

    /**
     * Get the server's {@link CertificateValidator}.
     *
     * @return the server's {@link CertificateValidator}.
     */
    CertificateValidator getCertificateValidator();

    /**
     * Get the server's static {@link EncodingContext}.
     *
     * @return the server's static {@link EncodingContext}.
     */
    EncodingContext getEncodingContext();

    /**
     * Get the next unique secure channel id to assign to a secure channel.
     *
     * @return the next unique secure channel id to assign to a secure channel.
     */
    Long getNextSecureChannelId();

    /**
     * Get the next unique secure channel token id to assign when a secure channel is renewed.
     *
     * @return the next unique secure channel token id to assign when a secure channel is renewed.
     */
    Long getNextSecureChannelTokenId();

    /**
     * Handle an inbound service request, returning a {@link CompletableFuture} that completes
     * with the service response.
     *
     * @param context        the {@link ServiceRequestContext}.
     * @param requestMessage the {@link UaRequestMessageType} to handle.
     * @return a {@link CompletableFuture} that completes successfully with the service result, or
     * completes exceptionally if there was a service fault.
     */
    CompletableFuture<UaResponseMessageType> handleServiceRequest(
        ServiceRequestContext context,
        UaRequestMessageType requestMessage
    );

}
