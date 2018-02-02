package org.eclipse.milo.opcua.sdk.server.model.types.objects;

import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.sdk.core.model.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.server.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.ULong;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;

public interface FileType extends BaseObjectType {
    QualifiedProperty<ULong> SIZE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "Size",
        NodeId.parse("ns=0;i=9"),
        ValueRanks.Scalar,
        ULong.class
    );

    QualifiedProperty<Boolean> WRITABLE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "Writable",
        NodeId.parse("ns=0;i=1"),
        ValueRanks.Scalar,
        Boolean.class
    );

    QualifiedProperty<Boolean> USER_WRITABLE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "UserWritable",
        NodeId.parse("ns=0;i=1"),
        ValueRanks.Scalar,
        Boolean.class
    );

    QualifiedProperty<UShort> OPEN_COUNT = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "OpenCount",
        NodeId.parse("ns=0;i=5"),
        ValueRanks.Scalar,
        UShort.class
    );

    QualifiedProperty<String> MIME_TYPE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "MimeType",
        NodeId.parse("ns=0;i=12"),
        ValueRanks.Scalar,
        String.class
    );

    PropertyType getSizeNode();

    ULong getSize();

    void setSize(ULong value);

    PropertyType getWritableNode();

    Boolean getWritable();

    void setWritable(Boolean value);

    PropertyType getUserWritableNode();

    Boolean getUserWritable();

    void setUserWritable(Boolean value);

    PropertyType getOpenCountNode();

    UShort getOpenCount();

    void setOpenCount(UShort value);

    PropertyType getMimeTypeNode();

    String getMimeType();

    void setMimeType(String value);
}
