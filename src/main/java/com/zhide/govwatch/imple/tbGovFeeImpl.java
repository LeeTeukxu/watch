package com.zhide.govwatch.imple;

import com.zhide.govwatch.define.ItbGovFeeService;
import com.zhide.govwatch.model.tbGovFee;
import com.zhide.govwatch.repository.PatentRepository;
import com.zhide.govwatch.repository.patentInfoPermissionRepository;
import com.zhide.govwatch.repository.tbGovFeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class tbGovFeeImpl implements ItbGovFeeService {

    @Autowired
    patentInfoPermissionRepository patentInfoPermissionRepository;

    @Autowired
    tbGovFeeRepository govFeeRepository;

    @Autowired
    PatentRepository patentRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(List<String> shenqinghs) throws Exception {
        return true;
    }

    @Override
    public List<tbGovFee> getGovFeeByAppNoToMap(String AppNo) {
        return govFeeRepository.findAllByAppNo(AppNo);
    }
}
