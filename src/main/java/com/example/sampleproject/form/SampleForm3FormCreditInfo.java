package com.example.sampleproject.form;

public class SampleForm3FormCreditInfo {
    private Number creditNo;
    private Number expirationDateMonth;
    private Number expirationDateYear;
    private Number securityCode;
    private String holderName;

    public Number getCreditNo() {
        return creditNo;
    }
    public void setCreditNo(Number creditNo) {
        this.creditNo = creditNo;
    }

    public Number getExpirationDateMonth() {
        return expirationDateMonth;
    }
    public void setExpirationDateMonth(Number expirationDateMonth) {
        this.expirationDateMonth = expirationDateMonth;
    }

    public Number getExpirationDateYear() {
        return expirationDateYear;
    }
    public void setExpirationDateYear(Number expirationDateYear) {
        this.expirationDateYear = expirationDateYear;
    }

    public Number getSecurityCode() {
        return securityCode;
    }
    public void setSecurityCode(Number securityCode) {
        this.securityCode = securityCode;
    }

    public String getHolderName() {
        return holderName;
    }
    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }
}