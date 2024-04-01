package org.eclipse.milo.opcua.sdk.server.servicesets.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.Session;
import org.eclipse.milo.opcua.sdk.server.servicesets.impl.AccessController.AccessControlContext.AccessControlAttributes;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.structured.RolePermissionType;

public class BrowseAccessController extends AccessController {

    public BrowseAccessController(OpcUaServer server) {
        super(server);
    }

    /**
     * Check if the current Session has Browse permission for the Nodes identified by
     * {@code nodeIds}.
     *
     * @param session the Session to check access for.
     * @param nodeIds the {@link NodeId}s of Nodes to check access for.
     * @return a List of {@link AccessCheckResult} indicating the access status for each
     *     {@link NodeId}.
     */
    public List<AccessCheckResult> checkBrowseAccess(Session session, List<NodeId> nodeIds) {
        var context = new DefaultAccessControlContext(server, session);

        return checkBrowsePermission(context, nodeIds);
    }

    static List<AccessCheckResult> checkBrowsePermission(AccessControlContext context, List<NodeId> nodeIds) {
        Map<NodeId, AccessControlAttributes> attributes =
            context.readAccessControlAttributes(nodeIds);

        // TODO AccessRestrictions might affect browse permissions.

        List<NodeId> roleIds = context.getRoleIds().orElse(null);

        var results = new ArrayList<AccessCheckResult>();

        for (NodeId nodeId : nodeIds) {
            if (roleIds != null) {
                // If non-null, there is a Session and Server has been configured with a
                // RoleManager that provides Identity to RoleId mappings, so we can proceed with
                // checking the RolePermissions and UserRolePermissions attributes.
                RolePermissionType[] userRolePermissions =
                    attributes.get(nodeId).userRolePermissions();

                if (userRolePermissions != null) {
                    boolean hasAccess = Stream.of(userRolePermissions)
                        .filter(rp -> roleIds.contains(rp.getRoleId()))
                        .anyMatch(rp -> rp.getPermissions().getBrowse());

                    results.add(hasAccess ? AccessCheckResult.ALLOWED : AccessCheckResult.DENIED);
                } else {
                    // Node not found or no RolePermissions/UserRolePermissions attribute.
                    results.add(AccessCheckResult.ALLOWED);
                }
            } else {
                // Server is not configured with a RoleManager.
                results.add(AccessCheckResult.ALLOWED);
            }
        }

        return results;
    }

}
