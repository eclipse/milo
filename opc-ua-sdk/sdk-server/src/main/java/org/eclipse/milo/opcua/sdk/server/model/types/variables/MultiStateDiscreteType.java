package org.eclipse.milo.opcua.sdk.server.model.types.variables;

import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.sdk.core.model.QualifiedProperty;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public interface MultiStateDiscreteType extends DiscreteItemType {
    QualifiedProperty<LocalizedText[]> ENUM_STRINGS = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "EnumStrings",
        NodeId.parse("ns=0;i=21"),
        ValueRanks.OneDimension,
        LocalizedText[].class
    );

    PropertyType getEnumStringsNode();

    LocalizedText[] getEnumStrings();

    void setEnumStrings(LocalizedText[] value);
}
