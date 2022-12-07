package com.zhide.govwatch.repository;

import com.zhide.govwatch.model.patentupdatemain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface patentupdatemainRepository extends JpaRepository<patentupdatemain, String> {
    int deleteByNo(String No);

    Optional<patentupdatemain> findFirstByNo(String No);
}
