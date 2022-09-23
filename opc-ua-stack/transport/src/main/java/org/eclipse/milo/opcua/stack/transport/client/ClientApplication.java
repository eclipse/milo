/*
 * Copyright (c) 2022 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.transport.client;

import java.security.KeyPair;
import java.security.cert.X509Certificate;
import java.util.Optional;

import org.eclipse.milo.opcua.stack.core.encoding.EncodingContext;
import org.eclipse.milo.opcua.stack.core.security.CertificateValidator;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.structured.EndpointDescription;

public interface ClientApplication {

    EndpointDescription getEndpoint();

    Optional<KeyPair> getKeyPair();

    Optional<X509Certificate> getCertificate();

    Optional<X509Certificate[]> getCertificateChain();

    CertificateValidator getCertificateValidator();

    EncodingContext getEncodingContext();

    UInteger getRequestTimeout();

}
