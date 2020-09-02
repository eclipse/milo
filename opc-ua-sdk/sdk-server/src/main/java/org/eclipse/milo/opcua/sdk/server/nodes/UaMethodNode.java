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
import org.eclipse.milo.opcua.sdk.core.nodes.MethodNode;
import org.eclipse.milo.opcua.sdk.core.nodes.MethodNodeProperties;
import org.eclipse.milo.opcua.sdk.core.nodes.Node;
import org.eclipse.milo.opcua.sdk.core.nodes.ObjectNode;
import org.eclipse.milo.opcua.sdk.server.api.methods.MethodInvocationHandler;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;
import org.eclipse.milo.opcua.stack.core.types.structured.Argument;

import static org.eclipse.milo.opcua.sdk.core.Reference.ALWAYS_GENERATES_EVENT_PREDICATE;
import static org.eclipse.milo.opcua.sdk.core.Reference.HAS_MODELLING_RULE_PREDICATE;
import static org.eclipse.milo.opcua.sdk.core.Reference.HAS_PROPERTY_PREDICATE;
import static org.eclipse.milo.opcua.sdk.core.util.StreamUtil.opt2stream;

public class UaMethodNode extends UaNode implements MethodNode {

    private volatile MethodInvocationHandler handler = MethodInvocationHandler.NOT_IMPLEMENTED;

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

    @Override
    public synchronized Object getAttribute(AttributeId attributeId) {
        switch (attributeId) {
            case Executable:
                return executable;

            case UserExecutable:
                return userExecutable;

            default:
                return super.getAttribute(attributeId);
        }
    }

    @Override
    public synchronized void setAttribute(AttributeId attributeId, Object value) {
        switch (attributeId) {
            case Executable:
                executable = (Boolean) value;
                break;

            case UserExecutable:
                userExecutable = (Boolean) value;
                break;

            default:
                super.setAttribute(attributeId, value);
                return; // prevent firing an attribute change
        }

        fireAttributeChanged(attributeId, value);
    }

    public List<Node> getPropertyNodes() {
        return getReferences().stream()
            .filter(HAS_PROPERTY_PREDICATE)
            .flatMap(r -> opt2stream(getManagedNode(r.getTargetNodeId())))
            .collect(Collectors.toList());
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

    public List<Node> getAlwaysGeneratesEventNodes() {
        return getReferences().stream()
            .filter(ALWAYS_GENERATES_EVENT_PREDICATE)
            .flatMap(r -> opt2stream(getManagedNode(r.getTargetNodeId())))
            .collect(Collectors.toList());
    }

    public MethodInvocationHandler getInvocationHandler() {
        return handler;
    }

    public void setInvocationHandler(MethodInvocationHandler handler) {
        this.handler = handler;
    }

    /**
     * Get the value of the NodeVersion Property, if it exists.
     *
     * @return the value of the NodeVersion Property, if it exists.
     * @see MethodNodeProperties#NodeVersion
     */
    @Nullable
    public String getNodeVersion() {
        return getProperty(MethodNodeProperties.NodeVersion).orElse(null);
    }

    /**
     * Get the value of the InputArguments Property, if it exists.
     *
     * @return the value of the InputArguments Property, if it exists.
     * @see MethodNodeProperties#InputArguments
     */
    @Nullable
    public Argument[] getInputArguments() {
        return getProperty(MethodNodeProperties.InputArguments).orElse(null);
    }

    /**
     * Get the value of the OutputArguments Property, if it exists.
     *
     * @return the value of the OutputArguments Property, if it exists.
     * @see MethodNodeProperties#OutputArguments
     */
    @Nullable
    public Argument[] getOutputArguments() {
        return getProperty(MethodNodeProperties.OutputArguments).orElse(null);
    }

    /**
     * Set the value of the NodeVersion Property.
     * <p>
     * A PropertyNode will be created if it does not already exist.
     *
     * @param nodeVersion the value to set.
     * @see MethodNodeProperties#NodeVersion
     */
    public void setNodeVersion(String nodeVersion) {
        setProperty(MethodNodeProperties.NodeVersion, nodeVersion);
    }

    /**
     * Set the value of the InputArguments Property.
     * <p>
     * A PropertyNode will be created if it does not already exist.
     *
     * @param inputArguments the value to set.
     * @see MethodNodeProperties#InputArguments
     */
    public void setInputArguments(Argument[] inputArguments) {
        setProperty(MethodNodeProperties.InputArguments, inputArguments);
    }

    /**
     * Set the value of the OutputArguments Property.
     * <p>
     * A PropertyNode will be created if it does not already exist.
     *
     * @param outputArguments the value to set.
     * @see MethodNodeProperties#OutputArguments
     */
    public void setOutputArguments(Argument[] outputArguments) {
        setProperty(MethodNodeProperties.OutputArguments, outputArguments);
    }

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
