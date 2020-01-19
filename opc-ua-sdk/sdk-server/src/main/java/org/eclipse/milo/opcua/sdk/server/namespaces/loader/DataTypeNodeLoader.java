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
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

class DataTypeNodeLoader {
    private final UaNodeContext context;

    private final NodeManager<UaNode> nodeManager;

    DataTypeNodeLoader(UaNodeContext context, NodeManager<UaNode> nodeManager) {
        this.context = context;
        this.nodeManager = nodeManager;
    }

    private void loadNode0() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.Boolean, new QualifiedName(0, "Boolean"), new LocalizedText("en", "Boolean"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.Boolean, Identifiers.HasSubtype, Identifiers.BaseDataType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode1() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.SByte, new QualifiedName(0, "SByte"), new LocalizedText("en", "SByte"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.SByte, Identifiers.HasSubtype, Identifiers.Integer.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode2() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.Byte, new QualifiedName(0, "Byte"), new LocalizedText("en", "Byte"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.Byte, Identifiers.HasSubtype, Identifiers.UInteger.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode3() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.Int16, new QualifiedName(0, "Int16"), new LocalizedText("en", "Int16"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.Int16, Identifiers.HasSubtype, Identifiers.Integer.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode4() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.UInt16, new QualifiedName(0, "UInt16"), new LocalizedText("en", "UInt16"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.UInt16, Identifiers.HasSubtype, Identifiers.UInteger.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode5() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.Int32, new QualifiedName(0, "Int32"), new LocalizedText("en", "Int32"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.Int32, Identifiers.HasSubtype, Identifiers.Integer.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode6() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.UInt32, new QualifiedName(0, "UInt32"), new LocalizedText("en", "UInt32"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.UInt32, Identifiers.HasSubtype, Identifiers.UInteger.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode7() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.Int64, new QualifiedName(0, "Int64"), new LocalizedText("en", "Int64"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.Int64, Identifiers.HasSubtype, Identifiers.Integer.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode8() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.UInt64, new QualifiedName(0, "UInt64"), new LocalizedText("en", "UInt64"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.UInt64, Identifiers.HasSubtype, Identifiers.UInteger.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode9() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.Float, new QualifiedName(0, "Float"), new LocalizedText("en", "Float"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.Float, Identifiers.HasSubtype, Identifiers.Number.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode10() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.Double, new QualifiedName(0, "Double"), new LocalizedText("en", "Double"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.Double, Identifiers.HasSubtype, Identifiers.Number.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode11() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.String, new QualifiedName(0, "String"), new LocalizedText("en", "String"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.String, Identifiers.HasSubtype, Identifiers.BaseDataType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode12() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.DateTime, new QualifiedName(0, "DateTime"), new LocalizedText("en", "DateTime"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.DateTime, Identifiers.HasSubtype, Identifiers.BaseDataType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode13() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.Guid, new QualifiedName(0, "Guid"), new LocalizedText("en", "Guid"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.Guid, Identifiers.HasSubtype, Identifiers.BaseDataType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode14() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.ByteString, new QualifiedName(0, "ByteString"), new LocalizedText("en", "ByteString"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.ByteString, Identifiers.HasSubtype, Identifiers.BaseDataType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode15() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.XmlElement, new QualifiedName(0, "XmlElement"), new LocalizedText("en", "XmlElement"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.XmlElement, Identifiers.HasSubtype, Identifiers.BaseDataType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode16() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.NodeId, new QualifiedName(0, "NodeId"), new LocalizedText("en", "NodeId"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.NodeId, Identifiers.HasSubtype, Identifiers.BaseDataType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode17() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.ExpandedNodeId, new QualifiedName(0, "ExpandedNodeId"), new LocalizedText("en", "ExpandedNodeId"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.ExpandedNodeId, Identifiers.HasSubtype, Identifiers.BaseDataType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode18() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.StatusCode, new QualifiedName(0, "StatusCode"), new LocalizedText("en", "StatusCode"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.StatusCode, Identifiers.HasSubtype, Identifiers.BaseDataType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode19() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.QualifiedName, new QualifiedName(0, "QualifiedName"), new LocalizedText("en", "QualifiedName"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.QualifiedName, Identifiers.HasSubtype, Identifiers.BaseDataType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode20() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.LocalizedText, new QualifiedName(0, "LocalizedText"), new LocalizedText("en", "LocalizedText"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.LocalizedText, Identifiers.HasSubtype, Identifiers.BaseDataType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode21() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.Structure, new QualifiedName(0, "Structure"), new LocalizedText("en", "Structure"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true);
        node.addReference(new Reference(Identifiers.Structure, Identifiers.HasSubtype, Identifiers.BaseDataType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode22() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.DataValue, new QualifiedName(0, "DataValue"), new LocalizedText("en", "DataValue"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.DataValue, Identifiers.HasSubtype, Identifiers.BaseDataType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode23() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.BaseDataType, new QualifiedName(0, "BaseDataType"), new LocalizedText("en", "BaseDataType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true);
        this.nodeManager.addNode(node);
    }

    private void loadNode24() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.DiagnosticInfo, new QualifiedName(0, "DiagnosticInfo"), new LocalizedText("en", "DiagnosticInfo"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.DiagnosticInfo, Identifiers.HasSubtype, Identifiers.BaseDataType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode25() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.Number, new QualifiedName(0, "Number"), new LocalizedText("en", "Number"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true);
        node.addReference(new Reference(Identifiers.Number, Identifiers.HasSubtype, Identifiers.BaseDataType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode26() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.Integer, new QualifiedName(0, "Integer"), new LocalizedText("en", "Integer"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true);
        node.addReference(new Reference(Identifiers.Integer, Identifiers.HasSubtype, Identifiers.Number.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode27() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.UInteger, new QualifiedName(0, "UInteger"), new LocalizedText("en", "UInteger"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true);
        node.addReference(new Reference(Identifiers.UInteger, Identifiers.HasSubtype, Identifiers.Number.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode28() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.Enumeration, new QualifiedName(0, "Enumeration"), new LocalizedText("en", "Enumeration"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), true);
        node.addReference(new Reference(Identifiers.Enumeration, Identifiers.HasSubtype, Identifiers.BaseDataType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode29() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.Image, new QualifiedName(0, "Image"), new LocalizedText("en", "Image"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.Image, Identifiers.HasSubtype, Identifiers.ByteString.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode30() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.NamingRuleType, new QualifiedName(0, "NamingRuleType"), new LocalizedText("en", "NamingRuleType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.NamingRuleType, Identifiers.HasProperty, Identifiers.NamingRuleType_EnumValues.expanded(), true));
        node.addReference(new Reference(Identifiers.NamingRuleType, Identifiers.HasSubtype, Identifiers.Enumeration.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode31() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.Decimal128, new QualifiedName(0, "Decimal128"), new LocalizedText("en", "Decimal128"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.Decimal128, Identifiers.HasSubtype, Identifiers.Number.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode32() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.IdType, new QualifiedName(0, "IdType"), new LocalizedText("en", "IdType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.IdType, Identifiers.HasProperty, Identifiers.IdType_EnumStrings.expanded(), true));
        node.addReference(new Reference(Identifiers.IdType, Identifiers.HasSubtype, Identifiers.Enumeration.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode33() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.NodeClass, new QualifiedName(0, "NodeClass"), new LocalizedText("en", "NodeClass"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.NodeClass, Identifiers.HasProperty, Identifiers.NodeClass_EnumValues.expanded(), true));
        node.addReference(new Reference(Identifiers.NodeClass, Identifiers.HasSubtype, Identifiers.Enumeration.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode34() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.TrustListMasks, new QualifiedName(0, "TrustListMasks"), new LocalizedText("en", "TrustListMasks"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.TrustListMasks, Identifiers.HasProperty, Identifiers.TrustListMasks_EnumValues.expanded(), true));
        node.addReference(new Reference(Identifiers.TrustListMasks, Identifiers.HasSubtype, Identifiers.Enumeration.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode35() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.TrustListDataType, new QualifiedName(0, "TrustListDataType"), new LocalizedText("en", "TrustListDataType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.TrustListDataType, Identifiers.HasSubtype, Identifiers.Structure.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode36() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.IntegerId, new QualifiedName(0, "IntegerId"), new LocalizedText("en", "IntegerId"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.IntegerId, Identifiers.HasSubtype, Identifiers.UInt32.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode37() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.Counter, new QualifiedName(0, "Counter"), new LocalizedText("en", "Counter"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.Counter, Identifiers.HasSubtype, Identifiers.UInt32.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode38() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.Duration, new QualifiedName(0, "Duration"), new LocalizedText("en", "Duration"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.Duration, Identifiers.HasSubtype, Identifiers.Double.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode39() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.NumericRange, new QualifiedName(0, "NumericRange"), new LocalizedText("en", "NumericRange"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.NumericRange, Identifiers.HasSubtype, Identifiers.String.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode40() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.Time, new QualifiedName(0, "Time"), new LocalizedText("en", "Time"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.Time, Identifiers.HasSubtype, Identifiers.String.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode41() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.Date, new QualifiedName(0, "Date"), new LocalizedText("en", "Date"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.Date, Identifiers.HasSubtype, Identifiers.DateTime.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode42() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.UtcTime, new QualifiedName(0, "UtcTime"), new LocalizedText("en", "UtcTime"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.UtcTime, Identifiers.HasSubtype, Identifiers.DateTime.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode43() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.LocaleId, new QualifiedName(0, "LocaleId"), new LocalizedText("en", "LocaleId"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.LocaleId, Identifiers.HasSubtype, Identifiers.String.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode44() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.Argument, new QualifiedName(0, "Argument"), new LocalizedText("en", "Argument"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.Argument, Identifiers.HasSubtype, Identifiers.Structure.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode45() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.StatusResult, new QualifiedName(0, "StatusResult"), new LocalizedText("en", "StatusResult"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.StatusResult, Identifiers.HasSubtype, Identifiers.Structure.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode46() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.MessageSecurityMode, new QualifiedName(0, "MessageSecurityMode"), new LocalizedText("en", "MessageSecurityMode"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.MessageSecurityMode, Identifiers.HasProperty, Identifiers.MessageSecurityMode_EnumStrings.expanded(), true));
        node.addReference(new Reference(Identifiers.MessageSecurityMode, Identifiers.HasSubtype, Identifiers.Enumeration.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode47() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.UserTokenType, new QualifiedName(0, "UserTokenType"), new LocalizedText("en", "UserTokenType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.UserTokenType, Identifiers.HasProperty, Identifiers.UserTokenType_EnumStrings.expanded(), true));
        node.addReference(new Reference(Identifiers.UserTokenType, Identifiers.HasSubtype, Identifiers.Enumeration.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode48() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.UserTokenPolicy, new QualifiedName(0, "UserTokenPolicy"), new LocalizedText("en", "UserTokenPolicy"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.UserTokenPolicy, Identifiers.HasSubtype, Identifiers.Structure.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode49() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.ApplicationType, new QualifiedName(0, "ApplicationType"), new LocalizedText("en", "ApplicationType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.ApplicationType, Identifiers.HasProperty, Identifiers.ApplicationType_EnumStrings.expanded(), true));
        node.addReference(new Reference(Identifiers.ApplicationType, Identifiers.HasSubtype, Identifiers.Enumeration.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode50() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.ApplicationDescription, new QualifiedName(0, "ApplicationDescription"), new LocalizedText("en", "ApplicationDescription"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.ApplicationDescription, Identifiers.HasSubtype, Identifiers.Structure.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode51() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.ApplicationInstanceCertificate, new QualifiedName(0, "ApplicationInstanceCertificate"), new LocalizedText("en", "ApplicationInstanceCertificate"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.ApplicationInstanceCertificate, Identifiers.HasSubtype, Identifiers.ByteString.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode52() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.EndpointDescription, new QualifiedName(0, "EndpointDescription"), new LocalizedText("en", "EndpointDescription"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.EndpointDescription, Identifiers.HasSubtype, Identifiers.Structure.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode53() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.SecurityTokenRequestType, new QualifiedName(0, "SecurityTokenRequestType"), new LocalizedText("en", "SecurityTokenRequestType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.SecurityTokenRequestType, Identifiers.HasProperty, Identifiers.SecurityTokenRequestType_EnumStrings.expanded(), true));
        node.addReference(new Reference(Identifiers.SecurityTokenRequestType, Identifiers.HasSubtype, Identifiers.Enumeration.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode54() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.UserIdentityToken, new QualifiedName(0, "UserIdentityToken"), new LocalizedText("en", "UserIdentityToken"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.UserIdentityToken, Identifiers.HasSubtype, Identifiers.Structure.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode55() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.AnonymousIdentityToken, new QualifiedName(0, "AnonymousIdentityToken"), new LocalizedText("en", "AnonymousIdentityToken"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.AnonymousIdentityToken, Identifiers.HasSubtype, Identifiers.UserIdentityToken.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode56() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.UserNameIdentityToken, new QualifiedName(0, "UserNameIdentityToken"), new LocalizedText("en", "UserNameIdentityToken"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.UserNameIdentityToken, Identifiers.HasSubtype, Identifiers.UserIdentityToken.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode57() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.X509IdentityToken, new QualifiedName(0, "X509IdentityToken"), new LocalizedText("en", "X509IdentityToken"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.X509IdentityToken, Identifiers.HasSubtype, Identifiers.UserIdentityToken.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode58() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.EndpointConfiguration, new QualifiedName(0, "EndpointConfiguration"), new LocalizedText("en", "EndpointConfiguration"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.EndpointConfiguration, Identifiers.HasSubtype, Identifiers.Structure.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode59() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.BuildInfo, new QualifiedName(0, "BuildInfo"), new LocalizedText("en", "BuildInfo"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.BuildInfo, Identifiers.HasSubtype, Identifiers.Structure.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode60() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.SignedSoftwareCertificate, new QualifiedName(0, "SignedSoftwareCertificate"), new LocalizedText("en", "SignedSoftwareCertificate"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.SignedSoftwareCertificate, Identifiers.HasSubtype, Identifiers.Structure.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode61() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.AttributeWriteMask, new QualifiedName(0, "AttributeWriteMask"), new LocalizedText("en", "AttributeWriteMask"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.AttributeWriteMask, Identifiers.HasProperty, Identifiers.AttributeWriteMask_EnumValues.expanded(), true));
        node.addReference(new Reference(Identifiers.AttributeWriteMask, Identifiers.HasSubtype, Identifiers.Enumeration.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode62() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.NodeAttributesMask, new QualifiedName(0, "NodeAttributesMask"), new LocalizedText("en", "NodeAttributesMask"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.NodeAttributesMask, Identifiers.HasProperty, Identifiers.NodeAttributesMask_EnumValues.expanded(), true));
        node.addReference(new Reference(Identifiers.NodeAttributesMask, Identifiers.HasSubtype, Identifiers.Enumeration.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode63() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.AddNodesItem, new QualifiedName(0, "AddNodesItem"), new LocalizedText("en", "AddNodesItem"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.AddNodesItem, Identifiers.HasSubtype, Identifiers.Structure.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode64() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.AddReferencesItem, new QualifiedName(0, "AddReferencesItem"), new LocalizedText("en", "AddReferencesItem"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.AddReferencesItem, Identifiers.HasSubtype, Identifiers.Structure.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode65() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.DeleteNodesItem, new QualifiedName(0, "DeleteNodesItem"), new LocalizedText("en", "DeleteNodesItem"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.DeleteNodesItem, Identifiers.HasSubtype, Identifiers.Structure.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode66() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.DeleteReferencesItem, new QualifiedName(0, "DeleteReferencesItem"), new LocalizedText("en", "DeleteReferencesItem"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.DeleteReferencesItem, Identifiers.HasSubtype, Identifiers.Structure.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode67() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.SessionAuthenticationToken, new QualifiedName(0, "SessionAuthenticationToken"), new LocalizedText("en", "SessionAuthenticationToken"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.SessionAuthenticationToken, Identifiers.HasSubtype, Identifiers.NodeId.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode68() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.RegisteredServer, new QualifiedName(0, "RegisteredServer"), new LocalizedText("en", "RegisteredServer"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.RegisteredServer, Identifiers.HasSubtype, Identifiers.Structure.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode69() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.OptionSet, new QualifiedName(0, "OptionSet"), new LocalizedText("en", "OptionSet"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.OptionSet, Identifiers.HasSubtype, Identifiers.Structure.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode70() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.Union, new QualifiedName(0, "Union"), new LocalizedText("en", "Union"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.Union, Identifiers.HasSubtype, Identifiers.Structure.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode71() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.ContinuationPoint, new QualifiedName(0, "ContinuationPoint"), new LocalizedText("en", "ContinuationPoint"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.ContinuationPoint, Identifiers.HasSubtype, Identifiers.ByteString.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode72() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.RelativePathElement, new QualifiedName(0, "RelativePathElement"), new LocalizedText("en", "RelativePathElement"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.RelativePathElement, Identifiers.HasSubtype, Identifiers.Structure.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode73() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.RelativePath, new QualifiedName(0, "RelativePath"), new LocalizedText("en", "RelativePath"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.RelativePath, Identifiers.HasSubtype, Identifiers.Structure.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode74() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.FilterOperator, new QualifiedName(0, "FilterOperator"), new LocalizedText("en", "FilterOperator"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.FilterOperator, Identifiers.HasProperty, Identifiers.FilterOperator_EnumStrings.expanded(), true));
        node.addReference(new Reference(Identifiers.FilterOperator, Identifiers.HasSubtype, Identifiers.Enumeration.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode75() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.ContentFilterElement, new QualifiedName(0, "ContentFilterElement"), new LocalizedText("en", "ContentFilterElement"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.ContentFilterElement, Identifiers.HasSubtype, Identifiers.Structure.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode76() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.ContentFilter, new QualifiedName(0, "ContentFilter"), new LocalizedText("en", "ContentFilter"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.ContentFilter, Identifiers.HasSubtype, Identifiers.Structure.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode77() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.NormalizedString, new QualifiedName(0, "NormalizedString"), new LocalizedText("en", "NormalizedString"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.NormalizedString, Identifiers.HasSubtype, Identifiers.String.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode78() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.FilterOperand, new QualifiedName(0, "FilterOperand"), new LocalizedText("en", "FilterOperand"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.FilterOperand, Identifiers.HasSubtype, Identifiers.Structure.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode79() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.DecimalString, new QualifiedName(0, "DecimalString"), new LocalizedText("en", "DecimalString"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.DecimalString, Identifiers.HasSubtype, Identifiers.String.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode80() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.DurationString, new QualifiedName(0, "DurationString"), new LocalizedText("en", "DurationString"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.DurationString, Identifiers.HasSubtype, Identifiers.String.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode81() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.TimeString, new QualifiedName(0, "TimeString"), new LocalizedText("en", "TimeString"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.TimeString, Identifiers.HasSubtype, Identifiers.String.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode82() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.ElementOperand, new QualifiedName(0, "ElementOperand"), new LocalizedText("en", "ElementOperand"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.ElementOperand, Identifiers.HasSubtype, Identifiers.FilterOperand.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode83() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.DateString, new QualifiedName(0, "DateString"), new LocalizedText("en", "DateString"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.DateString, Identifiers.HasSubtype, Identifiers.String.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode84() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.LiteralOperand, new QualifiedName(0, "LiteralOperand"), new LocalizedText("en", "LiteralOperand"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.LiteralOperand, Identifiers.HasSubtype, Identifiers.FilterOperand.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode85() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.AttributeOperand, new QualifiedName(0, "AttributeOperand"), new LocalizedText("en", "AttributeOperand"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.AttributeOperand, Identifiers.HasSubtype, Identifiers.FilterOperand.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode86() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.SimpleAttributeOperand, new QualifiedName(0, "SimpleAttributeOperand"), new LocalizedText("en", "SimpleAttributeOperand"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.SimpleAttributeOperand, Identifiers.HasSubtype, Identifiers.FilterOperand.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode87() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.DiscoveryConfiguration, new QualifiedName(0, "DiscoveryConfiguration"), new LocalizedText("en", "DiscoveryConfiguration"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.DiscoveryConfiguration, Identifiers.HasSubtype, Identifiers.Structure.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode88() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.MdnsDiscoveryConfiguration, new QualifiedName(0, "MdnsDiscoveryConfiguration"), new LocalizedText("en", "MdnsDiscoveryConfiguration"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.MdnsDiscoveryConfiguration, Identifiers.HasSubtype, Identifiers.DiscoveryConfiguration.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode89() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.HistoryEvent, new QualifiedName(0, "HistoryEvent"), new LocalizedText("en", "HistoryEvent"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.HistoryEvent, Identifiers.HasSubtype, Identifiers.Structure.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode90() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.MonitoringFilter, new QualifiedName(0, "MonitoringFilter"), new LocalizedText("en", "MonitoringFilter"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.MonitoringFilter, Identifiers.HasSubtype, Identifiers.Structure.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode91() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.TimeZoneDataType, new QualifiedName(0, "TimeZoneDataType"), new LocalizedText("en", "TimeZoneDataType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.TimeZoneDataType, Identifiers.HasSubtype, Identifiers.Structure.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode92() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.EventFilter, new QualifiedName(0, "EventFilter"), new LocalizedText("en", "EventFilter"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.EventFilter, Identifiers.HasSubtype, Identifiers.MonitoringFilter.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode93() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.RedundancySupport, new QualifiedName(0, "RedundancySupport"), new LocalizedText("en", "RedundancySupport"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.RedundancySupport, Identifiers.HasProperty, Identifiers.RedundancySupport_EnumStrings.expanded(), true));
        node.addReference(new Reference(Identifiers.RedundancySupport, Identifiers.HasSubtype, Identifiers.Enumeration.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode94() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.ServerState, new QualifiedName(0, "ServerState"), new LocalizedText("en", "ServerState"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.ServerState, Identifiers.HasProperty, Identifiers.ServerState_EnumStrings.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerState, Identifiers.HasSubtype, Identifiers.Enumeration.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode95() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.RedundantServerDataType, new QualifiedName(0, "RedundantServerDataType"), new LocalizedText("en", "RedundantServerDataType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.RedundantServerDataType, Identifiers.HasSubtype, Identifiers.Structure.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode96() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.SamplingIntervalDiagnosticsDataType, new QualifiedName(0, "SamplingIntervalDiagnosticsDataType"), new LocalizedText("en", "SamplingIntervalDiagnosticsDataType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.SamplingIntervalDiagnosticsDataType, Identifiers.HasSubtype, Identifiers.Structure.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode97() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.ServerDiagnosticsSummaryDataType, new QualifiedName(0, "ServerDiagnosticsSummaryDataType"), new LocalizedText("en", "ServerDiagnosticsSummaryDataType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.ServerDiagnosticsSummaryDataType, Identifiers.HasSubtype, Identifiers.Structure.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode98() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.ServerStatusDataType, new QualifiedName(0, "ServerStatusDataType"), new LocalizedText("en", "ServerStatusDataType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.ServerStatusDataType, Identifiers.HasSubtype, Identifiers.Structure.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode99() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.SessionDiagnosticsDataType, new QualifiedName(0, "SessionDiagnosticsDataType"), new LocalizedText("en", "SessionDiagnosticsDataType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.SessionDiagnosticsDataType, Identifiers.HasSubtype, Identifiers.Structure.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode100() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.SessionSecurityDiagnosticsDataType, new QualifiedName(0, "SessionSecurityDiagnosticsDataType"), new LocalizedText("en", "SessionSecurityDiagnosticsDataType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.SessionSecurityDiagnosticsDataType, Identifiers.HasSubtype, Identifiers.Structure.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode101() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.ServiceCounterDataType, new QualifiedName(0, "ServiceCounterDataType"), new LocalizedText("en", "ServiceCounterDataType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.ServiceCounterDataType, Identifiers.HasSubtype, Identifiers.Structure.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode102() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.SubscriptionDiagnosticsDataType, new QualifiedName(0, "SubscriptionDiagnosticsDataType"), new LocalizedText("en", "SubscriptionDiagnosticsDataType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.SubscriptionDiagnosticsDataType, Identifiers.HasSubtype, Identifiers.Structure.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode103() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.ModelChangeStructureDataType, new QualifiedName(0, "ModelChangeStructureDataType"), new LocalizedText("en", "ModelChangeStructureDataType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.ModelChangeStructureDataType, Identifiers.HasSubtype, Identifiers.Structure.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode104() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.Range, new QualifiedName(0, "Range"), new LocalizedText("en", "Range"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.Range, Identifiers.HasSubtype, Identifiers.Structure.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode105() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.EUInformation, new QualifiedName(0, "EUInformation"), new LocalizedText("en", "EUInformation"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.EUInformation, Identifiers.HasSubtype, Identifiers.Structure.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode106() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.ExceptionDeviationFormat, new QualifiedName(0, "ExceptionDeviationFormat"), new LocalizedText("en", "ExceptionDeviationFormat"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.ExceptionDeviationFormat, Identifiers.HasProperty, Identifiers.ExceptionDeviationFormat_EnumStrings.expanded(), true));
        node.addReference(new Reference(Identifiers.ExceptionDeviationFormat, Identifiers.HasSubtype, Identifiers.Enumeration.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode107() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.Annotation, new QualifiedName(0, "Annotation"), new LocalizedText("en", "Annotation"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.Annotation, Identifiers.HasSubtype, Identifiers.Structure.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode108() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.ProgramDiagnosticDataType, new QualifiedName(0, "ProgramDiagnosticDataType"), new LocalizedText("en", "ProgramDiagnosticDataType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.ProgramDiagnosticDataType, Identifiers.HasSubtype, Identifiers.Structure.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode109() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.SemanticChangeStructureDataType, new QualifiedName(0, "SemanticChangeStructureDataType"), new LocalizedText("en", "SemanticChangeStructureDataType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.SemanticChangeStructureDataType, Identifiers.HasSubtype, Identifiers.Structure.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode110() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.HistoryEventFieldList, new QualifiedName(0, "HistoryEventFieldList"), new LocalizedText("en", "HistoryEventFieldList"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.HistoryEventFieldList, Identifiers.HasSubtype, Identifiers.Structure.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode111() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.IssuedIdentityToken, new QualifiedName(0, "IssuedIdentityToken"), new LocalizedText("en", "IssuedIdentityToken"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.IssuedIdentityToken, Identifiers.HasSubtype, Identifiers.UserIdentityToken.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode112() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.AggregateConfiguration, new QualifiedName(0, "AggregateConfiguration"), new LocalizedText("en", "AggregateConfiguration"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.AggregateConfiguration, Identifiers.HasSubtype, Identifiers.Structure.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode113() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.ImageBMP, new QualifiedName(0, "ImageBMP"), new LocalizedText("en", "ImageBMP"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.ImageBMP, Identifiers.HasSubtype, Identifiers.Image.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode114() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.ImageGIF, new QualifiedName(0, "ImageGIF"), new LocalizedText("en", "ImageGIF"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.ImageGIF, Identifiers.HasSubtype, Identifiers.Image.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode115() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.ImageJPG, new QualifiedName(0, "ImageJPG"), new LocalizedText("en", "ImageJPG"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.ImageJPG, Identifiers.HasSubtype, Identifiers.Image.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode116() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.ImagePNG, new QualifiedName(0, "ImagePNG"), new LocalizedText("en", "ImagePNG"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.ImagePNG, Identifiers.HasSubtype, Identifiers.Image.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode117() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.HistoryUpdateType, new QualifiedName(0, "HistoryUpdateType"), new LocalizedText("en", "HistoryUpdateType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.HistoryUpdateType, Identifiers.HasProperty, Identifiers.HistoryUpdateType_EnumValues.expanded(), true));
        node.addReference(new Reference(Identifiers.HistoryUpdateType, Identifiers.HasSubtype, Identifiers.Enumeration.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode118() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.PerformUpdateType, new QualifiedName(0, "PerformUpdateType"), new LocalizedText("en", "PerformUpdateType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.PerformUpdateType, Identifiers.HasProperty, Identifiers.PerformUpdateType_EnumValues.expanded(), true));
        node.addReference(new Reference(Identifiers.PerformUpdateType, Identifiers.HasSubtype, Identifiers.Enumeration.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode119() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.EnumValueType, new QualifiedName(0, "EnumValueType"), new LocalizedText("en", "EnumValueType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.EnumValueType, Identifiers.HasSubtype, Identifiers.Structure.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode120() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.BitFieldMaskDataType, new QualifiedName(0, "BitFieldMaskDataType"), new LocalizedText("en", "BitFieldMaskDataType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.BitFieldMaskDataType, Identifiers.HasSubtype, Identifiers.UInt64.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode121() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.OpenFileMode, new QualifiedName(0, "OpenFileMode"), new LocalizedText("en", "OpenFileMode"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.OpenFileMode, Identifiers.HasProperty, Identifiers.OpenFileMode_EnumValues.expanded(), true));
        node.addReference(new Reference(Identifiers.OpenFileMode, Identifiers.HasSubtype, Identifiers.Enumeration.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode122() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.EndpointUrlListDataType, new QualifiedName(0, "EndpointUrlListDataType"), new LocalizedText("en", "EndpointUrlListDataType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.EndpointUrlListDataType, Identifiers.HasSubtype, Identifiers.Structure.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode123() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.NetworkGroupDataType, new QualifiedName(0, "NetworkGroupDataType"), new LocalizedText("en", "NetworkGroupDataType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.NetworkGroupDataType, Identifiers.HasSubtype, Identifiers.Structure.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode124() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.AxisScaleEnumeration, new QualifiedName(0, "AxisScaleEnumeration"), new LocalizedText("en", "AxisScaleEnumeration"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.AxisScaleEnumeration, Identifiers.HasProperty, Identifiers.AxisScaleEnumeration_EnumStrings.expanded(), true));
        node.addReference(new Reference(Identifiers.AxisScaleEnumeration, Identifiers.HasSubtype, Identifiers.Enumeration.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode125() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.AxisInformation, new QualifiedName(0, "AxisInformation"), new LocalizedText("en", "AxisInformation"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.AxisInformation, Identifiers.HasSubtype, Identifiers.Structure.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode126() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.XVType, new QualifiedName(0, "XVType"), new LocalizedText("en", "XVType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.XVType, Identifiers.HasSubtype, Identifiers.Structure.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode127() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.ComplexNumberType, new QualifiedName(0, "ComplexNumberType"), new LocalizedText("en", "ComplexNumberType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.ComplexNumberType, Identifiers.HasSubtype, Identifiers.Structure.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode128() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.DoubleComplexNumberType, new QualifiedName(0, "DoubleComplexNumberType"), new LocalizedText("en", "DoubleComplexNumberType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.DoubleComplexNumberType, Identifiers.HasSubtype, Identifiers.Structure.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode129() {
        UaDataTypeNode node = new UaDataTypeNode(this.context, Identifiers.ServerOnNetwork, new QualifiedName(0, "ServerOnNetwork"), new LocalizedText("en", "ServerOnNetwork"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), false);
        node.addReference(new Reference(Identifiers.ServerOnNetwork, Identifiers.HasSubtype, Identifiers.Structure.expanded(), false));
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
