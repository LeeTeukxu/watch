package com.zhide.govwatch.common;

import com.alibaba.fastjson.JSON;
import com.zhide.govwatch.define.ItbGovFeeService;
import com.zhide.govwatch.model.LoginUserInfo;
import com.zhide.govwatch.model.patentarea;
import com.zhide.govwatch.model.sqlParameter;
import com.zhide.govwatch.repository.patentareaRepository;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

@Component
public class GovFeeUtils {
    @Autowired
    patentareaRepository patentareaRepository;
    @Autowired
    private ItbGovFeeService itbGovFeeService;

    public void generatorCriteria(Criteria criteria, HttpServletRequest request, Class<?> tClass) throws Exception {
        Date begin = null;
        Date end = null;
        LoginUserInfo Info = CompanyContext.get();
        List<Integer> listAreaID = new ArrayList<>();
        List<Criteria> listCriteria = new ArrayList<>();
        String RoleName = Info.getRoleName();
        if (RoleName.equals("企业用户") == false) {
            if (!RoleName.equals("系统管理员")) {
                List<patentarea> listPatentarea = patentareaRepository.findAllByUserId(Info.getUserId());
                if (listPatentarea.size() > 0) {
                    for (patentarea area : listPatentarea) {
                        listAreaID.add(area.getAreaId());
                    }
                }
                if (listAreaID.size() > 0) {
                    Criteria c1 = Criteria.where("GOVID").gt(0)
                            .orOperator(
                                    Criteria.where("PROVINCEID").in(listAreaID),
                                    Criteria.where("CITYID").in(listAreaID),
                                    Criteria.where("COUNTYID").in(listAreaID)
                            );
                    listCriteria.add(c1);
                }
            }
        }
        if (RoleName.equals("企业用户") == true) {
            listCriteria.add(Criteria.where("CLIENTID").is(Info.getUserId()));
        }
        String ClientId = request.getParameter("clientId");
        if (ClientId != null || Strings.isNotEmpty(ClientId)) {
            listCriteria.add(Criteria.where("CLIENTID").is(Integer.parseInt(ClientId)));
        }
        ;

        String queryText = request.getParameter("Query");
        if (Strings.isNotEmpty(queryText)) {
            List<sqlParameter> Vs = JSON.parseArray(queryText, sqlParameter.class);
            List<sqlParameter> OrItems = sqlParameterCreator.convert(Vs);

            if (OrItems.size() > 0) {
                List<Criteria> bb = new ArrayList<>();
                for (int i = 0; i < OrItems.size(); i++) {
                    String field = OrItems.get(i).getField();
                    String value = OrItems.get(i).getValue().replace("%", "");
                    Pattern pattern = Pattern.compile("^.*" + value + ".*$", Pattern.CASE_INSENSITIVE);
                    bb.add(Criteria.where(field).is(pattern));
                }
                Criteria tiny = new Criteria();
                tiny.orOperator(bb);
                listCriteria.add(tiny);
            }
        }

        String highText = request.getParameter("High");
        if (Strings.isNotEmpty(highText)) {
            List<sqlParameter> Ps = JSON.parseArray(highText, sqlParameter.class);
            List<sqlParameter> AndItems = sqlParameterCreator.convert(Ps);
            if (AndItems.size() > 0) {
                for (int i = 0; i < AndItems.size(); i++) {
                    String field = AndItems.get(i).getField();
                    String oper = AndItems.get(i).getOper();
                    if (oper.equals(">=")) {
                        String value = AndItems.get(i).getValue();
                        begin = new SimpleDateFormat("yyyy-MM-dd").parse(value);
                        listCriteria.add(Criteria.where(field).gte(begin));
                    } else if (oper.equals("<=")) {
                        String value = AndItems.get(i).getValue();
                        end = new SimpleDateFormat("yyyy-MM-dd").parse(value);
                        listCriteria.add(Criteria.where(field).lte(end));
                    } else if (oper.equals("LIKE")) {
                        String value = AndItems.get(i).getValue().replace("%", "");
                        Pattern pattern = Pattern.compile("^.*" + value + ".*$", Pattern.CASE_INSENSITIVE);
                        listCriteria.add(Criteria.where(field).regex(pattern));
                    } else if (field.equals("MONEY")) {
                        Integer value = Integer.parseInt(AndItems.get(i).getValue());
                        listCriteria.add(Criteria.where(field).is(value));
                    } else if (oper.equals("=")) {
                        String value = AndItems.get(i).getValue();
                        Field findfield = tClass.getDeclaredField(field);
                        findfield.setAccessible(true);
                        Object OO = null;
                        if (findfield.getType() == Integer.class) {
                            OO = Integer.parseInt(value);
                        } else if (findfield.getType() == Double.class) {
                            OO = Double.parseDouble(value);
                        } else OO = value;
                        listCriteria.add(Criteria.where(field).is(OO));
                    }
                }
            }
        }

        String State = request.getParameter("State");
        if (State.equals("ZeroToThirty")) {
            listCriteria.add(Criteria.where("DIFFDATE").gte(0));
            listCriteria.add(Criteria.where("DIFFDATE").lte(30));
        } else if (State.equals("ThirtyToNinety")) {
            listCriteria.add(Criteria.where("DIFFDATE").gte(30));
            listCriteria.add(Criteria.where("DIFFDATE").lte(90));
        } else if (State.equals("Invalid")) {
            listCriteria.add(Criteria.where("DIFFDATE").lt(0));
        } else if (State.equals("Customize")) {
            String Code = request.getParameter("Code");
            if (StringUtils.isEmpty(Code) == false) {
                Integer Num = Integer.parseInt(Code);
                if (Num != null) {
                    listCriteria.add(Criteria.where("DIFFDATE").is(Num));
                }
            }
        }
        if (listCriteria.size() > 0) criteria.andOperator(listCriteria);
    }
}
