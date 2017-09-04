package org.eclipse.milo.opcua.sdk.server.model.types.objects;

import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.sdk.core.model.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.server.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;

public interface AggregateConfigurationType extends BaseObjectType {
    QualifiedProperty<Boolean> TREAT_UNCERTAIN_AS_BAD = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "TreatUncertainAsBad",
        NodeId.parse("ns=0;i=1"),
        ValueRanks.Scalar,
        Boolean.class
    );

    QualifiedProperty<UByte> PERCENT_DATA_BAD = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "PercentDataBad",
        NodeId.parse("ns=0;i=3"),
        ValueRanks.Scalar,
        UByte.class
    );

    QualifiedProperty<UByte> PERCENT_DATA_GOOD = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "PercentDataGood",
        NodeId.parse("ns=0;i=3"),
        ValueRanks.Scalar,
        UByte.class
    );

    QualifiedProperty<Boolean> USE_SLOPED_EXTRAPOLATION = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "UseSlopedExtrapolation",
        NodeId.parse("ns=0;i=1"),
        ValueRanks.Scalar,
        Boolean.class
    );

    PropertyType getTreatUncertainAsBadNode();

    Boolean getTreatUncertainAsBad();

    void setTreatUncertainAsBad(Boolean value);

    PropertyType getPercentDataBadNode();

    UByte getPercentDataBad();

    void setPercentDataBad(UByte value);

    PropertyType getPercentDataGoodNode();

    UByte getPercentDataGood();

    void setPercentDataGood(UByte value);

    PropertyType getUseSlopedExtrapolationNode();

    Boolean getUseSlopedExtrapolation();

    void setUseSlopedExtrapolation(Boolean value);
}
