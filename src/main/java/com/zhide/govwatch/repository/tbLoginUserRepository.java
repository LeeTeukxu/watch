package com.zhide.govwatch.repository;

import com.zhide.govwatch.model.tbLoginUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface tbLoginUserRepository extends JpaRepository<tbLoginUser, Integer> {
    Optional<tbLoginUser> findAllByLoginCode(String loginCode);

    Optional<tbLoginUser> findAllByLoginCodeAndUserIdIsNot(String loginCode, Integer userId);

    List<tbLoginUser> findAllByDepIdIn(List<Integer> deps);

    tbLoginUser findAllByUserId(Integer UserID);

    List<tbLoginUser> findAllByUserName(String UserName);

    Optional<tbLoginUser> findFirstByMyCode(String MyCode);
}

