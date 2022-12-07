package com.zhide.govwatch.repository;

import com.zhide.govwatch.model.patentupdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface patentupdateRepository extends JpaRepository<patentupdate, Integer> {
    Optional<patentupdate> findFirstByShenqinghAndNo(String Shenqingh, String No);

    @Query(value = "select ROUND(((select count(0) from patentupdate where No=:No and  ifnull(gupdate,0)>0)/(select " +
            "count(0) from patentupdate where No=:No))*100,2) as percent from dual", nativeQuery = true)
    Double getPercent(String No);

    List<patentupdate> findAllByNo(String No);

    int deleteByNo(String No);
}
