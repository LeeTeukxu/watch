package com.zhide.govwatch.listener;

import com.zhide.govwatch.common.CompanyContext;
import com.zhide.govwatch.common.RabbitUtil;
import com.zhide.govwatch.common.allRabbitMQQueueNames;
import com.zhide.govwatch.model.LoginUserInfo;
import com.zhide.govwatch.model.patent;
import com.zhide.govwatch.vo.PatentChangedInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import java.util.Date;

/**
 * @ClassName: PantentChangeListener
 * @Author: 肖新民
 * @*TODO:
 * @CreateTime: 2021年04月19日 21:25
 **/
@Component
public class PatentInfoChangeListener {
    Logger logger= LoggerFactory.getLogger(PatentInfoChangeListener.class);

    @Autowired
    allRabbitMQQueueNames allNames;
    @Autowired
    RabbitUtil rabbitUtil;
    @PostRemove
    public void OnRemove(patent obj) {
        LoginUserInfo Info = CompanyContext.get();
        if (Info != null) {
            PatentChangedInfo change=new PatentChangedInfo();
            change.setContent(obj);
            change.setTime(new Date());
            change.setType("REMOVE");
            change.setUserName(Info.getUserName());
            rabbitUtil.publish(allNames.patentInfoChanged(),change);
        }
    }
    @PostPersist
    public void OnAdd(patent obj) {
        LoginUserInfo Info = CompanyContext.get();
        if (Info != null) {
            PatentChangedInfo change=new PatentChangedInfo();
            change.setContent(obj);
            change.setTime(new Date());
            change.setType("ADD");
            change.setUserName(Info.getUserName());
            rabbitUtil.publish(allNames.patentInfoChanged(),change);
        }
    }
    @PostUpdate
    public void OnUpdate(patent obj){
        LoginUserInfo Info = CompanyContext.get();
        if (Info != null) {
            PatentChangedInfo change=new PatentChangedInfo();
            change.setContent(obj);
            change.setTime(new Date());
            change.setType("UPDATE");
            change.setUserName(Info.getUserName());
            rabbitUtil.publish(allNames.patentInfoChanged(),change);
        }
    }
}
