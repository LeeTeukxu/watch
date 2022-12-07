package com.zhide.govwatch.repository;

import com.zhide.govwatch.model.v_LoginUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface v_LoginUserRepository extends JpaRepository<v_LoginUser, Integer> {
    v_LoginUser findAllByLoginCode(String loginCode);
    v_LoginUser findAllByUserId(Integer UserID);
}

