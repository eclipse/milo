package org.eclipse.milo.opcua.sdk.server.model.types.objects;

import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.sdk.core.model.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.server.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.structured.ModelChangeStructureDataType;

public interface GeneralModelChangeEventType extends BaseModelChangeEventType {
    QualifiedProperty<ModelChangeStructureDataType[]> CHANGES = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "Changes",
        NodeId.parse("ns=0;i=877"),
        ValueRanks.OneDimension,
        ModelChangeStructureDataType[].class
    );

    PropertyType getChangesNode();

    ModelChangeStructureDataType[] getChanges();

    void setChanges(ModelChangeStructureDataType[] value);
}
