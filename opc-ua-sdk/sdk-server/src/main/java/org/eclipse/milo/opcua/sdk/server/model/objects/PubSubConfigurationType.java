package org.eclipse.milo.opcua.sdk.server.model.objects;

import org.eclipse.milo.opcua.sdk.core.nodes.MethodNode;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part14/9.1.3/#9.1.3.7.1">https://reference.opcfoundation.org/v105/Core/docs/Part14/9.1.3/#9.1.3.7.1</a>
 */
public interface PubSubConfigurationType extends FileType {
    MethodNode getReserveIdsMethodNode();

    MethodNode getCloseAndUpdateMethodNode();
}
