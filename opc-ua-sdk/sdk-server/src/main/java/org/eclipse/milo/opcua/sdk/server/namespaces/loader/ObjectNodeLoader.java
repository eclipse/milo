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
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.AddressSpaceFileTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.AggregateConfigurationTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.AggregateFunctionTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.BaseObjectTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.CertificateGroupFolderTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.CertificateGroupTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.DataTypeEncodingTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.DataTypeSystemTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.ExclusiveLimitStateMachineTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.FileDirectoryTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.FileTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.FolderTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.HistoricalDataConfigurationTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.HistoryServerCapabilitiesTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.ModellingRuleTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.NamespaceMetadataTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.NamespacesTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.OperationLimitsTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.ServerCapabilitiesTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.ServerConfigurationTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.ServerDiagnosticsTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.ServerRedundancyTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.ServerTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.SessionDiagnosticsObjectTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.SessionsDiagnosticsSummaryTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.ShelvedStateMachineTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.StateTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.TransitionTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.TrustListTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.VendorServerInfoTypeNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

class ObjectNodeLoader {
    private final UaNodeContext context;

    private final NodeManager<UaNode> nodeManager;

    ObjectNodeLoader(UaNodeContext context, NodeManager<UaNode> nodeManager) {
        this.context = context;
        this.nodeManager = nodeManager;
    }

