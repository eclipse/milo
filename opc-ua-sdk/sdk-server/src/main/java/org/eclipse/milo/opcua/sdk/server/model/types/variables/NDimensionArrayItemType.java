package org.eclipse.milo.opcua.sdk.server.model.types.variables;

import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.sdk.core.model.QualifiedProperty;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.structured.AxisInformation;

public interface NDimensionArrayItemType extends ArrayItemType {
    QualifiedProperty<AxisInformation[]> AXIS_DEFINITION = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "AxisDefinition",
        NodeId.parse("ns=0;i=12079"),
        ValueRanks.OneDimension,
        AxisInformation[].class
    );

    PropertyType getAxisDefinitionNode();

    AxisInformation[] getAxisDefinition();

    void setAxisDefinition(AxisInformation[] value);
}
