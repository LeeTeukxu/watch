package com.zhide.govwatch.repository;

import com.zhide.govwatch.model.tbOrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface tbOrderDetailRepository extends JpaRepository<tbOrderDetail, Integer> {
    void deleteAllByOrderNoIn(List<String> listOrderNo);
    List<tbOrderDetail> getAllByOrderNo(String OrderNo);
    List<tbOrderDetail> findAllByOrderNoIn(List<String> listOrderNo);
}
