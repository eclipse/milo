package org.eclipse.milo.opcua.sdk.server.servicesets;

import org.eclipse.milo.opcua.sdk.core.Reference;
import org.eclipse.milo.opcua.sdk.server.UaNodeManager;
import org.eclipse.milo.opcua.sdk.server.nodes.UaFolderNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;
import org.eclipse.milo.opcua.sdk.server.nodes.filters.AttributeFilter;
import org.eclipse.milo.opcua.sdk.server.nodes.filters.AttributeFilterContext;
import org.eclipse.milo.opcua.sdk.test.AbstractClientServerTest;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.NodeIds;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.structured.PermissionType;
import org.eclipse.milo.opcua.stack.core.types.structured.RolePermissionType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BrowsePermissionsTest extends AbstractClientServerTest {

    @BeforeEach
    void setUpNodes() {
        testNamespace.configure(this::setUpBrowsePermissionsNodes);
    }

    @Test
    void testBrowsePermissions() {
        // TODO
    }

    private void setUpBrowsePermissionsNodes(UaNodeContext nodeContext, UaNodeManager nodeManager) {
        NodeId roleA = newNodeId("RoleA");
        NodeId roleB = newNodeId("RoleB");

        RolePermissionType roleAPermissions = new RolePermissionType(
            roleA,
            PermissionType.of(
                PermissionType.Field.Browse,
                PermissionType.Field.ReadRolePermissions,
                PermissionType.Field.Read)
        );

        RolePermissionType roleBPermissions = new RolePermissionType(
            roleB,
            PermissionType.of(
                PermissionType.Field.Browse,
                PermissionType.Field.ReadRolePermissions,
                PermissionType.Field.Read)
        );

        var folder = new UaFolderNode(
            nodeContext,
            newNodeId("BrowsePermissionsTest"),
            newQualifiedName("BrowsePermissionsTest"),
            LocalizedText.english("BrowsePermissionsTest")
        );
        folder.addReference(new Reference(
            folder.getNodeId(),
            NodeIds.HasComponent,
            NodeIds.ObjectsFolder.expanded(),
            false
        ));
        nodeManager.addNode(folder);

        var roleAFolder = new UaFolderNode(
            nodeContext,
            newNodeId("RoleA"),
            newQualifiedName("RoleA"),
            LocalizedText.english("RoleA")
        );

        roleAFolder.getFilterChain().addLast(new AttributeFilter() {
            @Override
            public Object getAttribute(AttributeFilterContext ctx, AttributeId attributeId) {
                if (attributeId == AttributeId.RolePermissions || attributeId == AttributeId.UserRolePermissions) {
                    return new RolePermissionType[]{roleAPermissions};
                } else {
                    return AttributeFilter.super.getAttribute(ctx, attributeId);
                }
            }
        });

        folder.addOrganizes(roleAFolder);

        var roleBFolder = new UaFolderNode(
            nodeContext,
            newNodeId("RoleB"),
            newQualifiedName("RoleB"),
            LocalizedText.english("RoleB")
        );

        roleBFolder.getFilterChain().addLast(new AttributeFilter() {
            @Override
            public Object getAttribute(AttributeFilterContext ctx, AttributeId attributeId) {
                if (attributeId == AttributeId.RolePermissions || attributeId == AttributeId.UserRolePermissions) {
                    return new RolePermissionType[]{roleBPermissions};
                } else {
                    return AttributeFilter.super.getAttribute(ctx, attributeId);
                }
            }
        });

        folder.addOrganizes(roleBFolder);
    }

}
