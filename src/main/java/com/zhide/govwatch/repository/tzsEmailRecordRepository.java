package com.zhide.govwatch.repository;

import com.zhide.govwatch.model.tzsEmailRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface tzsEmailRecordRepository extends JpaRepository<tzsEmailRecord,Integer> {
    List<tzsEmailRecord> findAllByTongzhisbhOrderBySendTimeDesc(String tongzhisbh);
}
