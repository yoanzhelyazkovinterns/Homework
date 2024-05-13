package org.example.performance;

import java.util.Random;

public class MockService {

    public void execute() throws Exception {
        Random random = new Random();
        int time = random.nextInt(1200) + 1;
        Thread.sleep(time);
        if (random.nextDouble() < 0.3) {
            throw new Exception("Execution failed");
        }
    }
}