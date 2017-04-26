/*
 * Copyright (c) 2016 Kevin Herron
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v1.0 which accompany this distribution.
 *
 * The Eclipse Public License is available at
 *   http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 *   http://www.eclipse.org/org/documents/edl-v10.html.
 */

package org.eclipse.milo.opcua.sdk.server.nodes;

import java.util.List;
import java.util.function.Supplier;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import org.eclipse.milo.opcua.sdk.core.Reference;
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.sdk.core.model.BasicProperty;
import org.eclipse.milo.opcua.sdk.core.model.Property;
import org.eclipse.milo.opcua.sdk.core.model.UaOptional;
import org.eclipse.milo.opcua.sdk.server.api.ServerNodeMap;
import org.eclipse.milo.opcua.sdk.server.api.nodes.ObjectTypeNode;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;

public class UaObjectTypeNode extends UaNode implements ObjectTypeNode {

    private volatile Boolean isAbstract;

    public UaObjectTypeNode(
        ServerNodeMap nodeMap,
        NodeId nodeId,
        QualifiedName browseName,
        LocalizedText displayName,
        LocalizedText description,
        UInteger writeMask,
        UInteger userWriteMask,
        Boolean isAbstract) {

        super(nodeMap, nodeId, NodeClass.ObjectType,
            browseName, displayName, description, writeMask, userWriteMask);

        this.isAbstract = isAbstract;
    }

    @Override
    public Boolean getIsAbstract() {
        return isAbstract;
    }

    @Override
    public synchronized void setIsAbstract(Boolean isAbstract) {
        this.isAbstract = isAbstract;

        fireAttributeChanged(AttributeId.IsAbstract, isAbstract);
    }

    /**
     * Add a 'HasComponent' reference from this node to {@code node} and an inverse 'ComponentOf' reference from
     * {@code node} back to this node.
     *
     * @param node the node to add as a component of this node.
     */
    public void addComponent(UaNode node) {
        addReference(new Reference(
            getNodeId(),
            Identifiers.HasComponent,
            node.getNodeId().expanded(),
            node.getNodeClass(),
            true
        ));

        node.addReference(new Reference(
            node.getNodeId(),
            Identifiers.HasComponent,
            getNodeId().expanded(),
            getNodeClass(),
            false
        ));
    }

    /**
     * Add a 'HasSubtype' reference from this node to {@code node} and an inverse 'SubtypeOf' reference from
     * {@code node} back to this node.
     *
     * @param node the node to add as a subtype of this node.
     */
    public void addSubtype(UaObjectTypeNode node) {
        addReference(new Reference(
            getNodeId(),
            Identifiers.HasSubtype,
            node.getNodeId().expanded(),
            node.getNodeClass(),
            true
        ));

        node.addReference(new Reference(
            node.getNodeId(),
            Identifiers.HasSubtype,
            getNodeId().expanded(),
            getNodeClass(),
            false
        ));
    }

    @UaOptional("NodeVersion")
    public String getNodeVersion() {
        return getProperty(NodeVersion).orElse(null);
    }

    @UaOptional("Icon")
    public ByteString getIcon() {
        return getProperty(Icon).orElse(null);
    }

    public static final Property<String> NodeVersion = new BasicProperty<>(
        new QualifiedName(0, "NodeVersion"),
        Identifiers.String,
        ValueRanks.Scalar,
        String.class
    );

    public static final Property<ByteString> Icon = new BasicProperty<>(
        new QualifiedName(0, "Icon"),
        Identifiers.Image,
        ValueRanks.Scalar,
        ByteString.class
    );

    public static UaObjectTypeNodeBuilder builder(ServerNodeMap nodeMap) {
        return new UaObjectTypeNodeBuilder(nodeMap);
    }

    public static class UaObjectTypeNodeBuilder implements Supplier<UaObjectTypeNode> {

        private final List<Reference> references = Lists.newArrayList();

        private NodeId nodeId;
        private QualifiedName browseName;
        private LocalizedText displayName;
        private LocalizedText description = LocalizedText.NULL_VALUE;
        private UInteger writeMask = UInteger.MIN;
        private UInteger userWriteMask = UInteger.MIN;
        private boolean isAbstract = false;

        private final ServerNodeMap nodeMap;

        public UaObjectTypeNodeBuilder(ServerNodeMap nodeMap) {
            this.nodeMap = nodeMap;
        }

        public UaObjectTypeNodeBuilder setNodeId(NodeId nodeId) {
            this.nodeId = nodeId;
            return this;
        }

        public UaObjectTypeNodeBuilder setBrowseName(QualifiedName browseName) {
            this.browseName = browseName;
            return this;
        }

        public UaObjectTypeNodeBuilder setDisplayName(LocalizedText displayName) {
            this.displayName = displayName;
            return this;
        }

        public UaObjectTypeNodeBuilder setDescription(LocalizedText description) {
            this.description = description;
            return this;
        }

        public UaObjectTypeNodeBuilder setWriteMask(UInteger writeMask) {
            this.writeMask = writeMask;
            return this;
        }

        public UaObjectTypeNodeBuilder setUserWriteMask(UInteger userWriteMask) {
            this.userWriteMask = userWriteMask;
            return this;
        }

        public UaObjectTypeNodeBuilder setIsAbstract(boolean isAbstract) {
            this.isAbstract = isAbstract;
            return this;
        }

        public UaObjectTypeNodeBuilder addReference(Reference reference) {
            references.add(reference);
            return this;
        }

        @Override
        public UaObjectTypeNode get() {
            return build();
        }

        public UaObjectTypeNode build() {
            Preconditions.checkNotNull(nodeId, "NodeId cannot be null");
            Preconditions.checkNotNull(browseName, "BrowseName cannot be null");
            Preconditions.checkNotNull(displayName, "DisplayName cannot be null");

            return new UaObjectTypeNode(
                nodeMap,
                nodeId,
                browseName,
                displayName,
                description,
                writeMask,
                userWriteMask,
                isAbstract
            );
        }
    }

}
