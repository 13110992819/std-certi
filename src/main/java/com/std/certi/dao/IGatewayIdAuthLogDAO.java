package com.std.certi.dao;

import com.std.certi.dao.base.IBaseDAO;
import com.std.certi.domain.GatewayIdAuthLog;

public interface IGatewayIdAuthLogDAO extends IBaseDAO<GatewayIdAuthLog> {
    String NAMESPACE = IGatewayIdAuthLogDAO.class.getName().concat(".");

    public int updateErrorInfo(GatewayIdAuthLog data);
}
