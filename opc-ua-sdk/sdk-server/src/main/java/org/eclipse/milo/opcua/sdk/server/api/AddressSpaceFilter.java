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

    /**
     * Return {@code true} if the browse operation for {@code nodeId} should be handled by the {@link AddressSpace}
     * this filter belongs to.
     *
     * @param server the {@link OpcUaServer}.
     * @param nodeId the {@link NodeId} from the browse operation.
     * @return {@code true} if the browse operation for {@code nodeId} should be handled the the {@link AddressSpace}
     * this filter belongs to.
     */
    boolean filterBrowse(OpcUaServer server, NodeId nodeId);

    //endregion

    //region AttributeServices

    /**
     * Return {@code true} if the read operation for {@code readValueId} should be handled by the {@link AddressSpace}
     * this filter belongs to.
     *
     * @param server      the {@link OpcUaServer}.
     * @param readValueId the {@link ReadValueId} from the read operation.
     * @return {@code true} if the read operation for {@code readValueId} should be handled by the {@link AddressSpace}
     * this filter belongs to.
     */
    boolean filterRead(OpcUaServer server, ReadValueId readValueId);

    /**
     * Return {@code true} if the write operation for {@code writeValue} should be handled by the {@link AddressSpace}
     * this filter belongs to.
     *
     * @param server     the {@link OpcUaServer}.
     * @param writeValue the {@link WriteValue} from the write operation.
     * @return {@code true} if the write operation for {@code writeValue} should be handled by the {@link AddressSpace}
     * this filter belongs to.
     */
    boolean filterWrite(OpcUaServer server, WriteValue writeValue);

    /**
     * Return {@code true} if the history read operation for {@code historyReadValueId} should be handled by the
     * {@link AddressSpace} this filter belongs to.
     *
     * @param server             the {@link OpcUaServer}.
     * @param historyReadValueId the {@link HistoryReadValueId} from the history read operation.
     * @return {@code true} if the history read operation for {@code historyReadValueId} should be handled by the
     * {@link AddressSpace} this filter belongs to.
     */
    boolean filterHistoryRead(OpcUaServer server, HistoryReadValueId historyReadValueId);

    /**
     * Return {@code true} if the history update operation for {@code historyUpdateDetails} should be handled by the
     * {@link AddressSpace} this filter belongs to.
     *
     * @param server               the {@link OpcUaServer}.
     * @param historyUpdateDetails the {@link HistoryUpdateDetails} from the history update operation.
     * @return {@code true} if the history update operation for {@code historyUpdateDetails} should be handled by the
     * {@link AddressSpace} this filter belongs to.
     */
    boolean filterHistoryUpdate(OpcUaServer server, HistoryUpdateDetails historyUpdateDetails);

    //endregion

    //region MethodServices

    /**
     * Return {@code true} if the call method operation for {@code callMethodRequest} should be handled by the
     * {@link AddressSpace} this filter belongs to.
     *
     * @param server            the {@link OpcUaServer}.
     * @param callMethodRequest the {@link CallMethodRequest} from the call method operation.
     * @return {@code true} if the call method operation for {@code callMethodRequest} should be handled by the
     * {@link AddressSpace} this filter belongs to.
     */
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
