package org.eclipse.milo.opcua.sdk.server.model.objects;

import org.eclipse.milo.opcua.sdk.core.nodes.MethodNode;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part14/9.1.6/#9.1.6.9">https://reference.opcfoundation.org/v105/Core/docs/Part14/9.1.6/#9.1.6.9</a>
 */
public interface ReaderGroupType extends PubSubGroupType {
    PubSubDiagnosticsReaderGroupType getDiagnosticsNode();

    ReaderGroupTransportType getTransportSettingsNode();

    ReaderGroupMessageType getMessageSettingsNode();

    MethodNode getAddDataSetReaderMethodNode();

    MethodNode getRemoveDataSetReaderMethodNode();
}
