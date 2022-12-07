package com.zhide.govwatch.autoTask;

import com.alibaba.fastjson.JSON;
import com.mongodb.BasicDBObject;
import com.zhide.govwatch.mapper.PatentMapper;
import com.zhide.govwatch.model.patent;
import com.zhide.govwatch.model.patentElInfo;
import com.zhide.govwatch.model.patentMongo;
import com.zhide.govwatch.repository.PatentRepository;
import com.zhide.govwatch.repository.patentElInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName: ElacticUpdateTask
 * @Author: 肖新民
 * @*TODO:
 * @CreateTime: 2022年03月21日 17:18
 **/
@Component
public class ElacticUpdateTask {
    @Autowired
    PatentRepository patentRep;
    @Autowired
    patentElInfoRepository pElRep;
    @Autowired
    ElasticsearchRestTemplate elRep;
    @Autowired
    MongoTemplate mongoRep;
    @Autowired
    PatentMapper pMapper;

    Logger logger = LoggerFactory.getLogger(ElacticUpdateTask.class);

    @Scheduled(cron = "0 0 12 * * ?")
    public void Process() {
        String CollectionName = "AllPatent";
        try {
            if (mongoRep.collectionExists(CollectionName) == true) {
                mongoRep.dropCollection(CollectionName);
                BasicDBObject object = new BasicDBObject();
                object.put("shenqingh", 1);
                mongoRep.createCollection(CollectionName).createIndex(object);
            }

            Long X = patentRep.count();
            int Total = Math.toIntExact(X) / 5000 + 1;
            Long X1 = System.currentTimeMillis();
            logger.info("一共{}条记录，分为{}次同步!", X, Total);
            for (int i = 0; i < Total; i++) {
                List<patent> Os = pMapper.getAll(i * 5000, 5000);
                if (Os.size() > 0) {
                    String OsText = JSON.toJSONString(Os);
                    List<patentElInfo> Rows = JSON.parseArray(OsText, patentElInfo.class);
                    elRep.save(Rows, IndexCoordinates.of("watch"));
                    logger.info("同步到Elasticsearch中成功!");
                    List<patentMongo> Ms = JSON.parseArray(OsText, patentMongo.class);
                    mongoRep.insert(Ms, CollectionName);
                    logger.info("同步到Mongodb中成功!");
                    Ms = null;
                    Rows = null;
                    OsText = null;
                }
                Os = null;
            }
            System.gc();
            Long X2 = System.currentTimeMillis();
            logger.info("开始同步专利信息，共{}条记录，分成{}次同步,一共用时{}", X, Total,(X2-X1));
        } catch (Exception ax) {
            logger.info("更新搜索引挚出错:" + ax.getMessage());
        }
    }
}
