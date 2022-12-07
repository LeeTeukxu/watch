package com.zhide.govwatch.imple;

import com.zhide.govwatch.common.CompanyContext;
import com.zhide.govwatch.common.EntityHelper;
import com.zhide.govwatch.define.ItbLoginUserService;
import com.zhide.govwatch.model.LoginUserInfo;
import com.zhide.govwatch.model.tbLoginUser;
import com.zhide.govwatch.model.v_LoginUser;
import com.zhide.govwatch.repository.tbLoginUserRepository;
import com.zhide.govwatch.repository.v_LoginUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class tbLoginUserServiceImpl implements ItbLoginUserService {
    @Autowired
    tbLoginUserRepository loginUserRepository;
    @Autowired
    v_LoginUserRepository userRep;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public tbLoginUser save(tbLoginUser user) throws Exception {
        LoginUserInfo Info = CompanyContext.get();
        Optional<tbLoginUser> existUser = null;
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(5);
        boolean addNew = false;
        if (user.getUserId() == null) {
            user.setPassword(encoder.encode(user.getPassword()));
            user.setXPass(user.getPassword());
            user.setCreateTime(new Date());
            existUser = loginUserRepository.findAllByLoginCode(user.getLoginCode());
        } else {
            Optional<tbLoginUser> ftbLoginUser = loginUserRepository.findById(user.getUserId());
            if (ftbLoginUser.isPresent()) {
                tbLoginUser nowOne = ftbLoginUser.get();
                if (nowOne.getPassword().equals(user.getPassword()) == false) {
                    user.setPassword(encoder.encode(user.getPassword()));
                    user.setXPass(user.getPassword());
                }
                user.setLastLoginTime(new Date());
                EntityHelper.copyObject(user, nowOne);
            }
            existUser = loginUserRepository.findAllByLoginCodeAndUserIdIsNot(user.getLoginCode(), user.getUserId());
        }
        if (existUser != null) {
            tbLoginUser OO = existUser.get();
            throw new Exception(OO.getLoginCode() + "已存在，不能重复使用。");
        }
        tbLoginUser result = loginUserRepository.save(user);
        return result;
    }

    @Override
    public tbLoginUser getById(int userId) {
        return loginUserRepository.findById(userId).get();
    }

    @Cacheable(value = "getAllLoginUser")
    public List<tbLoginUser> getAll() {
        return loginUserRepository.findAll();
    }

    @Override
    @Cacheable(value="findLoginUserByCode",unless = "#result==null")
    public v_LoginUser findByCode(String Code) {
        return userRep.findAllByLoginCode(Code);
    }
}
