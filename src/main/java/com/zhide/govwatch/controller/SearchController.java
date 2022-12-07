package com.zhide.govwatch.controller;

import com.alibaba.fastjson.JSON;
import com.zhide.govwatch.common.CompanyContext;
import com.zhide.govwatch.common.PageableUtils;
import com.zhide.govwatch.common.pageObject;
import com.zhide.govwatch.common.successResult;
import com.zhide.govwatch.define.ISearchPatentService;
import com.zhide.govwatch.model.*;
import com.zhide.govwatch.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller
@RequestMapping("/searchResult")
public class SearchController {
    @Autowired
    ISearchPatentService searchService;
    @Autowired
    patentInfoPermissionRepository patentInfoPermissionRepository;
    @Autowired
    PatentRepository patentRepository;
    @Autowired
    tbGovFeeRepository govFeeRepository;
    @Autowired
    tbgovpayRepository govpayRepository;
    @Autowired
    StringRedisTemplate redisTemplate;
    @Autowired
    smtpAccountRepository smtpAccountRepository;
    @Autowired
    MongoTemplate mongoTemplate;

    Logger logger= LoggerFactory.getLogger(SearchController.class);
    @RequestMapping("/index")
    public String Index(String word, String searchPage, Map<String,Object> model,HttpServletRequest request) throws Exception{
        if(searchPage==null)searchPage="";
        if(word==null)word="";
        LoginUserInfo Info= CompanyContext.get();
        request.setCharacterEncoding("gb2312");
        String strFinal = request.getParameter("strFinal");
        model.put("word",word);
        model.put("username",Info.getUserName());
        model.put("HasLogin", 1);
        model.put("roleName",Info.getRoleName());
        model.put("Final",strFinal == null?"":strFinal);
        model.put("SearchPage",searchPage);
        return "Searchresults";
    }
    @RequestMapping("/tabsearchindex")
    public String TabSearchIndex(String searchPage, Map<String,Object> model,HttpServletRequest request) throws Exception{
        LoginUserInfo Info= CompanyContext.get();
        request.setCharacterEncoding("gb2312");
        String strFinal = request.getParameter("strFinal");
        model.put("word","");
        model.put("HasLogin", 1);
        model.put("roleName",Info.getRoleName());
        model.put("Final",strFinal == null?"":strFinal);
        model.put("SearchPage",searchPage);
        return "Searchresults";
    }

    @RequestMapping("/menu")
    public String menu(String word, String searchPage, Map<String,Object> model,HttpServletRequest request) throws Exception{
        LoginUserInfo Info= CompanyContext.get();
        String strFinal = request.getParameter("strFinal");
        model.put("word",word);
        model.put("HasLogin", 1);
        model.put("roleName",Info.getRoleName());
        model.put("username",Info.getUserName());
        return "Headermenu";
    }

