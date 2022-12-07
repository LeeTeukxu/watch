package com.zhide.govwatch.autoTask;

import com.zhide.govwatch.mapper.PatentMapper;
import com.zhide.govwatch.model.companyall;
import com.zhide.govwatch.model.patent;
import com.zhide.govwatch.model.tbarea;
import com.zhide.govwatch.repository.companyallRepository;
import com.zhide.govwatch.repository.PatentRepository;
import com.zhide.govwatch.repository.tbareaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @ClassName: PatentAreaFindTask
 * @Author: 肖新民
 * @*TODO:
 * @CreateTime: 2022年04月07日 16:32
 **/
@Component
public class PatentAreaFindTask {
    @Autowired
    PatentMapper patentMapper;
    @Autowired
    companyallRepository companyAllRep;
    @Autowired
    PatentRepository patentRep;
    @Autowired
    tbareaRepository areaRep;
    Logger logger = LoggerFactory.getLogger(PatentAreaFindTask.class);

    @Scheduled(cron = "0 0 5 * * ?")
    @Transactional
    public void Process() {
        Map<String, Integer> CodeHash = new HashMap<>();
        List<Map<String, Object>> FindObjects = patentMapper.getEmptyAreaPatent();
        List<patent> Os = new ArrayList<>();
        if (FindObjects.size() > 0) {
            for (int i = 0; i < FindObjects.size(); i++) {
                Map<String, Object> obj = FindObjects.get(i);
                String Shenqingrxm = obj.get("Shenqingrxm").toString().trim();
                String Shenqingh = obj.get("Shenqingh").toString();
                String RealName = Shenqingrxm.split(";")[0];
                Optional<companyall> findOnes = companyAllRep.findFirstByName(RealName);
                if (findOnes.isPresent()) {
                    companyall find = findOnes.get();
                    patent findPatent = patentRep.findAllByShenqingh(Shenqingh);
                    if (findPatent != null) {
                        String ProvinceName = find.getProvinceName();
                        Integer ProvinceID = 0;
                        if (CodeHash.containsKey(ProvinceName)) {
                            ProvinceID = CodeHash.get(ProvinceName);
                        } else {
                            Optional<tbarea> findProvince = areaRep.findFirstByNameLike(ProvinceName);
                            if (findProvince.isPresent()) {
                                tbarea areaPro = findProvince.get();
                                ProvinceID = areaPro.getId();
                                CodeHash.put(ProvinceName, ProvinceID);
                            }
                        }
                        if (ProvinceID > 0) {
                            findPatent.setProvinceName(ProvinceName);
                            findPatent.setProvinceId(ProvinceID);
                        }

                        String CityName = find.getCityName();
                        Integer CityID = 0;
                        if (CodeHash.containsKey(CityName)) {
                            CityID = CodeHash.get(CityName);
                        } else {
                            Optional<tbarea> findCitys = areaRep.findFirstByNameLike(CityName);
                            if (findCitys.isPresent()) {
                                tbarea CityOne = findCitys.get();
                                CityID = CityOne.getId();
                                CodeHash.put(CityName, CityID);
                            }
                        }
                        if (CityID > 0) {
                            findPatent.setCityName(CityName);
                            findPatent.setCityId(CityID);
                        }

                        String CountyName = find.getCountyName();
                        Integer CountyID = 0;
                        if (CodeHash.containsKey(CountyName)) {
                            CountyID = CodeHash.get(CountyName);
                        } else {
                            Optional<tbarea> findCountys = areaRep.findFirstByNameLike(CountyName);
                            if (findCountys.isPresent()) {
                                tbarea CountyOne = findCountys.get();
                                CountyID = CountyOne.getId();
                                CodeHash.put(CountyName, CountyID);
                            }
                        }
                        if (CountyID > 0) {
                            findPatent.setCountyName(CountyName);
                            findPatent.setCountyId(CountyID);
                        }
                        findPatent.setAddress(find.getAddress());
                        logger.info(findPatent.getShenqingh() + ":" + findPatent.getFamingmc() + "已添加地址及行政区划信息。");
                        Os.add(findPatent);
                    }
                }
            }
            if (Os.size() > 0) {
                patentRep.saveAll(Os);
            }
        }
    }
}
