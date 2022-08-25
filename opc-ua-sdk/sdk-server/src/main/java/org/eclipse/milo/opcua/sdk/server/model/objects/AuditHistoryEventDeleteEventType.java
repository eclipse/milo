package org.eclipse.milo.opcua.sdk.server.model.objects;

import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.server.model.variables.PropertyType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.structured.HistoryEventFieldList;

/**
 * @see <a href="https://reference.opcfoundation.org/v104/Core/docs/Part11/5.6.8">https://reference.opcfoundation.org/v104/Core/docs/Part11/5.6.8</a>
 */
public interface AuditHistoryEventDeleteEventType extends AuditHistoryDeleteEventType {
    QualifiedProperty<ByteString[]> EVENT_IDS = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "EventIds",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15"),
        1,
        ByteString[].class
    );

    QualifiedProperty<HistoryEventFieldList> OLD_VALUES = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "OldValues",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=920"),
        -1,
        HistoryEventFieldList.class
    );

    ByteString[] getEventIds();

    void setEventIds(ByteString[] value);

    PropertyType getEventIdsNode();

    HistoryEventFieldList getOldValues();

    void setOldValues(HistoryEventFieldList value);

    PropertyType getOldValuesNode();
}
