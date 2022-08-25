package org.eclipse.milo.opcua.sdk.server.model.objects;

import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.server.model.variables.PropertyType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part14/9.1.12">https://reference.opcfoundation.org/v105/Core/docs/Part14/9.1.12</a>
 */
public interface PubSubCapabilitiesType extends BaseObjectType {
    QualifiedProperty<UInteger> MAX_PUB_SUB_CONNECTIONS = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "MaxPubSubConnections",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=7"),
        -1,
        UInteger.class
    );

    QualifiedProperty<UInteger> MAX_WRITER_GROUPS = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "MaxWriterGroups",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=7"),
        -1,
        UInteger.class
    );

    QualifiedProperty<UInteger> MAX_READER_GROUPS = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "MaxReaderGroups",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=7"),
        -1,
        UInteger.class
    );

    QualifiedProperty<UInteger> MAX_DATA_SET_WRITERS = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "MaxDataSetWriters",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=7"),
        -1,
        UInteger.class
    );

    QualifiedProperty<UInteger> MAX_DATA_SET_READERS = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "MaxDataSetReaders",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=7"),
        -1,
        UInteger.class
    );

    QualifiedProperty<UInteger> MAX_FIELDS_PER_DATA_SET = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "MaxFieldsPerDataSet",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=7"),
        -1,
        UInteger.class
    );

    UInteger getMaxPubSubConnections();

    void setMaxPubSubConnections(UInteger value);

    PropertyType getMaxPubSubConnectionsNode();

    UInteger getMaxWriterGroups();

    void setMaxWriterGroups(UInteger value);

    PropertyType getMaxWriterGroupsNode();

    UInteger getMaxReaderGroups();

    void setMaxReaderGroups(UInteger value);

    PropertyType getMaxReaderGroupsNode();

    UInteger getMaxDataSetWriters();

    void setMaxDataSetWriters(UInteger value);

    PropertyType getMaxDataSetWritersNode();

    UInteger getMaxDataSetReaders();

    void setMaxDataSetReaders(UInteger value);

    PropertyType getMaxDataSetReadersNode();

    UInteger getMaxFieldsPerDataSet();

    void setMaxFieldsPerDataSet(UInteger value);

    PropertyType getMaxFieldsPerDataSetNode();
}
