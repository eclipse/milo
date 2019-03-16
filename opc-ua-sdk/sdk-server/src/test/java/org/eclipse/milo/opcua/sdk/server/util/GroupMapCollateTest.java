package org.eclipse.milo.opcua.sdk.server.util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.IntStream;

import org.testng.annotations.Test;

import static java.util.stream.Collectors.toList;
import static org.eclipse.milo.opcua.sdk.server.util.GroupMapCollate.groupMapCollate;
import static org.testng.Assert.assertEquals;

public class GroupMapCollateTest {

    @Test
    public void testGroupMapCollate() throws ExecutionException, InterruptedException {
        int N = 10;
        List<Integer> items = new ArrayList<>();
        IntStream.range(0, N).forEach(items::add);

        for (int i = 1; i <= N; i++) {
            final int mod = i;
            CompletableFuture<List<String>> stringsFuture = groupMapCollate(
                items,
                item -> item % mod,
                remainder -> group -> {
                    System.out.println("mod=" + mod + " remainder=" + remainder + " group=" + group);

                    CompletableFuture<List<String>> future = new CompletableFuture<>();

                    future.complete(group.stream().map(Object::toString).collect(toList()));

                    return future;
                }
            );

            List<String> strings = stringsFuture.get();

            for (int j = 0; j < strings.size(); j++) {
                assertEquals(String.valueOf(j), strings.get(j));
            }
            System.out.println("--");
        }
    }

}