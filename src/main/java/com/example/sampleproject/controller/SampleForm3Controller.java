package com.example.sampleproject.controller;

import java.util.ArrayList;
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

    @ModelAttribute("agreeOptions")
    public List<String> ckAgree() {
        List<String> list = new ArrayList<String>();
        list.add("agree");
        
        return list;
    }

    @RequestMapping("/form")
    public String tlSample(@ModelAttribute("form") SampleForm3Form form) {
        return "sampleForm3/top";
    }

    @RequestMapping(value = "input_step1", method = RequestMethod.POST)     // value = URLのルート以降のパスを指定(formのaction名と同じにする)、method = 処理するリクエストの種類を指定
	public String nextToStep1(@ModelAttribute("form") SampleForm3Form form, Model model){
        return "sampleForm3/input_step1";
//      return "sampleForm3/top";
    }

    @RequestMapping(value = "input_step2", method = RequestMethod.POST)
	public String nextToStep2(@ModelAttribute("form") SampleForm3Form form, Model model, @RequestParam(value="action", required=true) String action){
        if(action.equals("next")){
            return "sampleForm3/input_step2";
        }
        else{
            return "sampleForm3/top";
        }
    }

    @RequestMapping(value = "confirm", method = RequestMethod.POST)
	public String confirm(@ModelAttribute("form") SampleForm3Form form, Model model, @RequestParam(value="action", required=true) String action){
        if(action.equals("next")){
            return "sampleForm3/confirm";
        }
        else{
            return "sampleForm3/input_step1";
        }
    }
}