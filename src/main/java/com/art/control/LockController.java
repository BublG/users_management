package com.art.control;

import com.art.entity.UserAccount;
import com.art.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class LockController {

    private UserAccountService userAccountService;

    @Autowired
    public void setUserAccountService(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @GetMapping("/{id}/lock")
    public String lock(@PathVariable long id) {
        setStatus(id, "INACTIVE");
        return "redirect:/user/" + id;
    }

    @GetMapping("/{id}/unlock")
    public String unlock(@PathVariable long id) {
        setStatus(id, "ACTIVE");
        return "redirect:/user/" + id;
    }

    private void setStatus(long id, String status) {
        UserAccount userAccount = userAccountService.loadUserAccountById(id);
        userAccount.setStatus(status);
        userAccountService.update(userAccount);
    }
}
