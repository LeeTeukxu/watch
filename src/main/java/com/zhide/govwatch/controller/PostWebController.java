package com.zhide.govwatch.controller;

import com.alibaba.fastjson.JSON;
import com.zhide.govwatch.common.successResult;
import com.zhide.govwatch.model.GetMessageObject;
import com.zhide.govwatch.model.machine;
import com.zhide.govwatch.model.patentupdate;
import com.zhide.govwatch.repository.machineRepository;
import com.zhide.govwatch.repository.patentupdateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Optional;

/**
 * @ClassName: PostWebController
 * @Author: 肖新民
 * @*TODO:
 * @CreateTime: 2022年07月28日 14:58
 **/
@RequestMapping("/PostWeb")
@RestController
public class PostWebController {
    @Autowired
    patentupdateRepository puRep;
    @Autowired
    machineRepository machineRep;

    @RequestMapping("/postGetResult")
    @ResponseBody
    @Transactional
    public successResult postGetResult(String Data) {
        successResult result = new successResult();
        try {
            GetMessageObject obj = JSON.parseObject(Data, GetMessageObject.class);
            String shenqingh = obj.getShenqingh();
            String no = obj.getNo();
            Optional<patentupdate> findOnes = puRep.findFirstByShenqinghAndNo(shenqingh, no);
            if (findOnes.isPresent()) {
                patentupdate up = findOnes.get();
                up.setPUpdate(obj.getPatentUpdate() == true ? 1 : 0);
                up.setPUpdateTime(obj.getPatentUpdateTime());
                up.setGUpdate(obj.getGovFeeUpdate() == true ? 1 : 0);
                up.setGUpdateTime(obj.getGovFeeUpdateTime());
                puRep.save(up);
            }
        } catch (Exception ax) {
            result.raiseException(ax);
        }
        return result;
    }


    @RequestMapping("/addMachine")
    @ResponseBody
    @Transactional
    public successResult addMachine(HttpServletRequest request, String Data) {
        successResult result = new successResult();
        try {
            machine m = JSON.parseObject(Data, machine.class);
            m.setIp(request.getRemoteAddr());
            if (m.getCreateTime() == null) m.setCreateTime(new Date());
            machineRep.save(m);
        } catch (Exception ax) {
            result.raiseException(ax);
        }
        return result;
    }
}
