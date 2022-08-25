package org.eclipse.milo.opcua.sdk.server.model.objects;

import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.server.model.variables.PropertyType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;

public interface AggregateConfigurationType extends BaseObjectType {
    QualifiedProperty<Boolean> TREAT_UNCERTAIN_AS_BAD = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "TreatUncertainAsBad",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=1"),
        -1,
        Boolean.class
    );

    QualifiedProperty<UByte> PERCENT_DATA_BAD = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "PercentDataBad",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=3"),
        -1,
        UByte.class
    );

    QualifiedProperty<UByte> PERCENT_DATA_GOOD = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "PercentDataGood",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=3"),
        -1,
        UByte.class
    );

    QualifiedProperty<Boolean> USE_SLOPED_EXTRAPOLATION = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "UseSlopedExtrapolation",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=1"),
        -1,
        Boolean.class
    );

    Boolean getTreatUncertainAsBad();

    void setTreatUncertainAsBad(Boolean value);

    PropertyType getTreatUncertainAsBadNode();

    UByte getPercentDataBad();

    void setPercentDataBad(UByte value);

    PropertyType getPercentDataBadNode();

    UByte getPercentDataGood();

    void setPercentDataGood(UByte value);

    PropertyType getPercentDataGoodNode();

    Boolean getUseSlopedExtrapolation();

    void setUseSlopedExtrapolation(Boolean value);

    PropertyType getUseSlopedExtrapolationNode();
}
