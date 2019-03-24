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

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.api.services.AttributeHistoryServices;
import org.eclipse.milo.opcua.sdk.server.api.services.AttributeHistoryServices.HistoryReadContext;
import org.eclipse.milo.opcua.sdk.server.api.services.AttributeHistoryServices.HistoryUpdateContext;
import org.eclipse.milo.opcua.sdk.server.api.services.AttributeServices;
import org.eclipse.milo.opcua.sdk.server.api.services.MethodServices;
import org.eclipse.milo.opcua.sdk.server.api.services.MonitoredItemServices;
import org.eclipse.milo.opcua.sdk.server.api.services.NodeManagementServices;
import org.eclipse.milo.opcua.sdk.server.api.services.NodeManagementServices.AddNodesContext;
import org.eclipse.milo.opcua.sdk.server.api.services.ViewServices;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;
import org.eclipse.milo.opcua.stack.core.types.structured.AddNodesItem;
import org.eclipse.milo.opcua.stack.core.types.structured.AddReferencesItem;
import org.eclipse.milo.opcua.stack.core.types.structured.CallMethodRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteNodesItem;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteReferencesItem;
import org.eclipse.milo.opcua.stack.core.types.structured.HistoryReadDetails;
import org.eclipse.milo.opcua.stack.core.types.structured.HistoryReadValueId;
import org.eclipse.milo.opcua.stack.core.types.structured.HistoryUpdateDetails;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadValueId;
import org.eclipse.milo.opcua.stack.core.types.structured.ViewDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.WriteValue;

