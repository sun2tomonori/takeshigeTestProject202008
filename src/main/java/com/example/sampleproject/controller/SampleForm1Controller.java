package com.example.sampleproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/sampleForm1")     //マッピングするtemplateディレクトリを指定(この場合、template/sampleForm1直下のhtmlのマッピング)
public class SampleForm1Controller {
    @RequestMapping("/form")
    public String input1() {
        return "sampleForm1/form";
    }

	@RequestMapping(value = "confirm", method = RequestMethod.POST)     // value = URLのルート以降のパスを指定、method = 処理するリクエストの種類を指定
	public String output1(@RequestParam(name = "text1") String str1, Model model) {

        model.addAttribute("moji1", str1);
        
        // modelに変数を追加すれば遷移後の画面で表示できる
        String addVariableString = "Controller内で追加された文字列変数";
        model.addAttribute("addVariableString",addVariableString);
        
        return "sampleForm1/confirm";
	}
}