package com.zhide.govwatch.imple;

import com.zhide.govwatch.common.CompanyContext;
import com.zhide.govwatch.define.IPatentPermissionService;
import com.zhide.govwatch.mapper.PatentPermissionMapper;
import com.zhide.govwatch.mapper.tbGovFeeMapper;
import com.zhide.govwatch.model.LoginUserInfo;
import com.zhide.govwatch.model.patentInfoPermission;
import com.zhide.govwatch.repository.PatentRepository;
import com.zhide.govwatch.repository.patentInfoPermissionRepository;
import com.zhide.govwatch.repository.tbGovFeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PatentPermissionServiceImpl implements IPatentPermissionService {

    @Autowired
    patentInfoPermissionRepository patentInfoPermissionRepository;

    @Autowired
    tbGovFeeRepository govFeeRepository;

    @Autowired
    tbGovFeeMapper govFeeMapper;

    @Autowired
    PatentRepository patentRepository;

    @Autowired
    PatentPermissionMapper patentPermissionMapper;

    Logger logger= LoggerFactory.getLogger(PatentPermissionServiceImpl.class);

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(List<String> shenqinghs) throws Exception {
        LoginUserInfo Info = CompanyContext.get();
        for (int i=0;i<shenqinghs.size();i++){
            String shenqingh = shenqinghs.get(i);

            patentInfoPermission patentInfoPermission = new patentInfoPermission();
            Optional<patentInfoPermission> findOne = patentInfoPermissionRepository.findByShenqingh(shenqingh);
            if (findOne.isPresent()){
//                EntityHelper.copyObject(patentInfoPermission,findOne.get());
            }else {
                patentInfoPermission.setShenqingh(shenqingh);
                patentInfoPermission.setUserid(Info.getUserId());
                patentInfoPermissionRepository.save(patentInfoPermission);
            }
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean remove(List<String> shenqinghs) throws Exception {
        LoginUserInfo Info = CompanyContext.get();
        for (int i=0;i<shenqinghs.size();i++){
            String shenqingh = shenqinghs.get(i);
            Optional<patentInfoPermission> findOne = patentInfoPermissionRepository.findByShenqingh(shenqingh);
            if (findOne.isPresent()){
                logger.info(Info.getUserName() + "试图删除已添加监控的专利号：" + findOne.get().getShenqingh());
            }
            govFeeMapper.delGovFee(shenqingh);
            patentPermissionMapper.delPatentPermission(shenqingh);
        }
        return false;
    }
}
