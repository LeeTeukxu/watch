package com.zhide.govwatch.repository;

import com.zhide.govwatch.model.view_govfee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @ClassName: view_govWaitRepository
 * @Author: 肖新民
 * @*TODO:
 * @CreateTime: 2022年06月23日 22:31
 **/
@Repository
public interface view_govfeeRepository extends JpaRepository<view_govfee,Integer> {
}
