package org.eclipse.milo.opcua.sdk.server.api.util;

import org.eclipse.milo.opcua.sdk.core.AccessLevel;
import org.eclipse.milo.opcua.sdk.server.nodes.UaVariableNode;
import org.eclipse.milo.opcua.sdk.test.AbstractClientServerTest;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AttributeWriterTest extends AbstractClientServerTest {

    @Test
    void writeNullAllowed() throws Exception {
        StatusCode statusCode = client.writeValue(
            new NodeId(2, "AllowNulls"),
            DataValue.valueOnly(Variant.NULL_VALUE)
        ).get();

        assertEquals(statusCode, StatusCode.GOOD);
    }

    @Test
    void writeNullDisallowed() throws Exception {
        StatusCode statusCode = client.writeValue(
            new NodeId(2, "DisallowNulls"),
            DataValue.valueOnly(Variant.NULL_VALUE)
        ).get();

        assertEquals(statusCode, new StatusCode(StatusCodes.Bad_TypeMismatch));
    }

    @Test
    void writeNullNotConfigured() throws Exception {
        // Default behavior when AllowNulls property is not configured is to reject null values.
        StatusCode statusCode = client.writeValue(
            new NodeId(2, "AllowNullsNotConfigured"),
            DataValue.valueOnly(Variant.NULL_VALUE)
        ).get();

        assertEquals(statusCode, new StatusCode(StatusCodes.Bad_TypeMismatch));
    }

    @Test
    void writeByteStringToUByteArray() throws Exception {
        StatusCode statusCode = client.writeValue(
            new NodeId(2, "UByteArray"),
            DataValue.valueOnly(new Variant(ByteString.of(new byte[]{1, 2, 3})))
        ).get();

        assertEquals(statusCode, StatusCode.GOOD);
    }

    @BeforeAll
    void configure() {
        testNamespace.configureNode((context, nodeManager) -> {
            UaVariableNode allowNulls = UaVariableNode.build(
                context,
                b -> {
                    b.setNodeId(new NodeId(2, "AllowNulls"));
                    b.setBrowseName(new QualifiedName(2, "AllowNulls"));
                    b.setDisplayName(LocalizedText.english("AllowNulls"));
                    b.setDataType(Identifiers.String);
                    b.setAccessLevel(AccessLevel.READ_WRITE);
                    b.setUserAccessLevel(AccessLevel.READ_WRITE);
                    return b.buildAndAdd();
                }
            );

            allowNulls.setAllowNulls(true);

            UaVariableNode disallowNulls = UaVariableNode.build(
                context,
                b -> {
                    b.setNodeId(new NodeId(2, "DisallowNulls"));
                    b.setBrowseName(new QualifiedName(2, "DisallowNulls"));
                    b.setDisplayName(LocalizedText.english("DisallowNulls"));
                    b.setDataType(Identifiers.String);
                    b.setAccessLevel(AccessLevel.READ_WRITE);
                    b.setUserAccessLevel(AccessLevel.READ_WRITE);
                    return b.buildAndAdd();
                }
            );

            disallowNulls.setAllowNulls(false);

            UaVariableNode.build(
                context,
                b -> {
                    b.setNodeId(new NodeId(2, "AllowNullsNotConfigured"));
                    b.setBrowseName(new QualifiedName(2, "AllowNullsNotConfigured"));
                    b.setDisplayName(LocalizedText.english("AllowNullsNotConfigured"));
                    b.setDataType(Identifiers.String);
                    b.setAccessLevel(AccessLevel.READ_WRITE);
                    b.setUserAccessLevel(AccessLevel.READ_WRITE);
                    return b.buildAndAdd();
                }
            );

            UaVariableNode.build(
                context,
                b -> {
                    b.setNodeId(new NodeId(2, "UByteArray"));
                    b.setBrowseName(new QualifiedName(2, "UByteArray"));
                    b.setDisplayName(LocalizedText.english("UByteArray"));
                    b.setDataType(Identifiers.Byte);
                    b.setArrayDimensions(new UInteger[]{UInteger.valueOf(0)});
                    b.setAccessLevel(AccessLevel.READ_WRITE);
                    b.setUserAccessLevel(AccessLevel.READ_WRITE);
                    return b.buildAndAdd();
                }
            );
        });
    }

}
