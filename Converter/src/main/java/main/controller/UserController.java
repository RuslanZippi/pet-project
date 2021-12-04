package main.controller;

import main.base.currency.CurrencyBase;
import main.base.currency.HistoryBase;
import main.base.user.User;
import main.reposiroty.currency.CurrencyRep;
import main.reposiroty.currency.HistoryRep;
import main.service.Calculate;
import main.service.ParseCurrency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/")
public class UserController {

    private String needValue;

    @Autowired
    private HistoryRep historyRep;

    @Autowired
    private ParseCurrency parseCurrency;

    @Autowired
    private CurrencyRep currencyRep;

    @Autowired
    private Calculate calculate;

    @GetMapping("/")
    public String main(Model model) {
        parseCurrency.setSite("http://www.cbr.ru/scripts/XML_daily.asp");

        model.addAttribute("bases", currencyRep.findAll());

        model.addAttribute("needValue",needValue);

        return "main";
    }

    @PostMapping("/")
    public String convert(@AuthenticationPrincipal User user, @RequestParam(name = "bases") String value, @RequestParam(name = "iValue") String iValue){
        CurrencyBase base = currencyRep.findByCharCode(value);
        if (iValue.matches("[A-Za-z]+")){
            iValue = "0";
        }
        needValue = calculate.calculate(base.getValue(),iValue);

        HistoryBase base1 = new HistoryBase();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        base1.setDate(dateFormat.format(new Date()));
        base1.setInNameValue(value);
        base1.setOutNameValue("Рубли");
        base1.setInValue(iValue);
        base1.setOutValue(needValue);
        base1.setUser(user);

        historyRep.save(base1);

        return "redirect:/";
    }
}
