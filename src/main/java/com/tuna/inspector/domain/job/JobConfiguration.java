package com.tuna.inspector.domain.job;

import com.tuna.inspector.domain.data.ColumnInfo;
import com.tuna.inspector.domain.data.ScoreInfo;
import com.tuna.inspector.domain.data.TargetInfo;
import com.tuna.inspector.domain.mapper.ColumnInfoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.*;

@Slf4j
@Configuration // spring batch의 모든 job을 등록해서 사용하게 해준다.
@RequiredArgsConstructor
public class JobConfiguration {
    private final JobBuilderFactory jobBuilderFactory; //원하는 job을 쉽게 만들 수 있다.
    private final StepBuilderFactory stepBuilderFactory; // step을 생성
    private final ColumnInfoMapper columnInfoMapper;

    Map<ColumnInfo, String> map1 = new HashMap<ColumnInfo, String>();
    Map<String, TargetInfo> map2 = new HashMap<String, TargetInfo>();
    Set<ColumnInfo> keySet1 = map1.keySet();


    ScoreInfo scoreInfo = new ScoreInfo();




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


    @Bean // 외부의 파라미터를 주입받을 수 있다.
    @JobScope
    public Step step1() {
        return stepBuilderFactory.get("Step1")
                .tasklet((StepContribution, ChunkContext) -> {
                    List<ColumnInfo> columnInfoList1 = columnInfoMapper.columnInfoList1();
                    for (ColumnInfo info1 : columnInfoList1) {
                        map1.put(info1, "0");
                        log.info(info1.toString());
                    }

                    log.info("Step1 end");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    @Bean
    @JobScope
    public Step step2() {
        return stepBuilderFactory.get("Step2")
                .tasklet((StepContribution, ChunkContext) -> {
                    List<TargetInfo> targetInfoList2 = columnInfoMapper.targetInfoList2();
                    for (TargetInfo info2 : targetInfoList2) {
                        scoreInfo.setColNmScore(true);

                        System.out.println(map2.get("a"));
                    }

                    return RepeatStatus.FINISHED;
                }).build();
    }

    @Bean
    @JobScope
    public Step step3() {
        return stepBuilderFactory.get("Step3")
                .tasklet((StepContribution, ChunkContext) -> {
                    List<TargetInfo> targetInfoList3 = columnInfoMapper.targetInfoList3();
                    for (TargetInfo info3 : targetInfoList3) {
                        scoreInfo.setSampleCountScore(true);
                        map2.put("b", info3);
                    }

                    /*for(Map.Entry<Boolean,TargetInfo> entryset : map2.entrySet()){
                        System.out.println(entryset.getKey()+" : " + entryset.getValue());
                    }*/
                    log.info("step3 end");
                    return RepeatStatus.FINISHED;
                }).build();
    }


    @Bean
    @JobScope
    public Step step4() {
        return stepBuilderFactory.get("Step4")
                .tasklet((StepContribution, ChunkContext) -> {
                    List<TargetInfo> targetInfoList4 = columnInfoMapper.targetInfoList4();
                    for (TargetInfo info4 : targetInfoList4) {
                        scoreInfo.setColLenScore(true);
                        map2.put("c", info4);
                    }


                    log.info("step4 end");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    @Bean
    @JobScope
    public Step step5() {
        return stepBuilderFactory.get("Step5")
                .tasklet((StepContribution, ChunkContext) -> {
                    List list = new ArrayList();
                    list.add(map2.get("a"));
                    map2.get("b");
                    map2.get("c");
                    /*HashMap map = new HashMap();
                    for (int i = 0; i < list.size(); i++) {
                        if(map.containsKey(list.get(i))) {
                            int count = (int)map.get(list.get(i));
                            map.put(list.get(i), count + 1);
                        }else {
                            map.put(list.get(i),1);
                        }
                    }
                    Iterator it = map.entrySet().iterator();
                    while(it.hasNext()){
                        Map.Entry entry = (Map.Entry)it.next();
                        System.out.println(entry.getKey() +  "  " + entry.getValue());
                    }*/

                    log.info("step5 end");
                    return RepeatStatus.FINISHED;
                }).build();
    }


}
