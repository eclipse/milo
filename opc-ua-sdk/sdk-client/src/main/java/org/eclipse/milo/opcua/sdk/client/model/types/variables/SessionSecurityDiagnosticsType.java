/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.client.model.types.variables;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MessageSecurityMode;

public interface SessionSecurityDiagnosticsType extends BaseDataVariableType {
    CompletableFuture<? extends BaseDataVariableType> getSessionIdNode();

    CompletableFuture<NodeId> getSessionId();

    CompletableFuture<StatusCode> setSessionId(NodeId value);

    CompletableFuture<? extends BaseDataVariableType> getClientUserIdOfSessionNode();

    CompletableFuture<String> getClientUserIdOfSession();

    CompletableFuture<StatusCode> setClientUserIdOfSession(String value);

    CompletableFuture<? extends BaseDataVariableType> getClientUserIdHistoryNode();

    CompletableFuture<String[]> getClientUserIdHistory();

    CompletableFuture<StatusCode> setClientUserIdHistory(String[] value);

    CompletableFuture<? extends BaseDataVariableType> getAuthenticationMechanismNode();

    CompletableFuture<String> getAuthenticationMechanism();

    CompletableFuture<StatusCode> setAuthenticationMechanism(String value);

    CompletableFuture<? extends BaseDataVariableType> getEncodingNode();

    CompletableFuture<String> getEncoding();

    CompletableFuture<StatusCode> setEncoding(String value);

    CompletableFuture<? extends BaseDataVariableType> getTransportProtocolNode();

    CompletableFuture<String> getTransportProtocol();

    CompletableFuture<StatusCode> setTransportProtocol(String value);

    CompletableFuture<? extends BaseDataVariableType> getSecurityModeNode();

    CompletableFuture<MessageSecurityMode> getSecurityMode();

    CompletableFuture<StatusCode> setSecurityMode(MessageSecurityMode value);

    CompletableFuture<? extends BaseDataVariableType> getSecurityPolicyUriNode();

    CompletableFuture<String> getSecurityPolicyUri();

    CompletableFuture<StatusCode> setSecurityPolicyUri(String value);

    CompletableFuture<? extends BaseDataVariableType> getClientCertificateNode();

    CompletableFuture<ByteString> getClientCertificate();

    CompletableFuture<StatusCode> setClientCertificate(ByteString value);
}
