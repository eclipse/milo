/*
 * Copyright (c) 2017 Kevin Herron
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

package org.eclipse.milo.opcua.sdk.server.events;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.common.collect.ImmutableSet;
import org.eclipse.milo.opcua.sdk.core.Reference;
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.Session;
import org.eclipse.milo.opcua.sdk.server.UaNodeManager;
import org.eclipse.milo.opcua.sdk.server.api.nodes.Node;
import org.eclipse.milo.opcua.sdk.server.api.nodes.ObjectTypeNode;
import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.server.events.operators.Operator;
import org.eclipse.milo.opcua.sdk.server.events.operators.Operators;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.BaseEventNode;
import org.eclipse.milo.opcua.sdk.server.nodes.AttributeContext;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNode;
import org.eclipse.milo.opcua.sdk.server.util.AttributeReader;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.DiagnosticInfo;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.FilterOperator;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;
import org.eclipse.milo.opcua.stack.core.types.structured.AttributeOperand;
import org.eclipse.milo.opcua.stack.core.types.structured.ContentFilter;
import org.eclipse.milo.opcua.stack.core.types.structured.ContentFilterElement;
import org.eclipse.milo.opcua.stack.core.types.structured.ContentFilterElementResult;
import org.eclipse.milo.opcua.stack.core.types.structured.ContentFilterResult;
import org.eclipse.milo.opcua.stack.core.types.structured.ElementOperand;
import org.eclipse.milo.opcua.stack.core.types.structured.EventFilter;
import org.eclipse.milo.opcua.stack.core.types.structured.EventFilterResult;
import org.eclipse.milo.opcua.stack.core.types.structured.FilterOperand;
import org.eclipse.milo.opcua.stack.core.types.structured.LiteralOperand;
import org.eclipse.milo.opcua.stack.core.types.structured.SimpleAttributeOperand;
import org.jooq.lambda.tuple.Tuple2;

import static java.util.Collections.nCopies;
import static org.eclipse.milo.opcua.sdk.core.util.StreamUtil.opt2stream;
import static org.eclipse.milo.opcua.stack.core.util.ConversionUtil.l;

public class EventContentFilter {

    public static EventFilterResult validate(FilterContext context, EventFilter filter) throws UaException {
        SimpleAttributeOperand[] selectClauses = filter.getSelectClauses();

        if (selectClauses == null || selectClauses.length == 0) {
            // A valid filter has at least one select clause
            throw new UaException(StatusCodes.Bad_EventFilterInvalid);
        }

        Tuple2<StatusCode[], DiagnosticInfo[]> selectClauseResults =
            validateSelectClauses(context, selectClauses);

        ContentFilterResult whereClauseResult = validateWhereClause(context, filter.getWhereClause());

        return new EventFilterResult(
            selectClauseResults.v1(),
            selectClauseResults.v2(),
            whereClauseResult
        );
    }

    private static Tuple2<StatusCode[], DiagnosticInfo[]> validateSelectClauses(
        FilterContext context,
        SimpleAttributeOperand[] selectClauses) {

        List<StatusCode> statusCodes = new ArrayList<>();
        List<DiagnosticInfo> diagnosticInfos = new ArrayList<>();

        for (SimpleAttributeOperand select : selectClauses) {
            try {
                validateSimpleOperand(context, select);

                statusCodes.add(StatusCode.GOOD);
                diagnosticInfos.add(DiagnosticInfo.NULL_VALUE);
            } catch (ValidationException e) {
                statusCodes.add(e.getStatusCode());
                diagnosticInfos.add(e.getDiagnosticInfo());
            }
        }

        return new Tuple2<>(
            statusCodes.toArray(new StatusCode[0]),
            diagnosticInfos.toArray(new DiagnosticInfo[0]));
    }

    private static void validateSimpleOperand(
        FilterContext context,
        SimpleAttributeOperand select) throws ValidationException {

        NodeId eventTypeId = select.getTypeDefinitionId();

        if (eventTypeId != null && !eventTypeId.equals(Identifiers.BaseEventType)) {
            UaNode node = context.getServer().getNodeManager().get(eventTypeId);

            if (node == null || node.getNodeClass() != NodeClass.ObjectType) {
                throw new ValidationException(StatusCodes.Bad_TypeDefinitionInvalid);
            }

            QualifiedName[] browsePath = select.getBrowsePath();

            if (browsePath == null || Arrays.stream(browsePath).anyMatch(Objects::isNull)) {
                throw new ValidationException(StatusCodes.Bad_BrowseNameInvalid);
            }

            Node relativeNode = getRelativeNode(context, node, browsePath);

            if (relativeNode == null) {
                throw new ValidationException(StatusCodes.Bad_NodeIdUnknown);
            }

            UInteger attributeId = select.getAttributeId();

            ImmutableSet<AttributeId> validAttributes =
                AttributeId.getAttributes(relativeNode.getNodeClass());

            boolean validAttribute = AttributeId.from(attributeId)
                .map(validAttributes::contains)
                .orElse(false);

            if (!validAttribute) {
                throw new ValidationException(StatusCodes.Bad_AttributeIdInvalid);
            }

            String indexRange = select.getIndexRange();
            int valueRank = ((VariableNode) relativeNode).getValueRank();

            if (valueRank == ValueRanks.Scalar && indexRange != null) {
                throw new ValidationException(StatusCodes.Bad_IndexRangeInvalid);
            }
        }
    }

    @Nullable
    private static Node getRelativeNode(
        FilterContext context,
        @Nonnull UaNode startingNode,
        @Nonnull QualifiedName[] browsePath) {

        UaNode relativeNode = startingNode;

        Predicate<Reference> references = r ->
            r.isForward() &&
                r.subtypeOf(Identifiers.HierarchicalReferences, context.getServer().getReferenceTypes()) &&
                (r.getTargetNodeClass() == NodeClass.Object || r.getTargetNodeClass() == NodeClass.Variable);

        // find the Node relative to eventNode using browsePath.
        for (QualifiedName targetBrowsePath : browsePath) {
            relativeNode = relativeNode
                .findNode(targetBrowsePath, references)
                .orElse(null);

            if (relativeNode == null) break;
        }

        return relativeNode;
    }

    private static ContentFilterResult validateWhereClause(
        FilterContext context,
        ContentFilter whereClause) {

        // TODO validate where clause

        List<ContentFilterElementResult> elementResults = new ArrayList<>();
        List<DiagnosticInfo> elementDiagnosticInfos = new ArrayList<>();

        List<ContentFilterElement> elements = l(whereClause.getElements());

        for (ContentFilterElement element : elements) {
            FilterOperator filterOperator = element.getFilterOperator();

            try {
                ExtensionObject[] xos = element.getFilterOperands();
                if (xos == null || xos.length == 0) {
                    throw new ValidationException(StatusCodes.Bad_FilterOperandCountMismatch);
                }

                FilterOperand[] operands = new FilterOperand[xos.length];

                for (int i = 0; i < xos.length; i++) {
                    FilterOperand operand = (FilterOperand) xos[i].decodeOrNull();

                    if (operand != null) {

                    }

                    operands[i] = operand;
                }

                Operator<?> operator = getOperator(filterOperator);
                operator.validate(context, operands);

                StatusCode[] operandStatusCodes = nCopies(
                    operands.length,
                    StatusCode.GOOD
                ).toArray(new StatusCode[0]);

                ContentFilterElementResult result = new ContentFilterElementResult(
                    StatusCode.GOOD,
                    operandStatusCodes,
                    new DiagnosticInfo[0]
                );

                elementResults.add(result);
                elementDiagnosticInfos.add(DiagnosticInfo.NULL_VALUE);
            } catch (ValidationException e) {
                ContentFilterElementResult result = new ContentFilterElementResult(
                    e.getStatusCode(),
                    new StatusCode[0],
                    new DiagnosticInfo[0]
                );

                elementResults.add(result);
                elementDiagnosticInfos.add(DiagnosticInfo.NULL_VALUE);
            }
        }

        return new ContentFilterResult(
            elementResults.toArray(new ContentFilterElementResult[0]),
            elementDiagnosticInfos.toArray(new DiagnosticInfo[0])
        );
    }

    public static Variant[] select(
        @Nonnull FilterContext context,
        @Nonnull SimpleAttributeOperand[] selectClauses,
        @Nonnull BaseEventNode eventNode) throws UaException {

        return Arrays.stream(selectClauses).map(operand -> {
            try {
                return new Variant(getSimpleAttribute(context, operand, eventNode));
            } catch (UaException e) {
                return Variant.NULL_VALUE;
            }
        }).toArray(Variant[]::new);
    }

    public static boolean evaluate(
        @Nonnull FilterContext context,
        @Nonnull ContentFilter whereClause,
        @Nonnull BaseEventNode eventNode) throws UaException {

        if (whereClause.getElements() == null || whereClause.getElements().length == 0) {
            return true;
        }

        ContentFilterElement[] elements = whereClause.getElements();

        OperatorContext operatorContext = new DefaultOperatorContext(context, elements);

        Object result = evaluate(operatorContext, eventNode, elements[0]);

        if (result == null) {
            return false;
        } else if (result instanceof Boolean) {
            return (Boolean) result;
        } else {
            throw new UaException(StatusCodes.Bad_ContentFilterInvalid);
        }
    }

    @Nullable
    private static Object evaluate(
        @Nonnull OperatorContext context,
        @Nonnull BaseEventNode eventNode,
        @Nonnull ContentFilterElement element) throws UaException {

        FilterOperator filterOperator = element.getFilterOperator();
        if (filterOperator == null) {
            throw new UaException(StatusCodes.Bad_FilterOperatorInvalid);
        }

        FilterOperand[] filterOperands = decodeOperands(element.getFilterOperands());

        Operator<?> operator = getOperator(filterOperator);

        return operator.apply(context, eventNode, filterOperands);
    }

    @Nonnull
    private static FilterOperand[] decodeOperands(@Nullable ExtensionObject[] operandXos) {
        if (operandXos == null) {
            return new FilterOperand[0];
        } else {
            return Arrays.stream(operandXos)
                .map(xo -> (FilterOperand) xo.decode())
                .toArray(FilterOperand[]::new);
        }
    }

    @Nonnull
    private static Operator<?> getOperator(@Nonnull FilterOperator filterOperator) {
        //@formatter:off
        switch (filterOperator) {
            // Basic FilterOperators
            case Equals:                return Operators.EQUALS;
            case IsNull:                return Operators.IS_NULL;
            case GreaterThan:           return Operators.GREATER_THAN;
            case LessThan:              return Operators.LESS_THAN;
            case GreaterThanOrEqual:    return Operators.GREATER_THAN_OR_EQUAL;
            case LessThanOrEqual:       return Operators.LESS_THAN_OR_EQUAL;
            case Like:                  return Operators.UNSUPPORTED;
            case Not:                   return Operators.NOT;
            case Between:               return Operators.UNSUPPORTED;
            case InList:                return Operators.UNSUPPORTED;
            case And:                   return Operators.UNSUPPORTED;
            case Or:                    return Operators.UNSUPPORTED;
            case Cast:                  return Operators.CAST;
            case BitwiseAnd:            return Operators.UNSUPPORTED;
            case BitwiseOr:             return Operators.UNSUPPORTED;

            // Complex FilterOperators
            case InView:                return Operators.UNSUPPORTED;
            case OfType:                return Operators.OF_TYPE;
            case RelatedTo:             return Operators.UNSUPPORTED;
            default:                    return Operators.UNSUPPORTED;
        }
        //@formatter:on
    }


    private static Object getAttribute(
        @Nonnull FilterContext context,
        @Nonnull AttributeOperand operand,
        @Nonnull BaseEventNode eventNode) throws UaException {

        // AttributeOperand is not allowed to be used in EventFilters... it's for the Query services.
        // Right now Query services are unsupported and this class exists to handle the application
        // of the whereClause of a ContentFilter to an event.
        throw new UaException(StatusCodes.Bad_EventFilterInvalid);
    }

    private static Object getSimpleAttribute(
        @Nonnull FilterContext context,
        @Nonnull SimpleAttributeOperand operand,
        @Nonnull BaseEventNode eventNode) throws UaException {

        NodeId typeDefinitionId = operand.getTypeDefinitionId();

        if (typeDefinitionId != null && !typeDefinitionId.equals(Identifiers.BaseEventType)) {
            NodeId eventTypeDefinitionId = eventNode.getTypeDefinitionNode().getNodeId();

            boolean sameOrSubtype = typeDefinitionId.equals(eventTypeDefinitionId) ||
                subtypeOf(eventTypeDefinitionId, typeDefinitionId, context.getServer().getNodeManager());

            if (!sameOrSubtype) {
                return null;
            }
        }

        QualifiedName[] browsePath = operand.getBrowsePath();

        UaNode targetNode = eventNode;

        if (browsePath != null) {
            Predicate<Reference> references = r ->
                r.isForward() &&
                    r.subtypeOf(Identifiers.HierarchicalReferences, context.getServer().getReferenceTypes()) &&
                    (r.getTargetNodeClass() == NodeClass.Object || r.getTargetNodeClass() == NodeClass.Variable);

            // find the Node relative to eventNode using browsePath.
            for (QualifiedName targetBrowsePath : browsePath) {
                targetNode = targetNode
                    .findNode(targetBrowsePath, references)
                    .orElse(null);

                if (targetNode == null) break;
            }
        }

        if (targetNode != null) {
            // read the attribute
            AttributeId attributeId = AttributeId.from(operand.getAttributeId())
                .orElseThrow(() -> new UaException(StatusCodes.Bad_AttributeIdInvalid));

            String indexRange = operand.getIndexRange();

            AttributeContext attributeContext = new AttributeContext(
                context.getServer(),
                context.getSession().orElse(null)
            );

            DataValue value = AttributeReader.readAttribute(
                attributeContext,
                targetNode,
                attributeId,
                TimestampsToReturn.Neither,
                indexRange,
                QualifiedName.NULL_VALUE
            );

            return value.getValue().getValue();
        } else {
            return null;
        }
    }

    private static boolean subtypeOf(NodeId typeId, NodeId superTypeId, UaNodeManager nodeManager) {
        UaNode node = nodeManager.get(typeId);

        if (node instanceof ObjectTypeNode) {
            return getParentTypeDefinition(node, nodeManager)
                .map(Node::getNodeId)
                .map(id -> id.equals(superTypeId) || subtypeOf(id, superTypeId, nodeManager))
                .orElse(false);
        } else {
            return false;
        }
    }

    private static Optional<UaNode> getParentTypeDefinition(UaNode node, UaNodeManager nodeManager) {
        return nodeManager.getReferences(node.getNodeId())
            .stream()
            .filter(Reference.SUBTYPE_OF)
            .flatMap(r -> opt2stream(r.getTargetNodeId().local()))
            .findFirst()
            .flatMap(nodeManager::getNode);
    }

    static class DefaultOperatorContext implements OperatorContext {

        private final FilterContext filterContext;
        private final ContentFilterElement[] elements;

        DefaultOperatorContext(FilterContext filterContext, ContentFilterElement[] elements) {
            this.filterContext = filterContext;
            this.elements = elements;
        }

        @Override
        public Optional<Session> getSession() {
            return filterContext.getSession();
        }

        @Override
        public OpcUaServer getServer() {
            return filterContext.getServer();
        }

        @Override
        public ContentFilterElement[] getElements() {
            return elements;
        }

        @Nullable
        @Override
        public Object resolve(FilterOperand operand, BaseEventNode eventNode) throws UaException {
            if (operand instanceof LiteralOperand) {
                return ((LiteralOperand) operand).getValue().getValue();
            } else if (operand instanceof ElementOperand) {
                UInteger index = ((ElementOperand) operand).getIndex();

                ContentFilterElement element = elements[index.intValue()];

                return evaluate(this, eventNode, element);
            } else if (operand instanceof AttributeOperand) {
                AttributeOperand ao = (AttributeOperand) operand;

                return getAttribute(filterContext, ao, eventNode);
            } else if (operand instanceof SimpleAttributeOperand) {
                SimpleAttributeOperand sao = (SimpleAttributeOperand) operand;

                return getSimpleAttribute(filterContext, sao, eventNode);
            } else {
                throw new UaException(StatusCodes.Bad_FilterOperandInvalid);
            }
        }

    }

}
