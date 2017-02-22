package com.xnjr.idAuth.dao;

import org.junit.Test;
import org.unitils.spring.annotation.SpringBeanByType;

import com.std.certi.dao.ICPasswordDAO;
import com.std.certi.domain.CPassword;

public class ICPasswordDAOTest extends ADAOTest {
    @SpringBeanByType
    private ICPasswordDAO cPasswordDAO;

    @Test
    public void insert() {
        CPassword data = new CPassword();
        data.setCode("code");
        data.setSystemCode("1");
        data.setCompanyCode("2");
        data.setType("1");
        data.setAccount("account");
        data.setPassword("password");
        data.setRemark("remark");
        cPasswordDAO.insert(data);
    }

}
