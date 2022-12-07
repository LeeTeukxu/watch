package com.zhide.govwatch.repository;

import com.zhide.govwatch.model.patent;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatentRepository extends JpaRepository<patent, Integer> {
        Optional<patent> findFirstByShenqingh(String shenqingh);
        List<patent> findAllByShenqinghIn(List<String> IDS);
        patent findAllByShenqingh(String shenqingh);
        List<patent> findAllByShenqingrxm(String shenqingrxm);
        @Query(value = "select * from `patent` where SHENQINGH in (:number1,:number2)",nativeQuery = true)
        List<patent> findByShenqinghInnumber(@Param("number1") String number1, @Param("number2") String number2);
        Optional<patent> findByShenqingh(String shenqingh);
        @Query(value="Select Shenqingh from Patent a  left  join lawstatus b on a.LAWSTATUS=b.`Name` Where IFNULL(b.`Status`,0)!=4 order by LASTUPDATETIME  limit ?1,50",nativeQuery = true)
        List<String> getAllCodesByLastUpdateTime(@Param("pageIndex")Integer pageIndex);
        @Query(value="Select Shenqingh from Patent a  left  join lawstatus b on a.LAWSTATUS=b.`Name` Where IFNULL(b" +
                ".`Status`,0)!=4 And IFNULL(Lawstatus,'')='' order by LASTUPDATETIME  limit ?1,50",nativeQuery = true)
        List<String> getAllCodesByLawStatus(@Param("pageIndex")Integer pageIndex);

        @Query(value = "SELECT DISTINCT LAWSTATUS FROM patent WHERE LAWSTATUS IS NOT NULL",nativeQuery = true)
        List<String> getDistinctByLawstatus();
        @Query(value = "SELECT DISTINCT SECLAWSTATUS FROM patent WHERE SECLAWSTATUS IS NOT NULL",nativeQuery = true)
        List<String> getDistinctBySeclawstatus();
        int countAllByLawstatusIsNull();

        List<patent> findAllByClientId(int ClientID);

}
