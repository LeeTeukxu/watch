package com.zhide.govwatch.service;

import com.alibaba.fastjson.JSON;
import com.zhide.govwatch.model.acceptmessageerror;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.AccessType;
import org.springframework.stereotype.Component;
import com.zhide.govwatch.repository.acceptmessageerrorRepository;

import java.util.Date;

/**
 * @ClassName: AcceptErrorService
 * @Author: 肖新民
 * @*TODO:
 * @CreateTime: 2022年02月17日 13:45
 **/
@Component
public class AcceptErrorService {
    @Autowired
    acceptmessageerrorRepository acceptRep;
    public boolean addOne(String Type,String Error,String Text){
        acceptmessageerror error=new acceptmessageerror();
        error.setError(Error);
        error.setText(Text);
        error.setType(Type);
        error.setCreateTime(new Date());
        acceptRep.save(error);
        return true;
    }

}
