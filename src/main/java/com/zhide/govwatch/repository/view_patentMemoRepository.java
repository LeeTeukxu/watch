package com.zhide.govwatch.repository;

import com.zhide.govwatch.model.view_patentMemo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface view_patentMemoRepository extends JpaRepository<view_patentMemo,Integer> {
    Page<view_patentMemo>findAllByShenqingh(String SHENQINGH,Pageable page);
}
