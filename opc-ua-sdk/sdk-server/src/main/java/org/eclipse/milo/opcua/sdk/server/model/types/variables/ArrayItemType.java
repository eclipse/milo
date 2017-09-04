package org.eclipse.milo.opcua.sdk.server.model.types.variables;

import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.sdk.core.model.QualifiedProperty;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.enumerated.AxisScaleEnumeration;
import org.eclipse.milo.opcua.stack.core.types.structured.EUInformation;
import org.eclipse.milo.opcua.stack.core.types.structured.Range;

public interface ArrayItemType extends DataItemType {
    QualifiedProperty<Range> INSTRUMENT_RANGE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "InstrumentRange",
        NodeId.parse("ns=0;i=884"),
        ValueRanks.Scalar,
        Range.class
    );

    QualifiedProperty<Range> E_U_RANGE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "EURange",
        NodeId.parse("ns=0;i=884"),
        ValueRanks.Scalar,
        Range.class
    );

    QualifiedProperty<EUInformation> ENGINEERING_UNITS = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "EngineeringUnits",
        NodeId.parse("ns=0;i=887"),
        ValueRanks.Scalar,
        EUInformation.class
    );

    QualifiedProperty<LocalizedText> TITLE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "Title",
        NodeId.parse("ns=0;i=21"),
        ValueRanks.Scalar,
        LocalizedText.class
    );

    QualifiedProperty<AxisScaleEnumeration> AXIS_SCALE_TYPE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "AxisScaleType",
        NodeId.parse("ns=0;i=12077"),
        ValueRanks.Scalar,
        AxisScaleEnumeration.class
    );

    PropertyType getInstrumentRangeNode();

    Range getInstrumentRange();

    void setInstrumentRange(Range value);

    PropertyType getEURangeNode();

    Range getEURange();

    void setEURange(Range value);

    PropertyType getEngineeringUnitsNode();

    EUInformation getEngineeringUnits();

    void setEngineeringUnits(EUInformation value);

    PropertyType getTitleNode();

    LocalizedText getTitle();

    void setTitle(LocalizedText value);

    PropertyType getAxisScaleTypeNode();

    AxisScaleEnumeration getAxisScaleType();

    void setAxisScaleType(AxisScaleEnumeration value);
}
