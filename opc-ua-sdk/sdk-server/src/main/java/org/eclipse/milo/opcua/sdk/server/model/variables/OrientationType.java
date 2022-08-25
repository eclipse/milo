package org.eclipse.milo.opcua.sdk.server.model.variables;

import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.structured.EUInformation;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part5/7.25">https://reference.opcfoundation.org/v105/Core/docs/Part5/7.25</a>
 */
public interface OrientationType extends BaseDataVariableType {
    QualifiedProperty<EUInformation> ANGLE_UNIT = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "AngleUnit",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=887"),
        -1,
        EUInformation.class
    );

    EUInformation getAngleUnit();

    void setAngleUnit(EUInformation value);

    PropertyType getAngleUnitNode();
}
