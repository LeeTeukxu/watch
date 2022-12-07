package com.zhide.govwatch.repository;

import com.zhide.govwatch.model.machine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface machineRepository extends JpaRepository<machine, String> {

}
