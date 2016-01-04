package part1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by nasir on 4/1/16.
 */
public class ThreadExecutors {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(() -> {
            String thread = Thread.currentThread().getName();
            System.out.println("Current thread : " + thread);
            try {
                TimeUnit.SECONDS.sleep(7);
            } catch (InterruptedException e) {
                System.err.println("I'm interrupted by shutdownnow method. I wasn't able to sleep");
            }
        });

        // The Executor process never stops!
        // Executors have to be stopped explicitly - otherwise they keep listening for new tasks.
        // An ExecutorService provides two methods for that purpose: shutdown() waits for currently running tasks to finish
        // while shutdownNow() interrupts all running tasks and shut the executor down immediately.
        System.out.println("Trying to shutdown the executor process");

        // Comment below code to check how shutdownnow interrupts the execution
        executorService.shutdown();
        try {
            executorService.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.err.println("Task interrupted due to shutdown method call");
        } finally {
            if (!executorService.isTerminated()) {
                System.err.println("cancelling non-finish task");
            }
            // ShutdownNow() interrupts all running tasks and shut the executor down immediately
            executorService.shutdownNow();
            System.out.println("Shutdown the task");
        }

    }
}
