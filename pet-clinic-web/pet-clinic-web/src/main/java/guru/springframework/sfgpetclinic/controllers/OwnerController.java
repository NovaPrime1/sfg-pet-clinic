package guru.springframework.sfgpetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/* trick with RequestMapping. So anything goes to owners with render this view. Because it is at the class level */
@RequestMapping("/owners")
@Controller
public class OwnerController {

    @RequestMapping({"","/", "/index", "/index.html"})
    public String listOwners(){
        return "owners/index";
    }
}
