package com.zhide.govwatch.repository;

import com.zhide.govwatch.model.tbMenuList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface tbMenuListRepository extends JpaRepository<tbMenuList, Integer> {
    List<tbMenuList> findAllByCanUseTrueOrderBySn();

    @Query(value = "Select Title from tbmenulist  Where FID=:Fid", nativeQuery = true)
    String getTitleByFid(Integer Fid);
}
