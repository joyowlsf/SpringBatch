package com.tuna.inspector.domain.job;

import org.junit.jupiter.api.Test;
import org.springframework.batch.core.Job;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@SpringBatchTest
class JobSchedulerTest {

    @Autowired
    JobScheduler scheduler;

    @Test
    void RunJob() {
        scheduler.RunJob();
    }
}