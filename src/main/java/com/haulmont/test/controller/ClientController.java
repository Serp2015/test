package com.haulmont.test.controller;

import com.haulmont.test.entity.Bank;
import com.haulmont.test.entity.Client;
import com.haulmont.test.service.BankService;
import com.haulmont.test.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/clients")
public class ClientController {

    private ClientService clientService;
    private BankService bankService;

    @Autowired
    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }

    @Autowired
    public void setBankService(BankService bankService) {
        this.bankService = bankService;
    }

    @GetMapping("/list")
    public String listClients(Model theModel) {
        List<Client> theClients = clientService.findAll();
        theModel.addAttribute("clients", theClients);
        return "clients/list-clients";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {
        Client theClient = new Client();
        List<Bank> banks = bankService.findAll();
        theModel.addAttribute("client", theClient);
        theModel.addAttribute("banks", banks);
        return "clients/client-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("clientId") UUID theId, Model theModel) {
        Client theClient = clientService.findById(theId);
        List<Bank> banks = bankService.findAll();
        theModel.addAttribute("client", theClient);
        theModel.addAttribute("banks", banks);
        return "clients/client-form";
    }

    @PostMapping("/save")
    public String saveClient(@ModelAttribute("client") @Valid Client theClient,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "clients/client-form";
        }
        clientService.save(theClient);
        return "redirect:/clients/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("clientId") UUID theId) {
        clientService.deleteById(theId);
        return "redirect:/clients/list";
    }
}