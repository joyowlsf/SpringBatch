package com.tuna.inspector.domain.job;

import com.tuna.inspector.domain.data.ColumnInfo;
import com.tuna.inspector.domain.data.TargetInfo;
import com.tuna.inspector.domain.mapper.ColumnInfoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class JobScheduler {

    private final JobLauncher jobLauncher; //job을 실행시켜준다
    private final JobConfiguration jobConfiguration;
    private final ColumnInfoMapper columnInfoMapper;

    public void RunJob() {
        List<ColumnInfo> columnInfoList1 = columnInfoMapper.columnInfoList1();
        columnInfoMapper.deleteInfo();
        for (ColumnInfo info1 : columnInfoList1) {

            String dbSeq = info1.getDbSeq();
            Map<String, JobParameter> confMap = new HashMap<>();
            confMap.put("time", new JobParameter(System.currentTimeMillis() + dbSeq));
            confMap.put("dbSeq", new JobParameter(dbSeq));
            JobParameters jobParameters = new JobParameters(confMap);


            try {
                jobLauncher.run(jobConfiguration.job(), jobParameters); // Job, JobParameters와 함께 배치를 실행하는 인터페이스, run()메서드 하나만 가짐
            } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException | JobParametersInvalidException e) {
                e.printStackTrace();
            }
        }
    }
}
