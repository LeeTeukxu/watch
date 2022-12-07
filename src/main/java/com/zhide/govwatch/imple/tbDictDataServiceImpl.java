package com.zhide.govwatch.imple;

import com.zhide.govwatch.define.ItbDictDataService;
import com.zhide.govwatch.model.ComboboxItem;
import com.zhide.govwatch.model.TreeListItem;
import com.zhide.govwatch.model.tbDictData;
import com.zhide.govwatch.repository.tbDictDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class tbDictDataServiceImpl implements ItbDictDataService {
    @Autowired
    tbDictDataRepository tbDictDataRepository;

    @Override
    public List<TreeListItem> getAllByDtID(int dtId) {
        List<TreeListItem> results = new ArrayList<>();
        List<tbDictData> items = tbDictDataRepository.findAllByDtIdAndCanUseTrueOrderBySn(dtId);
        items.stream().forEach(f -> {
            TreeListItem item = new TreeListItem();
            item.setPid(Integer.toString(f.getPid()));
            item.setId(Integer.toString(f.getFid()));
            item.setText(f.getName());
            results.add(item);
        });
        return results;
    }

    @Cacheable(value = "getByDtId")
    public List<ComboboxItem> getByDtId(int dtId) {
        List<ComboboxItem> results = new ArrayList<>();
        List<tbDictData> items = tbDictDataRepository.findAllByDtIdAndCanUseTrueOrderBySn(dtId);
        items.stream().forEach(f -> {
            ComboboxItem item = new ComboboxItem();
            item.setId(Integer.toString(f.getFid()));
            item.setText(f.getName());
            results.add(item);
        });
        return results;
    }

    @Cacheable(value = "getByDtIdAndCanUseTrue")
    public List<ComboboxItem> getByDtIdAndCanUseTrue(int dtId){
        List<ComboboxItem> results = new ArrayList<>();
        List<tbDictData> items = tbDictDataRepository.findAllByDtIdAndCanUseTrue(dtId);
        items.stream().forEach(f -> {
            ComboboxItem item = new ComboboxItem();
            item.setId(Integer.toString(f.getFid()));
            item.setText(f.getName());
            results.add(item);
        });
        return results;
    }
}
