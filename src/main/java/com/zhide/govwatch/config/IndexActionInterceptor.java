package com.zhide.govwatch.config;

import com.alibaba.fastjson.JSON;
import com.zhide.govwatch.common.CompanyContext;
import com.zhide.govwatch.common.menuIdCacheObject;
import com.zhide.govwatch.define.IRoleFunctionService;
import com.zhide.govwatch.model.LoginUserInfo;
import com.zhide.govwatch.repository.tbMenuListRepository;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 对Index访问进行拦截，并将前缀与MenuID做一个缓存处理。
 */
@Component
public class IndexActionInterceptor extends HandlerInterceptorAdapter {
    Logger logger = LoggerFactory.getLogger(IndexActionInterceptor.class);
    @Autowired
    tbMenuListRepository menuRepository;
    @Autowired
    menuIdCacheObject cacheObject;
    @Autowired
    IRoleFunctionService roleFunService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws
            Exception {
        if (handler instanceof HandlerMethod) {
            String Url = request.getRequestURI();
            String menuId = request.getParameter("MenuID");
            if (Url.indexOf("/index") > -1 && !Strings.isEmpty(menuId)) {
                String Tx = Url.replace("/index", "");
                if (Url.equals("/work/notice/index")) {
                    String type = request.getParameter("Type");
                    Url = Tx + "?Type=" + type;
                } else Url = Tx;
                int MID = Integer.parseInt(menuId);
                String menuName = menuRepository.getTitleByFid(MID);
                request.getSession(true).setAttribute("CurrentMenu", menuName);
                cacheObject.add(menuId, Url);
            }

        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView
            modelAndView) throws Exception {
        if (handler instanceof HandlerMethod) {
            String pageName = request.getParameter("pageName");
            String XUrl = request.getRequestURI();
            LoginUserInfo Info = CompanyContext.get();
            if (StringUtils.isEmpty(pageName) == false) {
                modelAndView.addObject("PageName", pageName);
                if (Info != null) {
                    String menuId = request.getParameter("MenuID");
                    String menuName= cacheObject.getNameById(menuId);
                    Integer  roleId = Info.getRoleId();
                    List<String> HasFuns = roleFunService.GetAllRoleFunctions(roleId, menuId);
                    modelAndView.addObject("HasFuns", JSON.toJSONString(HasFuns));
                    List<String> AllFuns = roleFunService.GetAllFunctions(menuId);
                    modelAndView.addObject("AllFuns", JSON.toJSONString(AllFuns));
                    modelAndView.addObject("CurrentRoleName", Info.getRoleName());
                    modelAndView.addObject("CurrentMenu",menuName);
                }
            }
        }
    }
}
