/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.nodes;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import javax.annotation.Nullable;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import org.eclipse.milo.opcua.sdk.core.AccessLevel;
import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.core.Reference;
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.sdk.server.api.nodes.Node;
import org.eclipse.milo.opcua.sdk.server.api.nodes.ObjectNode;
import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableTypeNode;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;
import org.eclipse.milo.opcua.stack.core.types.structured.EUInformation;
import org.eclipse.milo.opcua.stack.core.types.structured.TimeZoneDataType;
import org.eclipse.milo.opcua.stack.core.util.Namespaces;

import static org.eclipse.milo.opcua.sdk.core.Reference.HAS_COMPONENT_PREDICATE;
import static org.eclipse.milo.opcua.sdk.core.Reference.HAS_MODELLING_RULE_PREDICATE;
import static org.eclipse.milo.opcua.sdk.core.Reference.HAS_PROPERTY_PREDICATE;
import static org.eclipse.milo.opcua.sdk.core.Reference.HAS_TYPE_DEFINITION_PREDICATE;
import static org.eclipse.milo.opcua.sdk.core.util.StreamUtil.opt2stream;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ubyte;

public class UaVariableNode extends UaNode implements VariableNode {

    private static final DataValue INITIAL_VALUE = new DataValue(new StatusCode(StatusCodes.Uncertain_InitialValue));

    private volatile DataValue value = INITIAL_VALUE;
    private volatile NodeId dataType = Identifiers.BaseDataType;
    private volatile Integer valueRank = ValueRanks.Scalar;
    private volatile UInteger[] arrayDimensions = null;
    private volatile UByte accessLevel = Unsigned.ubyte(AccessLevel.getMask(AccessLevel.CurrentRead));
    private volatile UByte userAccessLevel = ubyte(AccessLevel.getMask(AccessLevel.CurrentRead));
    private volatile Double minimumSamplingInterval = 0.0;
    private volatile Boolean historizing = false;

    public UaVariableNode(
        UaNodeContext context,
        NodeId nodeId,
        VariableTypeNode variableTypeNode) {

        this(context, nodeId, variableTypeNode.getBrowseName(), variableTypeNode.getDisplayName());

        setDescription(variableTypeNode.getDescription());
        setWriteMask(variableTypeNode.getWriteMask());
        setUserWriteMask(variableTypeNode.getUserWriteMask());
        setValue(variableTypeNode.getValue());
        setDataType(variableTypeNode.getDataType());
        setValueRank(variableTypeNode.getValueRank());
        setArrayDimensions(variableTypeNode.getArrayDimensions());
    }

    public UaVariableNode(
        UaNodeContext context,
        NodeId nodeId,
        QualifiedName browseName,
        LocalizedText displayName) {

        super(context, nodeId, NodeClass.Variable, browseName, displayName);
    }

    public UaVariableNode(
        UaNodeContext context,
        NodeId nodeId,
        QualifiedName browseName,
        LocalizedText displayName,
        LocalizedText description,
        UInteger writeMask,
        UInteger userWriteMask) {

        super(context, nodeId, NodeClass.Variable, browseName, displayName, description, writeMask, userWriteMask);
    }

    public UaVariableNode(
        UaNodeContext context,
        NodeId nodeId,
        QualifiedName browseName,
        LocalizedText displayName,
        LocalizedText description,
        UInteger writeMask,
        UInteger userWriteMask,
        DataValue value,
        NodeId dataType,
        Integer valueRank,
        UInteger[] arrayDimensions,
        UByte accessLevel,
        UByte userAccessLevel,
        Double minimumSamplingInterval,
        boolean historizing) {

        super(context, nodeId, NodeClass.Variable,
            browseName, displayName, description, writeMask, userWriteMask);

        this.value = value;
        this.dataType = dataType;
        this.valueRank = valueRank;
        this.arrayDimensions = arrayDimensions;
        this.accessLevel = accessLevel;
        this.userAccessLevel = userAccessLevel;
        this.minimumSamplingInterval = minimumSamplingInterval;
        this.historizing = historizing;
    }

    @Override
    public DataValue getValue() {
        return value;
    }

    @Override
    public NodeId getDataType() {
        return dataType;
    }

    @Override
    public Integer getValueRank() {
        return valueRank;
    }

    @Override
    public UInteger[] getArrayDimensions() {
        return arrayDimensions;
    }

    @Override
    public UByte getAccessLevel() {
        return accessLevel;
    }

    @Override
    public UByte getUserAccessLevel() {
        return userAccessLevel;
    }