    @RequestMapping("/Patentdetails")
    public String Patentdetails(HttpServletRequest request,Map<String,Object> model) throws Exception{
            patent findAllByShenqingh = patentRepository.findAllByShenqingh(request.getParameter("SHENQINGH"));
            model.put("datas", findAllByShenqingh);
            return "Patentdetails";
    }
    @RequestMapping("/Patentcomparison")
    public String Patentcomparison(HttpServletRequest request,Map<String,Object> model) throws Exception{
        List<patent>findByShenqinghInnumber= patentRepository.findByShenqinghInnumber(request.getParameter("number1"),request.getParameter("number2"));
        model.put("datas", findByShenqinghInnumber);
        return "Patentcomparison";
    }
    @RequestMapping("/query")
    @ResponseBody
    //简单查询
    public pageObject Query(String words, HttpServletRequest request){
        pageObject object=new pageObject();
        try {
            Pageable pageable= PageableUtils.create(request);
            object= searchService.simpleSearch("",words,pageable);
            object.setSuccess(true);
        }
        catch(Exception ax){
            object.raiseException(ax);
        }
        return object;
    }
    @RequestMapping("/search")
    @ResponseBody
    //条件组合查询
    public pageObject Search(String words,HttpServletRequest request){
        pageObject object=new pageObject();
        try {
            Pageable pageable= PageableUtils.create(request);
            object= searchService.complexSearch(words,pageable);
            object.setSuccess(true);
        }
        catch(Exception ax){
            object.raiseException(ax);
        }
        return object;
    }
    @RequestMapping("/getHistory")
    @ResponseBody
    public successResult getHistory(){
        successResult result=new successResult();
        try {
            LoginUserInfo Info = CompanyContext.get();
            String code = Info.getAccount();
            String Key = "search::" + code + "::history";
            List<String> Res = redisTemplate.opsForList().range(Key, 0,20);
            result.setData(Res);
        }catch(Exception ax){
            result.raiseException(ax);
        }
        return result;
    }
    @RequestMapping("/indexs")
    public String indexs(){
        return "/Annualfee/index";
    }
    @RequestMapping("/AnnualfeeGaikuang")
    public String AnnualfeeGaikuang(Map<String,Object> model) throws Exception{
        LoginUserInfo Info = CompanyContext.get();
        List<patentInfoPermission> listPatentInfoPermission = patentInfoPermissionRepository.findAllByUserid(Info.getUserId());
        if (listPatentInfoPermission.size() > 0) {
            model.put("PatentInfoPermissionCount", listPatentInfoPermission.size());
        }else model.put("PatentInfoPermissionCount", 0);
        List<String> listShenqingh = new ArrayList<>();
        listPatentInfoPermission.stream().forEach(f -> {
            listShenqingh.add(f.getShenqingh());
        });

        List<patent> listPatent = patentRepository.findAllByShenqinghIn(listShenqingh);
        Long FMCount = listPatent.stream().filter(f -> f.getShenqinglx().equals("发明")).count();
        Long SYCount = listPatent.stream().filter(f -> f.getShenqinglx().equals("实用")).count();
        Long WGCount = listPatent.stream().filter(f -> f.getShenqinglx().equals("外观")).count();
        model.put("FMCount",FMCount);
        model.put("SYCount",SYCount);
        model.put("WGCount",WGCount);
        int GovFeeCount = 0;
        List<tbGovFee> listGovFee= govFeeRepository.findAllByAppNoIn(listShenqingh);
        if (listGovFee.size() > 0) {
            for (int i = 0; i < listGovFee.size(); i++) {
                GovFeeCount += listGovFee.get(i).getAmount();
            }
        }
        model.put("GovFeeCount",GovFeeCount);
        Long ZNJ = listGovFee.stream().filter(f -> f.getCostName().equals("滞纳金")).count();
        model.put("ZNJ",ZNJ);

        List<String> listjiaona = GetDateDiff(listPatentInfoPermission);
        long nine = listjiaona.stream().filter(f -> f.equals("nine")).count();
        long six = listjiaona.stream().filter(f -> f.equals("six")).count();
        long three = listjiaona.stream().filter(f -> f.equals("three")).count();
        long fifty = listjiaona.stream().filter(f -> f.equals("fifty")).count();
        model.put("nine",nine);
        model.put("six",six);
        model.put("three",three);
        model.put("fifty",fifty);
        return "/Annualfee/AnnualfeeGaikuang";
    }

    private List<String> GetDateDiff(List<patentInfoPermission> listPatentInfoPermission){
        List<String> list = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        List<tbGovFee> listGovFees = govFeeRepository.findAll();
        listPatentInfoPermission.stream().forEach(f ->{
            Optional<tbGovFee> findOne = listGovFees.stream().filter(x -> x.getAppNo().equals(f.getShenqingh())).filter(x -> x.getPayState().equals("未缴费")).findFirst();
            if (findOne.isPresent()){
                try {
                    Date date1 = format.parse(findOne.get().getLimitDate().toString());
                    String newDate = checkDate(new Date().toString());
                    Date date2 = format.parse(newDate);
                    int DateDiff = (int) ((date1.getTime() - date2.getTime()) / (1000 * 3600 * 24));
                    if (DateDiff <= 15){
                        list.add("fifty");
                    }else if(DateDiff <= 30){
                        list.add("three");
                    }else if(DateDiff <= 60){
                        list.add("six");
                    }else if(DateDiff <= 90){
                        list.add("nine");
                    }
                }catch (ParseException ax){
                    ax.printStackTrace();
                }
            }
        });
        return list;
    }

