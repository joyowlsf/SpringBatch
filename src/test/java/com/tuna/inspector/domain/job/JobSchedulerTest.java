package com.tuna.inspector.domain.job;

import com.tuna.inspector.domain.data.TargetInfo;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.Job;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


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