package com.tuna.inspector.domain.data;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ScoreInfo {
    private int step1Score;
    private int step2Score;
    private int step3Score;
    private int step4Score;
    private int totalScore;

    public void setStep1Score(int step1Score) {
        this.step1Score = step1Score;
        sum(step1Score);
    }

    public void setStep2Score(int step2Score) {
        this.step2Score = step2Score;
        sum(step2Score);
    }

    public void setStep3Score(int step3Score) {
        this.step3Score = step3Score;
        sum(step3Score);
    }

    public void setStep4Score(int step4Score) {
        this.step4Score = step4Score;
        sum(step4Score);
    }


    private int sum(int score){
        totalScore+=score;
        return totalScore;
    }
}
