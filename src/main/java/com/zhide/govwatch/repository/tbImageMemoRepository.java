package com.zhide.govwatch.repository;

import com.zhide.govwatch.model.tbImageMemo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface tbImageMemoRepository extends JpaRepository<tbImageMemo,Integer> {
    List<tbImageMemo> findAllByPid(String PID);
    int deleteAllByPid(String PID);
}
