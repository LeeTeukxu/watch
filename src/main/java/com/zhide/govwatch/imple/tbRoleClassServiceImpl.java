package com.zhide.govwatch.imple;

import com.zhide.govwatch.common.RedisUtils;
import com.zhide.govwatch.define.ItbRoleClassService;
import com.zhide.govwatch.model.TreeListItem;
import com.zhide.govwatch.model.tbRoleClass;
import com.zhide.govwatch.model.tbRoleFunctionSave;
import com.zhide.govwatch.repository.tbRoleClassRepository;
import com.zhide.govwatch.repository.tbRoleFunctionSaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class tbRoleClassServiceImpl implements ItbRoleClassService {

    @Autowired
    tbRoleClassRepository tbRoleClassRepository;
    @Autowired
    tbRoleFunctionSaveRepository roleFunRep;
    @Autowired
    RedisUtils redisUtils;
    @Override
    @Cacheable(value = "getAllRolesList")
    public List<tbRoleClass> getAll() {
        return tbRoleClassRepository.findAll();
    }

    @Override
//    @Cacheable(value = "getAllCanUseRoles", keyGenerator = "CompanyKeyGenerator")
    @Cacheable(value = "getAllCanUseRoles")
    public List<TreeListItem> getAllCanuseItems(boolean canUse) {
        List<tbRoleClass> rs = tbRoleClassRepository.findAllByCanUseTrue();
        List<TreeListItem> items = new ArrayList<>();
        for (int i = 0; i < rs.size(); i++) {
            tbRoleClass c = rs.get(i);
            boolean exists = items.stream().anyMatch(f -> f.getId().equals(Integer.toString(c.getRoleId())));
            if (exists == false) {
                TreeListItem item = new TreeListItem();
                item.setId(Integer.toString(c.getRoleId()));
                item.setPid(Integer.toString(c.getPid()));
                item.setText(c.getRoleName());
                items.add(item);
            }
        }
        return items;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveAll(List<tbRoleClass> items) {
        for (int i = 0; i < items.size(); i++) {
            tbRoleClass item = items.get(i);
            tbRoleClassRepository.save(item);
        }
        redisUtils.clearAll("Role");
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeAll(List<Integer> ids) {
        for (int i = 0; i < ids.size(); i++) {
            Integer id = ids.get(i);
            Optional<tbRoleClass> tbs=tbRoleClassRepository.findById(id);
            if(tbs.isPresent()) {
                List<tbRoleFunctionSave> Fs = roleFunRep.findAllByRoleId(id);
                if (Fs.size() > 0) throw new RuntimeException(tbs.get().getRoleName()+"还存在权限数据，无法删除!");
                tbRoleClassRepository.deleteById(id);
            }
        }
        redisUtils.clearAll("Role");
        return true;
    }
}
