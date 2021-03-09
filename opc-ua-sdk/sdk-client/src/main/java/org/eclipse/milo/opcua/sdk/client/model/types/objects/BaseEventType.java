package org.eclipse.milo.opcua.sdk.client.model.types.objects;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;
import org.eclipse.milo.opcua.stack.core.types.structured.TimeZoneDataType;

public interface BaseEventType extends BaseObjectType {
    QualifiedProperty<ByteString> EVENT_ID = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "EventId",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15"),
        ValueRanks.Scalar,
        ByteString.class
    );

    QualifiedProperty<NodeId> EVENT_TYPE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "EventType",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=17"),
        ValueRanks.Scalar,
        NodeId.class
    );

    QualifiedProperty<NodeId> SOURCE_NODE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "SourceNode",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=17"),
        ValueRanks.Scalar,
        NodeId.class
    );

    QualifiedProperty<String> SOURCE_NAME = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "SourceName",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=12"),
        ValueRanks.Scalar,
        String.class
    );

    QualifiedProperty<DateTime> TIME = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "Time",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=294"),
        ValueRanks.Scalar,
        DateTime.class
    );

    QualifiedProperty<DateTime> RECEIVE_TIME = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "ReceiveTime",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=294"),
        ValueRanks.Scalar,
        DateTime.class
    );

    QualifiedProperty<TimeZoneDataType> LOCAL_TIME = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "LocalTime",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=8912"),
        ValueRanks.Scalar,
        TimeZoneDataType.class
    );

    QualifiedProperty<LocalizedText> MESSAGE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "Message",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=21"),
        ValueRanks.Scalar,
        LocalizedText.class
    );

    QualifiedProperty<UShort> SEVERITY = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "Severity",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=5"),
        ValueRanks.Scalar,
        UShort.class
    );

    /**
     * Get the local value of the EventId Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the EventId Node.
     * @throws UaException if an error occurs creating or getting the EventId Node.
     */
    ByteString getEventId() throws UaException;

    /**
     * Set the local value of the EventId Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param eventId the local value to set for the EventId Node.
     * @throws UaException if an error occurs creating or getting the EventId Node.
     */
    void setEventId(ByteString eventId) throws UaException;

    /**
     * Read the value of the EventId Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link ByteString} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    ByteString readEventId() throws UaException;

    /**
     * Write a new value for the EventId Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param eventId the {@link ByteString} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeEventId(ByteString eventId) throws UaException;

    /**
     * An asynchronous implementation of {@link #readEventId()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends ByteString> readEventIdAsync();

    /**
     * An asynchronous implementation of {@link #writeEventId(ByteString)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeEventIdAsync(ByteString eventId);

    /**
     * Get the EventId {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the EventId {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getEventIdNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getEventIdNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getEventIdNodeAsync();

    /**
     * Get the local value of the EventType Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the EventType Node.
     * @throws UaException if an error occurs creating or getting the EventType Node.
     */
    NodeId getEventType() throws UaException;

    /**
     * Set the local value of the EventType Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param eventType the local value to set for the EventType Node.
     * @throws UaException if an error occurs creating or getting the EventType Node.
     */
    void setEventType(NodeId eventType) throws UaException;

    /**
     * Read the value of the EventType Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link NodeId} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    NodeId readEventType() throws UaException;

    /**
     * Write a new value for the EventType Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param eventType the {@link NodeId} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeEventType(NodeId eventType) throws UaException;

    /**
     * An asynchronous implementation of {@link #readEventType()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends NodeId> readEventTypeAsync();

    /**
     * An asynchronous implementation of {@link #writeEventType(NodeId)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeEventTypeAsync(NodeId eventType);

    /**
     * Get the EventType {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the EventType {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getEventTypeNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getEventTypeNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getEventTypeNodeAsync();

    /**
     * Get the local value of the SourceNode Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the SourceNode Node.
     * @throws UaException if an error occurs creating or getting the SourceNode Node.
     */
    NodeId getSourceNode() throws UaException;

    /**
     * Set the local value of the SourceNode Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param sourceNode the local value to set for the SourceNode Node.
     * @throws UaException if an error occurs creating or getting the SourceNode Node.
     */
    void setSourceNode(NodeId sourceNode) throws UaException;

    /**
     * Read the value of the SourceNode Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link NodeId} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    NodeId readSourceNode() throws UaException;

    /**
     * Write a new value for the SourceNode Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param sourceNode the {@link NodeId} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeSourceNode(NodeId sourceNode) throws UaException;

    /**
     * An asynchronous implementation of {@link #readSourceNode()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends NodeId> readSourceNodeAsync();

    /**
     * An asynchronous implementation of {@link #writeSourceNode(NodeId)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeSourceNodeAsync(NodeId sourceNode);

    /**
     * Get the SourceNode {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the SourceNode {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getSourceNodeNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getSourceNodeNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getSourceNodeNodeAsync();

    /**
     * Get the local value of the SourceName Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the SourceName Node.
     * @throws UaException if an error occurs creating or getting the SourceName Node.
     */
    String getSourceName() throws UaException;

    /**
     * Set the local value of the SourceName Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param sourceName the local value to set for the SourceName Node.
     * @throws UaException if an error occurs creating or getting the SourceName Node.
     */
    void setSourceName(String sourceName) throws UaException;

    /**
     * Read the value of the SourceName Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link String} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    String readSourceName() throws UaException;

    /**
     * Write a new value for the SourceName Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param sourceName the {@link String} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeSourceName(String sourceName) throws UaException;

    /**
     * An asynchronous implementation of {@link #readSourceName()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends String> readSourceNameAsync();

    /**
     * An asynchronous implementation of {@link #writeSourceName(String)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeSourceNameAsync(String sourceName);

    /**
     * Get the SourceName {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the SourceName {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getSourceNameNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getSourceNameNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getSourceNameNodeAsync();

    /**
     * Get the local value of the Time Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the Time Node.
     * @throws UaException if an error occurs creating or getting the Time Node.
     */
    DateTime getTime() throws UaException;

    /**
     * Set the local value of the Time Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param time the local value to set for the Time Node.
     * @throws UaException if an error occurs creating or getting the Time Node.
     */
    void setTime(DateTime time) throws UaException;

    /**
     * Read the value of the Time Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link DateTime} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    DateTime readTime() throws UaException;

    /**
     * Write a new value for the Time Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param time the {@link DateTime} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeTime(DateTime time) throws UaException;

    /**
     * An asynchronous implementation of {@link #readTime()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends DateTime> readTimeAsync();

    /**
     * An asynchronous implementation of {@link #writeTime(DateTime)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeTimeAsync(DateTime time);

    /**
     * Get the Time {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the Time {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getTimeNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getTimeNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getTimeNodeAsync();

    /**
     * Get the local value of the ReceiveTime Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the ReceiveTime Node.
     * @throws UaException if an error occurs creating or getting the ReceiveTime Node.
     */
    DateTime getReceiveTime() throws UaException;

    /**
     * Set the local value of the ReceiveTime Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param receiveTime the local value to set for the ReceiveTime Node.
     * @throws UaException if an error occurs creating or getting the ReceiveTime Node.
     */
    void setReceiveTime(DateTime receiveTime) throws UaException;

    /**
     * Read the value of the ReceiveTime Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link DateTime} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    DateTime readReceiveTime() throws UaException;

    /**
     * Write a new value for the ReceiveTime Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param receiveTime the {@link DateTime} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeReceiveTime(DateTime receiveTime) throws UaException;

    /**
     * An asynchronous implementation of {@link #readReceiveTime()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends DateTime> readReceiveTimeAsync();

    /**
     * An asynchronous implementation of {@link #writeReceiveTime(DateTime)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeReceiveTimeAsync(DateTime receiveTime);

    /**
     * Get the ReceiveTime {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the ReceiveTime {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getReceiveTimeNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getReceiveTimeNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getReceiveTimeNodeAsync();

    /**
     * Get the local value of the LocalTime Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the LocalTime Node.
     * @throws UaException if an error occurs creating or getting the LocalTime Node.
     */
    TimeZoneDataType getLocalTime() throws UaException;

    /**
     * Set the local value of the LocalTime Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param localTime the local value to set for the LocalTime Node.
     * @throws UaException if an error occurs creating or getting the LocalTime Node.
     */
    void setLocalTime(TimeZoneDataType localTime) throws UaException;

    /**
     * Read the value of the LocalTime Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link TimeZoneDataType} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    TimeZoneDataType readLocalTime() throws UaException;

    /**
     * Write a new value for the LocalTime Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param localTime the {@link TimeZoneDataType} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeLocalTime(TimeZoneDataType localTime) throws UaException;

    /**
     * An asynchronous implementation of {@link #readLocalTime()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends TimeZoneDataType> readLocalTimeAsync();

    /**
     * An asynchronous implementation of {@link #writeLocalTime(TimeZoneDataType)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeLocalTimeAsync(TimeZoneDataType localTime);

    /**
     * Get the LocalTime {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the LocalTime {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getLocalTimeNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getLocalTimeNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getLocalTimeNodeAsync();

    /**
     * Get the local value of the Message Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the Message Node.
     * @throws UaException if an error occurs creating or getting the Message Node.
     */
    LocalizedText getMessage() throws UaException;

    /**
     * Set the local value of the Message Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param message the local value to set for the Message Node.
     * @throws UaException if an error occurs creating or getting the Message Node.
     */
    void setMessage(LocalizedText message) throws UaException;

    /**
     * Read the value of the Message Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link LocalizedText} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    LocalizedText readMessage() throws UaException;

    /**
     * Write a new value for the Message Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param message the {@link LocalizedText} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeMessage(LocalizedText message) throws UaException;

    /**
     * An asynchronous implementation of {@link #readMessage()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends LocalizedText> readMessageAsync();

    /**
     * An asynchronous implementation of {@link #writeMessage(LocalizedText)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeMessageAsync(LocalizedText message);

    /**
     * Get the Message {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the Message {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getMessageNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getMessageNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getMessageNodeAsync();

    /**
     * Get the local value of the Severity Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the Severity Node.
     * @throws UaException if an error occurs creating or getting the Severity Node.
     */
    UShort getSeverity() throws UaException;

    /**
     * Set the local value of the Severity Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param severity the local value to set for the Severity Node.
     * @throws UaException if an error occurs creating or getting the Severity Node.
     */
    void setSeverity(UShort severity) throws UaException;

    /**
     * Read the value of the Severity Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link UShort} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    UShort readSeverity() throws UaException;

    /**
     * Write a new value for the Severity Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param severity the {@link UShort} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeSeverity(UShort severity) throws UaException;

    /**
     * An asynchronous implementation of {@link #readSeverity()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends UShort> readSeverityAsync();

    /**
     * An asynchronous implementation of {@link #writeSeverity(UShort)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeSeverityAsync(UShort severity);

    /**
     * Get the Severity {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the Severity {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getSeverityNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getSeverityNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getSeverityNodeAsync();
}
