package org.eclipse.milo.opcua.sdk.server.model.objects;

import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.server.model.variables.PropertyType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.structured.JsonDataSetMessageContentMask;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part14/9.2.2/#9.2.2.2">https://reference.opcfoundation.org/v105/Core/docs/Part14/9.2.2/#9.2.2.2</a>
 */
public interface JsonDataSetWriterMessageType extends DataSetWriterMessageType {
    QualifiedProperty<JsonDataSetMessageContentMask> DATA_SET_MESSAGE_CONTENT_MASK = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "DataSetMessageContentMask",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15658"),
        -1,
        JsonDataSetMessageContentMask.class
    );

    JsonDataSetMessageContentMask getDataSetMessageContentMask();

    void setDataSetMessageContentMask(JsonDataSetMessageContentMask value);

    PropertyType getDataSetMessageContentMaskNode();
}