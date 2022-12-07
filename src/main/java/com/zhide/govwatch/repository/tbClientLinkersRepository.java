package com.zhide.govwatch.repository;

import com.zhide.govwatch.model.tbClientLinkers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface tbClientLinkersRepository  extends  JpaRepository<tbClientLinkers,Integer>  {
    Page<tbClientLinkers> findAllByClientID(int ClientID,Pageable pageable);
    List<tbClientLinkers> findAllByClientID(int ClientID);
}
