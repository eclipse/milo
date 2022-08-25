package org.eclipse.milo.opcua.sdk.server.model.objects;

import org.eclipse.milo.opcua.sdk.core.nodes.MethodNode;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part14/9.1.9/#9.1.9.4.1">https://reference.opcfoundation.org/v105/Core/docs/Part14/9.1.9/#9.1.9.4.1</a>
 */
public interface SubscribedDataSetFolderType extends FolderType {
    MethodNode getAddSubscribedDataSetMethodNode();

    MethodNode getRemoveSubscribedDataSetMethodNode();

    MethodNode getAddDataSetFolderMethodNode();

    MethodNode getRemoveDataSetFolderMethodNode();
}
