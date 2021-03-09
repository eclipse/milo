package org.eclipse.milo.opcua.sdk.client.model.nodes.objects;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.AuditCertificateUntrustedEventType;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;

public class AuditCertificateUntrustedEventTypeNode extends AuditCertificateEventTypeNode implements AuditCertificateUntrustedEventType {
    public AuditCertificateUntrustedEventTypeNode(OpcUaClient client, NodeId nodeId,
                                                  NodeClass nodeClass, QualifiedName browseName, LocalizedText displayName,
                                                  LocalizedText description, UInteger writeMask, UInteger userWriteMask, UByte eventNotifier) {
        super(client, nodeId, nodeClass, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }
}
