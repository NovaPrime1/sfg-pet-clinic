package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/* trick with RequestMapping. So anything goes to owners with render this view. Because it is at the class level */
@RequestMapping("/owners")
@Controller
public class OwnerController {

    private final OwnerService ownerService;

    // Constructor injection is the preferred way working with injected properties. Only one way to instanciate it.
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @RequestMapping({"","/", "/index", "/index.html"})
    public String listOwners(Model model){

        model.addAttribute("owners", ownerService.findAll()); // give us a set to iterate over.
        return "owners/index";
    }

    @RequestMapping("/find" )
    public String findOwners(){

        return "notimplemented";
    }



}
