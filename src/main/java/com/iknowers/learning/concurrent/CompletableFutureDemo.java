package com.iknowers.learning.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * CompletableFuture 学习
 */
public class CompletableFutureDemo {

    public static void simple() throws Exception {
        CompletableFuture<Double> f = CompletableFuture.supplyAsync(() -> "4")
                .thenApply(Integer::parseInt)
                .thenApply(r -> r * r * Math.PI);

        System.out.println(f.get());
    }

    public static Optional<List<Integer>> longTask(Integer i) {
        if (i > 0) {
            List<Integer> list = new ArrayList<>();
            for(int pc = 0; pc < i; pc++)
                list.add(pc);
            return Optional.of(list);
        }
        else
            return Optional.empty();
    }
    public static CompletableFuture<Long> getResultFuture(Optional<List<Integer>> op) {
        return CompletableFuture.supplyAsync(() -> {
            if (op.isPresent())
                return op.get().stream()
                        .map(Integer::toUnsignedLong)
                        .reduce(0L, (x, y) -> x + y);
            else
                return -1L;
        });
    }

    public static void main(String[] args) throws Exception {
        CompletableFuture<Long> f = CompletableFuture.supplyAsync(() -> longTask(100))
                .thenComposeAsync(CompletableFutureDemo::getResultFuture);

        f.whenComplete((result, exception) -> {System.out.println("result: " + result.doubleValue());});
        Long result = f.get();
        System.out.println(result);

        //Thread.sleep(10000);
    }
}
