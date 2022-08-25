package org.eclipse.milo.opcua.sdk.server.model.objects;

import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.core.nodes.MethodNode;
import org.eclipse.milo.opcua.sdk.server.model.variables.PropertyType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.structured.PriorityMappingEntryType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part22/5.5.2/#5.5.2.2">https://reference.opcfoundation.org/v105/Core/docs/Part22/5.5.2/#5.5.2.2</a>
 */
public interface PriorityMappingTableType extends BaseObjectType {
    QualifiedProperty<PriorityMappingEntryType[]> PRIORITY_MAPPPING_ENTRIES = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "PriorityMapppingEntries",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=25220"),
        1,
        PriorityMappingEntryType[].class
    );

    PriorityMappingEntryType[] getPriorityMapppingEntries();

    void setPriorityMapppingEntries(PriorityMappingEntryType[] value);

    PropertyType getPriorityMapppingEntriesNode();

    MethodNode getAddPriorityMappingEntryMethodNode();

    MethodNode getDeletePriorityMappingEntryMethodNode();
}
