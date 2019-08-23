package com.juma.tgm.gaode.domain;

import java.io.Serializable;

/**
 * @ClassName BusinessAreas.java
 * @Description 所属兴趣点
 * @author Libin.Wei
 * @Date 2017年5月3日 上午11:58:50
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public class Aois implements Serializable {

    private static final long serialVersionUID = 6268854434679355333L;
    /** 商圈中心点经纬度 */
    private String id;
    /** 商圈名称 */
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
