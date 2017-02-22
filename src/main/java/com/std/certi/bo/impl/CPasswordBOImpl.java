package com.std.certi.bo.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.std.certi.bo.ICPasswordBO;
import com.std.certi.bo.base.PaginableBOImpl;
import com.std.certi.dao.ICPasswordDAO;
import com.std.certi.domain.CPassword;

@Component
public class CPasswordBOImpl extends PaginableBOImpl<CPassword> implements
        ICPasswordBO {

    @Autowired
    private ICPasswordDAO cPasswordDAO;

    @Override
    public Map<String, String> queryCPasswordMap(String systemCode,
            String companyCode, String type) {
        Map<String, String> resultMap = new HashMap<String, String>();
        CPassword condition = new CPassword();
        condition.setSystemCode(systemCode);
        condition.setCompanyCode(companyCode);
        condition.setType(type);
        List<CPassword> list = cPasswordDAO.selectList(condition);
        for (CPassword ele : list) {
            resultMap.put(ele.getAccount(), ele.getPassword());
        }
        return resultMap;
    }

}
