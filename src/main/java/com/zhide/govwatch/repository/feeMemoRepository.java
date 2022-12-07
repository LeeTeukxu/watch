package com.zhide.govwatch.repository;

import com.zhide.govwatch.model.feeMemo;
import com.zhide.govwatch.model.followRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface feeMemoRepository extends JpaRepository<feeMemo, Integer> {
    List<feeMemo> getAllByShenqinghAndFeename(String SHENQINGH, String FEENAME);
    Optional<feeMemo> findFirstBySubId(String SubId);
}
