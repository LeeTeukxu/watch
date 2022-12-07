package com.zhide.govwatch.repository;

import com.zhide.govwatch.model.YearFeeBase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface YearFeeBaseRepository extends  JpaRepository<YearFeeBase,Integer>  {

}
