package com.zhide.govwatch.imple;

import com.alibaba.fastjson.JSONArray;
import com.zhide.govwatch.common.CompanyContext;
import com.zhide.govwatch.common.EntityHelper;
import com.zhide.govwatch.define.IPatentContactService;
import com.zhide.govwatch.model.LoginUserInfo;
import com.zhide.govwatch.model.tbPatentContact;
import com.zhide.govwatch.repository.PatentContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class PatentContactServiceImpl implements IPatentContactService {
    @Autowired
    PatentContactRepository patentContactRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(JSONArray jsonArray) throws Exception {
        LoginUserInfo Info = CompanyContext.get();
        tbPatentContact patentContact = new tbPatentContact();
        String Mail = "";
        if (jsonArray.size() > 0) {
            for (int i = 0; i <jsonArray.size();i++){
                String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
                Pattern regex = Pattern.compile(check);
                Matcher matcher = regex.matcher(jsonArray.get(i).toString());
                if (matcher.matches()) {
                    Mail += jsonArray.get(i) + ";";
                }else throw new Exception("存在非法的邮件地址");
            }
            Mail = Mail.substring(0,Mail.length()-1);
            Optional<tbPatentContact> findOne = patentContactRepository.findAllByUserId(Info.getUserId());
            if (findOne.isPresent()) {
                EntityHelper.copyObject(findOne.get(),patentContact);
                patentContact.setMailAddress(findOne.get().getMailAddress() + ";" + Mail);
            }else {
                patentContact.setMailAddress(Mail);
                patentContact.setUserId(Info.getUserId());
                patentContact.setCreateTime(new Date());
            }
            patentContactRepository.save(patentContact);
        }
        return true;
    }
}
