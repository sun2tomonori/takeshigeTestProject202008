package com.example.sampleproject.form;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class SampleForm2Form{

    private String message1;
    private String message2;
 
    private Calendar today;
 
    private String name;
    private List<String> ckColors;
    private String rdColor;
    private String lsColor;
 
    private List<SampleForm2Form> rowDatas;
 
    public String getMessage1() {
        return message1;
    }
    public void setMessage1(String message1) {
        this.message1 = message1;
    }
    public String getMessage2() {
        return message2;
    }
    public void setMessage2(String message2) {
        this.message2 = message2;
    }
    public Calendar getToday() {
        return today;
    }
    public void setToday(Calendar today) {
        this.today = today;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
 
    public List<String> getCkColors() {
        if (ckColors == null) {
            ckColors = Collections.emptyList();
        }
        return ckColors;
    }
    public void setCkColors(List<String> ckColors) {
        this.ckColors = ckColors;
    }
    public String getRdColor() {
        return rdColor;
    }
    public void setRdColor(String rdColor) {
        this.rdColor = rdColor;
    }
    public String getLsColor() {
        return lsColor;
    }
    public void setLsColor(String lsColor) {
        this.lsColor = lsColor;
    }
    public List<SampleForm2Form> getRowDatas() {
        if (rowDatas == null) {
            rowDatas = new ArrayList<SampleForm2Form>();
            for (int i = 0; i < 3; i++) {
                rowDatas.add(new SampleForm2Form());
            }
        }
        return rowDatas;
    }
    public void setRowDatas(List<SampleForm2Form> rowDatas) {
        this.rowDatas = rowDatas;
    }

}