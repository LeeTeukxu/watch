package com.zhide.govwatch.imple;

import com.zhide.govwatch.common.RedisUtils;
import com.zhide.govwatch.define.ItbMenuListService;
import com.zhide.govwatch.define.ItbMenuPermissionService;
import com.zhide.govwatch.model.tbMenuList;
import com.zhide.govwatch.model.tbRoleFunctionSave;
import com.zhide.govwatch.repository.tbMenuListRepository;
import com.zhide.govwatch.repository.tbRoleFunctionSaveRepository;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class tbMenuListServiceImpl implements ItbMenuListService {
    @Autowired
    private tbMenuListRepository tbMenuListRepository;
    @Autowired
    ItbMenuPermissionService menuPermissionService;
    @Autowired
    tbRoleFunctionSaveRepository tbRoleFunSaveRep;
    @Autowired
    RedisUtils redisUtils;
    @Override
    public List<tbMenuList> getAll() {
        return tbMenuListRepository.findAll(Sort.by("Sn"));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<tbMenuList> saveAll(List<tbMenuList> tbMenuLists) {
        List<tbMenuList> res = new ArrayList<>();
        for (int i = 0; i < tbMenuLists.size(); i++) {
            tbMenuList tb = tbMenuLists.get(i);
            tbMenuList t = tbMenuListRepository.save(tb);
            res.add(t);
        }
        redisUtils.clearAll("Menu");
        return res;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeOne(List<Integer> Ids) {
        for (int i = 0; i < Ids.size(); i++) {
            int Id = Ids.get(i);
            tbMenuListRepository.deleteById(Id);
        }
        redisUtils.clearAll("Menu");
        return true;
    }

    @Cacheable("getAllCanUseMenu")
    public List<tbMenuList> getAllByCanUse() throws Exception {
        List<tbMenuList> menus = tbMenuListRepository.findAllByCanUseTrueOrderBySn();
        int count = menus.size();
        for (int i = 0; i < count; i++) {
            tbMenuList item = menus.get(i);
            String url = item.getUrl();
            if (StringUtils.isEmpty(url)) continue;
            if (url.indexOf("?") > -1) {
                String[] texts = url.split("\\?");
                String baseUrl = texts[0];
                String paramters = texts[1];
                String newParamter = urlDecode(paramters);
                item.setUrl(baseUrl + "?" + newParamter);
            }
        }
        return menus;
    }

    @Override
    @Cacheable(value = "VisibleMenu")
    public List<tbMenuList> getVisibleMenus(int roleId) {
        return getVisible(roleId);
    }
    private List<tbMenuList> getVisible(int roleId) {
        List<tbMenuList> res = new ArrayList<>();
        List<tbMenuList> menus = null;
        try {
            menus = getAllByCanUse();
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<tbRoleFunctionSave> allFuns = tbRoleFunSaveRep.findAllByRoleId(roleId);
        List<Integer> allMenus = allFuns.stream()
                .filter(f -> f.getFunId() == 3)
                .map(f -> f.getMenuId())
                .distinct().collect(Collectors.toList());
        if (roleId == 2) {
            List<Integer> noPages = menus.stream()
                    .filter(f -> StringUtils.isEmpty(f.getPageName()) && StringUtils.isEmpty(f.getUrl()) == false)
                    .map(x -> x.getFid())
                    .collect(Collectors.toList());
            allMenus.addAll(noPages);
        }
        for (int i = 0; i < allMenus.size(); i++) {
            Integer MenuID = allMenus.get(i);
            Optional<tbMenuList> Fx = menus.stream().filter(f -> f.getFid().equals(MenuID)).findFirst();
            if (Fx.isPresent()) {
                tbMenuList F = Fx.get();
                res.add(F);
                Optional<tbMenuList> FPS = menus.stream().filter(f -> f.getFid().equals(F.getPid())).findFirst();
                if (FPS.isPresent()) {
                    tbMenuList P = FPS.get();
                    List<tbMenuList> ps = res.stream().filter(f -> f.getFid().equals(P.getFid()))
                            .collect(Collectors.toList());
                    if (ps.size() == 0) {
                        res.add(P);
                    }
                }
            }
        }
        res.sort((a, b) -> Integer.compare(Integer.parseInt(a.getSn()), Integer.parseInt(b.getSn())));
        return res;
    }

    private String urlDecode(String paramText) throws UnsupportedEncodingException {
        String[] VS = paramText.split("&");
        List<String> ps = new ArrayList<>();
        for (int i = 0; i < VS.length; i++) {
            String V = VS[i];
            String[] values = V.split("=");
            String newText = values[0] + '=' + URLEncoder.encode(values[1], "utf-8");
            ps.add(newText);
        }
        return Strings.join(ps, '&');
    }
}
