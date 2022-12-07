package com.zhide.govwatch.repository;

import com.zhide.govwatch.model.tbCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface tbCompanyRepository extends JpaRepository<tbCompany,Integer> {

    Optional<tbCompany> findFirstByName(String Name);
}
