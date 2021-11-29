package com.haulmont.test.controller;

import com.haulmont.test.entity.Bank;
import com.haulmont.test.entity.Credit;
import com.haulmont.test.service.BankService;
import com.haulmont.test.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/credits")
public class CreditController {

    private CreditService creditService;
    private BankService bankService;

    @Autowired
    public void setCreditService(CreditService creditService) {
        this.creditService = creditService;
    }

    @Autowired
    public void setBankService(BankService bankService) {
        this.bankService = bankService;
    }

    @GetMapping("/list")
    public String listCredits(Model theModel) {
        List<Credit> theCredits = creditService.findAll();
        theModel.addAttribute("credits", theCredits);
        return "credits/list-credits";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {
        Credit theCredit = new Credit();
        List<Bank> banks = bankService.findAll();
        theModel.addAttribute("credit", theCredit);
        theModel.addAttribute("banks", banks);
        return "credits/credit-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("creditId") UUID theId, Model theModel) {
        Credit theCredit = creditService.findById(theId);
        List<Bank> banks = bankService.findAll();
        theModel.addAttribute("credit", theCredit);
        theModel.addAttribute("banks", banks);
        return "credits/credit-form";
    }

    @PostMapping("/save")
    public String saveCredit(@ModelAttribute("credit") @Valid Credit theCredit,
                             BindingResult bindingResult, Model theModel) {
        if (bindingResult.hasErrors()) {
            List<Bank> banks = bankService.findAll();
            theModel.addAttribute("banks", banks);
            return "credits/credit-form";
        }
        creditService.save(theCredit);
        return "redirect:/credits/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("creditId") UUID theId) {
        creditService.deleteById(theId);
        return "redirect:/credits/list";
    }
}