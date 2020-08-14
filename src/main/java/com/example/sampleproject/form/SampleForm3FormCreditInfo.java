package com.example.sampleproject.form;

public class SampleForm3FormCreditInfo {
    private int creditNo;
    private int expirationDateMonth;
    private int expirationDateYear;
    private int securityCode;
    private String holderName;

    public int getCreditNo() {
        return creditNo;
    }
    public void setCreditNo(int creditNo) {
        this.creditNo = creditNo;
    }

    public int getExpirationDateMonth() {
        return expirationDateMonth;
    }
    public void setExpirationDateMonth(int expirationDateMonth) {
        this.expirationDateMonth = expirationDateMonth;
    }

    public int getExpirationDateYear() {
        return expirationDateYear;
    }
    public void setExpirationDateYear(int expirationDateYear) {
        this.expirationDateYear = expirationDateYear;
    }

    public int getSecurityCode() {
        return securityCode;
    }
    public void setSecurityCode(int securityCode) {
        this.securityCode = securityCode;
    }

    public String getHolderName() {
        return holderName;
    }
    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }
}