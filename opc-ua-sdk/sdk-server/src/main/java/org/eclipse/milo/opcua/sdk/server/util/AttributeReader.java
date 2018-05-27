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

import java.lang.reflect.Array;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import javax.annotation.Nullable;

import org.eclipse.milo.opcua.sdk.core.AccessLevel;
import org.eclipse.milo.opcua.sdk.core.NumericRange;
import org.eclipse.milo.opcua.sdk.server.UaNodeManager;
import org.eclipse.milo.opcua.sdk.server.api.nodes.Node;
import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableTypeNode;
import org.eclipse.milo.opcua.sdk.server.nodes.AttributeContext;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaServerNode;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.DataTypeEncoding;
import org.eclipse.milo.opcua.stack.core.types.OpcUaDataTypeManager;
import org.eclipse.milo.opcua.stack.core.types.OpcUaDefaultBinaryEncoding;
import org.eclipse.milo.opcua.stack.core.types.OpcUaDefaultXmlEncoding;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;
import org.eclipse.milo.opcua.stack.core.util.ArrayUtil;

import static org.eclipse.milo.opcua.sdk.core.util.StreamUtil.opt2stream;
import static org.eclipse.milo.opcua.sdk.server.util.AttributeUtil.getAccessLevels;
import static org.eclipse.milo.opcua.sdk.server.util.AttributeUtil.getUserAccessLevels;

public class AttributeReader {

    public static DataValue readAttribute(
        AttributeContext context,
        UaServerNode node,
        AttributeId attributeId,
        @Nullable TimestampsToReturn timestamps,
        @Nullable String indexRange,
        @Nullable QualifiedName encodingName) {

        try {
            AttributeContext internalContext = new AttributeContext(context.getServer());

            NodeClass nodeClass = node.getNodeClass();

            if (attributeId == AttributeId.Value && nodeClass == NodeClass.Variable) {
                Set<AccessLevel> accessLevels = getAccessLevels(node, internalContext);
                if (!accessLevels.contains(AccessLevel.CurrentRead)) {
                    throw new UaException(StatusCodes.Bad_NotWritable);
                }

                Set<AccessLevel> userAccessLevels = getUserAccessLevels(node, context);
                if (!userAccessLevels.contains(AccessLevel.CurrentRead)) {
                    throw new UaException(StatusCodes.Bad_UserAccessDenied);
                }
            }

            if (encodingName != null && encodingName.isNotNull()) {
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

                boolean structured = isStructureSubtype(context.getServer().getNodeManager(), dataTypeId);

                if (!structured) {
                    throw new UaException(StatusCodes.Bad_DataEncodingInvalid);
                }
            }

            DataValue value = node.getAttribute(context, attributeId);

            Object valueObject = value.getValue().getValue();

            // Maybe transcode the structure...
            if (valueObject != null) {
                Class<?> valueClazz = valueObject.getClass();

                if (valueClazz.isArray() && ArrayUtil.getType(valueObject) == ExtensionObject.class) {
                    valueObject = transformArray(
                        valueObject,
                        (Function<ExtensionObject, ExtensionObject>) xo ->
                            transcode(context, node, xo, encodingName),
                        ExtensionObject.class
                    );
                } else if (valueClazz == ExtensionObject.class) {
                    ExtensionObject xo = (ExtensionObject) valueObject;

                    valueObject = transcode(context, node, xo, encodingName);
                }

                value = new DataValue(
                    new Variant(valueObject),
                    value.getStatusCode(),
                    value.getSourceTime(),
                    value.getServerTime()
                );
            }

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

    private static boolean isStructureSubtype(UaNodeManager nodeManager, NodeId dataTypeId) {
        UaNode dataTypeNode = nodeManager.get(dataTypeId);

        Optional<NodeId> superTypeId = dataTypeNode.getReferences().stream()
            .filter(r ->
                r.getReferenceTypeId().equals(Identifiers.HasSubtype) &&
                    r.isInverse() &&
                    r.getTargetNodeClass() == NodeClass.DataType)
            .flatMap(r -> opt2stream(r.getTargetNodeId().local()))
            .findFirst();

        return superTypeId
            .map(id -> id.equals(Identifiers.Structure) || isStructureSubtype(nodeManager, id))
            .orElse(false);
    }

    private static ExtensionObject transcode(
        AttributeContext context,
        UaServerNode node,
        ExtensionObject xo,
        QualifiedName encodingName) {

        if (encodingName == null || encodingName.isNull()) {
            // TODO default encoding should be derived from session/transport
            encodingName = OpcUaDefaultBinaryEncoding.ENCODING_NAME;
        }

        DataTypeEncoding encoding;
        if (OpcUaDefaultBinaryEncoding.ENCODING_NAME.equals(encodingName)) {
            encoding = OpcUaDefaultBinaryEncoding.getInstance();
        } else if (OpcUaDefaultXmlEncoding.ENCODING_NAME.equals(encodingName)) {
            encoding = OpcUaDefaultXmlEncoding.getInstance();
        } else {
            // TODO look up registered alternate encodings
            encoding = OpcUaDefaultBinaryEncoding.getInstance();
        }

        if (!xo.isNull()) {
            NodeId newEncodingId = getEncodingId(context, node, encodingName);

            if (newEncodingId != null) {
                return xo.withEncoding(
                    newEncodingId,
                    encoding,
                    OpcUaDataTypeManager.getInstance()
                );
            } else {
                return xo;
            }
        } else {
            return xo;
        }
    }

    @Nullable
    private static NodeId getEncodingId(AttributeContext context, UaServerNode node, QualifiedName encodingName) {
        NodeId dataTypeId;
        if (node instanceof VariableNode) {
            dataTypeId = ((VariableNode) node).getDataType();
        } else if (node instanceof VariableTypeNode) {
            dataTypeId = ((VariableTypeNode) node).getDataType();
        } else {
            return null;
        }

        UaNodeManager nodeManager = context.getServer().getNodeManager();

        UaServerNode dataTypeNode = nodeManager.get(dataTypeId);

        return dataTypeNode.getReferences().stream()
            .filter(r -> r.isForward() && Identifiers.HasEncoding.equals(r.getReferenceTypeId()))
            .flatMap(r -> opt2stream(nodeManager.getNode(r.getTargetNodeId())))
            .filter(n -> encodingName.equals(n.getBrowseName()))
            .map(Node::getNodeId)
            .findFirst()
            .orElse(null);
    }

    private static <F, T> Object transformArray(Object o, Function<F, T> transform, Class<T> toType) {
        int length = Array.getLength(o);
        Object array = Array.newInstance(toType, length);
        for (int i = 0; i < length; i++) {
            @SuppressWarnings("unchecked")
            Object transformed = transform.apply((F) Array.get(o, i));
            Array.set(array, i, transformed);
        }
        return array;
    }

}
