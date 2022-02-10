package com.tuna.inspector.domain.job.tasklet;

import com.tuna.inspector.domain.data.ScoreInfo;
import com.tuna.inspector.domain.mapper.ColumnInfoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Component
@RequiredArgsConstructor
public abstract class AbstractDomainYnTasklet implements Tasklet {
    protected final ColumnInfoMapper columnInfoMapper;
    static final Map<String, ScoreInfo> map = new HashMap<>();

    protected ScoreInfo scoreInfo(String key) {
        return map.containsKey(key) ? map.get(key) : new ScoreInfo();
    }
}
