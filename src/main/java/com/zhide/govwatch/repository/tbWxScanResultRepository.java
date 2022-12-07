package com.zhide.govwatch.repository;

import com.zhide.govwatch.model.tbWxScanResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface tbWxScanResultRepository extends JpaRepository<tbWxScanResult, Integer> {
    Optional<tbWxScanResult> findAllByOrderNo(String OrderNo);
}
