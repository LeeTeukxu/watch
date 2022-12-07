package com.zhide.govwatch.imple;

import com.alibaba.fastjson.JSON;
import com.zhide.govwatch.define.IElasticsearchService;
import com.zhide.govwatch.model.patent;
import com.zhide.govwatch.model.patentElInfo;
import com.zhide.govwatch.repository.PatentRepository;
import com.zhide.govwatch.repository.patentElInfoRepository;
import org.apache.ibatis.annotations.Delete;
import org.elasticsearch.index.query.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.data.elasticsearch.core.document.Document;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName: ElasticsearchServiceImpl
 * @Author: 肖新民
 * @*TODO:
 * @CreateTime: 2021年12月23日 11:13
 **/
@Service
public class ElasticsearchServiceImpl implements IElasticsearchService {
    @Value("${spring.elasticsearch.uris}")
    String elServer;
    @Autowired
    PatentRepository patentRep;
    @Autowired
    patentElInfoRepository pElRep;
    @Autowired
    ElasticsearchRestTemplate elRep;

    Logger logger= LoggerFactory.getLogger(ElasticsearchServiceImpl.class);
    @Override
    public String RebuildIndex() throws Exception {
//        String OK="{\"acknowledged\":true}";
//        String DeleteResult= DeleteIndex();
//        if(OK.equals(DeleteResult)){
//            logger.info("索引删除成功!");
//        }

        //IndexOperations indexOperations= elRep.indexOps(patentElInfo.class);
        //Document doc= indexOperations.createMapping(patentElInfo.class);
        Long X1 = System.currentTimeMillis();
        List<patent> Ps = patentRep.findAll();
        int Total=Ps.size()/500+1;
        for (int i = 0; i < Total; i++) {
            List<patent> Os=Ps.stream().skip(i*500).limit(500).collect(Collectors.toList());
            String OsText=JSON.toJSONString(Os);
            List<patentElInfo> Rows = JSON.parseArray(OsText, patentElInfo.class);
            elRep.save(Rows,IndexCoordinates.of("watch"));
            Thread.sleep(100);
        }
        Long X2=System.currentTimeMillis();
        logger.info("添加索引用时:{}",(X2-X1));
        return String.format("添加索引用时:%d",(X2-X1));
    }
    @Override
    public  String  DeleteIndex() throws Exception{
        String msg="";
        URL url = new URL(elServer + "/watch");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("DELETE");
        connection.connect();
        int code = connection.getResponseCode();
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            msg += line + "\n";
        }
        reader.close();
        // 5. 断开连接
        connection.disconnect();
        return msg;
    }
}
