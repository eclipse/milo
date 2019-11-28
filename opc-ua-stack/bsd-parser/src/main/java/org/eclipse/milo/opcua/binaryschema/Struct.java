/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.binaryschema;

import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;

public class Struct {

    private final String name;
    private final ImmutableMap<String, Member> members;

    public Struct(@Nonnull String name, @Nonnull Map<String, Member> members) {
        this(name, ImmutableMap.copyOf(members));
    }

    public Struct(@Nonnull String name, @Nonnull ImmutableMap<String, Member> members) {
        Preconditions.checkNotNull(name);
        Preconditions.checkNotNull(members);

        this.name = name;
        this.members = members;
    }

    @Nonnull
    public String getName() {
        return name;
    }

    @Nonnull
    public ImmutableMap<String, Member> getMembers() {
        return members;
    }

    @Nullable
    public Member getMember(String name) {
        return members.get(name);
    }

    @Nonnull
    public Optional<Member> getMemberSafe(String name) {
        return Optional.ofNullable(members.get(name));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Struct that = (Struct) o;
        return Objects.equals(name, that.name) &&
            Objects.equals(members, that.members);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, members);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("name", name)
            .add("members", members)
            .toString();
    }

    public static Builder builder(String name) {
        return new Builder(name);
    }

    public static class Member {
        private final String name;
        private final Object value;

        public Member(String name, Object value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public Object getValue() {
            return value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Member member = (Member) o;

            if (value != null && value.getClass().isArray()) {
                return Objects.equals(name, member.name) &&
                    Objects.deepEquals(value, member.value);
            } else {
                return Objects.equals(name, member.name) &&
                    Objects.equals(value, member.value);
            }
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, value);
        }

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this)
                .add("name", name)
                .add("value", value)
                .toString();
        }
    }

    public static class Builder {

        private final String name;
        private final LinkedList<Struct.Member> members = new LinkedList<>();

        public Builder(String name) {
            this.name = name;
        }

        public Builder addMember(String name, Object value) {
            // Member can't contain another Member; it can contain
            // a Struct or any other kind of value type.
            Preconditions.checkArgument(!(value instanceof Member));

            members.add(new Struct.Member(name, value));

            return this;
        }

        public Struct build() {
            ImmutableMap.Builder<String, Member> mapBuilder = ImmutableMap.builder();

            members.forEach(m -> mapBuilder.put(m.name, m));

            return new Struct(name, mapBuilder.build());
        }

    }

}
