package com.zhide.govwatch.controller.system;

import com.alibaba.fastjson.JSON;
import com.zhide.govwatch.common.CompanyContext;
import com.zhide.govwatch.common.pageObject;
import com.zhide.govwatch.common.successResult;
import com.zhide.govwatch.define.ILoginUserService;
import com.zhide.govwatch.define.ItbCompanyService;
import com.zhide.govwatch.define.ItbRoleClassService;
import com.zhide.govwatch.model.*;
import com.zhide.govwatch.repository.tbCompanyRepository;
import com.zhide.govwatch.repository.tbDepListRepository;
import com.zhide.govwatch.repository.tbLoginUserRepository;
import javafx.print.PageOrientation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @ClassName: LoginUserController
 * @Author: 肖新民
 * @*TODO:
 * @CreateTime: 2021年12月15日 9:44
 **/
@RequestMapping("/loginUser")
@Controller
public class LoginUserController {
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

    @RequestMapping("/index")
    public String Index() {
        return "/system/loginUser/index";
    }

    @RequestMapping("/add")
    public String add(Integer CompanyID,Map<String, Object> model) {
        model.put("Mode", "Add");
        model.put("Data","{}");
        if(CompanyID==null)CompanyID=0;
        model.put("CompanyID",CompanyID);
        return "/system/loginUser/edit";
    }
    @RequestMapping("/edit")
    public String edit(Integer UserID,Map<String,Object> model) throws Exception{
        model.put("Mode","Edit");
        Optional<tbLoginUser> findUsers=userRep.findById(UserID);
        if(findUsers.isPresent()){
            tbLoginUser user=findUsers.get();
            model.put("Data",JSON.toJSONString(user));
            model.put("CompanyID",user.getCompanyId());
        } else throw new Exception("指定的用户资料不存在!");
        return "/system/loginUser/edit";
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
        List<TreeNode> Nodes = new ArrayList<>();
        List<tbRoleClass> roles = roleService.getAll();
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
    @RequestMapping("/getDepList")
    @ResponseBody
    public List<TreeNode> getDepList(Integer CompanyID) {
        List<TreeNode> Nodes = new ArrayList<>();
        if (CompanyID != null && CompanyID > 0) {
            List<tbDepList> Deps = depRep.findAllByCompanyId(CompanyID);
            for (int i = 0; i < Deps.size(); i++) {
                tbDepList dep = Deps.get(i);
                Integer CID = dep.getCompanyId();
                if (CID > 0) {
                    Optional<tbCompany> findCompany = companyRep.findById(CID);
                    if (findCompany.isPresent()) {
                        tbCompany company = findCompany.get();
                        String cName = company.getName();
                        Optional<TreeNode> findNodes =
                                Nodes.stream().filter(f -> f.getName().equals(cName)).findFirst();
                        String PID = "";
                        if (findNodes.isPresent() == false) {
                            TreeNode CNode = new TreeNode();
                            CNode.setName(cName);
                            CNode.setFID("C_" + Integer.toString(company.getId()));
                            CNode.setPID("0");
                            Nodes.add(CNode);
                            PID = CNode.getFID();
                        } else {
                            PID = findNodes.get().getFID();
                        }
                        TreeNode NNode = new TreeNode();
                        NNode.setPID(PID);
                        NNode.setFID(Integer.toString(dep.getDepId()));
                        NNode.setName(dep.getName());
                        Nodes.add(NNode);
                    }
                }
            }
        }
        return Nodes;
    }

    @ResponseBody
    @RequestMapping("/getData")
    public pageObject getData(HttpServletRequest request) {
        pageObject object = new pageObject();
        try {
            object = loginService.getData(request);
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
            loginService.save(login);
        } catch (Exception ax) {
            result.raiseException(ax);
        }
        return result;
    }
}
