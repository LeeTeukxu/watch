package com.zhide.govwatch.repository;

import com.zhide.govwatch.model.followRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface followRecordRepository extends JpaRepository<followRecord, Integer> {
    Page<followRecord> findAllByClientId(int ClientID, Pageable page);
    Optional<followRecord> findFirstByFid(String FID);
}