    @Override
    public Double getMinimumSamplingInterval() {
        return minimumSamplingInterval;
    }

    @Override
    public Boolean getHistorizing() {
        return historizing;
    }

    @Override
    public synchronized void setValue(DataValue value) {
        this.value = value;

        fireAttributeChanged(AttributeId.Value, value);
    }

    @Override
    public synchronized void setDataType(NodeId dataType) {
        this.dataType = dataType;

        fireAttributeChanged(AttributeId.DataType, dataType);
    }

    @Override
    public synchronized void setValueRank(Integer valueRank) {
        this.valueRank = valueRank;

        fireAttributeChanged(AttributeId.ValueRank, valueRank);
    }

    @Override
    public synchronized void setArrayDimensions(UInteger[] arrayDimensions) {
        this.arrayDimensions = arrayDimensions;

        fireAttributeChanged(AttributeId.ArrayDimensions, arrayDimensions);
    }

    @Override
    public synchronized void setAccessLevel(UByte accessLevel) {
        this.accessLevel = accessLevel;

        fireAttributeChanged(AttributeId.AccessLevel, accessLevel);
    }

    @Override
    public synchronized void setUserAccessLevel(UByte userAccessLevel) {
        this.userAccessLevel = userAccessLevel;

        fireAttributeChanged(AttributeId.UserAccessLevel, userAccessLevel);
    }

    @Override
    public void setMinimumSamplingInterval(Double minimumSamplingInterval) {
        this.minimumSamplingInterval = minimumSamplingInterval;

        fireAttributeChanged(AttributeId.MinimumSamplingInterval, minimumSamplingInterval);
    }

    @Override
    public void setHistorizing(Boolean historizing) {
        this.historizing = historizing;

        fireAttributeChanged(AttributeId.Historizing, historizing);
    }

    public Optional<ObjectNode> getModellingRuleNode() {
        Node node = getReferences().stream()
            .filter(HAS_MODELLING_RULE_PREDICATE)
            .findFirst()
            .flatMap(r -> getManagedNode(r.getTargetNodeId()))
            .orElse(null);

        ObjectNode objectNode = (node instanceof ObjectNode) ? (ObjectNode) node : null;

        return Optional.ofNullable(objectNode);
    }

    public List<Node> getPropertyNodes() {
        return getReferences().stream()
            .filter(HAS_PROPERTY_PREDICATE)
            .flatMap(r -> opt2stream(getManagedNode(r.getTargetNodeId())))
            .collect(Collectors.toList());
    }

    public List<Node> getComponentNodes() {
        return getReferences().stream()
            .filter(HAS_COMPONENT_PREDICATE)
            .flatMap(r -> opt2stream(getManagedNode(r.getTargetNodeId())))
            .collect(Collectors.toList());
    }

    public VariableTypeNode getTypeDefinitionNode() {
        Node node = getReferences().stream()
            .filter(HAS_TYPE_DEFINITION_PREDICATE)
            .findFirst()
            .flatMap(r -> getManagedNode(r.getTargetNodeId()))
            .orElse(null);

        return (node instanceof VariableTypeNode) ? (VariableTypeNode) node : null;
    }

    /**
     * Add a 'HasComponent' reference from this Object to {@code node} and an inverse 'ComponentOf' reference from
     * {@code node} back to this Object.
     *
     * @param node the node to add as a component of this Object.
     */
    public void addComponent(UaNode node) {
        addReference(new Reference(
            getNodeId(),
            Identifiers.HasComponent,
            node.getNodeId().expanded(),
            true
        ));
    }

    /**
     * Remove the 'HasComponent' reference from this Object to {@code node} and the inverse 'ComponentOf' reference
     * from {@code node} back to this Object.
     *
     * @param node the node to remove as a component of this Object.
     */
    public void removeComponent(UaNode node) {
        removeReference(new Reference(
            getNodeId(),
            Identifiers.HasComponent,
            node.getNodeId().expanded(),
            true
        ));
    }

    @Nullable
    public String getNodeVersion() {
        return getProperty(NodeVersion).orElse(null);
    }

    @Nullable
    public TimeZoneDataType getLocalTime() {
        return getProperty(LocalTime).orElse(null);
    }

    @Nullable
    public String getDataTypeVersion() {
        return getProperty(DataTypeVersion).orElse(null);
    }

    @Nullable
    public ByteString getDictionaryFragment() {
        return getProperty(DictionaryFragment).orElse(null);
    }

    @Nullable
    public Boolean getAllowNulls() {
        return getProperty(AllowNulls).orElse(null);
    }

