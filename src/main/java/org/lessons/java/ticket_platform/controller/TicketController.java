package org.lessons.java.ticket_platform.controller;

import org.lessons.java.ticket_platform.model.Ticket;
import org.lessons.java.ticket_platform.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private TicketRepository ticketRepository;

    @GetMapping
    public String index(Model model){
        model.addAttribute("tickets", ticketRepository.findAll());

        return "tickets/index";
        
    }

     @GetMapping("/{id}")
    public String show( @PathVariable Integer id,Model model){

        model.addAttribute("ticket", ticketRepository.findById(id).get());
        return "tickets/show";
    }


     @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("ticket", new Ticket());
        return "tickets/create";
    }

    @PostMapping
    public String store( @Valid @ModelAttribute("ticket") Ticket formTicket, BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            return "tickets/create";
        }

        ticketRepository.save(formTicket);
        return "redirect:/games";
    }



    

   
    
}
