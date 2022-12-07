package com.zhide.govwatch.define;

import com.zhide.govwatch.model.MethodWatch;
import com.zhide.govwatch.model.patentMemo;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IPatentMemoService {
    List<String> getImages(String MID) throws Exception;
    boolean SaveAll(Object menuName, List<patentMemo> Infos) throws Exception;
    void  SaveImage(patentMemo memo) throws Exception;
    boolean RemoveAll(List<String> IDS) throws Exception;
}
