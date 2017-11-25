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

import java.lang.ref.WeakReference;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import javax.annotation.Nonnull;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import org.eclipse.milo.opcua.sdk.core.Reference;
import org.eclipse.milo.opcua.sdk.core.model.Property;
import org.eclipse.milo.opcua.sdk.core.model.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.server.api.ServerNodeMap;
import org.eclipse.milo.opcua.sdk.server.api.nodes.Node;
import org.eclipse.milo.opcua.sdk.server.api.nodes.ObjectNode;
import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.server.nodes.delegates.AttributeDelegate;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.eclipse.milo.opcua.sdk.core.util.StreamUtil.opt2stream;

public abstract class UaNode implements ServerNode {

    private static final Logger LOGGER = LoggerFactory.getLogger(UaNode.class);

    private static final AttributeDelegate DEFAULT_ATTRIBUTE_DELEGATE = AttributeDelegate.DEFAULT;

    private final AtomicInteger refCount = new AtomicInteger(0);

    private final List<Reference> references = new CopyOnWriteArrayList<>();

    private final AtomicReference<AttributeDelegate> attributeDelegate =
        new AtomicReference<>(DEFAULT_ATTRIBUTE_DELEGATE);

    private List<WeakReference<AttributeObserver>> observers;

    private final ServerNodeMap nodeMap;

    private volatile NodeId nodeId;
    private volatile NodeClass nodeClass;
    private volatile QualifiedName browseName;
    private volatile LocalizedText displayName;
    private volatile LocalizedText description;
    private volatile UInteger writeMask;
    private volatile UInteger userWriteMask;

    protected UaNode(
        ServerNodeMap nodeMap,
        NodeId nodeId,
        NodeClass nodeClass,
        QualifiedName browseName,
        LocalizedText displayName) {

        this(nodeMap, nodeId, nodeClass, browseName,
            displayName, LocalizedText.NULL_VALUE, UInteger.MIN, UInteger.MIN);
    }

    protected UaNode(
        ServerNodeMap nodeMap,
        NodeId nodeId,
        NodeClass nodeClass,
        QualifiedName browseName,
        LocalizedText displayName,
        LocalizedText description,
        UInteger writeMask,
        UInteger userWriteMask) {

        this.nodeMap = nodeMap;

        this.nodeId = nodeId;
        this.nodeClass = nodeClass;
        this.browseName = browseName;
        this.displayName = displayName;
        this.description = description;
        this.writeMask = writeMask;
        this.userWriteMask = userWriteMask;
    }

    @Override
    public NodeId getNodeId() {
        return nodeId;
    }

    @Override
    public NodeClass getNodeClass() {
        return nodeClass;
    }

    @Override
    public QualifiedName getBrowseName() {
        return browseName;
    }

    @Override
    public LocalizedText getDisplayName() {
        return displayName;
    }

    @Override
    public LocalizedText getDescription() {
        return description;
    }

    @Override
    public UInteger getWriteMask() {
        return writeMask;
    }

    @Override
    public UInteger getUserWriteMask() {
        return userWriteMask;
    }

    @Override
    public synchronized void setNodeId(NodeId nodeId) {
        this.nodeId = nodeId;

        fireAttributeChanged(AttributeId.NodeId, nodeId);
    }

    @Override
    public synchronized void setNodeClass(NodeClass nodeClass) {
        this.nodeClass = nodeClass;

        fireAttributeChanged(AttributeId.NodeClass, nodeClass);
    }

    @Override
    public synchronized void setBrowseName(QualifiedName browseName) {
        this.browseName = browseName;

        fireAttributeChanged(AttributeId.BrowseName, browseName);
    }

    @Override
    public synchronized void setDisplayName(LocalizedText displayName) {
        this.displayName = displayName;

        fireAttributeChanged(AttributeId.DisplayName, displayName);
    }

    @Override
    public synchronized void setDescription(LocalizedText description) {
        this.description = description;

        fireAttributeChanged(AttributeId.Description, description);
    }

    @Override
    public synchronized void setWriteMask(UInteger writeMask) {
        this.writeMask = writeMask;

        fireAttributeChanged(AttributeId.WriteMask, writeMask);
    }

    @Override
    public synchronized void setUserWriteMask(UInteger userWriteMask) {
        this.userWriteMask = userWriteMask;

        fireAttributeChanged(AttributeId.UserWriteMask, userWriteMask);
    }

    public ServerNodeMap getNodeMap() {
        return nodeMap;
    }

    protected Optional<ServerNode> getNode(NodeId nodeId) {
        return nodeMap.getNode(nodeId);
    }

    protected Optional<ServerNode> getNode(ExpandedNodeId nodeId) {
        return nodeMap.getNode(nodeId);
    }

    public ImmutableList<Reference> getReferences() {
        return ImmutableList.copyOf(references);
    }

