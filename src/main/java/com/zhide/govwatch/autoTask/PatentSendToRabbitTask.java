package com.zhide.govwatch.autoTask;

import com.alibaba.fastjson.JSON;
import com.zhide.govwatch.common.RabbitUtil;
import com.zhide.govwatch.common.allRabbitMQQueueNames;
import com.zhide.govwatch.model.GovPatentInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName: PatentSendToRabbitTask
 * @Author: 肖新民
 * @*TODO:
 * @CreateTime: 2022年03月17日 10:15
 **/
@Component
public class PatentSendToRabbitTask {
    @Autowired
    StringRedisTemplate redisUtils;
    @Autowired
    RabbitUtil rabbitUtil;

    @Autowired
    allRabbitMQQueueNames allMessage;
    String ListKey="PatentPushList";
    @Scheduled(cron = "0/5 * * * * ?")
    public void Process(){
        List<GovPatentInfo> Res=new ArrayList<>();
        List<String> Objs=new ArrayList<>();
        Long Size= redisUtils.opsForList().size(ListKey);
         if(Size>50){
           Objs= redisUtils.opsForList().range(ListKey,0,50);
        }
        else if(Size<50 && Size>0){
            String X=redisUtils.opsForList().range(ListKey,0,1).get(0);
            GovPatentInfo LastOne= JSON.parseObject(X,GovPatentInfo.class);
            Date Process=LastOne.getProcessTime();
            if(Process==null) {
                Process = new Date();
                Process.setHours(10);
            }
            Long LastTime=new Date().getTime()-Process.getTime();
            if(LastTime>10000){
                Objs=redisUtils.opsForList().range(ListKey,0,50);
            }
        }
        if(Objs.size()>0){
            Res=Objs.stream().map(f->JSON.parseObject(f,GovPatentInfo.class)).collect(Collectors.toList());
            rabbitUtil.send(allMessage.pantentItemAccepted(), Res);
            for(int i=0;i<Objs.size();i++) {
                redisUtils.opsForList().leftPop(ListKey);
            }
        }
    }
}
