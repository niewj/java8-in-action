package concurrent.bruceEckel.constructorSafe;// IDChecker.java

import com.google.common.collect.Sets;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IDChecker {
    public static int SIZE = 100000;

    static class MakeObjects implements Supplier<List<Integer>> {
        private Supplier<HasID> gen;

        public MakeObjects(Supplier<HasID> gen) {
            this.gen = gen;
        }

        @Override
        public List<Integer> get() {
            return Stream.generate(gen).limit(SIZE).map(HasID::getID).collect(Collectors.toList());
        }
    }

    public static void test(Supplier<HasID> gen) {
        CompletableFuture<List<Integer>> groupA = CompletableFuture.supplyAsync(new MakeObjects(gen)), groupB = CompletableFuture.supplyAsync(new MakeObjects(gen));
        groupA.thenAcceptBoth(groupB, (a, b) -> {
            System.out.println(Sets.intersection(Sets.newHashSet(a), Sets.newHashSet(b)).size());
        }).join();
    }
}