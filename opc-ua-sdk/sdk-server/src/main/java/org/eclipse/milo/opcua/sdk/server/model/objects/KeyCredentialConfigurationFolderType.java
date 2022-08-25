package org.eclipse.milo.opcua.sdk.server.model.objects;

import org.eclipse.milo.opcua.sdk.core.nodes.MethodNode;

public interface KeyCredentialConfigurationFolderType extends FolderType {
    MethodNode getCreateCredentialMethodNode();
}
