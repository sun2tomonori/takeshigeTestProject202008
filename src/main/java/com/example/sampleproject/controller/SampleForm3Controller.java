package com.example.sampleproject.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.sampleproject.form.SampleForm3Form;
import com.example.sampleproject.form.SampleForm3FormAgree;
import com.example.sampleproject.form.SampleForm3FormCreditInfo;
import com.example.sampleproject.form.SampleForm3FormCustomer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping("/sampleForm3")
public class SampleForm3Controller {

    private static final Logger log = LoggerFactory
    .getLogger(SampleForm2Controller.class);

    @Autowired
    HttpSession session;

    @ModelAttribute("agreeOptions")
    public List<String> ckAgree() {
        List<String> list = new ArrayList<String>();
        list.add("agree");
        
        return list;
    }

    // @RequestMapping("/form")
    // public String tlSample(@ModelAttribute("form") SampleForm3Form form) {
    //     return "sampleForm3/top";
    // }

    @RequestMapping("/form")
    public String tlSample(@ModelAttribute("formAgree") SampleForm3FormAgree formAgree) {
        return "sampleForm3/top";
    }

    @RequestMapping(value = "input_step1", method = RequestMethod.POST)     // value = URLのルート以降のパスを指定(formのaction名と同じにする)、method = 処理するリクエストの種類を指定
    public String nextToStep1(  @ModelAttribute("formAgree") SampleForm3FormAgree formAgree, Model modelAgree,
                                @ModelAttribute("formCreditInfo") SampleForm3FormCreditInfo formCreditInfo, Model modelCreditInfo,
                                @ModelAttribute("formCustomer") SampleForm3FormCustomer formCustomer, Model modelCustomer){
        
        // SET SESSION
        session.setAttribute("formAgree", formAgree);
        return "sampleForm3/input_step1";
    }

    @RequestMapping(value = "input_step2", method = RequestMethod.POST)
	public String nextToStep2(  @ModelAttribute("formAgree") SampleForm3FormAgree formAgree, Model modelAgree,
                                @ModelAttribute("formCreditInfo") SampleForm3FormCreditInfo formCreditInfo, Model modelCreditInfo,
                                @ModelAttribute("formCustomer") SampleForm3FormCustomer formCustomer, Model modelCustomer,
                                @RequestParam(value="action", required=true) String action){
        if(action.equals("next")){
            // SET SESSION
            session.setAttribute("formCustomer", formCustomer);

            return "sampleForm3/input_step2";
        }
        else{
            // REMOVE ALL SESSION
            session.invalidate();

            return "sampleForm3/top";
        }
    }

    @RequestMapping(value = "confirm", method = RequestMethod.POST)
	public String confirm(  @ModelAttribute("formAgree") SampleForm3FormAgree formAgree, Model modelAgree,
                            @ModelAttribute("formCreditInfo") SampleForm3FormCreditInfo formCreditInfo, Model modelCreditInfo,
                            @ModelAttribute("formCustomer") SampleForm3FormCustomer formCustomer, Model modelCustomer,
                            @RequestParam(value="action", required=true) String action){
        if(action.equals("next")){

            // SET SESSION
            session.setAttribute("formCreditInfo", formCreditInfo);
            // GET SESSION
            SampleForm3FormCustomer sessionFormCustomer = (SampleForm3FormCustomer) session.getAttribute("formCustomer");
            formCustomer.setCustomerNo(sessionFormCustomer.getCustomerNo());
            formCustomer.setConfirmNo(sessionFormCustomer.getConfirmNo());

            return "sampleForm3/confirm";
        }
        else{
            // GET SESSION
            SampleForm3FormCustomer sessionFormCustomer = (SampleForm3FormCustomer) session.getAttribute("formCustomer");
            formCustomer.setCustomerNo(sessionFormCustomer.getCustomerNo());
            formCustomer.setConfirmNo(sessionFormCustomer.getConfirmNo());

            return "sampleForm3/input_step1";
        }
    }

    @RequestMapping(value = "finish", method = RequestMethod.POST)
	public String finish(   @ModelAttribute("formAgree") SampleForm3FormAgree formAgree, Model modelAgree,
                            @ModelAttribute("formCreditInfo") SampleForm3FormCreditInfo formCreditInfo, Model modelCreditInfo,
                            @ModelAttribute("formCustomer") SampleForm3FormCustomer formCustomer, Model modelCustomer,
                            @RequestParam(value="action", required=true) String action){
        if(action.equals("finish")){
            // GET SESSION
            SampleForm3FormAgree sessionFormAgree = (SampleForm3FormAgree) session.getAttribute("formAgree");
            formAgree.setAgree(sessionFormAgree.getAgree());

            SampleForm3FormCustomer sessionFormCustomer = (SampleForm3FormCustomer) session.getAttribute("formCustomer");
            formCustomer.setCustomerNo(sessionFormCustomer.getCustomerNo());
            formCustomer.setConfirmNo(sessionFormCustomer.getConfirmNo());

            SampleForm3FormCreditInfo sessionFormCreditInfo = (SampleForm3FormCreditInfo) session.getAttribute("formCreditInfo");
            formCreditInfo.setCreditNo(sessionFormCreditInfo.getCreditNo());
            formCreditInfo.setExpirationDateMonth(sessionFormCreditInfo.getExpirationDateMonth());
            formCreditInfo.setExpirationDateYear(sessionFormCreditInfo.getExpirationDateYear());
            formCreditInfo.setSecurityCode(sessionFormCreditInfo.getSecurityCode());
            formCreditInfo.setHolderName(sessionFormCreditInfo.getHolderName());



            // INSERT DB

            // REMOVE ALL SESSION
            session.invalidate();

            return "sampleForm3/finish";
        }
        else{
            // GET SESSION
            SampleForm3FormCreditInfo sessionFormCreditInfo = (SampleForm3FormCreditInfo) session.getAttribute("formCreditInfo");
            formCreditInfo.setCreditNo(sessionFormCreditInfo.getCreditNo());
            formCreditInfo.setExpirationDateMonth(sessionFormCreditInfo.getExpirationDateMonth());
            formCreditInfo.setExpirationDateYear(sessionFormCreditInfo.getExpirationDateYear());
            formCreditInfo.setSecurityCode(sessionFormCreditInfo.getSecurityCode());
            formCreditInfo.setHolderName(sessionFormCreditInfo.getHolderName());

            return "sampleForm3/input_step2";
        }
    }

    @RequestMapping(value = "backform", method = RequestMethod.POST)
	public String backFormForDone(@ModelAttribute("formAgree") SampleForm3FormAgree formAgree, Model model){
        return "sampleForm3/top";
    }
}