package org.eclipse.milo.opcua.sdk.server.model.variables;

import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part8/5.3.3/#5.3.3.2">https://reference.opcfoundation.org/v105/Core/docs/Part8/5.3.3/#5.3.3.2</a>
 */
public interface TwoStateDiscreteType extends DiscreteItemType {
    QualifiedProperty<LocalizedText> FALSE_STATE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "FalseState",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=21"),
        -1,
        LocalizedText.class
    );

    QualifiedProperty<LocalizedText> TRUE_STATE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "TrueState",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=21"),
        -1,
        LocalizedText.class
    );

    LocalizedText getFalseState();

    void setFalseState(LocalizedText value);

    PropertyType getFalseStateNode();

    LocalizedText getTrueState();

    void setTrueState(LocalizedText value);

    PropertyType getTrueStateNode();
}
