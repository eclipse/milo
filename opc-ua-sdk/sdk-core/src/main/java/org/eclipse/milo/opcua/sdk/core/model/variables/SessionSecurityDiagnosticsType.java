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

package org.eclipse.milo.opcua.sdk.core.model.variables;

import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MessageSecurityMode;


public interface SessionSecurityDiagnosticsType extends BaseDataVariableType {


    NodeId getSessionId();

    BaseDataVariableType getSessionIdNode();

    void setSessionId(NodeId value);

    String getClientUserIdOfSession();

    BaseDataVariableType getClientUserIdOfSessionNode();

    void setClientUserIdOfSession(String value);

    String[] getClientUserIdHistory();

    BaseDataVariableType getClientUserIdHistoryNode();

    void setClientUserIdHistory(String[] value);

    String getAuthenticationMechanism();

    BaseDataVariableType getAuthenticationMechanismNode();

    void setAuthenticationMechanism(String value);

    String getEncoding();

    BaseDataVariableType getEncodingNode();

    void setEncoding(String value);

    String getTransportProtocol();

    BaseDataVariableType getTransportProtocolNode();

    void setTransportProtocol(String value);

    MessageSecurityMode getSecurityMode();

    BaseDataVariableType getSecurityModeNode();

    void setSecurityMode(MessageSecurityMode value);

    String getSecurityPolicyUri();

    BaseDataVariableType getSecurityPolicyUriNode();

    void setSecurityPolicyUri(String value);

    ByteString getClientCertificate();

    BaseDataVariableType getClientCertificateNode();

    void setClientCertificate(ByteString value);
}
