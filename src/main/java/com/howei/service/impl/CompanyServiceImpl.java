package com.howei.service.impl;

import com.howei.mapper.CompanyMaper;
import com.howei.pojo.Company;
import com.howei.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CompanyServiceImpl  implements CompanyService {

    @Autowired
    CompanyMaper companyMaper;

    @Override
    public List<Company> getCompanyList(String parent) {
        return companyMaper.getCompanyList(parent);
    }

    @Override
    public Company getCompanyById(String id) {
        return companyMaper.getCompanyById(id);
    }

    @Override
    public void updateCompany(Company company) {
        companyMaper.updateCompany(company);
    }

    @Override
    public int addCompany(Company company) {
        return companyMaper.addCompany(company);
    }

    @Override
    public List<Company> getCodeNameList(String companyId) {
        return companyMaper.getCodeNameList(companyId);
    }

    @Override
    public Map<Integer, Object> getCompanyMap(String s) {
        List<Map<String, Object>> companyMap = companyMaper.getCompanyMap(s);
        Map<Integer,Object> result=new HashMap<>();
        for (Map<String,Object> map:companyMap) {
            Integer id=(int)map.get("id");
            result.put(id,map.get("name"));
        }
        return result;
    }

    @Override
    public List<Map<String, String>> getDepartmentList(String companyId) {
        List<Company> companyMap = companyMaper.getDepartmentList(companyId);
        List<Map<String, String>> result=new ArrayList<>();
        for (Company company:companyMap) {
            Map<String,String> map=new HashMap<>();
            map.put("id",company.getId()+"");
            map.put("name",company.getName());
            result.add(map);
        }
        return result;
    }

    @Override
    public Company getCompany(Company company) {
        return companyMaper.getCompany(company);
    }

    @Override
    public List<Map<String,String>> getDepartmentMap(int companyId) {
        List<Company> list=companyMaper.getDepartmentMap(companyId);
        List<Map<String,String>> result=new ArrayList<>();
        if(list!=null){
            for(Company company:list){
                Map map=new HashMap();
                map.put("id",company.getId());
                map.put("name",company.getName());
                result.add(map);
            }
        }
        return result;
    }

    @Override
    public List<Company> getAllDepartmentList() {
        return companyMaper.getAllDepartmentList();
    }

    @Override
    public List<Company> getLayIMDepShowMap(String companyId) {
        return companyMaper.getLayIMDepShowMap(companyId);
    }
}
