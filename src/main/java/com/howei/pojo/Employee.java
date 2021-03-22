package com.howei.pojo;


//员工信息
public class Employee {
    private int id;
    private int Userid;
    private String UserName;
    private String Name;
    private String Idnumber;
    private String Card;//银行卡
    private String Phone;
    private String Closhe;//衣服尺寸
    private String Hat;//安全帽编号
    private String Laowupaiqian;
    private String Education;//学历
    private String Credentials1;
    private String Credentials2;
    private String Credentials3;
    private String Post;
    private String date;
    private int State;
    private String Sex;
    private int Department;
    private String DepartmentName;
    private String Poision;
    private String Emergency;//应急联系人
    private String EmergencyTel;//应急手机
    private String Remark;//备注
    private String Wages;//待遇标准
    private String Basicwages;//基本工资
    private String Meritpay;//绩效工资
    private String Manager;
    private String projectId;
    private String address;//住址
    private String bank;//开户行
    private int roleId;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getManager() {
        return Manager;
    }

    public void setManager(String manager) {
        Manager = manager;
    }

    public int getUserid() {
        return Userid;
    }

    public void setUserid(int userId) {
        Userid = userId;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        Sex = sex;
    }

    public String getPoision() {
        return Poision;
    }

    public void setPoision(String poision) {
        Poision = poision;
    }

    public String getEmergency() {
        return Emergency;
    }

    public void setEmergency(String emergency) {
        Emergency = emergency;
    }

    public String getEmergencyTel() {
        return EmergencyTel;
    }

    public void setEmergencyTel(String emergencyTel) {
        EmergencyTel = emergencyTel;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public String getWages() {
        return Wages;
    }

    public void setWages(String wages) {
        Wages = wages;
    }

    public String getBasicwages() {
        return Basicwages;
    }

    public void setBasicwages(String basicwages) {
        Basicwages = basicwages;
    }

    public String getMeritpay() {
        return Meritpay;
    }

    public void setMeritpay(String meritpay) {
        Meritpay = meritpay;
    }

    public int getDepartment() {
        return Department;
    }

    public void setDepartment(int department) {
        Department = department;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getIdnumber() {
        return Idnumber;
    }

    public void setIdnumber(String idnumber) {
        Idnumber = idnumber;
    }

    public String getCard() {
        return Card;
    }

    public void setCard(String card) {
        Card = card;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getCloshe() {
        return Closhe;
    }

    public void setCloshe(String closhe) {
        Closhe = closhe;
    }

    public String getHat() {
        return Hat;
    }

    public void setHat(String hat) {
        Hat = hat;
    }

    public String getLaowupaiqian() {
        return Laowupaiqian;
    }

    public void setLaowupaiqian(String laowupaiqian) {
        Laowupaiqian = laowupaiqian;
    }

    public String getEducation() {
        return Education;
    }

    public void setEducation(String education) {
        Education = education;
    }

    public String getCredentials1() {
        return Credentials1;
    }

    public void setCredentials1(String credentials1) {
        Credentials1 = credentials1;
    }

    public String getCredentials2() {
        return Credentials2;
    }

    public void setCredentials2(String credentials2) {
        Credentials2 = credentials2;
    }

    public String getCredentials3() {
        return Credentials3;
    }

    public void setCredentials3(String credentials3) {
        Credentials3 = credentials3;
    }

    public String getPost() {
        return Post;
    }

    public void setPost(String post) {
        Post = post;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getState() {
        return State;
    }

    public void setState(int state) {
        State = state;
    }


    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getDepartmentName() {
        return DepartmentName;
    }

    public void setDepartmentName(String DepartmentName) {
        this.DepartmentName = DepartmentName;
    }

    public String[] getExcelRow() {
        String[] row = new String[]{
                this.UserName, this.Name, this.Department + "", this.Idnumber, this.Card, this.Phone, this.Closhe, this.Hat
                , this.Emergency, this.EmergencyTel, this.Laowupaiqian, this.Education, this.Credentials1, this.Credentials2
                , this.Credentials3, this.Post, this.date, this.Remark, this.Wages, this.Basicwages, this.Meritpay
        };
        return row;
    }
}
