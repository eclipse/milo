/*
 * Copyright (c) 2021 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.client.dtd;

import java.util.Map;

import com.google.common.collect.ImmutableMap;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.jetbrains.annotations.Nullable;

abstract class BuiltinDataTypeInfo {
    private static final Map<String, DataTypeInfo> DATA_TYPE_INFO_MAP = ImmutableMap.<String, DataTypeInfo>builder()
        .put("AddNodesItem", new DataTypeInfo(NodeId.parse("ns=0;i=376"), NodeId.parse("ns=0;i=378")))
        .put("AddReferencesItem", new DataTypeInfo(NodeId.parse("ns=0;i=379"), NodeId.parse("ns=0;i=381")))
        .put("AggregateConfiguration", new DataTypeInfo(NodeId.parse("ns=0;i=948"), NodeId.parse("ns=0;i=950")))
        .put("Annotation", new DataTypeInfo(NodeId.parse("ns=0;i=891"), NodeId.parse("ns=0;i=893")))
        .put("AnonymousIdentityToken", new DataTypeInfo(NodeId.parse("ns=0;i=319"), NodeId.parse("ns=0;i=321")))
        .put("ApplicationDescription", new DataTypeInfo(NodeId.parse("ns=0;i=308"), NodeId.parse("ns=0;i=310")))
        .put("Argument", new DataTypeInfo(NodeId.parse("ns=0;i=296"), NodeId.parse("ns=0;i=298")))
        .put("AttributeOperand", new DataTypeInfo(NodeId.parse("ns=0;i=598"), NodeId.parse("ns=0;i=600")))
        .put("AxisInformation", new DataTypeInfo(NodeId.parse("ns=0;i=12079"), NodeId.parse("ns=0;i=12089")))
        .put("BuildInfo", new DataTypeInfo(NodeId.parse("ns=0;i=338"), NodeId.parse("ns=0;i=340")))
        .put("ComplexNumberType", new DataTypeInfo(NodeId.parse("ns=0;i=12171"), NodeId.parse("ns=0;i=12181")))
        .put("ContentFilter", new DataTypeInfo(NodeId.parse("ns=0;i=586"), NodeId.parse("ns=0;i=588")))
        .put("ContentFilterElement", new DataTypeInfo(NodeId.parse("ns=0;i=583"), NodeId.parse("ns=0;i=585")))
        .put("DeleteNodesItem", new DataTypeInfo(NodeId.parse("ns=0;i=382"), NodeId.parse("ns=0;i=384")))
        .put("DeleteReferencesItem", new DataTypeInfo(NodeId.parse("ns=0;i=385"), NodeId.parse("ns=0;i=387")))
        .put("DiscoveryConfiguration", new DataTypeInfo(NodeId.parse("ns=0;i=12890"), NodeId.parse("ns=0;i=12900")))
        .put("DoubleComplexNumberType", new DataTypeInfo(NodeId.parse("ns=0;i=12172"), NodeId.parse("ns=0;i=12182")))
        .put("EUInformation", new DataTypeInfo(NodeId.parse("ns=0;i=887"), NodeId.parse("ns=0;i=889")))
        .put("ElementOperand", new DataTypeInfo(NodeId.parse("ns=0;i=592"), NodeId.parse("ns=0;i=594")))
        .put("EndpointConfiguration", new DataTypeInfo(NodeId.parse("ns=0;i=331"), NodeId.parse("ns=0;i=333")))
        .put("EndpointDescription", new DataTypeInfo(NodeId.parse("ns=0;i=312"), NodeId.parse("ns=0;i=314")))
        .put("EndpointUrlListDataType", new DataTypeInfo(NodeId.parse("ns=0;i=11943"), NodeId.parse("ns=0;i=11957")))
        .put("EnumValueType", new DataTypeInfo(NodeId.parse("ns=0;i=7594"), NodeId.parse("ns=0;i=8251")))
        .put("EventFilter", new DataTypeInfo(NodeId.parse("ns=0;i=725"), NodeId.parse("ns=0;i=727")))
        .put("FilterOperand", new DataTypeInfo(NodeId.parse("ns=0;i=589"), NodeId.parse("ns=0;i=591")))
        .put("HistoryEvent", new DataTypeInfo(NodeId.parse("ns=0;i=659"), NodeId.parse("ns=0;i=661")))
        .put("HistoryEventFieldList", new DataTypeInfo(NodeId.parse("ns=0;i=920"), NodeId.parse("ns=0;i=922")))
        .put("IssuedIdentityToken", new DataTypeInfo(NodeId.parse("ns=0;i=938"), NodeId.parse("ns=0;i=940")))
        .put("LiteralOperand", new DataTypeInfo(NodeId.parse("ns=0;i=595"), NodeId.parse("ns=0;i=597")))
        .put("MdnsDiscoveryConfiguration", new DataTypeInfo(NodeId.parse("ns=0;i=12891"), NodeId.parse("ns=0;i=12901")))
        .put("ModelChangeStructureDataType", new DataTypeInfo(NodeId.parse("ns=0;i=877"), NodeId.parse("ns=0;i=879")))
        .put("MonitoringFilter", new DataTypeInfo(NodeId.parse("ns=0;i=719"), NodeId.parse("ns=0;i=721")))
        .put("NetworkGroupDataType", new DataTypeInfo(NodeId.parse("ns=0;i=11944"), NodeId.parse("ns=0;i=11958")))
        .put("OptionSet", new DataTypeInfo(NodeId.parse("ns=0;i=12755"), NodeId.parse("ns=0;i=12765")))
        .put("ProgramDiagnosticDataType", new DataTypeInfo(NodeId.parse("ns=0;i=894"), NodeId.parse("ns=0;i=896")))
        .put("Range", new DataTypeInfo(NodeId.parse("ns=0;i=884"), NodeId.parse("ns=0;i=886")))
        .put("RedundantServerDataType", new DataTypeInfo(NodeId.parse("ns=0;i=853"), NodeId.parse("ns=0;i=855")))
        .put("RegisteredServer", new DataTypeInfo(NodeId.parse("ns=0;i=432"), NodeId.parse("ns=0;i=434")))
        .put("RelativePath", new DataTypeInfo(NodeId.parse("ns=0;i=540"), NodeId.parse("ns=0;i=542")))
        .put("RelativePathElement", new DataTypeInfo(NodeId.parse("ns=0;i=537"), NodeId.parse("ns=0;i=539")))
        .put("SamplingIntervalDiagnosticsDataType", new DataTypeInfo(NodeId.parse("ns=0;i=856"), NodeId.parse("ns=0;i=858")))
        .put("SemanticChangeStructureDataType", new DataTypeInfo(NodeId.parse("ns=0;i=897"), NodeId.parse("ns=0;i=899")))
        .put("ServerDiagnosticsSummaryDataType", new DataTypeInfo(NodeId.parse("ns=0;i=859"), NodeId.parse("ns=0;i=861")))
        .put("ServerOnNetwork", new DataTypeInfo(NodeId.parse("ns=0;i=12189"), NodeId.parse("ns=0;i=12207")))
        .put("ServerStatusDataType", new DataTypeInfo(NodeId.parse("ns=0;i=862"), NodeId.parse("ns=0;i=864")))
        .put("ServiceCounterDataType", new DataTypeInfo(NodeId.parse("ns=0;i=871"), NodeId.parse("ns=0;i=873")))
        .put("SessionDiagnosticsDataType", new DataTypeInfo(NodeId.parse("ns=0;i=865"), NodeId.parse("ns=0;i=867")))
        .put("SessionSecurityDiagnosticsDataType", new DataTypeInfo(NodeId.parse("ns=0;i=868"), NodeId.parse("ns=0;i=870")))
        .put("SignedSoftwareCertificate", new DataTypeInfo(NodeId.parse("ns=0;i=344"), NodeId.parse("ns=0;i=346")))
        .put("SimpleAttributeOperand", new DataTypeInfo(NodeId.parse("ns=0;i=601"), NodeId.parse("ns=0;i=603")))
        .put("StatusResult", new DataTypeInfo(NodeId.parse("ns=0;i=299"), NodeId.parse("ns=0;i=301")))
        .put("SubscriptionDiagnosticsDataType", new DataTypeInfo(NodeId.parse("ns=0;i=874"), NodeId.parse("ns=0;i=876")))
        .put("TimeZoneDataType", new DataTypeInfo(NodeId.parse("ns=0;i=8912"), NodeId.parse("ns=0;i=8917")))
        .put("TrustListDataType", new DataTypeInfo(NodeId.parse("ns=0;i=12554"), NodeId.parse("ns=0;i=12680")))
        .put("Union", new DataTypeInfo(NodeId.parse("ns=0;i=12756"), NodeId.parse("ns=0;i=12766")))
        .put("UserIdentityToken", new DataTypeInfo(NodeId.parse("ns=0;i=316"), NodeId.parse("ns=0;i=318")))
        .put("UserNameIdentityToken", new DataTypeInfo(NodeId.parse("ns=0;i=322"), NodeId.parse("ns=0;i=324")))
        .put("UserTokenPolicy", new DataTypeInfo(NodeId.parse("ns=0;i=304"), NodeId.parse("ns=0;i=306")))
        .put("X509IdentityToken", new DataTypeInfo(NodeId.parse("ns=0;i=325"), NodeId.parse("ns=0;i=327")))
        .put("XVType", new DataTypeInfo(NodeId.parse("ns=0;i=12080"), NodeId.parse("ns=0;i=12090")))
        .build();

    @Nullable
    static DataTypeInfo getDataTypeInfo(String description) {
        return DATA_TYPE_INFO_MAP.get(description);
    }

    static class DataTypeInfo {
        final NodeId dataTypeId;

        final NodeId encodingId;

        DataTypeInfo(NodeId dataTypeId, NodeId encodingId) {
            this.dataTypeId = dataTypeId;
            this.encodingId = encodingId;
        }
    }
}