    public synchronized void addReference(Reference reference) {
        references.add(reference);

        if (reference.isInverse()) {
            int count = refCount.incrementAndGet();
            LOGGER.trace("{} refCount={}", getNodeId(), count);

            if (count == 1) {
                nodeMap.addNode(this);
            }
        }
    }

    public synchronized void addReferences(Collection<Reference> c) {
        c.forEach(this::addReference);
    }

    public synchronized void removeReference(Reference reference) {
        references.remove(reference);

        if (reference.isInverse()) {
            int count = refCount.decrementAndGet();
            LOGGER.trace("{} refCount={}", getNodeId(), count);

            if (count == 0) {
                deallocate();
            }
        }
    }

    public synchronized void removeReferences(Collection<Reference> c) {
        c.forEach(this::removeReference);
    }

    protected synchronized void deallocate() {
        LOGGER.trace("{} deallocate()", getNodeId());

        ExpandedNodeId expanded = getNodeId().expanded();

        List<ServerNode> referencedNodes = getReferences().stream()
            .filter(Reference::isForward)
            .flatMap(r -> opt2stream(getNode(r.getTargetNodeId())))
            .collect(Collectors.toList());

        for (ServerNode node : referencedNodes) {
            List<Reference> inverseReferences = node.getReferences().stream()
                .filter(Reference::isInverse)
                .filter(r -> r.getTargetNodeId().equals(expanded))
                .collect(Collectors.toList());

            node.removeReferences(inverseReferences);
        }

        nodeMap.removeNode(getNodeId());
    }

    public <T> Optional<T> getProperty(Property<T> property) {
        return getProperty(property.getBrowseName());
    }

    public <T> Optional<T> getProperty(QualifiedProperty<T> property) {
        return getProperty(property.getBrowseName());
    }

    public <T> Optional<T> getProperty(String browseName) {
        return getProperty(new QualifiedName(getNodeId().getNamespaceIndex(), browseName));
    }

    public <T> Optional<T> getProperty(QualifiedName browseName) {
        Node node = getPropertyNode(browseName).orElse(null);

        try {
            return Optional.ofNullable((T) ((VariableNode) node).getValue().getValue().getValue());
        } catch (Throwable t) {
            return Optional.empty();
        }
    }

    public <T> void setProperty(Property<T> property, T value) {
        VariableNode node = getPropertyNode(property.getBrowseName()).orElseGet(() -> {
            QualifiedName browseName = property.getBrowseName();

            NodeId propertyNodeId = new NodeId(
                getNodeId().getNamespaceIndex(),
                String.format("%s.%s", getNodeId().getIdentifier().toString(), browseName.getName())
            );

            UaPropertyNode propertyNode = new UaPropertyNode(
                getNodeMap(),
                propertyNodeId,
                browseName,
                LocalizedText.english(browseName.getName())
            );

            propertyNode.setDataType(property.getDataType());
            propertyNode.setValueRank(property.getValueRank());

            addProperty(propertyNode);

            return propertyNode;
        });

        node.setValue(new DataValue(new Variant(value)));
    }

    public <T> void setProperty(QualifiedProperty<T> property, T value) {
        VariableNode node = getPropertyNode(property.getBrowseName()).orElseGet(() -> {
            String browseName = property.getBrowseName();

            NodeId propertyNodeId = new NodeId(
                getNodeId().getNamespaceIndex(),
                String.format("%s.%s", getNodeId().getIdentifier().toString(), browseName)
            );

            UaPropertyNode propertyNode = new UaPropertyNode(
                getNodeMap(),
                propertyNodeId,
                new QualifiedName(getNodeId().getNamespaceIndex(), browseName),
                LocalizedText.english(browseName)
            );

            propertyNode.setDataType(property.getDataType());
            propertyNode.setValueRank(property.getValueRank());

            addProperty(propertyNode);

            return propertyNode;
        });

        node.setValue(new DataValue(new Variant(value)));
    }

    public Optional<VariableNode> getPropertyNode(Property<?> property) {
        return getPropertyNode(property.getBrowseName());
    }

    public Optional<VariableNode> getPropertyNode(QualifiedProperty<?> property) {
        return getPropertyNode(property.getBrowseName());
    }

    public Optional<VariableNode> getPropertyNode(String browseName) {
        return getPropertyNode(new QualifiedName(getNodeId().getNamespaceIndex(), browseName));
    }

    public Optional<VariableNode> getPropertyNode(QualifiedName browseName) {
        Node node = references.stream()
            .filter(Reference.HAS_PROPERTY_PREDICATE)
            .flatMap(r -> opt2stream(getNode(r.getTargetNodeId())))
            .filter(n -> n.getBrowseName().equals(browseName))
            .findFirst().orElse(null);

        try {
            return Optional.ofNullable((VariableNode) node);
        } catch (Throwable t) {
            return Optional.empty();
        }
    }

