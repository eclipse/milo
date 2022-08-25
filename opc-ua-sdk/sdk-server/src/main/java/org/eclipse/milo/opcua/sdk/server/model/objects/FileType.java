package org.eclipse.milo.opcua.sdk.server.model.objects;

import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.core.nodes.MethodNode;
import org.eclipse.milo.opcua.sdk.server.model.variables.PropertyType;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.ULong;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part20/4.2.1">https://reference.opcfoundation.org/v105/Core/docs/Part20/4.2.1</a>
 */
public interface FileType extends BaseObjectType {
    QualifiedProperty<ULong> SIZE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "Size",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=9"),
        -1,
        ULong.class
    );

    QualifiedProperty<Boolean> WRITABLE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "Writable",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=1"),
        -1,
        Boolean.class
    );

    QualifiedProperty<Boolean> USER_WRITABLE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "UserWritable",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=1"),
        -1,
        Boolean.class
    );

    QualifiedProperty<UShort> OPEN_COUNT = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "OpenCount",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=5"),
        -1,
        UShort.class
    );

    QualifiedProperty<String> MIME_TYPE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "MimeType",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=12"),
        -1,
        String.class
    );

    QualifiedProperty<UInteger> MAX_BYTE_STRING_LENGTH = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "MaxByteStringLength",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=7"),
        -1,
        UInteger.class
    );

    QualifiedProperty<DateTime> LAST_MODIFIED_TIME = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "LastModifiedTime",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=13"),
        -1,
        DateTime.class
    );

    ULong getSize();

    void setSize(ULong value);

    PropertyType getSizeNode();

    Boolean getWritable();

    void setWritable(Boolean value);

    PropertyType getWritableNode();

    Boolean getUserWritable();

    void setUserWritable(Boolean value);

    PropertyType getUserWritableNode();

    UShort getOpenCount();

    void setOpenCount(UShort value);

    PropertyType getOpenCountNode();

    String getMimeType();

    void setMimeType(String value);

    PropertyType getMimeTypeNode();

    UInteger getMaxByteStringLength();

    void setMaxByteStringLength(UInteger value);

    PropertyType getMaxByteStringLengthNode();

    DateTime getLastModifiedTime();

    void setLastModifiedTime(DateTime value);

    PropertyType getLastModifiedTimeNode();

    MethodNode getOpenMethodNode();

    MethodNode getCloseMethodNode();

    MethodNode getReadMethodNode();

    MethodNode getWriteMethodNode();

    MethodNode getGetPositionMethodNode();

    MethodNode getSetPositionMethodNode();
}
