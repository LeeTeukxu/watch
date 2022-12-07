package com.zhide.govwatch.define;

import com.zhide.govwatch.model.patentarea;

import java.util.List;

/**
 * @ClassName: IPatentAreaService
 * @Author: 肖新民
 * @*TODO:
 * @CreateTime: 2022年02月21日 17:34
 **/
public interface IPatentAreaService {
    void saveAll(List<patentarea> areas);
    List<patentarea> getData();
}
