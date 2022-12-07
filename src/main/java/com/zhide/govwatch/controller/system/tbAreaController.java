package com.zhide.govwatch.controller.system;

import com.zhide.govwatch.common.IntegerUtils;
import com.zhide.govwatch.define.IAreaService;
import com.zhide.govwatch.model.tbarea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @ClassName: tbAreaController
 * @Author: 肖新民
 * @*TODO:
 * @CreateTime: 2022年02月18日 16:43
 **/
@Controller
@RequestMapping("/area")
public class tbAreaController {
    @Autowired
    IAreaService areaService;

    @RequestMapping("/index")
    public String Index() {
        return "/system/area/index";
    }

    @RequestMapping("/getData")
    @ResponseBody
    public List<tbarea> getData() {
        return areaService.getAll();
    }

    @RequestMapping("/getProvinces")
    @ResponseBody
    public List<tbarea> getProvinces() {
        return areaService.getProvinces();
    }

    @RequestMapping("/getCitys")
    @ResponseBody
    public List<tbarea> getCitys(String IDS) {
        List<Integer> DDS = IntegerUtils.parseIntArray(IDS);
        return areaService.getCitys(DDS);
    }

    @RequestMapping("/getCountys")
    @ResponseBody
    public List<tbarea> getCountys(String IDS) {
        List<Integer> DDS = IntegerUtils.parseIntArray(IDS);
        List<tbarea> datas = areaService.getCoutys(DDS);
        return datas;
    }
}
