package com.zhide.govwatch.imple;

import com.zhide.govwatch.define.IRoleFunctionService;
import com.zhide.govwatch.mapper.RoleFunctionSaveMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: RoleFunctionsServiceImpl
 * @Author: 肖新民
 * @*TODO:
 * @CreateTime: 2020年12月13日 21:46
 **/
@Service
public class RoleFunctionsServiceImpl implements IRoleFunctionService {
    @Autowired
    RoleFunctionSaveMapper funSaveMapper;

    /**
     * create by: mmzs
     * description: TODO
     * create time:
     * 当前角色可用的。
     *
     * @return
     */
    @Override
    @Cacheable(value = "RoleHasFuns")
    public List<String> GetAllRoleFunctions(Integer  RoleID, String MenuID) {
        return funSaveMapper.getAllRoleFunctions(MenuID, RoleID);
    }

    @Override
    @Cacheable(value = "RoleAllFuns")
    public List<String> GetAllFunctions(String MenuID) {
        return funSaveMapper.getAllFunctions(MenuID);
    }

    @Override
    @Cacheable(value = "AllRoleFuns")
    public List<String>GetAllFunctionsByRole(String RoleName){
        return funSaveMapper.getAllFunctionsByRole(RoleName);
    }
}
