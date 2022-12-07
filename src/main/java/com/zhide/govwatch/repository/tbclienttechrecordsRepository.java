package com.zhide.govwatch.repository;


import com.zhide.govwatch.model.tbclienttechrecords;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface tbclienttechrecordsRepository extends JpaRepository<tbclienttechrecords, Integer> {

}
