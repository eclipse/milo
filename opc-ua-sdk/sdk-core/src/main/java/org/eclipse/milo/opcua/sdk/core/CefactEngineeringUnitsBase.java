package org.eclipse.milo.opcua.sdk.core;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.eclipse.milo.opcua.stack.core.types.structured.EUInformation;

abstract class CefactEngineeringUnitsBase {
    protected static final String CEFACT_NAMESPACE_URI = "http://www.opcfoundation.org/UA/units/un/cefact";

    protected static final Map<Integer, EUInformation> BY_UNIT_ID = new ConcurrentHashMap<>();
}
