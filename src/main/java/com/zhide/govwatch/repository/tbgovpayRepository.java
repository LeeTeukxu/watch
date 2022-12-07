package com.zhide.govwatch.repository;


import com.zhide.govwatch.model.tbgovpay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface tbgovpayRepository  extends  JpaRepository<tbgovpay,Integer>  {
    List<tbgovpay>  findAllByAppNo(String AppNo);
}
