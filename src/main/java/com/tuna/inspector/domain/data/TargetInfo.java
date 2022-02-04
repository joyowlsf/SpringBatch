package com.tuna.inspector.domain.data;

import lombok.Data;
import lombok.ToString;

@Data
public class TargetInfo {
    private String colNm;
    private String dbSeq;
    private String tblNm;
    private String domain;
    private int score;
}
