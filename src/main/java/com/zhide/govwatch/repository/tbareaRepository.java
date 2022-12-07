package com.zhide.govwatch.repository;

import com.zhide.govwatch.model.tbarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface tbareaRepository  extends  JpaRepository<tbarea,Integer>  {
    Optional<tbarea> findFirstByNameLike(String name);

    List<tbarea> findAllByPidIn(List<Integer> IDS);

    @Query(value = "SELECT * FROM tbarea WHERE Name=:ProvinceName OR Name=:CityName OR Name=:CountyName",
            nativeQuery = true)
    List<tbarea> getareainfo(String ProvinceName, String CityName, String CountyName);
}
