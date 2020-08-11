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

import com.example.sampleproject.form.SampleForm2Form;
import com.example.sampleproject.form.SampleForm2Row;

@Controller
@RequestMapping("/sampleForm2")
public class SampleForm2Controller {
    private static final Logger log = LoggerFactory
    .getLogger(SampleForm2Controller.class);

    @ModelAttribute("colorOptions")
    public List<String> ckBoxList() {
        List<String> list = new ArrayList<String>();
        list.add("red");
        list.add("green");
        list.add("blue");
        
        return list;
    }

    @RequestMapping("/form")
    public String tlSample(@ModelAttribute("form") SampleForm2Form form) {
        form.setMessage1("<h1>Thymeleaf サンプル入力</h1>");
        form.setMessage2("Thymeleafを使用したサンプル入力画面です。");
        form.setToday(Calendar.getInstance());
/*
        log.info("#########");
        log.info("入力された氏名は "+form.getName());
        log.info("選択されたチェックボックスは ");
        for(String color: form.getCkColors()) {
            log.info("\t"+color);
        }
        log.info("選択されたラジオボタンは "+form.getRdColor());
        log.info("選択されたリストは "+form.getLsColor());
        log.info("入力されたテーブルデータは");

        
        for (SampleForm2Row row : form.getRowDatas()) {
            log.info("\t"+row.getCol1()+", "+row.getCol2()+", "+row.getCol3());
        }
        
        log.info("#########");
*/

        return "sampleForm2/form";
    }

    @RequestMapping(value = "confirm", method = RequestMethod.POST)     // value = URLのルート以降のパスを指定、method = 処理するリクエストの種類を指定
	public String confirm(@ModelAttribute("form") SampleForm2Form form, Model model){
        form.setMessage1("<h1>Thymeleaf サンプル確認</h1>");
        form.setMessage2("Thymeleafを使用したサンプル確認画面です。");
        form.setToday(Calendar.getInstance());

        log.info("#########");
        log.info("入力された氏名は "+form.getName());
        log.info("選択されたチェックボックスは ");
        for(String color: form.getCkColors()) {
            log.info("\t"+color);
        }
        log.info("選択されたラジオボタンは "+form.getRdColor());
        log.info("選択されたリストは "+form.getLsColor());
        log.info("入力されたテーブルデータは");
        
        log.info("#########");

        return "sampleForm2/confirm";
    }
}