/**
* @Title: VersionService.java
* @Package com.juma.tgm.version.service
*<B>Copyright</B> Copyright (c) 2016 www.jumapeisong.com All rights reserved. <br />
* 本软件源代码版权归驹马,未经许可不得任意复制与传播.<br />
* <B>Company</B> 驹马配送
* @date 2016年5月31日 上午11:21:12
* @version V1.0  
 */
package com.juma.tgm.version.service;

import com.juma.tgm.base.domain.VersionCheck;
import com.juma.tgm.version.domain.Version;

/**
 * @Description: 版本管理
 * @author Administrator
 * @date 2016年5月31日 上午11:21:12
 * @version V1.0
 */
public interface VersionService {

    /**
     * 
     * @Title: apkVersion   
     * @Description: 版本号
     * @param: @return      
     * @return: String      
     * @throws
     */
    String apkVersion();
    
    Version checkNewVersion(Version version);

    /**
     * 检查版本，决策是否要求重新登录
     * 
     * @return
     */
    String checkVersion();

    /**
     * 当权限变更时，配置新的版本，强制重新登录
     * 
     * @param version 新版本号
     * @return
     */
    void addNewVersion(VersionCheck version);

    /**
     * 
     * @Description 获取默认运费规则城市编码
     * @author Libin.Wei
     * @Date 2017年3月1日 下午4:37:36
     * @param key redis的KEY值
     * @return
     */
    String findDefaultRegionCode(String key);

    /**
     * 
     * @Description 添加默认运费规则城市编码
     * @author Libin.Wei
     * @Date 2017年3月1日 下午4:40:01
     * @param key redis的KEY值
     * @param regionCode 城市编码
     */
    void addDefaultRegionCode(String key, String regionCode);
}
