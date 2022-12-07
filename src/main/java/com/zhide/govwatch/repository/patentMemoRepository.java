package com.zhide.govwatch.repository;

import com.zhide.govwatch.model.patentMemo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface patentMemoRepository extends JpaRepository<patentMemo,Integer>  {
    Optional<patentMemo> findFirstByMid(String  MID);
}
