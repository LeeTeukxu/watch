package com.zhide.govwatch.controller.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.zhide.govwatch.common.ExcelBuilder;
import com.zhide.govwatch.common.WebFileUtils;
import com.zhide.govwatch.common.complexExcelBuilder;
import com.zhide.govwatch.define.ItbGovFeeService;
import com.zhide.govwatch.model.gridColumnInfo;
import com.zhide.govwatch.model.tbExcelTemplate;
import com.zhide.govwatch.model.tbGovFee;
import com.zhide.govwatch.repository.tbExcelTemplateRepository;
import com.zhide.govwatch.repository.tbGovFeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
@RequestMapping("/excel")
public class ExcelDownloadController {
    @Autowired
    tbGovFeeRepository govFeeRepository;

    @RequestMapping("/download")
    public void DownLoad(String data, String columns, String filename, HttpServletResponse response) throws Exception {
        try {
            List<Map<String,Object>> datas = JSON.parseObject(data, new TypeReference<List<Map<String,Object>>>(){
            });
            List<gridColumnInfo> cols =JSON.parseArray(columns, gridColumnInfo.class);
            ExcelBuilder bb = new ExcelBuilder(cols, datas);
            byte[] Bx = bb.create();
            WebFileUtils.download(filename, Bx, response);
        } catch (Exception ax) {
            response.getWriter().write(ax.getMessage());
        }
    }

    @Autowired
    tbExcelTemplateRepository excelRep;
    @Autowired
    ItbGovFeeService itbGovFeeService;

    @RequestMapping("/download1")
    public void Download1(String data, String filename, String code, String ConsType, HttpServletResponse response,
            HttpServletRequest request) throws Exception {
        try {
            Map<String, Object> O = JSON.parseObject(data, new TypeReference<Map<String, Object>>() {
            });
            complexExcelBuilder exB = null;
            String numberCell = request.getParameter("numberCell");
            String sheetName = request.getParameter("sheetName");
            String autoCreateNew = request.getParameter("autoCreateNew");
            Optional<tbExcelTemplate> findOnes = excelRep.findFirstByCode(code);
            if(findOnes.isPresent()) {
                tbExcelTemplate one=findOnes.get();
                String X=one.getTemplatePath();
                if(StringUtils.isEmpty(X)==true) {
                    exB = new complexExcelBuilder(code, sheetName);
                } else exB = new complexExcelBuilder(X, sheetName);
            } else {
                exB = new complexExcelBuilder(code, sheetName);
            }
            if (exB != null) {
                exB.setSheetName(sheetName);
                exB.setNumberCell(numberCell);
                exB.setAutoCreateNew(Boolean.parseBoolean(autoCreateNew));
            }
            Map<String, List<Map<String, Object>>> cache = getDataCache(O);
            byte[] Bs = exB.getContentSender(O, ConsType, cache);
            WebFileUtils.download(filename, Bs, response);
        } catch (Exception ax) {
            response.getWriter().write(ax.getMessage());
        }
    }

    private Map<String, List<Map<String, Object>>> getDataCache(Map<String, Object> content) {
        Map<String, List<Map<String, Object>>> res = new HashMap<>();
        if (content.containsKey("Rows")) {
            String rowText = content.get("Rows").toString();
            TypeReference<List<Map<String, Object>>> O = new TypeReference<List<Map<String, Object>>>() {
            };
            List<Map<String, Object>> rows = JSON.parseObject(rowText, O);
            for (int i = 0; i < rows.size(); i++) {
                Map<String, Object> row = rows.get(i);
                String AppNo = row.get("SHENQINGH").toString();
                if (res.containsKey(AppNo) == false) {
                    List<Map<String, Object>> OX = getGovFeeByAppNo(AppNo);
                    res.put(AppNo, OX);
                }
            }
        }
        return res;
    }

    public List<Map<String, Object>> getGovFeeByAppNo(String AppNo) {
        List<tbGovFee> listGovFee = itbGovFeeService.getGovFeeByAppNoToMap(AppNo);
        List<Map<String, Object>> listMap = new ArrayList<>();
        listGovFee.stream().filter(f -> f.getPayState().equals("未缴费")).forEach(f -> {
            Map<String, Object> map = new HashMap<>();
            map.put("COSTNAME", f.getCostName());
            map.put("MONEY", f.getAmount());
            map.put("PAYSTATE", f.getPayState());
            map.put("JIAOFEIR", f.getLimitDate());
            listMap.add(map);
        });
        return listMap;
    }
}
