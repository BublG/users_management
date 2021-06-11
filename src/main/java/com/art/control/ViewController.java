package com.art.control;

import com.art.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class ViewController {

    private UserAccountService userAccountService;

    @Autowired
    public void setUserAccountService(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @GetMapping("")
    public String viewList(Model model, Principal principal) {
        model.addAttribute("userAccounts", userAccountService.getAllUserAccounts());
        model.addAttribute("name", principal.getName());
        return "list";
    }

    @GetMapping("/{id}")
    public String viewUser(@PathVariable("id") long id, Model model) {
        model.addAttribute("userAccount", userAccountService.loadUserAccountById(id));
        return "view";
    }
}