    @Nullable
    public UInteger getMaxStringLength() {
        return getProperty(MaxStringLength).orElse(null);
    }

    @Nullable
    public UInteger getMaxArrayLength() {
        return getProperty(MaxArrayLength).orElse(null);
    }

    @Nullable
    public EUInformation getEngineeringUnits() {
        return getProperty(EngineeringUnits).orElse(null);
    }

    public void setNodeVersion(String nodeVersion) {
        setProperty(NodeVersion, nodeVersion);
    }

    public void setLocalTime(TimeZoneDataType localTime) {
        setProperty(LocalTime, localTime);
    }

    public void setDataTypeVersion(String dataTypeVersion) {
        setProperty(DataTypeVersion, dataTypeVersion);
    }

    public void setDictionaryFragment(ByteString dictionaryFragment) {
        setProperty(DictionaryFragment, dictionaryFragment);
    }

    public void setAllowNulls(Boolean allowNulls) {
        setProperty(AllowNulls, allowNulls);
    }

    public void setMaxStringLength(UInteger maxStringLength) {
        setProperty(MaxStringLength, maxStringLength);
    }

    public void setMaxArrayLength(UInteger maxArrayLength) {
        setProperty(MaxArrayLength, maxArrayLength);
    }

    public void setEngineeringUnits(EUInformation engineeringUnits) {
        setProperty(EngineeringUnits, engineeringUnits);
    }

    public static final QualifiedProperty<String> NodeVersion = new QualifiedProperty<>(
        Namespaces.OPC_UA,
        "NodeVersion",
        Identifiers.String,
        ValueRanks.Scalar,
        String.class
    );

    public static final QualifiedProperty<TimeZoneDataType> LocalTime = new QualifiedProperty<>(
        Namespaces.OPC_UA,
        "LocalTime",
        Identifiers.TimeZoneDataType,
        ValueRanks.Scalar,
        TimeZoneDataType.class
    );

    public static final QualifiedProperty<String> DataTypeVersion = new QualifiedProperty<>(
        Namespaces.OPC_UA,
        "DataTypeVersion",
        Identifiers.String,
        ValueRanks.Scalar,
        String.class
    );

    public static final QualifiedProperty<ByteString> DictionaryFragment = new QualifiedProperty<>(
        Namespaces.OPC_UA,
        "DictionaryFragment",
        Identifiers.ByteString,
        ValueRanks.Scalar,
        ByteString.class
    );

    public static final QualifiedProperty<Boolean> AllowNulls = new QualifiedProperty<>(
        Namespaces.OPC_UA,
        "AllowNulls",
        Identifiers.Boolean,
        ValueRanks.Scalar,
        Boolean.class
    );

    public static final QualifiedProperty<LocalizedText> ValueAsText = new QualifiedProperty<>(
        Namespaces.OPC_UA,
        "ValueAsText",
        Identifiers.LocalizedText,
        ValueRanks.Scalar,
        LocalizedText.class
    );

    public static final QualifiedProperty<UInteger> MaxStringLength = new QualifiedProperty<>(
        Namespaces.OPC_UA,
        "MaxStringLength",
        Identifiers.UInt32,
        ValueRanks.Scalar,
        UInteger.class
    );

    public static final QualifiedProperty<UInteger> MaxArrayLength = new QualifiedProperty<>(
        Namespaces.OPC_UA,
        "MaxArrayLength",
        Identifiers.UInt32,
        ValueRanks.Scalar,
        UInteger.class
    );

    public static final QualifiedProperty<EUInformation> EngineeringUnits = new QualifiedProperty<>(
        Namespaces.OPC_UA,
        "EngineeringUnits",
        Identifiers.EUInformation,
        ValueRanks.Scalar,
        EUInformation.class
    );

    public static UaVariableNodeBuilder builder(UaNodeContext context) {
        return new UaVariableNodeBuilder(context);
    }

    public static class UaVariableNodeBuilder implements Supplier<UaVariableNode> {

        private final List<Reference> references = Lists.newArrayList();

        private NodeId nodeId;
        private QualifiedName browseName;
        private LocalizedText displayName;
        private LocalizedText description = LocalizedText.NULL_VALUE;
        private UInteger writeMask = UInteger.MIN;
        private UInteger userWriteMask = UInteger.MIN;

        private DataValue value = new DataValue(
            Variant.NULL_VALUE, new StatusCode(StatusCodes.Uncertain_InitialValue),
            DateTime.now(), DateTime.now()
        );

