package com.zhide.govwatch.repository;

import com.zhide.govwatch.model.tbPatentContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatentContactRepository extends  JpaRepository<tbPatentContact,Integer>  {
    Optional<tbPatentContact> findAllByUserId(Integer UserID);
}
