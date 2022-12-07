package com.zhide.govwatch.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.aspose.words.Document;
import com.aspose.words.SaveFormat;
import com.zhide.govwatch.common.*;
import com.zhide.govwatch.define.IClientInfoService;
import com.zhide.govwatch.mapper.AllUserListMapper;
import com.zhide.govwatch.mapper.ClientInfoMapper;
import com.zhide.govwatch.model.*;
import com.zhide.govwatch.repository.PatentRepository;
import com.zhide.govwatch.repository.tbClientRepository;
import com.zhide.govwatch.repository.tbExcelTemplateRepository;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/clientInfo")
public class ClientInfoController {

    Logger logger = LoggerFactory.getLogger(ClientInfoController.class);
    @Autowired
    IClientInfoService clientInfoService;
    @Autowired
    tbClientRepository clientRepository;
    @Autowired
    ClientInfoMapper clientInfoMapper;
    @Autowired
    StringRedisTemplate redisUtil;
    @Autowired
    tbExcelTemplateRepository excelRep;
    @Autowired
    AllUserListMapper userListMapper;
    @Autowired
    PatentRepository patentRep;

    public static void getFile(byte[] bfile, String filePath, String fileName) {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            File dir = new File(filePath);
            if (!dir.exists()) {//判断文件目录是否存在
                dir.mkdirs();
            }
            file = new File(filePath + "\\" + fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bfile);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    @RequestMapping("/query")
    public String Query(boolean multiselect, Map<String, Object> model) {
        model.put("multiselect", multiselect ? "true" : "false");
        return "/work/clientInfo/query";
    }

    @RequestMapping("/queryAll")
    public String QueryAll(String KHID, Map<String, Object> model) {
        model.put("KHID", org.apache.commons.lang.StringUtils.isEmpty(KHID) ? "" : KHID);
        return "/system/client/queryAll";
    }

    @ResponseBody
    @RequestMapping("/getAll")
    public pageObject getAll(HttpServletRequest request) {
        Map<String, Object> param = getParams(request);
        pageObject result = new pageObject();
        List<Map<String, Object>> datas = clientInfoMapper.getDataHasEmail(param);
        int Total = 0;
        if (datas.size() > 0) {
            Total = Integer.parseInt(datas.get(0).get("_TotalNum").toString());
            datas.stream().forEach(f -> {
                f.remove("_TotalNum");
            });
        }
        result.setData(datas);
        result.setTotal(Total);
        return result;
    }

    @ResponseBody
    @RequestMapping("/getAllEmailUser")
    public pageObject getAllEmailUser(HttpServletRequest request) {
        Map<String, Object> param = getParams1(request);
        pageObject result = new pageObject();
        List<Map<String, Object>> datas = userListMapper.getAllEmailUser(param);
        int Total = 0;
        if (datas.size() > 0) {
            Total = Integer.parseInt(datas.get(0).get("_TotalNum").toString());
            datas.stream().forEach(f -> {
                f.remove("_TotalNum");
            });
        }
        result.setData(datas);
        result.setTotal(Total);
        return result;
    }

    @RequestMapping("/queryLinkMan")
    public String QueryLinkMan(boolean multiselect, Map<String, Object> model) {
        model.put("multiselect", multiselect ? "true" : "false");
        return "/work/clientInfo/queryLinkMan";
    }

    @RequestMapping("/index")
    public String Index(Map<String, Object> model) {
        model.put("Mode", "Add");
        return "/work/client/index";
    }

    @RequestMapping("/edit")
    public String Edit(int ClientID, Map<String, Object> model) {
        model.put("ClientID", Integer.toString(ClientID));
        model.put("Mode", "Edit");
        model.put("AllClientInfo", "{}");
        model.put("SignMan", "");
        model.put("SignManID", "");
        model.put("SignDate", "");
        model.put("Role", "Admin");
        Optional<tbClient> findtb = clientRepository.findById(ClientID);
        if (findtb.isPresent()) {
            model.put("LoadData", JSON.toJSONString(findtb.get()));
        } else model.put("LoadData", "{}");
        return "/work/client/edit";
    }

    @RequestMapping("/add")
    public String Add(String Type, Map<String, Object> model) {
        LoginUserInfo loginUserInfo = CompanyContext.get();
        Date dt = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        model.put("Mode", "Add");
        model.put("Type", Type);
        model.put("ClientID", 0);
        model.put("LoadData", "{}");
        model.put("Role", "Admin");
        model.put("SignMan", loginUserInfo.getUserName());
        model.put("SignManID", loginUserInfo.getUserId());
        model.put("SignDate", simpleDateFormat.format(dt));
//        List<Map<String, String>> ftb = clientInfoMapper.getAllClient();
        model.put("AllClientInfo", JSON.toJSONString(new ArrayList<>()));
        return "/work/client/edit";
    }

    @RequestMapping("/browse")
    public String Browse(int ClientID, String Role, String Type, Map<String, Object> model) {
        LoginUserInfo loginUserInfo = CompanyContext.get();
        Date dt = new Date();
        if (StringUtils.isEmpty(Role)) Role = "Admin";
        model.put("Role", Role);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        model.put("Type", Type);
        model.put("ClientID", Integer.toString(ClientID));
        model.put("Mode", "look");
        model.put("AllClientInfo", "{}");

        model.put("SignMan", loginUserInfo.getUserName());
        model.put("SignManID", loginUserInfo.getUserId());
        model.put("SignDate", simpleDateFormat.format(dt));
        Optional<tbClient> findtb = clientRepository.findById(ClientID);
        if (findtb.isPresent()) {
            model.put("LoadData", JSON.toJSONString(findtb.get()));
        } else model.put("LoadData", "{}");
        return "/work/client/edit";
    }

    @RequestMapping("/getPageData")
    @ResponseBody
    public pageObject getPageData(HttpServletRequest request) {
        pageObject result = new pageObject();
        try {
            result = clientInfoService.getPageData(request);
        } catch (Exception ax) {
            result.raiseException(ax);
        }
        return result;
    }

    @RequestMapping("/getData")
    @ResponseBody
    public pageObject getData(HttpServletRequest request) {
        pageObject result = new pageObject();
        try {
            result = clientInfoService.getData(request);
        } catch (Exception ax) {
            result.raiseException(ax);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/getAllLinksClient")
    public List<Map<String, Object>> getAllClientInfoExistLinkMan(HttpServletRequest request) {
        List<Map<String, Object>> rows = new ArrayList<>();
        try {
            rows = clientInfoService.getAllClientInfoExistLinkMan(request);
        } catch (Exception ax) {
            ax.printStackTrace();
        }
        return rows;
    }

    @RequestMapping("/getJSR")
    @ResponseBody
    public List<Map<String, Object>> getJSR(int ClientID) {
        LoginUserInfo Info = CompanyContext.get();
        return clientInfoService.getJSR(ClientID, Info.getUserId(), Info.getDepId(), Info.getRoleName());
    }

    @RequestMapping("/save")
    @ResponseBody
    public successResult save(HttpServletRequest request) {
        successResult result = new successResult();
        tbClient client = null;
        tbClientLinkers clientLinkers = null;
        ClientAndClientLinkers clientAndClientLinkers = null;
        try {
            String clients = request.getParameter("client");
            String clinetLinkers = request.getParameter("clientLinkers");
            String mode = request.getParameter("mode");
            if (Strings.isEmpty(clients) == false) {
                client = JSON.parseObject(clients, tbClient.class);
                clientLinkers = JSON.parseObject(clinetLinkers, tbClientLinkers.class);
                clientAndClientLinkers = clientInfoService.Save(client, clientLinkers, mode);
                result.setData(clientAndClientLinkers);

            } else throw new Exception("数据格式不正确！");
        } catch (Exception ax) {

            result.raiseException(ax);
        }
        return result;
    }

    @RequestMapping("/saveFollowRecord")
    @ResponseBody
    public successResult saveFollowRecord(HttpServletRequest request) {
        successResult result = new successResult();
        try {
            String Data = request.getParameter("Data");
            followRecord followRecord = JSON.parseObject(Data, followRecord.class);
            result.setData(clientInfoService.SaveFollowRecord(followRecord));
        } catch (Exception ax) {
            result.raiseException(ax);
        }
        return result;
    }

    @RequestMapping("/getFollowRecords")
    @ResponseBody
    public pageObject getFollowRecords(int ClientID, int pageIndex, int pageSize) {
        pageObject result = new pageObject();
        try {
            Page<followRecord> recordPage = clientInfoService.getFollowRecords(ClientID, pageIndex, pageSize);
            result.setData(recordPage.getContent());
            String x = Long.toString(recordPage.getTotalElements());
            result.setTotal(Integer.parseInt(x));
        } catch (Exception ax) {
            result.raiseException(ax);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/saveClientLinkers")
    public successResult SaveLinkers(HttpServletRequest request) {
        successResult result = new successResult();
        try {
            String Data = request.getParameter("Data");
            tbClientLinkers clientLinkers = JSON.parseObject(Data, tbClientLinkers.class);
            result.setData(clientInfoService.SaveLinkers(clientLinkers));
        } catch (Exception ax) {
            result.raiseException(ax);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/getClientLinkers")
    public pageObject getClientLinkers(int ClientID, int pageIndex, int pageSize) {
        pageObject result = new pageObject();
        try {
            Page<tbClientLinkers> recordPage = clientInfoService.getLinkers(ClientID, pageIndex, pageSize);
            result.setData(recordPage.getContent());
            String x = Long.toString(recordPage.getTotalElements());
            result.setTotal(Integer.parseInt(x));
        } catch (Exception ax) {
            result.raiseException(ax);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/getClientLinkersUpdateRecord")
    public pageObject getClientLinkersUpdateRecord(int ClientID, int pageIndex, int pageSize) {
        pageObject result = new pageObject();
        try {
            Page<tbLinkersUpdateRecord> recordPage = clientInfoService.getLinkersUpdateRecord(ClientID, pageIndex,
                    pageSize);
            result.setData(recordPage.getContent());
            String x = Long.toString(recordPage.getTotalElements());
            result.setTotal(Integer.parseInt(x));
        } catch (Exception ax) {
            result.raiseException(ax);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/GetLoginClientReords")
    public pageObject GetLoginClientReords(int ClientID, HttpServletRequest request) {
        pageObject result = new pageObject();
        try {
            result = clientInfoService.GetLoginClientReords(ClientID, request);
        } catch (Exception ax) {
            logger.info(ax.getMessage());
        }
        return result;
    }

    @RequestMapping("/downLoadDJFQD")
    @ResponseBody
    public successResult downLoadDJFQD(String data, String code, HttpServletResponse response) throws Exception {
        successResult res = new successResult();
        try {
            String decodeName = URLDecoder.decode(data, "UTF-8");
            Map<String, Object> O = JSON.parseObject(decodeName, new TypeReference<Map<String, Object>>() {
            });
            complexExcelBuilder exB = null;
            if (code.equals("OneYear")) {
                Optional<tbExcelTemplate> findOnes = excelRep.findFirstByCode(code);
                if (findOnes.isPresent()) {
                    tbExcelTemplate one = findOnes.get();
                    String X = one.getTemplatePath();
                    if (StringUtils.isEmpty(X) == true) {
                        exB = new complexExcelBuilder(code);
                    } else exB = new complexExcelBuilder(X);
                }
            }
            byte[] Bs = exB.getContent(O);
            File directory = new File("");
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String courseFile = CompanyPathUtils.getFullPath("Temp", "DJFQD");
            getFile(Bs, courseFile, "专利申请代缴费清单.xls");
            res.setMessage(courseFile);
            res.setSuccess(true);
        } catch (Exception ax) {
            response.getWriter().write(ax.getMessage());
            res.setSuccess(false);
        }
        return res;
    }

    @RequestMapping("/getRegisterPath")
    @ResponseBody
    public successResult getRegisterPath(HttpServletRequest request, int ClientID, String OrgCode) {
        String courseFile = "";
        successResult res = new successResult();
        try {
            LoginUserInfo Info = CompanyContext.get();
            Map<String, Object> OK = new HashMap<>();
            String RKey = UUID.randomUUID().toString();
            OK.put("ClientID", ClientID);
            OK.put("OrgCode", OrgCode);
            OK.put("CompanyID", Info.getCompanyId());
            redisUtil.opsForValue().set(RKey, JSON.toJSONString(OK), Duration.ofDays(1));
            courseFile = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() +
                    "/resetPassword/index?Key=" + RKey;
            res.setMessage(courseFile);
            res.setSuccess(true);
        } catch (Exception ax) {
            res.setSuccess(false);
        }
        return res;
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    @ResponseBody
    public successResult remove(String IDS) {
        successResult result = new successResult();
        try {
            List<String> ids = JSON.parseArray(IDS, String.class);
            clientInfoService.remove(ids);
        } catch (Exception ax) {
            result.raiseException(ax);
        }
        return result;
    }

    @RequestMapping("/exportExcel")
    public void exportExcel(HttpServletRequest request, HttpServletResponse response) {
        try {
            String VX = request.getParameter("Data");
            List<IExcelExportTemplate> Rows = JSON.parseArray(VX, ClientInfoExcelTemplate.class)
                    .stream().map(f -> (IExcelExportTemplate) f).collect(Collectors.toList());
            if (Rows.size() > 0) {
                ExcelFileBuilder builder = new ExcelFileBuilder(Rows);
                byte[] datas = builder.export();
                String fileName = builder.getCurrentFileName();
                WebFileUtils.download(fileName, datas, response);
            } else response.getWriter().write("<script>alert('没有数据可以导出。');</script>");
        } catch (Exception ax) {
            try {
                response.getWriter().write("<script>alert('导出Excel失败:" + ax.getMessage() + "');</script>");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @RequestMapping("/findNameByName")
    @ResponseBody
    public successResult findNameByName(HttpServletRequest request) {
        successResult result = new successResult();
        tbClient tc = new tbClient();
        try {
            String Data = request.getParameter("Data");
            if (Strings.isEmpty(Data) == false) {
                tbClient client = JSON.parseObject(Data, tbClient.class);
                tc = clientInfoService.findNameByName(client.getName());
                if (tc == null) {
                    result.setData("");
                } else {
                    result.setData(tc.getName());
                }
            }
        } catch (Exception ax) {
            result.raiseException(ax);
            result.setSuccess(false);
        }
        return result;
    }

    @RequestMapping("/getGridDataExcel")
    @ResponseBody
    public successResult GetGridDataExcel(HttpServletRequest request, HttpServletResponse response) {
        successResult res = new successResult();
        try {
            String Data = request.getParameter("data");
            String Colums = request.getParameter("columns");
            String FileName = request.getParameter("filename");

            try {
                List<Map<String, Object>> datas = JSON.parseObject(Data,
                        new TypeReference<List<Map<String, Object>>>() {
                        });
                List<gridColumnInfo> cols = JSON.parseArray(Colums, gridColumnInfo.class);
                ExcelBuilder bb = new ExcelBuilder(cols, datas);
                byte[] Bx = bb.create();
                String Path = uploadExcel(Bx, CompanyPathUtils.getFullPath("Temp"), FileName);
                res.setMessage(Path);
                res.setSuccess(true);
            } catch (Exception ax) {
                response.getWriter().write(ax.getMessage());
            }
        } catch (Exception ax) {
            try {
                response.getWriter().write("<script>alert('导出Excel失败:" + ax.getMessage() + "');</script>");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return res;
    }

    private String uploadExcel(byte[] bfile, String filePath, String fileName) {
        BufferedOutputStream bufferedOutputStream = null;
        FileOutputStream fileOutputStream = null;
        File file = null;
        try {
            File dir = new File(filePath);
            if (!dir.exists() && dir.isDirectory()) {
                dir.mkdirs();
            }
            file = new File(filePath + "\\" + fileName);
            fileOutputStream = new FileOutputStream(file);
            bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            bufferedOutputStream.write(bfile);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bufferedOutputStream != null) {
                try {
                    bufferedOutputStream.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return filePath + "\\" + fileName;
    }

    @ResponseBody
    @RequestMapping("/saveImageFollow")
    public successResult SaveImageFollowRecord(String Data) {
        successResult result = new successResult();
        try {
            followRecord record = JSON.parseObject(Data, followRecord.class);
            clientInfoService.SaveImageFollowRecord(record);
        } catch (Exception ax) {
            result.raiseException(ax);
        }
        return result;
    }

    @RequestMapping("/ImportClientData")
    public String ImportClientData(String Code, String FileName, Map<String, Object> model) {
        StringBuilder sb = new StringBuilder();
        tbClient client;
        List<Map<String, String>> list = clientInfoMapper.getAllClient();
        List<String> KS = list.stream().map(f -> f.get("Name").toString()).collect(Collectors.toList());
        model.put("Code", Code);
        model.put("FileName", FileName);
        model.put("AllClient", org.apache.commons.lang.StringUtils.join(KS, ","));
        return "/work/client/ImportClientData";
    }

    private Map<String, Object> getParams(HttpServletRequest request) {
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        int pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
        String sortOrder = request.getParameter("sortOrder");
        if (sortOrder.isEmpty()) sortOrder = "Desc";
        String sortField = request.getParameter("sortField");
        if (sortField.isEmpty()) sortField = "ClientName";
        String Name = request.getParameter("Name");
        Map<String, Object> params = new HashMap<>();
        params.put("Begin", pageIndex * pageSize + 1);
        params.put("End", (pageIndex + 1) * pageSize);
        params.put("sortOrder", sortOrder);
        params.put("sortField", sortField);
        if (Strings.isEmpty(Name) == false) {
            params.put("Name", "%" + Name + "%");
        }
        LoginUserInfo Info = CompanyContext.get();
        if (Info != null) {
            params.put("DepID", Info.getDepId());
            params.put("RoleName", Info.getRoleName());
            params.put("UserID", Info.getUserId());
        } else throw new RuntimeException("登录信息失效，请重新登录！");


        String KHID = request.getParameter("KHID");
        if (org.apache.commons.lang.StringUtils.isEmpty(KHID) == false) {
            List<String> KHIDS = Arrays.asList(KHID.split(",")).stream().collect(Collectors.toList());
            if (KHIDS.size() > 0) {
                params.put("KHID", KHIDS);
                params.replace("RoleName", "系统管理员");
            }
        }
        return params;
    }

    private Map<String, Object> getParams1(HttpServletRequest request) {
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        int pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
        String sortOrder = request.getParameter("sortOrder");
        if (sortOrder.isEmpty()) sortOrder = "Desc";
        String sortField = request.getParameter("sortField");
        if (sortField.isEmpty()) sortField = "UserName";
        String Name = request.getParameter("Name");
        Map<String, Object> params = new HashMap<>();
        params.put("Begin", pageIndex * pageSize + 1);
        params.put("End", (pageIndex + 1) * pageSize);
        params.put("sortOrder", sortOrder);
        params.put("sortField", sortField);
        if (Strings.isEmpty(Name) == false) {
            params.put("UserName", "%" + Name + "%");
        }
        LoginUserInfo Info = CompanyContext.get();
        if (Info == null) throw new RuntimeException("登录信息失效，请重新登录！");
        return params;
    }

    //clientRepository
    @RequestMapping("/ClientCmb")
    public List<tbClient> ClientCmb(HttpServletRequest request) {
        List<tbClient> items = new ArrayList<>();
        List<tbClient> Cs = clientRepository.findtbClentall(request.getParameter("Name"));
        for (tbClient tb : Cs) {
            tbClient item = new tbClient();
            item.setClientId(tb.getClientId());
            item.setName(tb.getName());
            items.add(item);
        }
        return items;
    }

    private String getTemplateByCode(String code) throws Exception {
        String BaseDir = GlobalContext.getStaticUrl() + "\\template\\";
        return StringUtilTool.readAll(BaseDir + code + ".ftl");
    }

    private String createXml(String code, Object obj) throws Exception {
        String xmlTemplate = getTemplateByCode(code);
        String xmlText = StringUtilTool.createByTemplate(xmlTemplate, obj);
        return xmlText;
    }

    @RequestMapping("/exportPatentRecord")
    public void ExportPatentRecord(int ClientID, HttpServletRequest request, HttpServletResponse response) {
        companyPatentRecord record = new companyPatentRecord();
        try {
            String FileName = "企业知识产权档案表.docx";
            Optional<tbClient> findClients = clientRepository.findById(ClientID);
            if (findClients.isPresent()) {
                tbClient client = findClients.get();
                record.setCompanyName(client.getName());
                record.setAddress(client.getAddress());
                record.setLinkMan(client.getLinkMan());
                record.setLinkPhone(client.getLinkPhone());
                List<patent> Ps = patentRep.findAllByClientId(ClientID);
                if (Ps.size() > 0) {
                    record.setItems(Ps);
                    record.setPatentNum(Ps.size());
                    String xmlText = createXml("PatentRecord", record);
                    byte[] BB = null;
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    try {
                        Document doc = new Document(new ByteArrayInputStream(xmlText.getBytes(StandardCharsets.UTF_8)));
                        doc.save(byteArrayOutputStream, SaveFormat.DOCX);
                        BB = byteArrayOutputStream.toByteArray();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            byteArrayOutputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    response.setContentType("application/msword");
                    response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(FileName,
                            "UTF-8"));
                    OutputStream out = response.getOutputStream();
                    out.write(BB);
                    out.flush();
                } else response.getWriter().write(client.getName() + "没有专利信息无法生成档案!");
            } else response.getWriter().write("客户信息不存在!");
        } catch (Exception ax) {
            ax.printStackTrace();
        }
    }
}
