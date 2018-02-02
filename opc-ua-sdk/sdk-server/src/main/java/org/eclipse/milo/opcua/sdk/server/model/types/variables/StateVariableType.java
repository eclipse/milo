package org.eclipse.milo.opcua.sdk.server.model.types.variables;

import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.sdk.core.model.QualifiedProperty;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public interface StateVariableType extends BaseDataVariableType {
    QualifiedProperty<Object> ID = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "Id",
        NodeId.parse("ns=0;i=24"),
        ValueRanks.Scalar,
        Object.class
    );

    QualifiedProperty<QualifiedName> NAME = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "Name",
        NodeId.parse("ns=0;i=20"),
        ValueRanks.Scalar,
        QualifiedName.class
    );

    QualifiedProperty<UInteger> NUMBER = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "Number",
        NodeId.parse("ns=0;i=7"),
        ValueRanks.Scalar,
        UInteger.class
    );

    QualifiedProperty<LocalizedText> EFFECTIVE_DISPLAY_NAME = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "EffectiveDisplayName",
        NodeId.parse("ns=0;i=21"),
        ValueRanks.Scalar,
        LocalizedText.class
    );

    PropertyType getIdNode();

    Object getId();

    void setId(Object value);

    PropertyType getNameNode();

    QualifiedName getName();

    void setName(QualifiedName value);

    PropertyType getNumberNode();

    UInteger getNumber();

    void setNumber(UInteger value);

    PropertyType getEffectiveDisplayNameNode();

    LocalizedText getEffectiveDisplayName();

    void setEffectiveDisplayName(LocalizedText value);
}
