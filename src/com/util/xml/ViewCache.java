package com.util.xml;

import java.util.ArrayList;
import java.util.List;

public class ViewCache {
	private String name;
    private List areaList= new ArrayList();
    public List getAreaList() {
        return areaList;
    }
    public void setAreaList(List areaList) {
        this.areaList = areaList;
    }
    
    // 供Digester调用的方法
    public void addArea(Area area) {
        this.areaList.add(area);
    }
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}