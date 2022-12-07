package com.zhide.govwatch.repository;

import com.zhide.govwatch.model.TZS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TZSRepository extends JpaRepository<TZS, String> {
    @Query(value = "Select  top 20  TONGZHISBH from TZS Where len(replace(isnull(TZSPATH,''),' ',''))<3  and TONGZHISBH not in (Select TZSID from  NotExistTZS)  Order by  XIAZAIRQ ", nativeQuery = true)
    List<String> getNotuploadTZS();

    List<TZS> findAllByTongzhisbhIn(String[] codes);

    List<TZS> findAllByShenqinghIn(String[] codes);

    @Query(value = "SELECT TONGZHISMC FROM TZS WHERE TONGZHISBH=:TONGZHISBH", nativeQuery = true)
    String getTongzhismcByTongzhisbh(String TONGZHISBH);

    @Query(value = "Select top 200 * from TZS a Where TZSPath is not null And SHENQINGH is not null And Tongzhismc in" +
            " (?1) And  not Exists(Select Distinct SHENQINGH from tbFeeItem b  Where b.Type='Apply' And a.SHENQINGH=b" +
            ".SHENQINGH)  and tongzhisbh not in(Select tzsid from tbFeeItemError)", nativeQuery = true)
    List<TZS> findAllApplyNeedDecodeTzs(List<String> tongzhismc);

    @Query(value = "Select top 200 * from TZS a  Where TZSPath is not null And SHENQINGH is not null And Tongzhismc " +
            "in(?1) And  not Exists(Select Distinct SHENQINGH from tbFeeItem b  Where b.Type='Year' And a.SHENQINGH=b" +
            ".SHENQINGH)  and shenqingh not in(Select Shenqingh from YearWatchNotExistXml)", nativeQuery = true)
    List<TZS> findAllYearNeedDecodeTzs(List<String> tongzhismc);

    @Query(value = "Select * from TZS Where  len(isnull(shenqingh,''))<5 and TONGZHISMC not in('电子申请待处理回执'," +
            "'电子申请拒收回执')", nativeQuery = true)
    List<TZS> findAllEmptyShenqingh();
}
