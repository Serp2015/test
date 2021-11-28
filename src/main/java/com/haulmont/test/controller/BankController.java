package com.haulmont.test.controller;

import java.util.List;
import java.util.UUID;

import com.haulmont.test.entity.Bank;
import com.haulmont.test.service.BankService;
import com.haulmont.test.service.ClientService;
import com.haulmont.test.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequestMapping("/banks")
public class BankController {

    private BankService bankService;

    @Autowired
    public void setBankService(BankService bankService) {
        this.bankService = bankService;
    }

    @GetMapping("/list")
    public String listBanks(Model theModel) {
        List<Bank> theBanks = bankService.findAll();
        theModel.addAttribute("banks", theBanks);
        return "banks/list-banks";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {
        Bank theBank = new Bank();
        theModel.addAttribute("bank", theBank);
        return "banks/bank-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("bankId") UUID theId,
                                    Model theModel) {
        Bank theBank = bankService.findById(theId);
        theModel.addAttribute("bank", theBank);
        return "banks/bank-form";
    }

    @PostMapping("/save")
    public String saveBank(@ModelAttribute("bank") @Valid Bank theBank,
                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "banks/bank-form";
        }
        bankService.save(theBank);
        return "redirect:/banks/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("bankId") UUID theId) {
        bankService.deleteById(theId);
        return "redirect:/banks/list";
    }

    @GetMapping("/showFormForClientList")
    public String showFormForClient(@RequestParam("bankId") UUID theId, Model theModel) {
        Bank theBank = bankService.findById(theId);
        theModel.addAttribute("bank", theBank);
        return "banks/list-clients";
    }

    @GetMapping("/showFormForCreditList")
    public String showFormForCredit(@RequestParam("bankId") UUID theId, Model theModel) {
        Bank theBank = bankService.findById(theId);
        theModel.addAttribute("bank", theBank);
        return "banks/list-credits";
    }
}