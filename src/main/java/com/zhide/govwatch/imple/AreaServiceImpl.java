package com.zhide.govwatch.imple;

import com.zhide.govwatch.common.addressParsor;
import com.zhide.govwatch.define.IAreaService;
import com.zhide.govwatch.model.tbarea;
import com.zhide.govwatch.repository.tbareaRepository;
import com.zhide.govwatch.vo.addressNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @ClassName: AreaServiceImpl
 * @Author: 肖新民
 * @*TODO:
 * @CreateTime: 2022年03月18日 10:36
 **/
@Service
public class AreaServiceImpl implements IAreaService {

    @Autowired
    tbareaRepository areaRep;

    @Override
    @Cacheable(value="getAllArea")
    public List<tbarea> getAll() {
        List<tbarea> areas = areaRep.findAll();
        if(areas.size()==0) {
            addressParsor addressParsor = new addressParsor();
            List<addressNode> adds = addressParsor.getAll();
            for (int i = 0; i < adds.size(); i++) {
                addressNode rootNode = adds.get(i);
                tbarea root = new tbarea();
                root.setId(Integer.parseInt(rootNode.getCode() + "0000"));
                root.setPid(0);
                root.setName(rootNode.getName());
                root.setSn(i + 1);
                areas.add(root);
                if (rootNode.getChildren() != null) {
                    for (int a = 0; a < rootNode.getChildren().size(); a++) {
                        addressNode cityNode = rootNode.getChildren().get(a);
                        Integer CityID = Integer.parseInt(cityNode.getCode() + "00");
                        tbarea city = new tbarea();
                        city.setId(CityID);
                        city.setPid(root.getId());
                        city.setName(cityNode.getName());
                        city.setSn(root.getSn() * 10 + a + 1);
                        areas.add(city);

                        if (cityNode.getChildren() != null) {
                            for (int b = 0; b < cityNode.getChildren().size(); b++) {
                                addressNode countyNode = cityNode.getChildren().get(b);
                                Integer CountyID = Integer.parseInt(countyNode.getCode());
                                tbarea county = new tbarea();
                                county.setId(CountyID);
                                county.setPid(city.getId());
                                county.setName(countyNode.getName());
                                county.setSn(city.getSn() * 10 + b + 1);
                                areas.add(county);
                            }
                        }
                    }
                }
            }
        }
        return areas;
    }

    @Cacheable(value = "getProvinces")
    public List<tbarea> getProvinces() {
        return areaRep.findAllByPidIn(Arrays.asList(0));
    }

    public List<tbarea> getCitys(List<Integer> provinces) {
        return areaRep.findAllByPidIn(provinces);
    }

    public List<tbarea> getCoutys(List<Integer> citys) {
        List<tbarea> datas = areaRep.findAllByPidIn(citys);
        if (citys.size() > 1) {
            List<tbarea> nows = areaRep.findAllById(citys);
            datas.addAll(nows);
        }
        return datas;
    }

}
