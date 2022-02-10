package com.tuna.inspector.domain.mapper;

import com.tuna.inspector.domain.data.ColumnInfo;
import com.tuna.inspector.domain.data.ScoreInfo;
import com.tuna.inspector.domain.data.TargetInfo;
import org.apache.ibatis.annotations.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper //매핑XML에 기재된 SQL을 호출하기 위한 인터페이스
public interface ColumnInfoMapper {

    @Select("""
            select b.db_seq , b.status
              from report b
             where  status = 'READY' LIMIT 10
            """)
    List<ColumnInfo> columnInfoList1();

    @Select("""
            select COL_NM,tbl_nm ,db_seq
            from target_column
            where DB_SEQ = #{dbSeq} 
            and (col_nm ilike '%YN'
            or col_nm ilike '%DEL'
            or col_nm ilike '%CHK'
            or col_nm ilike '%GENDER'
            or col_nm ilike '%hidden'
            or col_nm ilike '%allow'
            or col_nm ilike '%AT'
            or col_nm ilike '%STATUS'
            or col_nm ilike '%TYPE'
            or col_nm ilike '%USE'
            or col_nm ilike '%ST'
            or col_nm ilike '%STATE'
            or col_nm ilike '%STS'
            or col_nm ilike '%NOTICE'
            or col_nm ilike '%DIV'
            or col_nm ilike '%SECRET'
            or col_nm ilike '%FLAG'
            or col_nm ilike '%SEX'
            or col_nm ilike '%GB'
            or col_nm ilike '%FLG'
            or col_nm ilike 'FG'
            or col_nm ilike '%HTML'
            or col_nm ilike '%OPEN')
            """)
    List<TargetInfo> targetInfoList1(String dbSeq);

    @Select("""
            select COL_NM,tbl_nm ,db_seq
                    from target_column
                    where DB_SEQ = #{dbSeq} 
                    and ARRAY_LENGTH(string_to_array(sample_data , ','), 1) = 2 
            """)
    List<TargetInfo> targetInfoList2(String dbSeq);

    @Select("""
            select COL_NM,tbl_nm ,db_seq
            from target_column tc
            where DB_SEQ = #{dbSeq}
            and cast(col_len as numeric ) < 2 and col_len != ''
            """)
    List<TargetInfo> targetInfoList3(String dbSeq);

    @Select("""
            select COL_NM,tbl_nm ,db_seq
            from target_column tc
            where DB_SEQ = #{dbSeq}
            and upper(split_part(sample_data ,',', array_length(string_to_array(sample_data,','),1))) 
            in ('Y','N','1','0','TRUE','FALSE','F','T')
            """)
    List<TargetInfo> targetInfoList4(String dbSeq);

    @Insert("""
            insert into domain_result values(#{colNm},#{dbSeq},#{tblNm},#{domainCd},#{totalScore})
            """)
    public void insertInfo(TargetInfo targetInfo);

    @Delete("""
            delete from domain_result
            """)
    public void deleteInfo();

}
