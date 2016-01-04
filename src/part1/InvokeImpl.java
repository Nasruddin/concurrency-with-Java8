package part1;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by nasir on 4/1/16.
 */
public class InvokeImpl {

    public static void main(String[] args) {

        // This factory method is part of Java 8 and returns an executor of type ForkJoinPool
        // which works slightly different than normal executors. Instead of using a fixed size
        // thread-pool ForkJoinPools are created for a given parallelism size which per default
        // is the number of available cores of the hosts CPU.
        ExecutorService executor = Executors.newWorkStealingPool();

        List<Callable<String>> callables =  Arrays.asList(
                () -> "task 1",
                () -> "task 2",
                () -> "task 3"
        );

        try {
            executor.invokeAll(callables)
                    .stream()
                    .map(future -> {
                        String s = null;
                        try {

                            s = future.get();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        }
                        return s;
                    })
                    .forEach(System.out::println);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
