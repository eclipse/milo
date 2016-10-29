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

package org.eclipse.milo.opcua.sdk.core.model.variables;

import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.ServerState;
import org.eclipse.milo.opcua.stack.core.types.structured.BuildInfo;


public interface ServerStatusType extends BaseDataVariableType {


    DateTime getStartTime();

    BaseDataVariableType getStartTimeNode();

    void setStartTime(DateTime value);

    DateTime getCurrentTime();

    BaseDataVariableType getCurrentTimeNode();

    void setCurrentTime(DateTime value);

    ServerState getState();

    BaseDataVariableType getStateNode();

    void setState(ServerState value);

    BuildInfo getBuildInfo();

    BuildInfoType getBuildInfoNode();

    void setBuildInfo(BuildInfo value);

    UInteger getSecondsTillShutdown();

    BaseDataVariableType getSecondsTillShutdownNode();

    void setSecondsTillShutdown(UInteger value);

    LocalizedText getShutdownReason();

    BaseDataVariableType getShutdownReasonNode();

    void setShutdownReason(LocalizedText value);
}
