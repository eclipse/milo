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

package org.eclipse.milo.opcua.stack.core.channel.messages;

import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;

public enum MessageType {

    Hello,
    Acknowledge,
    Error,
    OpenSecureChannel,
    CloseSecureChannel,
    SecureMessage;

    private static final int HEL = ('L' << 16) | ('E' << 8) | 'H';
    private static final int ACK = ('K' << 16) | ('C' << 8) | 'A';
    private static final int ERR = ('R' << 16) | ('R' << 8) | 'E';
    private static final int OPN = ('N' << 16) | ('P' << 8) | 'O';
    private static final int CLO = ('O' << 16) | ('L' << 8) | 'C';
    private static final int MSG = ('G' << 16) | ('S' << 8) | 'M';

    public static int toMediumInt(MessageType messageType) throws UaException {
        switch (messageType) {
            case Hello:
                return HEL;
            case Acknowledge:
                return ACK;
            case Error:
                return ERR;
            case OpenSecureChannel:
                return OPN;
            case CloseSecureChannel:
                return CLO;
            case SecureMessage:
                return MSG;
            default:
                throw new UaException(StatusCodes.Bad_TcpMessageTypeInvalid, "unknown message type: " + messageType);
        }
    }

    public static MessageType fromMediumInt(int messageType) throws UaException {
        switch (messageType) {
            case HEL:
                return Hello;
            case ACK:
                return Acknowledge;
            case ERR:
                return Error;
            case OPN:
                return OpenSecureChannel;
            case CLO:
                return CloseSecureChannel;
            case MSG:
                return SecureMessage;
            default:
                throw new UaException(StatusCodes.Bad_TcpMessageTypeInvalid, "unknown message type: " + messageType);
        }
    }

}
