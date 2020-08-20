package com.example.sampleproject.data;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface Sample3DataMapperForApplicationaInfo {

    @Insert("INSERT INTO APPLICATIONINFO(SEQ,USERNUMBER,CONFIRMATIONNUMBER,AGREE,APPLICATION_DATE) VALUES(APPLICATIONINFO_SEQ.nextval,#{userNumber},#{confirmationNumber},#{agree},'20-08-20 15:46:44.055000000')")
    void create(Sample3DataForApplicationaInfo applicationInfo);

    @Select("SELECT APPLICATIONINFO_SEQ.currval FROM dual")
    int getSeq();
}