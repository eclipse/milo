package org.eclipse.milo.opcua.sdk.server.model.objects;

import org.eclipse.milo.opcua.sdk.core.nodes.MethodNode;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part17/6.3.1">https://reference.opcfoundation.org/v105/Core/docs/Part17/6.3.1</a>
 */
public interface AliasNameCategoryType extends FolderType {
    MethodNode getFindAliasMethodNode();
}