    private static String checkDate(String str){
        String format1 = null;
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date(str);
            format1 = format.format(date);
        } catch (Exception e) {

        }
        return format1;
    }
    @RequestMapping("/Addpatent")
    public String Addpatent(Map<String,Object> model){
        return "/Annualfee/Addpatent";
    }
    @RequestMapping("/AnnualfeeMonitorlist")
    public String AnnualfeeMonitorlist(){
        return "/Annualfee/AnnualfeeMonitorlist";
    }
    @RequestMapping("/Annualfeereminder")
    public String Annualfeereminder(){
        return "/Annualfee/Annualfeereminder";
    }
    @RequestMapping("/Mailboxbinding")
    public String Mailboxbinding(Map<String, Object> model){
        LoginUserInfo loginUserInfo = CompanyContext.get();
        Optional<smtpAccount> findtb = smtpAccountRepository.getAllByUserId(loginUserInfo.getUserId());
        if (findtb.isPresent()) {
            model.put("LoadData", JSON.toJSONString(findtb.get()));
        } else model.put("LoadData", "{}");
        return "/Annualfee/Mailboxbinding";
    }
    @RequestMapping("/Paymentinformation")
    public String Paymentinformation(String shenqingh,Map<String,Object> model){
        List<tbGovFee> listFee = govFeeRepository.findAllByAppNo(shenqingh);
        List<tbgovpay> listPay = govpayRepository.findAllByAppNo(shenqingh);
        List<tbgovpay> listYJ = new ArrayList<>();
        List<tbGovFee> listWJ = new ArrayList<>();
        if (listPay.size() > 0) {
            listPay.stream().forEach(x -> {
                listYJ.add(x);
            });
        }
        if (listFee.size() > 0) {
            listFee.stream().filter(f -> f.getPayState().equals("未缴费")).sorted(Comparator.comparing(tbGovFee::getLimitDate)).forEach(x -> {
                listWJ.add(x);
            });
        }
        model.put("YJS",listYJ);
        model.put("WJS",listWJ);
        return "/Annualfee/Paymentinformation";
    }
    @RequestMapping("/Tableretrieval")
    public String Tableretrieval() {
        return "/Annualfee/Tableretrieval";
    }

    @RequestMapping("/parametersearchcount")
    @ResponseBody
    public successResult ParameterSearchCount(HttpServletRequest request){
        successResult result = new successResult();
        try {
            String Field = request.getParameter("Field");
            String words = request.getParameter("words");
            String SearchPage = request.getParameter("searchPage");
            List<String> list = searchService.ParameterSearchCount(Field, words,SearchPage);
            result.setData(list);
        }catch (Exception ax){
            result.raiseException(ax);
        }
        return result;
    }

    @RequestMapping("/AnHome")
    public String AnHome() {
        return "/Annualfee/AnHome";
    }
    @RequestMapping("/Annmonitoring")
    public String Annmonitoring() { return "/Annualfee/Annmonitoring"; }
    @RequestMapping("/Appfeemonitoring")
    public String Appfeemonitoring() {return "/Annualfee/Appfeemonitoring";
    }
    @RequestMapping("/Cooperativecustomers")
    public String Cooperativecustomers() { return "/Annualfee/Cooperativecustomers";
    }
    @RequestMapping("/Intendedcustomer")
    public String Intendedcustomer() { return "/Annualfee/Intendedcustomer"; }
    @RequestMapping("/Paidlist")
    public String Paidlist() {
        return "/Annualfee/Paidlist";
    }
    @RequestMapping("/Patentcominformation")
    public String Patentcominformation() {
        return "/Annualfee/Patentcominformation";
    }
    @RequestMapping("/Pendingpayment")
    public String Pendingpayment() {
        return "/Annualfee/Pendingpayment";
    }
    @RequestMapping("/workBench")
    public String workBench() {
        return "/Annualfee/workBench";
    }

    @RequestMapping("/getDistinctLAWSTATUS")
    @ResponseBody
    public List<ComboboxItem> GetDistinctLAWSTATUS(){
        List<ComboboxItem> comboboxItems = searchService.GetDistinctLAWSTATUS();
        return comboboxItems;
    }

    @RequestMapping("/getDistinctSECLAWSTATUS")
    @ResponseBody
    public List<ComboboxItem> GetDistinctSECLAWSTATUS(){ return searchService.GetDistinctSECLAWSTATUS(); }
}
