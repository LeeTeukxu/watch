package com.zhide.govwatch.repository;

import com.zhide.govwatch.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FunctionItemRepository extends JpaRepository<FunctionItem, Integer> {
    List<FunctionItem> findAllByCanUseOrderBySn(boolean canUse);
}
