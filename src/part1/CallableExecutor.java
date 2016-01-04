package part1;

import java.util.concurrent.*;

/**
 * Created by nasir on 4/1/16.
 */
public class CallableExecutor {

    public static void main(String[] args) {

        Callable<Integer> task = () -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                return 123;
            }
            catch (InterruptedException e) {
                throw new IllegalStateException("task interrupted", e);
            }
        };

        // Callables are functional interfaces just like runnables
        // but instead of being void they return a value.
        ExecutorService executor = Executors.newFixedThreadPool(1);


        // Executor returns a special result of type Future
        // which can be used to retrieve the actual result at a later point in time.
        Future<Integer> future = executor.submit(task);
        System.out.println("future done? " + future.isDone());

        Integer result = 0;

        try {
            //result = future.get();
            // Any call to future.get() will block and wait until the underlying callable has been terminated.
            // In the worst case a callable runs forever - thus making your application unresponsive.
            // You can simply counteract those scenarios by passing a timeout:
            result = future.get(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();

            // To trigger timeout exception, make callable to sleep to 2 seconds before returning
            // the result. Since our future get will wait for 1 second to get result, it will through ex.
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

        System.out.println("future done? " + future.isDone());
        System.out.print("result: " + result);

        executor.shutdown();
    }
}

