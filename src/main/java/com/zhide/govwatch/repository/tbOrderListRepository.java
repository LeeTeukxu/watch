package com.zhide.govwatch.repository;

import com.zhide.govwatch.model.tbOrderList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface tbOrderListRepository extends JpaRepository<tbOrderList, Integer> {
    Optional<tbOrderList> findAllByOrderNo(String OrderNo);
    List<tbOrderList> findAllByOrderListIdIn(List<Integer> OrderListID);
    List<tbOrderList> getAllByOrderNo(String OrderNo);
}
