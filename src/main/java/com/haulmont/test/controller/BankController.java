package com.haulmont.test.controller;

import java.util.List;
import java.util.UUID;

import com.haulmont.test.entity.Bank;
import com.haulmont.test.entity.Client;
import com.haulmont.test.entity.Credit;
import com.haulmont.test.service.BankService;
import com.haulmont.test.service.ClientService;
import com.haulmont.test.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/banks")
public class BankController {

    private BankService bankService;
    private ClientService clientService;
    private CreditService creditService;

    @Autowired
    public void setBankService(BankService bankService) {
        this.bankService = bankService;
    }

    @Autowired
    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }

    @Autowired
    public void setCreditService(CreditService creditService) {
        this.creditService = creditService;
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
    public String saveBank(@ModelAttribute("bank") Bank theBank) {
        bankService.save(theBank);
        return "redirect:/banks/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("bankId") UUID theId) {
        bankService.deleteById(theId);
        return "redirect:/banks/list";
    }

    @GetMapping("/showFormForClientCreditList")
    public String showFormForSchedule(@RequestParam("bankId") UUID theId, Model theModel) {
        Bank theBank = bankService.findById(theId);
        theModel.addAttribute("bank", theBank);
        return "banks/list-clients-credits";
    }

    @GetMapping("/showFormForAddClientCredit")
    public String showFormForAddClientCredit(@RequestParam("bankId") UUID theId, Model theModel) {
        Bank theBank = bankService.findById(theId);
        List<Client> clients = clientService.findAll();
//        List<Credit> credits = creditService.findAll();
        theModel.addAttribute("bank", theBank);
        theModel.addAttribute("clients", clients);
//        theModel.addAttribute("credits", credits);
        return "banks/client-credit-form";
    }

    @PostMapping("/saveClientCredit")
    public String saveClientCredit(@ModelAttribute("bank") Bank theBank) {
        bankService.save(theBank);
        return "redirect:/banks/list-clients-credits";
    }

//    @GetMapping("/saveClientCredit")
//    public String saveClientCredit(@RequestParam("bankId") UUID theId,
//                                    Model theModel) {
//        Bank theBank = bankService.findById(theId);
//        theModel.addAttribute("bank", theBank);
//        return "redirect:/banks/list-clients-credits";
//    }

}