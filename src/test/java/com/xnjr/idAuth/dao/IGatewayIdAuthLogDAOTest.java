package com.xnjr.idAuth.dao;

import java.util.Date;

import org.junit.Test;
import org.unitils.spring.annotation.SpringBeanByType;

import com.std.certi.dao.IGatewayIdAuthLogDAO;
import com.std.certi.domain.GatewayIdAuthLog;

public class IGatewayIdAuthLogDAOTest extends ADAOTest {
    @SpringBeanByType
    private IGatewayIdAuthLogDAO gatewayIdAuthLogDAO;

    @Test
    public void insert() {
        GatewayIdAuthLog data = new GatewayIdAuthLog();
        data.setSystemCode("SystemCode");
        data.setCompanyCode("companyCode");
        data.setUserId("userId");
        data.setIdKind("1");
        data.setIdNo("idNo");
        data.setRealName("realName");
        data.setCardNo("cardNo");
        data.setBindMobile("bindMobile");
        data.setErrorCode("9113");
        data.setErrorMsg("ddd");
        data.setCreateDatetime(new Date());
        data.setRemark("remark");
        gatewayIdAuthLogDAO.insert(data);

    }

    @Test
    public void select() {
        GatewayIdAuthLog data = new GatewayIdAuthLog();
        data.setSystemCode("SystemCode");

        gatewayIdAuthLogDAO.selectList(data);
    }
}
