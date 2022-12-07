package com.zhide.govwatch.model;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

public class NBBHInfo {
    List<UInfo> KH;
    List<UInfo> CITY;
    List<UInfo> PARK;
    boolean decodeAll;
    Map<String, List<UInfo>> all = new HashMap<>();

    public NBBHInfo() {
        KH = new ArrayList<UInfo>();
        CITY = new ArrayList<UInfo>();
        PARK = new ArrayList<UInfo>();
        decodeAll = true;
    }

    public boolean isDecodeAll() {
        return decodeAll;
    }

    public void setDecodeAll(boolean decodeAll) {
        this.decodeAll = decodeAll;
    }

    public void foreach(BiConsumer<String, List<UInfo>> f) {

        all.put("KH", KH);
        all.put("CITY", CITY);
        all.put("PARK", PARK);
        all.forEach(f);
    }
    public Integer getSize(){
        return KH.size()+CITY.size()+ PARK.size();
    }
    public void SetValue(String Type, List<UInfo> us) {
        if (Type.equals("KH")) setKH(us);
        else if (Type.equals("CITY")) setCITY(us);
        else if (Type.equals("PARK")) setPARK(us);
    }

    public List<UInfo> getKH() {
        return KH;
    }

    public void setKH(List<UInfo> KH) {
        this.KH = KH;
    }

    public List<UInfo> getCITY() {
        return CITY;
    }

    public void setCITY(List<UInfo> CITY) {
        this.CITY = CITY;
    }

    public List<UInfo> getPARK() {
        return PARK;
    }

    public void setPARK(List<UInfo> PARK) {
        this.PARK = PARK;
    }
}
