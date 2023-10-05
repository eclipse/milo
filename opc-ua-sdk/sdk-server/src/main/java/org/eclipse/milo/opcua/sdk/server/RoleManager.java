package org.eclipse.milo.opcua.sdk.server;

import java.util.List;

import org.eclipse.milo.opcua.sdk.server.identity.Identity;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public interface RoleManager {

    List<NodeId> getRoleIds(Identity identity);

}
