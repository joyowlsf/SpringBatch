package com.tuna.inspector.domain.data;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ColumnInfo {
    private String colNm;
    private String tblNm;
    private String dbSeq;
    private String status;
}
