package com.zhide.govwatch.controller.system;

import com.zhide.govwatch.model.ComboboxItem;
import com.zhide.govwatch.model.TreeListItem;
import com.zhide.govwatch.define.ItbDictDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/system/dict")
public class tbDictDataController {
    @Autowired
    ItbDictDataService itbDictDataService;

    @ResponseBody
    @RequestMapping("/getAllByDtId")
    public List<TreeListItem> getAllByDtId(int dtId) {
        return itbDictDataService.getAllByDtID(dtId);
    }

    @RequestMapping("/getByDtId")
    @ResponseBody
    public List<ComboboxItem> getByDtId(int dtId) {
        return itbDictDataService.getByDtId(dtId);
    }
}
