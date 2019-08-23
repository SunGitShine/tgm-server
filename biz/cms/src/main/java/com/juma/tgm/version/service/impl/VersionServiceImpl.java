/**
* @Title: VersionServiceImpl.java
* @Package com.juma.tgm.version.service.impl
*<B>Copyright</B> Copyright (c) 2016 www.jumapeisong.com All rights reserved. <br />
* 本软件源代码版权归驹马,未经许可不得任意复制与传播.<br />
* <B>Company</B> 驹马配送
* @date 2016年6月12日 上午10:30:39
* @version V1.0  
 */
package com.juma.tgm.version.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.giants.cache.redis.RedisClient;
import com.juma.tgm.base.domain.VersionCheck;
import com.juma.tgm.common.Constants;
import com.juma.tgm.version.dao.VersionDao;
import com.juma.tgm.version.domain.Version;
import com.juma.tgm.version.service.VersionService;

/**
 * @Description: 版本管理
 * @author Administrator
 * @date 2016年6月12日 上午10:30:39
 * @version V1.0
 */
@Service
public class VersionServiceImpl implements VersionService {

    @Resource
    private VersionDao versionDao;
    @Autowired
    private RedisClient redisClient;

    @Override
    public Version checkNewVersion(Version version) {
        List<Version> rows = versionDao.findByExample(version);
        return rows.size() > 0 ? rows.get(0) : null;
    }

    @Override
    public String checkVersion() {
        Serializable serializable = redisClient.get(Constants.VERSION_CHECK_KEY);
        if (null != serializable) {
            return String.valueOf(serializable);
        }
        return Constants.VERSIONCHECK;
    }

    @Override
    public void addNewVersion(VersionCheck version) {
        if (null != version && StringUtils.isNotBlank(version.getVersionCheck())) {
            redisClient.set(Constants.VERSION_CHECK_KEY, version.getVersionCheck());
        }
    }

    @Override
    public String findDefaultRegionCode(String key) {
        Serializable serializable = redisClient.get(key);
        if (null != serializable) {
            return String.valueOf(serializable);
        }
        return Constants.DEFAULT_FRIGHT_REGION_CODE;
    }

    @Override
    public void addDefaultRegionCode(String key, String regionCode) {
        if (StringUtils.isNotBlank(key) && StringUtils.isNotBlank(regionCode)) {
            redisClient.set(key, regionCode);
        }
    }

    @Override
    public String apkVersion() {
        Serializable serializable = redisClient.get(Constants.TMS_APK_VERSION_KEY);
        if (null != serializable) {
            return String.valueOf(serializable);
        }
        return "UNKNOW";
    }
}
