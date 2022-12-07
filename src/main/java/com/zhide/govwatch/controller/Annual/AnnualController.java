package com.zhide.govwatch.controller.Annual;

import com.zhide.govwatch.common.PageableUtils;
import com.zhide.govwatch.common.pageObject;
import com.zhide.govwatch.common.successResult;
import com.zhide.govwatch.define.IPatentPermissionService;
import com.zhide.govwatch.define.ISearService;
import com.zhide.govwatch.define.ISearchPatentService;
import com.zhide.govwatch.define.ItbGovFeeService;
import com.zhide.govwatch.model.patentElInfo;
import com.zhide.govwatch.repository.patentElInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/Annual")
public class AnnualController {
    @Autowired
    IPatentPermissionService iPatentPermissionService;

    @Autowired
    ItbGovFeeService itbGovFeeService;

    @Autowired
    ISearService iSearService;

    @Autowired
    ISearchPatentService searchService;

    @Autowired
    patentElInfoRepository patentElInfoRepository;

    @RequestMapping("/getTopMonitor")
    @ResponseBody
    public pageObject GetTopMonitor(HttpServletRequest request){
        pageObject result = new pageObject();
        try {
            result = iSearService.GetTopMonitor(request);
        }catch (Exception ax){
            result.raiseException(ax);
        }
        return result;
    }

    @RequestMapping("/getMonitor")
    @ResponseBody
    public pageObject GetMonitor(HttpServletRequest request){
        pageObject result = new pageObject();
        try {
            result = iSearService.GetAllMonitor(request);
        }catch (Exception ax){
            result.raiseException(ax);
        }
        return result;
    }

    @RequestMapping("/addannual")
    @ResponseBody
    public successResult AddAnnual(@RequestBody List<String> ids){
        successResult result = new successResult();
        try {
            iPatentPermissionService.save(ids);
        }catch (Exception ax){
            result.raiseException(ax);
        }
        return result;
    }

    @RequestMapping("/remove")
    @ResponseBody
    public successResult Remove(@RequestBody List<String> shenqinghs){
        successResult result = new successResult();
        try {
            iPatentPermissionService.remove(shenqinghs);
        }catch (Exception ax){
            result.raiseException(ax);
        }
        return result;
    }

    @RequestMapping("/query")
    @ResponseBody
    public pageObject Query(HttpServletRequest request){
        pageObject object=new pageObject();
        try {
            Pageable pageable= PageableUtils.create(request);
            String Filelds = request.getParameter("Filelds");
            String word = request.getParameter("word");
            object= searchService.simpleAnnualSearch(Filelds,word,pageable);
            object.setSuccess(true);
        }
        catch(Exception ax){
            object.raiseException(ax);
        }
        return object;
    }

    @RequestMapping("/getByShenqinghsIn")
    @ResponseBody
    public pageObject getByShenqinghsIn( HttpServletRequest request) {
        pageObject result = new pageObject();
        try {
            result = iSearService.GetByShenqinghsIn(request);
        } catch (Exception ax) {
            result.raiseException(ax);
            ax.printStackTrace();
        }
        return result;
    }



}
