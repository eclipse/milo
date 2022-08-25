package org.eclipse.milo.opcua.sdk.server.model.variables;

import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.structured.EUInformation;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part5/7.21">https://reference.opcfoundation.org/v105/Core/docs/Part5/7.21</a>
 */
public interface VectorType extends BaseDataVariableType {
    QualifiedProperty<EUInformation> VECTOR_UNIT = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "VectorUnit",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=887"),
        -1,
        EUInformation.class
    );

    EUInformation getVectorUnit();

    void setVectorUnit(EUInformation value);

    PropertyType getVectorUnitNode();
}
