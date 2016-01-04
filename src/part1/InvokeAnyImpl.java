package part1;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by nasir on 4/1/16.
 */
public class InvokeAnyImpl {

    public static Callable<String> callable(String result, long sleepSeconds) {
        return () -> {
            TimeUnit.SECONDS.sleep(sleepSeconds);
            return result;
        };
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executor = Executors.newWorkStealingPool();

        List<Callable<String>> callables = Arrays.asList(
                callable("task 2", 2),
                callable("task 1", 1),
                callable("task 3", 3)
        );

        // INVOKEANY() - Instead of returning future objects all callable invokeAny() methods blocks until the
        // first callable terminates and returns the result of that callable.
        // So the result will fastest task i.e task 1
        String result = executor.invokeAny(callables);
        System.out.println("Results : "+result);
    }
}