import static org.eclipse.milo.opcua.sdk.server.api.services.AttributeServices.ReadContext;
import static org.eclipse.milo.opcua.sdk.server.api.services.AttributeServices.WriteContext;
import static org.eclipse.milo.opcua.sdk.server.api.services.MethodServices.CallContext;
import static org.eclipse.milo.opcua.sdk.server.api.services.NodeManagementServices.AddReferencesContext;
import static org.eclipse.milo.opcua.sdk.server.api.services.NodeManagementServices.DeleteNodesContext;
import static org.eclipse.milo.opcua.sdk.server.api.services.NodeManagementServices.DeleteReferencesContext;
import static org.eclipse.milo.opcua.sdk.server.api.services.ViewServices.BrowseContext;
import static org.eclipse.milo.opcua.sdk.server.api.services.ViewServices.RegisterNodesContext;
import static org.eclipse.milo.opcua.sdk.server.api.services.ViewServices.UnregisterNodesContext;

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
     * @see ViewServices#browse(BrowseContext, ViewDescription, NodeId)
     */
    boolean filterBrowse(OpcUaServer server, NodeId nodeId);

    /**
     * Return {@code true} if the register node operation for {@code nodeId} should be handled by the
     * {@link AddressSpace} this filter belongs to.
     *
     * @param server the {@link OpcUaServer}.
     * @param nodeId the {@link NodeId} to register.
     * @return {@code true} if the register node operation for {@code nodeId} should be handled by the
     * {@link AddressSpace} this filter belongs to.
     * @see ViewServices#registerNodes(RegisterNodesContext, List)
     */
    boolean filterRegisterNode(OpcUaServer server, NodeId nodeId);

    /**
     * Return {@code true} if the unregister node operation for {@code nodeId} should be handled by the
     * {@link AddressSpace} this filter belongs to.
     *
     * @param server the {@link OpcUaServer}.
     * @param nodeId the {@link NodeId} to unregister.
     * @return {@code true} if the unregister node operation for {@code nodeId} should be handled by the
     * {@link AddressSpace} this filter belongs to.
     * @see ViewServices#unregisterNodes(UnregisterNodesContext, List)
     */
    boolean filterUnregisterNode(OpcUaServer server, NodeId nodeId);

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
     * @see AttributeServices#read(ReadContext, Double, TimestampsToReturn, List)
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
     * @see AttributeServices#write(WriteContext, List)
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
     * @see AttributeHistoryServices#historyRead(HistoryReadContext, HistoryReadDetails, TimestampsToReturn, List)
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
     * @see AttributeHistoryServices#historyUpdate(HistoryUpdateContext, List)
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
     * @see MethodServices#call(CallContext, List)
     */
    boolean filterCall(OpcUaServer server, CallMethodRequest callMethodRequest);

    //endregion

    //region MonitoredItemServices

    /**
     * Return {@code true} if the monitored item operation for {@code readValueId} should be handled by the
     * {@link AddressSpace} this filter belongs to.
     *
     * @param server      the {@link OpcUaServer}.
     * @param readValueId the {@link ReadValueId} from the monitored item operation.
     * @return {@code true} if the monitored item operation for {@code readValueId} should be handled by the
     * {@link AddressSpace} this filter belongs to.
     * @see MonitoredItemServices#onCreateDataItem(ReadValueId, Double, UInteger, BiConsumer)
     */
    boolean filterOnCreateDataItem(OpcUaServer server, ReadValueId readValueId);

    /**
     * Return {@code true} if the monitored item operation for {@code readValueId} should be handled by the
     * {@link AddressSpace} this filter belongs to.
     *
     * @param server      the {@link OpcUaServer}.
     * @param readValueId the {@link ReadValueId} from the monitored item operation.
     * @return {@code true} if the monitored item operation for {@code readValueId} should be handled by the
     * {@link AddressSpace} this filter belongs to.
     * @see MonitoredItemServices#onModifyDataItem(ReadValueId, Double, UInteger, BiConsumer)
     */
    boolean filterOnModifyDataItem(OpcUaServer server, ReadValueId readValueId);

    /**
     * Return {@code true} if the monitored item operation for {@code readValueId} should be handled by the
     * {@link AddressSpace} this filter belongs to.
     *
     * @param server      the {@link OpcUaServer}.
     * @param readValueId the {@link ReadValueId} from the monitored item operation.
     * @return {@code true} if the monitored item operation for {@code readValueId} should be handled by the
     * {@link AddressSpace} this filter belongs to.
     * @see MonitoredItemServices#onCreateEventItem(ReadValueId, UInteger, Consumer)
     */
    boolean filterOnCreateEventItem(OpcUaServer server, ReadValueId readValueId);

    /**
     * Return {@code true} if the monitored item operation for {@code readValueId} should be handled by the
     * {@link AddressSpace} this filter belongs to.
     *
     * @param server      the {@link OpcUaServer}.
     * @param readValueId the {@link ReadValueId} from the monitored item operation.
     * @return {@code true} if the monitored item operation for {@code readValueId} should be handled by the
     * {@link AddressSpace} this filter belongs to.
     * @see MonitoredItemServices#onModifyEventItem(ReadValueId, UInteger, Consumer)
     */
    boolean filterOnModifyEventItem(OpcUaServer server, ReadValueId readValueId);

    /**
     * Return {@code true} if the monitored item operation for {@code readValueId} should be handled by the
     * {@link AddressSpace} this filter belongs to.
     *
     * @param server      the {@link OpcUaServer}.
     * @param readValueId the {@link ReadValueId} from the monitored item operation.
     * @return {@code true} if the monitored item operation for {@code readValueId} should be handled by the
     * {@link AddressSpace} this filter belongs to.
     * @see MonitoredItemServices#onDataItemsCreated(List)
     */
    boolean filterOnDataItemsCreated(OpcUaServer server, ReadValueId readValueId);

    /**
     * Return {@code true} if the monitored item operation for {@code readValueId} should be handled by the
     * {@link AddressSpace} this filter belongs to.
     *
     * @param server      the {@link OpcUaServer}.
     * @param readValueId the {@link ReadValueId} from the monitored item operation.
     * @return {@code true} if the monitored item operation for {@code readValueId} should be handled by the
     * {@link AddressSpace} this filter belongs to.
     * @see MonitoredItemServices#onDataItemsModified(List)
     */
    boolean filterOnDataItemsModified(OpcUaServer server, ReadValueId readValueId);

    /**
     * Return {@code true} if the monitored item operation for {@code readValueId} should be handled by the
     * {@link AddressSpace} this filter belongs to.
     *
     * @param server      the {@link OpcUaServer}.
     * @param readValueId the {@link ReadValueId} from the monitored item operation.
     * @return {@code true} if the monitored item operation for {@code readValueId} should be handled by the
     * {@link AddressSpace} this filter belongs to.
     * @see MonitoredItemServices#onDataItemsDeleted(List)
     */
    boolean filterOnDataItemsDeleted(OpcUaServer server, ReadValueId readValueId);

    /**
     * Return {@code true} if the monitored item operation for {@code readValueId} should be handled by the
     * {@link AddressSpace} this filter belongs to.
     *
     * @param server      the {@link OpcUaServer}.
     * @param readValueId the {@link ReadValueId} from the monitored item operation.
     * @return {@code true} if the monitored item operation for {@code readValueId} should be handled by the
     * {@link AddressSpace} this filter belongs to.
     * @see MonitoredItemServices#onEventItemsCreated(List)
     */
    boolean filterOnEventItemsCreated(OpcUaServer server, ReadValueId readValueId);

    /**
     * Return {@code true} if the monitored item operation for {@code readValueId} should be handled by the
     * {@link AddressSpace} this filter belongs to.
     *
     * @param server      the {@link OpcUaServer}.
     * @param readValueId the {@link ReadValueId} from the monitored item operation.
     * @return {@code true} if the monitored item operation for {@code readValueId} should be handled by the
     * {@link AddressSpace} this filter belongs to.
     * @see MonitoredItemServices#onEventItemsModified(List)
     */
    boolean filterOnEventItemsModified(OpcUaServer server, ReadValueId readValueId);

    /**
     * Return {@code true} if the monitored item operation for {@code readValueId} should be handled by the
     * {@link AddressSpace} this filter belongs to.
     *
     * @param server      the {@link OpcUaServer}.
     * @param readValueId the {@link ReadValueId} from the monitored item operation.
     * @return {@code true} if the monitored item operation for {@code readValueId} should be handled by the
     * {@link AddressSpace} this filter belongs to.
     * @see MonitoredItemServices#onEventItemsDeleted(List)
     */
    boolean filterOnEventItemsDeleted(OpcUaServer server, ReadValueId readValueId);

    /**
     * Return {@code true} if the monitored item operation for {@code readValueId} should be handled by the
     * {@link AddressSpace} this filter belongs to.
     *
     * @param server      the {@link OpcUaServer}.
     * @param readValueId the {@link ReadValueId} from the monitored item operation.
     * @return {@code true} if the monitored item operation for {@code readValueId} should be handled by the
     * {@link AddressSpace} this filter belongs to.
     * @see MonitoredItemServices#onMonitoringModeChanged(List)
     */
    boolean filterOnMonitoringModeChanged(OpcUaServer server, ReadValueId readValueId);

    //endregion

    //region NodeManagementServices

    /**
     * Return {@code true} if the add nodes operation for {@code addNodesItem} should be handled by the
     * {@link AddressSpace} this filter belongs to.
     *
     * @param server       the {@link OpcUaServer}.
     * @param addNodesItem the {@link AddNodesItem} from the add nodes operation.
     * @return {@code true} if the add nodes operation for {@code addNodesItem} should be handled by the
     * {@link AddressSpace} this filter belongs to.
     * @see NodeManagementServices#addNodes(AddNodesContext, List)
     */
    boolean filterAddNodes(OpcUaServer server, AddNodesItem addNodesItem);

    /**
     * Return {@code true} if the delete nodes operation for {@code deleteNodesItem} should be handled by the
     * {@link AddressSpace} this filter belongs to.
     *
     * @param server          the {@link OpcUaServer}.
     * @param deleteNodesItem the {@link DeleteNodesItem} from the delete nodes operation.
     * @return Return {@code true} if the delete nodes operation for {@code deleteNodesItem} should be handled by the
     * {@link AddressSpace} this filter belongs to.
     * @see NodeManagementServices#deleteNodes(DeleteNodesContext, List)
     */
    boolean filterDeleteNodes(OpcUaServer server, DeleteNodesItem deleteNodesItem);

    /**
     * Return {@code true} if the add references operation for {@code addReferencesItem} should be handled by the
     * {@link AddressSpace} this filter belongs to.
     *
     * @param server            the {@link OpcUaServer}.
     * @param addReferencesItem the {@link AddReferencesItem} from the add references operation.
     * @return {@code true} if the add references operation for {@code addReferencesItem} should be handled by the
     * {@link AddressSpace} this filter belongs to.
     * @see NodeManagementServices#addReferences(AddReferencesContext, List)
     */
    boolean filterAddReferences(OpcUaServer server, AddReferencesItem addReferencesItem);

    /**
     * Return {@code true} if the delete references operation for {@code deleteReferencesITem} should be handled by the
     * {@link AddressSpace} this filter belongs to.
     *
     * @param server               the {@link OpcUaServer}.
     * @param deleteReferencesItem the {@link DeleteReferencesItem} from the delete references operation.
     * @return {@code true} if the delete references operation for {@code deleteReferencesITem} should be handled by the
     * {@link AddressSpace} this filter belongs to.
     * @see NodeManagementServices#deleteReferences(DeleteReferencesContext, List)
     */
    boolean filterDeleteReferences(OpcUaServer server, DeleteReferencesItem deleteReferencesItem);

    //endregion

}
