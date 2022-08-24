/*
 * Copyright (c) 2022 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.model.types.objects;

import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.core.nodes.MethodNode;
import org.eclipse.milo.opcua.sdk.server.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.structured.EndpointType;
import org.eclipse.milo.opcua.stack.core.types.structured.IdentityMappingRuleType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part18/4.4.1">https://reference.opcfoundation.org/v105/Core/docs/Part18/4.4.1</a>
 */
public interface RoleType extends BaseObjectType {
    QualifiedProperty<IdentityMappingRuleType[]> IDENTITIES = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "Identities",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15634"),
        1,
        IdentityMappingRuleType[].class
    );

    QualifiedProperty<Boolean> APPLICATIONS_EXCLUDE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "ApplicationsExclude",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=1"),
        -1,
        Boolean.class
    );

    QualifiedProperty<String[]> APPLICATIONS = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "Applications",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=12"),
        1,
        String[].class
    );

    QualifiedProperty<Boolean> ENDPOINTS_EXCLUDE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "EndpointsExclude",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=1"),
        -1,
        Boolean.class
    );

    QualifiedProperty<EndpointType[]> ENDPOINTS = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "Endpoints",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15528"),
        1,
        EndpointType[].class
    );

    QualifiedProperty<Boolean> CUSTOM_CONFIGURATION = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "CustomConfiguration",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=1"),
        -1,
        Boolean.class
    );

    IdentityMappingRuleType[] getIdentities();

    void setIdentities(IdentityMappingRuleType[] value);

    PropertyType getIdentitiesNode();

    Boolean getApplicationsExclude();

    void setApplicationsExclude(Boolean value);

    PropertyType getApplicationsExcludeNode();

    String[] getApplications();

    void setApplications(String[] value);

    PropertyType getApplicationsNode();

    Boolean getEndpointsExclude();

    void setEndpointsExclude(Boolean value);

    PropertyType getEndpointsExcludeNode();

    EndpointType[] getEndpoints();

    void setEndpoints(EndpointType[] value);

    PropertyType getEndpointsNode();

    Boolean getCustomConfiguration();

    void setCustomConfiguration(Boolean value);

    PropertyType getCustomConfigurationNode();

    MethodNode getAddIdentityMethodNode();

    MethodNode getRemoveIdentityMethodNode();

    MethodNode getAddApplicationMethodNode();

    MethodNode getRemoveApplicationMethodNode();

    MethodNode getAddEndpointMethodNode();

    MethodNode getRemoveEndpointMethodNode();
}
