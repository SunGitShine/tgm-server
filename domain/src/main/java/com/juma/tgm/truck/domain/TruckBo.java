package com.juma.tgm.truck.domain;

import java.util.List;

import com.juma.tgm.base.domain.RegionBo;

public class TruckBo extends Truck {

    private static final long serialVersionUID = 2262350170858365586L;
    private Truck truck;
    /** 区域 */
    private String region;
    /** 签约公司 */
    private String signingCompany;
    /** 车型名称 */
    private String truckTypeName;
    /** 车队名称 */
    private String truckFleetName;
    /** 司机姓名 */
    private String nickname;
    /** 司机电话 */
    private String driverPhone;
    /** 司机评分 */
    private String driverScore;
    /** 附加功能 */
    private String additionalFunction;
    /** 附加功能名称 */
    private String functionNames;
    /** 车型信息 */
    private TruckType truckTypeInfo;
    /** 附加功能 */
    private List<String> funNameList;
    /** 入城证开始有效时间 */
    private String ValidStartDate;
    /** 入城证结束有效时间 */
    private String ValidEndDate;
    /** 停放区域的回显 */
    private RegionBo regionBo;
    /** 停放地 */
    private String parkingTown;
    /** 创建时间 */
    private String createDate;
    /** 入城证 */
    private String entryLicenseStr;
    /** 尾板*/
    private String tailBoardStr;

    public Truck getTruck() {
        return truck;
    }

    public void setTruck(Truck truck) {
        this.truck = truck;
    }

    public String getParkingTown() {
		return parkingTown;
	}

	public void setParkingTown(String parkingTown) {
		this.parkingTown = parkingTown;
	}

	public RegionBo getRegionBo() {
		return regionBo;
	}

	public void setRegionBo(RegionBo regionBo) {
		this.regionBo = regionBo;
	}
    
    public String getValidStartDate() {
  		return ValidStartDate;
  	}

  	public void setValidStartDate(String validStartDate) {
  		ValidStartDate = validStartDate;
  	}

  	public String getValidEndDate() {
  		return ValidEndDate;
  	}

  	public void setValidEndDate(String validEndDate) {
  		ValidEndDate = validEndDate;
  	}
    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSigningCompany() {
        return signingCompany;
    }

    public void setSigningCompany(String signingCompany) {
        this.signingCompany = signingCompany;
    }

    public String getTruckTypeName() {
        return truckTypeName;
    }

    public void setTruckTypeName(String truckTypeName) {
        this.truckTypeName = truckTypeName;
    }

    public String getTruckFleetName() {
        return truckFleetName;
    }

    public void setTruckFleetName(String truckFleetName) {
        this.truckFleetName = truckFleetName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getDriverPhone() {
        return driverPhone;
    }

    public void setDriverPhone(String driverPhone) {
        this.driverPhone = driverPhone;
    }

    public String getDriverScore() {
        return driverScore;
    }

    public void setDriverScore(String driverScore) {
        this.driverScore = driverScore;
    }

    public String getAdditionalFunction() {
        return additionalFunction;
    }

    public void setAdditionalFunction(String additionalFunction) {
        this.additionalFunction = additionalFunction;
    }

    public String getFunctionNames() {
        return functionNames;
    }

    public void setFunctionNames(String functionNames) {
        this.functionNames = functionNames;
    }

    public TruckType getTruckTypeInfo() {
        return truckTypeInfo;
    }

    public void setTruckTypeInfo(TruckType truckTypeInfo) {
        this.truckTypeInfo = truckTypeInfo;
    }

    public List<String> getFunNameList() {
        return funNameList;
    }

    public void setFunNameList(List<String> funNameList) {
        this.funNameList = funNameList;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getEntryLicenseStr() {
        return entryLicenseStr;
    }

    public void setEntryLicenseStr(String entryLicenseStr) {
        this.entryLicenseStr = entryLicenseStr;
    }

    public String getTailBoardStr() {
        return tailBoardStr;
    }

    public void setTailBoardStr(String tailBoardStr) {
        this.tailBoardStr = tailBoardStr;
    }

}
