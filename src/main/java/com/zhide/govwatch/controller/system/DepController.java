package com.zhide.govwatch.controller.system;

import com.zhide.govwatch.common.successResult;
import com.zhide.govwatch.define.ItbDepListService;
import com.zhide.govwatch.model.Allow;
import com.zhide.govwatch.model.PermissionType;
import com.zhide.govwatch.model.TreeNode;
import com.zhide.govwatch.model.tbDepList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/system/dep")
public class DepController {

    @Autowired
    ItbDepListService itbDepListService;

    @ResponseBody
    @RequestMapping("/getAllLoginUsersByDep")
    public List<TreeNode> getAllLoginUserInDep() {
        return itbDepListService.getAllLoginUserInDep();
    }

    @RequestMapping("/index")
    public String  Index(){
        return "/system/dep/index";
    }

    @RequestMapping("/getAll")
    @ResponseBody
    public List<tbDepList> getAll(String CompanyID) {
        return itbDepListService.getAll(CompanyID);
    }

    @Allow(permission = PermissionType.Save)
    @RequestMapping(value = "/saveAll", method = RequestMethod.POST)
    @ResponseBody
    public successResult saveAll(@RequestBody List<Map<String, Object>> datas, String CompanyID) {
        successResult result = new successResult();
        try {
            itbDepListService.saveAll(datas,CompanyID);
        } catch (Exception ax) {
            result.raiseException(ax);
        }
        return result;
    }

    @RequestMapping(value = "/removeAll", method = RequestMethod.POST)
    @ResponseBody
    public successResult removeAll(@RequestBody List<Integer> ids) {
        successResult result = new successResult();
        try {
            itbDepListService.removeAll(ids);
        } catch (Exception ax) {
            result.raiseException(ax);
        }
        return result;
    }
}
