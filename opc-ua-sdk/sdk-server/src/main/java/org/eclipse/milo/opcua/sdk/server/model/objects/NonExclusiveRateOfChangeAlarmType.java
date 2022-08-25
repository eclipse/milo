package org.eclipse.milo.opcua.sdk.server.model.objects;

import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.server.model.variables.PropertyType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.structured.EUInformation;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part9/5.8.22/#5.8.22.2">https://reference.opcfoundation.org/v105/Core/docs/Part9/5.8.22/#5.8.22.2</a>
 */
public interface NonExclusiveRateOfChangeAlarmType extends NonExclusiveLimitAlarmType {
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
