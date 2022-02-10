package com.tuna.inspector.domain.job.tasklet;

import com.tuna.inspector.domain.data.TargetInfo;
import com.tuna.inspector.domain.mapper.ColumnInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class DomainScoreInsertTasklet extends AbstractDomainYnTasklet  {

    public DomainScoreInsertTasklet(ColumnInfoMapper columnInfoMapper) {
        super(columnInfoMapper);
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

        for (String key : map.keySet()) {
            String[] keyInfo = key.split("@");
            columnInfoMapper.insertInfo(TargetInfo.builder()
                    .dbSeq(keyInfo[0])
                    .tblNm(keyInfo[1])
                    .colNm(keyInfo[2])
                    .domainCd("YN")
                    .totalScore(map.get(key).getTotalScore())
                    .build());
        }
        return RepeatStatus.FINISHED;
    }
}
