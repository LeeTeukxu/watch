package com.zhide.govwatch.repository;

import com.zhide.govwatch.model.patentarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface patentareaRepository  extends  JpaRepository<patentarea,Integer>  {
    Optional<patentarea> findFirstByAreaIdAndUserId(Integer AreaID,Integer UserID);
    int deleteAllByUserId(Integer UserID);
    List<patentarea> findAllByUserId(Integer UserID);
}
