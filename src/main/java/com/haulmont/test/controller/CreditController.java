package com.haulmont.test.controller;

import com.haulmont.test.entity.Credit;
import com.haulmont.test.service.CreditService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/credits")
public class CreditController {

    private CreditService creditService;

    public CreditController(CreditService creditService) {
        this.creditService = creditService;
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
        theModel.addAttribute("credit", theCredit);
        return "credits/credit-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("creditId") UUID theId, Model theModel) {
        Credit theCredit = creditService.findById(theId);
        theModel.addAttribute("credit", theCredit);
        return "credits/credit-form";
    }

    @PostMapping("/save")
    public String saveCredit(@ModelAttribute("credit") Credit theCredit) {
        creditService.save(theCredit);
        return "redirect:/credits/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("creditId") UUID theId) {
        creditService.deleteById(theId);
        return "redirect:/credits/list";
    }
}