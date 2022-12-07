package com.zhide.govwatch.controller.system;

import com.alibaba.fastjson.JSON;
import com.zhide.govwatch.common.CompanyContext;
import com.zhide.govwatch.common.pageObject;
import com.zhide.govwatch.common.successResult;
import com.zhide.govwatch.define.ILoginUserService;
import com.zhide.govwatch.define.ItbCompanyService;
import com.zhide.govwatch.define.ItbRoleClassService;
import com.zhide.govwatch.model.*;
import com.zhide.govwatch.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @ClassName: LoginUserController
 * @Author: 肖新民
 * @*TODO:
 * @CreateTime: 2021年12月15日 9:44
 **/
@RequestMapping("/myUser")
@Controller
public class MyUserController {
    @Autowired
    ItbCompanyService companyService;
    @Autowired
    ILoginUserService loginService;
    @Autowired
    ItbRoleClassService roleService;
    @Autowired
    tbCompanyRepository companyRep;
    @Autowired
    tbDepListRepository depRep;
    @Autowired
    tbLoginUserRepository userRep;
    @Autowired
    patentareaRepository areaRep;
    @Autowired
    tbareaRepository aRep;

    @RequestMapping("/index")
    public String Index() {
        return "/loginUser/index";
    }

    @RequestMapping("/add")
    public String add(Integer CompanyID, Map<String, Object> model) {
        model.put("Mode", "Add");
        model.put("Data", "{}");
        if (CompanyID == null) CompanyID = 0;
        model.put("CompanyID", CompanyID);
        return "/loginUser/edit";
    }

    @RequestMapping("/edit")
    public String edit(Integer UserID, Map<String, Object> model) throws Exception {
        model.put("Mode", "Edit");
        Optional<tbLoginUser> findUsers = userRep.findById(UserID);
        if (findUsers.isPresent()) {
            tbLoginUser user = findUsers.get();
            List<patentarea> areas = areaRep.findAllByUserId(user.getUserId());
            if (areas.size() > 0) {
                List<String> tt = areas.stream().map(f -> Integer.toString(f.getAreaId())).collect(Collectors.toList());
                user.setAreaId(String.join(",", tt));
                List<Integer> dd = areas.stream().map(f -> f.getAreaId()).collect(Collectors.toList());
                List<tbarea> aas = aRep.findAllById(dd);
                String aap = String.join(",", aas.stream().map(f -> f.getName()).collect(Collectors.toList()));
                user.setAreaName(aap);

            }
            model.put("Data", JSON.toJSONString(user));
            model.put("CompanyID", user.getCompanyId());
        } else throw new Exception("指定的用户资料不存在!");
        return "/loginUser/edit";
    }

    @RequestMapping("/getCompanyList")
    @ResponseBody
    public List<TreeNode> getCompanyList() {
        List<TreeNode> Nodes = new ArrayList<>();
        try {
            Nodes = companyService.getCompanyList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Nodes;
    }

    @RequestMapping("/getRoles")
    @ResponseBody
    public List<TreeNode> getAllRoles() {
        LoginUserInfo Info = CompanyContext.get();
        Integer RoleID = Info.getRoleId();
        List<TreeNode> Nodes = new ArrayList<>();
        List<tbRoleClass> roles =
                roleService.getAll().stream().filter(f -> f.getRoleId() == 1 || f.getRoleId() > RoleID).collect(Collectors.toList());
        for (int i = 0; i < roles.size(); i++) {
            tbRoleClass role = roles.get(i);
            TreeNode node = new TreeNode();
            node.setPID(Integer.toString(role.getPid()));
            node.setFID(Integer.toString(role.getRoleId()));
            node.setName(role.getRoleName());
            Nodes.add(node);
        }
        return Nodes;
    }



    @ResponseBody
    @RequestMapping("/getData")
    public pageObject getData(HttpServletRequest request) {
        pageObject object = new pageObject();
        try {
            object = loginService.getMineData(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return object;
    }

    @ResponseBody
    @RequestMapping("/save")
    public successResult save(String Data) {
        successResult result = new successResult();
        try {
            tbLoginUser login = JSON.parseObject(Data, tbLoginUser.class);
            tbLoginUser oo = loginService.SaveMine(login);
            result.setData(oo);
        } catch (Exception ax) {
            result.raiseException(ax);
        }
        return result;
    }

    @RequestMapping("/remove")
    @ResponseBody
    public successResult remove(Integer UserID) {
        successResult result = new successResult();
        try {
            loginService.RemoveUser(UserID);
        } catch (Exception ax) {
            result.raiseException(ax);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/createCode")
    public successResult creatCode(HttpServletRequest request, Integer UserID) {
        successResult result = new successResult();
        try {
            String DD = request.getRequestURL().toString();
            DD = DD.replace("/myUser/createCode", "");
            String TT = loginService.CreateCode(DD, UserID);
            result.setData(TT);
        } catch (Exception ax) {
            result.raiseException(ax);
        }
        return result;
    }
}
