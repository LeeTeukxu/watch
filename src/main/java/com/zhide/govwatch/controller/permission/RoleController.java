package com.zhide.govwatch.controller.permission;

import com.zhide.govwatch.common.successResult;
import com.zhide.govwatch.define.ItbRoleClassService;
import com.zhide.govwatch.mapper.SysLoginUserMapper;
import com.zhide.govwatch.model.TreeListItem;
import com.zhide.govwatch.model.tbRoleClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/permission/roleClass")
public class RoleController {

    @Autowired
    private ItbRoleClassService itbRoleClassService;

    @Autowired
    private SysLoginUserMapper sysMapper;

    @RequestMapping("/index")
    public String Index() {
        return "/permission/roleClass/index";
    }

    @RequestMapping("/getAllCanUse")
    @ResponseBody
    public List<TreeListItem> getAllCanUseItems() {
        List<TreeListItem> lists = itbRoleClassService.getAllCanuseItems(true);
        return lists;
    }

    @RequestMapping("/getAll")
    @ResponseBody
    public List<tbRoleClass> getAll() {
        return itbRoleClassService.getAll();
    }

    @RequestMapping("/saveAll")
    @ResponseBody
    public successResult SaveAll(@RequestBody List<tbRoleClass> datas) {
        successResult result = new successResult();
        try {
            itbRoleClassService.saveAll(datas);
        } catch (Exception ax) {
            result.raiseException(ax);
        }
        return result;
    }

    @RequestMapping("/removeAll")
    @ResponseBody
    public successResult removeAll(@RequestBody List<Integer> ids) {
        successResult result = new successResult();
        try {
            itbRoleClassService.removeAll(ids);
        } catch (Exception ax) {
            result.raiseException(ax);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/getAllUserByRole")
    public List<Map<String, Object>> getAllUserByRole(Integer RoleID) {
        return sysMapper.getAllUserByRole(RoleID);
    }
}
