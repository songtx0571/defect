package com.howei.service;

import com.howei.pojo.Company;

import java.util.List;
import java.util.Map;

public interface CompanyService {
    List<Company> getCompanyList(String parent);

    Company getCompanyById(String id);

    void updateCompany(Company company);

    int addCompany(Company company);

    List<Company> getCodeNameList(String companyId);

    Map<Integer,Object> getCompanyMap(String s);

    List<Map<String,String>> getDepartmentList(String companyId);

    Company getCompany(Company company);

    List<Map<String,String>> getDepartmentMap(int companyId);

    List<Company> getAllDepartmentList();

    List<Company> getLayIMDepShowMap(String companyId);

}
