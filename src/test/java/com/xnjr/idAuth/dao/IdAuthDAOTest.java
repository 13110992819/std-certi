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
        data.setCode("code");
        data.setIdKind("1");
        data.setIdNo("idNo");
        data.setRealName("realName");
        data.setCardNo("cardno");
        data.setBindMobile("bindMobile");
        data.setCreateDatetime(new Date());
        tjcIdAuthDAO.insert(data);
    }

    @Test
    public void select() {
        IdAuth data = new IdAuth();
        data.setCode("code");

        tjcIdAuthDAO.select(data);
    }
}
