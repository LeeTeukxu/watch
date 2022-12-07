package com.zhide.govwatch.repository;

import com.zhide.govwatch.model.view_OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface view_OrderDetailsRepository extends JpaRepository<view_OrderDetails, Integer> {
    List<view_OrderDetails> findAllByOrderNoIn(List<String> OrderNo);
    List<view_OrderDetails> getAllByOrderNo(String OrderNo);
}
