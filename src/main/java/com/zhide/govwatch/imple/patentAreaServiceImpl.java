package com.zhide.govwatch.imple;

import com.zhide.govwatch.common.CompanyContext;
import com.zhide.govwatch.common.ListUtils;
import com.zhide.govwatch.define.IPatentAreaService;
import com.zhide.govwatch.model.*;
import com.zhide.govwatch.repository.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.AccessType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @ClassName: patentAreaServiceImpl
 * @Author: 肖新民
 * @*TODO:
 * @CreateTime: 2022年02月21日 17:36
 **/
@Service
public class patentAreaServiceImpl implements IPatentAreaService {

    @Autowired
    patentareaRepository areaRep;
    @Autowired
    tbRoleClassRepository roleRep;
    @Autowired
    tbLoginUserRepository loginUserRep;
    @Autowired
    tbCompanyRepository companyRep;
    @Autowired
    tbDepListRepository depRep;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveAll(List<patentarea> areas) {
        LoginUserInfo Info= CompanyContext.get();
        for(int i=0;i<areas.size();i++){
            patentarea area=areas.get(i);
            Integer userId=area.getUserId();
            String strArea=area.getAreaIds();
            areaRep.deleteAllByUserId(userId);
            List<Integer> AreaIDS= ListUtils.parse(strArea,Integer.class);
            for(int n=0;n<AreaIDS.size();n++) {
                Integer AreaID=AreaIDS.get(n);
                patentarea newArea=new patentarea();
                newArea.setAreaId(AreaID);
                newArea.setUserId(userId);
                newArea.setCreateMan(Info.getUserId());
                newArea.setCreateTime(new Date());
                areaRep.save(newArea);
            }
        }
    }

    @Override
    public List<patentarea> getData() {
        List<tbLoginUser> users=loginUserRep.findAll();
        List<tbCompany> companyLists= companyRep.findAll();
        List<patentarea> areas= areaRep.findAll();
        List<patentarea> res=new ArrayList<>();
        for(int i=0;i<users.size();i++){
            tbLoginUser user=users.get(i);
            Integer CompanyId=800000+user.getCompanyId();
            Optional<patentarea> findOnes=res.stream().filter(f->f.getUserId().equals(CompanyId)).findFirst();
            patentarea parent=null;
            if(findOnes.isPresent()==false){
                tbCompany company=
                        companyLists.stream().filter(f->f.getId().equals(user.getCompanyId())).findFirst().get();
                parent=new patentarea();
                parent.setUserId(CompanyId);
                parent.setPid(0);
                parent.setUserName(company.getName());
                parent.setSn(Long.toString(res.stream().filter(f->f.getPid()==0).count()+1));
                res.add(parent);
            } else parent= findOnes.get();
            patentarea child=new patentarea();
            child.setPid(CompanyId);
            child.setUserId(user.getUserId());
            child.setUserName(user.getUserName());
            child.setSn(parent.getSn()+Long.toString(res.stream().filter(f->f.getPid().equals(CompanyId)).count()+1));
            List<Integer>areaIds=
                    areas.stream().filter(f->f.getUserId()==user.getUserId()).map(f->f.getAreaId()).collect(Collectors.toList());
            child.setAreaIds(StringUtils.join(areaIds,","));
            res.add(child);
        }
        return res;
    }
}
