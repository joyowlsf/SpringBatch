package com.tuna.inspector.domain.mapper;

import com.tuna.inspector.domain.data.ColumnInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper //매핑XML에 기재된 SQL을 호출하기 위한 인터페이스
public interface ColumnInfoMapper {

    @Select("""
    select db_seq,status
    FROM report
    where status ='READY'
    """)
    List<ColumnInfo>columnInfoList();
}
