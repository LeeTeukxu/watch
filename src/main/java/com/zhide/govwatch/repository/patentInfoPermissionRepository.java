package com.zhide.govwatch.repository;

import com.zhide.govwatch.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface patentInfoPermissionRepository  extends  JpaRepository<patentInfoPermission,Integer>  {
    Optional<patentInfoPermission> findByShenqingh(String shenqingh);
    List<patentInfoPermission> findAllByShenqinghAndUsertypeAndUserid (String shenqingh,String userType,Integer userId);
    @Transactional(rollbackFor = Exception.class)
    int  deleteAllByShenqingh(String shenqingh);
    @Transactional(rollbackFor = Exception.class)
    int deleteAllByShenqinghIn(List<String> IDS);
    @Transactional(rollbackFor = Exception.class)
    int deleteAllByUserid(Integer UserID);
    patentInfoPermission getAllByUserid(Integer UserID);
    List<patentInfoPermission> findAllByShenqingh(String SHENQINGH);
    List<patentInfoPermission> findAllByShenqinghIn(List<String> IDS);
    List<patentInfoPermission> findAllByUserid(Integer UserID);
    patentInfoPermission getAllByUseridAndUsertype(Integer UserID,String UserType);

}
