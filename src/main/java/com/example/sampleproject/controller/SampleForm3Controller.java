package com.example.sampleproject.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.sampleproject.data.Sample3DataMapperForPaymentInfo;
import com.example.sampleproject.data.Sample3DataForApplicationaInfo;
import com.example.sampleproject.data.Sample3DataForPaymentInfo;
import com.example.sampleproject.data.Sample3DataMapperForApplicationaInfo;
import com.example.sampleproject.form.SampleForm3FormAgree;
import com.example.sampleproject.form.SampleForm3FormCreditInfo;
import com.example.sampleproject.form.SampleForm3FormCustomer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping("/sampleForm3")
public class SampleForm3Controller {

    @Autowired
    private Sample3DataMapperForPaymentInfo paymentInfoMapper;
    @Autowired
    private Sample3DataMapperForApplicationaInfo applicationInfoMapper;

    private static final Logger log = LoggerFactory.getLogger(SampleForm2Controller.class);

    @Autowired
    HttpSession session;

    @Autowired
    PlatformTransactionManager txManager;

    @ModelAttribute("agreeOptions")
    public List<String> ckAgree() {
        List<String> list = new ArrayList<String>();
        list.add("agree");

        return list;
    }

    // @RequestMapping("/form")
    // public String tlSample(@ModelAttribute("form") SampleForm3Form form) {
    // return "sampleForm3/top";
    // }

    @RequestMapping("/form")
    public String tlSample(@ModelAttribute("formAgree") SampleForm3FormAgree formAgree) {
        return "sampleForm3/top";
    }

    @RequestMapping(value = "input_step1", method = RequestMethod.POST) // value =
                                                                        // URLのルート以降のパスを指定(formのaction名と同じにする)、method =
                                                                        // 処理するリクエストの種類を指定
    public String nextToStep1(@ModelAttribute("formAgree") SampleForm3FormAgree formAgree, Model modelAgree,
            @ModelAttribute("formCreditInfo") SampleForm3FormCreditInfo formCreditInfo, Model modelCreditInfo,
            @ModelAttribute("formCustomer") SampleForm3FormCustomer formCustomer, Model modelCustomer) {

        // SET SESSION
        session.setAttribute("formAgree", formAgree);
        return "sampleForm3/input_step1";
    }

    @RequestMapping(value = "input_step2", method = RequestMethod.POST)
    public String nextToStep2(@ModelAttribute("formAgree") SampleForm3FormAgree formAgree, Model modelAgree,
            @ModelAttribute("formCreditInfo") SampleForm3FormCreditInfo formCreditInfo, Model modelCreditInfo,
            @ModelAttribute("formCustomer") SampleForm3FormCustomer formCustomer, Model modelCustomer,
            @RequestParam(value = "action", required = true) String action) {
        if (action.equals("next")) {
            // SET SESSION
            session.setAttribute("formCustomer", formCustomer);

            return "sampleForm3/input_step2";
        } else {
            // REMOVE ALL SESSION
            session.invalidate();

            return "sampleForm3/top";
        }
    }

    @RequestMapping(value = "confirm", method = RequestMethod.POST)
    public String confirm(@ModelAttribute("formAgree") SampleForm3FormAgree formAgree, Model modelAgree,
            @ModelAttribute("formCreditInfo") SampleForm3FormCreditInfo formCreditInfo, Model modelCreditInfo,
            @ModelAttribute("formCustomer") SampleForm3FormCustomer formCustomer, Model modelCustomer,
            @RequestParam(value = "action", required = true) String action) {
        if (action.equals("next")) {

            // SET SESSION
            session.setAttribute("formCreditInfo", formCreditInfo);
            // GET SESSION
            SampleForm3FormCustomer sessionFormCustomer = (SampleForm3FormCustomer) session
                    .getAttribute("formCustomer");
            formCustomer.setCustomerNo(sessionFormCustomer.getCustomerNo());
            formCustomer.setConfirmNo(sessionFormCustomer.getConfirmNo());

            return "sampleForm3/confirm";
        } else {
            // GET SESSION
            SampleForm3FormCustomer sessionFormCustomer = (SampleForm3FormCustomer) session
                    .getAttribute("formCustomer");
            formCustomer.setCustomerNo(sessionFormCustomer.getCustomerNo());
            formCustomer.setConfirmNo(sessionFormCustomer.getConfirmNo());

            return "sampleForm3/input_step1";
        }
    }

    @Transactional
    @RequestMapping(value = "finish", method = RequestMethod.POST)
    public String finish(@ModelAttribute("formAgree") SampleForm3FormAgree formAgree, Model modelAgree,
            @ModelAttribute("formCreditInfo") SampleForm3FormCreditInfo formCreditInfo, Model modelCreditInfo,
            @ModelAttribute("formCustomer") SampleForm3FormCustomer formCustomer, Model modelCustomer,
            @RequestParam(value = "action", required = true) String action) {
        if (action.equals("finish")) {
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

            Sample3DataForPaymentInfo paymentInfo = new Sample3DataForPaymentInfo();
            Sample3DataForApplicationaInfo applicationInfo = new Sample3DataForApplicationaInfo();

            int agree = 0;
            if ( (formAgree.getAgree().get(0)).equals("agree") ) {
                agree = 1;
            }
            applicationInfo.setAgree(agree);
            applicationInfo.setUserNumber(formCustomer.getCustomerNo());
            applicationInfo.setConfirmationNumber(formCustomer.getConfirmNo());
            // applicationInfo.setApplicationDate(null);

            paymentInfo.setCreditno(formCreditInfo.getCreditNo());
            paymentInfo.setExpirattiondateMonth(formCreditInfo.getExpirationDateMonth());
            paymentInfo.setExpirattiondateYear(formCreditInfo.getExpirationDateYear());
            paymentInfo.setSecuritycode(formCreditInfo.getSecurityCode());
            paymentInfo.setHoldername(formCreditInfo.getHolderName());

            // INSERT DB
            DefaultTransactionDefinition txDefinition = new DefaultTransactionDefinition();
            txDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
            TransactionStatus txStatus = txManager.getTransaction(txDefinition);

            try{
                applicationInfoMapper.create(applicationInfo);
                paymentInfo.setApplicationInfoSeq(applicationInfoMapper.getSeq());
                paymentInfoMapper.create(paymentInfo);
                txManager.commit(txStatus);
            }catch (Exception e) {
                log.info(e.getMessage());
                txManager.rollback(txStatus);
                return "sampleForm3/error";
            }
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