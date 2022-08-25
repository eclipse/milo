package org.eclipse.milo.opcua.sdk.server.model.variables;

import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.structured.EUInformation;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part8/5.3.2/#5.3.2.4">https://reference.opcfoundation.org/v105/Core/docs/Part8/5.3.2/#5.3.2.4</a>
 */
public interface AnalogUnitType extends BaseAnalogType {
    QualifiedProperty<EUInformation> ENGINEERING_UNITS = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "EngineeringUnits",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=887"),
        -1,
        EUInformation.class
    );

    EUInformation getEngineeringUnits();

    void setEngineeringUnits(EUInformation value);

    PropertyType getEngineeringUnitsNode();
}
