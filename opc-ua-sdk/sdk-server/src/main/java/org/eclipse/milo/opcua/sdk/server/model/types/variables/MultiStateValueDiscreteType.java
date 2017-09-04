package org.eclipse.milo.opcua.sdk.server.model.types.variables;

import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.sdk.core.model.QualifiedProperty;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.structured.EnumValueType;

public interface MultiStateValueDiscreteType extends DiscreteItemType {
    QualifiedProperty<EnumValueType[]> ENUM_VALUES = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "EnumValues",
        NodeId.parse("ns=0;i=7594"),
        ValueRanks.OneDimension,
        EnumValueType[].class
    );

    QualifiedProperty<LocalizedText> VALUE_AS_TEXT = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "ValueAsText",
        NodeId.parse("ns=0;i=21"),
        ValueRanks.Scalar,
        LocalizedText.class
    );

    PropertyType getEnumValuesNode();

    EnumValueType[] getEnumValues();

    void setEnumValues(EnumValueType[] value);

    PropertyType getValueAsTextNode();

    LocalizedText getValueAsText();

    void setValueAsText(LocalizedText value);
}
