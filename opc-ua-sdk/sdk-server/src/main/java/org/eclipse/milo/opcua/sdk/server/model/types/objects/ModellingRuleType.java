package org.eclipse.milo.opcua.sdk.server.model.types.objects;

import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.sdk.core.model.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.server.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NamingRuleType;

public interface ModellingRuleType extends BaseObjectType {
    QualifiedProperty<NamingRuleType> NAMING_RULE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "NamingRule",
        NodeId.parse("ns=0;i=120"),
        ValueRanks.Scalar,
        NamingRuleType.class
    );

    PropertyType getNamingRuleNode();

    NamingRuleType getNamingRule();

    void setNamingRule(NamingRuleType value);
}
