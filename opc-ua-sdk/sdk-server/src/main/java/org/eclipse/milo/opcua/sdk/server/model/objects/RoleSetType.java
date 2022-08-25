package org.eclipse.milo.opcua.sdk.server.model.objects;

import org.eclipse.milo.opcua.sdk.core.nodes.MethodNode;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part18/4.2.1">https://reference.opcfoundation.org/v105/Core/docs/Part18/4.2.1</a>
 */
public interface RoleSetType extends BaseObjectType {
    MethodNode getAddRoleMethodNode();

    MethodNode getRemoveRoleMethodNode();
}
