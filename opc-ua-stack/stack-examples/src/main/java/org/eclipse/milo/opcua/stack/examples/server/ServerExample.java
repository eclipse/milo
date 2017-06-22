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

package org.eclipse.milo.opcua.stack.examples.server;

import java.io.File;
import java.security.KeyPair;
import java.security.cert.X509Certificate;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import org.eclipse.milo.opcua.stack.core.application.CertificateManager;
import org.eclipse.milo.opcua.stack.core.application.CertificateValidator;
import org.eclipse.milo.opcua.stack.core.application.DefaultCertificateManager;
import org.eclipse.milo.opcua.stack.core.application.DefaultCertificateValidator;
import org.eclipse.milo.opcua.stack.core.security.SecurityPolicy;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MessageSecurityMode;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadValueId;
import org.eclipse.milo.opcua.stack.core.types.structured.ResponseHeader;
import org.eclipse.milo.opcua.stack.server.config.UaTcpStackServerConfig;
import org.eclipse.milo.opcua.stack.server.tcp.UaTcpStackServer;

import static org.eclipse.milo.opcua.stack.core.util.ConversionUtil.a;
import static org.eclipse.milo.opcua.stack.core.util.ConversionUtil.l;

public class ServerExample {

    private final UaTcpStackServer server;

    public ServerExample(X509Certificate certificate, KeyPair keyPair) throws Exception {
        File securityDir = new File("./security/");

        if (!securityDir.exists() && !securityDir.mkdirs()) {
            throw new Exception("unable to create security directory");
        }

        CertificateManager certificateManager = new DefaultCertificateManager(keyPair, certificate);
        CertificateValidator certificateValidator = new DefaultCertificateValidator(securityDir);

        UaTcpStackServerConfig config = UaTcpStackServerConfig.builder()
            .setServerName("example")
            .setApplicationName(LocalizedText.english("Stack Example Server"))
            .setApplicationUri(String.format("urn:example-server:%s", UUID.randomUUID()))
            .setCertificateManager(certificateManager)
            .setCertificateValidator(certificateValidator)
            .build();

        server = new UaTcpStackServer(config);

        server.addEndpoint(
            "opc.tcp://localhost:12685/example",
            null,
            certificate,
            SecurityPolicy.None,
            MessageSecurityMode.None
        );

        server.addEndpoint(
            "opc.tcp://localhost:12685/example",
            null,
            certificate,
            SecurityPolicy.Basic128Rsa15,
            MessageSecurityMode.SignAndEncrypt
        );

        server.addRequestHandler(ReadRequest.class, service -> {
            ReadRequest request = service.getRequest();

            ResponseHeader header = new ResponseHeader(
                DateTime.now(),
                request.getRequestHeader().getRequestHandle(),
                StatusCode.GOOD,
                null,
                null,
                null
            );

            List<ReadValueId> nodesToRead = l(request.getNodesToRead());
            List<DataValue> results = Collections.nCopies(nodesToRead.size(), new DataValue(42));

            ReadResponse response = new ReadResponse(header, a(results, DataValue.class), null);

            service.setResponse(response);
        });
    }

    public void startup() throws ExecutionException, InterruptedException {
        server.startup().get();
    }

    public void shutdown() throws ExecutionException, InterruptedException {
        server.shutdown().get();
    }

}
