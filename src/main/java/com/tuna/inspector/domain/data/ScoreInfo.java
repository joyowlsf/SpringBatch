package com.tuna.inspector.domain.data;

import lombok.Data;

@Data
public class ScoreInfo {
    private boolean colNmScore;
    private boolean sampleCountScore;
    private boolean colLenScore;
    private boolean sampleDataScore;
    private int calcScore;

    public void sum(Boolean bo) {
        if (colLenScore = bo) {
            calcScore += 20;
        } else if (colNmScore = bo) {
            calcScore += 30;
        } else if (sampleCountScore = bo) {
            calcScore += 20;
        } else if (sampleDataScore = bo) {
            calcScore += 30;
        }
    }
}
