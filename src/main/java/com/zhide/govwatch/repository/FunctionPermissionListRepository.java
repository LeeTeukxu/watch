package com.zhide.govwatch.repository;

import com.zhide.govwatch.model.functionPermissionList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface FunctionPermissionListRepository extends JpaRepository<functionPermissionList,Integer> {

    @Query(value = "Select MAX(ifnull(SN,0))+1 as 'SN' from functionpermissionlist", nativeQuery = true)
    Integer getMax();

    Optional<functionPermissionList> findFirstByFid(Integer FID);
}
