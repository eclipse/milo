package org.eclipse.milo.opcua.stack.transport.server;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

import org.eclipse.milo.opcua.stack.core.encoding.EncodingContext;
import org.eclipse.milo.opcua.stack.core.security.CertificateManager;
import org.eclipse.milo.opcua.stack.core.security.CertificateValidator;
import org.eclipse.milo.opcua.stack.core.types.UaRequestMessageType;
import org.eclipse.milo.opcua.stack.core.types.UaResponseMessageType;
import org.eclipse.milo.opcua.stack.core.types.structured.EndpointDescription;

public interface ServerApplication {

    List<EndpointDescription> getEndpointDescriptions();

    EncodingContext getEncodingContext();

    CertificateManager getCertificateManager();

    CertificateValidator getCertificateValidator();

    Long getNextSecureChannelId();

    Long getNextSecureChannelTokenId();

    ExecutorService getExecutor();

    CompletableFuture<UaResponseMessageType> handleServiceRequest(
        ServiceRequestContext context,
        UaRequestMessageType requestMessage
    );

}
