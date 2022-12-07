package com.zhide.govwatch.repository;

import com.zhide.govwatch.model.discode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface discodeRepository  extends  JpaRepository<discode,Integer>  {

}
