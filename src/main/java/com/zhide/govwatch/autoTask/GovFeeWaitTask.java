package com.zhide.govwatch.autoTask;

import com.alibaba.fastjson.JSON;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.zhide.govwatch.mapper.GovFeeMapper;
import com.zhide.govwatch.model.govFeeWaitMongo;
import com.zhide.govwatch.model.view_govfee;
import com.zhide.govwatch.repository.view_govfeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class GovFeeWaitTask {
    @Autowired
    view_govfeeRepository govFeeRep;
    @Autowired
    MongoTemplate mongoRep;
    @Autowired
    GovFeeMapper govMapper;

    Logger logger = LoggerFactory.getLogger(GovFeeWaitTask.class);

    @Scheduled(cron = "0 0 1 * * ?")
    @Transactional
    public void Process() {
        String CollectionName = "govwait";
        try {
            Integer XSource = Math.toIntExact(govFeeRep.count());
            Integer XNow = Math.toIntExact(mongoRep.getCollection(CollectionName).estimatedDocumentCount());
            if (XSource.equals(XNow) == false) {
                if (mongoRep.collectionExists(CollectionName) == true) {
                    logger.info("数据库:{},Mopngodb:{},两者数量不一致，删除重建!", XSource, XNow);
                    mongoRep.dropCollection(CollectionName);

                    MongoCollection doc= mongoRep.createCollection(CollectionName);
                    List<String> keys = new ArrayList<>();
                    keys.add("SHENQINGH");
                    keys.add("JIAOFEIR");
                    keys.add("PROVINCEID");
                    keys.add("CITYID");
                    keys.add("COUNTRYID");
                    keys.add("CLIENTID");
                    keys.add("CLIENTNAME");
                    keys.add("DIFFDATE");
                    keys.add("FAMINGMC");
                    keys.add("FEENAME");
                    keys.add("SHENQINGLX");
                    keys.add("LAWSTATUS");
                    keys.add("MONEY");
                    for(int i=0;i<keys.size();i++){
                        BasicDBObject bson=new BasicDBObject();
                        bson.put(keys.get(i),1);
                        doc.createIndex(bson);
                    }
                }
            } else {
                logger.info("govwait集合没有发生变化。不进行数据操作!");
                return ;
            }
            Long X1 = System.currentTimeMillis();
            int Total = Math.toIntExact(XSource) / 50000 + 1;
            logger.info("开始同步tbgovFee表,一共{}条记录，分成{}次同步.", XSource, Total);
            for (int i = 0; i < Total; i++) {
                Long T1 = System.currentTimeMillis();
                List<view_govfee> Os = govMapper.getAll(i * 50000, 50000);
                if (Os.size() > 0) {
                    String OsText = JSON.toJSONString(Os);
                    List<govFeeWaitMongo> Ms = JSON.parseArray(OsText, govFeeWaitMongo.class);
                    mongoRep.insert(Ms, CollectionName);
                    Long T2 = System.currentTimeMillis();
                    logger.info("插入{}页,共{}条记录，累计用时:{}毫秒。", i, Ms.size(), (T2 - T1));
                    OsText = null;
                    Ms = null;
                }
                Os = null;
            }
            Long X2 = System.currentTimeMillis();
            logger.info("同步成功,一共有时:" + Long.toString(X2 - X1));
            System.gc();
        } catch (Exception ax) {
            ax.printStackTrace();
        }
    }
}
