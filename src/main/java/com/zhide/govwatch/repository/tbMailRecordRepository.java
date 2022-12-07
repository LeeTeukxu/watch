package com.zhide.govwatch.repository;

import com.zhide.govwatch.model.tbMailRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface tbMailRecordRepository extends JpaRepository<tbMailRecord,Integer> {
    tbMailRecord findAllByGovFeeIdAndDay(Integer GovFeeID, String Day);
}
