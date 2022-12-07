package com.zhide.govwatch;

import com.alibaba.fastjson.JSON;
import com.aspose.words.License;
import com.zhide.govwatch.common.CompanyContext;
import com.zhide.govwatch.define.IClientInfoService;
import com.zhide.govwatch.define.ItbMenuListService;
import com.zhide.govwatch.model.ClientChangeInfo;
import com.zhide.govwatch.model.LoginUserInfo;
import com.zhide.govwatch.model.tbMenuList;
import com.zhide.govwatch.repository.PatentRepository;
import com.zhide.govwatch.repository.patentElInfoRepository;
import com.zhide.govwatch.repository.tbClientRepository;
import com.zhide.govwatch.repository.tbLoginUserRepository;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SpringBootApplication()
@Controller
@EnableCaching
@MapperScan(basePackages = "com.zhide.govwatch.mapper")
@EnableJpaRepositories(enableDefaultTransactions = false)
@EnableElasticsearchRepositories()
@EnableTransactionManagement
public class GovwatchApplication {
    private final Logger logger = LoggerFactory.getLogger(GovwatchApplication.class);
    @Autowired
    ItbMenuListService itbMenuListService;
    @Autowired
    tbLoginUserRepository loginUserRep;
    @Autowired
    tbClientRepository clientRep;
    @Autowired
    patentElInfoRepository pElRep;
    @Autowired
    PatentRepository patentRep;
    @Autowired
    ElasticsearchRestTemplate elRep;
    @Autowired
    IClientInfoService clientService;

    public static void main(String[] args) {
        getLicense();
//        StartNetty();
        SpringApplication.run(GovwatchApplication.class, args);
    }

    protected static void StartNetty() {
        try {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            Selector selector = Selector.open();
            NioServerSocketChannel attachment = new NioServerSocketChannel();
            SelectionKey sscKey = serverSocketChannel.register(selector, 0, attachment);
            serverSocketChannel.bind(new InetSocketAddress(9099));
            sscKey.interestOps(SelectionKey.OP_ACCEPT);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected static boolean getLicense() {
        boolean result = false;
        try {

            System.out.println("开始破解........");
            InputStream is = GovwatchApplication.class.getClassLoader().getResourceAsStream("license.xml");
            License aposeLic = new License();
            aposeLic.setLicense(is);
            result = aposeLic.getIsLicensed();
            System.out.println("破解完成........,结果:" + Boolean.toString(result));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping("/")
    public String Index(Map<String, Object> model, HttpServletRequest request) {
        return DoFirstPage(model, request, "/Annualfee/AnHome", "login.html");
    }

    @RequestMapping("/index")
    public String FirstPage(Map<String, Object> model, HttpServletRequest request) {
        // loginUserRep.findAll();
        return DoFirstPage(model, request, "/Annualfee/AnHome", "login.html");
    }

    @RequestMapping("/admin")
    public String Admin(Map<String, Object> model, HttpServletRequest request) {
        LoginUserInfo Info = CompanyContext.get();
        if (Info.getRoleName().equals("系统管理员") == false) return "/login.html";
        return DoFirstPage(model, request, "default", "login.html");
    }

    @GetMapping("/login.html")
    public String LoginPage(String word, Map<String, Object> model, HttpServletRequest request) {
        if (StringUtils.isEmpty(word)) word = "";
        model.put("word", word);
        return "loginPage";
    }

    @RequestMapping("/workBench")
    public String WorkBench(Map<String, Object> model) {
        model.put("loginUser", CompanyContext.get());
        return "/Annualfee/workBench";
    }

    @RequestMapping(value = "/xx*")
    public void TopCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            Object OO = request.getSession().getAttribute("topCode");
            if (OO != null) {
                String DD = OO.toString();
                clientService.UpdatePID(DD);
            }
            response.sendRedirect("/index");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String DoFirstPage(Map<String, Object> model, HttpServletRequest request, String okUrl, String failureUrl) {
        try {
            LoginUserInfo Info = CompanyContext.get();
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth.isAuthenticated() && Info != null) {
                List<tbMenuList> allMenus = null;
                //allMenus = itbMenuListService.getVisibleMenus(Info.getRoleId());
                try {
                    allMenus = itbMenuListService.getAllByCanUse();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                List<tbMenuList> childs =
                        allMenus.stream().filter(f -> f.getPid() > 0).distinct().collect(Collectors.toList());
                for (int i = 0; i < childs.size(); i++) {
                    tbMenuList child = childs.get(i);
                    String url = child.getUrl();
                    if (StringUtils.isEmpty(url)) continue;
                    String pageName = child.getPageName();
                    if (StringUtils.isEmpty(pageName)) pageName = "";
                    if (url.indexOf(pageName) == -1) {
                        if (StringUtils.isEmpty(pageName) == false) {
                            if (url.indexOf("?") > -1) {
                                url += "&pageName=" + pageName;
                            } else url += "?pageName=" + pageName;
                        }
                    }
                    if (url.indexOf("MenuID") == -1) {
                        String MenuID = Integer.toString(child.getFid());
                        if (StringUtils.isEmpty(MenuID) == false) {
                            if (url.indexOf("?") > -1) url += "&MenuID=" + MenuID;
                            else url += "?MenuID=" + MenuID;
                        }
                    }
                    child.setUrl(url);
                }
                List<tbMenuList> roots = allMenus.stream()
                        .filter(f -> f.getPid() == 0)
                        .collect(Collectors.toList());
                model.put("roots", roots);
                model.put("loginUser", CompanyContext.get());
                model.put("childMenus", childs);

                List<ClientChangeInfo> clientInfos = null;
                Object OX = request.getSession().getAttribute("Companys");
                if (OX != null) clientInfos = (List<ClientChangeInfo>) OX;
                else clientInfos = new ArrayList<>();
                clientInfos.stream().forEach(f -> {
                    f.setPassword(null);
                });
                model.put("Companys", JSON.toJSONString(clientInfos));
                model.put("Account", Info.getUserName());
                model.put("username",Info.getUserName());
                model.put("HasLogin", 1);
                model.put("roleName", Info.getRoleName());
                return okUrl;
            } else {
                model.put("roots", new ArrayList<tbMenuList>());
                model.put("firsts", new ArrayList<tbMenuList>());
                model.put("HasLogin", 0);
                model.put("roleName", "");
                return failureUrl;
            }
        } catch (Exception ax) {
            return failureUrl;
        }
    }
}
