package org.eclipse.milo.opcua.sdk.server.model.types.variables;

import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.sdk.core.model.QualifiedProperty;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.structured.AxisInformation;

public interface YArrayItemType extends ArrayItemType {
    QualifiedProperty<AxisInformation> X_AXIS_DEFINITION = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "XAxisDefinition",
        NodeId.parse("ns=0;i=12079"),
        ValueRanks.Scalar,
        AxisInformation.class
    );

    PropertyType getXAxisDefinitionNode();

    AxisInformation getXAxisDefinition();

    void setXAxisDefinition(AxisInformation value);
}
