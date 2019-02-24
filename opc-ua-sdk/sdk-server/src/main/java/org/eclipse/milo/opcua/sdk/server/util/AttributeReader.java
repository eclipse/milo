/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.util;

import java.util.Optional;
import java.util.Set;
import javax.annotation.Nullable;

import org.eclipse.milo.opcua.sdk.core.AccessLevel;
import org.eclipse.milo.opcua.sdk.core.NumericRange;
import org.eclipse.milo.opcua.sdk.server.api.NodeManager;
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
import static org.eclipse.milo.opcua.stack.core.util.ArrayUtil.transformArray;

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
                    throw new UaException(StatusCodes.Bad_NotReadable);
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

            final DataValue.Builder value = node.getAttribute(context, attributeId).copy();

            // Maybe transcode the structure...
            if (value.value.isNotNull()) {
                final Object valueObject = value.value.getValue();

                Class<?> valueClazz = valueObject.getClass();

                if (valueClazz.isArray() && ArrayUtil.getType(valueObject) == ExtensionObject.class) {
                    Object newValue = transformArray(
                        valueObject,
                        (ExtensionObject xo) ->
                            transcode(context, node, xo, encodingName),
                        ExtensionObject.class
                    );

                    value.setValue(new Variant(newValue));
                } else if (valueClazz == ExtensionObject.class) {
                    ExtensionObject xo = (ExtensionObject) valueObject;

                    Object newValue = transcode(context, node, xo, encodingName);

                    value.setValue(new Variant(newValue));
                }
            }

            if (indexRange != null) {
                NumericRange range = NumericRange.parse(indexRange);

                Object valueAtRange = NumericRange.readFromValueAtRange(value.value, range);

                value.setValue(new Variant(valueAtRange));
            }

            if (timestamps != null) {
                value.applyTimestamps(attributeId, timestamps);
            }

            return value.build();
        } catch (UaException e) {
            return new DataValue(e.getStatusCode());
        }
    }

    private static boolean isStructureSubtype(NodeManager<UaNode> nodeManager, NodeId dataTypeId) {
        UaNode dataTypeNode = nodeManager.get(dataTypeId);

        if (dataTypeNode != null) {
            Optional<NodeId> superTypeId = dataTypeNode.getReferences().stream()
                .filter(r -> r.isInverse() && r.getReferenceTypeId().equals(Identifiers.HasSubtype))
                .flatMap(r -> opt2stream(r.getTargetNodeId().local()))
                .findFirst();

            return superTypeId
                .map(id -> id.equals(Identifiers.Structure) || isStructureSubtype(nodeManager, id))
                .orElse(false);
        } else {
            return false;
        }
    }

    private static ExtensionObject transcode(
        AttributeContext context,
        UaServerNode node,
        ExtensionObject xo,
        QualifiedName encodingName) {

        if (xo.isNull()) {
            return xo;
        }

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

        NodeId newEncodingId = getEncodingId(context, node, encodingName);

        if (newEncodingId != null) {
            return xo.transcode(
                newEncodingId,
                encoding,
                context.getServer().getConfig().getEncodingLimits(),
                OpcUaDataTypeManager.getInstance()
            );
        } else {
            return xo;
        }
    }

    @Nullable
    private static NodeId getEncodingId(AttributeContext context, UaServerNode node, QualifiedName encodingName) {
        // TODO avoid dynamic lookup by registering codecs with their associated DataType and Encoding name
        NodeId dataTypeId;
        if (node instanceof VariableNode) {
            dataTypeId = ((VariableNode) node).getDataType();
        } else if (node instanceof VariableTypeNode) {
            dataTypeId = ((VariableTypeNode) node).getDataType();
        } else {
            return null;
        }

        NodeManager<UaNode> nodeManager = context.getServer().getNodeManager();

        UaNode dataTypeNode = nodeManager.get(dataTypeId);

        if (dataTypeNode != null) {
            return dataTypeNode.getReferences().stream()
                .filter(r -> r.isForward() && Identifiers.HasEncoding.equals(r.getReferenceTypeId()))
                .flatMap(r -> opt2stream(nodeManager.getNode(r.getTargetNodeId())))
                .filter(n -> encodingName.equals(n.getBrowseName()))
                .map(Node::getNodeId)
                .findFirst()
                .orElse(null);
        } else {
            return null;
        }
    }

}
