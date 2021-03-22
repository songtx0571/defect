package com.howei.pojo;

/**
 * 缺陷单
 */
public class Defect {

    private Integer id;
    private Integer company;//公司
    private Integer createdBy;
    private Integer level;//缺陷等级
    private String created;
    private Integer type;//缺陷状态
    private Integer departmentId;//部门
    private String abs;//故障描述
    private String orderReceivingTime;//接单开始时间
    private String planedTime;//预估完成时间
    private String realSTime;//实际检修开启日期
    private String realETime;//实际检修结束日期
    private String bPlc;//消缺前图片路径
    private String aPlc;//消缺后图片路径
    private Integer claimant;//认领人
    private Integer confirmer1;//值班确认人
    private String confirmer1Time;//值班确认时间
    private Integer confirmer2;//甲方确认人
    private Integer sysId;//系统Id
    private Integer equipmentId;//设备Id
    private String feedbackAbs;//检修单故障描述
    private String situation;//缺陷情况
    private String method;//处理措施
    private String problem;//遗留问题
    private String remark;//备注
    private Integer completer;//反馈单填写人
    private String year;//年份
    private String number;//编号
    private Integer maintenanceCategory;// 检修类别;1：机务；2：电仪
    private String empIds;//执行人员Id


    //虚字段
    private String createdByName;//创建人
    private String departmentName;//部门
    private String companyName;//公司
    private String claimantName;//认领人
    private String confirmer1Name;//值班人
    private String confirmer2Name;//甲方确认人
    private String completerName;//反馈单填写人
    private String sysName;//系统名称
    private String equipmentName;//设备名称
    private String empIdsName;//执行人员名称
    private String aPlc64;
    private String bPlc64;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCompany() {
        return company;
    }

    public void setCompany(Integer company) {
        this.company = company;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getAbs() {
        return abs;
    }

    public void setAbs(String abs) {
        this.abs = abs;
    }

    public String getOrderReceivingTime() {
        return orderReceivingTime;
    }

    public void setOrderReceivingTime(String orderReceivingTime) {
        this.orderReceivingTime = orderReceivingTime;
    }

    public String getPlanedTime() {
        return planedTime;
    }

    public void setPlanedTime(String planedTime) {
        this.planedTime = planedTime;
    }

    public String getRealSTime() {
        return realSTime;
    }

    public void setRealSTime(String realSTime) {
        this.realSTime = realSTime;
    }

    public String getRealETime() {
        return realETime;
    }

    public void setRealETime(String realETime) {
        this.realETime = realETime;
    }

    public String getbPlc() {
        return bPlc;
    }

    public void setbPlc(String bPlc) {
        this.bPlc = bPlc;
    }

    public String getaPlc() {
        return aPlc;
    }

    public void setaPlc(String aPlc) {
        this.aPlc = aPlc;
    }

    public Integer getClaimant() {
        return claimant;
    }

    public void setClaimant(Integer claimant) {
        this.claimant = claimant;
    }

    public Integer getConfirmer1() {
        return confirmer1;
    }

    public void setConfirmer1(Integer confirmer1) {
        this.confirmer1 = confirmer1;
    }

    public Integer getConfirmer2() {
        return confirmer2;
    }

    public void setConfirmer2(Integer confirmer2) {
        this.confirmer2 = confirmer2;
    }

    public String getFeedbackAbs() {
        return feedbackAbs;
    }

    public void setFeedbackAbs(String feedbackAbs) {
        this.feedbackAbs = feedbackAbs;
    }

    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getCompleter() {
        return completer;
    }

    public void setCompleter(Integer completer) {
        this.completer = completer;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCreatedByName() {
        return createdByName;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getClaimantName() {
        return claimantName;
    }

    public void setClaimantName(String claimantName) {
        this.claimantName = claimantName;
    }

    public String getConfirmer1Name() {
        return confirmer1Name;
    }

    public void setConfirmer1Name(String confirmer1Name) {
        this.confirmer1Name = confirmer1Name;
    }

    public String getConfirmer2Name() {
        return confirmer2Name;
    }

    public void setConfirmer2Name(String confirmer2Name) {
        this.confirmer2Name = confirmer2Name;
    }

    public String getCompleterName() {
        return completerName;
    }

    public void setCompleterName(String completerName) {
        this.completerName = completerName;
    }

    public Integer getSysId() {
        return sysId;
    }

    public void setSysId(Integer sysId) {
        this.sysId = sysId;
    }

    public Integer getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(Integer equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getSysName() {
        return sysName;
    }

    public void setSysName(String sysName) {
        this.sysName = sysName;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public Integer getMaintenanceCategory() {
        return maintenanceCategory;
    }

    public void setMaintenanceCategory(Integer maintenanceCategory) {
        this.maintenanceCategory = maintenanceCategory;
    }

    public String getEmpIds() {
        return empIds;
    }

    public void setEmpIds(String empIds) {
        this.empIds = empIds;
    }

    public String getEmpIdsName() {
        return empIdsName;
    }

    public void setEmpIdsName(String empIdsName) {
        this.empIdsName = empIdsName;
    }

    public String getaPlc64() {
        return aPlc64;
    }

    public void setaPlc64(String aPlc64) {
        this.aPlc64 = aPlc64;
    }

    public String getbPlc64() {
        return bPlc64;
    }

    public void setbPlc64(String bPlc64) {
        this.bPlc64 = bPlc64;
    }

    public String getConfirmer1Time() {
        return confirmer1Time;
    }

    public void setConfirmer1Time(String confirmer1Time) {
        this.confirmer1Time = confirmer1Time;
    }
}
