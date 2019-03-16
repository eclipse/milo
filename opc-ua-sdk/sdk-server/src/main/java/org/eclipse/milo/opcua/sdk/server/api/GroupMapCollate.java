package org.eclipse.milo.opcua.sdk.server.api;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.LoggerFactory;


public class GroupMapCollate {

    public static <T, K, R> CompletableFuture<List<R>> groupMapCollate(
        List<T> items,
        Function<T, K> grouper,
        Function<K, Mapper<T, R>> mappers) {


        List<Pending<T, R>> pending = new ArrayList<>();

        for (int i = 0; i < items.size(); i++) {
            pending.add(new Pending<>(items.get(i), i));
        }

        Map<K, List<Pending<T, R>>> grouped = pending.stream()
            .collect(Collectors.groupingBy(p -> grouper.apply(p.item)));

        Stream<CompletableFuture<Void>> futures = grouped.entrySet().stream().map(entry -> {
            K key = entry.getKey();
            List<Pending<T, R>> pendingForKey = entry.getValue();

            Mapper<T, R> mapper = mappers.apply(key);

            CompletableFuture<List<R>> future = mapper.map(
                pendingForKey.stream()
                    .map(p -> p.item)
                    .collect(Collectors.toList())
            );

            return future.thenAccept(results -> {
                if (results.size() != pendingForKey.size()) {
                    String message = String.format(
                        "result size (%s) does not match pending size (%s)",
                        results.size(), pendingForKey.size());
                    LoggerFactory.getLogger(GroupMapCollate.class).error(message);
                    throw new RuntimeException(message);
                }

                Iterator<Pending<T, R>> pi = pendingForKey.iterator();
                Iterator<R> ri = results.iterator();

                while (pi.hasNext() && ri.hasNext()) {
                    pi.next().result.set(ri.next());
                }
            });
        });

        return allOf(futures).thenApply(
            v ->
                pending.stream()
                    .map(p -> p.result.get())
                    .collect(Collectors.toList())
        );
    }

    private static CompletableFuture<Void> allOf(Stream<CompletableFuture<Void>> futureStream) {
        CompletableFuture[] fa = futureStream.toArray(CompletableFuture[]::new);

        return CompletableFuture.allOf(fa);
    }

    public interface Mapper<T, R> {
        CompletableFuture<List<R>> map(List<T> items);
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

}
