package com.zhide.govwatch.repository;

import com.zhide.govwatch.model.tbRoleFunctionSave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface tbRolePermissionRepository extends JpaRepository<tbRoleFunctionSave,Integer> {
    List<tbRoleFunctionSave> findAllByRoleId(int roleId);
    List<tbRoleFunctionSave> findAllByMenuIdAndRoleId(int menuId,int roleId);
    void removeByRoleId(int roleId);
}
