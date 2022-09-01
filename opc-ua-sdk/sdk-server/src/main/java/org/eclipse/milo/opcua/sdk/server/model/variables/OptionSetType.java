package org.eclipse.milo.opcua.sdk.server.model.variables;

import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part5/7.17">https://reference.opcfoundation.org/v105/Core/docs/Part5/7.17</a>
 */
public interface OptionSetType extends BaseDataVariableType {
    QualifiedProperty<LocalizedText[]> OPTION_SET_VALUES = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "OptionSetValues",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=21"),
        1,
        LocalizedText[].class
    );

    QualifiedProperty<Boolean[]> BIT_MASK = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "BitMask",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=1"),
        1,
        Boolean[].class
    );

    LocalizedText[] getOptionSetValues();

    void setOptionSetValues(LocalizedText[] value);

    PropertyType getOptionSetValuesNode();

    Boolean[] getBitMask();

    void setBitMask(Boolean[] value);

    PropertyType getBitMaskNode();
}