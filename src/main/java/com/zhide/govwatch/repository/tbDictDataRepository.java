package com.zhide.govwatch.repository;

import com.zhide.govwatch.model.tbDictData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface tbDictDataRepository extends JpaRepository<tbDictData, Integer> {
    List<tbDictData> findAllByDtIdAndCanUseTrueOrderBySn(Integer dtId);

    List<tbDictData> findAllByDtIdAndCanUseTrue(Integer dtId);

    Optional<tbDictData> findAllByFid(int FID);

    Page<tbDictData> findAllBydtId(Integer dtId, Pageable page);
}
