package org.eclipse.milo.opcua.sdk.server.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.milo.opcua.stack.core.util.FutureUtils;


public class GroupMapCollate {

    public static <T, K, R> CompletableFuture<List<R>> groupMapCollate(
        List<T> items,
        Function<T, K> grouper,
        Function<K, Mapper<T, R>> mappers) {

        Function<K, IndexedMapper<T, R>> indexedMappers = k -> {
            Mapper<T, R> mapper = mappers.apply(k);

            return is -> mapper.map(
                is.stream()
                    .map(IndexedValue::getValue)
                    .collect(Collectors.toList())
            );
        };

        return groupMapCollateIndexed(items, grouper, indexedMappers);
    }

    public static <T, K, R> CompletableFuture<List<R>> groupMapCollateIndexed(
        List<T> items,
        Function<T, K> grouper,
        Function<K, IndexedMapper<T, R>> mappers) {

        List<Pending<T, R>> pending = new ArrayList<>();

        for (int i = 0; i < items.size(); i++) {
            pending.add(new Pending<>(items.get(i), i));
        }

        Map<K, List<Pending<T, R>>> grouped = pending.stream()
            .collect(Collectors.groupingBy(p -> grouper.apply(p.item)));

        Stream<CompletableFuture<Void>> futures = grouped.entrySet().stream().map(entry -> {
            K key = entry.getKey();
            List<Pending<T, R>> pendingForKey = entry.getValue();

            IndexedMapper<T, R> mapper = mappers.apply(key);

            CompletableFuture<List<R>> future = mapper.map(
                pendingForKey.stream()
                    .map(p -> new IndexedValue<>(p.item, p.index))
                    .collect(Collectors.toList())
            );

            return future.thenAccept(results -> {
                for (int i = 0; i < results.size(); i++) {
                    pendingForKey.get(i).result.set(results.get(i));
                }
            });
        });

        return FutureUtils.sequence(futures)
            .thenApply(v -> pending.stream().map(p -> p.result.get()).collect(Collectors.toList()));
    }

    public static <T, K, R> CompletableFuture<Void> groupMapIndexed(
        List<T> items, Function<T, K> grouper,
        Function<K, VoidIndexedMapper<T>> mappers) {

        List<Pending<T, R>> pending = new ArrayList<>();

        for (int i = 0; i < items.size(); i++) {
            pending.add(new Pending<>(items.get(i), i));
        }

        Map<K, List<Pending<T, R>>> grouped = pending.stream()
            .collect(Collectors.groupingBy(p -> grouper.apply(p.item)));

        Stream<CompletableFuture<Void>> futures = grouped.entrySet().stream().map(entry -> {
            K key = entry.getKey();
            List<Pending<T, R>> pendingForKey = entry.getValue();

            VoidIndexedMapper<T> mapper = mappers.apply(key);
            CompletableFuture<Void> future;
            try {
                future = mapper.map(
                    pendingForKey.stream()
                        .map(p -> new IndexedValue<>(p.item, p.index))
                        .collect(Collectors.toList())
                );
            } catch (Exception e) {
                future = new CompletableFuture<>();
                future.completeExceptionally(e);
            }
            return future;
        });

        return CompletableFuture.allOf(futures.toArray(size -> new CompletableFuture[size]));
    }

    public interface Mapper<T, R> {
        CompletableFuture<List<R>> map(List<T> items);
    }

    public interface IndexedMapper<T, R> {
        CompletableFuture<List<R>> map(List<IndexedValue<T>> items);
    }

    public interface VoidIndexedMapper<T> {
        CompletableFuture<Void> map(List<IndexedValue<T>> items);
    }

    private static class Pending<T, R> {
        final AtomicReference<R> result = new AtomicReference<>();

        final T item;
        final int index;

        Pending(T item, int index) {
            this.item = item;
            this.index = index;
        }
    }

    public static class IndexedValue<In> {

        private In inItem;
        private Integer index;

        public IndexedValue(In inItem, Integer index) {
            this.inItem = inItem;
            this.index = index;
        }

        public In getValue() {
            return inItem;
        }

        public Integer getIndex() {
            return index;
        }

    }

}
