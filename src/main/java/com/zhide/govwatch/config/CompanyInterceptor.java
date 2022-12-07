package com.zhide.govwatch.config;

import com.zhide.govwatch.common.CompanyContext;
import com.zhide.govwatch.common.CompanyTokenUtils;
import com.zhide.govwatch.common.FTPConfig;
import com.zhide.govwatch.model.LoginUserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

@Component
public class CompanyInterceptor extends HandlerInterceptorAdapter {
    Logger logger = LoggerFactory.getLogger(CompanyInterceptor.class);
    List<String> Urls = Arrays.asList("/login.html", "/login", "/logout", "/error", "/WebAPI/Login", "/PostWeb" +
            "/postGetResult", "/PostWeb/addMachine");
    @Value("${publish.version}")
    String version;
    @Autowired
    FTPConfig ftpConfig;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView
            modelAndView) throws Exception {
        CompanyContext.clear();
        if (version != null && modelAndView != null) {
            modelAndView.addObject("version", version);
        }
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws
            Exception {
        String VX = request.getRequestURI();
        if (VX.startsWith("/js") ||
                VX.startsWith("/images") ||
                VX.startsWith("/appImages") ||
                VX.startsWith("/res") ||
                VX.startsWith("/scripts") ||
                VX.startsWith("/img") ||
                VX.startsWith("/font") ||
                VX.startsWith("/error") ||
                VX.startsWith("/css")) return true;
        if (Urls.contains(VX)) return true;
        logger.info("URL:" + VX);
        boolean hasLogin = false;
        if (VX.startsWith("/xx")) {
            request.getSession().setAttribute("topCode", VX.replace("/", ""));
        }
        try {
            HttpSession session = request.getSession();
            if (session != null) {
                Object OO = session.getAttribute("LoginUser");
                if (OO != null) {
                    LoginUserInfo Info = (LoginUserInfo) OO;
                    if (Info != null) {
                        CompanyContext.set(Info);
                        hasLogin = true;
                    }
                } else {
                    String token = request.getParameter("webToken");
                    if (StringUtils.hasText(token)) {
                        if (CompanyTokenUtils.existToken(token)==true) {
                            LoginUserInfo info = new LoginUserInfo();
                            CompanyContext.set(info);
                            hasLogin = true;
                            logger.info(VX + " take webToken:" + token);
                        }
                    }
                }
            }
        }catch(Exception ax){
            hasLogin=false;
        }
        if (hasLogin == false) {
            List<String>firstPages=Arrays.asList("/","/index");
            if(firstPages.contains(VX)==false) {
                //logger.info(VX + "被访问时Session已过期!");
                response.sendRedirect("/login.html");
                return false;
            } else return true;
        }
        return true;
    }
}
