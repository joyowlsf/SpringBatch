package com.tuna.inspector.domain.job;

import com.tuna.inspector.domain.data.ColumnInfo;
import com.tuna.inspector.domain.mapper.ColumnInfoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class JobConfiguration {
    private final JobBuilderFactory jobBuilderFactory; //원하는 job을 쉽게 만들 수 있다.
    private final StepBuilderFactory stepBuilderFactory; // step을 생성
    private final ColumnInfoMapper columnInfoMapper;

    private final String JOB_NAME = "domain job";

    @Bean
    //job에다가 step을 추가하고 job을 생성
    public Job job(){
        return jobBuilderFactory.get(JOB_NAME) //get() 메서드로 JobBuilder를 생성할 수 있다. -> job 생성
                .start(step1()) // Step을 추가해서 가장 기본이 되는 빌더를 생성
                .build();
    }


    @Bean // 외부의 파라미터를 주입받을 수 있다.
    @JobScope
    public Step step1() {
        return stepBuilderFactory.get(JOB_NAME + "Step1")
                .tasklet((StepContribution, ChunkContext) -> {
                    List<ColumnInfo> columnInfoList = columnInfoMapper.columnInfoList();
                    for(ColumnInfo info:columnInfoList){
                        log.info("ColumnInfo{}",info.toString());
                    }
                    log.info(">>>>>"+(JOB_NAME+"Step1 Started"));
                    return RepeatStatus.FINISHED;
                }).build();
    }

}
