/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.jetbrains.annotations.Nullable;

public enum BuiltinReferenceType implements ReferenceType {

    References(
        NodeIds.References,
        "References",
        null,
        true, true, null),

    HierarchicalReferences(
        NodeIds.HierarchicalReferences,
        "HierarchicalReferences",
        null,
        false, true, References),

    NonHierarchicalReferences(
        NodeIds.NonHierarchicalReferences,
        "NonHierarchicalReferences",
        null,
        true, true, References),

    HasChild(
        NodeIds.HasChild,
        "HasChild",
        null,
        false, true, HierarchicalReferences),

    Aggregates(
        NodeIds.Aggregates,
        "Aggregates",
        null,
        false, true, HasChild),

    Organizes(
        NodeIds.Organizes,
        "Organizes",
        "OrganizedBy",
        false, false, HierarchicalReferences),

    HasComponent(
        NodeIds.HasComponent,
        "HasComponent",
        "ComponentOf",
        false, false, Aggregates),

    HasOrderedComponent(
        NodeIds.HasOrderedComponent,
        "HasOrderedComponent",
        "OrderedComponentOf",
        false, false, HasComponent),

    HasProperty(
        NodeIds.HasProperty,
        "HasProperty",
        "PropertyOf",
        false, false, Aggregates),

    HasSubtype(
        NodeIds.HasSubtype,
        "HasSubtype",
        "SubtypeOf", false, false, HasChild),

    HasModellingRule(
        NodeIds.HasModellingRule,
        "HasModellingRule",
        "ModellingRuleOf",
        false, false, NonHierarchicalReferences),

    HasTypeDefinition(
        NodeIds.HasTypeDefinition,
        "HasTypeDefinition",
        "TypeDefinitionOf",
        false, false, NonHierarchicalReferences),

    HasEncoding(
        NodeIds.HasEncoding,
        "HasEncoding",
        "EncodingOf",
        false, false, NonHierarchicalReferences),

    HasDescription(
        NodeIds.HasDescription,
        "HasDescription",
        "DescriptionOf",
        false, false, NonHierarchicalReferences),

    HasEventSource(
        NodeIds.HasEventSource,
        "HasEventSource",
        "EventSourceOf",
        false, false, HierarchicalReferences),

    HasNotifier(
        NodeIds.HasNotifier,
        "HasNotifier",
        "NotifierOf",
        false, false, HasEventSource),

    GeneratesEvent(
        NodeIds.GeneratesEvent,
        "GeneratesEvent",
        "GeneratedBy",
        false, false, NonHierarchicalReferences),

    AlwaysGeneratesEvent(
        NodeIds.AlwaysGeneratesEvent,
        "AlwaysGeneratesEvent",
        "AlwaysGeneratedBy",
        false, false, GeneratesEvent),

    FromState(
        NodeIds.FromState,
        "FromState",
        "ToTransition",
        false, false, NonHierarchicalReferences),

    ToState(
        NodeIds.ToState,
        "ToState",
        "FromTransition",
        false, false, NonHierarchicalReferences),

    HasCause(
        NodeIds.HasCause,
        "HasCause",
        "MayBeCausedBy",
        false, false, NonHierarchicalReferences),

    HasEffect(
        NodeIds.HasEffect,
        "HasEffect",
        "MayBeEffectedBy",
        false, false, NonHierarchicalReferences),

    HasSubStateMachine(
        NodeIds.HasSubStateMachine,
        "HasSubStateMachine",
        "SubStateMachineOf",
        false, false, NonHierarchicalReferences),

    HasTrueSubState(
        NodeIds.HasTrueSubState,
        "HasTrueSubState",
        "IsTrueSubStateOf",
        false, false, NonHierarchicalReferences),

    HasFalseSubState(
        NodeIds.HasFalseSubState,
        "HasFalseSubState",
        "IsFalseSubStateOf",
        false, false, NonHierarchicalReferences);

    private final NodeId nodeId;
    private final QualifiedName browseName;
    private final String inverseName;
    private final boolean symmetric;
    private final boolean isAbstract;
    private final NodeId superTypeId;

    BuiltinReferenceType(
        NodeId nodeId,
        String browseName,
        @Nullable String inverseName,
        boolean symmetric,
        boolean isAbstract,
        @Nullable BuiltinReferenceType superType) {

        this.nodeId = nodeId;
        this.browseName = new QualifiedName(0, browseName);
        this.inverseName = inverseName;
        this.symmetric = symmetric;
        this.isAbstract = isAbstract;

        if (superType != null) {
            this.superTypeId = superType.getNodeId();
        } else {
            this.superTypeId = null;
        }
    }

    @Override
    public NodeId getNodeId() {
        return nodeId;
    }

    @Override
    public QualifiedName getBrowseName() {
        return browseName;
    }

    @Override
    public Optional<String> getInverseName() {
        return Optional.ofNullable(inverseName);
    }

    @Override
    public boolean isSymmetric() {
        return symmetric;
    }

    @Override
    public boolean isAbstract() {
        return isAbstract;
    }

    @Override
    public Optional<NodeId> getSuperTypeId() {
        return Optional.ofNullable(superTypeId);
    }

    private static final Map<NodeId, ReferenceType> BUILTIN_REFERENCE_MAP;

    static {
        Map<NodeId, ReferenceType> map = new HashMap<>();
        for (ReferenceType referenceType : BuiltinReferenceType.values()) {
            map.put(referenceType.getNodeId(), referenceType);
        }
        BUILTIN_REFERENCE_MAP = Map.copyOf(map);
    }

    public static Map<NodeId, ReferenceType> getReferenceMap() {
        return BUILTIN_REFERENCE_MAP;
    }

}
