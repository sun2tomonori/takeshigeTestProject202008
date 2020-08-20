package com.example.sampleproject.data;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface Sample3DataMapperForPaymentInfo {
    /**
     * @param paymentInfo
     */
    @Insert("INSERT INTO PAYMENTINFO(SEQ,APPLICATIONINFO_SEQ,CREDITNO,EXPIRATIONDATE_YEAR,EXPIRATIONDATE_MONTH,SECURITYCODE,HOLDERNAME) VALUES (PAYMENTINFO_SEQ.nextval,#{applicationInfoSeq},#{creditno},#{expirattiondateYear},#{expirattiondateMonth},#{securitycode},#{holdername})")
    void create(Sample3DataForPaymentInfo paymentInfo);
}