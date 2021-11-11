/*
 * Copyright (c) 2021 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.core.util;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.TransformerFactory;

import org.slf4j.LoggerFactory;

public class SecureXmlUtil {

    private SecureXmlUtil() {}

    /**
     * A shared {@link DocumentBuilderFactory} that has been configured securely to prevent XXE attacks.
     */
    public static final DocumentBuilderFactory SHARED_DOCUMENT_BUILDER_FACTORY = DocumentBuilderFactory.newInstance();

    /**
     * A shared {@link TransformerFactory} that has been configured securely to prevent XXE attacks.
     */
    public static final TransformerFactory SHARED_TRANSFORMER_FACTORY = TransformerFactory.newInstance();

    static {
        SHARED_DOCUMENT_BUILDER_FACTORY.setCoalescing(true);
        SHARED_DOCUMENT_BUILDER_FACTORY.setNamespaceAware(true);

        // XXE Prevention
        // https://www.owasp.org/index.php/XML_External_Entity_(XXE)_Prevention_Cheat_Sheet

        SHARED_DOCUMENT_BUILDER_FACTORY.setExpandEntityReferences(false);
        SHARED_DOCUMENT_BUILDER_FACTORY.setXIncludeAware(false);

        trySetFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
        trySetFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
        trySetFeature("http://xml.org/sax/features/external-general-entities", false);
        trySetFeature("http://xml.org/sax/features/external-parameter-entities", false);
        trySetFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);

        SHARED_TRANSFORMER_FACTORY.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
        SHARED_TRANSFORMER_FACTORY.setAttribute(XMLConstants.ACCESS_EXTERNAL_STYLESHEET, "");
    }

    private static void trySetFeature(String feature, boolean value) {
        try {
            SHARED_DOCUMENT_BUILDER_FACTORY.setFeature(feature, value);
        } catch (Exception e) {
            LoggerFactory.getLogger(SecureXmlUtil.class)
                .debug("Error configuring feature: " + feature, e);
        }
    }

}
