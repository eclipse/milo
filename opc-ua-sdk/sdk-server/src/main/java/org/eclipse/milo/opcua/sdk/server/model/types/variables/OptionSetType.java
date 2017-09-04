package org.eclipse.milo.opcua.sdk.server.model.types.variables;

import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.sdk.core.model.QualifiedProperty;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public interface OptionSetType extends BaseDataVariableType {
    QualifiedProperty<LocalizedText[]> OPTION_SET_VALUES = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "OptionSetValues",
        NodeId.parse("ns=0;i=21"),
        ValueRanks.OneDimension,
        LocalizedText[].class
    );

    QualifiedProperty<Boolean[]> BIT_MASK = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "BitMask",
        NodeId.parse("ns=0;i=1"),
        ValueRanks.OneDimension,
        Boolean[].class
    );

    PropertyType getOptionSetValuesNode();

    LocalizedText[] getOptionSetValues();

    void setOptionSetValues(LocalizedText[] value);

    PropertyType getBitMaskNode();

    Boolean[] getBitMask();

    void setBitMask(Boolean[] value);
}
