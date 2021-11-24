package com.haulmont.test.controller;

import com.haulmont.test.entity.*;
import com.haulmont.test.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/offers")
public class OfferController {

    private OfferService offerService;
    private CreditService creditService;
    private ClientService clientService;
    private ScheduleService scheduleService;

    @Autowired
    public void setOfferService(OfferService offerService) {
        this.offerService = offerService;
    }

    @Autowired
    public void setCreditService(CreditService creditService) {
        this.creditService = creditService;
    }

    @Autowired
    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }

    @Autowired
    public void setScheduleService(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping("/list")
    public String listOffers(Model theModel) {
        List<Offer> theOffers = offerService.findAll();
        theModel.addAttribute("offers", theOffers);
        return "offers/list-offers";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {
        Offer theOffer = new Offer();
        List<Client> clients = clientService.findAll();
        List<Credit> credits = creditService.findAll();
        theModel.addAttribute("offer", theOffer);
        theModel.addAttribute("clients", clients);
        theModel.addAttribute("credits", credits);
        return "offers/offer-form";
    }

    @GetMapping("/showFormForSchedule")
    public String showFormForSchedule(@RequestParam("offerId") UUID theId, Model theModel) {
        Offer theOffer = offerService.findById(theId);
        theModel.addAttribute("offer", theOffer);
        return "offers/list-schedules";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("offerId") UUID theId, Model theModel) {
        Offer theOffer = offerService.findById(theId);
        List<Client> clients = clientService.findAll();
        List<Credit> credits = creditService.findAll();
        theModel.addAttribute("offer", theOffer);
        theModel.addAttribute("clients", clients);
        theModel.addAttribute("credits", credits);
        return "offers/offer-form";
    }

    @PostMapping("/save")
    public String saveOffer(@ModelAttribute("offer") Offer theOffer) {
        offerService.save(theOffer);
        List<Schedule> schedules = scheduleService.findAll(theOffer.getTermInMonth(),
                theOffer.getCredit().getInterestRate(), theOffer.getCreditSum(), theOffer);
        scheduleService.saveAll(schedules);
        return "redirect:/offers/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("offerId") UUID theId) {
        offerService.deleteById(theId);
        return "redirect:/offers/list";
    }
}