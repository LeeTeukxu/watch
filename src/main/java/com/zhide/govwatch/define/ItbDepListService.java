package com.zhide.govwatch.define;

import com.zhide.govwatch.model.TreeNode;
import com.zhide.govwatch.model.tbDepList;

import java.util.List;
import java.util.Map;

public interface ItbDepListService {
    List<tbDepList> getAll(String CompanyID);

    List<tbDepList> getAllCanUse();

    List<tbDepList> getAllDep(int DepId);

    List<tbDepList>findAllByDepId(int DepId);

    List<Map<String,Object>> Chengshixiaqiye();

    List<Map<String,Object>> getDepPantPermissionXinxi(int DepId);

    boolean saveAll(List<Map<String, Object>> datas, String CompanyID);

    boolean removeAll(List<Integer> ids);

    List<TreeNode> getAllUsersByDep();

    List<TreeNode> getAllLoginUserInDep();

    List<tbDepList> getfindAllByName(String Name) throws Exception;

    tbDepList shengSave();

    tbDepList ShiSave(String Name);

    tbDepList YuanquSave(String Name,int ShidepId,String ShiSort);

    List<tbDepList> findAllByDeptype();

    List<Map<String, Object>> getAllByCanUseAndDepNum();
    Map<Integer, Integer> GetEmployeeNumbers() throws Exception;
    List<Map<String,Object>> getAllLoginUserByFun(String FunName);

    Map<String, Integer> getAllByNameAndID() throws Exception;
}
