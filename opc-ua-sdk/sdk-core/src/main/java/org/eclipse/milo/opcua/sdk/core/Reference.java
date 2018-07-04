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

package org.eclipse.milo.opcua.sdk.core;

import java.util.Map;
import java.util.function.Predicate;

import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.ReferenceType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;
import org.slf4j.LoggerFactory;

public class Reference {

    private final NodeId sourceNodeId;
    private final NodeId referenceTypeId;
    private final ExpandedNodeId targetNodeId;
    private final NodeClass targetNodeClass;
    private final boolean forward;

    public Reference(NodeId sourceNodeId,
                     NodeId referenceTypeId,
                     ExpandedNodeId targetNodeId,
                     NodeClass targetNodeClass,
                     boolean forward) {

        this.sourceNodeId = sourceNodeId;
        this.referenceTypeId = referenceTypeId;
        this.targetNodeId = targetNodeId;
        this.targetNodeClass = targetNodeClass;
        this.forward = forward;
    }

    public NodeId getSourceNodeId() {
        return sourceNodeId;
    }

    public NodeId getReferenceTypeId() {
        return referenceTypeId;
    }

    public ExpandedNodeId getTargetNodeId() {
        return targetNodeId;
    }

    public NodeClass getTargetNodeClass() {
        return targetNodeClass;
    }

    public boolean isForward() {
        return forward;
    }

    public boolean isInverse() {
        return !isForward();
    }

    public boolean subtypeOf(NodeId superTypeId, Map<NodeId, ReferenceType> referenceTypes) {
        return subtypeOf(referenceTypeId, superTypeId, referenceTypes);
    }

    private boolean subtypeOf(NodeId typeId, NodeId superTypeId, Map<NodeId, ReferenceType> referenceTypes) {
        ReferenceType referenceType = referenceTypes.get(typeId);

        if (referenceType == null) {
            LoggerFactory.getLogger(getClass()).warn("Unknown reference type: {}", typeId);
            return false;
        }

        return referenceType.getSuperTypeId()
            .map(id -> id.equals(superTypeId) || subtypeOf(id, superTypeId, referenceTypes))
            .orElse(false);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Reference reference = (Reference) o;

        return forward == reference.forward &&
            referenceTypeId.equals(reference.referenceTypeId) &&
            sourceNodeId.equals(reference.sourceNodeId) &&
            targetNodeClass == reference.targetNodeClass &&
            targetNodeId.equals(reference.targetNodeId);
    }

    @Override
    public int hashCode() {
        int result = sourceNodeId.hashCode();
        result = 31 * result + referenceTypeId.hashCode();
        result = 31 * result + targetNodeId.hashCode();
        result = 31 * result + targetNodeClass.hashCode();
        result = 31 * result + (forward ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Reference{" +
            "sourceNodeId=" + sourceNodeId +
            ", referenceTypeId=" + referenceTypeId +
            ", targetNodeId=" + targetNodeId +
            ", targetNodeClass=" + targetNodeClass +
            ", forward=" + forward +
            '}';
    }

    public static final Predicate<Reference> HAS_COMPONENT_PREDICATE =
        (reference) -> reference.isForward() && Identifiers.HasComponent.equals(reference.getReferenceTypeId());

    public static final Predicate<Reference> HAS_PROPERTY_PREDICATE =
        (reference) -> reference.isForward() && Identifiers.HasProperty.equals(reference.getReferenceTypeId());

    public static final Predicate<Reference> HAS_TYPE_DEFINITION_PREDICATE =
        (reference) -> reference.isForward() && Identifiers.HasTypeDefinition.equals(reference.getReferenceTypeId());

    public static final Predicate<Reference> HAS_EVENT_SOURCE_PREDICATE =
        (reference) -> reference.isForward() && Identifiers.HasEventSource.equals(reference.getReferenceTypeId());

    public static final Predicate<Reference> HAS_NOTIFIER_PREDICATE =
        (reference) -> reference.isForward() && Identifiers.HasNotifier.equals(reference.getReferenceTypeId());

    public static final Predicate<Reference> ORGANIZES_PREDICATE =
        (reference) -> reference.isForward() && Identifiers.Organizes.equals(reference.getReferenceTypeId());

    public static final Predicate<Reference> HAS_DESCRIPTION_PREDICATE =
        (reference) -> reference.isForward() && Identifiers.HasDescription.equals(reference.getReferenceTypeId());

    public static final Predicate<Reference> HAS_MODELLING_RULE_PREDICATE =
        (reference) -> reference.isForward() && Identifiers.HasModellingRule.equals(reference.getReferenceTypeId());

    public static final Predicate<Reference> ALWAYS_GENERATES_EVENT_PREDICATE =
        (reference) -> reference.isForward() && Identifiers.AlwaysGeneratesEvent.equals(reference.getReferenceTypeId());

    public static final Predicate<Reference> HAS_SUBTYPE =
        (reference) -> reference.isForward() && Identifiers.HasSubtype.equals(reference.getReferenceTypeId());

    public static final Predicate<Reference> SUBTYPE_OF =
        (reference) -> reference.isInverse() && Identifiers.HasSubtype.equals(reference.getReferenceTypeId());

}
