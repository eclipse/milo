/*
 * Copyright (c) 2026 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.core.encoding.json;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.function.Consumer;
import java.util.stream.Stream;
import org.eclipse.milo.opcua.stack.core.NodeIds;
import org.eclipse.milo.opcua.stack.core.encoding.DefaultEncodingContext;
import org.eclipse.milo.opcua.stack.core.encoding.EncodingContext;
import org.eclipse.milo.opcua.stack.core.encoding.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.encoding.UaDecoder;
import org.eclipse.milo.opcua.stack.core.encoding.UaEncoder;
import org.eclipse.milo.opcua.stack.core.encoding.json.OpcUaJsonEncoder.Encoding;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.enumerated.ApplicationType;
import org.eclipse.milo.opcua.stack.core.types.structured.Range;
import org.eclipse.milo.opcua.stack.core.types.structured.Structure;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;

class JsonArrayFieldEncodingTest {

  // Part 6 §5.4.6: null mandatory fields are explicit in VERBOSE and omitted in COMPACT.
  @ParameterizedTest
  @MethodSource("nullFields")
  void encodesNullArrayFieldsAccordingToMode(Encoding encoding, String expected) throws Exception {
    assertEquals(
        expected,
        encodeFields(
            encoding,
            e -> {
              e.encodeInt32Array("Numbers", null);
              e.encodeEnumArray("Enums", null);
              e.encodeStructArray("Structures", null, NodeIds.Range);
              e.encodeStructArray("ExpandedStructures", null, Range.TYPE_ID);
              e.encodeExtensionObjectArray("Objects", null);
            }));
  }

  static Stream<Arguments> nullFields() {
    return Stream.of(
        Arguments.of(Encoding.COMPACT, "{\"Tail\":88}"),
        Arguments.of(
            Encoding.VERBOSE,
            "{\"Numbers\":null,\"Enums\":null,\"Structures\":null,\"ExpandedStructures\":null,\"Objects\":null,\"Tail\":88}"));
  }

  // Part 6 §5.4.7: invoking a present-null field differs from skipping an absent field.
  @ParameterizedTest
  @MethodSource("optionalFields")
  void distinguishesAbsentNullEmptyAndPopulatedFields(
      Encoding encoding, boolean present, Integer[] values, String expected) throws Exception {
    assertEquals(
        expected,
        encodeFields(
            encoding,
            e -> {
              if (present) {
                e.encodeInt32Array("Numbers", values);
              }
            }));
  }

  static Stream<Arguments> optionalFields() {
    return Stream.of(
        Arguments.of(Encoding.COMPACT, false, null, "{\"Tail\":88}"),
        Arguments.of(Encoding.VERBOSE, false, null, "{\"Tail\":88}"),
        Arguments.of(Encoding.COMPACT, true, null, "{\"Tail\":88}"),
        Arguments.of(Encoding.VERBOSE, true, null, "{\"Numbers\":null,\"Tail\":88}"),
        Arguments.of(Encoding.COMPACT, true, new Integer[0], "{\"Numbers\":[],\"Tail\":88}"),
        Arguments.of(Encoding.VERBOSE, true, new Integer[0], "{\"Numbers\":[],\"Tail\":88}"),
        Arguments.of(
            Encoding.COMPACT, true, new Integer[] {0, 1}, "{\"Numbers\":[0,1],\"Tail\":88}"),
        Arguments.of(
            Encoding.VERBOSE, true, new Integer[] {0, 1}, "{\"Numbers\":[0,1],\"Tail\":88}"));
  }

  // Empty arrays must remain distinguishable from null across all shared array entry points.
  @ParameterizedTest
  @EnumSource(Encoding.class)
  void preservesEmptyArrayFields(Encoding encoding) throws Exception {
    assertEquals(
        "{\"Numbers\":[],\"Enums\":[],\"Structures\":[],\"Objects\":[],\"Tail\":88}",
        encodeFields(
            encoding,
            e -> {
              e.encodeInt32Array("Numbers", new Integer[0]);
              e.encodeEnumArray("Enums", new ApplicationType[0]);
              e.encodeStructArray("Structures", new Range[0], NodeIds.Range);
              e.encodeExtensionObjectArray("Objects", new ExtensionObject[0]);
            }));
  }

  // A null value must occupy its position rather than shift later JSON array elements.
  @ParameterizedTest
  @EnumSource(Encoding.class)
  void preservesPositionalNullArrays(Encoding encoding) throws Exception {
    try (var encoder = new OpcUaJsonEncoder(new DefaultEncodingContext())) {
      encoder.setEncoding(encoding);
      encoder.jsonWriter.beginArray();
      encoder.encodeInt32Array(null, null);
      encoder.encodeEnumArray(null, null);
      encoder.encodeStructArray(null, null, NodeIds.Range);
      encoder.encodeExtensionObjectArray(null, null);
      encoder.encodeInt32(null, 88);
      encoder.jsonWriter.endArray();
      assertEquals("[null,null,null,null,88]", encoder.getOutputString());
    }
  }

  private String encodeFields(Encoding encoding, Consumer<UaEncoder> fields) throws Exception {
    var codec =
        new GenericDataTypeCodec<Structure>() {
          @Override
          public Class<Structure> getType() {
            return Structure.class;
          }

          @Override
          public Structure decodeType(EncodingContext context, UaDecoder decoder) {
            throw new UnsupportedOperationException();
          }

          @Override
          public void encodeType(EncodingContext context, UaEncoder encoder, Structure value) {
            fields.accept(encoder);
            encoder.encodeInt32("Tail", 88);
          }
        };
    try (var encoder = new OpcUaJsonEncoder(new DefaultEncodingContext())) {
      encoder.setEncoding(encoding);
      encoder.encodeStruct(null, new Structure() {}, codec);
      return encoder.getOutputString();
    }
  }
}
