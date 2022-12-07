package com.zhide.govwatch.controller;

import com.zhide.govwatch.common.pageObject;
import com.zhide.govwatch.define.IWorkBenchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: WorkBenchController
 * @Author: 肖新民
 * @*TODO:
 * @CreateTime: 2022年02月28日 10:19
 **/
@Controller
@RequestMapping("/workBench")
public class WorkBenchController {

    @Autowired
    IWorkBenchService workService;
    @ResponseBody
    @RequestMapping("getAddFee")
    public pageObject GetAddFee(HttpServletRequest request) {
        pageObject result = new pageObject();
        try {
            List<Map<String,Object>> datas  = workService.getAddFee(request);
            int total = 0;
            if (datas.size() > 0) {
                total = Integer.parseInt(datas.get(0).get("_TotalNum").toString());
            }
            result.setData(datas);
            result.setTotal(total);
        } catch (Exception ax) {
            result.raiseException(ax);
        }
        return result;
    }
    @ResponseBody
    @RequestMapping("getPatent")
    public pageObject GetPantent(HttpServletRequest request) {
        pageObject result = new pageObject();
        try {
            List<Map<String,Object>> datas  = workService.getPatent(request);
            int total = 0;
            if (datas.size() > 0) {
                total = Integer.parseInt(datas.get(0).get("_TotalNum").toString());
            }
            result.setData(datas);
            result.setTotal(total);
        } catch (Exception ax) {
            result.raiseException(ax);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("getRecentFee")
    public pageObject getRecentFee(HttpServletRequest request) {
        pageObject result = new pageObject();
        try {
            List<Map<String,Object>> datas  = workService.getRecentFee(request);
            int total = 0;
            if (datas.size() > 0) {
                total = Integer.parseInt(datas.get(0).get("_TotalNum").toString());
            }
            result.setData(datas);
            result.setTotal(total);
        } catch (Exception ax) {
            result.raiseException(ax);
        }
        return result;
    }
}
