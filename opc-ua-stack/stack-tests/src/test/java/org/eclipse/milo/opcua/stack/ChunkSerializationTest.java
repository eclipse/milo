/*
 * Copyright (c) 2016 Kevin Herron
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v1.0 which accompany this distribution.
 *
 * The Eclipse Public License is available at
 *   http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 *   http://www.eclipse.org/org/documents/edl-v10.html.
 */

package org.eclipse.milo.opcua.stack;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.util.ReferenceCountUtil;
import org.eclipse.milo.opcua.stack.core.channel.ChannelConfig;
import org.eclipse.milo.opcua.stack.core.channel.ChannelParameters;
import org.eclipse.milo.opcua.stack.core.channel.ChunkDecoder;
import org.eclipse.milo.opcua.stack.core.channel.ChunkEncoder;
import org.eclipse.milo.opcua.stack.core.channel.ClientSecureChannel;
import org.eclipse.milo.opcua.stack.core.channel.SecureChannel;
import org.eclipse.milo.opcua.stack.core.channel.ServerSecureChannel;
import org.eclipse.milo.opcua.stack.core.channel.messages.MessageType;
import org.eclipse.milo.opcua.stack.core.security.SecurityPolicy;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MessageSecurityMode;
import org.eclipse.milo.opcua.stack.core.util.BufferUtil;
import org.eclipse.milo.opcua.stack.core.util.CryptoRestrictions;
import org.eclipse.milo.opcua.stack.core.util.LongSequence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ChunkSerializationTest extends SecureChannelFixture {

    static {
        CryptoRestrictions.remove();
    }

    Logger logger = LoggerFactory.getLogger(getClass());

    ChannelParameters parameters = new ChannelParameters(
        ChannelConfig.DEFAULT_MAX_MESSAGE_SIZE,
        ChannelConfig.DEFAULT_MAX_CHUNK_SIZE,
        ChannelConfig.DEFAULT_MAX_CHUNK_SIZE,
        ChannelConfig.DEFAULT_MAX_CHUNK_COUNT,
        ChannelConfig.DEFAULT_MAX_MESSAGE_SIZE,
        ChannelConfig.DEFAULT_MAX_CHUNK_SIZE,
        ChannelConfig.DEFAULT_MAX_CHUNK_SIZE,
        ChannelConfig.DEFAULT_MAX_CHUNK_COUNT
    );

    @DataProvider
    public Object[][] getAsymmetricSecurityParameters() {
        return new Object[][]{
            {SecurityPolicy.None, MessageSecurityMode.None, 128},
            {SecurityPolicy.Basic128Rsa15, MessageSecurityMode.Sign, 128},
            {SecurityPolicy.Basic128Rsa15, MessageSecurityMode.SignAndEncrypt, 128},
            {SecurityPolicy.Basic256, MessageSecurityMode.Sign, 128},
            {SecurityPolicy.Basic256, MessageSecurityMode.SignAndEncrypt, 128},
            {SecurityPolicy.Basic256Sha256, MessageSecurityMode.Sign, 128},
            {SecurityPolicy.Basic256Sha256, MessageSecurityMode.SignAndEncrypt, 128},

            {SecurityPolicy.None, MessageSecurityMode.None, ChannelConfig.DEFAULT_MAX_CHUNK_SIZE},
            {SecurityPolicy.Basic128Rsa15, MessageSecurityMode.Sign, ChannelConfig.DEFAULT_MAX_CHUNK_SIZE},
            {SecurityPolicy.Basic128Rsa15, MessageSecurityMode.SignAndEncrypt, ChannelConfig.DEFAULT_MAX_CHUNK_SIZE},
            {SecurityPolicy.Basic256, MessageSecurityMode.Sign, ChannelConfig.DEFAULT_MAX_CHUNK_SIZE},
            {SecurityPolicy.Basic256, MessageSecurityMode.SignAndEncrypt, ChannelConfig.DEFAULT_MAX_CHUNK_SIZE},
            {SecurityPolicy.Basic256Sha256, MessageSecurityMode.Sign, ChannelConfig.DEFAULT_MAX_CHUNK_SIZE},
            {SecurityPolicy.Basic256Sha256, MessageSecurityMode.SignAndEncrypt, ChannelConfig.DEFAULT_MAX_CHUNK_SIZE},
        };
    }

    @Test(dataProvider = "getAsymmetricSecurityParameters")
    public void testAsymmetricMessage(SecurityPolicy securityPolicy,
                                      MessageSecurityMode messageSecurity,
                                      int messageSize) throws Exception {

        logger.info("Asymmetric chunk serialization, securityPolicy={}, messageSecurityMode={}, messageSize={}",
            securityPolicy, messageSecurity, messageSize);

        ChunkEncoder encoder = new ChunkEncoder(parameters);
        ChunkDecoder decoder = new ChunkDecoder(parameters);

        SecureChannel[] channels = generateChannels(securityPolicy, messageSecurity);
        ClientSecureChannel clientChannel = (ClientSecureChannel) channels[0];
        ServerSecureChannel serverChannel = (ServerSecureChannel) channels[1];

        clientChannel
            .attr(ClientSecureChannel.KEY_REQUEST_ID_SEQUENCE)
            .setIfAbsent(new LongSequence(1L, UInteger.MAX_VALUE));

        LongSequence requestId = clientChannel
            .attr(ClientSecureChannel.KEY_REQUEST_ID_SEQUENCE).get();

        byte[] messageBytes = new byte[messageSize];
        for (int i = 0; i < messageBytes.length; i++) {
            messageBytes[i] = (byte) i;
        }

        ByteBuf messageBuffer = BufferUtil.buffer().writeBytes(messageBytes);

        List<ByteBuf> chunkBuffers = encoder.encodeAsymmetric(
            clientChannel,
            MessageType.OpenSecureChannel,
            messageBuffer,
            requestId.getAndIncrement()
        );

        ByteBuf decodedBuffer = decoder.decodeAsymmetric(
            serverChannel,
            chunkBuffers
        );

        ReferenceCountUtil.releaseLater(messageBuffer);
        ReferenceCountUtil.releaseLater(decodedBuffer);

        messageBuffer.readerIndex(0);
        assertEquals(decodedBuffer, messageBuffer);
    }

    @DataProvider
    public Object[][] getSymmetricSecurityParameters() {
        return new Object[][]{
            {SecurityPolicy.None, MessageSecurityMode.None, 128},
            {SecurityPolicy.Basic128Rsa15, MessageSecurityMode.Sign, 128},
            {SecurityPolicy.Basic128Rsa15, MessageSecurityMode.SignAndEncrypt, 128},
            {SecurityPolicy.Basic256, MessageSecurityMode.Sign, 128},
            {SecurityPolicy.Basic256, MessageSecurityMode.SignAndEncrypt, 128},
            {SecurityPolicy.Basic256Sha256, MessageSecurityMode.Sign, 128},
            {SecurityPolicy.Basic256Sha256, MessageSecurityMode.SignAndEncrypt, 128},

            {SecurityPolicy.None, MessageSecurityMode.None, ChannelConfig.DEFAULT_MAX_CHUNK_SIZE},
            {SecurityPolicy.Basic128Rsa15, MessageSecurityMode.Sign, ChannelConfig.DEFAULT_MAX_CHUNK_SIZE},
            {SecurityPolicy.Basic128Rsa15, MessageSecurityMode.SignAndEncrypt, ChannelConfig.DEFAULT_MAX_CHUNK_SIZE},
            {SecurityPolicy.Basic256, MessageSecurityMode.Sign, ChannelConfig.DEFAULT_MAX_CHUNK_SIZE},
            {SecurityPolicy.Basic256, MessageSecurityMode.SignAndEncrypt, ChannelConfig.DEFAULT_MAX_CHUNK_SIZE},
            {SecurityPolicy.Basic256Sha256, MessageSecurityMode.Sign, ChannelConfig.DEFAULT_MAX_CHUNK_SIZE},
            {SecurityPolicy.Basic256Sha256, MessageSecurityMode.SignAndEncrypt, ChannelConfig.DEFAULT_MAX_CHUNK_SIZE},

            {SecurityPolicy.None, MessageSecurityMode.None, ChannelConfig.DEFAULT_MAX_MESSAGE_SIZE},
            {SecurityPolicy.Basic128Rsa15, MessageSecurityMode.Sign, ChannelConfig.DEFAULT_MAX_MESSAGE_SIZE},
            {SecurityPolicy.Basic128Rsa15, MessageSecurityMode.SignAndEncrypt, ChannelConfig.DEFAULT_MAX_MESSAGE_SIZE},
            {SecurityPolicy.Basic256, MessageSecurityMode.Sign, ChannelConfig.DEFAULT_MAX_MESSAGE_SIZE},
            {SecurityPolicy.Basic256, MessageSecurityMode.SignAndEncrypt, ChannelConfig.DEFAULT_MAX_MESSAGE_SIZE},
            {SecurityPolicy.Basic256Sha256, MessageSecurityMode.Sign, ChannelConfig.DEFAULT_MAX_MESSAGE_SIZE},
            {SecurityPolicy.Basic256Sha256, MessageSecurityMode.SignAndEncrypt, ChannelConfig.DEFAULT_MAX_MESSAGE_SIZE},
        };
    }

    @Test(dataProvider = "getSymmetricSecurityParameters")
    public void testSymmetricMessage(SecurityPolicy securityPolicy,
                                     MessageSecurityMode messageSecurity,
                                     int messageSize) throws Exception {

        logger.info("Symmetric chunk serialization, securityPolicy={}, messageSecurityMode={}, messageSize={}",
            securityPolicy, messageSecurity, messageSize);

        ChunkEncoder encoder = new ChunkEncoder(parameters);
        ChunkDecoder decoder = new ChunkDecoder(parameters);

        SecureChannel[] channels = generateChannels(securityPolicy, messageSecurity);
        ClientSecureChannel clientChannel = (ClientSecureChannel) channels[0];
        ServerSecureChannel serverChannel = (ServerSecureChannel) channels[1];

        clientChannel
            .attr(ClientSecureChannel.KEY_REQUEST_ID_SEQUENCE)
            .setIfAbsent(new LongSequence(1L, UInteger.MAX_VALUE));

        LongSequence requestId = clientChannel
            .attr(ClientSecureChannel.KEY_REQUEST_ID_SEQUENCE).get();

        byte[] messageBytes = new byte[messageSize];
        for (int i = 0; i < messageBytes.length; i++) {
            messageBytes[i] = (byte) i;
        }

        ByteBuf messageBuffer = BufferUtil.buffer().writeBytes(messageBytes);

        List<ByteBuf> chunkBuffers = encoder.encodeSymmetric(
            clientChannel,
            MessageType.SecureMessage,
            messageBuffer,
            requestId.getAndIncrement()
        );

        ByteBuf decodedBuffer = decoder.decodeSymmetric(
            serverChannel,
            chunkBuffers
        );

        ReferenceCountUtil.releaseLater(messageBuffer);
        ReferenceCountUtil.releaseLater(decodedBuffer);

        messageBuffer.readerIndex(0);
        assertEquals(decodedBuffer, messageBuffer);
    }

}
