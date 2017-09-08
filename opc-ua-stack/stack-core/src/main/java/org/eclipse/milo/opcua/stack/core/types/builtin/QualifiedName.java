/*
 * Copyright (c) 2016 Kevin Herron and others
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

package org.eclipse.milo.opcua.stack.core.types.builtin;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ushort;

/**
 * This Built-in DataType contains a qualified name. It is, for example, used as BrowseName.
 */
public final class QualifiedName {

    public static final QualifiedName NULL_VALUE = new QualifiedName(ushort(0), null);

    private final UShort namespaceIndex;
    private final String name;

    /**
     * The name part of the QualifiedName is restricted to 512 characters.
     *
     * @param namespaceIndex index that identifies the namespace that defines the name. This index is the index of that
     *                       namespace in the local Server’s NamespaceArray.
     * @param name           the text portion of the QualifiedName.
     */
    public QualifiedName(int namespaceIndex, @Nullable String name) {
        this(ushort(namespaceIndex), name);
    }

    /**
     * The name part of the QualifiedName is restricted to 512 characters.
     *
     * @param namespaceIndex index that identifies the namespace that defines the name. This index is the index of that
     *                       namespace in the local Server’s NamespaceArray.
     * @param name           the text portion of the QualifiedName.
     */
    public QualifiedName(@Nonnull UShort namespaceIndex, @Nullable String name) {
        Preconditions.checkNotNull(namespaceIndex);
        Preconditions.checkArgument(name == null || name.length() <= 512, "name");

        this.namespaceIndex = namespaceIndex;
        this.name = name;
    }

    public UShort getNamespaceIndex() {
        return namespaceIndex;
    }

    @Nullable
    public String getName() {
        return name;
    }

    public boolean isNull() {
        return name == null;
    }

    public boolean isNotNull() {
        return !isNull();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QualifiedName that = (QualifiedName) o;

        return !(name != null ? !name.equals(that.name) : that.name != null) &&
            namespaceIndex.equals(that.namespaceIndex);
    }

    @Override
    public int hashCode() {
        int result = namespaceIndex.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("name", name)
            .add("namespaceIndex", namespaceIndex)
            .toString();
    }

    public String toParseableString() {
        return String.format("%s:%s", namespaceIndex.intValue(), name);
    }

    public static QualifiedName parse(String s) {
        String[] ss = s.split(":", 2);

        int namespaceIndex = 0;
        String name = s;

        if (ss.length > 1) {
            try {
                namespaceIndex = Short.parseShort(ss[0]);
            } catch (NumberFormatException ignored) {
                namespaceIndex = 0;
            }
            name = ss[1];
        }

        return new QualifiedName(ushort(namespaceIndex), name);
    }

}
