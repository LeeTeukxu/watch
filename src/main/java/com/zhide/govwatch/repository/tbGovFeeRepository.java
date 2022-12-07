package com.zhide.govwatch.repository;

import com.zhide.govwatch.model.tbGovFee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface tbGovFeeRepository extends JpaRepository<tbGovFee,Integer>  {
    Optional<tbGovFee> findByAppNo(String shenqingh);

    List<tbGovFee> findAllByAppNo(String shenqingh);

    List<tbGovFee> findAllByAppNoIn(List<String> AppNo);

    List<tbGovFee> findAllByAppNoAndCostName(String AppNo, String CostName);
}