    public void addProperty(UaVariableNode node) {
        addReference(new Reference(
            getNodeId(),
            Identifiers.HasProperty,
            node.getNodeId().expanded(),
            NodeClass.Variable,
            true
        ));

        node.addReference(new Reference(
            node.getNodeId(),
            Identifiers.HasProperty,
            getNodeId().expanded(),
            getNodeClass(),
            false
        ));
    }

    public void removeProperty(UaVariableNode node) {
        removeReference(new Reference(
            getNodeId(),
            Identifiers.HasProperty,
            node.getNodeId().expanded(),
            NodeClass.Variable,
            true
        ));

        node.removeReference(new Reference(
            node.getNodeId(),
            Identifiers.HasProperty,
            getNodeId().expanded(),
            getNodeClass(),
            false
        ));
    }

    protected Optional<ObjectNode> getObjectComponent(String namespaceUri, String name) {
        UShort namespaceIndex = nodeMap.getNamespaceTable().getIndex(namespaceUri);

        if (namespaceIndex != null) {
            return getObjectComponent(new QualifiedName(namespaceIndex, name));
        } else {
            return Optional.empty();
        }
    }

    protected Optional<ObjectNode> getObjectComponent(String browseName) {
        return getObjectComponent(new QualifiedName(getNodeId().getNamespaceIndex(), browseName));
    }

    protected Optional<ObjectNode> getObjectComponent(QualifiedName browseName) {
        ObjectNode node = (ObjectNode) references.stream()
            .filter(Reference.HAS_COMPONENT_PREDICATE.and(r -> r.getTargetNodeClass() == NodeClass.Object))
            .flatMap(r -> opt2stream(getNode(r.getTargetNodeId())))
            .filter(n -> n.getBrowseName().equals(browseName))
            .findFirst().orElse(null);

        return Optional.ofNullable(node);
    }

    protected Optional<VariableNode> getVariableComponent(String namespaceUri, String name) {
        UShort namespaceIndex = nodeMap.getNamespaceTable().getIndex(namespaceUri);

        if (namespaceIndex != null) {
            return getVariableComponent(new QualifiedName(namespaceIndex, name));
        } else {
            return Optional.empty();
        }
    }

    protected Optional<VariableNode> getVariableComponent(String browseName) {
        return getVariableComponent(new QualifiedName(getNodeId().getNamespaceIndex(), browseName));
    }

    protected Optional<VariableNode> getVariableComponent(QualifiedName browseName) {
        VariableNode node = (VariableNode) references.stream()
            .filter(Reference.HAS_COMPONENT_PREDICATE.and(r -> r.getTargetNodeClass() == NodeClass.Variable))
            .flatMap(r -> opt2stream(getNode(r.getTargetNodeId())))
            .filter(n -> n.getBrowseName().equals(browseName))
            .findFirst().orElse(null);

        return Optional.ofNullable(node);
    }

    public synchronized void addAttributeObserver(AttributeObserver observer) {
        if (observers == null) {
            observers = new LinkedList<>();
        }

        observers.add(new WeakReference<>(observer));
    }

    public synchronized void removeAttributeObserver(AttributeObserver observer) {
        if (observers == null) return;

        Iterator<WeakReference<AttributeObserver>> iterator = observers.iterator();

        while (iterator.hasNext()) {
            WeakReference<AttributeObserver> ref = iterator.next();
            if (ref.get() == null || ref.get() == observer) {
                iterator.remove();
            }
        }

        if (observers.isEmpty()) observers = null;
    }

    protected synchronized void fireAttributeChanged(AttributeId attributeId, Object attributeValue) {
        if (observers == null) return;

        Iterator<WeakReference<AttributeObserver>> iterator = observers.iterator();

        while (iterator.hasNext()) {
            WeakReference<AttributeObserver> ref = iterator.next();
            AttributeObserver observer = ref.get();
            if (observer != null) {
                observer.attributeChanged(this, attributeId, attributeValue);
            } else {
                iterator.remove();
            }
        }
    }

    /**
     * Set the {@link AttributeDelegate} for this node.
     * <p>
     * Shall be non-null. To revert from a custom delegate to default behavior set {@link AttributeDelegate#DEFAULT}.
     *
     * @param attributeDelegate the {@link AttributeDelegate}.
     */
    public void setAttributeDelegate(@Nonnull AttributeDelegate attributeDelegate) {
        Preconditions.checkNotNull(attributeDelegate);

        this.attributeDelegate.set(attributeDelegate);
    }

    @Override
    public DataValue getAttribute(AttributeContext context, AttributeId attributeId) {
        return attributeDelegate.get().getAttribute(context, this, attributeId);
    }

    @Override
    public void setAttribute(AttributeContext context,
                             AttributeId attributeId,
                             DataValue value) throws UaException {

        attributeDelegate.get().setAttribute(context, this, attributeId, value);
    }

}
