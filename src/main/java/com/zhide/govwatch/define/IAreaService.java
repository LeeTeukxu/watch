package com.zhide.govwatch.define;

import com.zhide.govwatch.model.tbarea;

import java.util.List;

public interface IAreaService {
    List<tbarea> getAll();

    List<tbarea> getProvinces();

    List<tbarea> getCitys(List<Integer> provinces);

    List<tbarea> getCoutys(List<Integer> citys);
}
