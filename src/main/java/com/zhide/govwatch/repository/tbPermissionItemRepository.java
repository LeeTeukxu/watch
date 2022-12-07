package com.zhide.govwatch.repository;

import com.zhide.govwatch.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface tbPermissionItemRepository  extends  JpaRepository<tbPermissionItem,Integer>  {

}
