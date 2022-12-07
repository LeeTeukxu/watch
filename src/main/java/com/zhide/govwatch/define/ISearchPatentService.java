package com.zhide.govwatch.define;

import com.google.common.collect.Lists;
import com.zhide.govwatch.common.pageObject;
import com.zhide.govwatch.model.ComboboxItem;
import com.zhide.govwatch.model.patent;
import com.zhide.govwatch.model.patentElInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @ClassName: ISearchPatentService
 * @Author: 肖新民
 * @*TODO:
 * @CreateTime: 2021年12月22日 9:40
 **/
public interface ISearchPatentService {
    pageObject simpleSearch(String Fields,String Code, Pageable pageable) throws Exception;
    pageObject complexSearch(String words,Pageable pageable) throws Exception;
    pageObject simpleAnnualSearch(String Fields, String Code, Pageable pageable) throws Exception;
    List<String> ParameterSearchCount(String Field, String words, String SearchPage) throws Exception;
    List<ComboboxItem> GetDistinctLAWSTATUS();
    List<ComboboxItem> GetDistinctSECLAWSTATUS();
}
