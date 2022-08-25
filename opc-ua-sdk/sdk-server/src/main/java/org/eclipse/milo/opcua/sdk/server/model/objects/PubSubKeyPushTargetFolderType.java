package org.eclipse.milo.opcua.sdk.server.model.objects;

import org.eclipse.milo.opcua.sdk.core.nodes.MethodNode;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part14/8.7.1">https://reference.opcfoundation.org/v105/Core/docs/Part14/8.7.1</a>
 */
public interface PubSubKeyPushTargetFolderType extends FolderType {
    MethodNode getAddPushTargetMethodNode();

    MethodNode getRemovePushTargetMethodNode();

    MethodNode getAddPushTargetFolderMethodNode();

    MethodNode getRemovePushTargetFolderMethodNode();
}
