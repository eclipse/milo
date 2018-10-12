package org.eclipse.milo.opcua.sdk.client.api.nodes.snapshots;

import java.util.EnumSet;

import org.eclipse.milo.opcua.sdk.client.api.nodes.Node;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;

public interface NodeSnapshot {

    /**
     * Get the NodeId attribute.
     *
     * @return the NodeId attribute.
     */
    NodeId getNodeId();

    /**
     * Get the NodeClass attribute.
     *
     * @return the NodeClass attribute.
     */
    NodeClass getNodeClass();

    /**
     * Get the BrowseName attribute.
     *
     * @return the BrowseName attribute.
     */
    QualifiedName getBrowseName();

    /**
     * Get the DisplayName attribute.
     *
     * @return the DisplayName attribute.
     */
    LocalizedText getDisplayName();

    /**
     * Get the Description attribute.
     *
     * @return the Description attribute, if present.
     */
    LocalizedText getDescription();

    /**
     * Get the WriteMask attribute.
     *
     * @return the WriteMask attribute, if present.
     */
    UInteger getWriteMask();

    /**
     * Get the WriteMask attribute.
     *
     * @return the WriteMask attribute, if present.
     */
    UInteger getUserWriteMask();

    /**
     * Convert this snapshot to a "live" {@link Node} instance.
     *
     * @return a "live" {@link Node} instance.
     */
    Node live();

    /**
     * Refresh all attribute values for this Node and return a new snapshot.
     *
     * @return a new {@link NodeSnapshot} of this Node with refreshed attribute values.
     */
    NodeSnapshot refresh();

    /**
     * Refresh the specified attribute values for this Node and return a new snapshot.
     *
     * @param attributes the {@link AttributeId}s of the attributes to refresh.
     * @return a new {@link NodeSnapshot} of this Node with refreshed attribute values.
     */
    NodeSnapshot refresh(EnumSet<AttributeId> attributes);

}
