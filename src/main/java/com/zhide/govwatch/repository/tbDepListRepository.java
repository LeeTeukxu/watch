package com.zhide.govwatch.repository;

import com.zhide.govwatch.model.tbDepList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface tbDepListRepository extends JpaRepository<tbDepList, Integer> {
    Optional<tbDepList> findByCompanyId(Integer CompanyID);
    List<tbDepList> findAllByPidAndCanUseTrueOrderBySort(Integer PID);
    List<tbDepList> findAllByCompanyId(Integer CompanyID);

    @Query(value = "Select MAX(ifnull(DepID,0))+1 as 'Num' from tbDepList", nativeQuery = true)
    Integer getMax();

    @Query(value = "select Dep.*,Pantent.* from tbDepList as Dep left join PatentInfoPermission as Pantent on Dep.DepID=Pantent.USERID"+
            " WHERE Dep.DepID=:DepId or Dep.PID=:DepId", nativeQuery = true)
    List<Map<String,Object>> getDepPantPermissionXinxi(int DepId);

    List<tbDepList> getAllByCanUse(boolean canUse);

    List<tbDepList> findAllByDepId(int depId);

    @Query(value = "SELECT a.Name,a.PID,a.DepID,a.Sort, (SELECT COUNT(*) FROM v_LoginUser AS b WHERE a.DepID=b.DepID)" +
            " AS Num FROM tbDepList AS a", nativeQuery = true)
    List<Map<String, Object>> getAllByCanUseAndDepNum();

    List<tbDepList> findAllByName(String Name);

    @Query(value = "select top 1  * from tbDepList where Deptype = 2 order by DepID desc", nativeQuery = true)
    List<tbDepList> findOderdescShizhouName();

    @Query(value = "select top 1  * from tbDepList where PID=:PID AND Deptype=3 order by DepID desc", nativeQuery = true)
    List<tbDepList> findOderdescYuanquPID(int PID);

    @Query(value = "Select FID,PID,Name from v_depEmployee", nativeQuery = true)
    List<Map<String, Object>> getAllUsersInDep();

    @Query(value = "Select FID,PID,Name from View_AllLoginUserInDep", nativeQuery = true)
    List<Map<String, Object>> getAllLoginUserInDep();
    @Query(value="Select * from dbo.getAllUsersByFunName(:FunName)",nativeQuery = true)
    List<Map<String,Object>> getAllLoginUserByFun(String FunName);
}
