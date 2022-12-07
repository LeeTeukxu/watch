package com.zhide.govwatch.repository;

import com.zhide.govwatch.model.companyConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface companyConfigRepository extends JpaRepository<companyConfig,String> {

}
