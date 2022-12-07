package com.zhide.govwatch.config;

import com.zhide.govwatch.common.CompanyContext;
import com.zhide.govwatch.model.LoginUserInfo;
import com.zhide.govwatch.model.tbClient;
import com.zhide.govwatch.model.tbLoginUser;
import com.zhide.govwatch.repository.tbClientRepository;
import com.zhide.govwatch.repository.tbLoginUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Optional;

/**
 * @ClassName: LoginSuccessHandler
 * @Author: 肖新民
 * @*TODO:
 * @CreateTime: 2021年04月09日 21:14
 **/
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    Logger logger= LoggerFactory.getLogger(LoginSuccessHandler.class);
    @Autowired
    tbLoginUserRepository loginUserRep;
    @Autowired
    tbClientRepository clientRep;

    @Override
    @Transactional
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws ServletException, IOException {
        LoginUserInfo userDetails = (LoginUserInfo) authentication.getPrincipal();
        if (userDetails != null) {
            request.getSession().setAttribute("LoginUser", userDetails);
            CompanyContext.set(userDetails);
            Optional<tbLoginUser> findUsers = loginUserRep.findAllByLoginCode(userDetails.getAccount());
            if(findUsers.isPresent()){
                tbLoginUser loginUser=findUsers.get();
                loginUser.setLastLoginTime(new Date());
                Integer LogNum=loginUser.getLoginCount();
                if(LogNum==null)LogNum=0;
                LogNum+=1;
                loginUser.setLoginCount(LogNum);
                loginUserRep.save(loginUser);
            } else {
                Optional<tbClient> findClients=clientRep.findById(userDetails.getUserId());
                if(findClients.isPresent()){
                    tbClient client= findClients.get();
                    Integer LogNum=client.getLoginCount();
                    if(LogNum==null)LogNum=0;
                    LogNum++;

                    client.setLoginCount(LogNum);
                    client.setLastLoginTime(new Date());
                    clientRep.save(client);
                }
            }
        }
        logger.info(authentication.getName()+"已成功登录系统");
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
