package com.zhide.govwatch.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PatentPermissionMapper {
    @Delete("DELETE FROM patentpermission WHERE SHENQINGH=#{SHENQINGH}")
    int delPatentPermission(String SHENQINGH);
}
