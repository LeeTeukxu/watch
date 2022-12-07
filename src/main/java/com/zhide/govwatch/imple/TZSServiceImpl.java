package com.zhide.govwatch.imple;

import com.zhide.govwatch.define.ITZSService;
import com.zhide.govwatch.model.ComboboxItem;
import com.zhide.govwatch.model.TZS;
import com.zhide.govwatch.repository.TZSRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TZSServiceImpl implements ITZSService {

    @Autowired
    TZSRepository tzsRep;

    @Override
    public List<ComboboxItem> getItemsByCode(String[] codes) {
        List<ComboboxItem> result = new ArrayList<ComboboxItem>();
        List<TZS> tzsList = tzsRep.findAllByTongzhisbhIn(codes);
        for (int i = 0; i < tzsList.size(); i++) {
            TZS tzs = tzsList.get(i);
            ComboboxItem item = new ComboboxItem();
            item.setId(tzs.getTzspath());
            item.setText(tzs.getFamingmc() + "(" + tzs.getTongzhismc()+")");
            result.add(item);
        }
        return result;
    }
}
