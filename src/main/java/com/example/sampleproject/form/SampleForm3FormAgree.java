package com.example.sampleproject.form;

import java.util.Collections;
import java.util.List;

public class SampleForm3FormAgree {
    private List<String> agree;

    public List<String> getAgree() {
        if (agree == null) {
            agree = Collections.emptyList();
        }
        return agree;
    }
    public void setAgree(List<String> agree) {
        this.agree = agree;
    }
}