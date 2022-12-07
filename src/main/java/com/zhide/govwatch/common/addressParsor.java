package com.zhide.govwatch.common;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Charsets;
import com.zhide.govwatch.model.addressInfo;
import com.zhide.govwatch.vo.addressNode;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName: AddressParsor
 * @Author: 肖新民
 * @*TODO:根据地址字符串解析省市县
 * @CreateTime: 2021年12月08日 9:43
 **/

public class addressParsor {
    List<addressNode> addressList=new ArrayList<>();
    List<addressNode> allNodes=new ArrayList<>();
    public  addressParsor(){
        try {
            InputStream in = addressParsor.class.getClassLoader().getResourceAsStream("address.json");
            byte[] Bs=new byte[in.available()];
            in.read(Bs);
            in.close();
            String X=new String(Bs, Charsets.UTF_8);
            addressList= JSON.parseArray(X,addressNode.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public List<addressNode> getAll(){
        return addressList;
    }
    public Integer getIdByName(String Name){
       Optional<addressNode> finds= allNodes.stream().filter(f->f.getName().equals(Name)).findFirst();
       if(finds.isPresent()) return Integer.parseInt(finds.get().getCode()) ;else return 0;
    }
    public addressInfo getDetail(String address){
        addressInfo add=new addressInfo();
        address=address.trim();
        List<addressNode> citys=new ArrayList<>();
        List<addressNode> countys=new ArrayList<>();
        for(int i=1;i<address.length();i++){
            String shen=address.substring(0,i);
            Optional<addressNode> findShens=addressList.stream().filter(f->f.getName().equals(shen)).findFirst();
            if(findShens.isPresent()){
                addressNode sNode= findShens.get();
                add.setProvince(sNode.getName());
                allNodes.add(sNode);
                address=address.replace(shen,"");
                citys=sNode.getChildren();
                if(citys==null)citys=new ArrayList<>();
                break;
            }
        }
        if(citys.size()>0) {
            for (int i = 0; i < address.length(); i++) {
                String city =address.substring(0,i);
                Optional<addressNode> findCitys=citys.stream().filter(f->f.getName().equals(city)).findFirst();
                if(findCitys.isPresent()){
                    addressNode sNode= findCitys.get();
                    allNodes.add(sNode);
                    add.setCity(sNode.getName());
                    address=address.replace(city,"");
                    countys=sNode.getChildren();
                    if(countys==null) countys=new ArrayList<>();
                    break;
                }
            }
        }
        if(countys.size()>0) {
            for (int i = 0; i < address.length(); i++) {
                String county =address.substring(0,i);
                Optional<addressNode> findCountys=countys.stream().filter(f->f.getName().equals(county)).findFirst();
                if(findCountys.isPresent()==false){
                    if(county.length()>=2){
                        findCountys=countys.stream().filter(f->f.getName().startsWith(county)).findFirst();
                    }
                }
                if(findCountys.isPresent()){
                    addressNode sNode= findCountys.get();
                    allNodes.add(sNode);
                    add.setCounty(sNode.getName());
                    break;
                }
            }
        }
        return add;
    }
}
