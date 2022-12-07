package com.zhide.govwatch.service;

import com.zhide.govwatch.model.tbarea;
import com.zhide.govwatch.repository.tbareaRepository;
import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @ClassName: AreaCodeService
 * @Author: 肖新民
 * @*TODO:
 * @CreateTime: 2022年02月18日 15:00
 **/
@Component
public class AreaCodeService {
    @Autowired
    tbareaRepository areaRep;

    @Cacheable(value = "getCodeByName")
    public Integer getCodeByName(String name){
        if(StringUtils.isEmpty(name)) return null;
        if(name.endsWith("省") || name.endsWith("市") || name.endsWith("县")){
            String K=name.substring(0,name.length()-1);
            if(K.length()>=2)name=K;
        }
        Optional<tbarea> findAreas=areaRep.findFirstByNameLike("%"+name+"%");
        if(findAreas.isPresent()) return findAreas.get().getId();else return null;
    }
}
