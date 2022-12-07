package com.zhide.govwatch.mapper;

import com.zhide.govwatch.model.*;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface ClientInfoMapper {
    @Select(value = "Select ClientID as ID, upper(replace(Name,' ','')) as Name  from tbClient")
    List<Map<String, Object>> getAllByNameAndID();

    List<Map<String, Object>> getPageData(Map<String, Object> params);

    List<Map<String, Object>> getData(Map<String, Object> params);

    List<Map<String, Object>> getDataWindow(Map<String, Object> params);

    List<Map<String, Object>> getJSR(Map<String, Object> params);

    List<tbClient> getClientTree(Map<String, Object> params);

    List<Map<String, Object>> getLoginClientReords(Map<String, Object> arguments);

    @Delete("DELETE FROM tbClient WHERE ClientID=#{ClientID}")
    int delClient(int ClientID);

    @Delete("DELETE FROM tbClientLinkers WHERE ClientID=#{ClientID}")
    int delClientLinkers(int ClientID);

    @Delete("DELETE FROM FollowRecord WHERE ClientID=#{ClientID}")
    int delFollowRecord(int ClientID);
    tbClient findNameByName(@Param("name") String name);
    List<Map<String, String>> getAllClient();
    Integer addImportExcelReturnClientID(tbClient client);
    List<Map<String, Object>> getAllClientInfoExistLinkMan(Map<String, Object> args);
    int insertBatch(List<tbClient> datas);

    List<Map<String, Object>> getDataHasEmail(Map<String, Object> arguments);
}
