package org.eclipse.milo.opcua.sdk.server.model.objects;

import org.eclipse.milo.opcua.sdk.core.nodes.MethodNode;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part5/6.3.12">https://reference.opcfoundation.org/v105/Core/docs/Part5/6.3.12</a>
 */
public interface AddressSpaceFileType extends FileType {
    MethodNode getExportNamespaceMethodNode();
}
