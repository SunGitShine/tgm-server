/**
* @Title: Version.java
* @Package com.juma.tgm.version.domain
*<B>Copyright</B> Copyright (c) 2016 www.jumapeisong.com All rights reserved. <br />
* 本软件源代码版权归驹马,未经许可不得任意复制与传播.<br />
* <B>Company</B> 驹马配送
* @date 2016年5月31日 上午11:02:59
* @version V1.0  
 */
package com.juma.tgm.version.domain;

import com.juma.tgm.base.domain.BaseDomain;

/**
 *@Description: 版本管理
 *@author Administrator
 *@date 2016年5月31日 上午11:02:59
 *@version V1.0  
 */
public class Version extends BaseDomain {

    private static final long serialVersionUID = 3515886691341495804L;

    private Integer versionId;
	
	private String versionName;
	
	private Integer versionCode;
	
	private String packageName;
	
	private String platform;
	
	private String downloadUrl;
	
	private String remark;

	public Integer getVersionId() {
		return versionId;
	}

	public void setVersionId(Integer versionId) {
		this.versionId = versionId;
	}

	public String getVersionName() {
		return versionName;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}


	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getDownloadUrl() {
		return downloadUrl;
	}

	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getVersionCode() {
		return versionCode;
	}

	public void setVersionCode(Integer versionCode) {
		this.versionCode = versionCode;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	
	
}
