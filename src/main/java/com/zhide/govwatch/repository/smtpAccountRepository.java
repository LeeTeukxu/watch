package com.zhide.govwatch.repository;

import com.zhide.govwatch.model.smtpAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface smtpAccountRepository extends JpaRepository<smtpAccount, Integer> {
    smtpAccount getByUserId(Integer userId);
    Optional<smtpAccount> findFirstByCompanyDefaultIsTrue();
    Optional<smtpAccount> getAllByUserId(Integer UserID);
}
