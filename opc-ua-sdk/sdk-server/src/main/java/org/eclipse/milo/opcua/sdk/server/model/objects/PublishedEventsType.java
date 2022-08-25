package org.eclipse.milo.opcua.sdk.server.model.objects;

import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.core.nodes.MethodNode;
import org.eclipse.milo.opcua.sdk.server.model.variables.PropertyType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.structured.ContentFilter;
import org.eclipse.milo.opcua.stack.core.types.structured.SimpleAttributeOperand;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part14/9.1.4/#9.1.4.4.1">https://reference.opcfoundation.org/v105/Core/docs/Part14/9.1.4/#9.1.4.4.1</a>
 */
public interface PublishedEventsType extends PublishedDataSetType {
    QualifiedProperty<NodeId> PUB_SUB_EVENT_NOTIFIER = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "EventNotifier",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=17"),
        -1,
        NodeId.class
    );

    QualifiedProperty<SimpleAttributeOperand[]> SELECTED_FIELDS = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "SelectedFields",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=601"),
        1,
        SimpleAttributeOperand[].class
    );

    QualifiedProperty<ContentFilter> FILTER = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "Filter",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=586"),
        -1,
        ContentFilter.class
    );

    NodeId getPubSubEventNotifier();

    void setPubSubEventNotifier(NodeId value);

    PropertyType getPubSubEventNotifierNode();

    SimpleAttributeOperand[] getSelectedFields();

    void setSelectedFields(SimpleAttributeOperand[] value);

    PropertyType getSelectedFieldsNode();

    ContentFilter getFilter();

    void setFilter(ContentFilter value);

    PropertyType getFilterNode();

    MethodNode getModifyFieldSelectionMethodNode();
}
