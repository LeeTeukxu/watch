package com.zhide.govwatch.repository;

import com.zhide.govwatch.model.tbClient;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface tbClientRepository extends JpaRepository<tbClient, Integer> {
    tbClient findByAccount(String account);
    Optional<tbClient> findFirstByCreditCode(String CreditCode);
    Optional<tbClient> findFirstByName(String Name);
    List<tbClient> findAllByNameIn(List<String> ClientNames);
    @Query(value="SELECT * FROM tbclient ORDER BY INSTR(Name,:Name) desc",nativeQuery = true)
    List<tbClient>findtbClentall(@Param("Name")String Name);
}
