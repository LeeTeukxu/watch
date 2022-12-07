package com.zhide.govwatch.imple;

import com.zhide.govwatch.common.CompanyContext;
import com.zhide.govwatch.common.EntityHelper;
import com.zhide.govwatch.define.IsmtpAccountService;
import com.zhide.govwatch.model.LoginUserInfo;
import com.zhide.govwatch.model.smtpAccount;
import com.zhide.govwatch.repository.smtpAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class smtpAccountServiceImpl implements IsmtpAccountService {

    @Autowired
    smtpAccountRepository smtpAccountRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public smtpAccount Save(smtpAccount sAccount, String UserName) throws Exception {
        LoginUserInfo loginUserInfo = CompanyContext.get();
        if (sAccount.getId() == null) {
            if (sAccount.getMailSSL() == true) {
                sAccount.setMailSSL(true);
            } else sAccount.setMailSSL(false);
            sAccount.setUserName(UserName);
            sAccount.setUserId(loginUserInfo.getUserId());
            sAccount.setCanUse(true);
        } else {
            sAccount.setUserName(UserName);
            Optional<smtpAccount> fsmtpAccount = smtpAccountRepository.findById(sAccount.getId());
            if (fsmtpAccount.isPresent()) {
                EntityHelper.copyObject(sAccount, fsmtpAccount.get());
            }
        }
        smtpAccountRepository.save(sAccount);
        return sAccount;
    }
}
