/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.api;

import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.structured.AddNodesItem;
import org.eclipse.milo.opcua.stack.core.types.structured.AddReferencesItem;
import org.eclipse.milo.opcua.stack.core.types.structured.CallMethodRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteNodesItem;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteReferencesItem;
import org.eclipse.milo.opcua.stack.core.types.structured.HistoryReadValueId;
import org.eclipse.milo.opcua.stack.core.types.structured.HistoryUpdateDetails;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadValueId;
import org.eclipse.milo.opcua.stack.core.types.structured.WriteValue;

public interface AddressSpaceFilter {

    //region ViewServices

    boolean filterBrowse(OpcUaServer server, NodeId nodeId);

    //endregion

    //region AttributeServices

    boolean filterRead(OpcUaServer server, ReadValueId readValueId);

    boolean filterWrite(OpcUaServer server, WriteValue writeValue);

    boolean filterHistoryRead(OpcUaServer server, HistoryReadValueId historyReadValueId);

    boolean filterHistoryUpdate(OpcUaServer server, HistoryUpdateDetails historyUpdateDetails);

    //endregion

    //region MethodServices

    boolean filterCall(OpcUaServer server, CallMethodRequest callMethodRequest);

    //endregion

    //region MonitoredItemServices

    boolean filterOnCreateDataItem(OpcUaServer server, ReadValueId readValueId);

    boolean filterOnModifyDataItem(OpcUaServer server, ReadValueId readValueId);

    boolean filterOnCreateEventItem(OpcUaServer server, ReadValueId readValueId);

    boolean filterOnModifyEventItem(OpcUaServer server, ReadValueId readValueId);

    boolean filterOnDataItemsCreated(OpcUaServer server, ReadValueId readValueId);

    boolean filterOnDataItemsModified(OpcUaServer server, ReadValueId readValueId);

    boolean filterOnDataItemsDeleted(OpcUaServer server, ReadValueId readValueId);

    boolean filterOnEventItemsCreated(OpcUaServer server, ReadValueId readValueId);

    boolean filterOnEventItemsModified(OpcUaServer server, ReadValueId readValueId);

    boolean filterOnEventItemsDeleted(OpcUaServer server, ReadValueId readValueId);

    boolean filterOnMonitoringModeChanged(OpcUaServer server, ReadValueId readValueId);

    //endregion

    //region NodeManagementServices

    boolean filterAddNodes(OpcUaServer server, AddNodesItem addNodesItem);

    boolean filterDeleteNodes(OpcUaServer server, DeleteNodesItem deleteNodesItem);

    boolean filterAddReferences(OpcUaServer server, AddReferencesItem addReferencesItem);

    boolean filterDeleteReferences(OpcUaServer server, DeleteReferencesItem deleteReferencesItem);

    //endregion

}
