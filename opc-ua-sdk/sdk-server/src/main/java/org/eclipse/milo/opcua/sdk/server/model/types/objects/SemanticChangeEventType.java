package org.eclipse.milo.opcua.sdk.server.model.types.objects;

import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.sdk.core.model.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.server.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.structured.SemanticChangeStructureDataType;

public interface SemanticChangeEventType extends BaseModelChangeEventType {
    QualifiedProperty<SemanticChangeStructureDataType[]> CHANGES = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "Changes",
        NodeId.parse("ns=0;i=897"),
        ValueRanks.OneDimension,
        SemanticChangeStructureDataType[].class
    );

    PropertyType getChangesNode();

    SemanticChangeStructureDataType[] getChanges();

    void setChanges(SemanticChangeStructureDataType[] value);
}
