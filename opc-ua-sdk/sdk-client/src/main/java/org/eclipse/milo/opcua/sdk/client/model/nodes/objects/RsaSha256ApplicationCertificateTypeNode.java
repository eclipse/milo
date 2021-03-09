package org.eclipse.milo.opcua.sdk.client.model.nodes.objects;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.RsaSha256ApplicationCertificateType;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;

public class RsaSha256ApplicationCertificateTypeNode extends ApplicationCertificateTypeNode implements RsaSha256ApplicationCertificateType {
    public RsaSha256ApplicationCertificateTypeNode(OpcUaClient client, NodeId nodeId,
                                                   NodeClass nodeClass, QualifiedName browseName, LocalizedText displayName,
                                                   LocalizedText description, UInteger writeMask, UInteger userWriteMask, UByte eventNotifier) {
        super(client, nodeId, nodeClass, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }
}
