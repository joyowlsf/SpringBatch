package com.tuna.inspector.domain.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Setter
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TargetInfo {
    private String colNm;
    private String dbSeq;
    private String tblNm;
    private String domainCd;
    private int totalScore;


}
