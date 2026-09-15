/*
 * Copyright (c) 2026 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.core.types;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import org.eclipse.milo.opcua.sdk.core.types.codec.DynamicCodecFactory;
import org.eclipse.milo.opcua.sdk.core.types.codec.DynamicStructCodec;
import org.eclipse.milo.opcua.sdk.core.types.util.AbstractDataType;
import org.eclipse.milo.opcua.sdk.core.types.util.DynamicEncodingContext;
import org.eclipse.milo.opcua.sdk.core.typetree.DataType;
import org.eclipse.milo.opcua.stack.core.NodeIds;
import org.eclipse.milo.opcua.stack.core.encoding.binary.OpcUaBinaryDecoder;
import org.eclipse.milo.opcua.stack.core.encoding.binary.OpcUaBinaryEncoder;
import org.eclipse.milo.opcua.stack.core.types.UaStructuredType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;
import org.eclipse.milo.opcua.stack.core.types.structured.DataTypeDefinition;
import org.eclipse.milo.opcua.stack.core.types.structured.EnumDefinition;
import org.eclipse.milo.opcua.stack.core.types.structured.EnumField;
import org.eclipse.milo.opcua.stack.core.types.structured.Range;
import org.eclipse.milo.opcua.stack.core.types.structured.StructureDefinition;
import org.eclipse.milo.opcua.stack.core.types.structured.StructureField;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class SubtypedStructureArrayTest {

  // Nested codecs must retain their Java representation and produce the same binary wire values.
  @ParameterizedTest
  @ValueSource(strings = {"java", "struct", "union", "optionSet", "mixed", "empty"})
  void decodesAndReencodesPermittedStructuredValues(String scenario) {
    var context = new DynamicEncodingContext();
    context.dataTypeManager.registerType(
        NodeIds.Range,
        new Range.Codec(),
        NodeIds.Range_Encoding_DefaultBinary,
        NodeIds.Range_Encoding_DefaultXml,
        NodeIds.Range_Encoding_DefaultJson);

    DataType rangeSubtype =
        register(
            context,
            "RangeSubtype",
            new StructureDefinition(
                new NodeId(1, "RangeSubtype.Binary"),
                NodeIds.Range,
                StructureType.Structure,
                new StructureField[] {
                  field("Low", NodeIds.Double, -1, false),
                  field("High", NodeIds.Double, -1, false),
                  field("Label", NodeIds.String, -1, false)
                }));
    DataType optionSet = register(context, "Flags", new EnumDefinition(new EnumField[0]));
    var range = new Range(1.0, 2.0);
    var dynamicRange =
        new DynamicStructType(
            rangeSubtype, new LinkedHashMap<>(Map.of("Low", 3.0, "High", 4.0, "Label", "subtype")));
    var union =
        new DynamicUnionType(
            context.unionOfScalar, new DynamicUnionType.UnionValue("SByte", (byte) 42));
    var flags =
        new DynamicOptionSetType(
            optionSet, ByteString.of(new byte[] {1}), ByteString.of(new byte[] {3}));

    UaStructuredType[] values =
        switch (scenario) {
          case "java" -> new Range[] {range};
          case "struct" -> new DynamicStructType[] {dynamicRange};
          case "union" -> new DynamicUnionType[] {union};
          case "optionSet" -> new DynamicOptionSetType[] {flags};
          case "mixed" -> new UaStructuredType[] {range, dynamicRange};
          case "empty" -> new UaStructuredType[0];
          default -> throw new IllegalArgumentException(scenario);
        };
    NodeId declaredType =
        switch (scenario) {
          case "union" -> context.unionOfScalar.getNodeId();
          case "optionSet" -> optionSet.getNodeId();
          default -> NodeIds.Range;
        };
    when(context.dataTypeTree.isStructType(declaredType)).thenReturn(true);
    DataType parent =
        register(
            context,
            "Poly",
            new StructureDefinition(
                new NodeId(1, "Poly.Binary"),
                NodeIds.Structure,
                StructureType.StructureWithSubtypedValues,
                new StructureField[] {field("Items", declaredType, 1, true)}));
    var codec = (DynamicStructCodec) DynamicCodecFactory.create(parent, context.dataTypeTree);
    ExtensionObject[] expected =
        Arrays.stream(values)
            .map(value -> ExtensionObject.encode(context, value))
            .toArray(ExtensionObject[]::new);

    ByteBuf input = Unpooled.buffer();
    ByteBuf output = Unpooled.buffer();
    try {
      // Write the parent wire layout independently of DynamicStructCodec's array encoder.
      new OpcUaBinaryEncoder(context)
          .setBuffer(input)
          .encodeExtensionObjectArray("Items", expected);
      DynamicStructType decoded =
          assertInstanceOf(
              DynamicStructType.class,
              new OpcUaBinaryDecoder(context).setBuffer(input).decodeStruct(null, codec));
      assertFalse(input.isReadable());
      UaStructuredType[] actual =
          assertInstanceOf(UaStructuredType[].class, decoded.getMembers().get("Items"));
      assertEquals(values.length, actual.length);
      for (int i = 0; i < values.length; i++) {
        assertEquals(values[i].getClass(), actual[i].getClass());
        if (values[i] instanceof DynamicOptionSetType expectedFlags) {
          var actualFlags = (DynamicOptionSetType) actual[i];
          assertEquals(expectedFlags.getValue(), actualFlags.getValue());
          assertEquals(expectedFlags.getValidBits(), actualFlags.getValidBits());
        } else {
          assertEquals(values[i], actual[i]);
        }
      }

      new OpcUaBinaryEncoder(context).setBuffer(output).encodeStruct(null, decoded, codec);
      ExtensionObject[] reencoded =
          new OpcUaBinaryDecoder(context).setBuffer(output).decodeExtensionObjectArray("Items");
      assertArrayEquals(expected, reencoded);
      assertFalse(output.isReadable());
    } finally {
      input.release();
      output.release();
    }
  }

  private static StructureField field(String name, NodeId type, int rank, boolean subtypes) {
    return new StructureField(name, LocalizedText.NULL_VALUE, type, rank, null, uint(0), subtypes);
  }

  private static DataType register(
      DynamicEncodingContext context, String name, DataTypeDefinition definition) {
    DataType dataType =
        new AbstractDataType(new NodeId(1, name), new QualifiedName(1, name), definition, false) {
          @Override
          public NodeId getBinaryEncodingId() {
            return new NodeId(1, name + ".Binary");
          }
        };
    context.dataTypeManager.registerType(
        dataType.getNodeId(),
        DynamicCodecFactory.create(dataType, context.dataTypeTree),
        dataType.getBinaryEncodingId(),
        null,
        null);
    return dataType;
  }
}
