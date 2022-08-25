package org.eclipse.milo.opcua.sdk.server.model.objects;

import org.eclipse.milo.opcua.sdk.core.nodes.MethodNode;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part20/4.3.1">https://reference.opcfoundation.org/v105/Core/docs/Part20/4.3.1</a>
 */
public interface FileDirectoryType extends FolderType {
    MethodNode getCreateDirectoryMethodNode();

    MethodNode getCreateFileMethodNode();

    MethodNode getDeleteFileSystemObjectMethodNode();

    MethodNode getMoveOrCopyMethodNode();
}
