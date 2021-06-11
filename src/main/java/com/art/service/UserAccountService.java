package com.art.service;

import com.art.entity.UserAccount;
import com.art.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAccountService implements UserDetailsService {

    private UserAccountRepository userAccountRepository;

    private PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public void setUserAccountRepository(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public List<UserAccount> getAllUserAccounts() {
        return userAccountRepository.findAll();
    }

    public UserAccount loadUserAccountById(long id) {
        UserAccount userAccount = userAccountRepository.findUserAccountById(id);
        if (userAccount == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return userAccount;
    }

    public boolean usernameExists(String username) {
        return userAccountRepository.findUserAccountByUsername(username) != null;
    }

    public boolean saveUserAccount(UserAccount userAccount) {
        if (userAccountRepository.findUserAccountByUsername(userAccount.getUsername()) != null) {
            return false;
        }
        save(userAccount);
        return true;
    }

    public void save(UserAccount userAccount) {
        userAccount.setPassword(bCryptPasswordEncoder.encode(userAccount.getPassword()));
        userAccountRepository.save(userAccount);
    }

    public void update(UserAccount userAccount) {
        userAccountRepository.save(userAccount);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserAccount userAccount = userAccountRepository.findUserAccountByUsername(s);
        if (userAccount == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return userAccount;
    }
}