    private void loadNode0() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.EnumValueType_Encoding_DefaultBinary, new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.EnumValueType_Encoding_DefaultBinary, Identifiers.HasEncoding, Identifiers.EnumValueType.expanded(), false));
        node.addReference(new Reference(Identifiers.EnumValueType_Encoding_DefaultBinary, Identifiers.HasDescription, Identifiers.OpcUa_BinarySchema_EnumValueType.expanded(), true));
        node.addReference(new Reference(Identifiers.EnumValueType_Encoding_DefaultBinary, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode1() {
        ModellingRuleTypeNode node = new ModellingRuleTypeNode(this.context, Identifiers.ModellingRule_Mandatory, new QualifiedName(0, "Mandatory"), new LocalizedText("en", "Mandatory"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.ModellingRule_Mandatory, Identifiers.HasProperty, Identifiers.ModellingRule_Mandatory_NamingRule.expanded(), true));
        node.addReference(new Reference(Identifiers.ModellingRule_Mandatory, Identifiers.HasTypeDefinition, Identifiers.ModellingRuleType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode2() {
        ModellingRuleTypeNode node = new ModellingRuleTypeNode(this.context, Identifiers.ModellingRule_MandatoryShared, new QualifiedName(0, "MandatoryShared"), new LocalizedText("en", "MandatoryShared"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.ModellingRule_MandatoryShared, Identifiers.HasProperty, Identifiers.ModellingRule_MandatoryShared_NamingRule.expanded(), true));
        node.addReference(new Reference(Identifiers.ModellingRule_MandatoryShared, Identifiers.HasTypeDefinition, Identifiers.ModellingRuleType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode3() {
        ModellingRuleTypeNode node = new ModellingRuleTypeNode(this.context, Identifiers.ModellingRule_Optional, new QualifiedName(0, "Optional"), new LocalizedText("en", "Optional"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.ModellingRule_Optional, Identifiers.HasProperty, Identifiers.ModellingRule_Optional_NamingRule.expanded(), true));
        node.addReference(new Reference(Identifiers.ModellingRule_Optional, Identifiers.HasTypeDefinition, Identifiers.ModellingRuleType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode4() {
        ModellingRuleTypeNode node = new ModellingRuleTypeNode(this.context, Identifiers.ModellingRule_ExposesItsArray, new QualifiedName(0, "ExposesItsArray"), new LocalizedText("en", "ExposesItsArray"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.ModellingRule_ExposesItsArray, Identifiers.HasProperty, Identifiers.ModellingRule_ExposesItsArray_NamingRule.expanded(), true));
        node.addReference(new Reference(Identifiers.ModellingRule_ExposesItsArray, Identifiers.HasTypeDefinition, Identifiers.ModellingRuleType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode5() {
        FolderTypeNode node = new FolderTypeNode(this.context, Identifiers.RootFolder, new QualifiedName(0, "Root"), new LocalizedText("en", "Root"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.RootFolder, Identifiers.HasTypeDefinition, Identifiers.FolderType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode6() {
        FolderTypeNode node = new FolderTypeNode(this.context, Identifiers.ObjectsFolder, new QualifiedName(0, "Objects"), new LocalizedText("en", "Objects"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.ObjectsFolder, Identifiers.Organizes, Identifiers.RootFolder.expanded(), false));
        node.addReference(new Reference(Identifiers.ObjectsFolder, Identifiers.HasTypeDefinition, Identifiers.FolderType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode7() {
        FolderTypeNode node = new FolderTypeNode(this.context, Identifiers.TypesFolder, new QualifiedName(0, "Types"), new LocalizedText("en", "Types"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.TypesFolder, Identifiers.Organizes, Identifiers.RootFolder.expanded(), false));
        node.addReference(new Reference(Identifiers.TypesFolder, Identifiers.HasTypeDefinition, Identifiers.FolderType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode8() {
        FolderTypeNode node = new FolderTypeNode(this.context, Identifiers.ViewsFolder, new QualifiedName(0, "Views"), new LocalizedText("en", "Views"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.ViewsFolder, Identifiers.Organizes, Identifiers.RootFolder.expanded(), false));
        node.addReference(new Reference(Identifiers.ViewsFolder, Identifiers.HasTypeDefinition, Identifiers.FolderType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode9() {
        FolderTypeNode node = new FolderTypeNode(this.context, Identifiers.ObjectTypesFolder, new QualifiedName(0, "ObjectTypes"), new LocalizedText("en", "ObjectTypes"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.ObjectTypesFolder, Identifiers.Organizes, Identifiers.TypesFolder.expanded(), false));
        node.addReference(new Reference(Identifiers.ObjectTypesFolder, Identifiers.Organizes, Identifiers.BaseObjectType.expanded(), true));
        node.addReference(new Reference(Identifiers.ObjectTypesFolder, Identifiers.HasTypeDefinition, Identifiers.FolderType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode10() {
        FolderTypeNode node = new FolderTypeNode(this.context, Identifiers.VariableTypesFolder, new QualifiedName(0, "VariableTypes"), new LocalizedText("en", "VariableTypes"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.VariableTypesFolder, Identifiers.Organizes, Identifiers.TypesFolder.expanded(), false));
        node.addReference(new Reference(Identifiers.VariableTypesFolder, Identifiers.Organizes, Identifiers.BaseVariableType.expanded(), true));
        node.addReference(new Reference(Identifiers.VariableTypesFolder, Identifiers.HasTypeDefinition, Identifiers.FolderType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode11() {
        FolderTypeNode node = new FolderTypeNode(this.context, Identifiers.DataTypesFolder, new QualifiedName(0, "DataTypes"), new LocalizedText("en", "DataTypes"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.DataTypesFolder, Identifiers.Organizes, Identifiers.TypesFolder.expanded(), false));
        node.addReference(new Reference(Identifiers.DataTypesFolder, Identifiers.Organizes, Identifiers.BaseDataType.expanded(), true));
        node.addReference(new Reference(Identifiers.DataTypesFolder, Identifiers.HasTypeDefinition, Identifiers.FolderType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode12() {
        FolderTypeNode node = new FolderTypeNode(this.context, Identifiers.ReferenceTypesFolder, new QualifiedName(0, "ReferenceTypes"), new LocalizedText("en", "ReferenceTypes"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.ReferenceTypesFolder, Identifiers.Organizes, Identifiers.TypesFolder.expanded(), false));
        node.addReference(new Reference(Identifiers.ReferenceTypesFolder, Identifiers.Organizes, Identifiers.References.expanded(), true));
        node.addReference(new Reference(Identifiers.ReferenceTypesFolder, Identifiers.HasTypeDefinition, Identifiers.FolderType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode13() {
        DataTypeSystemTypeNode node = new DataTypeSystemTypeNode(this.context, Identifiers.XmlSchema_TypeSystem, new QualifiedName(0, "XML Schema"), new LocalizedText("en", "XML Schema"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.XmlSchema_TypeSystem, Identifiers.Organizes, Identifiers.DataTypesFolder.expanded(), false));
        node.addReference(new Reference(Identifiers.XmlSchema_TypeSystem, Identifiers.HasTypeDefinition, Identifiers.DataTypeSystemType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode14() {
        DataTypeSystemTypeNode node = new DataTypeSystemTypeNode(this.context, Identifiers.OPCBinarySchema_TypeSystem, new QualifiedName(0, "OPC Binary"), new LocalizedText("en", "OPC Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.OPCBinarySchema_TypeSystem, Identifiers.Organizes, Identifiers.DataTypesFolder.expanded(), false));
        node.addReference(new Reference(Identifiers.OPCBinarySchema_TypeSystem, Identifiers.HasTypeDefinition, Identifiers.DataTypeSystemType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode15() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.Argument_Encoding_DefaultXml, new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.Argument_Encoding_DefaultXml, Identifiers.HasEncoding, Identifiers.Argument.expanded(), false));
        node.addReference(new Reference(Identifiers.Argument_Encoding_DefaultXml, Identifiers.HasDescription, Identifiers.OpcUa_XmlSchema_Argument.expanded(), true));
        node.addReference(new Reference(Identifiers.Argument_Encoding_DefaultXml, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode16() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.Argument_Encoding_DefaultBinary, new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.Argument_Encoding_DefaultBinary, Identifiers.HasEncoding, Identifiers.Argument.expanded(), false));
        node.addReference(new Reference(Identifiers.Argument_Encoding_DefaultBinary, Identifiers.HasDescription, Identifiers.OpcUa_BinarySchema_Argument.expanded(), true));
        node.addReference(new Reference(Identifiers.Argument_Encoding_DefaultBinary, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode17() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.StatusResult_Encoding_DefaultXml, new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.StatusResult_Encoding_DefaultXml, Identifiers.HasEncoding, Identifiers.StatusResult.expanded(), false));
        node.addReference(new Reference(Identifiers.StatusResult_Encoding_DefaultXml, Identifiers.HasDescription, Identifiers.OpcUa_XmlSchema_StatusResult.expanded(), true));
        node.addReference(new Reference(Identifiers.StatusResult_Encoding_DefaultXml, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode18() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.StatusResult_Encoding_DefaultBinary, new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.StatusResult_Encoding_DefaultBinary, Identifiers.HasEncoding, Identifiers.StatusResult.expanded(), false));
        node.addReference(new Reference(Identifiers.StatusResult_Encoding_DefaultBinary, Identifiers.HasDescription, Identifiers.OpcUa_BinarySchema_StatusResult.expanded(), true));
        node.addReference(new Reference(Identifiers.StatusResult_Encoding_DefaultBinary, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode19() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.UserTokenPolicy_Encoding_DefaultXml, new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.UserTokenPolicy_Encoding_DefaultXml, Identifiers.HasEncoding, Identifiers.UserTokenPolicy.expanded(), false));
        node.addReference(new Reference(Identifiers.UserTokenPolicy_Encoding_DefaultXml, Identifiers.HasDescription, Identifiers.OpcUa_XmlSchema_UserTokenPolicy.expanded(), true));
        node.addReference(new Reference(Identifiers.UserTokenPolicy_Encoding_DefaultXml, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode20() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.UserTokenPolicy_Encoding_DefaultBinary, new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.UserTokenPolicy_Encoding_DefaultBinary, Identifiers.HasEncoding, Identifiers.UserTokenPolicy.expanded(), false));
        node.addReference(new Reference(Identifiers.UserTokenPolicy_Encoding_DefaultBinary, Identifiers.HasDescription, Identifiers.OpcUa_BinarySchema_UserTokenPolicy.expanded(), true));
        node.addReference(new Reference(Identifiers.UserTokenPolicy_Encoding_DefaultBinary, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode21() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.ApplicationDescription_Encoding_DefaultXml, new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.ApplicationDescription_Encoding_DefaultXml, Identifiers.HasEncoding, Identifiers.ApplicationDescription.expanded(), false));
        node.addReference(new Reference(Identifiers.ApplicationDescription_Encoding_DefaultXml, Identifiers.HasDescription, Identifiers.OpcUa_XmlSchema_ApplicationDescription.expanded(), true));
        node.addReference(new Reference(Identifiers.ApplicationDescription_Encoding_DefaultXml, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode22() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.ApplicationDescription_Encoding_DefaultBinary, new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.ApplicationDescription_Encoding_DefaultBinary, Identifiers.HasEncoding, Identifiers.ApplicationDescription.expanded(), false));
        node.addReference(new Reference(Identifiers.ApplicationDescription_Encoding_DefaultBinary, Identifiers.HasDescription, Identifiers.OpcUa_BinarySchema_ApplicationDescription.expanded(), true));
        node.addReference(new Reference(Identifiers.ApplicationDescription_Encoding_DefaultBinary, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode23() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.EndpointDescription_Encoding_DefaultXml, new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.EndpointDescription_Encoding_DefaultXml, Identifiers.HasEncoding, Identifiers.EndpointDescription.expanded(), false));
        node.addReference(new Reference(Identifiers.EndpointDescription_Encoding_DefaultXml, Identifiers.HasDescription, Identifiers.OpcUa_XmlSchema_EndpointDescription.expanded(), true));
        node.addReference(new Reference(Identifiers.EndpointDescription_Encoding_DefaultXml, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode24() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.EndpointDescription_Encoding_DefaultBinary, new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.EndpointDescription_Encoding_DefaultBinary, Identifiers.HasEncoding, Identifiers.EndpointDescription.expanded(), false));
        node.addReference(new Reference(Identifiers.EndpointDescription_Encoding_DefaultBinary, Identifiers.HasDescription, Identifiers.OpcUa_BinarySchema_EndpointDescription.expanded(), true));
        node.addReference(new Reference(Identifiers.EndpointDescription_Encoding_DefaultBinary, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode25() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.UserIdentityToken_Encoding_DefaultXml, new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.UserIdentityToken_Encoding_DefaultXml, Identifiers.HasEncoding, Identifiers.UserIdentityToken.expanded(), false));
        node.addReference(new Reference(Identifiers.UserIdentityToken_Encoding_DefaultXml, Identifiers.HasDescription, Identifiers.OpcUa_XmlSchema_UserIdentityToken.expanded(), true));
        node.addReference(new Reference(Identifiers.UserIdentityToken_Encoding_DefaultXml, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode26() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.UserIdentityToken_Encoding_DefaultBinary, new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.UserIdentityToken_Encoding_DefaultBinary, Identifiers.HasEncoding, Identifiers.UserIdentityToken.expanded(), false));
        node.addReference(new Reference(Identifiers.UserIdentityToken_Encoding_DefaultBinary, Identifiers.HasDescription, Identifiers.OpcUa_BinarySchema_UserIdentityToken.expanded(), true));
        node.addReference(new Reference(Identifiers.UserIdentityToken_Encoding_DefaultBinary, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode27() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.AnonymousIdentityToken_Encoding_DefaultXml, new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.AnonymousIdentityToken_Encoding_DefaultXml, Identifiers.HasEncoding, Identifiers.AnonymousIdentityToken.expanded(), false));
        node.addReference(new Reference(Identifiers.AnonymousIdentityToken_Encoding_DefaultXml, Identifiers.HasDescription, Identifiers.OpcUa_XmlSchema_AnonymousIdentityToken.expanded(), true));
        node.addReference(new Reference(Identifiers.AnonymousIdentityToken_Encoding_DefaultXml, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode28() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.AnonymousIdentityToken_Encoding_DefaultBinary, new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.AnonymousIdentityToken_Encoding_DefaultBinary, Identifiers.HasEncoding, Identifiers.AnonymousIdentityToken.expanded(), false));
        node.addReference(new Reference(Identifiers.AnonymousIdentityToken_Encoding_DefaultBinary, Identifiers.HasDescription, Identifiers.OpcUa_BinarySchema_AnonymousIdentityToken.expanded(), true));
        node.addReference(new Reference(Identifiers.AnonymousIdentityToken_Encoding_DefaultBinary, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode29() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.UserNameIdentityToken_Encoding_DefaultXml, new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.UserNameIdentityToken_Encoding_DefaultXml, Identifiers.HasEncoding, Identifiers.UserNameIdentityToken.expanded(), false));
        node.addReference(new Reference(Identifiers.UserNameIdentityToken_Encoding_DefaultXml, Identifiers.HasDescription, Identifiers.OpcUa_XmlSchema_UserNameIdentityToken.expanded(), true));
        node.addReference(new Reference(Identifiers.UserNameIdentityToken_Encoding_DefaultXml, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode30() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.UserNameIdentityToken_Encoding_DefaultBinary, new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.UserNameIdentityToken_Encoding_DefaultBinary, Identifiers.HasEncoding, Identifiers.UserNameIdentityToken.expanded(), false));
        node.addReference(new Reference(Identifiers.UserNameIdentityToken_Encoding_DefaultBinary, Identifiers.HasDescription, Identifiers.OpcUa_BinarySchema_UserNameIdentityToken.expanded(), true));
        node.addReference(new Reference(Identifiers.UserNameIdentityToken_Encoding_DefaultBinary, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode31() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.X509IdentityToken_Encoding_DefaultXml, new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.X509IdentityToken_Encoding_DefaultXml, Identifiers.HasEncoding, Identifiers.X509IdentityToken.expanded(), false));
        node.addReference(new Reference(Identifiers.X509IdentityToken_Encoding_DefaultXml, Identifiers.HasDescription, Identifiers.OpcUa_XmlSchema_X509IdentityToken.expanded(), true));
        node.addReference(new Reference(Identifiers.X509IdentityToken_Encoding_DefaultXml, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode32() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.X509IdentityToken_Encoding_DefaultBinary, new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.X509IdentityToken_Encoding_DefaultBinary, Identifiers.HasEncoding, Identifiers.X509IdentityToken.expanded(), false));
        node.addReference(new Reference(Identifiers.X509IdentityToken_Encoding_DefaultBinary, Identifiers.HasDescription, Identifiers.OpcUa_BinarySchema_X509IdentityToken.expanded(), true));
        node.addReference(new Reference(Identifiers.X509IdentityToken_Encoding_DefaultBinary, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode33() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.EndpointConfiguration_Encoding_DefaultXml, new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.EndpointConfiguration_Encoding_DefaultXml, Identifiers.HasEncoding, Identifiers.EndpointConfiguration.expanded(), false));
        node.addReference(new Reference(Identifiers.EndpointConfiguration_Encoding_DefaultXml, Identifiers.HasDescription, Identifiers.OpcUa_XmlSchema_EndpointConfiguration.expanded(), true));
        node.addReference(new Reference(Identifiers.EndpointConfiguration_Encoding_DefaultXml, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode34() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.EndpointConfiguration_Encoding_DefaultBinary, new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.EndpointConfiguration_Encoding_DefaultBinary, Identifiers.HasEncoding, Identifiers.EndpointConfiguration.expanded(), false));
        node.addReference(new Reference(Identifiers.EndpointConfiguration_Encoding_DefaultBinary, Identifiers.HasDescription, Identifiers.OpcUa_BinarySchema_EndpointConfiguration.expanded(), true));
        node.addReference(new Reference(Identifiers.EndpointConfiguration_Encoding_DefaultBinary, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode35() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.BuildInfo_Encoding_DefaultXml, new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.BuildInfo_Encoding_DefaultXml, Identifiers.HasEncoding, Identifiers.BuildInfo.expanded(), false));
        node.addReference(new Reference(Identifiers.BuildInfo_Encoding_DefaultXml, Identifiers.HasDescription, Identifiers.OpcUa_XmlSchema_BuildInfo.expanded(), true));
        node.addReference(new Reference(Identifiers.BuildInfo_Encoding_DefaultXml, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode36() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.BuildInfo_Encoding_DefaultBinary, new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.BuildInfo_Encoding_DefaultBinary, Identifiers.HasEncoding, Identifiers.BuildInfo.expanded(), false));
        node.addReference(new Reference(Identifiers.BuildInfo_Encoding_DefaultBinary, Identifiers.HasDescription, Identifiers.OpcUa_BinarySchema_BuildInfo.expanded(), true));
        node.addReference(new Reference(Identifiers.BuildInfo_Encoding_DefaultBinary, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode37() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.SignedSoftwareCertificate_Encoding_DefaultXml, new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.SignedSoftwareCertificate_Encoding_DefaultXml, Identifiers.HasEncoding, Identifiers.SignedSoftwareCertificate.expanded(), false));
        node.addReference(new Reference(Identifiers.SignedSoftwareCertificate_Encoding_DefaultXml, Identifiers.HasDescription, Identifiers.OpcUa_XmlSchema_SignedSoftwareCertificate.expanded(), true));
        node.addReference(new Reference(Identifiers.SignedSoftwareCertificate_Encoding_DefaultXml, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode38() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.SignedSoftwareCertificate_Encoding_DefaultBinary, new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.SignedSoftwareCertificate_Encoding_DefaultBinary, Identifiers.HasEncoding, Identifiers.SignedSoftwareCertificate.expanded(), false));
        node.addReference(new Reference(Identifiers.SignedSoftwareCertificate_Encoding_DefaultBinary, Identifiers.HasDescription, Identifiers.OpcUa_BinarySchema_SignedSoftwareCertificate.expanded(), true));
        node.addReference(new Reference(Identifiers.SignedSoftwareCertificate_Encoding_DefaultBinary, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode39() {
        ServerConfigurationTypeNode node = new ServerConfigurationTypeNode(this.context, Identifiers.ServerConfiguration, new QualifiedName(0, "ServerConfiguration"), new LocalizedText("en", "ServerConfiguration"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.ServerConfiguration, Identifiers.HasComponent, Identifiers.ServerConfiguration_CertificateGroups.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration, Identifiers.HasProperty, Identifiers.ServerConfiguration_ServerCapabilities.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration, Identifiers.HasProperty, Identifiers.ServerConfiguration_SupportedPrivateKeyFormats.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration, Identifiers.HasProperty, Identifiers.ServerConfiguration_MaxTrustListSize.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration, Identifiers.HasProperty, Identifiers.ServerConfiguration_MulticastDnsEnabled.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration, Identifiers.HasComponent, Identifiers.ServerConfiguration_UpdateCertificate.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration, Identifiers.HasComponent, Identifiers.ServerConfiguration_ApplyChanges.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration, Identifiers.HasComponent, Identifiers.ServerConfiguration_CreateSigningRequest.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration, Identifiers.HasComponent, Identifiers.ServerConfiguration_GetRejectedList.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration, Identifiers.HasComponent, Identifiers.Server.expanded(), false));
        node.addReference(new Reference(Identifiers.ServerConfiguration, Identifiers.HasTypeDefinition, Identifiers.ServerConfigurationType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode40() {
        TrustListTypeNode node = new TrustListTypeNode(this.context, Identifiers.ServerConfiguration_CertificateGroups_DefaultApplicationGroup_TrustList, new QualifiedName(0, "TrustList"), new LocalizedText("en", "TrustList"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultApplicationGroup_TrustList, Identifiers.HasProperty, Identifiers.ServerConfiguration_CertificateGroups_DefaultApplicationGroup_TrustList_Size.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultApplicationGroup_TrustList, Identifiers.HasProperty, Identifiers.ServerConfiguration_CertificateGroups_DefaultApplicationGroup_TrustList_Writable.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultApplicationGroup_TrustList, Identifiers.HasProperty, Identifiers.ServerConfiguration_CertificateGroups_DefaultApplicationGroup_TrustList_UserWritable.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultApplicationGroup_TrustList, Identifiers.HasProperty, Identifiers.ServerConfiguration_CertificateGroups_DefaultApplicationGroup_TrustList_OpenCount.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultApplicationGroup_TrustList, Identifiers.HasComponent, Identifiers.ServerConfiguration_CertificateGroups_DefaultApplicationGroup_TrustList_Open.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultApplicationGroup_TrustList, Identifiers.HasComponent, Identifiers.ServerConfiguration_CertificateGroups_DefaultApplicationGroup_TrustList_Close.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultApplicationGroup_TrustList, Identifiers.HasComponent, Identifiers.ServerConfiguration_CertificateGroups_DefaultApplicationGroup_TrustList_Read.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultApplicationGroup_TrustList, Identifiers.HasComponent, Identifiers.ServerConfiguration_CertificateGroups_DefaultApplicationGroup_TrustList_Write.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultApplicationGroup_TrustList, Identifiers.HasComponent, Identifiers.ServerConfiguration_CertificateGroups_DefaultApplicationGroup_TrustList_GetPosition.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultApplicationGroup_TrustList, Identifiers.HasComponent, Identifiers.ServerConfiguration_CertificateGroups_DefaultApplicationGroup_TrustList_SetPosition.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultApplicationGroup_TrustList, Identifiers.HasProperty, Identifiers.ServerConfiguration_CertificateGroups_DefaultApplicationGroup_TrustList_LastUpdateTime.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultApplicationGroup_TrustList, Identifiers.HasComponent, Identifiers.ServerConfiguration_CertificateGroups_DefaultApplicationGroup_TrustList_OpenWithMasks.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultApplicationGroup_TrustList, Identifiers.HasComponent, Identifiers.ServerConfiguration_CertificateGroups_DefaultApplicationGroup_TrustList_CloseAndUpdate.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultApplicationGroup_TrustList, Identifiers.HasComponent, Identifiers.ServerConfiguration_CertificateGroups_DefaultApplicationGroup_TrustList_AddCertificate.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultApplicationGroup_TrustList, Identifiers.HasComponent, Identifiers.ServerConfiguration_CertificateGroups_DefaultApplicationGroup_TrustList_RemoveCertificate.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultApplicationGroup_TrustList, Identifiers.HasTypeDefinition, Identifiers.TrustListType.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultApplicationGroup_TrustList, Identifiers.HasComponent, Identifiers.ServerConfiguration_CertificateGroups_DefaultApplicationGroup.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode41() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.AddNodesItem_Encoding_DefaultXml, new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.AddNodesItem_Encoding_DefaultXml, Identifiers.HasEncoding, Identifiers.AddNodesItem.expanded(), false));
        node.addReference(new Reference(Identifiers.AddNodesItem_Encoding_DefaultXml, Identifiers.HasDescription, Identifiers.OpcUa_XmlSchema_AddNodesItem.expanded(), true));
        node.addReference(new Reference(Identifiers.AddNodesItem_Encoding_DefaultXml, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode42() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.AddNodesItem_Encoding_DefaultBinary, new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.AddNodesItem_Encoding_DefaultBinary, Identifiers.HasEncoding, Identifiers.AddNodesItem.expanded(), false));
        node.addReference(new Reference(Identifiers.AddNodesItem_Encoding_DefaultBinary, Identifiers.HasDescription, Identifiers.OpcUa_BinarySchema_AddNodesItem.expanded(), true));
        node.addReference(new Reference(Identifiers.AddNodesItem_Encoding_DefaultBinary, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode43() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.AddReferencesItem_Encoding_DefaultXml, new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.AddReferencesItem_Encoding_DefaultXml, Identifiers.HasEncoding, Identifiers.AddReferencesItem.expanded(), false));
        node.addReference(new Reference(Identifiers.AddReferencesItem_Encoding_DefaultXml, Identifiers.HasDescription, Identifiers.OpcUa_XmlSchema_AddReferencesItem.expanded(), true));
        node.addReference(new Reference(Identifiers.AddReferencesItem_Encoding_DefaultXml, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode44() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.AddReferencesItem_Encoding_DefaultBinary, new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.AddReferencesItem_Encoding_DefaultBinary, Identifiers.HasEncoding, Identifiers.AddReferencesItem.expanded(), false));
        node.addReference(new Reference(Identifiers.AddReferencesItem_Encoding_DefaultBinary, Identifiers.HasDescription, Identifiers.OpcUa_BinarySchema_AddReferencesItem.expanded(), true));
        node.addReference(new Reference(Identifiers.AddReferencesItem_Encoding_DefaultBinary, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode45() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.DeleteNodesItem_Encoding_DefaultXml, new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.DeleteNodesItem_Encoding_DefaultXml, Identifiers.HasEncoding, Identifiers.DeleteNodesItem.expanded(), false));
        node.addReference(new Reference(Identifiers.DeleteNodesItem_Encoding_DefaultXml, Identifiers.HasDescription, Identifiers.OpcUa_XmlSchema_DeleteNodesItem.expanded(), true));
        node.addReference(new Reference(Identifiers.DeleteNodesItem_Encoding_DefaultXml, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode46() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.DeleteNodesItem_Encoding_DefaultBinary, new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.DeleteNodesItem_Encoding_DefaultBinary, Identifiers.HasEncoding, Identifiers.DeleteNodesItem.expanded(), false));
        node.addReference(new Reference(Identifiers.DeleteNodesItem_Encoding_DefaultBinary, Identifiers.HasDescription, Identifiers.OpcUa_BinarySchema_DeleteNodesItem.expanded(), true));
        node.addReference(new Reference(Identifiers.DeleteNodesItem_Encoding_DefaultBinary, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode47() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.DeleteReferencesItem_Encoding_DefaultXml, new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.DeleteReferencesItem_Encoding_DefaultXml, Identifiers.HasEncoding, Identifiers.DeleteReferencesItem.expanded(), false));
        node.addReference(new Reference(Identifiers.DeleteReferencesItem_Encoding_DefaultXml, Identifiers.HasDescription, Identifiers.OpcUa_XmlSchema_DeleteReferencesItem.expanded(), true));
        node.addReference(new Reference(Identifiers.DeleteReferencesItem_Encoding_DefaultXml, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode48() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.DeleteReferencesItem_Encoding_DefaultBinary, new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.DeleteReferencesItem_Encoding_DefaultBinary, Identifiers.HasEncoding, Identifiers.DeleteReferencesItem.expanded(), false));
        node.addReference(new Reference(Identifiers.DeleteReferencesItem_Encoding_DefaultBinary, Identifiers.HasDescription, Identifiers.OpcUa_BinarySchema_DeleteReferencesItem.expanded(), true));
        node.addReference(new Reference(Identifiers.DeleteReferencesItem_Encoding_DefaultBinary, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode49() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.TrustListDataType_Encoding_DefaultXml, new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.TrustListDataType_Encoding_DefaultXml, Identifiers.HasEncoding, Identifiers.TrustListDataType.expanded(), false));
        node.addReference(new Reference(Identifiers.TrustListDataType_Encoding_DefaultXml, Identifiers.HasDescription, Identifiers.OpcUa_XmlSchema_TrustListDataType.expanded(), true));
        node.addReference(new Reference(Identifiers.TrustListDataType_Encoding_DefaultXml, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode50() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.TrustListDataType_Encoding_DefaultBinary, new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.TrustListDataType_Encoding_DefaultBinary, Identifiers.HasEncoding, Identifiers.TrustListDataType.expanded(), false));
        node.addReference(new Reference(Identifiers.TrustListDataType_Encoding_DefaultBinary, Identifiers.HasDescription, Identifiers.OpcUa_BinarySchema_TrustListDataType.expanded(), true));
        node.addReference(new Reference(Identifiers.TrustListDataType_Encoding_DefaultBinary, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode51() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.RegisteredServer_Encoding_DefaultXml, new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.RegisteredServer_Encoding_DefaultXml, Identifiers.HasEncoding, Identifiers.RegisteredServer.expanded(), false));
        node.addReference(new Reference(Identifiers.RegisteredServer_Encoding_DefaultXml, Identifiers.HasDescription, Identifiers.OpcUa_XmlSchema_RegisteredServer.expanded(), true));
        node.addReference(new Reference(Identifiers.RegisteredServer_Encoding_DefaultXml, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode52() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.RegisteredServer_Encoding_DefaultBinary, new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.RegisteredServer_Encoding_DefaultBinary, Identifiers.HasEncoding, Identifiers.RegisteredServer.expanded(), false));
        node.addReference(new Reference(Identifiers.RegisteredServer_Encoding_DefaultBinary, Identifiers.HasDescription, Identifiers.OpcUa_BinarySchema_RegisteredServer.expanded(), true));
        node.addReference(new Reference(Identifiers.RegisteredServer_Encoding_DefaultBinary, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode53() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.OptionSet_Encoding_DefaultXml, new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.OptionSet_Encoding_DefaultXml, Identifiers.HasEncoding, Identifiers.OptionSet.expanded(), false));
        node.addReference(new Reference(Identifiers.OptionSet_Encoding_DefaultXml, Identifiers.HasDescription, Identifiers.OpcUa_XmlSchema_OptionSet.expanded(), true));
        node.addReference(new Reference(Identifiers.OptionSet_Encoding_DefaultXml, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode54() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.Union_Encoding_DefaultXml, new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.Union_Encoding_DefaultXml, Identifiers.HasEncoding, Identifiers.Union.expanded(), false));
        node.addReference(new Reference(Identifiers.Union_Encoding_DefaultXml, Identifiers.HasDescription, Identifiers.OpcUa_XmlSchema_Union.expanded(), true));
        node.addReference(new Reference(Identifiers.Union_Encoding_DefaultXml, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode55() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.OptionSet_Encoding_DefaultBinary, new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.OptionSet_Encoding_DefaultBinary, Identifiers.HasEncoding, Identifiers.OptionSet.expanded(), false));
        node.addReference(new Reference(Identifiers.OptionSet_Encoding_DefaultBinary, Identifiers.HasDescription, Identifiers.OpcUa_BinarySchema_OptionSet.expanded(), true));
        node.addReference(new Reference(Identifiers.OptionSet_Encoding_DefaultBinary, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode56() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.Union_Encoding_DefaultBinary, new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.Union_Encoding_DefaultBinary, Identifiers.HasEncoding, Identifiers.Union.expanded(), false));
        node.addReference(new Reference(Identifiers.Union_Encoding_DefaultBinary, Identifiers.HasDescription, Identifiers.OpcUa_BinarySchema_Union.expanded(), true));
        node.addReference(new Reference(Identifiers.Union_Encoding_DefaultBinary, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode57() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.RelativePathElement_Encoding_DefaultXml, new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.RelativePathElement_Encoding_DefaultXml, Identifiers.HasEncoding, Identifiers.RelativePathElement.expanded(), false));
        node.addReference(new Reference(Identifiers.RelativePathElement_Encoding_DefaultXml, Identifiers.HasDescription, Identifiers.OpcUa_XmlSchema_RelativePathElement.expanded(), true));
        node.addReference(new Reference(Identifiers.RelativePathElement_Encoding_DefaultXml, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode58() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.RelativePathElement_Encoding_DefaultBinary, new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.RelativePathElement_Encoding_DefaultBinary, Identifiers.HasEncoding, Identifiers.RelativePathElement.expanded(), false));
        node.addReference(new Reference(Identifiers.RelativePathElement_Encoding_DefaultBinary, Identifiers.HasDescription, Identifiers.OpcUa_BinarySchema_RelativePathElement.expanded(), true));
        node.addReference(new Reference(Identifiers.RelativePathElement_Encoding_DefaultBinary, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode59() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.RelativePath_Encoding_DefaultXml, new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.RelativePath_Encoding_DefaultXml, Identifiers.HasEncoding, Identifiers.RelativePath.expanded(), false));
        node.addReference(new Reference(Identifiers.RelativePath_Encoding_DefaultXml, Identifiers.HasDescription, Identifiers.OpcUa_XmlSchema_RelativePath.expanded(), true));
        node.addReference(new Reference(Identifiers.RelativePath_Encoding_DefaultXml, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode60() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.RelativePath_Encoding_DefaultBinary, new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.RelativePath_Encoding_DefaultBinary, Identifiers.HasEncoding, Identifiers.RelativePath.expanded(), false));
        node.addReference(new Reference(Identifiers.RelativePath_Encoding_DefaultBinary, Identifiers.HasDescription, Identifiers.OpcUa_BinarySchema_RelativePath.expanded(), true));
        node.addReference(new Reference(Identifiers.RelativePath_Encoding_DefaultBinary, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode61() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.ContentFilterElement_Encoding_DefaultXml, new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.ContentFilterElement_Encoding_DefaultXml, Identifiers.HasEncoding, Identifiers.ContentFilterElement.expanded(), false));
        node.addReference(new Reference(Identifiers.ContentFilterElement_Encoding_DefaultXml, Identifiers.HasDescription, Identifiers.OpcUa_XmlSchema_ContentFilterElement.expanded(), true));
        node.addReference(new Reference(Identifiers.ContentFilterElement_Encoding_DefaultXml, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode62() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.ContentFilterElement_Encoding_DefaultBinary, new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.ContentFilterElement_Encoding_DefaultBinary, Identifiers.HasEncoding, Identifiers.ContentFilterElement.expanded(), false));
        node.addReference(new Reference(Identifiers.ContentFilterElement_Encoding_DefaultBinary, Identifiers.HasDescription, Identifiers.OpcUa_BinarySchema_ContentFilterElement.expanded(), true));
        node.addReference(new Reference(Identifiers.ContentFilterElement_Encoding_DefaultBinary, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode63() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.ContentFilter_Encoding_DefaultXml, new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.ContentFilter_Encoding_DefaultXml, Identifiers.HasEncoding, Identifiers.ContentFilter.expanded(), false));
        node.addReference(new Reference(Identifiers.ContentFilter_Encoding_DefaultXml, Identifiers.HasDescription, Identifiers.OpcUa_XmlSchema_ContentFilter.expanded(), true));
        node.addReference(new Reference(Identifiers.ContentFilter_Encoding_DefaultXml, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode64() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.ContentFilter_Encoding_DefaultBinary, new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.ContentFilter_Encoding_DefaultBinary, Identifiers.HasEncoding, Identifiers.ContentFilter.expanded(), false));
        node.addReference(new Reference(Identifiers.ContentFilter_Encoding_DefaultBinary, Identifiers.HasDescription, Identifiers.OpcUa_BinarySchema_ContentFilter.expanded(), true));
        node.addReference(new Reference(Identifiers.ContentFilter_Encoding_DefaultBinary, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode65() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.FilterOperand_Encoding_DefaultXml, new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.FilterOperand_Encoding_DefaultXml, Identifiers.HasEncoding, Identifiers.FilterOperand.expanded(), false));
        node.addReference(new Reference(Identifiers.FilterOperand_Encoding_DefaultXml, Identifiers.HasDescription, Identifiers.OpcUa_XmlSchema_FilterOperand.expanded(), true));
        node.addReference(new Reference(Identifiers.FilterOperand_Encoding_DefaultXml, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode66() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.FilterOperand_Encoding_DefaultBinary, new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.FilterOperand_Encoding_DefaultBinary, Identifiers.HasEncoding, Identifiers.FilterOperand.expanded(), false));
        node.addReference(new Reference(Identifiers.FilterOperand_Encoding_DefaultBinary, Identifiers.HasDescription, Identifiers.OpcUa_BinarySchema_FilterOperand.expanded(), true));
        node.addReference(new Reference(Identifiers.FilterOperand_Encoding_DefaultBinary, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode67() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.ElementOperand_Encoding_DefaultXml, new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.ElementOperand_Encoding_DefaultXml, Identifiers.HasEncoding, Identifiers.ElementOperand.expanded(), false));
        node.addReference(new Reference(Identifiers.ElementOperand_Encoding_DefaultXml, Identifiers.HasDescription, Identifiers.OpcUa_XmlSchema_ElementOperand.expanded(), true));
        node.addReference(new Reference(Identifiers.ElementOperand_Encoding_DefaultXml, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode68() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.ElementOperand_Encoding_DefaultBinary, new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.ElementOperand_Encoding_DefaultBinary, Identifiers.HasEncoding, Identifiers.ElementOperand.expanded(), false));
        node.addReference(new Reference(Identifiers.ElementOperand_Encoding_DefaultBinary, Identifiers.HasDescription, Identifiers.OpcUa_BinarySchema_ElementOperand.expanded(), true));
        node.addReference(new Reference(Identifiers.ElementOperand_Encoding_DefaultBinary, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode69() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.LiteralOperand_Encoding_DefaultXml, new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.LiteralOperand_Encoding_DefaultXml, Identifiers.HasEncoding, Identifiers.LiteralOperand.expanded(), false));
        node.addReference(new Reference(Identifiers.LiteralOperand_Encoding_DefaultXml, Identifiers.HasDescription, Identifiers.OpcUa_XmlSchema_LiteralOperand.expanded(), true));
        node.addReference(new Reference(Identifiers.LiteralOperand_Encoding_DefaultXml, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode70() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.LiteralOperand_Encoding_DefaultBinary, new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.LiteralOperand_Encoding_DefaultBinary, Identifiers.HasEncoding, Identifiers.LiteralOperand.expanded(), false));
        node.addReference(new Reference(Identifiers.LiteralOperand_Encoding_DefaultBinary, Identifiers.HasDescription, Identifiers.OpcUa_BinarySchema_LiteralOperand.expanded(), true));
        node.addReference(new Reference(Identifiers.LiteralOperand_Encoding_DefaultBinary, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode71() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.AttributeOperand_Encoding_DefaultXml, new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.AttributeOperand_Encoding_DefaultXml, Identifiers.HasEncoding, Identifiers.AttributeOperand.expanded(), false));
        node.addReference(new Reference(Identifiers.AttributeOperand_Encoding_DefaultXml, Identifiers.HasDescription, Identifiers.OpcUa_XmlSchema_AttributeOperand.expanded(), true));
        node.addReference(new Reference(Identifiers.AttributeOperand_Encoding_DefaultXml, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode72() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.AttributeOperand_Encoding_DefaultBinary, new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.AttributeOperand_Encoding_DefaultBinary, Identifiers.HasEncoding, Identifiers.AttributeOperand.expanded(), false));
        node.addReference(new Reference(Identifiers.AttributeOperand_Encoding_DefaultBinary, Identifiers.HasDescription, Identifiers.OpcUa_BinarySchema_AttributeOperand.expanded(), true));
        node.addReference(new Reference(Identifiers.AttributeOperand_Encoding_DefaultBinary, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode73() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.SimpleAttributeOperand_Encoding_DefaultXml, new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.SimpleAttributeOperand_Encoding_DefaultXml, Identifiers.HasEncoding, Identifiers.SimpleAttributeOperand.expanded(), false));
        node.addReference(new Reference(Identifiers.SimpleAttributeOperand_Encoding_DefaultXml, Identifiers.HasDescription, Identifiers.OpcUa_XmlSchema_SimpleAttributeOperand.expanded(), true));
        node.addReference(new Reference(Identifiers.SimpleAttributeOperand_Encoding_DefaultXml, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode74() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.SimpleAttributeOperand_Encoding_DefaultBinary, new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.SimpleAttributeOperand_Encoding_DefaultBinary, Identifiers.HasEncoding, Identifiers.SimpleAttributeOperand.expanded(), false));
        node.addReference(new Reference(Identifiers.SimpleAttributeOperand_Encoding_DefaultBinary, Identifiers.HasDescription, Identifiers.OpcUa_BinarySchema_SimpleAttributeOperand.expanded(), true));
        node.addReference(new Reference(Identifiers.SimpleAttributeOperand_Encoding_DefaultBinary, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode75() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.DiscoveryConfiguration_Encoding_DefaultXml, new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.DiscoveryConfiguration_Encoding_DefaultXml, Identifiers.HasEncoding, Identifiers.DiscoveryConfiguration.expanded(), false));
        node.addReference(new Reference(Identifiers.DiscoveryConfiguration_Encoding_DefaultXml, Identifiers.HasDescription, Identifiers.OpcUa_XmlSchema_DiscoveryConfiguration.expanded(), true));
        node.addReference(new Reference(Identifiers.DiscoveryConfiguration_Encoding_DefaultXml, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode76() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.MdnsDiscoveryConfiguration_Encoding_DefaultXml, new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.MdnsDiscoveryConfiguration_Encoding_DefaultXml, Identifiers.HasEncoding, Identifiers.MdnsDiscoveryConfiguration.expanded(), false));
        node.addReference(new Reference(Identifiers.MdnsDiscoveryConfiguration_Encoding_DefaultXml, Identifiers.HasDescription, Identifiers.OpcUa_XmlSchema_MdnsDiscoveryConfiguration.expanded(), true));
        node.addReference(new Reference(Identifiers.MdnsDiscoveryConfiguration_Encoding_DefaultXml, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode77() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.DiscoveryConfiguration_Encoding_DefaultBinary, new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.DiscoveryConfiguration_Encoding_DefaultBinary, Identifiers.HasEncoding, Identifiers.DiscoveryConfiguration.expanded(), false));
        node.addReference(new Reference(Identifiers.DiscoveryConfiguration_Encoding_DefaultBinary, Identifiers.HasDescription, Identifiers.OpcUa_BinarySchema_DiscoveryConfiguration.expanded(), true));
        node.addReference(new Reference(Identifiers.DiscoveryConfiguration_Encoding_DefaultBinary, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode78() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.MdnsDiscoveryConfiguration_Encoding_DefaultBinary, new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.MdnsDiscoveryConfiguration_Encoding_DefaultBinary, Identifiers.HasEncoding, Identifiers.MdnsDiscoveryConfiguration.expanded(), false));
        node.addReference(new Reference(Identifiers.MdnsDiscoveryConfiguration_Encoding_DefaultBinary, Identifiers.HasDescription, Identifiers.OpcUa_BinarySchema_MdnsDiscoveryConfiguration.expanded(), true));
        node.addReference(new Reference(Identifiers.MdnsDiscoveryConfiguration_Encoding_DefaultBinary, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode79() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.HistoryEvent_Encoding_DefaultXml, new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.HistoryEvent_Encoding_DefaultXml, Identifiers.HasEncoding, Identifiers.HistoryEvent.expanded(), false));
        node.addReference(new Reference(Identifiers.HistoryEvent_Encoding_DefaultXml, Identifiers.HasDescription, Identifiers.OpcUa_XmlSchema_HistoryEvent.expanded(), true));
        node.addReference(new Reference(Identifiers.HistoryEvent_Encoding_DefaultXml, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode80() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.HistoryEvent_Encoding_DefaultBinary, new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.HistoryEvent_Encoding_DefaultBinary, Identifiers.HasEncoding, Identifiers.HistoryEvent.expanded(), false));
        node.addReference(new Reference(Identifiers.HistoryEvent_Encoding_DefaultBinary, Identifiers.HasDescription, Identifiers.OpcUa_BinarySchema_HistoryEvent.expanded(), true));
        node.addReference(new Reference(Identifiers.HistoryEvent_Encoding_DefaultBinary, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode81() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.MonitoringFilter_Encoding_DefaultXml, new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.MonitoringFilter_Encoding_DefaultXml, Identifiers.HasEncoding, Identifiers.MonitoringFilter.expanded(), false));
        node.addReference(new Reference(Identifiers.MonitoringFilter_Encoding_DefaultXml, Identifiers.HasDescription, Identifiers.OpcUa_XmlSchema_MonitoringFilter.expanded(), true));
        node.addReference(new Reference(Identifiers.MonitoringFilter_Encoding_DefaultXml, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode82() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.TimeZoneDataType_Encoding_DefaultXml, new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.TimeZoneDataType_Encoding_DefaultXml, Identifiers.HasEncoding, Identifiers.TimeZoneDataType.expanded(), false));
        node.addReference(new Reference(Identifiers.TimeZoneDataType_Encoding_DefaultXml, Identifiers.HasDescription, Identifiers.OpcUa_XmlSchema_TimeZoneDataType.expanded(), true));
        node.addReference(new Reference(Identifiers.TimeZoneDataType_Encoding_DefaultXml, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode83() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.MonitoringFilter_Encoding_DefaultBinary, new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.MonitoringFilter_Encoding_DefaultBinary, Identifiers.HasEncoding, Identifiers.MonitoringFilter.expanded(), false));
        node.addReference(new Reference(Identifiers.MonitoringFilter_Encoding_DefaultBinary, Identifiers.HasDescription, Identifiers.OpcUa_BinarySchema_MonitoringFilter.expanded(), true));
        node.addReference(new Reference(Identifiers.MonitoringFilter_Encoding_DefaultBinary, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode84() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.TimeZoneDataType_Encoding_DefaultBinary, new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.TimeZoneDataType_Encoding_DefaultBinary, Identifiers.HasEncoding, Identifiers.TimeZoneDataType.expanded(), false));
        node.addReference(new Reference(Identifiers.TimeZoneDataType_Encoding_DefaultBinary, Identifiers.HasDescription, Identifiers.OpcUa_BinarySchema_TimeZoneDataType.expanded(), true));
        node.addReference(new Reference(Identifiers.TimeZoneDataType_Encoding_DefaultBinary, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode85() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.EventFilter_Encoding_DefaultXml, new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.EventFilter_Encoding_DefaultXml, Identifiers.HasEncoding, Identifiers.EventFilter.expanded(), false));
        node.addReference(new Reference(Identifiers.EventFilter_Encoding_DefaultXml, Identifiers.HasDescription, Identifiers.OpcUa_XmlSchema_EventFilter.expanded(), true));
        node.addReference(new Reference(Identifiers.EventFilter_Encoding_DefaultXml, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode86() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.EventFilter_Encoding_DefaultBinary, new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.EventFilter_Encoding_DefaultBinary, Identifiers.HasEncoding, Identifiers.EventFilter.expanded(), false));
        node.addReference(new Reference(Identifiers.EventFilter_Encoding_DefaultBinary, Identifiers.HasDescription, Identifiers.OpcUa_BinarySchema_EventFilter.expanded(), true));
        node.addReference(new Reference(Identifiers.EventFilter_Encoding_DefaultBinary, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode87() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.RedundantServerDataType_Encoding_DefaultXml, new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.RedundantServerDataType_Encoding_DefaultXml, Identifiers.HasEncoding, Identifiers.RedundantServerDataType.expanded(), false));
        node.addReference(new Reference(Identifiers.RedundantServerDataType_Encoding_DefaultXml, Identifiers.HasDescription, Identifiers.OpcUa_XmlSchema_RedundantServerDataType.expanded(), true));
        node.addReference(new Reference(Identifiers.RedundantServerDataType_Encoding_DefaultXml, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode88() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.RedundantServerDataType_Encoding_DefaultBinary, new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.RedundantServerDataType_Encoding_DefaultBinary, Identifiers.HasEncoding, Identifiers.RedundantServerDataType.expanded(), false));
        node.addReference(new Reference(Identifiers.RedundantServerDataType_Encoding_DefaultBinary, Identifiers.HasDescription, Identifiers.OpcUa_BinarySchema_RedundantServerDataType.expanded(), true));
        node.addReference(new Reference(Identifiers.RedundantServerDataType_Encoding_DefaultBinary, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode89() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.SamplingIntervalDiagnosticsDataType_Encoding_DefaultXml, new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.SamplingIntervalDiagnosticsDataType_Encoding_DefaultXml, Identifiers.HasEncoding, Identifiers.SamplingIntervalDiagnosticsDataType.expanded(), false));
        node.addReference(new Reference(Identifiers.SamplingIntervalDiagnosticsDataType_Encoding_DefaultXml, Identifiers.HasDescription, Identifiers.OpcUa_XmlSchema_SamplingIntervalDiagnosticsDataType.expanded(), true));
        node.addReference(new Reference(Identifiers.SamplingIntervalDiagnosticsDataType_Encoding_DefaultXml, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode90() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.SamplingIntervalDiagnosticsDataType_Encoding_DefaultBinary, new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.SamplingIntervalDiagnosticsDataType_Encoding_DefaultBinary, Identifiers.HasEncoding, Identifiers.SamplingIntervalDiagnosticsDataType.expanded(), false));
        node.addReference(new Reference(Identifiers.SamplingIntervalDiagnosticsDataType_Encoding_DefaultBinary, Identifiers.HasDescription, Identifiers.OpcUa_BinarySchema_SamplingIntervalDiagnosticsDataType.expanded(), true));
        node.addReference(new Reference(Identifiers.SamplingIntervalDiagnosticsDataType_Encoding_DefaultBinary, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode91() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.ServerDiagnosticsSummaryDataType_Encoding_DefaultXml, new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.ServerDiagnosticsSummaryDataType_Encoding_DefaultXml, Identifiers.HasEncoding, Identifiers.ServerDiagnosticsSummaryDataType.expanded(), false));
        node.addReference(new Reference(Identifiers.ServerDiagnosticsSummaryDataType_Encoding_DefaultXml, Identifiers.HasDescription, Identifiers.OpcUa_XmlSchema_ServerDiagnosticsSummaryDataType.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerDiagnosticsSummaryDataType_Encoding_DefaultXml, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode92() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.ServerDiagnosticsSummaryDataType_Encoding_DefaultBinary, new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.ServerDiagnosticsSummaryDataType_Encoding_DefaultBinary, Identifiers.HasEncoding, Identifiers.ServerDiagnosticsSummaryDataType.expanded(), false));
        node.addReference(new Reference(Identifiers.ServerDiagnosticsSummaryDataType_Encoding_DefaultBinary, Identifiers.HasDescription, Identifiers.OpcUa_BinarySchema_ServerDiagnosticsSummaryDataType.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerDiagnosticsSummaryDataType_Encoding_DefaultBinary, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode93() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.ServerStatusDataType_Encoding_DefaultXml, new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.ServerStatusDataType_Encoding_DefaultXml, Identifiers.HasEncoding, Identifiers.ServerStatusDataType.expanded(), false));
        node.addReference(new Reference(Identifiers.ServerStatusDataType_Encoding_DefaultXml, Identifiers.HasDescription, Identifiers.OpcUa_XmlSchema_ServerStatusDataType.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerStatusDataType_Encoding_DefaultXml, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode94() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.ServerStatusDataType_Encoding_DefaultBinary, new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.ServerStatusDataType_Encoding_DefaultBinary, Identifiers.HasEncoding, Identifiers.ServerStatusDataType.expanded(), false));
        node.addReference(new Reference(Identifiers.ServerStatusDataType_Encoding_DefaultBinary, Identifiers.HasDescription, Identifiers.OpcUa_BinarySchema_ServerStatusDataType.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerStatusDataType_Encoding_DefaultBinary, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode95() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.SessionDiagnosticsDataType_Encoding_DefaultXml, new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.SessionDiagnosticsDataType_Encoding_DefaultXml, Identifiers.HasEncoding, Identifiers.SessionDiagnosticsDataType.expanded(), false));
        node.addReference(new Reference(Identifiers.SessionDiagnosticsDataType_Encoding_DefaultXml, Identifiers.HasDescription, Identifiers.OpcUa_XmlSchema_SessionDiagnosticsDataType.expanded(), true));
        node.addReference(new Reference(Identifiers.SessionDiagnosticsDataType_Encoding_DefaultXml, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode96() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.SessionDiagnosticsDataType_Encoding_DefaultBinary, new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.SessionDiagnosticsDataType_Encoding_DefaultBinary, Identifiers.HasEncoding, Identifiers.SessionDiagnosticsDataType.expanded(), false));
        node.addReference(new Reference(Identifiers.SessionDiagnosticsDataType_Encoding_DefaultBinary, Identifiers.HasDescription, Identifiers.OpcUa_BinarySchema_SessionDiagnosticsDataType.expanded(), true));
        node.addReference(new Reference(Identifiers.SessionDiagnosticsDataType_Encoding_DefaultBinary, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode97() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.SessionSecurityDiagnosticsDataType_Encoding_DefaultXml, new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.SessionSecurityDiagnosticsDataType_Encoding_DefaultXml, Identifiers.HasEncoding, Identifiers.SessionSecurityDiagnosticsDataType.expanded(), false));
        node.addReference(new Reference(Identifiers.SessionSecurityDiagnosticsDataType_Encoding_DefaultXml, Identifiers.HasDescription, Identifiers.OpcUa_XmlSchema_SessionSecurityDiagnosticsDataType.expanded(), true));
        node.addReference(new Reference(Identifiers.SessionSecurityDiagnosticsDataType_Encoding_DefaultXml, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode98() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.SessionSecurityDiagnosticsDataType_Encoding_DefaultBinary, new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.SessionSecurityDiagnosticsDataType_Encoding_DefaultBinary, Identifiers.HasEncoding, Identifiers.SessionSecurityDiagnosticsDataType.expanded(), false));
        node.addReference(new Reference(Identifiers.SessionSecurityDiagnosticsDataType_Encoding_DefaultBinary, Identifiers.HasDescription, Identifiers.OpcUa_BinarySchema_SessionSecurityDiagnosticsDataType.expanded(), true));
        node.addReference(new Reference(Identifiers.SessionSecurityDiagnosticsDataType_Encoding_DefaultBinary, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode99() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.ServiceCounterDataType_Encoding_DefaultXml, new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.ServiceCounterDataType_Encoding_DefaultXml, Identifiers.HasEncoding, Identifiers.ServiceCounterDataType.expanded(), false));
        node.addReference(new Reference(Identifiers.ServiceCounterDataType_Encoding_DefaultXml, Identifiers.HasDescription, Identifiers.OpcUa_XmlSchema_ServiceCounterDataType.expanded(), true));
        node.addReference(new Reference(Identifiers.ServiceCounterDataType_Encoding_DefaultXml, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode100() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.ServiceCounterDataType_Encoding_DefaultBinary, new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.ServiceCounterDataType_Encoding_DefaultBinary, Identifiers.HasEncoding, Identifiers.ServiceCounterDataType.expanded(), false));
        node.addReference(new Reference(Identifiers.ServiceCounterDataType_Encoding_DefaultBinary, Identifiers.HasDescription, Identifiers.OpcUa_BinarySchema_ServiceCounterDataType.expanded(), true));
        node.addReference(new Reference(Identifiers.ServiceCounterDataType_Encoding_DefaultBinary, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode101() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.SubscriptionDiagnosticsDataType_Encoding_DefaultXml, new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.SubscriptionDiagnosticsDataType_Encoding_DefaultXml, Identifiers.HasEncoding, Identifiers.SubscriptionDiagnosticsDataType.expanded(), false));
        node.addReference(new Reference(Identifiers.SubscriptionDiagnosticsDataType_Encoding_DefaultXml, Identifiers.HasDescription, Identifiers.OpcUa_XmlSchema_SubscriptionDiagnosticsDataType.expanded(), true));
        node.addReference(new Reference(Identifiers.SubscriptionDiagnosticsDataType_Encoding_DefaultXml, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode102() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.SubscriptionDiagnosticsDataType_Encoding_DefaultBinary, new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.SubscriptionDiagnosticsDataType_Encoding_DefaultBinary, Identifiers.HasEncoding, Identifiers.SubscriptionDiagnosticsDataType.expanded(), false));
        node.addReference(new Reference(Identifiers.SubscriptionDiagnosticsDataType_Encoding_DefaultBinary, Identifiers.HasDescription, Identifiers.OpcUa_BinarySchema_SubscriptionDiagnosticsDataType.expanded(), true));
        node.addReference(new Reference(Identifiers.SubscriptionDiagnosticsDataType_Encoding_DefaultBinary, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode103() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.ModelChangeStructureDataType_Encoding_DefaultXml, new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.ModelChangeStructureDataType_Encoding_DefaultXml, Identifiers.HasEncoding, Identifiers.ModelChangeStructureDataType.expanded(), false));
        node.addReference(new Reference(Identifiers.ModelChangeStructureDataType_Encoding_DefaultXml, Identifiers.HasDescription, Identifiers.OpcUa_XmlSchema_ModelChangeStructureDataType.expanded(), true));
        node.addReference(new Reference(Identifiers.ModelChangeStructureDataType_Encoding_DefaultXml, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode104() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.ModelChangeStructureDataType_Encoding_DefaultBinary, new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.ModelChangeStructureDataType_Encoding_DefaultBinary, Identifiers.HasEncoding, Identifiers.ModelChangeStructureDataType.expanded(), false));
        node.addReference(new Reference(Identifiers.ModelChangeStructureDataType_Encoding_DefaultBinary, Identifiers.HasDescription, Identifiers.OpcUa_BinarySchema_ModelChangeStructureDataType.expanded(), true));
        node.addReference(new Reference(Identifiers.ModelChangeStructureDataType_Encoding_DefaultBinary, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode105() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.Range_Encoding_DefaultXml, new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.Range_Encoding_DefaultXml, Identifiers.HasEncoding, Identifiers.Range.expanded(), false));
        node.addReference(new Reference(Identifiers.Range_Encoding_DefaultXml, Identifiers.HasDescription, Identifiers.OpcUa_XmlSchema_Range.expanded(), true));
        node.addReference(new Reference(Identifiers.Range_Encoding_DefaultXml, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode106() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.Range_Encoding_DefaultBinary, new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.Range_Encoding_DefaultBinary, Identifiers.HasEncoding, Identifiers.Range.expanded(), false));
        node.addReference(new Reference(Identifiers.Range_Encoding_DefaultBinary, Identifiers.HasDescription, Identifiers.OpcUa_BinarySchema_Range.expanded(), true));
        node.addReference(new Reference(Identifiers.Range_Encoding_DefaultBinary, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode107() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.EUInformation_Encoding_DefaultXml, new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.EUInformation_Encoding_DefaultXml, Identifiers.HasEncoding, Identifiers.EUInformation.expanded(), false));
        node.addReference(new Reference(Identifiers.EUInformation_Encoding_DefaultXml, Identifiers.HasDescription, Identifiers.OpcUa_XmlSchema_EUInformation.expanded(), true));
        node.addReference(new Reference(Identifiers.EUInformation_Encoding_DefaultXml, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode108() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.EUInformation_Encoding_DefaultBinary, new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.EUInformation_Encoding_DefaultBinary, Identifiers.HasEncoding, Identifiers.EUInformation.expanded(), false));
        node.addReference(new Reference(Identifiers.EUInformation_Encoding_DefaultBinary, Identifiers.HasDescription, Identifiers.OpcUa_BinarySchema_EUInformation.expanded(), true));
        node.addReference(new Reference(Identifiers.EUInformation_Encoding_DefaultBinary, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode109() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.Annotation_Encoding_DefaultXml, new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.Annotation_Encoding_DefaultXml, Identifiers.HasEncoding, Identifiers.Annotation.expanded(), false));
        node.addReference(new Reference(Identifiers.Annotation_Encoding_DefaultXml, Identifiers.HasDescription, Identifiers.OpcUa_XmlSchema_Annotation.expanded(), true));
        node.addReference(new Reference(Identifiers.Annotation_Encoding_DefaultXml, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode110() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.Annotation_Encoding_DefaultBinary, new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.Annotation_Encoding_DefaultBinary, Identifiers.HasEncoding, Identifiers.Annotation.expanded(), false));
        node.addReference(new Reference(Identifiers.Annotation_Encoding_DefaultBinary, Identifiers.HasDescription, Identifiers.OpcUa_BinarySchema_Annotation.expanded(), true));
        node.addReference(new Reference(Identifiers.Annotation_Encoding_DefaultBinary, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode111() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.ProgramDiagnosticDataType_Encoding_DefaultXml, new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.ProgramDiagnosticDataType_Encoding_DefaultXml, Identifiers.HasEncoding, Identifiers.ProgramDiagnosticDataType.expanded(), false));
        node.addReference(new Reference(Identifiers.ProgramDiagnosticDataType_Encoding_DefaultXml, Identifiers.HasDescription, Identifiers.OpcUa_XmlSchema_ProgramDiagnosticDataType.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramDiagnosticDataType_Encoding_DefaultXml, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode112() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.ProgramDiagnosticDataType_Encoding_DefaultBinary, new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.ProgramDiagnosticDataType_Encoding_DefaultBinary, Identifiers.HasEncoding, Identifiers.ProgramDiagnosticDataType.expanded(), false));
        node.addReference(new Reference(Identifiers.ProgramDiagnosticDataType_Encoding_DefaultBinary, Identifiers.HasDescription, Identifiers.OpcUa_BinarySchema_ProgramDiagnosticDataType.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramDiagnosticDataType_Encoding_DefaultBinary, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode113() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.SemanticChangeStructureDataType_Encoding_DefaultXml, new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.SemanticChangeStructureDataType_Encoding_DefaultXml, Identifiers.HasEncoding, Identifiers.SemanticChangeStructureDataType.expanded(), false));
        node.addReference(new Reference(Identifiers.SemanticChangeStructureDataType_Encoding_DefaultXml, Identifiers.HasDescription, Identifiers.OpcUa_XmlSchema_SemanticChangeStructureDataType.expanded(), true));
        node.addReference(new Reference(Identifiers.SemanticChangeStructureDataType_Encoding_DefaultXml, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode114() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.SemanticChangeStructureDataType_Encoding_DefaultBinary, new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.SemanticChangeStructureDataType_Encoding_DefaultBinary, Identifiers.HasEncoding, Identifiers.SemanticChangeStructureDataType.expanded(), false));
        node.addReference(new Reference(Identifiers.SemanticChangeStructureDataType_Encoding_DefaultBinary, Identifiers.HasDescription, Identifiers.OpcUa_BinarySchema_SemanticChangeStructureDataType.expanded(), true));
        node.addReference(new Reference(Identifiers.SemanticChangeStructureDataType_Encoding_DefaultBinary, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode115() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.HistoryEventFieldList_Encoding_DefaultXml, new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.HistoryEventFieldList_Encoding_DefaultXml, Identifiers.HasEncoding, Identifiers.HistoryEventFieldList.expanded(), false));
        node.addReference(new Reference(Identifiers.HistoryEventFieldList_Encoding_DefaultXml, Identifiers.HasDescription, Identifiers.OpcUa_XmlSchema_HistoryEventFieldList.expanded(), true));
        node.addReference(new Reference(Identifiers.HistoryEventFieldList_Encoding_DefaultXml, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode116() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.HistoryEventFieldList_Encoding_DefaultBinary, new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.HistoryEventFieldList_Encoding_DefaultBinary, Identifiers.HasEncoding, Identifiers.HistoryEventFieldList.expanded(), false));
        node.addReference(new Reference(Identifiers.HistoryEventFieldList_Encoding_DefaultBinary, Identifiers.HasDescription, Identifiers.OpcUa_BinarySchema_HistoryEventFieldList.expanded(), true));
        node.addReference(new Reference(Identifiers.HistoryEventFieldList_Encoding_DefaultBinary, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode117() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.IssuedIdentityToken_Encoding_DefaultXml, new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.IssuedIdentityToken_Encoding_DefaultXml, Identifiers.HasEncoding, Identifiers.IssuedIdentityToken.expanded(), false));
        node.addReference(new Reference(Identifiers.IssuedIdentityToken_Encoding_DefaultXml, Identifiers.HasDescription, Identifiers.OpcUa_XmlSchema_IssuedIdentityToken.expanded(), true));
        node.addReference(new Reference(Identifiers.IssuedIdentityToken_Encoding_DefaultXml, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode118() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.IssuedIdentityToken_Encoding_DefaultBinary, new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.IssuedIdentityToken_Encoding_DefaultBinary, Identifiers.HasEncoding, Identifiers.IssuedIdentityToken.expanded(), false));
        node.addReference(new Reference(Identifiers.IssuedIdentityToken_Encoding_DefaultBinary, Identifiers.HasDescription, Identifiers.OpcUa_BinarySchema_IssuedIdentityToken.expanded(), true));
        node.addReference(new Reference(Identifiers.IssuedIdentityToken_Encoding_DefaultBinary, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode119() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.AggregateConfiguration_Encoding_DefaultXml, new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.AggregateConfiguration_Encoding_DefaultXml, Identifiers.HasEncoding, Identifiers.AggregateConfiguration.expanded(), false));
        node.addReference(new Reference(Identifiers.AggregateConfiguration_Encoding_DefaultXml, Identifiers.HasDescription, Identifiers.OpcUa_XmlSchema_AggregateConfiguration.expanded(), true));
        node.addReference(new Reference(Identifiers.AggregateConfiguration_Encoding_DefaultXml, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode120() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.AggregateConfiguration_Encoding_DefaultBinary, new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.AggregateConfiguration_Encoding_DefaultBinary, Identifiers.HasEncoding, Identifiers.AggregateConfiguration.expanded(), false));
        node.addReference(new Reference(Identifiers.AggregateConfiguration_Encoding_DefaultBinary, Identifiers.HasDescription, Identifiers.OpcUa_BinarySchema_AggregateConfiguration.expanded(), true));
        node.addReference(new Reference(Identifiers.AggregateConfiguration_Encoding_DefaultBinary, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode121() {
        ShelvedStateMachineTypeNode node = new ShelvedStateMachineTypeNode(this.context, Identifiers.AlarmConditionType_ShelvingState, new QualifiedName(0, "ShelvingState"), new LocalizedText("en", "ShelvingState"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.AlarmConditionType_ShelvingState, Identifiers.HasComponent, Identifiers.AlarmConditionType_ShelvingState_CurrentState.expanded(), true));
        node.addReference(new Reference(Identifiers.AlarmConditionType_ShelvingState, Identifiers.HasComponent, Identifiers.AlarmConditionType_ShelvingState_LastTransition.expanded(), true));
        node.addReference(new Reference(Identifiers.AlarmConditionType_ShelvingState, Identifiers.HasProperty, Identifiers.AlarmConditionType_ShelvingState_UnshelveTime.expanded(), true));
        node.addReference(new Reference(Identifiers.AlarmConditionType_ShelvingState, Identifiers.HasComponent, Identifiers.AlarmConditionType_ShelvingState_Unshelve.expanded(), true));
        node.addReference(new Reference(Identifiers.AlarmConditionType_ShelvingState, Identifiers.HasComponent, Identifiers.AlarmConditionType_ShelvingState_OneShotShelve.expanded(), true));
        node.addReference(new Reference(Identifiers.AlarmConditionType_ShelvingState, Identifiers.HasComponent, Identifiers.AlarmConditionType_ShelvingState_TimedShelve.expanded(), true));
        node.addReference(new Reference(Identifiers.AlarmConditionType_ShelvingState, Identifiers.HasTrueSubState, Identifiers.AlarmConditionType_EnabledState.expanded(), false));
        node.addReference(new Reference(Identifiers.AlarmConditionType_ShelvingState, Identifiers.HasTypeDefinition, Identifiers.ShelvedStateMachineType.expanded(), true));
        node.addReference(new Reference(Identifiers.AlarmConditionType_ShelvingState, Identifiers.HasModellingRule, Identifiers.ModellingRule_Optional.expanded(), true));
        node.addReference(new Reference(Identifiers.AlarmConditionType_ShelvingState, Identifiers.HasComponent, Identifiers.AlarmConditionType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode122() {
        FileDirectoryTypeNode node = new FileDirectoryTypeNode(this.context, Identifiers.FileDirectoryType_FileDirectoryName_Placeholder, new QualifiedName(0, "<FileDirectoryName>"), new LocalizedText("en", "<FileDirectoryName>"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.FileDirectoryType_FileDirectoryName_Placeholder, Identifiers.HasComponent, Identifiers.FileDirectoryType_FileDirectoryName_Placeholder_CreateDirectory.expanded(), true));
        node.addReference(new Reference(Identifiers.FileDirectoryType_FileDirectoryName_Placeholder, Identifiers.HasComponent, Identifiers.FileDirectoryType_FileDirectoryName_Placeholder_CreateFile.expanded(), true));
        node.addReference(new Reference(Identifiers.FileDirectoryType_FileDirectoryName_Placeholder, Identifiers.HasComponent, Identifiers.FileDirectoryType_FileDirectoryName_Placeholder_Delete.expanded(), true));
        node.addReference(new Reference(Identifiers.FileDirectoryType_FileDirectoryName_Placeholder, Identifiers.HasComponent, Identifiers.FileDirectoryType_FileDirectoryName_Placeholder_MoveOrCopy.expanded(), true));
        node.addReference(new Reference(Identifiers.FileDirectoryType_FileDirectoryName_Placeholder, Identifiers.HasTypeDefinition, Identifiers.FileDirectoryType.expanded(), true));
        node.addReference(new Reference(Identifiers.FileDirectoryType_FileDirectoryName_Placeholder, Identifiers.HasModellingRule, Identifiers.ModellingRule_OptionalPlaceholder.expanded(), true));
        node.addReference(new Reference(Identifiers.FileDirectoryType_FileDirectoryName_Placeholder, Identifiers.HasComponent, Identifiers.FileDirectoryType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode123() {
        FileTypeNode node = new FileTypeNode(this.context, Identifiers.FileDirectoryType_FileName_Placeholder, new QualifiedName(0, "<FileName>"), new LocalizedText("en", "<FileName>"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.FileDirectoryType_FileName_Placeholder, Identifiers.HasProperty, Identifiers.FileDirectoryType_FileName_Placeholder_Size.expanded(), true));
        node.addReference(new Reference(Identifiers.FileDirectoryType_FileName_Placeholder, Identifiers.HasProperty, Identifiers.FileDirectoryType_FileName_Placeholder_Writable.expanded(), true));
        node.addReference(new Reference(Identifiers.FileDirectoryType_FileName_Placeholder, Identifiers.HasProperty, Identifiers.FileDirectoryType_FileName_Placeholder_UserWritable.expanded(), true));
        node.addReference(new Reference(Identifiers.FileDirectoryType_FileName_Placeholder, Identifiers.HasProperty, Identifiers.FileDirectoryType_FileName_Placeholder_OpenCount.expanded(), true));
        node.addReference(new Reference(Identifiers.FileDirectoryType_FileName_Placeholder, Identifiers.HasComponent, Identifiers.FileDirectoryType_FileName_Placeholder_Open.expanded(), true));
        node.addReference(new Reference(Identifiers.FileDirectoryType_FileName_Placeholder, Identifiers.HasComponent, Identifiers.FileDirectoryType_FileName_Placeholder_Close.expanded(), true));
        node.addReference(new Reference(Identifiers.FileDirectoryType_FileName_Placeholder, Identifiers.HasComponent, Identifiers.FileDirectoryType_FileName_Placeholder_Read.expanded(), true));
        node.addReference(new Reference(Identifiers.FileDirectoryType_FileName_Placeholder, Identifiers.HasComponent, Identifiers.FileDirectoryType_FileName_Placeholder_Write.expanded(), true));
        node.addReference(new Reference(Identifiers.FileDirectoryType_FileName_Placeholder, Identifiers.HasComponent, Identifiers.FileDirectoryType_FileName_Placeholder_GetPosition.expanded(), true));
        node.addReference(new Reference(Identifiers.FileDirectoryType_FileName_Placeholder, Identifiers.HasComponent, Identifiers.FileDirectoryType_FileName_Placeholder_SetPosition.expanded(), true));
        node.addReference(new Reference(Identifiers.FileDirectoryType_FileName_Placeholder, Identifiers.HasTypeDefinition, Identifiers.FileType.expanded(), true));
        node.addReference(new Reference(Identifiers.FileDirectoryType_FileName_Placeholder, Identifiers.HasModellingRule, Identifiers.ModellingRule_OptionalPlaceholder.expanded(), true));
        node.addReference(new Reference(Identifiers.FileDirectoryType_FileName_Placeholder, Identifiers.HasComponent, Identifiers.FileDirectoryType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode124() {
        StateTypeNode node = new StateTypeNode(this.context, Identifiers.ExclusiveLimitStateMachineType_HighHigh, new QualifiedName(0, "HighHigh"), new LocalizedText("en", "HighHigh"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.ExclusiveLimitStateMachineType_HighHigh, Identifiers.HasProperty, Identifiers.ExclusiveLimitStateMachineType_HighHigh_StateNumber.expanded(), true));
        node.addReference(new Reference(Identifiers.ExclusiveLimitStateMachineType_HighHigh, Identifiers.FromState, Identifiers.ExclusiveLimitStateMachineType_HighHighToHigh.expanded(), false));
        node.addReference(new Reference(Identifiers.ExclusiveLimitStateMachineType_HighHigh, Identifiers.ToState, Identifiers.ExclusiveLimitStateMachineType_HighToHighHigh.expanded(), false));
        node.addReference(new Reference(Identifiers.ExclusiveLimitStateMachineType_HighHigh, Identifiers.HasTypeDefinition, Identifiers.StateType.expanded(), true));
        node.addReference(new Reference(Identifiers.ExclusiveLimitStateMachineType_HighHigh, Identifiers.HasComponent, Identifiers.ExclusiveLimitStateMachineType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode125() {
        StateTypeNode node = new StateTypeNode(this.context, Identifiers.ExclusiveLimitStateMachineType_High, new QualifiedName(0, "High"), new LocalizedText("en", "High"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.ExclusiveLimitStateMachineType_High, Identifiers.HasProperty, Identifiers.ExclusiveLimitStateMachineType_High_StateNumber.expanded(), true));
        node.addReference(new Reference(Identifiers.ExclusiveLimitStateMachineType_High, Identifiers.ToState, Identifiers.ExclusiveLimitStateMachineType_HighHighToHigh.expanded(), false));
        node.addReference(new Reference(Identifiers.ExclusiveLimitStateMachineType_High, Identifiers.FromState, Identifiers.ExclusiveLimitStateMachineType_HighToHighHigh.expanded(), false));
        node.addReference(new Reference(Identifiers.ExclusiveLimitStateMachineType_High, Identifiers.HasTypeDefinition, Identifiers.StateType.expanded(), true));
        node.addReference(new Reference(Identifiers.ExclusiveLimitStateMachineType_High, Identifiers.HasComponent, Identifiers.ExclusiveLimitStateMachineType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode126() {
        StateTypeNode node = new StateTypeNode(this.context, Identifiers.ExclusiveLimitStateMachineType_Low, new QualifiedName(0, "Low"), new LocalizedText("en", "Low"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.ExclusiveLimitStateMachineType_Low, Identifiers.HasProperty, Identifiers.ExclusiveLimitStateMachineType_Low_StateNumber.expanded(), true));
        node.addReference(new Reference(Identifiers.ExclusiveLimitStateMachineType_Low, Identifiers.ToState, Identifiers.ExclusiveLimitStateMachineType_LowLowToLow.expanded(), false));
        node.addReference(new Reference(Identifiers.ExclusiveLimitStateMachineType_Low, Identifiers.FromState, Identifiers.ExclusiveLimitStateMachineType_LowToLowLow.expanded(), false));
        node.addReference(new Reference(Identifiers.ExclusiveLimitStateMachineType_Low, Identifiers.HasTypeDefinition, Identifiers.StateType.expanded(), true));
        node.addReference(new Reference(Identifiers.ExclusiveLimitStateMachineType_Low, Identifiers.HasComponent, Identifiers.ExclusiveLimitStateMachineType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode127() {
        StateTypeNode node = new StateTypeNode(this.context, Identifiers.ExclusiveLimitStateMachineType_LowLow, new QualifiedName(0, "LowLow"), new LocalizedText("en", "LowLow"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.ExclusiveLimitStateMachineType_LowLow, Identifiers.HasProperty, Identifiers.ExclusiveLimitStateMachineType_LowLow_StateNumber.expanded(), true));
        node.addReference(new Reference(Identifiers.ExclusiveLimitStateMachineType_LowLow, Identifiers.FromState, Identifiers.ExclusiveLimitStateMachineType_LowLowToLow.expanded(), false));
        node.addReference(new Reference(Identifiers.ExclusiveLimitStateMachineType_LowLow, Identifiers.ToState, Identifiers.ExclusiveLimitStateMachineType_LowToLowLow.expanded(), false));
        node.addReference(new Reference(Identifiers.ExclusiveLimitStateMachineType_LowLow, Identifiers.HasTypeDefinition, Identifiers.StateType.expanded(), true));
        node.addReference(new Reference(Identifiers.ExclusiveLimitStateMachineType_LowLow, Identifiers.HasComponent, Identifiers.ExclusiveLimitStateMachineType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode128() {
        TransitionTypeNode node = new TransitionTypeNode(this.context, Identifiers.ExclusiveLimitStateMachineType_LowLowToLow, new QualifiedName(0, "LowLowToLow"), new LocalizedText("en", "LowLowToLow"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.ExclusiveLimitStateMachineType_LowLowToLow, Identifiers.HasProperty, Identifiers.ExclusiveLimitStateMachineType_LowLowToLow_TransitionNumber.expanded(), true));
        node.addReference(new Reference(Identifiers.ExclusiveLimitStateMachineType_LowLowToLow, Identifiers.FromState, Identifiers.ExclusiveLimitStateMachineType_LowLow.expanded(), true));
        node.addReference(new Reference(Identifiers.ExclusiveLimitStateMachineType_LowLowToLow, Identifiers.ToState, Identifiers.ExclusiveLimitStateMachineType_Low.expanded(), true));
        node.addReference(new Reference(Identifiers.ExclusiveLimitStateMachineType_LowLowToLow, Identifiers.HasTypeDefinition, Identifiers.TransitionType.expanded(), true));
        node.addReference(new Reference(Identifiers.ExclusiveLimitStateMachineType_LowLowToLow, Identifiers.HasComponent, Identifiers.ExclusiveLimitStateMachineType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode129() {
        TransitionTypeNode node = new TransitionTypeNode(this.context, Identifiers.ExclusiveLimitStateMachineType_LowToLowLow, new QualifiedName(0, "LowToLowLow"), new LocalizedText("en", "LowToLowLow"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.ExclusiveLimitStateMachineType_LowToLowLow, Identifiers.HasProperty, Identifiers.ExclusiveLimitStateMachineType_LowToLowLow_TransitionNumber.expanded(), true));
        node.addReference(new Reference(Identifiers.ExclusiveLimitStateMachineType_LowToLowLow, Identifiers.FromState, Identifiers.ExclusiveLimitStateMachineType_Low.expanded(), true));
        node.addReference(new Reference(Identifiers.ExclusiveLimitStateMachineType_LowToLowLow, Identifiers.ToState, Identifiers.ExclusiveLimitStateMachineType_LowLow.expanded(), true));
        node.addReference(new Reference(Identifiers.ExclusiveLimitStateMachineType_LowToLowLow, Identifiers.HasTypeDefinition, Identifiers.TransitionType.expanded(), true));
        node.addReference(new Reference(Identifiers.ExclusiveLimitStateMachineType_LowToLowLow, Identifiers.HasComponent, Identifiers.ExclusiveLimitStateMachineType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode130() {
        TransitionTypeNode node = new TransitionTypeNode(this.context, Identifiers.ExclusiveLimitStateMachineType_HighHighToHigh, new QualifiedName(0, "HighHighToHigh"), new LocalizedText("en", "HighHighToHigh"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.ExclusiveLimitStateMachineType_HighHighToHigh, Identifiers.HasProperty, Identifiers.ExclusiveLimitStateMachineType_HighHighToHigh_TransitionNumber.expanded(), true));
        node.addReference(new Reference(Identifiers.ExclusiveLimitStateMachineType_HighHighToHigh, Identifiers.FromState, Identifiers.ExclusiveLimitStateMachineType_HighHigh.expanded(), true));
        node.addReference(new Reference(Identifiers.ExclusiveLimitStateMachineType_HighHighToHigh, Identifiers.ToState, Identifiers.ExclusiveLimitStateMachineType_High.expanded(), true));
        node.addReference(new Reference(Identifiers.ExclusiveLimitStateMachineType_HighHighToHigh, Identifiers.HasTypeDefinition, Identifiers.TransitionType.expanded(), true));
        node.addReference(new Reference(Identifiers.ExclusiveLimitStateMachineType_HighHighToHigh, Identifiers.HasComponent, Identifiers.ExclusiveLimitStateMachineType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode131() {
        TransitionTypeNode node = new TransitionTypeNode(this.context, Identifiers.ExclusiveLimitStateMachineType_HighToHighHigh, new QualifiedName(0, "HighToHighHigh"), new LocalizedText("en", "HighToHighHigh"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.ExclusiveLimitStateMachineType_HighToHighHigh, Identifiers.HasProperty, Identifiers.ExclusiveLimitStateMachineType_HighToHighHigh_TransitionNumber.expanded(), true));
        node.addReference(new Reference(Identifiers.ExclusiveLimitStateMachineType_HighToHighHigh, Identifiers.FromState, Identifiers.ExclusiveLimitStateMachineType_High.expanded(), true));
        node.addReference(new Reference(Identifiers.ExclusiveLimitStateMachineType_HighToHighHigh, Identifiers.ToState, Identifiers.ExclusiveLimitStateMachineType_HighHigh.expanded(), true));
        node.addReference(new Reference(Identifiers.ExclusiveLimitStateMachineType_HighToHighHigh, Identifiers.HasTypeDefinition, Identifiers.TransitionType.expanded(), true));
        node.addReference(new Reference(Identifiers.ExclusiveLimitStateMachineType_HighToHighHigh, Identifiers.HasComponent, Identifiers.ExclusiveLimitStateMachineType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode132() {
        ExclusiveLimitStateMachineTypeNode node = new ExclusiveLimitStateMachineTypeNode(this.context, Identifiers.ExclusiveLimitAlarmType_LimitState, new QualifiedName(0, "LimitState"), new LocalizedText("en", "LimitState"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.ExclusiveLimitAlarmType_LimitState, Identifiers.HasComponent, Identifiers.ExclusiveLimitAlarmType_LimitState_CurrentState.expanded(), true));
        node.addReference(new Reference(Identifiers.ExclusiveLimitAlarmType_LimitState, Identifiers.HasComponent, Identifiers.ExclusiveLimitAlarmType_LimitState_LastTransition.expanded(), true));
        node.addReference(new Reference(Identifiers.ExclusiveLimitAlarmType_LimitState, Identifiers.HasTrueSubState, Identifiers.ExclusiveLimitAlarmType_ActiveState.expanded(), false));
        node.addReference(new Reference(Identifiers.ExclusiveLimitAlarmType_LimitState, Identifiers.HasTypeDefinition, Identifiers.ExclusiveLimitStateMachineType.expanded(), true));
        node.addReference(new Reference(Identifiers.ExclusiveLimitAlarmType_LimitState, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.ExclusiveLimitAlarmType_LimitState, Identifiers.HasComponent, Identifiers.ExclusiveLimitAlarmType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode133() {
        TrustListTypeNode node = new TrustListTypeNode(this.context, Identifiers.CertificateGroupType_TrustList, new QualifiedName(0, "TrustList"), new LocalizedText("en", "TrustList"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.CertificateGroupType_TrustList, Identifiers.HasProperty, Identifiers.CertificateGroupType_TrustList_Size.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupType_TrustList, Identifiers.HasProperty, Identifiers.CertificateGroupType_TrustList_Writable.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupType_TrustList, Identifiers.HasProperty, Identifiers.CertificateGroupType_TrustList_UserWritable.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupType_TrustList, Identifiers.HasProperty, Identifiers.CertificateGroupType_TrustList_OpenCount.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupType_TrustList, Identifiers.HasComponent, Identifiers.CertificateGroupType_TrustList_Open.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupType_TrustList, Identifiers.HasComponent, Identifiers.CertificateGroupType_TrustList_Close.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupType_TrustList, Identifiers.HasComponent, Identifiers.CertificateGroupType_TrustList_Read.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupType_TrustList, Identifiers.HasComponent, Identifiers.CertificateGroupType_TrustList_Write.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupType_TrustList, Identifiers.HasComponent, Identifiers.CertificateGroupType_TrustList_GetPosition.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupType_TrustList, Identifiers.HasComponent, Identifiers.CertificateGroupType_TrustList_SetPosition.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupType_TrustList, Identifiers.HasProperty, Identifiers.CertificateGroupType_TrustList_LastUpdateTime.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupType_TrustList, Identifiers.HasComponent, Identifiers.CertificateGroupType_TrustList_OpenWithMasks.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupType_TrustList, Identifiers.HasTypeDefinition, Identifiers.TrustListType.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupType_TrustList, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupType_TrustList, Identifiers.HasComponent, Identifiers.CertificateGroupType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode134() {
        CertificateGroupTypeNode node = new CertificateGroupTypeNode(this.context, Identifiers.CertificateGroupFolderType_DefaultApplicationGroup, new QualifiedName(0, "DefaultApplicationGroup"), new LocalizedText("en", "DefaultApplicationGroup"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultApplicationGroup, Identifiers.HasComponent, Identifiers.CertificateGroupFolderType_DefaultApplicationGroup_TrustList.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultApplicationGroup, Identifiers.HasProperty, Identifiers.CertificateGroupFolderType_DefaultApplicationGroup_CertificateTypes.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultApplicationGroup, Identifiers.HasTypeDefinition, Identifiers.CertificateGroupType.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultApplicationGroup, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultApplicationGroup, Identifiers.HasComponent, Identifiers.CertificateGroupFolderType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode135() {
        TrustListTypeNode node = new TrustListTypeNode(this.context, Identifiers.CertificateGroupFolderType_DefaultApplicationGroup_TrustList, new QualifiedName(0, "TrustList"), new LocalizedText("en", "TrustList"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultApplicationGroup_TrustList, Identifiers.HasProperty, Identifiers.CertificateGroupFolderType_DefaultApplicationGroup_TrustList_Size.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultApplicationGroup_TrustList, Identifiers.HasProperty, Identifiers.CertificateGroupFolderType_DefaultApplicationGroup_TrustList_Writable.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultApplicationGroup_TrustList, Identifiers.HasProperty, Identifiers.CertificateGroupFolderType_DefaultApplicationGroup_TrustList_UserWritable.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultApplicationGroup_TrustList, Identifiers.HasProperty, Identifiers.CertificateGroupFolderType_DefaultApplicationGroup_TrustList_OpenCount.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultApplicationGroup_TrustList, Identifiers.HasComponent, Identifiers.CertificateGroupFolderType_DefaultApplicationGroup_TrustList_Open.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultApplicationGroup_TrustList, Identifiers.HasComponent, Identifiers.CertificateGroupFolderType_DefaultApplicationGroup_TrustList_Close.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultApplicationGroup_TrustList, Identifiers.HasComponent, Identifiers.CertificateGroupFolderType_DefaultApplicationGroup_TrustList_Read.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultApplicationGroup_TrustList, Identifiers.HasComponent, Identifiers.CertificateGroupFolderType_DefaultApplicationGroup_TrustList_Write.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultApplicationGroup_TrustList, Identifiers.HasComponent, Identifiers.CertificateGroupFolderType_DefaultApplicationGroup_TrustList_GetPosition.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultApplicationGroup_TrustList, Identifiers.HasComponent, Identifiers.CertificateGroupFolderType_DefaultApplicationGroup_TrustList_SetPosition.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultApplicationGroup_TrustList, Identifiers.HasProperty, Identifiers.CertificateGroupFolderType_DefaultApplicationGroup_TrustList_LastUpdateTime.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultApplicationGroup_TrustList, Identifiers.HasComponent, Identifiers.CertificateGroupFolderType_DefaultApplicationGroup_TrustList_OpenWithMasks.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultApplicationGroup_TrustList, Identifiers.HasTypeDefinition, Identifiers.TrustListType.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultApplicationGroup_TrustList, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultApplicationGroup_TrustList, Identifiers.HasComponent, Identifiers.CertificateGroupFolderType_DefaultApplicationGroup.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode136() {
        CertificateGroupTypeNode node = new CertificateGroupTypeNode(this.context, Identifiers.CertificateGroupFolderType_DefaultHttpsGroup, new QualifiedName(0, "DefaultHttpsGroup"), new LocalizedText("en", "DefaultHttpsGroup"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultHttpsGroup, Identifiers.HasComponent, Identifiers.CertificateGroupFolderType_DefaultHttpsGroup_TrustList.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultHttpsGroup, Identifiers.HasProperty, Identifiers.CertificateGroupFolderType_DefaultHttpsGroup_CertificateTypes.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultHttpsGroup, Identifiers.HasTypeDefinition, Identifiers.CertificateGroupType.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultHttpsGroup, Identifiers.HasModellingRule, Identifiers.ModellingRule_Optional.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultHttpsGroup, Identifiers.HasComponent, Identifiers.CertificateGroupFolderType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode137() {
        TrustListTypeNode node = new TrustListTypeNode(this.context, Identifiers.CertificateGroupFolderType_DefaultHttpsGroup_TrustList, new QualifiedName(0, "TrustList"), new LocalizedText("en", "TrustList"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultHttpsGroup_TrustList, Identifiers.HasProperty, Identifiers.CertificateGroupFolderType_DefaultHttpsGroup_TrustList_Size.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultHttpsGroup_TrustList, Identifiers.HasProperty, Identifiers.CertificateGroupFolderType_DefaultHttpsGroup_TrustList_Writable.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultHttpsGroup_TrustList, Identifiers.HasProperty, Identifiers.CertificateGroupFolderType_DefaultHttpsGroup_TrustList_UserWritable.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultHttpsGroup_TrustList, Identifiers.HasProperty, Identifiers.CertificateGroupFolderType_DefaultHttpsGroup_TrustList_OpenCount.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultHttpsGroup_TrustList, Identifiers.HasComponent, Identifiers.CertificateGroupFolderType_DefaultHttpsGroup_TrustList_Open.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultHttpsGroup_TrustList, Identifiers.HasComponent, Identifiers.CertificateGroupFolderType_DefaultHttpsGroup_TrustList_Close.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultHttpsGroup_TrustList, Identifiers.HasComponent, Identifiers.CertificateGroupFolderType_DefaultHttpsGroup_TrustList_Read.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultHttpsGroup_TrustList, Identifiers.HasComponent, Identifiers.CertificateGroupFolderType_DefaultHttpsGroup_TrustList_Write.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultHttpsGroup_TrustList, Identifiers.HasComponent, Identifiers.CertificateGroupFolderType_DefaultHttpsGroup_TrustList_GetPosition.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultHttpsGroup_TrustList, Identifiers.HasComponent, Identifiers.CertificateGroupFolderType_DefaultHttpsGroup_TrustList_SetPosition.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultHttpsGroup_TrustList, Identifiers.HasProperty, Identifiers.CertificateGroupFolderType_DefaultHttpsGroup_TrustList_LastUpdateTime.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultHttpsGroup_TrustList, Identifiers.HasComponent, Identifiers.CertificateGroupFolderType_DefaultHttpsGroup_TrustList_OpenWithMasks.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultHttpsGroup_TrustList, Identifiers.HasTypeDefinition, Identifiers.TrustListType.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultHttpsGroup_TrustList, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultHttpsGroup_TrustList, Identifiers.HasComponent, Identifiers.CertificateGroupFolderType_DefaultHttpsGroup.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode138() {
        CertificateGroupTypeNode node = new CertificateGroupTypeNode(this.context, Identifiers.CertificateGroupFolderType_DefaultUserTokenGroup, new QualifiedName(0, "DefaultUserTokenGroup"), new LocalizedText("en", "DefaultUserTokenGroup"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultUserTokenGroup, Identifiers.HasComponent, Identifiers.CertificateGroupFolderType_DefaultUserTokenGroup_TrustList.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultUserTokenGroup, Identifiers.HasProperty, Identifiers.CertificateGroupFolderType_DefaultUserTokenGroup_CertificateTypes.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultUserTokenGroup, Identifiers.HasTypeDefinition, Identifiers.CertificateGroupType.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultUserTokenGroup, Identifiers.HasModellingRule, Identifiers.ModellingRule_Optional.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultUserTokenGroup, Identifiers.HasComponent, Identifiers.CertificateGroupFolderType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode139() {
        TrustListTypeNode node = new TrustListTypeNode(this.context, Identifiers.CertificateGroupFolderType_DefaultUserTokenGroup_TrustList, new QualifiedName(0, "TrustList"), new LocalizedText("en", "TrustList"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultUserTokenGroup_TrustList, Identifiers.HasProperty, Identifiers.CertificateGroupFolderType_DefaultUserTokenGroup_TrustList_Size.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultUserTokenGroup_TrustList, Identifiers.HasProperty, Identifiers.CertificateGroupFolderType_DefaultUserTokenGroup_TrustList_Writable.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultUserTokenGroup_TrustList, Identifiers.HasProperty, Identifiers.CertificateGroupFolderType_DefaultUserTokenGroup_TrustList_UserWritable.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultUserTokenGroup_TrustList, Identifiers.HasProperty, Identifiers.CertificateGroupFolderType_DefaultUserTokenGroup_TrustList_OpenCount.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultUserTokenGroup_TrustList, Identifiers.HasComponent, Identifiers.CertificateGroupFolderType_DefaultUserTokenGroup_TrustList_Open.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultUserTokenGroup_TrustList, Identifiers.HasComponent, Identifiers.CertificateGroupFolderType_DefaultUserTokenGroup_TrustList_Close.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultUserTokenGroup_TrustList, Identifiers.HasComponent, Identifiers.CertificateGroupFolderType_DefaultUserTokenGroup_TrustList_Read.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultUserTokenGroup_TrustList, Identifiers.HasComponent, Identifiers.CertificateGroupFolderType_DefaultUserTokenGroup_TrustList_Write.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultUserTokenGroup_TrustList, Identifiers.HasComponent, Identifiers.CertificateGroupFolderType_DefaultUserTokenGroup_TrustList_GetPosition.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultUserTokenGroup_TrustList, Identifiers.HasComponent, Identifiers.CertificateGroupFolderType_DefaultUserTokenGroup_TrustList_SetPosition.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultUserTokenGroup_TrustList, Identifiers.HasProperty, Identifiers.CertificateGroupFolderType_DefaultUserTokenGroup_TrustList_LastUpdateTime.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultUserTokenGroup_TrustList, Identifiers.HasComponent, Identifiers.CertificateGroupFolderType_DefaultUserTokenGroup_TrustList_OpenWithMasks.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultUserTokenGroup_TrustList, Identifiers.HasTypeDefinition, Identifiers.TrustListType.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultUserTokenGroup_TrustList, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_DefaultUserTokenGroup_TrustList, Identifiers.HasComponent, Identifiers.CertificateGroupFolderType_DefaultUserTokenGroup.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode140() {
        CertificateGroupTypeNode node = new CertificateGroupTypeNode(this.context, Identifiers.CertificateGroupFolderType_AdditionalGroup_Placeholder, new QualifiedName(0, "<AdditionalGroup>"), new LocalizedText("en", "<AdditionalGroup>"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_AdditionalGroup_Placeholder, Identifiers.HasComponent, Identifiers.CertificateGroupFolderType_AdditionalGroup_Placeholder_TrustList.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_AdditionalGroup_Placeholder, Identifiers.HasProperty, Identifiers.CertificateGroupFolderType_AdditionalGroup_Placeholder_CertificateTypes.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_AdditionalGroup_Placeholder, Identifiers.HasTypeDefinition, Identifiers.CertificateGroupType.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_AdditionalGroup_Placeholder, Identifiers.HasModellingRule, Identifiers.ModellingRule_OptionalPlaceholder.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_AdditionalGroup_Placeholder, Identifiers.Organizes, Identifiers.CertificateGroupFolderType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode141() {
        TrustListTypeNode node = new TrustListTypeNode(this.context, Identifiers.CertificateGroupFolderType_AdditionalGroup_Placeholder_TrustList, new QualifiedName(0, "TrustList"), new LocalizedText("en", "TrustList"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_AdditionalGroup_Placeholder_TrustList, Identifiers.HasProperty, Identifiers.CertificateGroupFolderType_AdditionalGroup_Placeholder_TrustList_Size.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_AdditionalGroup_Placeholder_TrustList, Identifiers.HasProperty, Identifiers.CertificateGroupFolderType_AdditionalGroup_Placeholder_TrustList_Writable.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_AdditionalGroup_Placeholder_TrustList, Identifiers.HasProperty, Identifiers.CertificateGroupFolderType_AdditionalGroup_Placeholder_TrustList_UserWritable.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_AdditionalGroup_Placeholder_TrustList, Identifiers.HasProperty, Identifiers.CertificateGroupFolderType_AdditionalGroup_Placeholder_TrustList_OpenCount.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_AdditionalGroup_Placeholder_TrustList, Identifiers.HasComponent, Identifiers.CertificateGroupFolderType_AdditionalGroup_Placeholder_TrustList_Open.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_AdditionalGroup_Placeholder_TrustList, Identifiers.HasComponent, Identifiers.CertificateGroupFolderType_AdditionalGroup_Placeholder_TrustList_Close.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_AdditionalGroup_Placeholder_TrustList, Identifiers.HasComponent, Identifiers.CertificateGroupFolderType_AdditionalGroup_Placeholder_TrustList_Read.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_AdditionalGroup_Placeholder_TrustList, Identifiers.HasComponent, Identifiers.CertificateGroupFolderType_AdditionalGroup_Placeholder_TrustList_Write.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_AdditionalGroup_Placeholder_TrustList, Identifiers.HasComponent, Identifiers.CertificateGroupFolderType_AdditionalGroup_Placeholder_TrustList_GetPosition.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_AdditionalGroup_Placeholder_TrustList, Identifiers.HasComponent, Identifiers.CertificateGroupFolderType_AdditionalGroup_Placeholder_TrustList_SetPosition.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_AdditionalGroup_Placeholder_TrustList, Identifiers.HasProperty, Identifiers.CertificateGroupFolderType_AdditionalGroup_Placeholder_TrustList_LastUpdateTime.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_AdditionalGroup_Placeholder_TrustList, Identifiers.HasComponent, Identifiers.CertificateGroupFolderType_AdditionalGroup_Placeholder_TrustList_OpenWithMasks.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_AdditionalGroup_Placeholder_TrustList, Identifiers.HasTypeDefinition, Identifiers.TrustListType.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_AdditionalGroup_Placeholder_TrustList, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.CertificateGroupFolderType_AdditionalGroup_Placeholder_TrustList, Identifiers.HasComponent, Identifiers.CertificateGroupFolderType_AdditionalGroup_Placeholder.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode142() {
        CertificateGroupFolderTypeNode node = new CertificateGroupFolderTypeNode(this.context, Identifiers.ServerConfigurationType_CertificateGroups, new QualifiedName(0, "CertificateGroups"), new LocalizedText("en", "CertificateGroups"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.ServerConfigurationType_CertificateGroups, Identifiers.HasComponent, Identifiers.ServerConfigurationType_CertificateGroups_DefaultApplicationGroup.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfigurationType_CertificateGroups, Identifiers.HasTypeDefinition, Identifiers.CertificateGroupFolderType.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfigurationType_CertificateGroups, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfigurationType_CertificateGroups, Identifiers.HasComponent, Identifiers.ServerConfigurationType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode143() {
        CertificateGroupTypeNode node = new CertificateGroupTypeNode(this.context, Identifiers.ServerConfigurationType_CertificateGroups_DefaultApplicationGroup, new QualifiedName(0, "DefaultApplicationGroup"), new LocalizedText("en", "DefaultApplicationGroup"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.ServerConfigurationType_CertificateGroups_DefaultApplicationGroup, Identifiers.HasComponent, Identifiers.ServerConfigurationType_CertificateGroups_DefaultApplicationGroup_TrustList.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfigurationType_CertificateGroups_DefaultApplicationGroup, Identifiers.HasProperty, Identifiers.ServerConfigurationType_CertificateGroups_DefaultApplicationGroup_CertificateTypes.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfigurationType_CertificateGroups_DefaultApplicationGroup, Identifiers.HasTypeDefinition, Identifiers.CertificateGroupType.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfigurationType_CertificateGroups_DefaultApplicationGroup, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfigurationType_CertificateGroups_DefaultApplicationGroup, Identifiers.HasComponent, Identifiers.ServerConfigurationType_CertificateGroups.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode144() {
        TrustListTypeNode node = new TrustListTypeNode(this.context, Identifiers.ServerConfigurationType_CertificateGroups_DefaultApplicationGroup_TrustList, new QualifiedName(0, "TrustList"), new LocalizedText("en", "TrustList"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.ServerConfigurationType_CertificateGroups_DefaultApplicationGroup_TrustList, Identifiers.HasProperty, Identifiers.ServerConfigurationType_CertificateGroups_DefaultApplicationGroup_TrustList_Size.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfigurationType_CertificateGroups_DefaultApplicationGroup_TrustList, Identifiers.HasProperty, Identifiers.ServerConfigurationType_CertificateGroups_DefaultApplicationGroup_TrustList_Writable.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfigurationType_CertificateGroups_DefaultApplicationGroup_TrustList, Identifiers.HasProperty, Identifiers.ServerConfigurationType_CertificateGroups_DefaultApplicationGroup_TrustList_UserWritable.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfigurationType_CertificateGroups_DefaultApplicationGroup_TrustList, Identifiers.HasProperty, Identifiers.ServerConfigurationType_CertificateGroups_DefaultApplicationGroup_TrustList_OpenCount.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfigurationType_CertificateGroups_DefaultApplicationGroup_TrustList, Identifiers.HasComponent, Identifiers.ServerConfigurationType_CertificateGroups_DefaultApplicationGroup_TrustList_Open.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfigurationType_CertificateGroups_DefaultApplicationGroup_TrustList, Identifiers.HasComponent, Identifiers.ServerConfigurationType_CertificateGroups_DefaultApplicationGroup_TrustList_Close.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfigurationType_CertificateGroups_DefaultApplicationGroup_TrustList, Identifiers.HasComponent, Identifiers.ServerConfigurationType_CertificateGroups_DefaultApplicationGroup_TrustList_Read.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfigurationType_CertificateGroups_DefaultApplicationGroup_TrustList, Identifiers.HasComponent, Identifiers.ServerConfigurationType_CertificateGroups_DefaultApplicationGroup_TrustList_Write.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfigurationType_CertificateGroups_DefaultApplicationGroup_TrustList, Identifiers.HasComponent, Identifiers.ServerConfigurationType_CertificateGroups_DefaultApplicationGroup_TrustList_GetPosition.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfigurationType_CertificateGroups_DefaultApplicationGroup_TrustList, Identifiers.HasComponent, Identifiers.ServerConfigurationType_CertificateGroups_DefaultApplicationGroup_TrustList_SetPosition.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfigurationType_CertificateGroups_DefaultApplicationGroup_TrustList, Identifiers.HasProperty, Identifiers.ServerConfigurationType_CertificateGroups_DefaultApplicationGroup_TrustList_LastUpdateTime.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfigurationType_CertificateGroups_DefaultApplicationGroup_TrustList, Identifiers.HasComponent, Identifiers.ServerConfigurationType_CertificateGroups_DefaultApplicationGroup_TrustList_OpenWithMasks.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfigurationType_CertificateGroups_DefaultApplicationGroup_TrustList, Identifiers.HasTypeDefinition, Identifiers.TrustListType.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfigurationType_CertificateGroups_DefaultApplicationGroup_TrustList, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfigurationType_CertificateGroups_DefaultApplicationGroup_TrustList, Identifiers.HasComponent, Identifiers.ServerConfigurationType_CertificateGroups_DefaultApplicationGroup.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode145() {
        CertificateGroupFolderTypeNode node = new CertificateGroupFolderTypeNode(this.context, Identifiers.ServerConfiguration_CertificateGroups, new QualifiedName(0, "CertificateGroups"), new LocalizedText("en", "CertificateGroups"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups, Identifiers.HasComponent, Identifiers.ServerConfiguration_CertificateGroups_DefaultApplicationGroup.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups, Identifiers.HasComponent, Identifiers.ServerConfiguration_CertificateGroups_DefaultHttpsGroup.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups, Identifiers.HasComponent, Identifiers.ServerConfiguration_CertificateGroups_DefaultUserTokenGroup.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups, Identifiers.HasTypeDefinition, Identifiers.CertificateGroupFolderType.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups, Identifiers.HasComponent, Identifiers.ServerConfiguration.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode146() {
        CertificateGroupTypeNode node = new CertificateGroupTypeNode(this.context, Identifiers.ServerConfiguration_CertificateGroups_DefaultHttpsGroup, new QualifiedName(0, "DefaultHttpsGroup"), new LocalizedText("en", "DefaultHttpsGroup"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultHttpsGroup, Identifiers.HasComponent, Identifiers.ServerConfiguration_CertificateGroups_DefaultHttpsGroup_TrustList.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultHttpsGroup, Identifiers.HasProperty, Identifiers.ServerConfiguration_CertificateGroups_DefaultHttpsGroup_CertificateTypes.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultHttpsGroup, Identifiers.HasTypeDefinition, Identifiers.CertificateGroupType.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultHttpsGroup, Identifiers.HasComponent, Identifiers.ServerConfiguration_CertificateGroups.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode147() {
        TrustListTypeNode node = new TrustListTypeNode(this.context, Identifiers.ServerConfiguration_CertificateGroups_DefaultHttpsGroup_TrustList, new QualifiedName(0, "TrustList"), new LocalizedText("en", "TrustList"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultHttpsGroup_TrustList, Identifiers.HasProperty, Identifiers.ServerConfiguration_CertificateGroups_DefaultHttpsGroup_TrustList_Size.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultHttpsGroup_TrustList, Identifiers.HasProperty, Identifiers.ServerConfiguration_CertificateGroups_DefaultHttpsGroup_TrustList_Writable.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultHttpsGroup_TrustList, Identifiers.HasProperty, Identifiers.ServerConfiguration_CertificateGroups_DefaultHttpsGroup_TrustList_UserWritable.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultHttpsGroup_TrustList, Identifiers.HasProperty, Identifiers.ServerConfiguration_CertificateGroups_DefaultHttpsGroup_TrustList_OpenCount.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultHttpsGroup_TrustList, Identifiers.HasComponent, Identifiers.ServerConfiguration_CertificateGroups_DefaultHttpsGroup_TrustList_Open.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultHttpsGroup_TrustList, Identifiers.HasComponent, Identifiers.ServerConfiguration_CertificateGroups_DefaultHttpsGroup_TrustList_Close.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultHttpsGroup_TrustList, Identifiers.HasComponent, Identifiers.ServerConfiguration_CertificateGroups_DefaultHttpsGroup_TrustList_Read.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultHttpsGroup_TrustList, Identifiers.HasComponent, Identifiers.ServerConfiguration_CertificateGroups_DefaultHttpsGroup_TrustList_Write.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultHttpsGroup_TrustList, Identifiers.HasComponent, Identifiers.ServerConfiguration_CertificateGroups_DefaultHttpsGroup_TrustList_GetPosition.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultHttpsGroup_TrustList, Identifiers.HasComponent, Identifiers.ServerConfiguration_CertificateGroups_DefaultHttpsGroup_TrustList_SetPosition.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultHttpsGroup_TrustList, Identifiers.HasProperty, Identifiers.ServerConfiguration_CertificateGroups_DefaultHttpsGroup_TrustList_LastUpdateTime.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultHttpsGroup_TrustList, Identifiers.HasComponent, Identifiers.ServerConfiguration_CertificateGroups_DefaultHttpsGroup_TrustList_OpenWithMasks.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultHttpsGroup_TrustList, Identifiers.HasComponent, Identifiers.ServerConfiguration_CertificateGroups_DefaultHttpsGroup_TrustList_CloseAndUpdate.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultHttpsGroup_TrustList, Identifiers.HasComponent, Identifiers.ServerConfiguration_CertificateGroups_DefaultHttpsGroup_TrustList_AddCertificate.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultHttpsGroup_TrustList, Identifiers.HasComponent, Identifiers.ServerConfiguration_CertificateGroups_DefaultHttpsGroup_TrustList_RemoveCertificate.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultHttpsGroup_TrustList, Identifiers.HasTypeDefinition, Identifiers.TrustListType.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultHttpsGroup_TrustList, Identifiers.HasComponent, Identifiers.ServerConfiguration_CertificateGroups_DefaultHttpsGroup.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode148() {
        CertificateGroupTypeNode node = new CertificateGroupTypeNode(this.context, Identifiers.ServerConfiguration_CertificateGroups_DefaultUserTokenGroup, new QualifiedName(0, "DefaultUserTokenGroup"), new LocalizedText("en", "DefaultUserTokenGroup"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultUserTokenGroup, Identifiers.HasComponent, Identifiers.ServerConfiguration_CertificateGroups_DefaultUserTokenGroup_TrustList.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultUserTokenGroup, Identifiers.HasProperty, Identifiers.ServerConfiguration_CertificateGroups_DefaultUserTokenGroup_CertificateTypes.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultUserTokenGroup, Identifiers.HasTypeDefinition, Identifiers.CertificateGroupType.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultUserTokenGroup, Identifiers.HasComponent, Identifiers.ServerConfiguration_CertificateGroups.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode149() {
        TrustListTypeNode node = new TrustListTypeNode(this.context, Identifiers.ServerConfiguration_CertificateGroups_DefaultUserTokenGroup_TrustList, new QualifiedName(0, "TrustList"), new LocalizedText("en", "TrustList"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultUserTokenGroup_TrustList, Identifiers.HasProperty, Identifiers.ServerConfiguration_CertificateGroups_DefaultUserTokenGroup_TrustList_Size.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultUserTokenGroup_TrustList, Identifiers.HasProperty, Identifiers.ServerConfiguration_CertificateGroups_DefaultUserTokenGroup_TrustList_Writable.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultUserTokenGroup_TrustList, Identifiers.HasProperty, Identifiers.ServerConfiguration_CertificateGroups_DefaultUserTokenGroup_TrustList_UserWritable.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultUserTokenGroup_TrustList, Identifiers.HasProperty, Identifiers.ServerConfiguration_CertificateGroups_DefaultUserTokenGroup_TrustList_OpenCount.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultUserTokenGroup_TrustList, Identifiers.HasComponent, Identifiers.ServerConfiguration_CertificateGroups_DefaultUserTokenGroup_TrustList_Open.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultUserTokenGroup_TrustList, Identifiers.HasComponent, Identifiers.ServerConfiguration_CertificateGroups_DefaultUserTokenGroup_TrustList_Close.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultUserTokenGroup_TrustList, Identifiers.HasComponent, Identifiers.ServerConfiguration_CertificateGroups_DefaultUserTokenGroup_TrustList_Read.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultUserTokenGroup_TrustList, Identifiers.HasComponent, Identifiers.ServerConfiguration_CertificateGroups_DefaultUserTokenGroup_TrustList_Write.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultUserTokenGroup_TrustList, Identifiers.HasComponent, Identifiers.ServerConfiguration_CertificateGroups_DefaultUserTokenGroup_TrustList_GetPosition.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultUserTokenGroup_TrustList, Identifiers.HasComponent, Identifiers.ServerConfiguration_CertificateGroups_DefaultUserTokenGroup_TrustList_SetPosition.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultUserTokenGroup_TrustList, Identifiers.HasProperty, Identifiers.ServerConfiguration_CertificateGroups_DefaultUserTokenGroup_TrustList_LastUpdateTime.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultUserTokenGroup_TrustList, Identifiers.HasComponent, Identifiers.ServerConfiguration_CertificateGroups_DefaultUserTokenGroup_TrustList_OpenWithMasks.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultUserTokenGroup_TrustList, Identifiers.HasComponent, Identifiers.ServerConfiguration_CertificateGroups_DefaultUserTokenGroup_TrustList_CloseAndUpdate.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultUserTokenGroup_TrustList, Identifiers.HasComponent, Identifiers.ServerConfiguration_CertificateGroups_DefaultUserTokenGroup_TrustList_AddCertificate.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultUserTokenGroup_TrustList, Identifiers.HasComponent, Identifiers.ServerConfiguration_CertificateGroups_DefaultUserTokenGroup_TrustList_RemoveCertificate.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultUserTokenGroup_TrustList, Identifiers.HasTypeDefinition, Identifiers.TrustListType.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultUserTokenGroup_TrustList, Identifiers.HasComponent, Identifiers.ServerConfiguration_CertificateGroups_DefaultUserTokenGroup.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode150() {
        CertificateGroupTypeNode node = new CertificateGroupTypeNode(this.context, Identifiers.ServerConfiguration_CertificateGroups_DefaultApplicationGroup, new QualifiedName(0, "DefaultApplicationGroup"), new LocalizedText("en", "DefaultApplicationGroup"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultApplicationGroup, Identifiers.HasComponent, Identifiers.ServerConfiguration_CertificateGroups_DefaultApplicationGroup_TrustList.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultApplicationGroup, Identifiers.HasProperty, Identifiers.ServerConfiguration_CertificateGroups_DefaultApplicationGroup_CertificateTypes.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultApplicationGroup, Identifiers.HasTypeDefinition, Identifiers.CertificateGroupType.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerConfiguration_CertificateGroups_DefaultApplicationGroup, Identifiers.HasComponent, Identifiers.ServerConfiguration_CertificateGroups.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode151() {
        ServerCapabilitiesTypeNode node = new ServerCapabilitiesTypeNode(this.context, Identifiers.ServerType_ServerCapabilities, new QualifiedName(0, "ServerCapabilities"), new LocalizedText("en", "ServerCapabilities"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.ServerType_ServerCapabilities, Identifiers.HasProperty, Identifiers.ServerType_ServerCapabilities_ServerProfileArray.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerType_ServerCapabilities, Identifiers.HasProperty, Identifiers.ServerType_ServerCapabilities_LocaleIdArray.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerType_ServerCapabilities, Identifiers.HasProperty, Identifiers.ServerType_ServerCapabilities_MinSupportedSampleRate.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerType_ServerCapabilities, Identifiers.HasProperty, Identifiers.ServerType_ServerCapabilities_MaxBrowseContinuationPoints.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerType_ServerCapabilities, Identifiers.HasProperty, Identifiers.ServerType_ServerCapabilities_MaxQueryContinuationPoints.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerType_ServerCapabilities, Identifiers.HasProperty, Identifiers.ServerType_ServerCapabilities_MaxHistoryContinuationPoints.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerType_ServerCapabilities, Identifiers.HasProperty, Identifiers.ServerType_ServerCapabilities_SoftwareCertificates.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerType_ServerCapabilities, Identifiers.HasComponent, Identifiers.ServerType_ServerCapabilities_ModellingRules.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerType_ServerCapabilities, Identifiers.HasComponent, Identifiers.ServerType_ServerCapabilities_AggregateFunctions.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerType_ServerCapabilities, Identifiers.HasTypeDefinition, Identifiers.ServerCapabilitiesType.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerType_ServerCapabilities, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerType_ServerCapabilities, Identifiers.HasComponent, Identifiers.ServerType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode152() {
        ServerDiagnosticsTypeNode node = new ServerDiagnosticsTypeNode(this.context, Identifiers.ServerType_ServerDiagnostics, new QualifiedName(0, "ServerDiagnostics"), new LocalizedText("en", "ServerDiagnostics"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.ServerType_ServerDiagnostics, Identifiers.HasComponent, Identifiers.ServerType_ServerDiagnostics_ServerDiagnosticsSummary.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerType_ServerDiagnostics, Identifiers.HasComponent, Identifiers.ServerType_ServerDiagnostics_SubscriptionDiagnosticsArray.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerType_ServerDiagnostics, Identifiers.HasComponent, Identifiers.ServerType_ServerDiagnostics_SessionsDiagnosticsSummary.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerType_ServerDiagnostics, Identifiers.HasProperty, Identifiers.ServerType_ServerDiagnostics_EnabledFlag.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerType_ServerDiagnostics, Identifiers.HasTypeDefinition, Identifiers.ServerDiagnosticsType.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerType_ServerDiagnostics, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerType_ServerDiagnostics, Identifiers.HasComponent, Identifiers.ServerType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode153() {
        VendorServerInfoTypeNode node = new VendorServerInfoTypeNode(this.context, Identifiers.ServerType_VendorServerInfo, new QualifiedName(0, "VendorServerInfo"), new LocalizedText("en", "VendorServerInfo"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.ServerType_VendorServerInfo, Identifiers.HasTypeDefinition, Identifiers.VendorServerInfoType.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerType_VendorServerInfo, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerType_VendorServerInfo, Identifiers.HasComponent, Identifiers.ServerType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode154() {
        ServerRedundancyTypeNode node = new ServerRedundancyTypeNode(this.context, Identifiers.ServerType_ServerRedundancy, new QualifiedName(0, "ServerRedundancy"), new LocalizedText("en", "ServerRedundancy"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.ServerType_ServerRedundancy, Identifiers.HasProperty, Identifiers.ServerType_ServerRedundancy_RedundancySupport.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerType_ServerRedundancy, Identifiers.HasTypeDefinition, Identifiers.ServerRedundancyType.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerType_ServerRedundancy, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerType_ServerRedundancy, Identifiers.HasComponent, Identifiers.ServerType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode155() {
        FolderTypeNode node = new FolderTypeNode(this.context, Identifiers.ServerCapabilitiesType_ModellingRules, new QualifiedName(0, "ModellingRules"), new LocalizedText("en", "ModellingRules"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.ServerCapabilitiesType_ModellingRules, Identifiers.HasTypeDefinition, Identifiers.FolderType.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerCapabilitiesType_ModellingRules, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerCapabilitiesType_ModellingRules, Identifiers.HasComponent, Identifiers.ServerCapabilitiesType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode156() {
        ServerTypeNode node = new ServerTypeNode(this.context, Identifiers.Server, new QualifiedName(0, "Server"), new LocalizedText("en", "Server"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(1));
        node.addReference(new Reference(Identifiers.Server, Identifiers.HasProperty, Identifiers.Server_ServerArray.expanded(), true));
        node.addReference(new Reference(Identifiers.Server, Identifiers.HasProperty, Identifiers.Server_NamespaceArray.expanded(), true));
        node.addReference(new Reference(Identifiers.Server, Identifiers.HasComponent, Identifiers.Server_ServerStatus.expanded(), true));
        node.addReference(new Reference(Identifiers.Server, Identifiers.HasProperty, Identifiers.Server_ServiceLevel.expanded(), true));
        node.addReference(new Reference(Identifiers.Server, Identifiers.HasProperty, Identifiers.Server_Auditing.expanded(), true));
        node.addReference(new Reference(Identifiers.Server, Identifiers.HasProperty, Identifiers.Server_EstimatedReturnTime.expanded(), true));
        node.addReference(new Reference(Identifiers.Server, Identifiers.HasComponent, Identifiers.Server_ServerCapabilities.expanded(), true));
        node.addReference(new Reference(Identifiers.Server, Identifiers.HasComponent, Identifiers.Server_ServerDiagnostics.expanded(), true));
        node.addReference(new Reference(Identifiers.Server, Identifiers.HasComponent, Identifiers.Server_VendorServerInfo.expanded(), true));
        node.addReference(new Reference(Identifiers.Server, Identifiers.HasComponent, Identifiers.Server_ServerRedundancy.expanded(), true));
        node.addReference(new Reference(Identifiers.Server, Identifiers.HasComponent, Identifiers.Server_Namespaces.expanded(), true));
        node.addReference(new Reference(Identifiers.Server, Identifiers.HasComponent, Identifiers.Server_GetMonitoredItems.expanded(), true));
        node.addReference(new Reference(Identifiers.Server, Identifiers.HasComponent, Identifiers.Server_ResendData.expanded(), true));
        node.addReference(new Reference(Identifiers.Server, Identifiers.HasComponent, Identifiers.Server_SetSubscriptionDurable.expanded(), true));
        node.addReference(new Reference(Identifiers.Server, Identifiers.HasComponent, Identifiers.Server_RequestServerStateChange.expanded(), true));
        node.addReference(new Reference(Identifiers.Server, Identifiers.Organizes, Identifiers.ObjectsFolder.expanded(), false));
        node.addReference(new Reference(Identifiers.Server, Identifiers.HasTypeDefinition, Identifiers.ServerType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode157() {
        ServerCapabilitiesTypeNode node = new ServerCapabilitiesTypeNode(this.context, Identifiers.Server_ServerCapabilities, new QualifiedName(0, "ServerCapabilities"), new LocalizedText("en", "ServerCapabilities"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.Server_ServerCapabilities, Identifiers.HasProperty, Identifiers.Server_ServerCapabilities_ServerProfileArray.expanded(), true));
        node.addReference(new Reference(Identifiers.Server_ServerCapabilities, Identifiers.HasProperty, Identifiers.Server_ServerCapabilities_LocaleIdArray.expanded(), true));
        node.addReference(new Reference(Identifiers.Server_ServerCapabilities, Identifiers.HasProperty, Identifiers.Server_ServerCapabilities_MinSupportedSampleRate.expanded(), true));
        node.addReference(new Reference(Identifiers.Server_ServerCapabilities, Identifiers.HasProperty, Identifiers.Server_ServerCapabilities_MaxBrowseContinuationPoints.expanded(), true));
        node.addReference(new Reference(Identifiers.Server_ServerCapabilities, Identifiers.HasProperty, Identifiers.Server_ServerCapabilities_MaxQueryContinuationPoints.expanded(), true));
        node.addReference(new Reference(Identifiers.Server_ServerCapabilities, Identifiers.HasProperty, Identifiers.Server_ServerCapabilities_MaxHistoryContinuationPoints.expanded(), true));
        node.addReference(new Reference(Identifiers.Server_ServerCapabilities, Identifiers.HasProperty, Identifiers.Server_ServerCapabilities_SoftwareCertificates.expanded(), true));
        node.addReference(new Reference(Identifiers.Server_ServerCapabilities, Identifiers.HasProperty, Identifiers.Server_ServerCapabilities_MaxArrayLength.expanded(), true));
        node.addReference(new Reference(Identifiers.Server_ServerCapabilities, Identifiers.HasProperty, Identifiers.Server_ServerCapabilities_MaxStringLength.expanded(), true));
        node.addReference(new Reference(Identifiers.Server_ServerCapabilities, Identifiers.HasProperty, Identifiers.Server_ServerCapabilities_MaxByteStringLength.expanded(), true));
        node.addReference(new Reference(Identifiers.Server_ServerCapabilities, Identifiers.HasComponent, Identifiers.Server_ServerCapabilities_OperationLimits.expanded(), true));
        node.addReference(new Reference(Identifiers.Server_ServerCapabilities, Identifiers.HasComponent, Identifiers.Server_ServerCapabilities_ModellingRules.expanded(), true));
        node.addReference(new Reference(Identifiers.Server_ServerCapabilities, Identifiers.HasComponent, Identifiers.Server_ServerCapabilities_AggregateFunctions.expanded(), true));
        node.addReference(new Reference(Identifiers.Server_ServerCapabilities, Identifiers.HasTypeDefinition, Identifiers.ServerCapabilitiesType.expanded(), true));
        node.addReference(new Reference(Identifiers.Server_ServerCapabilities, Identifiers.HasComponent, Identifiers.Server.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode158() {
        ServerDiagnosticsTypeNode node = new ServerDiagnosticsTypeNode(this.context, Identifiers.Server_ServerDiagnostics, new QualifiedName(0, "ServerDiagnostics"), new LocalizedText("en", "ServerDiagnostics"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.Server_ServerDiagnostics, Identifiers.HasComponent, Identifiers.Server_ServerDiagnostics_ServerDiagnosticsSummary.expanded(), true));
        node.addReference(new Reference(Identifiers.Server_ServerDiagnostics, Identifiers.HasComponent, Identifiers.Server_ServerDiagnostics_SamplingIntervalDiagnosticsArray.expanded(), true));
        node.addReference(new Reference(Identifiers.Server_ServerDiagnostics, Identifiers.HasComponent, Identifiers.Server_ServerDiagnostics_SubscriptionDiagnosticsArray.expanded(), true));
        node.addReference(new Reference(Identifiers.Server_ServerDiagnostics, Identifiers.HasComponent, Identifiers.Server_ServerDiagnostics_SessionsDiagnosticsSummary.expanded(), true));
        node.addReference(new Reference(Identifiers.Server_ServerDiagnostics, Identifiers.HasProperty, Identifiers.Server_ServerDiagnostics_EnabledFlag.expanded(), true));
        node.addReference(new Reference(Identifiers.Server_ServerDiagnostics, Identifiers.HasTypeDefinition, Identifiers.ServerDiagnosticsType.expanded(), true));
        node.addReference(new Reference(Identifiers.Server_ServerDiagnostics, Identifiers.HasComponent, Identifiers.Server.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode159() {
        VendorServerInfoTypeNode node = new VendorServerInfoTypeNode(this.context, Identifiers.Server_VendorServerInfo, new QualifiedName(0, "VendorServerInfo"), new LocalizedText("en", "VendorServerInfo"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.Server_VendorServerInfo, Identifiers.HasTypeDefinition, Identifiers.VendorServerInfoType.expanded(), true));
        node.addReference(new Reference(Identifiers.Server_VendorServerInfo, Identifiers.HasComponent, Identifiers.Server.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode160() {
        ServerRedundancyTypeNode node = new ServerRedundancyTypeNode(this.context, Identifiers.Server_ServerRedundancy, new QualifiedName(0, "ServerRedundancy"), new LocalizedText("en", "ServerRedundancy"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.Server_ServerRedundancy, Identifiers.HasProperty, Identifiers.Server_ServerRedundancy_RedundancySupport.expanded(), true));
        node.addReference(new Reference(Identifiers.Server_ServerRedundancy, Identifiers.HasProperty, Identifiers.Server_ServerRedundancy_CurrentServerId.expanded(), true));
        node.addReference(new Reference(Identifiers.Server_ServerRedundancy, Identifiers.HasProperty, Identifiers.Server_ServerRedundancy_RedundantServerArray.expanded(), true));
        node.addReference(new Reference(Identifiers.Server_ServerRedundancy, Identifiers.HasProperty, Identifiers.Server_ServerRedundancy_ServerUriArray.expanded(), true));
        node.addReference(new Reference(Identifiers.Server_ServerRedundancy, Identifiers.HasProperty, Identifiers.Server_ServerRedundancy_ServerNetworkGroups.expanded(), true));
        node.addReference(new Reference(Identifiers.Server_ServerRedundancy, Identifiers.HasTypeDefinition, Identifiers.ServerRedundancyType.expanded(), true));
        node.addReference(new Reference(Identifiers.Server_ServerRedundancy, Identifiers.HasComponent, Identifiers.Server.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode161() {
        AggregateFunctionTypeNode node = new AggregateFunctionTypeNode(this.context, Identifiers.AggregateFunction_Interpolative, new QualifiedName(0, "Interpolative"), new LocalizedText("en", "Interpolative"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.AggregateFunction_Interpolative, Identifiers.HasTypeDefinition, Identifiers.AggregateFunctionType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode162() {
        AggregateFunctionTypeNode node = new AggregateFunctionTypeNode(this.context, Identifiers.AggregateFunction_Average, new QualifiedName(0, "Average"), new LocalizedText("en", "Average"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.AggregateFunction_Average, Identifiers.HasTypeDefinition, Identifiers.AggregateFunctionType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode163() {
        AggregateFunctionTypeNode node = new AggregateFunctionTypeNode(this.context, Identifiers.AggregateFunction_TimeAverage, new QualifiedName(0, "TimeAverage"), new LocalizedText("en", "TimeAverage"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.AggregateFunction_TimeAverage, Identifiers.HasTypeDefinition, Identifiers.AggregateFunctionType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode164() {
        AggregateFunctionTypeNode node = new AggregateFunctionTypeNode(this.context, Identifiers.AggregateFunction_Total, new QualifiedName(0, "Total"), new LocalizedText("en", "Total"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.AggregateFunction_Total, Identifiers.HasTypeDefinition, Identifiers.AggregateFunctionType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode165() {
        AggregateFunctionTypeNode node = new AggregateFunctionTypeNode(this.context, Identifiers.AggregateFunction_Minimum, new QualifiedName(0, "Minimum"), new LocalizedText("en", "Minimum"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.AggregateFunction_Minimum, Identifiers.HasTypeDefinition, Identifiers.AggregateFunctionType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode166() {
        AggregateFunctionTypeNode node = new AggregateFunctionTypeNode(this.context, Identifiers.AggregateFunction_Maximum, new QualifiedName(0, "Maximum"), new LocalizedText("en", "Maximum"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.AggregateFunction_Maximum, Identifiers.HasTypeDefinition, Identifiers.AggregateFunctionType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode167() {
        AggregateFunctionTypeNode node = new AggregateFunctionTypeNode(this.context, Identifiers.AggregateFunction_MinimumActualTime, new QualifiedName(0, "MinimumActualTime"), new LocalizedText("en", "MinimumActualTime"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.AggregateFunction_MinimumActualTime, Identifiers.HasTypeDefinition, Identifiers.AggregateFunctionType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode168() {
        AggregateFunctionTypeNode node = new AggregateFunctionTypeNode(this.context, Identifiers.AggregateFunction_MaximumActualTime, new QualifiedName(0, "MaximumActualTime"), new LocalizedText("en", "MaximumActualTime"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.AggregateFunction_MaximumActualTime, Identifiers.HasTypeDefinition, Identifiers.AggregateFunctionType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode169() {
        AggregateFunctionTypeNode node = new AggregateFunctionTypeNode(this.context, Identifiers.AggregateFunction_Range, new QualifiedName(0, "Range"), new LocalizedText("en", "Range"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.AggregateFunction_Range, Identifiers.HasTypeDefinition, Identifiers.AggregateFunctionType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode170() {
        AggregateFunctionTypeNode node = new AggregateFunctionTypeNode(this.context, Identifiers.AggregateFunction_AnnotationCount, new QualifiedName(0, "AnnotationCount"), new LocalizedText("en", "AnnotationCount"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.AggregateFunction_AnnotationCount, Identifiers.HasTypeDefinition, Identifiers.AggregateFunctionType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode171() {
        AggregateFunctionTypeNode node = new AggregateFunctionTypeNode(this.context, Identifiers.AggregateFunction_Count, new QualifiedName(0, "Count"), new LocalizedText("en", "Count"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.AggregateFunction_Count, Identifiers.HasTypeDefinition, Identifiers.AggregateFunctionType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode172() {
        AggregateFunctionTypeNode node = new AggregateFunctionTypeNode(this.context, Identifiers.AggregateFunction_NumberOfTransitions, new QualifiedName(0, "NumberOfTransitions"), new LocalizedText("en", "NumberOfTransitions"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.AggregateFunction_NumberOfTransitions, Identifiers.HasTypeDefinition, Identifiers.AggregateFunctionType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode173() {
        AggregateFunctionTypeNode node = new AggregateFunctionTypeNode(this.context, Identifiers.AggregateFunction_Start, new QualifiedName(0, "Start"), new LocalizedText("en", "Start"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.AggregateFunction_Start, Identifiers.HasTypeDefinition, Identifiers.AggregateFunctionType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode174() {
        AggregateFunctionTypeNode node = new AggregateFunctionTypeNode(this.context, Identifiers.AggregateFunction_End, new QualifiedName(0, "End"), new LocalizedText("en", "End"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.AggregateFunction_End, Identifiers.HasTypeDefinition, Identifiers.AggregateFunctionType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode175() {
        AggregateFunctionTypeNode node = new AggregateFunctionTypeNode(this.context, Identifiers.AggregateFunction_Delta, new QualifiedName(0, "Delta"), new LocalizedText("en", "Delta"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.AggregateFunction_Delta, Identifiers.HasTypeDefinition, Identifiers.AggregateFunctionType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode176() {
        AggregateFunctionTypeNode node = new AggregateFunctionTypeNode(this.context, Identifiers.AggregateFunction_DurationGood, new QualifiedName(0, "DurationGood"), new LocalizedText("en", "DurationGood"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.AggregateFunction_DurationGood, Identifiers.HasTypeDefinition, Identifiers.AggregateFunctionType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode177() {
        AggregateFunctionTypeNode node = new AggregateFunctionTypeNode(this.context, Identifiers.AggregateFunction_DurationBad, new QualifiedName(0, "DurationBad"), new LocalizedText("en", "DurationBad"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.AggregateFunction_DurationBad, Identifiers.HasTypeDefinition, Identifiers.AggregateFunctionType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode178() {
        AggregateFunctionTypeNode node = new AggregateFunctionTypeNode(this.context, Identifiers.AggregateFunction_PercentGood, new QualifiedName(0, "PercentGood"), new LocalizedText("en", "PercentGood"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.AggregateFunction_PercentGood, Identifiers.HasTypeDefinition, Identifiers.AggregateFunctionType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode179() {
        AggregateFunctionTypeNode node = new AggregateFunctionTypeNode(this.context, Identifiers.AggregateFunction_PercentBad, new QualifiedName(0, "PercentBad"), new LocalizedText("en", "PercentBad"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.AggregateFunction_PercentBad, Identifiers.HasTypeDefinition, Identifiers.AggregateFunctionType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode180() {
        AggregateFunctionTypeNode node = new AggregateFunctionTypeNode(this.context, Identifiers.AggregateFunction_WorstQuality, new QualifiedName(0, "WorstQuality"), new LocalizedText("en", "WorstQuality"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.AggregateFunction_WorstQuality, Identifiers.HasTypeDefinition, Identifiers.AggregateFunctionType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode181() {
        StateTypeNode node = new StateTypeNode(this.context, Identifiers.ProgramStateMachineType_Ready, new QualifiedName(0, "Ready"), new LocalizedText("en", "Ready"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_Ready, Identifiers.HasProperty, Identifiers.ProgramStateMachineType_Ready_StateNumber.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_Ready, Identifiers.ToState, Identifiers.ProgramStateMachineType_HaltedToReady.expanded(), false));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_Ready, Identifiers.FromState, Identifiers.ProgramStateMachineType_ReadyToRunning.expanded(), false));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_Ready, Identifiers.ToState, Identifiers.ProgramStateMachineType_RunningToReady.expanded(), false));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_Ready, Identifiers.ToState, Identifiers.ProgramStateMachineType_SuspendedToReady.expanded(), false));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_Ready, Identifiers.FromState, Identifiers.ProgramStateMachineType_ReadyToHalted.expanded(), false));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_Ready, Identifiers.HasTypeDefinition, Identifiers.StateType.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_Ready, Identifiers.HasComponent, Identifiers.ProgramStateMachineType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode182() {
        StateTypeNode node = new StateTypeNode(this.context, Identifiers.ProgramStateMachineType_Running, new QualifiedName(0, "Running"), new LocalizedText("en", "Running"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_Running, Identifiers.HasProperty, Identifiers.ProgramStateMachineType_Running_StateNumber.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_Running, Identifiers.ToState, Identifiers.ProgramStateMachineType_ReadyToRunning.expanded(), false));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_Running, Identifiers.FromState, Identifiers.ProgramStateMachineType_RunningToHalted.expanded(), false));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_Running, Identifiers.FromState, Identifiers.ProgramStateMachineType_RunningToReady.expanded(), false));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_Running, Identifiers.FromState, Identifiers.ProgramStateMachineType_RunningToSuspended.expanded(), false));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_Running, Identifiers.ToState, Identifiers.ProgramStateMachineType_SuspendedToRunning.expanded(), false));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_Running, Identifiers.HasTypeDefinition, Identifiers.StateType.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_Running, Identifiers.HasComponent, Identifiers.ProgramStateMachineType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode183() {
        StateTypeNode node = new StateTypeNode(this.context, Identifiers.ProgramStateMachineType_Suspended, new QualifiedName(0, "Suspended"), new LocalizedText("en", "Suspended"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_Suspended, Identifiers.HasProperty, Identifiers.ProgramStateMachineType_Suspended_StateNumber.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_Suspended, Identifiers.ToState, Identifiers.ProgramStateMachineType_RunningToSuspended.expanded(), false));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_Suspended, Identifiers.FromState, Identifiers.ProgramStateMachineType_SuspendedToRunning.expanded(), false));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_Suspended, Identifiers.FromState, Identifiers.ProgramStateMachineType_SuspendedToHalted.expanded(), false));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_Suspended, Identifiers.FromState, Identifiers.ProgramStateMachineType_SuspendedToReady.expanded(), false));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_Suspended, Identifiers.HasTypeDefinition, Identifiers.StateType.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_Suspended, Identifiers.HasComponent, Identifiers.ProgramStateMachineType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode184() {
        StateTypeNode node = new StateTypeNode(this.context, Identifiers.ProgramStateMachineType_Halted, new QualifiedName(0, "Halted"), new LocalizedText("en", "Halted"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_Halted, Identifiers.HasProperty, Identifiers.ProgramStateMachineType_Halted_StateNumber.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_Halted, Identifiers.FromState, Identifiers.ProgramStateMachineType_HaltedToReady.expanded(), false));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_Halted, Identifiers.ToState, Identifiers.ProgramStateMachineType_RunningToHalted.expanded(), false));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_Halted, Identifiers.ToState, Identifiers.ProgramStateMachineType_SuspendedToHalted.expanded(), false));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_Halted, Identifiers.ToState, Identifiers.ProgramStateMachineType_ReadyToHalted.expanded(), false));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_Halted, Identifiers.HasTypeDefinition, Identifiers.StateType.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_Halted, Identifiers.HasComponent, Identifiers.ProgramStateMachineType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode185() {
        TransitionTypeNode node = new TransitionTypeNode(this.context, Identifiers.ProgramStateMachineType_HaltedToReady, new QualifiedName(0, "HaltedToReady"), new LocalizedText("en", "HaltedToReady"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_HaltedToReady, Identifiers.HasProperty, Identifiers.ProgramStateMachineType_HaltedToReady_TransitionNumber.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_HaltedToReady, Identifiers.FromState, Identifiers.ProgramStateMachineType_Halted.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_HaltedToReady, Identifiers.ToState, Identifiers.ProgramStateMachineType_Ready.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_HaltedToReady, Identifiers.HasCause, Identifiers.ProgramStateMachineType_Reset.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_HaltedToReady, Identifiers.HasEffect, Identifiers.ProgramTransitionEventType.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_HaltedToReady, Identifiers.HasTypeDefinition, Identifiers.TransitionType.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_HaltedToReady, Identifiers.HasComponent, Identifiers.ProgramStateMachineType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode186() {
        TransitionTypeNode node = new TransitionTypeNode(this.context, Identifiers.ProgramStateMachineType_ReadyToRunning, new QualifiedName(0, "ReadyToRunning"), new LocalizedText("en", "ReadyToRunning"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_ReadyToRunning, Identifiers.HasProperty, Identifiers.ProgramStateMachineType_ReadyToRunning_TransitionNumber.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_ReadyToRunning, Identifiers.FromState, Identifiers.ProgramStateMachineType_Ready.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_ReadyToRunning, Identifiers.ToState, Identifiers.ProgramStateMachineType_Running.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_ReadyToRunning, Identifiers.HasCause, Identifiers.ProgramStateMachineType_Start.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_ReadyToRunning, Identifiers.HasEffect, Identifiers.ProgramTransitionEventType.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_ReadyToRunning, Identifiers.HasTypeDefinition, Identifiers.TransitionType.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_ReadyToRunning, Identifiers.HasComponent, Identifiers.ProgramStateMachineType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode187() {
        TransitionTypeNode node = new TransitionTypeNode(this.context, Identifiers.ProgramStateMachineType_RunningToHalted, new QualifiedName(0, "RunningToHalted"), new LocalizedText("en", "RunningToHalted"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_RunningToHalted, Identifiers.HasProperty, Identifiers.ProgramStateMachineType_RunningToHalted_TransitionNumber.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_RunningToHalted, Identifiers.FromState, Identifiers.ProgramStateMachineType_Running.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_RunningToHalted, Identifiers.ToState, Identifiers.ProgramStateMachineType_Halted.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_RunningToHalted, Identifiers.HasCause, Identifiers.ProgramStateMachineType_Halt.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_RunningToHalted, Identifiers.HasEffect, Identifiers.ProgramTransitionEventType.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_RunningToHalted, Identifiers.HasTypeDefinition, Identifiers.TransitionType.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_RunningToHalted, Identifiers.HasComponent, Identifiers.ProgramStateMachineType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode188() {
        TransitionTypeNode node = new TransitionTypeNode(this.context, Identifiers.ProgramStateMachineType_RunningToReady, new QualifiedName(0, "RunningToReady"), new LocalizedText("en", "RunningToReady"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_RunningToReady, Identifiers.HasProperty, Identifiers.ProgramStateMachineType_RunningToReady_TransitionNumber.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_RunningToReady, Identifiers.FromState, Identifiers.ProgramStateMachineType_Running.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_RunningToReady, Identifiers.ToState, Identifiers.ProgramStateMachineType_Ready.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_RunningToReady, Identifiers.HasEffect, Identifiers.ProgramTransitionEventType.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_RunningToReady, Identifiers.HasTypeDefinition, Identifiers.TransitionType.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_RunningToReady, Identifiers.HasComponent, Identifiers.ProgramStateMachineType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode189() {
        TransitionTypeNode node = new TransitionTypeNode(this.context, Identifiers.ProgramStateMachineType_RunningToSuspended, new QualifiedName(0, "RunningToSuspended"), new LocalizedText("en", "RunningToSuspended"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_RunningToSuspended, Identifiers.HasProperty, Identifiers.ProgramStateMachineType_RunningToSuspended_TransitionNumber.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_RunningToSuspended, Identifiers.FromState, Identifiers.ProgramStateMachineType_Running.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_RunningToSuspended, Identifiers.ToState, Identifiers.ProgramStateMachineType_Suspended.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_RunningToSuspended, Identifiers.HasCause, Identifiers.ProgramStateMachineType_Suspend.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_RunningToSuspended, Identifiers.HasEffect, Identifiers.ProgramTransitionEventType.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_RunningToSuspended, Identifiers.HasTypeDefinition, Identifiers.TransitionType.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_RunningToSuspended, Identifiers.HasComponent, Identifiers.ProgramStateMachineType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode190() {
        TransitionTypeNode node = new TransitionTypeNode(this.context, Identifiers.ProgramStateMachineType_SuspendedToRunning, new QualifiedName(0, "SuspendedToRunning"), new LocalizedText("en", "SuspendedToRunning"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_SuspendedToRunning, Identifiers.HasProperty, Identifiers.ProgramStateMachineType_SuspendedToRunning_TransitionNumber.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_SuspendedToRunning, Identifiers.FromState, Identifiers.ProgramStateMachineType_Suspended.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_SuspendedToRunning, Identifiers.ToState, Identifiers.ProgramStateMachineType_Running.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_SuspendedToRunning, Identifiers.HasCause, Identifiers.ProgramStateMachineType_Resume.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_SuspendedToRunning, Identifiers.HasEffect, Identifiers.ProgramTransitionEventType.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_SuspendedToRunning, Identifiers.HasTypeDefinition, Identifiers.TransitionType.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_SuspendedToRunning, Identifiers.HasComponent, Identifiers.ProgramStateMachineType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode191() {
        TransitionTypeNode node = new TransitionTypeNode(this.context, Identifiers.ProgramStateMachineType_SuspendedToHalted, new QualifiedName(0, "SuspendedToHalted"), new LocalizedText("en", "SuspendedToHalted"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_SuspendedToHalted, Identifiers.HasProperty, Identifiers.ProgramStateMachineType_SuspendedToHalted_TransitionNumber.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_SuspendedToHalted, Identifiers.FromState, Identifiers.ProgramStateMachineType_Suspended.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_SuspendedToHalted, Identifiers.ToState, Identifiers.ProgramStateMachineType_Halted.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_SuspendedToHalted, Identifiers.HasCause, Identifiers.ProgramStateMachineType_Halt.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_SuspendedToHalted, Identifiers.HasEffect, Identifiers.ProgramTransitionEventType.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_SuspendedToHalted, Identifiers.HasTypeDefinition, Identifiers.TransitionType.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_SuspendedToHalted, Identifiers.HasComponent, Identifiers.ProgramStateMachineType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode192() {
        TransitionTypeNode node = new TransitionTypeNode(this.context, Identifiers.ProgramStateMachineType_SuspendedToReady, new QualifiedName(0, "SuspendedToReady"), new LocalizedText("en", "SuspendedToReady"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_SuspendedToReady, Identifiers.HasProperty, Identifiers.ProgramStateMachineType_SuspendedToReady_TransitionNumber.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_SuspendedToReady, Identifiers.FromState, Identifiers.ProgramStateMachineType_Suspended.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_SuspendedToReady, Identifiers.ToState, Identifiers.ProgramStateMachineType_Ready.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_SuspendedToReady, Identifiers.HasEffect, Identifiers.ProgramTransitionEventType.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_SuspendedToReady, Identifiers.HasTypeDefinition, Identifiers.TransitionType.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_SuspendedToReady, Identifiers.HasComponent, Identifiers.ProgramStateMachineType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode193() {
        TransitionTypeNode node = new TransitionTypeNode(this.context, Identifiers.ProgramStateMachineType_ReadyToHalted, new QualifiedName(0, "ReadyToHalted"), new LocalizedText("en", "ReadyToHalted"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_ReadyToHalted, Identifiers.HasProperty, Identifiers.ProgramStateMachineType_ReadyToHalted_TransitionNumber.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_ReadyToHalted, Identifiers.FromState, Identifiers.ProgramStateMachineType_Ready.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_ReadyToHalted, Identifiers.ToState, Identifiers.ProgramStateMachineType_Halted.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_ReadyToHalted, Identifiers.HasCause, Identifiers.ProgramStateMachineType_Halt.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_ReadyToHalted, Identifiers.HasEffect, Identifiers.ProgramTransitionEventType.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_ReadyToHalted, Identifiers.HasTypeDefinition, Identifiers.TransitionType.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_ReadyToHalted, Identifiers.HasComponent, Identifiers.ProgramStateMachineType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode194() {
        SessionsDiagnosticsSummaryTypeNode node = new SessionsDiagnosticsSummaryTypeNode(this.context, Identifiers.ServerDiagnosticsType_SessionsDiagnosticsSummary, new QualifiedName(0, "SessionsDiagnosticsSummary"), new LocalizedText("en", "SessionsDiagnosticsSummary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.ServerDiagnosticsType_SessionsDiagnosticsSummary, Identifiers.HasComponent, Identifiers.ServerDiagnosticsType_SessionsDiagnosticsSummary_SessionDiagnosticsArray.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerDiagnosticsType_SessionsDiagnosticsSummary, Identifiers.HasComponent, Identifiers.ServerDiagnosticsType_SessionsDiagnosticsSummary_SessionSecurityDiagnosticsArray.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerDiagnosticsType_SessionsDiagnosticsSummary, Identifiers.HasTypeDefinition, Identifiers.SessionsDiagnosticsSummaryType.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerDiagnosticsType_SessionsDiagnosticsSummary, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerDiagnosticsType_SessionsDiagnosticsSummary, Identifiers.HasComponent, Identifiers.ServerDiagnosticsType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode195() {
        FolderTypeNode node = new FolderTypeNode(this.context, Identifiers.ServerCapabilitiesType_AggregateFunctions, new QualifiedName(0, "AggregateFunctions"), new LocalizedText("en", "AggregateFunctions"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.ServerCapabilitiesType_AggregateFunctions, Identifiers.HasTypeDefinition, Identifiers.FolderType.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerCapabilitiesType_AggregateFunctions, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerCapabilitiesType_AggregateFunctions, Identifiers.HasComponent, Identifiers.ServerCapabilitiesType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode196() {
        NamespaceMetadataTypeNode node = new NamespaceMetadataTypeNode(this.context, Identifiers.Server_Namespaces_OPCUANamespaceUri, new QualifiedName(0, "http://opcfoundation.org/UA/"), new LocalizedText("en", "http://opcfoundation.org/UA/"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.Server_Namespaces_OPCUANamespaceUri, Identifiers.HasProperty, Identifiers.Server_Namespaces_OPCUANamespaceUri_NamespaceUri.expanded(), true));
        node.addReference(new Reference(Identifiers.Server_Namespaces_OPCUANamespaceUri, Identifiers.HasProperty, Identifiers.Server_Namespaces_OPCUANamespaceUri_NamespaceVersion.expanded(), true));
        node.addReference(new Reference(Identifiers.Server_Namespaces_OPCUANamespaceUri, Identifiers.HasProperty, Identifiers.Server_Namespaces_OPCUANamespaceUri_NamespacePublicationDate.expanded(), true));
        node.addReference(new Reference(Identifiers.Server_Namespaces_OPCUANamespaceUri, Identifiers.HasProperty, Identifiers.Server_Namespaces_OPCUANamespaceUri_IsNamespaceSubset.expanded(), true));
        node.addReference(new Reference(Identifiers.Server_Namespaces_OPCUANamespaceUri, Identifiers.HasProperty, Identifiers.Server_Namespaces_OPCUANamespaceUri_StaticNodeIdTypes.expanded(), true));
        node.addReference(new Reference(Identifiers.Server_Namespaces_OPCUANamespaceUri, Identifiers.HasProperty, Identifiers.Server_Namespaces_OPCUANamespaceUri_StaticNumericNodeIdRange.expanded(), true));
        node.addReference(new Reference(Identifiers.Server_Namespaces_OPCUANamespaceUri, Identifiers.HasProperty, Identifiers.Server_Namespaces_OPCUANamespaceUri_StaticStringNodeIdPattern.expanded(), true));
        node.addReference(new Reference(Identifiers.Server_Namespaces_OPCUANamespaceUri, Identifiers.HasTypeDefinition, Identifiers.NamespaceMetadataType.expanded(), true));
        node.addReference(new Reference(Identifiers.Server_Namespaces_OPCUANamespaceUri, Identifiers.HasComponent, Identifiers.Server_Namespaces.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode197() {
        StateTypeNode node = new StateTypeNode(this.context, Identifiers.ShelvedStateMachineType_Unshelved, new QualifiedName(0, "Unshelved"), new LocalizedText("en", "Unshelved"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.ShelvedStateMachineType_Unshelved, Identifiers.HasProperty, Identifiers.ShelvedStateMachineType_Unshelved_StateNumber.expanded(), true));
        node.addReference(new Reference(Identifiers.ShelvedStateMachineType_Unshelved, Identifiers.FromState, Identifiers.ShelvedStateMachineType_UnshelvedToTimedShelved.expanded(), false));
        node.addReference(new Reference(Identifiers.ShelvedStateMachineType_Unshelved, Identifiers.FromState, Identifiers.ShelvedStateMachineType_UnshelvedToOneShotShelved.expanded(), false));
        node.addReference(new Reference(Identifiers.ShelvedStateMachineType_Unshelved, Identifiers.ToState, Identifiers.ShelvedStateMachineType_TimedShelvedToUnshelved.expanded(), false));
        node.addReference(new Reference(Identifiers.ShelvedStateMachineType_Unshelved, Identifiers.ToState, Identifiers.ShelvedStateMachineType_OneShotShelvedToUnshelved.expanded(), false));
        node.addReference(new Reference(Identifiers.ShelvedStateMachineType_Unshelved, Identifiers.HasTypeDefinition, Identifiers.StateType.expanded(), true));
        node.addReference(new Reference(Identifiers.ShelvedStateMachineType_Unshelved, Identifiers.HasComponent, Identifiers.ShelvedStateMachineType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode198() {
        StateTypeNode node = new StateTypeNode(this.context, Identifiers.ShelvedStateMachineType_TimedShelved, new QualifiedName(0, "TimedShelved"), new LocalizedText("en", "TimedShelved"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.ShelvedStateMachineType_TimedShelved, Identifiers.HasProperty, Identifiers.ShelvedStateMachineType_TimedShelved_StateNumber.expanded(), true));
        node.addReference(new Reference(Identifiers.ShelvedStateMachineType_TimedShelved, Identifiers.ToState, Identifiers.ShelvedStateMachineType_UnshelvedToTimedShelved.expanded(), false));
        node.addReference(new Reference(Identifiers.ShelvedStateMachineType_TimedShelved, Identifiers.FromState, Identifiers.ShelvedStateMachineType_TimedShelvedToUnshelved.expanded(), false));
        node.addReference(new Reference(Identifiers.ShelvedStateMachineType_TimedShelved, Identifiers.FromState, Identifiers.ShelvedStateMachineType_TimedShelvedToOneShotShelved.expanded(), false));
        node.addReference(new Reference(Identifiers.ShelvedStateMachineType_TimedShelved, Identifiers.ToState, Identifiers.ShelvedStateMachineType_OneShotShelvedToTimedShelved.expanded(), false));
        node.addReference(new Reference(Identifiers.ShelvedStateMachineType_TimedShelved, Identifiers.HasTypeDefinition, Identifiers.StateType.expanded(), true));
        node.addReference(new Reference(Identifiers.ShelvedStateMachineType_TimedShelved, Identifiers.HasComponent, Identifiers.ShelvedStateMachineType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode199() {
        StateTypeNode node = new StateTypeNode(this.context, Identifiers.ShelvedStateMachineType_OneShotShelved, new QualifiedName(0, "OneShotShelved"), new LocalizedText("en", "OneShotShelved"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.ShelvedStateMachineType_OneShotShelved, Identifiers.HasProperty, Identifiers.ShelvedStateMachineType_OneShotShelved_StateNumber.expanded(), true));
        node.addReference(new Reference(Identifiers.ShelvedStateMachineType_OneShotShelved, Identifiers.ToState, Identifiers.ShelvedStateMachineType_UnshelvedToOneShotShelved.expanded(), false));
        node.addReference(new Reference(Identifiers.ShelvedStateMachineType_OneShotShelved, Identifiers.ToState, Identifiers.ShelvedStateMachineType_TimedShelvedToOneShotShelved.expanded(), false));
        node.addReference(new Reference(Identifiers.ShelvedStateMachineType_OneShotShelved, Identifiers.FromState, Identifiers.ShelvedStateMachineType_OneShotShelvedToUnshelved.expanded(), false));
        node.addReference(new Reference(Identifiers.ShelvedStateMachineType_OneShotShelved, Identifiers.FromState, Identifiers.ShelvedStateMachineType_OneShotShelvedToTimedShelved.expanded(), false));
        node.addReference(new Reference(Identifiers.ShelvedStateMachineType_OneShotShelved, Identifiers.HasTypeDefinition, Identifiers.StateType.expanded(), true));
        node.addReference(new Reference(Identifiers.ShelvedStateMachineType_OneShotShelved, Identifiers.HasComponent, Identifiers.ShelvedStateMachineType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode200() {
        TransitionTypeNode node = new TransitionTypeNode(this.context, Identifiers.ShelvedStateMachineType_UnshelvedToTimedShelved, new QualifiedName(0, "UnshelvedToTimedShelved"), new LocalizedText("en", "UnshelvedToTimedShelved"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.ShelvedStateMachineType_UnshelvedToTimedShelved, Identifiers.HasProperty, Identifiers.ShelvedStateMachineType_UnshelvedToTimedShelved_TransitionNumber.expanded(), true));
        node.addReference(new Reference(Identifiers.ShelvedStateMachineType_UnshelvedToTimedShelved, Identifiers.FromState, Identifiers.ShelvedStateMachineType_Unshelved.expanded(), true));
        node.addReference(new Reference(Identifiers.ShelvedStateMachineType_UnshelvedToTimedShelved, Identifiers.ToState, Identifiers.ShelvedStateMachineType_TimedShelved.expanded(), true));
        node.addReference(new Reference(Identifiers.ShelvedStateMachineType_UnshelvedToTimedShelved, Identifiers.HasEffect, Identifiers.AlarmConditionType.expanded(), true));
        node.addReference(new Reference(Identifiers.ShelvedStateMachineType_UnshelvedToTimedShelved, Identifiers.HasCause, Identifiers.ShelvedStateMachineType_TimedShelve.expanded(), true));
        node.addReference(new Reference(Identifiers.ShelvedStateMachineType_UnshelvedToTimedShelved, Identifiers.HasTypeDefinition, Identifiers.TransitionType.expanded(), true));
        node.addReference(new Reference(Identifiers.ShelvedStateMachineType_UnshelvedToTimedShelved, Identifiers.HasComponent, Identifiers.ShelvedStateMachineType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode201() {
        TransitionTypeNode node = new TransitionTypeNode(this.context, Identifiers.ShelvedStateMachineType_UnshelvedToOneShotShelved, new QualifiedName(0, "UnshelvedToOneShotShelved"), new LocalizedText("en", "UnshelvedToOneShotShelved"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.ShelvedStateMachineType_UnshelvedToOneShotShelved, Identifiers.HasProperty, Identifiers.ShelvedStateMachineType_UnshelvedToOneShotShelved_TransitionNumber.expanded(), true));
        node.addReference(new Reference(Identifiers.ShelvedStateMachineType_UnshelvedToOneShotShelved, Identifiers.FromState, Identifiers.ShelvedStateMachineType_Unshelved.expanded(), true));
        node.addReference(new Reference(Identifiers.ShelvedStateMachineType_UnshelvedToOneShotShelved, Identifiers.ToState, Identifiers.ShelvedStateMachineType_OneShotShelved.expanded(), true));
        node.addReference(new Reference(Identifiers.ShelvedStateMachineType_UnshelvedToOneShotShelved, Identifiers.HasEffect, Identifiers.AlarmConditionType.expanded(), true));
        node.addReference(new Reference(Identifiers.ShelvedStateMachineType_UnshelvedToOneShotShelved, Identifiers.HasCause, Identifiers.ShelvedStateMachineType_OneShotShelve.expanded(), true));
        node.addReference(new Reference(Identifiers.ShelvedStateMachineType_UnshelvedToOneShotShelved, Identifiers.HasTypeDefinition, Identifiers.TransitionType.expanded(), true));
        node.addReference(new Reference(Identifiers.ShelvedStateMachineType_UnshelvedToOneShotShelved, Identifiers.HasComponent, Identifiers.ShelvedStateMachineType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode202() {
        TransitionTypeNode node = new TransitionTypeNode(this.context, Identifiers.ShelvedStateMachineType_TimedShelvedToUnshelved, new QualifiedName(0, "TimedShelvedToUnshelved"), new LocalizedText("en", "TimedShelvedToUnshelved"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.ShelvedStateMachineType_TimedShelvedToUnshelved, Identifiers.HasProperty, Identifiers.ShelvedStateMachineType_TimedShelvedToUnshelved_TransitionNumber.expanded(), true));
        node.addReference(new Reference(Identifiers.ShelvedStateMachineType_TimedShelvedToUnshelved, Identifiers.FromState, Identifiers.ShelvedStateMachineType_TimedShelved.expanded(), true));
        node.addReference(new Reference(Identifiers.ShelvedStateMachineType_TimedShelvedToUnshelved, Identifiers.ToState, Identifiers.ShelvedStateMachineType_Unshelved.expanded(), true));
        node.addReference(new Reference(Identifiers.ShelvedStateMachineType_TimedShelvedToUnshelved, Identifiers.HasEffect, Identifiers.AlarmConditionType.expanded(), true));
        node.addReference(new Reference(Identifiers.ShelvedStateMachineType_TimedShelvedToUnshelved, Identifiers.HasCause, Identifiers.ShelvedStateMachineType_Unshelve.expanded(), true));
        node.addReference(new Reference(Identifiers.ShelvedStateMachineType_TimedShelvedToUnshelved, Identifiers.HasTypeDefinition, Identifiers.TransitionType.expanded(), true));
        node.addReference(new Reference(Identifiers.ShelvedStateMachineType_TimedShelvedToUnshelved, Identifiers.HasComponent, Identifiers.ShelvedStateMachineType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode203() {
        TransitionTypeNode node = new TransitionTypeNode(this.context, Identifiers.ShelvedStateMachineType_TimedShelvedToOneShotShelved, new QualifiedName(0, "TimedShelvedToOneShotShelved"), new LocalizedText("en", "TimedShelvedToOneShotShelved"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.ShelvedStateMachineType_TimedShelvedToOneShotShelved, Identifiers.HasProperty, Identifiers.ShelvedStateMachineType_TimedShelvedToOneShotShelved_TransitionNumber.expanded(), true));
        node.addReference(new Reference(Identifiers.ShelvedStateMachineType_TimedShelvedToOneShotShelved, Identifiers.FromState, Identifiers.ShelvedStateMachineType_TimedShelved.expanded(), true));
        node.addReference(new Reference(Identifiers.ShelvedStateMachineType_TimedShelvedToOneShotShelved, Identifiers.ToState, Identifiers.ShelvedStateMachineType_OneShotShelved.expanded(), true));
        node.addReference(new Reference(Identifiers.ShelvedStateMachineType_TimedShelvedToOneShotShelved, Identifiers.HasEffect, Identifiers.AlarmConditionType.expanded(), true));
        node.addReference(new Reference(Identifiers.ShelvedStateMachineType_TimedShelvedToOneShotShelved, Identifiers.HasCause, Identifiers.ShelvedStateMachineType_OneShotShelve.expanded(), true));
        node.addReference(new Reference(Identifiers.ShelvedStateMachineType_TimedShelvedToOneShotShelved, Identifiers.HasTypeDefinition, Identifiers.TransitionType.expanded(), true));
        node.addReference(new Reference(Identifiers.ShelvedStateMachineType_TimedShelvedToOneShotShelved, Identifiers.HasComponent, Identifiers.ShelvedStateMachineType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode204() {
        TransitionTypeNode node = new TransitionTypeNode(this.context, Identifiers.ShelvedStateMachineType_OneShotShelvedToUnshelved, new QualifiedName(0, "OneShotShelvedToUnshelved"), new LocalizedText("en", "OneShotShelvedToUnshelved"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.ShelvedStateMachineType_OneShotShelvedToUnshelved, Identifiers.HasProperty, Identifiers.ShelvedStateMachineType_OneShotShelvedToUnshelved_TransitionNumber.expanded(), true));
        node.addReference(new Reference(Identifiers.ShelvedStateMachineType_OneShotShelvedToUnshelved, Identifiers.FromState, Identifiers.ShelvedStateMachineType_OneShotShelved.expanded(), true));
        node.addReference(new Reference(Identifiers.ShelvedStateMachineType_OneShotShelvedToUnshelved, Identifiers.ToState, Identifiers.ShelvedStateMachineType_Unshelved.expanded(), true));
        node.addReference(new Reference(Identifiers.ShelvedStateMachineType_OneShotShelvedToUnshelved, Identifiers.HasEffect, Identifiers.AlarmConditionType.expanded(), true));
        node.addReference(new Reference(Identifiers.ShelvedStateMachineType_OneShotShelvedToUnshelved, Identifiers.HasCause, Identifiers.ShelvedStateMachineType_Unshelve.expanded(), true));
        node.addReference(new Reference(Identifiers.ShelvedStateMachineType_OneShotShelvedToUnshelved, Identifiers.HasTypeDefinition, Identifiers.TransitionType.expanded(), true));
        node.addReference(new Reference(Identifiers.ShelvedStateMachineType_OneShotShelvedToUnshelved, Identifiers.HasComponent, Identifiers.ShelvedStateMachineType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode205() {
        TransitionTypeNode node = new TransitionTypeNode(this.context, Identifiers.ShelvedStateMachineType_OneShotShelvedToTimedShelved, new QualifiedName(0, "OneShotShelvedToTimedShelved"), new LocalizedText("en", "OneShotShelvedToTimedShelved"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.ShelvedStateMachineType_OneShotShelvedToTimedShelved, Identifiers.HasProperty, Identifiers.ShelvedStateMachineType_OneShotShelvedToTimedShelved_TransitionNumber.expanded(), true));
        node.addReference(new Reference(Identifiers.ShelvedStateMachineType_OneShotShelvedToTimedShelved, Identifiers.FromState, Identifiers.ShelvedStateMachineType_OneShotShelved.expanded(), true));
        node.addReference(new Reference(Identifiers.ShelvedStateMachineType_OneShotShelvedToTimedShelved, Identifiers.ToState, Identifiers.ShelvedStateMachineType_TimedShelved.expanded(), true));
        node.addReference(new Reference(Identifiers.ShelvedStateMachineType_OneShotShelvedToTimedShelved, Identifiers.HasEffect, Identifiers.AlarmConditionType.expanded(), true));
        node.addReference(new Reference(Identifiers.ShelvedStateMachineType_OneShotShelvedToTimedShelved, Identifiers.HasCause, Identifiers.ShelvedStateMachineType_TimedShelve.expanded(), true));
        node.addReference(new Reference(Identifiers.ShelvedStateMachineType_OneShotShelvedToTimedShelved, Identifiers.HasTypeDefinition, Identifiers.TransitionType.expanded(), true));
        node.addReference(new Reference(Identifiers.ShelvedStateMachineType_OneShotShelvedToTimedShelved, Identifiers.HasComponent, Identifiers.ShelvedStateMachineType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode206() {
        FolderTypeNode node = new FolderTypeNode(this.context, Identifiers.HistoryServerCapabilitiesType_AggregateFunctions, new QualifiedName(0, "AggregateFunctions"), new LocalizedText("en", "AggregateFunctions"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.HistoryServerCapabilitiesType_AggregateFunctions, Identifiers.HasTypeDefinition, Identifiers.FolderType.expanded(), true));
        node.addReference(new Reference(Identifiers.HistoryServerCapabilitiesType_AggregateFunctions, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.HistoryServerCapabilitiesType_AggregateFunctions, Identifiers.HasComponent, Identifiers.HistoryServerCapabilitiesType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode207() {
        FolderTypeNode node = new FolderTypeNode(this.context, Identifiers.Server_ServerCapabilities_ModellingRules, new QualifiedName(0, "ModellingRules"), new LocalizedText("en", "ModellingRules"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.Server_ServerCapabilities_ModellingRules, Identifiers.HasTypeDefinition, Identifiers.FolderType.expanded(), true));
        node.addReference(new Reference(Identifiers.Server_ServerCapabilities_ModellingRules, Identifiers.HasComponent, Identifiers.Server_ServerCapabilities.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode208() {
        FolderTypeNode node = new FolderTypeNode(this.context, Identifiers.Server_ServerCapabilities_AggregateFunctions, new QualifiedName(0, "AggregateFunctions"), new LocalizedText("en", "AggregateFunctions"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.Server_ServerCapabilities_AggregateFunctions, Identifiers.HasTypeDefinition, Identifiers.FolderType.expanded(), true));
        node.addReference(new Reference(Identifiers.Server_ServerCapabilities_AggregateFunctions, Identifiers.HasComponent, Identifiers.Server_ServerCapabilities.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode209() {
        HistoryServerCapabilitiesTypeNode node = new HistoryServerCapabilitiesTypeNode(this.context, Identifiers.HistoryServerCapabilities, new QualifiedName(0, "HistoryServerCapabilities"), new LocalizedText("en", "HistoryServerCapabilities"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.HistoryServerCapabilities, Identifiers.HasProperty, Identifiers.HistoryServerCapabilities_AccessHistoryDataCapability.expanded(), true));
        node.addReference(new Reference(Identifiers.HistoryServerCapabilities, Identifiers.HasProperty, Identifiers.HistoryServerCapabilities_AccessHistoryEventsCapability.expanded(), true));
        node.addReference(new Reference(Identifiers.HistoryServerCapabilities, Identifiers.HasProperty, Identifiers.HistoryServerCapabilities_MaxReturnDataValues.expanded(), true));
        node.addReference(new Reference(Identifiers.HistoryServerCapabilities, Identifiers.HasProperty, Identifiers.HistoryServerCapabilities_MaxReturnEventValues.expanded(), true));
        node.addReference(new Reference(Identifiers.HistoryServerCapabilities, Identifiers.HasProperty, Identifiers.HistoryServerCapabilities_InsertDataCapability.expanded(), true));
        node.addReference(new Reference(Identifiers.HistoryServerCapabilities, Identifiers.HasProperty, Identifiers.HistoryServerCapabilities_ReplaceDataCapability.expanded(), true));
        node.addReference(new Reference(Identifiers.HistoryServerCapabilities, Identifiers.HasProperty, Identifiers.HistoryServerCapabilities_UpdateDataCapability.expanded(), true));
        node.addReference(new Reference(Identifiers.HistoryServerCapabilities, Identifiers.HasProperty, Identifiers.HistoryServerCapabilities_DeleteRawCapability.expanded(), true));
        node.addReference(new Reference(Identifiers.HistoryServerCapabilities, Identifiers.HasProperty, Identifiers.HistoryServerCapabilities_DeleteAtTimeCapability.expanded(), true));
        node.addReference(new Reference(Identifiers.HistoryServerCapabilities, Identifiers.HasProperty, Identifiers.HistoryServerCapabilities_InsertEventCapability.expanded(), true));
        node.addReference(new Reference(Identifiers.HistoryServerCapabilities, Identifiers.HasProperty, Identifiers.HistoryServerCapabilities_ReplaceEventCapability.expanded(), true));
        node.addReference(new Reference(Identifiers.HistoryServerCapabilities, Identifiers.HasProperty, Identifiers.HistoryServerCapabilities_UpdateEventCapability.expanded(), true));
        node.addReference(new Reference(Identifiers.HistoryServerCapabilities, Identifiers.HasProperty, Identifiers.HistoryServerCapabilities_DeleteEventCapability.expanded(), true));
        node.addReference(new Reference(Identifiers.HistoryServerCapabilities, Identifiers.HasProperty, Identifiers.HistoryServerCapabilities_InsertAnnotationCapability.expanded(), true));
        node.addReference(new Reference(Identifiers.HistoryServerCapabilities, Identifiers.HasComponent, Identifiers.HistoryServerCapabilities_AggregateFunctions.expanded(), true));
        node.addReference(new Reference(Identifiers.HistoryServerCapabilities, Identifiers.HasComponent, Identifiers.Server_ServerCapabilities.expanded(), false));
        node.addReference(new Reference(Identifiers.HistoryServerCapabilities, Identifiers.HasTypeDefinition, Identifiers.HistoryServerCapabilitiesType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode210() {
        FolderTypeNode node = new FolderTypeNode(this.context, Identifiers.HistoryServerCapabilities_AggregateFunctions, new QualifiedName(0, "AggregateFunctions"), new LocalizedText("en", "AggregateFunctions"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.HistoryServerCapabilities_AggregateFunctions, Identifiers.HasTypeDefinition, Identifiers.FolderType.expanded(), true));
        node.addReference(new Reference(Identifiers.HistoryServerCapabilities_AggregateFunctions, Identifiers.HasComponent, Identifiers.HistoryServerCapabilities.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode211() {
        HistoricalDataConfigurationTypeNode node = new HistoricalDataConfigurationTypeNode(this.context, Identifiers.HAConfiguration, new QualifiedName(0, "HA Configuration"), new LocalizedText("en", "HA Configuration"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.HAConfiguration, Identifiers.HasComponent, Identifiers.HAConfiguration_AggregateConfiguration.expanded(), true));
        node.addReference(new Reference(Identifiers.HAConfiguration, Identifiers.HasProperty, Identifiers.HAConfiguration_Stepped.expanded(), true));
        node.addReference(new Reference(Identifiers.HAConfiguration, Identifiers.HasTypeDefinition, Identifiers.HistoricalDataConfigurationType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode212() {
        AggregateConfigurationTypeNode node = new AggregateConfigurationTypeNode(this.context, Identifiers.HAConfiguration_AggregateConfiguration, new QualifiedName(0, "AggregateConfiguration"), new LocalizedText("en", "AggregateConfiguration"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.HAConfiguration_AggregateConfiguration, Identifiers.HasProperty, Identifiers.HAConfiguration_AggregateConfiguration_TreatUncertainAsBad.expanded(), true));
        node.addReference(new Reference(Identifiers.HAConfiguration_AggregateConfiguration, Identifiers.HasProperty, Identifiers.HAConfiguration_AggregateConfiguration_PercentDataBad.expanded(), true));
        node.addReference(new Reference(Identifiers.HAConfiguration_AggregateConfiguration, Identifiers.HasProperty, Identifiers.HAConfiguration_AggregateConfiguration_PercentDataGood.expanded(), true));
        node.addReference(new Reference(Identifiers.HAConfiguration_AggregateConfiguration, Identifiers.HasProperty, Identifiers.HAConfiguration_AggregateConfiguration_UseSlopedExtrapolation.expanded(), true));
        node.addReference(new Reference(Identifiers.HAConfiguration_AggregateConfiguration, Identifiers.HasTypeDefinition, Identifiers.AggregateConfigurationType.expanded(), true));
        node.addReference(new Reference(Identifiers.HAConfiguration_AggregateConfiguration, Identifiers.HasComponent, Identifiers.HAConfiguration.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode213() {
        FolderTypeNode node = new FolderTypeNode(this.context, Identifiers.EventTypesFolder, new QualifiedName(0, "EventTypes"), new LocalizedText("en", "EventTypes"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.EventTypesFolder, Identifiers.Organizes, Identifiers.TypesFolder.expanded(), false));
        node.addReference(new Reference(Identifiers.EventTypesFolder, Identifiers.Organizes, Identifiers.BaseEventType.expanded(), true));
        node.addReference(new Reference(Identifiers.EventTypesFolder, Identifiers.HasTypeDefinition, Identifiers.FolderType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode214() {
        AggregateConfigurationTypeNode node = new AggregateConfigurationTypeNode(this.context, Identifiers.HistoricalDataConfigurationType_AggregateConfiguration, new QualifiedName(0, "AggregateConfiguration"), new LocalizedText("en", "AggregateConfiguration"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.HistoricalDataConfigurationType_AggregateConfiguration, Identifiers.HasProperty, Identifiers.HistoricalDataConfigurationType_AggregateConfiguration_TreatUncertainAsBad.expanded(), true));
        node.addReference(new Reference(Identifiers.HistoricalDataConfigurationType_AggregateConfiguration, Identifiers.HasProperty, Identifiers.HistoricalDataConfigurationType_AggregateConfiguration_PercentDataBad.expanded(), true));
        node.addReference(new Reference(Identifiers.HistoricalDataConfigurationType_AggregateConfiguration, Identifiers.HasProperty, Identifiers.HistoricalDataConfigurationType_AggregateConfiguration_PercentDataGood.expanded(), true));
        node.addReference(new Reference(Identifiers.HistoricalDataConfigurationType_AggregateConfiguration, Identifiers.HasProperty, Identifiers.HistoricalDataConfigurationType_AggregateConfiguration_UseSlopedExtrapolation.expanded(), true));
        node.addReference(new Reference(Identifiers.HistoricalDataConfigurationType_AggregateConfiguration, Identifiers.HasTypeDefinition, Identifiers.AggregateConfigurationType.expanded(), true));
        node.addReference(new Reference(Identifiers.HistoricalDataConfigurationType_AggregateConfiguration, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.HistoricalDataConfigurationType_AggregateConfiguration, Identifiers.HasComponent, Identifiers.HistoricalDataConfigurationType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode215() {
        BaseObjectTypeNode node = new BaseObjectTypeNode(this.context, Identifiers.DefaultBinary, new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.DefaultBinary, Identifiers.HasTypeDefinition, Identifiers.BaseObjectType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode216() {
        BaseObjectTypeNode node = new BaseObjectTypeNode(this.context, Identifiers.DefaultXml, new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.DefaultXml, Identifiers.HasTypeDefinition, Identifiers.BaseObjectType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode217() {
        FolderTypeNode node = new FolderTypeNode(this.context, Identifiers.ServerType_ServerCapabilities_ModellingRules, new QualifiedName(0, "ModellingRules"), new LocalizedText("en", "ModellingRules"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.ServerType_ServerCapabilities_ModellingRules, Identifiers.HasTypeDefinition, Identifiers.FolderType.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerType_ServerCapabilities_ModellingRules, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerType_ServerCapabilities_ModellingRules, Identifiers.HasComponent, Identifiers.ServerType_ServerCapabilities.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode218() {
        AggregateFunctionTypeNode node = new AggregateFunctionTypeNode(this.context, Identifiers.AggregateFunction_TimeAverage2, new QualifiedName(0, "TimeAverage2"), new LocalizedText("en", "TimeAverage2"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.AggregateFunction_TimeAverage2, Identifiers.HasTypeDefinition, Identifiers.AggregateFunctionType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode219() {
        FolderTypeNode node = new FolderTypeNode(this.context, Identifiers.ServerType_ServerCapabilities_AggregateFunctions, new QualifiedName(0, "AggregateFunctions"), new LocalizedText("en", "AggregateFunctions"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.ServerType_ServerCapabilities_AggregateFunctions, Identifiers.HasTypeDefinition, Identifiers.FolderType.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerType_ServerCapabilities_AggregateFunctions, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerType_ServerCapabilities_AggregateFunctions, Identifiers.HasComponent, Identifiers.ServerType_ServerCapabilities.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode220() {
        AggregateFunctionTypeNode node = new AggregateFunctionTypeNode(this.context, Identifiers.AggregateFunction_Minimum2, new QualifiedName(0, "Minimum2"), new LocalizedText("en", "Minimum2"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.AggregateFunction_Minimum2, Identifiers.HasTypeDefinition, Identifiers.AggregateFunctionType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode221() {
        AggregateFunctionTypeNode node = new AggregateFunctionTypeNode(this.context, Identifiers.AggregateFunction_Maximum2, new QualifiedName(0, "Maximum2"), new LocalizedText("en", "Maximum2"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.AggregateFunction_Maximum2, Identifiers.HasTypeDefinition, Identifiers.AggregateFunctionType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode222() {
        AggregateFunctionTypeNode node = new AggregateFunctionTypeNode(this.context, Identifiers.AggregateFunction_Range2, new QualifiedName(0, "Range2"), new LocalizedText("en", "Range2"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.AggregateFunction_Range2, Identifiers.HasTypeDefinition, Identifiers.AggregateFunctionType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode223() {
        AggregateFunctionTypeNode node = new AggregateFunctionTypeNode(this.context, Identifiers.AggregateFunction_WorstQuality2, new QualifiedName(0, "WorstQuality2"), new LocalizedText("en", "WorstQuality2"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.AggregateFunction_WorstQuality2, Identifiers.HasTypeDefinition, Identifiers.AggregateFunctionType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode224() {
        SessionsDiagnosticsSummaryTypeNode node = new SessionsDiagnosticsSummaryTypeNode(this.context, Identifiers.ServerType_ServerDiagnostics_SessionsDiagnosticsSummary, new QualifiedName(0, "SessionsDiagnosticsSummary"), new LocalizedText("en", "SessionsDiagnosticsSummary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.ServerType_ServerDiagnostics_SessionsDiagnosticsSummary, Identifiers.HasComponent, Identifiers.ServerType_ServerDiagnostics_SessionsDiagnosticsSummary_SessionDiagnosticsArray.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerType_ServerDiagnostics_SessionsDiagnosticsSummary, Identifiers.HasComponent, Identifiers.ServerType_ServerDiagnostics_SessionsDiagnosticsSummary_SessionSecurityDiagnosticsArray.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerType_ServerDiagnostics_SessionsDiagnosticsSummary, Identifiers.HasTypeDefinition, Identifiers.SessionsDiagnosticsSummaryType.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerType_ServerDiagnostics_SessionsDiagnosticsSummary, Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerType_ServerDiagnostics_SessionsDiagnosticsSummary, Identifiers.HasComponent, Identifiers.ServerType_ServerDiagnostics.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode225() {
        AggregateFunctionTypeNode node = new AggregateFunctionTypeNode(this.context, Identifiers.AggregateFunction_Total2, new QualifiedName(0, "Total2"), new LocalizedText("en", "Total2"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.AggregateFunction_Total2, Identifiers.HasTypeDefinition, Identifiers.AggregateFunctionType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode226() {
        AggregateFunctionTypeNode node = new AggregateFunctionTypeNode(this.context, Identifiers.AggregateFunction_MinimumActualTime2, new QualifiedName(0, "MinimumActualTime2"), new LocalizedText("en", "MinimumActualTime2"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.AggregateFunction_MinimumActualTime2, Identifiers.HasTypeDefinition, Identifiers.AggregateFunctionType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode227() {
        AggregateFunctionTypeNode node = new AggregateFunctionTypeNode(this.context, Identifiers.AggregateFunction_MaximumActualTime2, new QualifiedName(0, "MaximumActualTime2"), new LocalizedText("en", "MaximumActualTime2"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.AggregateFunction_MaximumActualTime2, Identifiers.HasTypeDefinition, Identifiers.AggregateFunctionType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode228() {
        AggregateFunctionTypeNode node = new AggregateFunctionTypeNode(this.context, Identifiers.AggregateFunction_DurationInStateZero, new QualifiedName(0, "DurationInStateZero"), new LocalizedText("en", "DurationInStateZero"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.AggregateFunction_DurationInStateZero, Identifiers.HasTypeDefinition, Identifiers.AggregateFunctionType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode229() {
        AggregateFunctionTypeNode node = new AggregateFunctionTypeNode(this.context, Identifiers.AggregateFunction_DurationInStateNonZero, new QualifiedName(0, "DurationInStateNonZero"), new LocalizedText("en", "DurationInStateNonZero"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.AggregateFunction_DurationInStateNonZero, Identifiers.HasTypeDefinition, Identifiers.AggregateFunctionType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode230() {
        AggregateFunctionTypeNode node = new AggregateFunctionTypeNode(this.context, Identifiers.AggregateFunction_StandardDeviationSample, new QualifiedName(0, "StandardDeviationSample"), new LocalizedText("en", "StandardDeviationSample"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.AggregateFunction_StandardDeviationSample, Identifiers.HasTypeDefinition, Identifiers.AggregateFunctionType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode231() {
        AggregateFunctionTypeNode node = new AggregateFunctionTypeNode(this.context, Identifiers.AggregateFunction_StandardDeviationPopulation, new QualifiedName(0, "StandardDeviationPopulation"), new LocalizedText("en", "StandardDeviationPopulation"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.AggregateFunction_StandardDeviationPopulation, Identifiers.HasTypeDefinition, Identifiers.AggregateFunctionType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode232() {
        AggregateFunctionTypeNode node = new AggregateFunctionTypeNode(this.context, Identifiers.AggregateFunction_VarianceSample, new QualifiedName(0, "VarianceSample"), new LocalizedText("en", "VarianceSample"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.AggregateFunction_VarianceSample, Identifiers.HasTypeDefinition, Identifiers.AggregateFunctionType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode233() {
        AggregateFunctionTypeNode node = new AggregateFunctionTypeNode(this.context, Identifiers.AggregateFunction_VariancePopulation, new QualifiedName(0, "VariancePopulation"), new LocalizedText("en", "VariancePopulation"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.AggregateFunction_VariancePopulation, Identifiers.HasTypeDefinition, Identifiers.AggregateFunctionType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode234() {
        AggregateFunctionTypeNode node = new AggregateFunctionTypeNode(this.context, Identifiers.AggregateFunction_StartBound, new QualifiedName(0, "StartBound"), new LocalizedText("en", "StartBound"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.AggregateFunction_StartBound, Identifiers.HasTypeDefinition, Identifiers.AggregateFunctionType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode235() {
        AggregateFunctionTypeNode node = new AggregateFunctionTypeNode(this.context, Identifiers.AggregateFunction_EndBound, new QualifiedName(0, "EndBound"), new LocalizedText("en", "EndBound"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.AggregateFunction_EndBound, Identifiers.HasTypeDefinition, Identifiers.AggregateFunctionType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode236() {
        AggregateFunctionTypeNode node = new AggregateFunctionTypeNode(this.context, Identifiers.AggregateFunction_DeltaBounds, new QualifiedName(0, "DeltaBounds"), new LocalizedText("en", "DeltaBounds"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.AggregateFunction_DeltaBounds, Identifiers.HasTypeDefinition, Identifiers.AggregateFunctionType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode237() {
        ModellingRuleTypeNode node = new ModellingRuleTypeNode(this.context, Identifiers.ModellingRule_OptionalPlaceholder, new QualifiedName(0, "OptionalPlaceholder"), new LocalizedText("en", "OptionalPlaceholder"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.ModellingRule_OptionalPlaceholder, Identifiers.HasProperty, Identifiers.ModellingRule_OptionalPlaceholder_NamingRule.expanded(), true));
        node.addReference(new Reference(Identifiers.ModellingRule_OptionalPlaceholder, Identifiers.HasTypeDefinition, Identifiers.ModellingRuleType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode238() {
        ModellingRuleTypeNode node = new ModellingRuleTypeNode(this.context, Identifiers.ModellingRule_MandatoryPlaceholder, new QualifiedName(0, "MandatoryPlaceholder"), new LocalizedText("en", "MandatoryPlaceholder"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.ModellingRule_MandatoryPlaceholder, Identifiers.HasProperty, Identifiers.ModellingRule_MandatoryPlaceholder_NamingRule.expanded(), true));
        node.addReference(new Reference(Identifiers.ModellingRule_MandatoryPlaceholder, Identifiers.HasTypeDefinition, Identifiers.ModellingRuleType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode239() {
        NamespacesTypeNode node = new NamespacesTypeNode(this.context, Identifiers.ServerType_Namespaces, new QualifiedName(0, "Namespaces"), new LocalizedText("en", "Namespaces"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.ServerType_Namespaces, Identifiers.HasTypeDefinition, Identifiers.NamespacesType.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerType_Namespaces, Identifiers.HasModellingRule, Identifiers.ModellingRule_Optional.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerType_Namespaces, Identifiers.HasComponent, Identifiers.ServerType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode240() {
        OperationLimitsTypeNode node = new OperationLimitsTypeNode(this.context, Identifiers.ServerCapabilitiesType_OperationLimits, new QualifiedName(0, "OperationLimits"), new LocalizedText("en", "OperationLimits"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.ServerCapabilitiesType_OperationLimits, Identifiers.HasTypeDefinition, Identifiers.OperationLimitsType.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerCapabilitiesType_OperationLimits, Identifiers.HasModellingRule, Identifiers.ModellingRule_Optional.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerCapabilitiesType_OperationLimits, Identifiers.HasComponent, Identifiers.ServerCapabilitiesType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode241() {
        AddressSpaceFileTypeNode node = new AddressSpaceFileTypeNode(this.context, Identifiers.NamespaceMetadataType_NamespaceFile, new QualifiedName(0, "NamespaceFile"), new LocalizedText("en", "NamespaceFile"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.NamespaceMetadataType_NamespaceFile, Identifiers.HasProperty, Identifiers.NamespaceMetadataType_NamespaceFile_Size.expanded(), true));
        node.addReference(new Reference(Identifiers.NamespaceMetadataType_NamespaceFile, Identifiers.HasProperty, Identifiers.NamespaceMetadataType_NamespaceFile_Writable.expanded(), true));
        node.addReference(new Reference(Identifiers.NamespaceMetadataType_NamespaceFile, Identifiers.HasProperty, Identifiers.NamespaceMetadataType_NamespaceFile_UserWritable.expanded(), true));
        node.addReference(new Reference(Identifiers.NamespaceMetadataType_NamespaceFile, Identifiers.HasProperty, Identifiers.NamespaceMetadataType_NamespaceFile_OpenCount.expanded(), true));
        node.addReference(new Reference(Identifiers.NamespaceMetadataType_NamespaceFile, Identifiers.HasComponent, Identifiers.NamespaceMetadataType_NamespaceFile_Open.expanded(), true));
        node.addReference(new Reference(Identifiers.NamespaceMetadataType_NamespaceFile, Identifiers.HasComponent, Identifiers.NamespaceMetadataType_NamespaceFile_Close.expanded(), true));
        node.addReference(new Reference(Identifiers.NamespaceMetadataType_NamespaceFile, Identifiers.HasComponent, Identifiers.NamespaceMetadataType_NamespaceFile_Read.expanded(), true));
        node.addReference(new Reference(Identifiers.NamespaceMetadataType_NamespaceFile, Identifiers.HasComponent, Identifiers.NamespaceMetadataType_NamespaceFile_Write.expanded(), true));
        node.addReference(new Reference(Identifiers.NamespaceMetadataType_NamespaceFile, Identifiers.HasComponent, Identifiers.NamespaceMetadataType_NamespaceFile_GetPosition.expanded(), true));
        node.addReference(new Reference(Identifiers.NamespaceMetadataType_NamespaceFile, Identifiers.HasComponent, Identifiers.NamespaceMetadataType_NamespaceFile_SetPosition.expanded(), true));
        node.addReference(new Reference(Identifiers.NamespaceMetadataType_NamespaceFile, Identifiers.HasTypeDefinition, Identifiers.AddressSpaceFileType.expanded(), true));
        node.addReference(new Reference(Identifiers.NamespaceMetadataType_NamespaceFile, Identifiers.HasModellingRule, Identifiers.ModellingRule_Optional.expanded(), true));
        node.addReference(new Reference(Identifiers.NamespaceMetadataType_NamespaceFile, Identifiers.HasComponent, Identifiers.NamespaceMetadataType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode242() {
        NamespaceMetadataTypeNode node = new NamespaceMetadataTypeNode(this.context, Identifiers.NamespacesType_NamespaceIdentifier_Placeholder, new QualifiedName(0, "<NamespaceIdentifier>"), new LocalizedText("en", "<NamespaceIdentifier>"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.NamespacesType_NamespaceIdentifier_Placeholder, Identifiers.HasProperty, Identifiers.NamespacesType_NamespaceIdentifier_Placeholder_NamespaceUri.expanded(), true));
        node.addReference(new Reference(Identifiers.NamespacesType_NamespaceIdentifier_Placeholder, Identifiers.HasProperty, Identifiers.NamespacesType_NamespaceIdentifier_Placeholder_NamespaceVersion.expanded(), true));
        node.addReference(new Reference(Identifiers.NamespacesType_NamespaceIdentifier_Placeholder, Identifiers.HasProperty, Identifiers.NamespacesType_NamespaceIdentifier_Placeholder_NamespacePublicationDate.expanded(), true));
        node.addReference(new Reference(Identifiers.NamespacesType_NamespaceIdentifier_Placeholder, Identifiers.HasProperty, Identifiers.NamespacesType_NamespaceIdentifier_Placeholder_IsNamespaceSubset.expanded(), true));
        node.addReference(new Reference(Identifiers.NamespacesType_NamespaceIdentifier_Placeholder, Identifiers.HasProperty, Identifiers.NamespacesType_NamespaceIdentifier_Placeholder_StaticNodeIdTypes.expanded(), true));
        node.addReference(new Reference(Identifiers.NamespacesType_NamespaceIdentifier_Placeholder, Identifiers.HasProperty, Identifiers.NamespacesType_NamespaceIdentifier_Placeholder_StaticNumericNodeIdRange.expanded(), true));
        node.addReference(new Reference(Identifiers.NamespacesType_NamespaceIdentifier_Placeholder, Identifiers.HasProperty, Identifiers.NamespacesType_NamespaceIdentifier_Placeholder_StaticStringNodeIdPattern.expanded(), true));
        node.addReference(new Reference(Identifiers.NamespacesType_NamespaceIdentifier_Placeholder, Identifiers.HasTypeDefinition, Identifiers.NamespaceMetadataType.expanded(), true));
        node.addReference(new Reference(Identifiers.NamespacesType_NamespaceIdentifier_Placeholder, Identifiers.HasModellingRule, Identifiers.ModellingRule_OptionalPlaceholder.expanded(), true));
        node.addReference(new Reference(Identifiers.NamespacesType_NamespaceIdentifier_Placeholder, Identifiers.HasComponent, Identifiers.NamespacesType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode243() {
        AddressSpaceFileTypeNode node = new AddressSpaceFileTypeNode(this.context, Identifiers.NamespacesType_AddressSpaceFile, new QualifiedName(0, "AddressSpaceFile"), new LocalizedText("en", "AddressSpaceFile"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.NamespacesType_AddressSpaceFile, Identifiers.HasProperty, Identifiers.NamespacesType_AddressSpaceFile_Size.expanded(), true));
        node.addReference(new Reference(Identifiers.NamespacesType_AddressSpaceFile, Identifiers.HasProperty, Identifiers.NamespacesType_AddressSpaceFile_Writable.expanded(), true));
        node.addReference(new Reference(Identifiers.NamespacesType_AddressSpaceFile, Identifiers.HasProperty, Identifiers.NamespacesType_AddressSpaceFile_UserWritable.expanded(), true));
        node.addReference(new Reference(Identifiers.NamespacesType_AddressSpaceFile, Identifiers.HasProperty, Identifiers.NamespacesType_AddressSpaceFile_OpenCount.expanded(), true));
        node.addReference(new Reference(Identifiers.NamespacesType_AddressSpaceFile, Identifiers.HasComponent, Identifiers.NamespacesType_AddressSpaceFile_Open.expanded(), true));
        node.addReference(new Reference(Identifiers.NamespacesType_AddressSpaceFile, Identifiers.HasComponent, Identifiers.NamespacesType_AddressSpaceFile_Close.expanded(), true));
        node.addReference(new Reference(Identifiers.NamespacesType_AddressSpaceFile, Identifiers.HasComponent, Identifiers.NamespacesType_AddressSpaceFile_Read.expanded(), true));
        node.addReference(new Reference(Identifiers.NamespacesType_AddressSpaceFile, Identifiers.HasComponent, Identifiers.NamespacesType_AddressSpaceFile_Write.expanded(), true));
        node.addReference(new Reference(Identifiers.NamespacesType_AddressSpaceFile, Identifiers.HasComponent, Identifiers.NamespacesType_AddressSpaceFile_GetPosition.expanded(), true));
        node.addReference(new Reference(Identifiers.NamespacesType_AddressSpaceFile, Identifiers.HasComponent, Identifiers.NamespacesType_AddressSpaceFile_SetPosition.expanded(), true));
        node.addReference(new Reference(Identifiers.NamespacesType_AddressSpaceFile, Identifiers.HasTypeDefinition, Identifiers.AddressSpaceFileType.expanded(), true));
        node.addReference(new Reference(Identifiers.NamespacesType_AddressSpaceFile, Identifiers.HasModellingRule, Identifiers.ModellingRule_Optional.expanded(), true));
        node.addReference(new Reference(Identifiers.NamespacesType_AddressSpaceFile, Identifiers.HasComponent, Identifiers.NamespacesType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode244() {
        OperationLimitsTypeNode node = new OperationLimitsTypeNode(this.context, Identifiers.Server_ServerCapabilities_OperationLimits, new QualifiedName(0, "OperationLimits"), new LocalizedText("en", "OperationLimits"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.Server_ServerCapabilities_OperationLimits, Identifiers.HasProperty, Identifiers.Server_ServerCapabilities_OperationLimits_MaxNodesPerRead.expanded(), true));
        node.addReference(new Reference(Identifiers.Server_ServerCapabilities_OperationLimits, Identifiers.HasProperty, Identifiers.Server_ServerCapabilities_OperationLimits_MaxNodesPerHistoryReadData.expanded(), true));
        node.addReference(new Reference(Identifiers.Server_ServerCapabilities_OperationLimits, Identifiers.HasProperty, Identifiers.Server_ServerCapabilities_OperationLimits_MaxNodesPerHistoryReadEvents.expanded(), true));
        node.addReference(new Reference(Identifiers.Server_ServerCapabilities_OperationLimits, Identifiers.HasProperty, Identifiers.Server_ServerCapabilities_OperationLimits_MaxNodesPerWrite.expanded(), true));
        node.addReference(new Reference(Identifiers.Server_ServerCapabilities_OperationLimits, Identifiers.HasProperty, Identifiers.Server_ServerCapabilities_OperationLimits_MaxNodesPerHistoryUpdateData.expanded(), true));
        node.addReference(new Reference(Identifiers.Server_ServerCapabilities_OperationLimits, Identifiers.HasProperty, Identifiers.Server_ServerCapabilities_OperationLimits_MaxNodesPerHistoryUpdateEvents.expanded(), true));
        node.addReference(new Reference(Identifiers.Server_ServerCapabilities_OperationLimits, Identifiers.HasProperty, Identifiers.Server_ServerCapabilities_OperationLimits_MaxNodesPerMethodCall.expanded(), true));
        node.addReference(new Reference(Identifiers.Server_ServerCapabilities_OperationLimits, Identifiers.HasProperty, Identifiers.Server_ServerCapabilities_OperationLimits_MaxNodesPerBrowse.expanded(), true));
        node.addReference(new Reference(Identifiers.Server_ServerCapabilities_OperationLimits, Identifiers.HasProperty, Identifiers.Server_ServerCapabilities_OperationLimits_MaxNodesPerRegisterNodes.expanded(), true));
        node.addReference(new Reference(Identifiers.Server_ServerCapabilities_OperationLimits, Identifiers.HasProperty, Identifiers.Server_ServerCapabilities_OperationLimits_MaxNodesPerTranslateBrowsePathsToNodeIds.expanded(), true));
        node.addReference(new Reference(Identifiers.Server_ServerCapabilities_OperationLimits, Identifiers.HasProperty, Identifiers.Server_ServerCapabilities_OperationLimits_MaxNodesPerNodeManagement.expanded(), true));
        node.addReference(new Reference(Identifiers.Server_ServerCapabilities_OperationLimits, Identifiers.HasProperty, Identifiers.Server_ServerCapabilities_OperationLimits_MaxMonitoredItemsPerCall.expanded(), true));
        node.addReference(new Reference(Identifiers.Server_ServerCapabilities_OperationLimits, Identifiers.HasTypeDefinition, Identifiers.OperationLimitsType.expanded(), true));
        node.addReference(new Reference(Identifiers.Server_ServerCapabilities_OperationLimits, Identifiers.HasComponent, Identifiers.Server_ServerCapabilities.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode245() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.EnumValueType_Encoding_DefaultXml, new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.EnumValueType_Encoding_DefaultXml, Identifiers.HasEncoding, Identifiers.EnumValueType.expanded(), false));
        node.addReference(new Reference(Identifiers.EnumValueType_Encoding_DefaultXml, Identifiers.HasDescription, Identifiers.OpcUa_XmlSchema_EnumValueType.expanded(), true));
        node.addReference(new Reference(Identifiers.EnumValueType_Encoding_DefaultXml, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode246() {
        NamespacesTypeNode node = new NamespacesTypeNode(this.context, Identifiers.Server_Namespaces, new QualifiedName(0, "Namespaces"), new LocalizedText("en", "Namespaces"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.Server_Namespaces, Identifiers.HasComponent, Identifiers.Server_Namespaces_OPCUANamespaceUri.expanded(), true));
        node.addReference(new Reference(Identifiers.Server_Namespaces, Identifiers.HasTypeDefinition, Identifiers.NamespacesType.expanded(), true));
        node.addReference(new Reference(Identifiers.Server_Namespaces, Identifiers.HasComponent, Identifiers.Server.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode247() {
        FolderTypeNode node = new FolderTypeNode(this.context, Identifiers.HistoricalDataConfigurationType_AggregateFunctions, new QualifiedName(0, "AggregateFunctions"), new LocalizedText("en", "AggregateFunctions"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.HistoricalDataConfigurationType_AggregateFunctions, Identifiers.HasTypeDefinition, Identifiers.FolderType.expanded(), true));
        node.addReference(new Reference(Identifiers.HistoricalDataConfigurationType_AggregateFunctions, Identifiers.HasModellingRule, Identifiers.ModellingRule_Optional.expanded(), true));
        node.addReference(new Reference(Identifiers.HistoricalDataConfigurationType_AggregateFunctions, Identifiers.HasComponent, Identifiers.HistoricalDataConfigurationType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode248() {
        SessionsDiagnosticsSummaryTypeNode node = new SessionsDiagnosticsSummaryTypeNode(this.context, Identifiers.Server_ServerDiagnostics_SessionsDiagnosticsSummary, new QualifiedName(0, "SessionsDiagnosticsSummary"), new LocalizedText("en", "SessionsDiagnosticsSummary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.Server_ServerDiagnostics_SessionsDiagnosticsSummary, Identifiers.HasComponent, Identifiers.Server_ServerDiagnostics_SessionsDiagnosticsSummary_SessionDiagnosticsArray.expanded(), true));
        node.addReference(new Reference(Identifiers.Server_ServerDiagnostics_SessionsDiagnosticsSummary, Identifiers.HasComponent, Identifiers.Server_ServerDiagnostics_SessionsDiagnosticsSummary_SessionSecurityDiagnosticsArray.expanded(), true));
        node.addReference(new Reference(Identifiers.Server_ServerDiagnostics_SessionsDiagnosticsSummary, Identifiers.HasTypeDefinition, Identifiers.SessionsDiagnosticsSummaryType.expanded(), true));
        node.addReference(new Reference(Identifiers.Server_ServerDiagnostics_SessionsDiagnosticsSummary, Identifiers.HasComponent, Identifiers.Server_ServerDiagnostics.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode249() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.EndpointUrlListDataType_Encoding_DefaultXml, new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.EndpointUrlListDataType_Encoding_DefaultXml, Identifiers.HasEncoding, Identifiers.EndpointUrlListDataType.expanded(), false));
        node.addReference(new Reference(Identifiers.EndpointUrlListDataType_Encoding_DefaultXml, Identifiers.HasDescription, Identifiers.OpcUa_XmlSchema_EndpointUrlListDataType.expanded(), true));
        node.addReference(new Reference(Identifiers.EndpointUrlListDataType_Encoding_DefaultXml, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode250() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.NetworkGroupDataType_Encoding_DefaultXml, new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.NetworkGroupDataType_Encoding_DefaultXml, Identifiers.HasEncoding, Identifiers.NetworkGroupDataType.expanded(), false));
        node.addReference(new Reference(Identifiers.NetworkGroupDataType_Encoding_DefaultXml, Identifiers.HasDescription, Identifiers.OpcUa_XmlSchema_NetworkGroupDataType.expanded(), true));
        node.addReference(new Reference(Identifiers.NetworkGroupDataType_Encoding_DefaultXml, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode251() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.EndpointUrlListDataType_Encoding_DefaultBinary, new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.EndpointUrlListDataType_Encoding_DefaultBinary, Identifiers.HasEncoding, Identifiers.EndpointUrlListDataType.expanded(), false));
        node.addReference(new Reference(Identifiers.EndpointUrlListDataType_Encoding_DefaultBinary, Identifiers.HasDescription, Identifiers.OpcUa_BinarySchema_EndpointUrlListDataType.expanded(), true));
        node.addReference(new Reference(Identifiers.EndpointUrlListDataType_Encoding_DefaultBinary, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode252() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.NetworkGroupDataType_Encoding_DefaultBinary, new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.NetworkGroupDataType_Encoding_DefaultBinary, Identifiers.HasEncoding, Identifiers.NetworkGroupDataType.expanded(), false));
        node.addReference(new Reference(Identifiers.NetworkGroupDataType_Encoding_DefaultBinary, Identifiers.HasDescription, Identifiers.OpcUa_BinarySchema_NetworkGroupDataType.expanded(), true));
        node.addReference(new Reference(Identifiers.NetworkGroupDataType_Encoding_DefaultBinary, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode253() {
        BaseObjectTypeNode node = new BaseObjectTypeNode(this.context, Identifiers.ProgramStateMachineType_FinalResultData, new QualifiedName(0, "FinalResultData"), new LocalizedText("en", "FinalResultData"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_FinalResultData, Identifiers.HasTypeDefinition, Identifiers.BaseObjectType.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_FinalResultData, Identifiers.HasModellingRule, Identifiers.ModellingRule_Optional.expanded(), true));
        node.addReference(new Reference(Identifiers.ProgramStateMachineType_FinalResultData, Identifiers.HasComponent, Identifiers.ProgramStateMachineType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode254() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.AxisInformation_Encoding_DefaultXml, new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.AxisInformation_Encoding_DefaultXml, Identifiers.HasEncoding, Identifiers.AxisInformation.expanded(), false));
        node.addReference(new Reference(Identifiers.AxisInformation_Encoding_DefaultXml, Identifiers.HasDescription, Identifiers.OpcUa_XmlSchema_AxisInformation.expanded(), true));
        node.addReference(new Reference(Identifiers.AxisInformation_Encoding_DefaultXml, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode255() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.XVType_Encoding_DefaultXml, new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.XVType_Encoding_DefaultXml, Identifiers.HasEncoding, Identifiers.XVType.expanded(), false));
        node.addReference(new Reference(Identifiers.XVType_Encoding_DefaultXml, Identifiers.HasDescription, Identifiers.OpcUa_XmlSchema_XVType.expanded(), true));
        node.addReference(new Reference(Identifiers.XVType_Encoding_DefaultXml, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode256() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.AxisInformation_Encoding_DefaultBinary, new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.AxisInformation_Encoding_DefaultBinary, Identifiers.HasEncoding, Identifiers.AxisInformation.expanded(), false));
        node.addReference(new Reference(Identifiers.AxisInformation_Encoding_DefaultBinary, Identifiers.HasDescription, Identifiers.OpcUa_BinarySchema_AxisInformation.expanded(), true));
        node.addReference(new Reference(Identifiers.AxisInformation_Encoding_DefaultBinary, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode257() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.XVType_Encoding_DefaultBinary, new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.XVType_Encoding_DefaultBinary, Identifiers.HasEncoding, Identifiers.XVType.expanded(), false));
        node.addReference(new Reference(Identifiers.XVType_Encoding_DefaultBinary, Identifiers.HasDescription, Identifiers.OpcUa_BinarySchema_XVType.expanded(), true));
        node.addReference(new Reference(Identifiers.XVType_Encoding_DefaultBinary, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode258() {
        SessionDiagnosticsObjectTypeNode node = new SessionDiagnosticsObjectTypeNode(this.context, Identifiers.SessionsDiagnosticsSummaryType_ClientName_Placeholder, new QualifiedName(0, "<ClientName>"), new LocalizedText("en", "<ClientName>"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.SessionsDiagnosticsSummaryType_ClientName_Placeholder, Identifiers.HasComponent, Identifiers.SessionsDiagnosticsSummaryType_ClientName_Placeholder_SessionDiagnostics.expanded(), true));
        node.addReference(new Reference(Identifiers.SessionsDiagnosticsSummaryType_ClientName_Placeholder, Identifiers.HasComponent, Identifiers.SessionsDiagnosticsSummaryType_ClientName_Placeholder_SessionSecurityDiagnostics.expanded(), true));
        node.addReference(new Reference(Identifiers.SessionsDiagnosticsSummaryType_ClientName_Placeholder, Identifiers.HasComponent, Identifiers.SessionsDiagnosticsSummaryType_ClientName_Placeholder_SubscriptionDiagnosticsArray.expanded(), true));
        node.addReference(new Reference(Identifiers.SessionsDiagnosticsSummaryType_ClientName_Placeholder, Identifiers.HasTypeDefinition, Identifiers.SessionDiagnosticsObjectType.expanded(), true));
        node.addReference(new Reference(Identifiers.SessionsDiagnosticsSummaryType_ClientName_Placeholder, Identifiers.HasModellingRule, Identifiers.ModellingRule_OptionalPlaceholder.expanded(), true));
        node.addReference(new Reference(Identifiers.SessionsDiagnosticsSummaryType_ClientName_Placeholder, Identifiers.HasComponent, Identifiers.SessionsDiagnosticsSummaryType.expanded(), false));
        this.nodeManager.addNode(node);
    }

    private void loadNode259() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.ComplexNumberType_Encoding_DefaultXml, new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.ComplexNumberType_Encoding_DefaultXml, Identifiers.HasEncoding, Identifiers.ComplexNumberType.expanded(), false));
        node.addReference(new Reference(Identifiers.ComplexNumberType_Encoding_DefaultXml, Identifiers.HasDescription, Identifiers.OpcUa_XmlSchema_ComplexNumberType.expanded(), true));
        node.addReference(new Reference(Identifiers.ComplexNumberType_Encoding_DefaultXml, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode260() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.DoubleComplexNumberType_Encoding_DefaultXml, new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.DoubleComplexNumberType_Encoding_DefaultXml, Identifiers.HasEncoding, Identifiers.DoubleComplexNumberType.expanded(), false));
        node.addReference(new Reference(Identifiers.DoubleComplexNumberType_Encoding_DefaultXml, Identifiers.HasDescription, Identifiers.OpcUa_XmlSchema_DoubleComplexNumberType.expanded(), true));
        node.addReference(new Reference(Identifiers.DoubleComplexNumberType_Encoding_DefaultXml, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode261() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.ComplexNumberType_Encoding_DefaultBinary, new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.ComplexNumberType_Encoding_DefaultBinary, Identifiers.HasEncoding, Identifiers.ComplexNumberType.expanded(), false));
        node.addReference(new Reference(Identifiers.ComplexNumberType_Encoding_DefaultBinary, Identifiers.HasDescription, Identifiers.OpcUa_BinarySchema_ComplexNumberType.expanded(), true));
        node.addReference(new Reference(Identifiers.ComplexNumberType_Encoding_DefaultBinary, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode262() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.DoubleComplexNumberType_Encoding_DefaultBinary, new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.DoubleComplexNumberType_Encoding_DefaultBinary, Identifiers.HasEncoding, Identifiers.DoubleComplexNumberType.expanded(), false));
        node.addReference(new Reference(Identifiers.DoubleComplexNumberType_Encoding_DefaultBinary, Identifiers.HasDescription, Identifiers.OpcUa_BinarySchema_DoubleComplexNumberType.expanded(), true));
        node.addReference(new Reference(Identifiers.DoubleComplexNumberType_Encoding_DefaultBinary, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode263() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.ServerOnNetwork_Encoding_DefaultXml, new QualifiedName(0, "Default XML"), new LocalizedText("en", "Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.ServerOnNetwork_Encoding_DefaultXml, Identifiers.HasEncoding, Identifiers.ServerOnNetwork.expanded(), false));
        node.addReference(new Reference(Identifiers.ServerOnNetwork_Encoding_DefaultXml, Identifiers.HasDescription, Identifiers.OpcUa_XmlSchema_ServerOnNetwork.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerOnNetwork_Encoding_DefaultXml, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        this.nodeManager.addNode(node);
    }

    private void loadNode264() {
        DataTypeEncodingTypeNode node = new DataTypeEncodingTypeNode(this.context, Identifiers.ServerOnNetwork_Encoding_DefaultBinary, new QualifiedName(0, "Default Binary"), new LocalizedText("en", "Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0), UInteger.valueOf(0), UByte.valueOf(0));
        node.addReference(new Reference(Identifiers.ServerOnNetwork_Encoding_DefaultBinary, Identifiers.HasEncoding, Identifiers.ServerOnNetwork.expanded(), false));
        node.addReference(new Reference(Identifiers.ServerOnNetwork_Encoding_DefaultBinary, Identifiers.HasDescription, Identifiers.OpcUa_BinarySchema_ServerOnNetwork.expanded(), true));
        node.addReference(new Reference(Identifiers.ServerOnNetwork_Encoding_DefaultBinary, Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
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
        loadNode130();
        loadNode131();
        loadNode132();
        loadNode133();
        loadNode134();
        loadNode135();
        loadNode136();
        loadNode137();
        loadNode138();
        loadNode139();
        loadNode140();
        loadNode141();
        loadNode142();
        loadNode143();
        loadNode144();
        loadNode145();
        loadNode146();
        loadNode147();
        loadNode148();
        loadNode149();
        loadNode150();
        loadNode151();
        loadNode152();
        loadNode153();
        loadNode154();
        loadNode155();
        loadNode156();
        loadNode157();
        loadNode158();
        loadNode159();
        loadNode160();
        loadNode161();
        loadNode162();
        loadNode163();
        loadNode164();
        loadNode165();
        loadNode166();
        loadNode167();
        loadNode168();
        loadNode169();
        loadNode170();
        loadNode171();
        loadNode172();
        loadNode173();
        loadNode174();
        loadNode175();
        loadNode176();
        loadNode177();
        loadNode178();
        loadNode179();
        loadNode180();
        loadNode181();
        loadNode182();
        loadNode183();
        loadNode184();
        loadNode185();
        loadNode186();
        loadNode187();
        loadNode188();
        loadNode189();
        loadNode190();
        loadNode191();
        loadNode192();
        loadNode193();
        loadNode194();
        loadNode195();
        loadNode196();
        loadNode197();
        loadNode198();
        loadNode199();
        loadNode200();
        loadNode201();
        loadNode202();
        loadNode203();
        loadNode204();
        loadNode205();
        loadNode206();
        loadNode207();
        loadNode208();
        loadNode209();
        loadNode210();
        loadNode211();
        loadNode212();
        loadNode213();
        loadNode214();
        loadNode215();
        loadNode216();
        loadNode217();
        loadNode218();
        loadNode219();
        loadNode220();
        loadNode221();
        loadNode222();
        loadNode223();
        loadNode224();
        loadNode225();
        loadNode226();
        loadNode227();
        loadNode228();
        loadNode229();
        loadNode230();
        loadNode231();
        loadNode232();
        loadNode233();
        loadNode234();
        loadNode235();
        loadNode236();
        loadNode237();
        loadNode238();
        loadNode239();
        loadNode240();
        loadNode241();
        loadNode242();
        loadNode243();
        loadNode244();
        loadNode245();
        loadNode246();
        loadNode247();
        loadNode248();
        loadNode249();
        loadNode250();
        loadNode251();
        loadNode252();
        loadNode253();
        loadNode254();
        loadNode255();
        loadNode256();
        loadNode257();
        loadNode258();
        loadNode259();
        loadNode260();
        loadNode261();
        loadNode262();
        loadNode263();
        loadNode264();
    }
}
