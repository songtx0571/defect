package com.howei.mapper;

import com.howei.pojo.Company;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface CompanyMaper {

    List<Company> getCompanyList(@Param("parent") String parent);

    Company getCompanyById(String id);

    void updateCompany(Company company);

    int addCompany(Company company);

    List<Company> getCodeNameList(String companyId);

    List<Map<String,Object>> getCompanyMap(@Param("parent") String s);

    List<Company> getDepartmentList(@Param("parent") String companyId);

    Company getCompany(Company company);

    List<Company> getDepartmentMap(@Param("parent") int parent);

    List<Company> getAllDepartmentList();

    List<Company> getLayIMDepShowMap(@Param("parent") String companyId);
}
