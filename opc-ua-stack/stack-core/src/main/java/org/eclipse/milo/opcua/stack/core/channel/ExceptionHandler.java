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

package org.eclipse.milo.opcua.stack.core.channel;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.UaRuntimeException;
import org.eclipse.milo.opcua.stack.core.channel.messages.ErrorMessage;
import org.eclipse.milo.opcua.stack.core.channel.messages.TcpMessageEncoder;

public class ExceptionHandler {

    public static ErrorMessage sendErrorMessage(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        String message = cause.getMessage();
        long statusCode = StatusCodes.Bad_UnexpectedError;

        if (cause instanceof UaException) {
            UaException ex = (UaException) cause;
            message = ex.getMessage();
            statusCode = ex.getStatusCode().getValue();
        } else {
            Throwable innerCause = cause.getCause();

            if (innerCause instanceof UaException) {
                UaException ex = (UaException) innerCause;
                message = ex.getMessage();
                statusCode = ex.getStatusCode().getValue();
            } else if (innerCause instanceof UaRuntimeException) {
                UaRuntimeException ex = (UaRuntimeException) innerCause;
                message = ex.getMessage();
                statusCode = ex.getStatusCode().getValue();
            }
        }

        ErrorMessage error = new ErrorMessage(statusCode, message);
        ByteBuf messageBuffer = TcpMessageEncoder.encode(error);

        ctx.writeAndFlush(messageBuffer).addListener(future -> ctx.close());

        return error;
    }

}
