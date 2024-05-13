package performance;

import org.example.performance.MockService;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PerformanceTest {

    @Test
    public void threadTest() {
        MockService mockService = new MockService();
        ExecutorService executor = Executors.newFixedThreadPool(100);
        CountDownLatch latch = new CountDownLatch(1000);

        final int[] successCount = {0};
        final int[] slowExecutionCount = {0};

        for (int i = 0; i < 1000; i++) {
            executor.submit(() -> {
                try {
                    long startTime = System.currentTimeMillis();
                    mockService.execute();
                    long executionTime = System.currentTimeMillis() - startTime;
                    if (executionTime > 1000) {
                        slowExecutionCount[0]++;
                    }
                    successCount[0]++;
                } catch (Exception e) {
                    System.out.println("Run failed");
                } finally {
                    latch.countDown();
                }
            });
        }

        try {
            latch.await(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executor.shutdown();

        var successRate = (double) successCount[0] / 1000 * 100;
        var slowExecutionRate = (double) slowExecutionCount[0] / 1000 * 100;

        System.out.println("Success rate: " + successRate + "%");
        System.out.println("Slow execution rate: " + slowExecutionRate + "%");
    }
}