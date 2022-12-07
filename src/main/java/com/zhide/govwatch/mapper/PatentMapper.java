package com.zhide.govwatch.mapper;

import com.zhide.govwatch.model.patent;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: PatentMapper
 * @Author: 肖新民
 * @*TODO:
 * @CreateTime: 2022年01月07日 10:06
 **/
public interface PatentMapper {
    int insertBatch(List<patent> datas);

    int updateBatch(List<patent> items);
    int update(patent items);
    int deleteBatch(List<String> ids);
    /**
     * create by: mmzs
     * description: TODO
     * create time:
     *
     *
     专利信息中申请人地址为空的列表。
     * @return
     */
    List<Map<String,Object>>getEmptyAreaPatent();
    @Select(value = "Select Distinct Shenqingrxm from Patent Where ProvinceID is null and LOCATE('公司',SHENQINGRXM)>0 limit 1")
    String  getAllProvinceIsNull();
    List<patent> getAll(Integer Begin,Integer End);
}
