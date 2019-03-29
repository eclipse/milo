/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.core.channel.messages;

import javax.annotation.Nonnull;

import com.google.common.base.Charsets;
import com.google.common.base.MoreObjects;
import io.netty.buffer.ByteBuf;
import org.eclipse.milo.opcua.stack.core.serialization.EncodingLimits;
import org.eclipse.milo.opcua.stack.core.util.annotations.UInt32Primitive;

import static com.google.common.base.Preconditions.checkArgument;

public class HelloMessage {

    private static final int MAX_ENDPOINT_URL_LENGTH = 4096;

    private static final EncodingLimits ENCODING_LIMITS = new EncodingLimits(
        EncodingLimits.DEFAULT_MAX_ARRAY_LENGTH,
        MAX_ENDPOINT_URL_LENGTH,
        EncodingLimits.DEFAULT_MAX_RECURSION_DEPTH
    );

    @UInt32Primitive
    private final long protocolVersion;

    @UInt32Primitive
    private final long receiveBufferSize;

    @UInt32Primitive
    private final long sendBufferSize;

    @UInt32Primitive
    private final long maxMessageSize;

    @UInt32Primitive
    private final long maxChunkCount;

    private final String endpointUrl;

    /**
     * @param protocolVersion   the latest version of the OPC UA TCP protocol supported by the Client.
     * @param receiveBufferSize the largest MessageChunk that the sender (Client) can receive. This value shall be
     *                          greater than or equal to 8192 bytes.
     * @param sendBufferSize    the largest MessageChunk that the sender (Client) will send. This value shall be
     *                          greater than or equal to 8192 bytes.
     * @param maxMessageSize    the maximum size for any response Message. The Message size is calculated using the
     *                          unencrypted Message body. A value of zero indicates that the Client has no limit.
     * @param maxChunkCount     the maximum number of chunks in any response Message. A value of zero indicates that
     *                          the Client has no limit.
     * @param endpointUrl       the URL of the Endpoint which the Client wished to connect to. The encoded value shall
     *                          be less than 4096 bytes.
     */
    public HelloMessage(@UInt32Primitive long protocolVersion,
                        @UInt32Primitive long receiveBufferSize,
                        @UInt32Primitive long sendBufferSize,
                        @UInt32Primitive long maxMessageSize,
                        @UInt32Primitive long maxChunkCount,
                        @Nonnull String endpointUrl) {

        checkArgument(receiveBufferSize >= 8192, "receiverBufferSize must be at least 8192 bytes");
        checkArgument(sendBufferSize >= 8192, "sendBufferSize must be at least 8192 bytes");
        checkArgument(
            endpointUrl.length() <= MAX_ENDPOINT_URL_LENGTH,
            "endpointUrl length cannot be greater than 4096 bytes");

        this.protocolVersion = protocolVersion;
        this.receiveBufferSize = receiveBufferSize;
        this.sendBufferSize = sendBufferSize;
        this.maxMessageSize = maxMessageSize;
        this.maxChunkCount = maxChunkCount;
        this.endpointUrl = endpointUrl;
    }

    @UInt32Primitive
    public long getProtocolVersion() {
        return protocolVersion;
    }

    @UInt32Primitive
    public long getReceiveBufferSize() {
        return receiveBufferSize;
    }

    @UInt32Primitive
    public long getSendBufferSize() {
        return sendBufferSize;
    }

    @UInt32Primitive
    public long getMaxMessageSize() {
        return maxMessageSize;
    }

    @UInt32Primitive
    public long getMaxChunkCount() {
        return maxChunkCount;
    }

    public String getEndpointUrl() {
        return endpointUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HelloMessage that = (HelloMessage) o;

        return maxChunkCount == that.maxChunkCount &&
            maxMessageSize == that.maxMessageSize &&
            protocolVersion == that.protocolVersion &&
            receiveBufferSize == that.receiveBufferSize &&
            sendBufferSize == that.sendBufferSize &&
            endpointUrl.equals(that.endpointUrl);

    }

    @Override
    public int hashCode() {
        int result = (int) (protocolVersion ^ (protocolVersion >>> 32));
        result = 31 * result + (int) (receiveBufferSize ^ (receiveBufferSize >>> 32));
        result = 31 * result + (int) (sendBufferSize ^ (sendBufferSize >>> 32));
        result = 31 * result + (int) (maxMessageSize ^ (maxMessageSize >>> 32));
        result = 31 * result + (int) (maxChunkCount ^ (maxChunkCount >>> 32));
        result = 31 * result + endpointUrl.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("protocolVersion", protocolVersion)
            .add("receiverBufferSize", receiveBufferSize)
            .add("sendBufferSize", sendBufferSize)
            .add("maxMessageSize", maxMessageSize)
            .add("maxChunkCount", maxChunkCount)
            .add("endpointUrl", endpointUrl)
            .toString();
    }

    public static void encode(HelloMessage message, ByteBuf buffer) {
        buffer.writeIntLE((int) message.getProtocolVersion());
        buffer.writeIntLE((int) message.getReceiveBufferSize());
        buffer.writeIntLE((int) message.getSendBufferSize());
        buffer.writeIntLE((int) message.getMaxMessageSize());
        buffer.writeIntLE((int) message.getMaxChunkCount());
        encodeString(message.getEndpointUrl(), buffer);
    }

    public static HelloMessage decode(ByteBuf buffer) {
        return new HelloMessage(
            buffer.readUnsignedIntLE(), /*    ProtocolVersion    */
            buffer.readUnsignedIntLE(), /*    ReceiveBufferSize  */
            buffer.readUnsignedIntLE(), /*    SendBufferSize     */
            buffer.readUnsignedIntLE(), /*    MaxMessageSize     */
            buffer.readUnsignedIntLE(), /*    MaxChunkCount      */
            decodeString(buffer)        /*    EndpointUrl        */
        );
    }

    private static void encodeString(String s, ByteBuf buffer) {
        if (s == null) {
            buffer.writeIntLE(-1);
        } else {
            buffer.writeIntLE(s.length());
            buffer.writeBytes(s.getBytes(Charsets.UTF_8));
        }
    }

    private static String decodeString(ByteBuf buffer) {
        int length = buffer.readIntLE();
        if (length == -1) {
            return null;
        } else {
            byte[] bs = new byte[length];
            buffer.readBytes(bs);
            return new String(bs, Charsets.UTF_8);
        }
    }

}
