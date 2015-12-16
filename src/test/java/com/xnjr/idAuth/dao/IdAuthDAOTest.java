package com.xnjr.idAuth.dao;

import java.util.Date;

import org.junit.Test;
import org.unitils.spring.annotation.SpringBeanByType;

import com.std.certi.dao.IIdAuthDAO;
import com.std.certi.domain.IdAuth;

public class IdAuthDAOTest extends ADAOTest {
    @SpringBeanByType
    private IIdAuthDAO tjcIdAuthDAO;

    @Test
    public void insert() {
    	IdAuth data = new IdAuth();
        data.setSystemId("1");
        data.setUserId("userId");
        data.setIdKind("1");
        data.setIdNo("idNo");
        data.setRealName("realName");
        data.setCreateDatetime(new Date());
    }
}
