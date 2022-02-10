package com.tuna.inspector.domain.job.tasklet;

import com.tuna.inspector.domain.data.ScoreInfo;
import com.tuna.inspector.domain.data.TargetInfo;
import com.tuna.inspector.domain.mapper.ColumnInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class DomainYnSampleDataNmCheckerTasklet extends AbstractDomainYnTasklet{

    public DomainYnSampleDataNmCheckerTasklet(ColumnInfoMapper columnInfoMapper) {
        super(columnInfoMapper);
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        String dbSeq = chunkContext.getStepContext().getJobParameters().get("dbSeq").toString();
        List<TargetInfo> targetInfoList = columnInfoMapper.targetInfoList4(dbSeq);
        for(TargetInfo targetInfo : targetInfoList){
            String key = String.format("%s@%s@%s@",targetInfo.getDbSeq(),targetInfo.getTblNm(),targetInfo.getColNm());
            ScoreInfo scoreInfo = scoreInfo(key);
            scoreInfo.setStep4Score(30);
            map.put(key,scoreInfo);
            log.info("step4 key: {}, scoreinfo: {}", targetInfo,scoreInfo.toString());
        }
        return RepeatStatus.FINISHED;
    }
}
