package com.example.sampleproject.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.example.sampleproject.SampleData;
import com.example.sampleproject.SampleDataMapper;
import com.example.sampleproject.form.SampleForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@SessionAttributes(types = {SampleForm.class})
public class SampleController {
    private static final Logger log = LoggerFactory
    .getLogger(SampleForm2Controller.class);

    @Autowired
    private SampleDataMapper mapper;

    @ModelAttribute("demoFormList")
    public List<SampleForm> userDataList(){
        List<SampleForm> demoFormList = new ArrayList<>();
        return demoFormList;
    }

    @ModelAttribute("sampleForm")
    public SampleForm createDemoForm(){
        SampleForm sampleForm = new SampleForm();
        return sampleForm;
    }

    @RequestMapping("/sample")
    public String index( Model model ) {
        List<SampleForm> sampleFormList = new ArrayList<>();
        //ユーザーデータテーブル(user_data)から全データを取得する
        Collection<SampleData> sampleDataList = mapper.findAll();
        for(SampleData userData : sampleDataList){
            sampleFormList.add(getSampleForm(userData));
            log.info(userData.toString());
            int id = userData.getId();
            String val = userData.getVal();
            log.info(String.valueOf(id));
            log.info(val);
        }
        //ユーザーデータリストを更新
        model.addAttribute("sampleFormList", sampleFormList);

        return "sample";
    }

    /**
     * 更新処理を行う画面に遷移する
     * @param id 更新対象のID
     * @param model Modelオブジェクト
     * @return 入力・更新画面へのパス
     */
    @GetMapping("/update")
    public String update(@RequestParam("id") String id, Model model){
        SampleData userData = mapper.findById(Long.parseLong(id));
        model.addAttribute("demoForm", getSampleForm(userData));
        return "input";
    }

    /**
     * 削除確認画面に遷移する
     * @param id 更新対象のID
     * @param model Modelオブジェクト
     * @return 削除確認画面へのパス
     */
    @GetMapping("/delete_confirm")
    public String delete_confirm(@RequestParam("id") String id, Model model){
        SampleData sampleData = mapper.findById(Long.parseLong(id));
        model.addAttribute("demoForm", getSampleForm(sampleData));
        return "confirm_delete";
    }

    /**
     * 削除処理を行う
     * @param demoForm Formオブジェクト
     * @return 一覧画面の表示処理
     */
    @PostMapping(value = "/delete", params = "next")
    @Transactional(readOnly = false)
    public String delete(SampleForm sampleForm){
        mapper.deleteById(sampleForm.getId());
        return "redirect:/to_index";
    }

    /**
     * 削除完了後に一覧画面に戻る
     * @param model Modelオブジェクト
     * @return 一覧画面
     */
    @GetMapping("/to_index")
    public String toIndex(Model model){
        return index(model);
    }

    /**
     * 削除確認画面から一覧画面に戻る
     * @param model Modelオブジェクト
     * @return 一覧画面
     */
    @PostMapping(value = "/delete", params = "back")
    public String confirmDeleteBack(Model model){
        return index(model);
    }

    /**
     * 追加処理を行う画面に遷移する
     * @param model Modelオブジェクト
     * @return 入力・更新画面へのパス
     */
    @PostMapping("/add")
    public String add(Model model){
        model.addAttribute("sampleForm", new SampleForm());
        return "input";
    }



    private SampleForm getSampleForm(SampleData sampleData){
        SampleForm sampleForm = new SampleForm();
        sampleForm.setId(sampleData.getId());
        sampleForm.setVal(sampleData.getVal());
        return sampleForm;
    }

    private SampleData getSampleData(SampleForm demoForm){
        SampleData userData = new SampleData();
        userData.setId(Integer.valueOf(demoForm.getId()));
        userData.setVal(demoForm.getVal());
        return userData;
    }
}