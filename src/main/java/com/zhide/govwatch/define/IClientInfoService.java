package com.zhide.govwatch.define;

import com.zhide.govwatch.common.pageObject;
import com.zhide.govwatch.model.*;
import org.springframework.data.domain.Page;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface IClientInfoService {
    Map<String, String> getAllByNameAndID() throws Exception;

    Map<String, String> getAllByIDAndName() throws Exception;

    pageObject getPageData(HttpServletRequest request) throws Exception;

    pageObject getData(HttpServletRequest request) throws Exception;

    List<Map<String, Object>> getJSR(int ClientID,int UserID,int DepID,String RoleName);

    ClientAndClientLinkers Save(tbClient client, tbClientLinkers clientLinkers, String mode) throws Exception;

    followRecord SaveFollowRecord(followRecord followRecord) throws Exception;

    Page<followRecord> getFollowRecords(int ClientID, int pageIndex, int pageSize);

    tbClientLinkers SaveLinkers(tbClientLinkers linkers) throws Exception;

    Page<tbClientLinkers> getLinkers(int ClientID, int pageIndex, int pageSize);

    Page<tbLinkersUpdateRecord> getLinkersUpdateRecord(int ClientID, int pageIndex, int pageSize);

    pageObject GetLoginClientReords(int ClientID, HttpServletRequest request) throws Exception;

    boolean remove(List<String> ids);

    tbClient findNameByName(String Name);

    List<Map<String, Object>> getAllClientInfoExistLinkMan(HttpServletRequest request) throws Exception;

    void SaveImageFollowRecord(followRecord record) throws Exception;

    void UpdatePID(String Code) throws Exception;
}
