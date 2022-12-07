package com.zhide.govwatch.controller.permission;

import com.alibaba.fastjson.JSON;
import com.zhide.govwatch.common.successResult;
import com.zhide.govwatch.define.IPatentAreaService;
import com.zhide.govwatch.model.patentarea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @ClassName: PatentPermissionController
 * @Author: 肖新民
 * @*TODO:
 * @CreateTime: 2022年02月19日 16:20
 **/
@RequestMapping("/patentPermission")
@Controller
public class PatentPermissionController {
    @Autowired
    IPatentAreaService areaService;

    @RequestMapping("/index")
    public String Index() {
        return "/permission/patentPermission/index";
    }
    @ResponseBody
    @RequestMapping("/saveAll")
    public successResult Save(String Data) {
        successResult result = new successResult();
        try {
            List<patentarea> areas = JSON.parseArray(Data, patentarea.class);
            areaService.saveAll(areas);
        } catch (Exception ax) {

        }
        return result;
    }
    @ResponseBody
    @RequestMapping("/getData")
    public List<patentarea> getData(){
            return areaService.getData();
    }
}
