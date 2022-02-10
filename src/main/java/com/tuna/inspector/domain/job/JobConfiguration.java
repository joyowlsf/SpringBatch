package com.tuna.inspector.domain.job;

import com.tuna.inspector.domain.data.ScoreInfo;
import com.tuna.inspector.domain.data.TargetInfo;
import com.tuna.inspector.domain.job.tasklet.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Slf4j
@Configuration // spring batch의 모든 job을 등록해서 사용하게 해준다.
@RequiredArgsConstructor
public class JobConfiguration  {
    private final JobBuilderFactory jobBuilderFactory; //원하는 job을 쉽게 만들 수 있다.
    private final StepBuilderFactory stepBuilderFactory; // step을 생성
    private final DomainYnColNmCheckerTasklet domainYnColNmCheckerTasklet;
    private final DomainYnSampleDataCheckerTasklet domainYnSampleDataCheckerTasklet;
    private final DomainYnColLenCheckerTasklet domainYnColLenCheckerTasklet;
    private final DomainYnSampleDataNmCheckerTasklet domainYnSampleDataNmCheckerTasklet;
    private final DomainScoreInsertTasklet domainScoreInsertTasklet;

    @Bean
    //job에다가 step을 추가하고 job을 생성
    public Job job() {
        return (Job) jobBuilderFactory.get("example") //get() 메서드로 JobBuilder를 생성할 수 있다. -> job 생성
                .start(step1())
                .next(step2())
                .next(step3())
                .next(step4())
                .next(step5())
                .build();
    }


    @Bean
    @JobScope
    public Step step1(){
        return stepBuilderFactory.get("Step1")
                .tasklet(domainYnColNmCheckerTasklet)
                .build();
    }

    @Bean
    @JobScope
    public Step step2(){
        return stepBuilderFactory.get("Step2")
                .tasklet(domainYnSampleDataCheckerTasklet)
                .build();
    }

    @Bean
    @JobScope
    public Step step3() {
        return stepBuilderFactory.get("Stepq3")
                .tasklet(domainYnColLenCheckerTasklet)
                .build();
    };

    @Bean
    @JobScope
    public Step step4() {
        return stepBuilderFactory.get("Step4")
                .tasklet(domainYnSampleDataNmCheckerTasklet)
                .build();
    };




    @Bean
    @JobScope
    public Step step5(){
        return stepBuilderFactory.get("step4")
                .tasklet(domainScoreInsertTasklet)
                .build();
    }

}
