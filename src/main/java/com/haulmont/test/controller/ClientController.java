package com.haulmont.test.controller;

import com.haulmont.test.entity.Client;
import com.haulmont.test.service.ClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/clients")
public class ClientController {

    private ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
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
        theModel.addAttribute("client", theClient);
        return "clients/client-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("clientId") UUID theId, Model theModel) {
        Client theClient = clientService.findById(theId);
        theModel.addAttribute("client", theClient);
        return "clients/client-form";
    }

    @PostMapping("/save")
    public String saveClient(@ModelAttribute("client") Client theClient) {
        clientService.save(theClient);
        return "redirect:/clients/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("clientId") UUID theId) {
        clientService.deleteById(theId);
        return "redirect:/clients/list";
    }
}