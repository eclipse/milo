package org.eclipse.milo.opcua.sdk.server.model.objects;

import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.server.model.variables.PropertyType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part5/6.11">https://reference.opcfoundation.org/v105/Core/docs/Part5/6.11</a>
 */
public interface IOrderedObjectType extends BaseInterfaceType {
    QualifiedProperty<Variant> NUMBER_IN_LIST = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "NumberInList",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=26"),
        -1,
        Variant.class
    );

    Variant getNumberInList();

    void setNumberInList(Variant value);

    PropertyType getNumberInListNode();
}
