package org.eclipse.milo.opcua.sdk.server.model.types.variables;

import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MessageSecurityMode;

public interface SessionSecurityDiagnosticsType extends BaseDataVariableType {
    BaseDataVariableType getSessionIdNode();

    NodeId getSessionId();

    void setSessionId(NodeId value);

    BaseDataVariableType getClientUserIdOfSessionNode();

    String getClientUserIdOfSession();

    void setClientUserIdOfSession(String value);

    BaseDataVariableType getClientUserIdHistoryNode();

    String[] getClientUserIdHistory();

    void setClientUserIdHistory(String[] value);

    BaseDataVariableType getAuthenticationMechanismNode();

    String getAuthenticationMechanism();

    void setAuthenticationMechanism(String value);

    BaseDataVariableType getEncodingNode();

    String getEncoding();

    void setEncoding(String value);

    BaseDataVariableType getTransportProtocolNode();

    String getTransportProtocol();

    void setTransportProtocol(String value);

    BaseDataVariableType getSecurityModeNode();

    MessageSecurityMode getSecurityMode();

    void setSecurityMode(MessageSecurityMode value);

    BaseDataVariableType getSecurityPolicyUriNode();

    String getSecurityPolicyUri();

    void setSecurityPolicyUri(String value);

    BaseDataVariableType getClientCertificateNode();

    ByteString getClientCertificate();

    void setClientCertificate(ByteString value);
}
