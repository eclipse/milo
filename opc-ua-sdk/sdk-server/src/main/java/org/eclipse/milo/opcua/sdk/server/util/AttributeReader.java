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

package org.eclipse.milo.opcua.sdk.server.util;

import java.util.Optional;
import java.util.Set;
import javax.annotation.Nullable;

import org.eclipse.milo.opcua.sdk.core.AccessLevel;
import org.eclipse.milo.opcua.sdk.core.NumericRange;
import org.eclipse.milo.opcua.sdk.server.api.ServerNodeMap;
import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableTypeNode;
import org.eclipse.milo.opcua.sdk.server.nodes.AttributeContext;
import org.eclipse.milo.opcua.sdk.server.nodes.ServerNode;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;

import static org.eclipse.milo.opcua.sdk.core.util.StreamUtil.opt2stream;
import static org.eclipse.milo.opcua.sdk.server.util.AttributeUtil.getAccessLevels;
import static org.eclipse.milo.opcua.sdk.server.util.AttributeUtil.getUserAccessLevels;

public class AttributeReader {

    private static QualifiedName ENCODING_DEFAULT_BINARY = new QualifiedName(0, "DefaultBinary");
    private static QualifiedName ENCODING_DEFAULT_XML = new QualifiedName(0, "DefaultXML");

    public static DataValue readAttribute(
        AttributeContext context,
        ServerNode node,
        AttributeId attributeId,
        @Nullable TimestampsToReturn timestamps,
        @Nullable String indexRange,
        @Nullable QualifiedName dataEncoding) {

        try {
            AttributeContext internalContext = new AttributeContext(context.getServer());

            NodeClass nodeClass = node.getNodeClass();

            if (attributeId == AttributeId.Value && nodeClass == NodeClass.Variable) {
                Set<AccessLevel> accessLevels = getAccessLevels(node, internalContext);
                if (!accessLevels.contains(AccessLevel.CurrentRead)) {
                    throw new UaException(StatusCodes.Bad_NotReadable);
                }

                Set<AccessLevel> userAccessLevels = getUserAccessLevels(node, context);
                if (!userAccessLevels.contains(AccessLevel.CurrentRead)) {
                    throw new UaException(StatusCodes.Bad_UserAccessDenied);
                }
            }

            if (dataEncoding != null && dataEncoding.isNotNull()) {
                if (attributeId != AttributeId.Value) {
                    throw new UaException(StatusCodes.Bad_DataEncodingInvalid);
                }

                NodeId dataTypeId;
                if (node instanceof VariableNode) {
                    dataTypeId = ((VariableNode) node).getDataType();
                } else if (node instanceof VariableTypeNode) {
                    dataTypeId = ((VariableTypeNode) node).getDataType();
                } else {
                    throw new UaException(StatusCodes.Bad_DataEncodingInvalid);
                }

                boolean structured = isStructureSubtype(context.getServer().getNodeMap(), dataTypeId);

                if (!structured) {
                    throw new UaException(StatusCodes.Bad_DataEncodingInvalid);
                }

                if (!dataEncoding.equals(ENCODING_DEFAULT_BINARY)) {
                    throw new UaException(StatusCodes.Bad_DataEncodingUnsupported);
                }
            }

            DataValue value = node.getAttribute(context, attributeId);

            if (indexRange != null) {
                NumericRange range = NumericRange.parse(indexRange);

                Object valueAtRange = NumericRange.readFromValueAtRange(value.getValue(), range);

                value = new DataValue(
                    new Variant(valueAtRange),
                    value.getStatusCode(),
                    value.getSourceTime(),
                    value.getServerTime()
                );
            }

            if (timestamps != null) {
                value = (attributeId == AttributeId.Value) ?
                    DataValue.derivedValue(value, timestamps) :
                    DataValue.derivedNonValue(value, timestamps);
            }

            return value;
        } catch (UaException e) {
            return new DataValue(e.getStatusCode());
        }
    }

    private static boolean isStructureSubtype(ServerNodeMap nodeMap, NodeId dataTypeId) {
        ServerNode dataTypeNode = nodeMap.get(dataTypeId);

        Optional<NodeId> superTypeId = dataTypeNode.getReferences().stream()
            .filter(r ->
                r.getReferenceTypeId().equals(Identifiers.HasSubtype) &&
                    r.isInverse() &&
                    r.getTargetNodeClass() == NodeClass.DataType)
            .flatMap(r -> opt2stream(r.getTargetNodeId().local()))
            .findFirst();

        return superTypeId
            .map(id -> id.equals(Identifiers.Structure) || isStructureSubtype(nodeMap, id))
            .orElse(false);
    }

}
