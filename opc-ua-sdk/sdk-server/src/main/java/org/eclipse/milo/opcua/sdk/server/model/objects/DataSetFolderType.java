package org.eclipse.milo.opcua.sdk.server.model.objects;

import org.eclipse.milo.opcua.sdk.core.nodes.MethodNode;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part14/9.1.4/#9.1.4.5.1">https://reference.opcfoundation.org/v105/Core/docs/Part14/9.1.4/#9.1.4.5.1</a>
 */
public interface DataSetFolderType extends FolderType {
    MethodNode getAddPublishedDataItemsMethodNode();

    MethodNode getAddPublishedEventsMethodNode();

    MethodNode getAddPublishedDataItemsTemplateMethodNode();

    MethodNode getAddPublishedEventsTemplateMethodNode();

    MethodNode getRemovePublishedDataSetMethodNode();

    MethodNode getAddDataSetFolderMethodNode();

    MethodNode getRemoveDataSetFolderMethodNode();
}
