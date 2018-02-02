package org.eclipse.milo.opcua.sdk.server.model.types.variables;

import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.sdk.core.model.QualifiedProperty;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public interface DataItemType extends BaseDataVariableType {
    QualifiedProperty<String> DEFINITION = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "Definition",
        NodeId.parse("ns=0;i=12"),
        ValueRanks.Scalar,
        String.class
    );

    QualifiedProperty<Double> VALUE_PRECISION = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "ValuePrecision",
        NodeId.parse("ns=0;i=11"),
        ValueRanks.Scalar,
        Double.class
    );

    PropertyType getDefinitionNode();

    String getDefinition();

    void setDefinition(String value);

    PropertyType getValuePrecisionNode();

    Double getValuePrecision();

    void setValuePrecision(Double value);
}
