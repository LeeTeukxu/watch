package com.zhide.govwatch.repository;


import com.zhide.govwatch.model.view_goypay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface view_govPayRepository extends  JpaRepository<view_goypay,Integer>  {
    List<view_goypay>  findAllBySHENQINGH(String shenqingh);

}
