package com.art.control;

import com.art.entity.UserAccount;
import com.art.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/user")
public class EditUserAccountController {

    private UserAccountService userAccountService;

    @Autowired
    public void setUserAccountService(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @GetMapping("/{id}/edit")
    public String getEditPage(@PathVariable("id") long id, Model model) {
        model.addAttribute("userAccount", userAccountService.loadUserAccountById(id));
        return "edit";
    }

    @PostMapping("/{id}/edit")
    public String editUserAccount(@PathVariable("id") long id, Model model, @RequestParam Map<String, String> form) {
        UserAccount userAccount = userAccountService.loadUserAccountById(id);
        String oldUsername = userAccount.getUsername();
        editAccount(userAccount, form);
        if (!oldUsername.equals(userAccount.getUsername()) &&
                userAccountService.usernameExists(userAccount.getUsername())) {
            model.addAttribute("error", "Пользователь с таким именем уже существует");
            model.addAttribute("userAccount", userAccount);
            return "edit";
        }
        userAccountService.save(userAccount);
        model.addAttribute("userAccounts", userAccountService.getAllUserAccounts());
        return "redirect:/user";
    }

    private void editAccount(UserAccount userAccount, Map<String, String> form) {
        userAccount.setUsername(form.get("username"));
        userAccount.setPassword(form.get("password"));
        userAccount.setFirstName(form.get("firstName"));
        userAccount.setLastName(form.get("lastName"));
        userAccount.setRole(form.get("role"));
        userAccount.setStatus(form.get("status"));
    }
}
