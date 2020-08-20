package com.example.sampleproject.data;

import lombok.Data;

@Data
public class Sample3DataForPaymentInfo {
    private int paymentInfoSeq;
    private int applicationInfoSeq;
    private String creditno;
    private String expirattiondateYear;
    private String expirattiondateMonth;
    private String securitycode;
    private String holdername;
}