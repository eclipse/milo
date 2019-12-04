/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.namespaces.loader;

import org.eclipse.milo.opcua.sdk.core.Reference;
import org.eclipse.milo.opcua.sdk.server.api.NodeManager;
import org.eclipse.milo.opcua.sdk.server.nodes.UaDataTypeNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;

class DataTypeNodeLoader {
    private final UaNodeContext context;

    private final NodeManager<UaNode> nodeManager;

    DataTypeNodeLoader(UaNodeContext context, NodeManager<UaNode> nodeManager) {
        this.context = context;
        this.nodeManager = nodeManager;
    }

    private void loadNode0() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 1), new QualifiedName(0, "Boolean"), new LocalizedText("en", "Boolean"), new LocalizedText("en", "Describes a value that is either TRUE or FALSE."), UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 1), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(24), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode1() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 2), new QualifiedName(0, "SByte"), new LocalizedText("en", "SByte"), new LocalizedText("en", "Describes a value that is an integer between -128 and 127."), UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 2), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(27), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode2() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 3), new QualifiedName(0, "Byte"), new LocalizedText("en", "Byte"), new LocalizedText("en", "Describes a value that is an integer between 0 and 255."), UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 3), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(28), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode3() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 4), new QualifiedName(0, "Int16"), new LocalizedText("en", "Int16"), new LocalizedText("en", "Describes a value that is an integer between −32,768 and 32,767."), UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 4), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(27), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode4() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 5), new QualifiedName(0, "UInt16"), new LocalizedText("en", "UInt16"), new LocalizedText("en", "Describes a value that is an integer between 0 and 65535."), UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 5), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(28), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode5() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 6), new QualifiedName(0, "Int32"), new LocalizedText("en", "Int32"), new LocalizedText("en", "Describes a value that is an integer between −2,147,483,648  and 2,147,483,647."), UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 6), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(27), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode6() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 7), new QualifiedName(0, "UInt32"), new LocalizedText("en", "UInt32"), new LocalizedText("en", "Describes a value that is an integer between 0 and 4,294,967,295."), UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 7), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(28), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode7() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 8), new QualifiedName(0, "Int64"), new LocalizedText("en", "Int64"), new LocalizedText("en", "Describes a value that is an integer between −9,223,372,036,854,775,808 and 9,223,372,036,854,775,807."), UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 8), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(27), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode8() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 9), new QualifiedName(0, "UInt64"), new LocalizedText("en", "UInt64"), new LocalizedText("en", "Describes a value that is an integer between 0 and 18,446,744,073,709,551,615."), UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 9), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(28), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode9() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 10), new QualifiedName(0, "Float"), new LocalizedText("en", "Float"), new LocalizedText("en", "Describes a value that is an IEEE 754-1985 single precision floating point number."), UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 10), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(26), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode10() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 11), new QualifiedName(0, "Double"), new LocalizedText("en", "Double"), new LocalizedText("en", "Describes a value that is an IEEE 754-1985 double precision floating point number."), UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 11), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(26), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode11() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 12), new QualifiedName(0, "String"), new LocalizedText("en", "String"), new LocalizedText("en", "Describes a value that is a sequence of printable Unicode characters."), UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 12), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(24), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode12() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 13), new QualifiedName(0, "DateTime"), new LocalizedText("en", "DateTime"), new LocalizedText("en", "Describes a value that is a Gregorian calender date and time."), UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 13), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(24), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode13() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 14), new QualifiedName(0, "Guid"), new LocalizedText("en", "Guid"), new LocalizedText("en", "Describes a value that is a 128-bit globally unique identifier."), UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 14), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(24), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode14() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 15), new QualifiedName(0, "ByteString"), new LocalizedText("en", "ByteString"), new LocalizedText("en", "Describes a value that is a sequence of bytes."), UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 15), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(24), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode15() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 16), new QualifiedName(0, "XmlElement"), new LocalizedText("en", "XmlElement"), new LocalizedText("en", "Describes a value that is an XML element."), UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 16), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(24), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode16() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 17), new QualifiedName(0, "NodeId"), new LocalizedText("en", "NodeId"), new LocalizedText("en", "Describes a value that is an identifier for a node within a Server address space."), UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 17), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(24), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode17() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 18), new QualifiedName(0, "ExpandedNodeId"), new LocalizedText("en", "ExpandedNodeId"), new LocalizedText("en", "Describes a value that is an absolute identifier for a node."), UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 18), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(24), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode18() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 19), new QualifiedName(0, "StatusCode"), new LocalizedText("en", "StatusCode"), new LocalizedText("en", "Describes a value that is a code representing the outcome of an operation by a Server."), UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 19), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(24), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode19() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 20), new QualifiedName(0, "QualifiedName"), new LocalizedText("en", "QualifiedName"), new LocalizedText("en", "Describes a value that is a name qualified by a namespace."), UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 20), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(24), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode20() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 21), new QualifiedName(0, "LocalizedText"), new LocalizedText("en", "LocalizedText"), new LocalizedText("en", "Describes a value that is human readable Unicode text with a locale identifier."), UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 21), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(24), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode21() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 22), new QualifiedName(0, "Structure"), new LocalizedText("en", "Structure"), new LocalizedText("en", "Describes a value that is any type of structure that can be described with a data encoding."), UInteger.valueOf(0), UInteger.valueOf(0), true);
        node.addReference(new Reference(new NodeId(0, 22), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(24), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode22() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 23), new QualifiedName(0, "DataValue"), new LocalizedText("en", "DataValue"), new LocalizedText("en", "Describes a value that is a structure containing a value, a status code and timestamps."), UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 23), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(24), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode23() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 24), new QualifiedName(0, "BaseDataType"), new LocalizedText("en", "BaseDataType"), new LocalizedText("en", "Describes a value that can have any valid DataType."), UInteger.valueOf(0), UInteger.valueOf(0), true);
        this.nodeManager.addNode(node);
    }

    private void loadNode24() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 25), new QualifiedName(0, "DiagnosticInfo"), new LocalizedText("en", "DiagnosticInfo"), new LocalizedText("en", "Describes a value that is a structure containing diagnostics associated with a StatusCode."), UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 25), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(24), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode25() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 26), new QualifiedName(0, "Number"), new LocalizedText("en", "Number"), new LocalizedText("en", "Describes a value that can have any numeric DataType."), UInteger.valueOf(0), UInteger.valueOf(0), true);
        node.addReference(new Reference(new NodeId(0, 26), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(24), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode26() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 27), new QualifiedName(0, "Integer"), new LocalizedText("en", "Integer"), new LocalizedText("en", "Describes a value that can have any integer DataType."), UInteger.valueOf(0), UInteger.valueOf(0), true);
        node.addReference(new Reference(new NodeId(0, 27), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(26), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode27() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 28), new QualifiedName(0, "UInteger"), new LocalizedText("en", "UInteger"), new LocalizedText("en", "Describes a value that can have any unsigned integer DataType."), UInteger.valueOf(0), UInteger.valueOf(0), true);
        node.addReference(new Reference(new NodeId(0, 28), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(26), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode28() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 29), new QualifiedName(0, "Enumeration"), new LocalizedText("en", "Enumeration"), new LocalizedText("en", "Describes a value that is an enumerated DataType."), UInteger.valueOf(0), UInteger.valueOf(0), true);
        node.addReference(new Reference(new NodeId(0, 29), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(24), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode29() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 30), new QualifiedName(0, "Image"), new LocalizedText("en", "Image"), new LocalizedText("en", "Describes a value that is an image encoded as a string of bytes."), UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 30), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(15), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode30() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 120), new QualifiedName(0, "NamingRuleType"), new LocalizedText("en", "NamingRuleType"), new LocalizedText("en", "Describes a value that specifies the significance of the BrowseName for an instance declaration."), UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 120), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12169), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 120), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(29), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode31() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 121), new QualifiedName(0, "Decimal128"), new LocalizedText("en", "Decimal128"), new LocalizedText("en", "Describes a 128-bit decimal value."), UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 121), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(26), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode32() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 256), new QualifiedName(0, "IdType"), new LocalizedText("en", "IdType"), new LocalizedText("en", "The type of identifier used in a node id."), UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 256), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(7591), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 256), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(29), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode33() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 257), new QualifiedName(0, "NodeClass"), new LocalizedText("en", "NodeClass"), new LocalizedText("en", "A mask specifying the class of the node."), UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 257), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11878), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 257), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(29), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode34() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 12552), new QualifiedName(0, "TrustListMasks"), new LocalizedText("en", "TrustListMasks"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 12552), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12553), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12552), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(29), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode35() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 12554), new QualifiedName(0, "TrustListDataType"), new LocalizedText("en", "TrustListDataType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 12554), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(22), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode36() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 288), new QualifiedName(0, "IntegerId"), new LocalizedText("en", "IntegerId"), new LocalizedText("en", "A numeric identifier for an object."), UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 288), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(7), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode37() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 289), new QualifiedName(0, "Counter"), new LocalizedText("en", "Counter"), new LocalizedText("en", "A monotonically increasing value."), UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 289), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(7), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode38() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 290), new QualifiedName(0, "Duration"), new LocalizedText("en", "Duration"), new LocalizedText("en", "A period of time measured in milliseconds."), UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 290), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode39() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 291), new QualifiedName(0, "NumericRange"), new LocalizedText("en", "NumericRange"), new LocalizedText("en", "Specifies a range of array indexes."), UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 291), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode40() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 292), new QualifiedName(0, "Time"), new LocalizedText("en", "Time"), new LocalizedText("en", "A time value specified as HH:MM:SS.SSS."), UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 292), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode41() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 293), new QualifiedName(0, "Date"), new LocalizedText("en", "Date"), new LocalizedText("en", "A date value."), UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 293), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode42() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 294), new QualifiedName(0, "UtcTime"), new LocalizedText("en", "UtcTime"), new LocalizedText("en", "A date/time value specified in Universal Coordinated Time (UTC)."), UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 294), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(13), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode43() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 295), new QualifiedName(0, "LocaleId"), new LocalizedText("en", "LocaleId"), new LocalizedText("en", "An identifier for a user locale."), UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 295), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode44() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 296), new QualifiedName(0, "Argument"), new LocalizedText("en", "Argument"), new LocalizedText("en", "An argument for a method."), UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 296), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(22), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode45() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 299), new QualifiedName(0, "StatusResult"), new LocalizedText("en", "StatusResult"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 299), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(22), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode46() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 302), new QualifiedName(0, "MessageSecurityMode"), new LocalizedText("en", "MessageSecurityMode"), new LocalizedText("en", "The type of security to use on a message."), UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 302), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(7595), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 302), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(29), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode47() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 303), new QualifiedName(0, "UserTokenType"), new LocalizedText("en", "UserTokenType"), new LocalizedText("en", "The possible user token types."), UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 303), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(7596), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 303), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(29), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode48() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 304), new QualifiedName(0, "UserTokenPolicy"), new LocalizedText("en", "UserTokenPolicy"), new LocalizedText("en", "Describes a user token that can be used with a server."), UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 304), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(22), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode49() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 307), new QualifiedName(0, "ApplicationType"), new LocalizedText("en", "ApplicationType"), new LocalizedText("en", "The types of applications."), UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 307), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(7597), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 307), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(29), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode50() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 308), new QualifiedName(0, "ApplicationDescription"), new LocalizedText("en", "ApplicationDescription"), new LocalizedText("en", "Describes an application and how to find it."), UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 308), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(22), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode51() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 311), new QualifiedName(0, "ApplicationInstanceCertificate"), new LocalizedText("en", "ApplicationInstanceCertificate"), new LocalizedText("en", "A certificate for an instance of an application."), UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 311), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(15), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode52() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 312), new QualifiedName(0, "EndpointDescription"), new LocalizedText("en", "EndpointDescription"), new LocalizedText("en", "The description of a endpoint that can be used to access a server."), UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 312), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(22), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode53() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 315), new QualifiedName(0, "SecurityTokenRequestType"), new LocalizedText("en", "SecurityTokenRequestType"), new LocalizedText("en", "Indicates whether a token if being created or renewed."), UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 315), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(7598), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 315), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(29), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode54() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 316), new QualifiedName(0, "UserIdentityToken"), new LocalizedText("en", "UserIdentityToken"), new LocalizedText("en", "A base type for a user identity token."), UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 316), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(22), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode55() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 319), new QualifiedName(0, "AnonymousIdentityToken"), new LocalizedText("en", "AnonymousIdentityToken"), new LocalizedText("en", "A token representing an anonymous user."), UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 319), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(316), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode56() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 322), new QualifiedName(0, "UserNameIdentityToken"), new LocalizedText("en", "UserNameIdentityToken"), new LocalizedText("en", "A token representing a user identified by a user name and password."), UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 322), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(316), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode57() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 325), new QualifiedName(0, "X509IdentityToken"), new LocalizedText("en", "X509IdentityToken"), new LocalizedText("en", "A token representing a user identified by an X509 certificate."), UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 325), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(316), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode58() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 331), new QualifiedName(0, "EndpointConfiguration"), new LocalizedText("en", "EndpointConfiguration"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 331), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(22), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode59() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 338), new QualifiedName(0, "BuildInfo"), new LocalizedText("en", "BuildInfo"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 338), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(22), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode60() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 344), new QualifiedName(0, "SignedSoftwareCertificate"), new LocalizedText("en", "SignedSoftwareCertificate"), new LocalizedText("en", "A software certificate with a digital signature."), UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 344), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(22), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode61() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 347), new QualifiedName(0, "AttributeWriteMask"), new LocalizedText("en", "AttributeWriteMask"), new LocalizedText("en", "Define bits used to indicate which attributes are writable."), UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 347), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11882), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 347), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(29), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode62() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 348), new QualifiedName(0, "NodeAttributesMask"), new LocalizedText("en", "NodeAttributesMask"), new LocalizedText("en", "The bits used to specify default attributes for a new node."), UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 348), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11881), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 348), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(29), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode63() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 376), new QualifiedName(0, "AddNodesItem"), new LocalizedText("en", "AddNodesItem"), new LocalizedText("en", "A request to add a node to the server address space."), UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 376), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(22), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode64() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 379), new QualifiedName(0, "AddReferencesItem"), new LocalizedText("en", "AddReferencesItem"), new LocalizedText("en", "A request to add a reference to the server address space."), UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 379), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(22), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode65() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 382), new QualifiedName(0, "DeleteNodesItem"), new LocalizedText("en", "DeleteNodesItem"), new LocalizedText("en", "A request to delete a node to the server address space."), UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 382), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(22), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode66() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 385), new QualifiedName(0, "DeleteReferencesItem"), new LocalizedText("en", "DeleteReferencesItem"), new LocalizedText("en", "A request to delete a node from the server address space."), UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 385), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(22), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode67() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 388), new QualifiedName(0, "SessionAuthenticationToken"), new LocalizedText("en", "SessionAuthenticationToken"), new LocalizedText("en", "A unique identifier for a session used to authenticate requests."), UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 388), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(17), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode68() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 432), new QualifiedName(0, "RegisteredServer"), new LocalizedText("en", "RegisteredServer"), new LocalizedText("en", "The information required to register a server with a discovery server."), UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 432), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(22), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode69() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 12755), new QualifiedName(0, "OptionSet"), new LocalizedText("en", "OptionSet"), new LocalizedText("en", "This abstract Structured DataType is the base DataType for all DataTypes representing a bit mask."), UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 12755), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(22), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode70() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 12756), new QualifiedName(0, "Union"), new LocalizedText("en", "Union"), new LocalizedText("en", "This abstract DataType is the base DataType for all union DataTypes."), UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 12756), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(22), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode71() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 521), new QualifiedName(0, "ContinuationPoint"), new LocalizedText("en", "ContinuationPoint"), new LocalizedText("en", "An identifier for a suspended query or browse operation."), UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 521), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(15), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode72() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 537), new QualifiedName(0, "RelativePathElement"), new LocalizedText("en", "RelativePathElement"), new LocalizedText("en", "An element in a relative path."), UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 537), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(22), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode73() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 540), new QualifiedName(0, "RelativePath"), new LocalizedText("en", "RelativePath"), new LocalizedText("en", "A relative path constructed from reference types and browse names."), UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 540), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(22), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode74() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 576), new QualifiedName(0, "FilterOperator"), new LocalizedText("en", "FilterOperator"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 576), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(7605), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 576), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(29), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode75() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 583), new QualifiedName(0, "ContentFilterElement"), new LocalizedText("en", "ContentFilterElement"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 583), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(22), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode76() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 586), new QualifiedName(0, "ContentFilter"), new LocalizedText("en", "ContentFilter"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 586), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(22), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode77() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 12877), new QualifiedName(0, "NormalizedString"), new LocalizedText("en", "NormalizedString"), new LocalizedText("en", "A string normalized based on the rules in the unicode specification."), UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 12877), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode78() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 589), new QualifiedName(0, "FilterOperand"), new LocalizedText("en", "FilterOperand"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 589), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(22), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode79() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 12878), new QualifiedName(0, "DecimalString"), new LocalizedText("en", "DecimalString"), new LocalizedText("en", "An arbitraty numeric value."), UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 12878), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode80() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 12879), new QualifiedName(0, "DurationString"), new LocalizedText("en", "DurationString"), new LocalizedText("en", "A period of time formatted as defined in ISO 8601-2000."), UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 12879), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode81() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 12880), new QualifiedName(0, "TimeString"), new LocalizedText("en", "TimeString"), new LocalizedText("en", "A time formatted as defined in ISO 8601-2000."), UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 12880), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode82() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 592), new QualifiedName(0, "ElementOperand"), new LocalizedText("en", "ElementOperand"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 592), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(589), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode83() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 12881), new QualifiedName(0, "DateString"), new LocalizedText("en", "DateString"), new LocalizedText("en", "A date formatted as defined in ISO 8601-2000."), UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 12881), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode84() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 595), new QualifiedName(0, "LiteralOperand"), new LocalizedText("en", "LiteralOperand"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 595), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(589), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode85() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 598), new QualifiedName(0, "AttributeOperand"), new LocalizedText("en", "AttributeOperand"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 598), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(589), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode86() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 601), new QualifiedName(0, "SimpleAttributeOperand"), new LocalizedText("en", "SimpleAttributeOperand"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 601), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(589), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode87() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 12890), new QualifiedName(0, "DiscoveryConfiguration"), new LocalizedText("en", "DiscoveryConfiguration"), new LocalizedText("en", "A base type for discovery configuration information."), UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 12890), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(22), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode88() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 12891), new QualifiedName(0, "MdnsDiscoveryConfiguration"), new LocalizedText("en", "MdnsDiscoveryConfiguration"), new LocalizedText("en", "The discovery information needed for mDNS registration."), UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 12891), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12890), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode89() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 659), new QualifiedName(0, "HistoryEvent"), new LocalizedText("en", "HistoryEvent"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 659), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(22), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode90() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 719), new QualifiedName(0, "MonitoringFilter"), new LocalizedText("en", "MonitoringFilter"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 719), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(22), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode91() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 8912), new QualifiedName(0, "TimeZoneDataType"), new LocalizedText("en", "TimeZoneDataType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 8912), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(22), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode92() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 725), new QualifiedName(0, "EventFilter"), new LocalizedText("en", "EventFilter"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 725), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(719), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode93() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 851), new QualifiedName(0, "RedundancySupport"), new LocalizedText("en", "RedundancySupport"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 851), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(7611), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 851), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(29), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode94() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 852), new QualifiedName(0, "ServerState"), new LocalizedText("en", "ServerState"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 852), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(7612), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 852), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(29), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode95() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 853), new QualifiedName(0, "RedundantServerDataType"), new LocalizedText("en", "RedundantServerDataType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 853), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(22), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode96() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 856), new QualifiedName(0, "SamplingIntervalDiagnosticsDataType"), new LocalizedText("en", "SamplingIntervalDiagnosticsDataType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 856), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(22), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode97() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 859), new QualifiedName(0, "ServerDiagnosticsSummaryDataType"), new LocalizedText("en", "ServerDiagnosticsSummaryDataType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 859), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(22), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode98() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 862), new QualifiedName(0, "ServerStatusDataType"), new LocalizedText("en", "ServerStatusDataType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 862), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(22), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode99() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 865), new QualifiedName(0, "SessionDiagnosticsDataType"), new LocalizedText("en", "SessionDiagnosticsDataType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 865), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(22), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode100() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 868), new QualifiedName(0, "SessionSecurityDiagnosticsDataType"), new LocalizedText("en", "SessionSecurityDiagnosticsDataType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 868), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(22), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode101() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 871), new QualifiedName(0, "ServiceCounterDataType"), new LocalizedText("en", "ServiceCounterDataType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 871), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(22), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode102() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 874), new QualifiedName(0, "SubscriptionDiagnosticsDataType"), new LocalizedText("en", "SubscriptionDiagnosticsDataType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 874), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(22), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode103() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 877), new QualifiedName(0, "ModelChangeStructureDataType"), new LocalizedText("en", "ModelChangeStructureDataType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 877), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(22), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode104() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 884), new QualifiedName(0, "Range"), new LocalizedText("en", "Range"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 884), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(22), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode105() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 887), new QualifiedName(0, "EUInformation"), new LocalizedText("en", "EUInformation"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 887), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(22), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode106() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 890), new QualifiedName(0, "ExceptionDeviationFormat"), new LocalizedText("en", "ExceptionDeviationFormat"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 890), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(7614), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 890), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(29), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode107() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 891), new QualifiedName(0, "Annotation"), new LocalizedText("en", "Annotation"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 891), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(22), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode108() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 894), new QualifiedName(0, "ProgramDiagnosticDataType"), new LocalizedText("en", "ProgramDiagnosticDataType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 894), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(22), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode109() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 897), new QualifiedName(0, "SemanticChangeStructureDataType"), new LocalizedText("en", "SemanticChangeStructureDataType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 897), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(22), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode110() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 920), new QualifiedName(0, "HistoryEventFieldList"), new LocalizedText("en", "HistoryEventFieldList"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 920), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(22), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode111() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 938), new QualifiedName(0, "IssuedIdentityToken"), new LocalizedText("en", "IssuedIdentityToken"), new LocalizedText("en", "A token representing a user identified by a WS-Security XML token."), UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 938), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(316), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode112() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 948), new QualifiedName(0, "AggregateConfiguration"), new LocalizedText("en", "AggregateConfiguration"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 948), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(22), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode113() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 2000), new QualifiedName(0, "ImageBMP"), new LocalizedText("en", "ImageBMP"), new LocalizedText("en", "An image encoded in BMP format."), UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 2000), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(30), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode114() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 2001), new QualifiedName(0, "ImageGIF"), new LocalizedText("en", "ImageGIF"), new LocalizedText("en", "An image encoded in GIF format."), UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 2001), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(30), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode115() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 2002), new QualifiedName(0, "ImageJPG"), new LocalizedText("en", "ImageJPG"), new LocalizedText("en", "An image encoded in JPEG format."), UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 2002), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(30), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode116() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 2003), new QualifiedName(0, "ImagePNG"), new LocalizedText("en", "ImagePNG"), new LocalizedText("en", "An image encoded in PNG format."), UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 2003), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(30), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode117() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 11234), new QualifiedName(0, "HistoryUpdateType"), new LocalizedText("en", "HistoryUpdateType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 11234), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11884), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11234), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(29), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode118() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 11293), new QualifiedName(0, "PerformUpdateType"), new LocalizedText("en", "PerformUpdateType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 11293), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11885), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11293), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(29), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode119() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 7594), new QualifiedName(0, "EnumValueType"), new LocalizedText("en", "EnumValueType"), new LocalizedText("en", "A mapping between a value of an enumerated type and a name and description."), UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 7594), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(22), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode120() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 11737), new QualifiedName(0, "BitFieldMaskDataType"), new LocalizedText("en", "BitFieldMaskDataType"), new LocalizedText("en", "A mask of 32 bits that can be updated individually by using the top 32 bits as a mask."), UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 11737), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(9), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode121() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 11939), new QualifiedName(0, "OpenFileMode"), new LocalizedText("en", "OpenFileMode"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 11939), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(11940), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 11939), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(29), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode122() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 11943), new QualifiedName(0, "EndpointUrlListDataType"), new LocalizedText("en", "EndpointUrlListDataType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 11943), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(22), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode123() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 11944), new QualifiedName(0, "NetworkGroupDataType"), new LocalizedText("en", "NetworkGroupDataType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 11944), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(22), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode124() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 12077), new QualifiedName(0, "AxisScaleEnumeration"), new LocalizedText("en", "AxisScaleEnumeration"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 12077), new NodeId(0, 46), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(12078), UInteger.valueOf(0)), true));
        node.addReference(new Reference(new NodeId(0, 12077), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(29), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode125() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 12079), new QualifiedName(0, "AxisInformation"), new LocalizedText("en", "AxisInformation"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 12079), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(22), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode126() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 12080), new QualifiedName(0, "XVType"), new LocalizedText("en", "XVType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 12080), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(22), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode127() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 12171), new QualifiedName(0, "ComplexNumberType"), new LocalizedText("en", "ComplexNumberType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 12171), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(22), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode128() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 12172), new QualifiedName(0, "DoubleComplexNumberType"), new LocalizedText("en", "DoubleComplexNumberType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 12172), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(22), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode129() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, new NodeId(0, 12189), new QualifiedName(0, "ServerOnNetwork"), new LocalizedText("en", "ServerOnNetwork"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(new NodeId(0, 12189), new NodeId(0, 45), new ExpandedNodeId(UShort.valueOf(0), null, UInteger.valueOf(22), UInteger.valueOf(0)), false));
        this.nodeManager.addNode(node);
    }

    public void loadAllNodes() {
        loadNode0();
        loadNode1();
        loadNode2();
        loadNode3();
        loadNode4();
        loadNode5();
        loadNode6();
        loadNode7();
        loadNode8();
        loadNode9();
        loadNode10();
        loadNode11();
        loadNode12();
        loadNode13();
        loadNode14();
        loadNode15();
        loadNode16();
        loadNode17();
        loadNode18();
        loadNode19();
        loadNode20();
        loadNode21();
        loadNode22();
        loadNode23();
        loadNode24();
        loadNode25();
        loadNode26();
        loadNode27();
        loadNode28();
        loadNode29();
        loadNode30();
        loadNode31();
        loadNode32();
        loadNode33();
        loadNode34();
        loadNode35();
        loadNode36();
        loadNode37();
        loadNode38();
        loadNode39();
        loadNode40();
        loadNode41();
        loadNode42();
        loadNode43();
        loadNode44();
        loadNode45();
        loadNode46();
        loadNode47();
        loadNode48();
        loadNode49();
        loadNode50();
        loadNode51();
        loadNode52();
        loadNode53();
        loadNode54();
        loadNode55();
        loadNode56();
        loadNode57();
        loadNode58();
        loadNode59();
        loadNode60();
        loadNode61();
        loadNode62();
        loadNode63();
        loadNode64();
        loadNode65();
        loadNode66();
        loadNode67();
        loadNode68();
        loadNode69();
        loadNode70();
        loadNode71();
        loadNode72();
        loadNode73();
        loadNode74();
        loadNode75();
        loadNode76();
        loadNode77();
        loadNode78();
        loadNode79();
        loadNode80();
        loadNode81();
        loadNode82();
        loadNode83();
        loadNode84();
        loadNode85();
        loadNode86();
        loadNode87();
        loadNode88();
        loadNode89();
        loadNode90();
        loadNode91();
        loadNode92();
        loadNode93();
        loadNode94();
        loadNode95();
        loadNode96();
        loadNode97();
        loadNode98();
        loadNode99();
        loadNode100();
        loadNode101();
        loadNode102();
        loadNode103();
        loadNode104();
        loadNode105();
        loadNode106();
        loadNode107();
        loadNode108();
        loadNode109();
        loadNode110();
        loadNode111();
        loadNode112();
        loadNode113();
        loadNode114();
        loadNode115();
        loadNode116();
        loadNode117();
        loadNode118();
        loadNode119();
        loadNode120();
        loadNode121();
        loadNode122();
        loadNode123();
        loadNode124();
        loadNode125();
        loadNode126();
        loadNode127();
        loadNode128();
        loadNode129();
    }
}
