package com.art.repository;

import com.art.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
    UserAccount findUserAccountById(long id);
    UserAccount findUserAccountByUsername(String username);
}
