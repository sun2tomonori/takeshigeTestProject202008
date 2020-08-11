package com.example.sampleproject.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.sampleproject.form.SampleForm3Form;

@Controller
@RequestMapping("/sampleForm3")
public class SampleForm3Controller {
    private static final Logger log = LoggerFactory.getLogger(SampleForm3Controller.class);
/*
    @ModelAttribute("colorOptions")
    public List<String> ckBoxList() {
        List<String> list = new ArrayList<String>();
        list.add("red");
        list.add("green");
        list.add("blue");
        
        return list;
    }
*/

    @RequestMapping("/form")
    public String tlSample(@ModelAttribute("form") SampleForm3Form form) {
        return "sampleForm3/top";
    }

    @RequestMapping(value = "input_step1", method = RequestMethod.POST)     // value = URLのルート以降のパスを指定(formのaction名と同じにする)、method = 処理するリクエストの種類を指定
	public String confirm(@ModelAttribute("form") SampleForm3Form form, Model model){
        return "sampleForm3/input_step1";
//      return "sampleForm3/top";
    }
}