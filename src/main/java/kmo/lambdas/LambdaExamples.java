package kmo.lambdas;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class LambdaExamples {

    public static void main(String[] args) {
        final var x1 = 1;
        final var x2 = 2;

        // lambda expression
        final BiFunction<Integer, Integer, Integer> add1 = (a, b) -> Integer.sum(a, b);
        System.out.println(add1.apply(x1, x2));

        // method reference
        final BiFunction<Integer, Integer, Integer> add2 = Integer::sum;
        System.out.println(add2.apply(x1, x2));

        // classic method call
        final var add3 = Integer.sum(x1, x2);
        System.out.println(add3);

        // runnable execution
        final Runnable add4 = () -> System.out.println(Integer.sum(x1, x2));
        add4.run();

        final DirtyFunctionBeforeJava8 add5 = new DirtyFunctionBeforeJava8(x1, x2);
        add5.run();
        System.out.println(add5.getResult());

        // own function
        final TriFunction<Integer, Integer, Integer, Integer> add6 = (a, b, c) -> Integer.sum(Integer.sum(a, b), c);
        final TriFunction<Integer, Integer, Integer, Integer> add7 = Calculator::add;
        System.out.println(add6.apply(x1, x2, 0));
        System.out.println(add7.apply(x1, x2, 0));

        // predicate
        final Predicate<String> predicate = String::isBlank;
        System.out.println(predicate.test(" "));

        // consumer
        final Consumer<String> consumer = System.out::println;
        consumer.accept("demo");

        // supplier
        final Supplier<String> supplier = () -> "demo";
        System.out.println(supplier.get());

    }

    static class DirtyFunctionBeforeJava8 implements Runnable {

        private final int x1;
        private final int x2;
        private int result;

        public DirtyFunctionBeforeJava8(final int x1, final int x2) {
            this.x1 = x1;
            this.x2 = x2;
        }

        @Override
        public void run() {
            result = Integer.sum(x1, x2);
        }

        public int getResult() {
            return result;
        }
    }

    static class Calculator {

        static int add(final int x, final int y, final int z) {
            return Integer.sum(x, y);
        }

    }

    interface ICalculator {
        default int add(final int x, final int y) {
            return Integer.sum(x, y);
        }
    }

    @FunctionalInterface
    interface TriFunction<F, S, T, R> {

        R apply(F f, S s, T t);
    }

}
