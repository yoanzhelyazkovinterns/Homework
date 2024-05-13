package org.example.performance;

import java.util.Random;

public class MockService {

    public void execute() throws Exception {
        Random random = new Random();
        int time = random.nextInt(1200) + 1; // Random execution time between 1 and 1200 milliseconds
        Thread.sleep(time);
        if (random.nextDouble() < 0.3) { // 30% chance of failure
            throw new Exception("Execution failed");
        }
    }
}