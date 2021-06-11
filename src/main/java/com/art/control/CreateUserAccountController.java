package com.art.control;

import com.art.entity.UserAccount;
import com.art.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class CreateUserAccountController {

    private UserAccountService userAccountService;

    @Autowired
    public void setUserAccountService(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @GetMapping("/new")
    public String getAddPage() {
        return "new";
    }

    @PostMapping("/new")
    public String addUserAccount(Model model, @RequestParam Map<String, String> form) {
        UserAccount userAccount = new UserAccount(form.get("username"), form.get("password"), form.get("firstName"),
                form.get("lastName"), form.get("role"), form.get("status"), new Date());
        if (!userAccountService.saveUserAccount(userAccount)) {
            model.addAttribute("error", "Пользователь с таким именем уже существует");
            return "new";
        }
        model.addAttribute("userAccounts", userAccountService.getAllUserAccounts());
        return "redirect:/user";
    }
}