        private NodeId dataType;
        private int valueRank = ValueRanks.Scalar;
        private UInteger[] arrayDimensions = null;
        private UByte accessLevel = ubyte(AccessLevel.getMask(AccessLevel.CurrentRead));
        private UByte userAccessLevel = ubyte(AccessLevel.getMask(AccessLevel.CurrentRead));
        private Double minimumSamplingInterval = 0.0;
        private boolean historizing = false;

        private final UaNodeContext context;

        public UaVariableNodeBuilder(UaNodeContext context) {
            this.context = context;
        }

        @Override
        public UaVariableNode get() {
            return build();
        }

        public UaVariableNode build() {
            Preconditions.checkNotNull(nodeId, "NodeId cannot be null");
            Preconditions.checkNotNull(browseName, "BrowseName cannot be null");
            Preconditions.checkNotNull(displayName, "DisplayName cannot be null");
            Preconditions.checkNotNull(dataType, "DataType cannot be null");

            long hasTypeDefinitionCount = references.stream()
                .filter(r -> Identifiers.HasTypeDefinition.equals(r.getReferenceTypeId())).count();

            if (hasTypeDefinitionCount == 0) {
                setTypeDefinition(Identifiers.BaseDataVariableType);
            } else {
                Preconditions.checkState(
                    hasTypeDefinitionCount == 1,
                    "Variable Node must have exactly one HasTypeDefinition reference.");
            }

            // TODO More validation on references.

            UaVariableNode node = new UaVariableNode(
                context,
                nodeId,
                browseName,
                displayName,
                description,
                writeMask,
                userWriteMask,
                value,
                dataType,
                valueRank,
                arrayDimensions,
                accessLevel,
                userAccessLevel,
                minimumSamplingInterval,
                historizing
            );

            references.forEach(node::addReference);

            return node;
        }

        public UaVariableNodeBuilder setNodeId(NodeId nodeId) {
            this.nodeId = nodeId;
            return this;
        }

        public UaVariableNodeBuilder setBrowseName(QualifiedName browseName) {
            this.browseName = browseName;
            return this;
        }

        public UaVariableNodeBuilder setDisplayName(LocalizedText displayName) {
            this.displayName = displayName;
            return this;
        }

        public UaVariableNodeBuilder setDescription(LocalizedText description) {
            this.description = description;
            return this;
        }

        public UaVariableNodeBuilder setWriteMask(UInteger writeMask) {
            this.writeMask = writeMask;
            return this;
        }

        public UaVariableNodeBuilder setUserWriteMask(UInteger userWriteMask) {
            this.userWriteMask = userWriteMask;
            return this;
        }

        public UaVariableNodeBuilder setValue(DataValue value) {
            this.value = value;
            return this;
        }

        public UaVariableNodeBuilder setDataType(NodeId dataType) {
            this.dataType = dataType;
            return this;
        }

        public UaVariableNodeBuilder setValueRank(int valueRank) {
            this.valueRank = valueRank;
            return this;
        }

        public UaVariableNodeBuilder setArrayDimensions(UInteger[] arrayDimensions) {
            this.arrayDimensions = arrayDimensions;
            return this;
        }

        public UaVariableNodeBuilder setAccessLevel(UByte accessLevel) {
            this.accessLevel = accessLevel;
            return this;
        }

        public UaVariableNodeBuilder setUserAccessLevel(UByte userAccessLevel) {
            this.userAccessLevel = userAccessLevel;
            return this;
        }

        public UaVariableNodeBuilder setMinimumSamplingInterval(Double minimumSamplingInterval) {
            this.minimumSamplingInterval = minimumSamplingInterval;
            return this;
        }

        public UaVariableNodeBuilder setHistorizing(boolean historizing) {
            this.historizing = historizing;
            return this;
        }

        public UaVariableNodeBuilder addReference(Reference reference) {
            references.add(reference);
            return this;
        }

        /**
         * Convenience method for adding the required HasTypeDefinition reference.
         * <p>
         * {@link #setNodeId(NodeId)} must have already been called before invoking this method.
         *
         * @param typeDefinition The {@link NodeId} of the TypeDefinition.
         * @return this {@link UaVariableNodeBuilder}.
         */
        public UaVariableNodeBuilder setTypeDefinition(NodeId typeDefinition) {
            Objects.requireNonNull(nodeId, "NodeId cannot be null");

            references.add(new Reference(
                nodeId,
                Identifiers.HasTypeDefinition,
                new ExpandedNodeId(typeDefinition),
                true
            ));

            return this;
        }

    }

}
