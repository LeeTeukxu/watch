package com.zhide.govwatch.autoTask;

import com.zhide.govwatch.mapper.PatentMapper;
import com.zhide.govwatch.model.patent;
import com.zhide.govwatch.repository.PatentRepository;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @ClassName: FixedPatentInfo
 * @Author: 肖新民
 * @*TODO:
 * @CreateTime: 2022年05月20日 15:58
 **/
@Component
public class FixedPatentInfo {

    @Autowired
    PatentMapper patentMapper;
    @Autowired
    PatentRepository pRep;


    @Scheduled(cron = "0 0 0/4 * * ?")
    @Transactional()
    public void Process(){
        String Shenqingrxm= patentMapper.getAllProvinceIsNull();
        if(StringUtils.isEmpty(Shenqingrxm)==false){
            List<patent> saveData=new ArrayList<>();
            List<patent> finds=pRep.findAllByShenqingrxm(Shenqingrxm);
            if(finds.size()>0){
                List<patent> fulls=finds.stream().filter(f->f.getProvinceId()!=null).collect(Collectors.toList());
                List<patent> emptys=finds.stream().filter(f->f.getProvinceId()==null).collect(Collectors.toList());
                if(fulls.size()>0 && emptys.size()>0){
                    patent province=fulls.get(0);
                    Optional<patent> citys=fulls.stream().filter(f->f.getCityId()!=null).findFirst();

                    for(int i=0;i<emptys.size();i++){
                        patent empty=emptys.get(i);
                        empty.setProvinceId(province.getProvinceId());
                        empty.setProvinceName(province.getProvinceName());
                        if(citys.isPresent()){
                            patent city=citys.get();
                            if(empty.getCityId()==null){
                                empty.setCityId(city.getCityId());
                                empty.setCityName(city.getCityName());
                            }
                        }
                        saveData.add(empty);
                    }
                }
            }
            if(saveData.size()>0) pRep.saveAll(saveData);
        }
    }
}
