package com.tuna.inspector.domain.mapper;

import com.tuna.inspector.domain.data.ColumnInfo;
import com.tuna.inspector.domain.data.TargetInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper //매핑XML에 기재된 SQL을 호출하기 위한 인터페이스
public interface ColumnInfoMapper {

    final String select1 = """
            select b.col_nm ,b.tbl_nm, b.db_seq , a.status 
            from report a join target_column b  on a.db_seq =b.db_seq
            where status = 'READY' and a.db_seq ='8421' limit 10
            """;

    final String select2 = """
            select COL_NM,tbl_nm ,db_seq
            from target_column
            where upper(split_part(col_nm,'_', array_length(string_to_array(col_nm,'_'),1)))
            in ('AT','STATUS','TYPE','USE','ST','STATE','STS','NOTICE','DIV','SECRET','FLAG','SEX','GB','FLG','FG','HTML','OPEN')
            or col_nm ilike '%YN' or col_nm ilike '%DEL' or col_nm ilike '%CHK' or col_nm ilike '%GENDER' or col_nm ilike '%hidden' or col_nm ilike '%allow' 
            LIMIT 100
            """;


    final String select3 = """
            select COL_NM,tbl_nm ,db_seq
                    from target_column
                    where ARRAY_LENGTH(string_to_array(sample_data , ','), 1) = 2 
                    LIMIT 800
            """;


    final String select4 = "select COL_NM,tbl_nm ,db_seq,domain \n" +
            "from target_column tc \n" +
            "where cast(col_len as numeric ) < 2 and col_len != '' limit 800";

    final String select5 = "select COL_NM,tbl_nm ,db_seq,domain \n" +
            "from target_column tc \n" +
            "where upper(split_part(sample_data ,',', array_length(string_to_array(sample_data,','),1))) in " +
            "('Y','N','1','0','TRUE','FALSE','F','T') limit 800;";



    /*    final String insert1 ="insert in to col_nm,tbl_nm,db_seq from target_column"*/

    @Select(select1)
    List<ColumnInfo> columnInfoList1();

    @Select(select2)
    List<TargetInfo> targetInfoList2();

    @Select(select3)
    List<TargetInfo> targetInfoList3();

    @Select(select4)
    List<TargetInfo> targetInfoList4();

    @Select(select5)
    List<TargetInfo> targetInfoList5();

    @Insert("""
            insert into domain_result values(#{colNm},#{dbSeq},#{tblNm},'여부',#{score})
            """)
    public void insertInfo(TargetInfo targetInfo);

/*   @Insert("insert info2 (colNm,dbSeq,tblNm) values(#{colNm},#{dbSeq},#{tblNm})")
    public void insertInfo(TargetInfo info2);*/
}
