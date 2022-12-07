package com.zhide.govwatch.repository;

import com.zhide.govwatch.model.companyall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface companyallRepository  extends  JpaRepository<companyall,Integer>  {
        List<companyall> findAllByCreditCodeIn(List<String> Codes);
        Optional<companyall> findFirstByName(String Name);
}
