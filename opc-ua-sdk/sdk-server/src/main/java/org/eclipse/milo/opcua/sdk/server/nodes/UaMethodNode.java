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
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import javax.annotation.Nullable;

import com.google.common.base.Preconditions;
import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.sdk.server.api.MethodInvocationHandler;
import org.eclipse.milo.opcua.sdk.server.api.nodes.MethodNode;
import org.eclipse.milo.opcua.sdk.server.api.nodes.Node;
import org.eclipse.milo.opcua.sdk.server.api.nodes.ObjectNode;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;
import org.eclipse.milo.opcua.stack.core.types.structured.Argument;
import org.eclipse.milo.opcua.stack.core.util.Namespaces;

import static org.eclipse.milo.opcua.sdk.core.Reference.ALWAYS_GENERATES_EVENT_PREDICATE;
import static org.eclipse.milo.opcua.sdk.core.Reference.HAS_MODELLING_RULE_PREDICATE;
import static org.eclipse.milo.opcua.sdk.core.Reference.HAS_PROPERTY_PREDICATE;
import static org.eclipse.milo.opcua.sdk.core.util.StreamUtil.opt2stream;

public class UaMethodNode extends UaNode implements MethodNode {

    private volatile Optional<MethodInvocationHandler> handler = Optional.empty();

    private volatile Boolean executable;
    private volatile Boolean userExecutable;

    public UaMethodNode(
        UaNodeContext context,
        NodeId nodeId,
        QualifiedName browseName,
        LocalizedText displayName,
        LocalizedText description,
        UInteger writeMask,
        UInteger userWriteMask,
        Boolean executable,
        Boolean userExecutable) {

        super(context, nodeId, NodeClass.Method,
            browseName, displayName, description, writeMask, userWriteMask);

        this.executable = executable;
        this.userExecutable = userExecutable;
    }

    @Override
    public Boolean isExecutable() {
        return executable;
    }

    @Override
    public Boolean isUserExecutable() {
        return userExecutable;
    }

    @Override
    public synchronized void setExecutable(Boolean executable) {
        this.executable = executable;

        fireAttributeChanged(AttributeId.Executable, executable);
    }

    @Override
    public synchronized void setUserExecutable(Boolean userExecutable) {
        this.userExecutable = userExecutable;

        fireAttributeChanged(AttributeId.UserExecutable, userExecutable);
    }

    public List<Node> getPropertyNodes() {
        return getReferences().stream()
            .filter(HAS_PROPERTY_PREDICATE)
            .flatMap(r -> opt2stream(getNode(r.getTargetNodeId())))
            .collect(Collectors.toList());
    }

    public Optional<ObjectNode> getModellingRuleNode() {
        Node node = getReferences().stream()
            .filter(HAS_MODELLING_RULE_PREDICATE)
            .findFirst()
            .flatMap(r -> getNode(r.getTargetNodeId()))
            .orElse(null);

        ObjectNode objectNode = (node instanceof ObjectNode) ? (ObjectNode) node : null;

        return Optional.ofNullable(objectNode);
    }

    public List<Node> getAlwaysGeneratesEventNodes() {
        return getReferences().stream()
            .filter(ALWAYS_GENERATES_EVENT_PREDICATE)
            .flatMap(r -> opt2stream(getNode(r.getTargetNodeId())))
            .collect(Collectors.toList());
    }

    public Optional<MethodInvocationHandler> getInvocationHandler() {
        return handler;
    }

    public void setInvocationHandler(MethodInvocationHandler handler) {
        this.handler = Optional.of(handler);
    }

    @Nullable
    public String getNodeVersion() {
        return getProperty(NodeVersion).orElse(null);
    }

    @Nullable
    public Argument[] getInputArguments() {
        return getProperty(InputArguments).orElse(null);
    }

    @Nullable
    public Argument[] getOutputArguments() {
        return getProperty(OutputArguments).orElse(null);
    }

    public void setNodeVersion(String nodeVersion) {
        setProperty(NodeVersion, nodeVersion);
    }

    public void setInputArguments(Argument[] inputArguments) {
        setProperty(InputArguments, inputArguments);
    }

    public void setOutputArguments(Argument[] outputArguments) {
        setProperty(OutputArguments, outputArguments);
    }


    public static final QualifiedProperty<Argument[]> InputArguments = new QualifiedProperty<>(
        Namespaces.OPC_UA,
        "InputArguments",
        Identifiers.Argument,
        ValueRanks.OneDimension,
        Argument[].class
    );

    public static final QualifiedProperty<Argument[]> OutputArguments = new QualifiedProperty<>(
        Namespaces.OPC_UA,
        "OutputArguments",
        Identifiers.Argument,
        ValueRanks.OneDimension,
        Argument[].class
    );

    public static final QualifiedProperty<String> NodeVersion = new QualifiedProperty<>(
        Namespaces.OPC_UA,
        "NodeVersion",
        Identifiers.String,
        ValueRanks.Scalar,
        String.class
    );

    /**
     * @return a new {@link UaMethodNodeBuilder}.
     */
    public static UaMethodNodeBuilder builder(UaNodeContext context) {
        return new UaMethodNodeBuilder(context);
    }

    public static class UaMethodNodeBuilder implements Supplier<UaMethodNode> {

        private NodeId nodeId;
        private QualifiedName browseName;
        private LocalizedText displayName;
        private LocalizedText description = LocalizedText.NULL_VALUE;
        private UInteger writeMask = UInteger.MIN;
        private UInteger userWriteMask = UInteger.MIN;

        private boolean executable = true;
        private boolean userExecutable = true;

        private final UaNodeContext context;

        public UaMethodNodeBuilder(UaNodeContext context) {
            this.context = context;
        }

        @Override
        public UaMethodNode get() {
            return build();
        }

        public UaMethodNode build() {
            Preconditions.checkNotNull(nodeId, "NodeId cannot be null");
            Preconditions.checkNotNull(browseName, "BrowseName cannot be null");
            Preconditions.checkNotNull(displayName, "DisplayName cannot be null");

            return new UaMethodNode(
                context,
                nodeId,
                browseName,
                displayName,
                description,
                writeMask,
                userWriteMask,
                executable,
                userExecutable
            );
        }

        public UaMethodNodeBuilder setNodeId(NodeId nodeId) {
            this.nodeId = nodeId;
            return this;
        }

        public UaMethodNodeBuilder setBrowseName(QualifiedName browseName) {
            this.browseName = browseName;
            return this;
        }

        public UaMethodNodeBuilder setDisplayName(LocalizedText displayName) {
            this.displayName = displayName;
            return this;
        }

        public UaMethodNodeBuilder setDescription(LocalizedText description) {
            this.description = description;
            return this;
        }

        public UaMethodNodeBuilder setWriteMask(UInteger writeMask) {
            this.writeMask = writeMask;
            return this;
        }

        public UaMethodNodeBuilder setUserWriteMask(UInteger userWriteMask) {
            this.userWriteMask = userWriteMask;
            return this;
        }

        public UaMethodNodeBuilder setExecutable(boolean executable) {
            this.executable = executable;
            return this;
        }

        public UaMethodNodeBuilder setUserExecutable(boolean userExecutable) {
            this.userExecutable = userExecutable;
            return this;
        }

    }

}
