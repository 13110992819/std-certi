package com.std.certi.dao;

import com.std.certi.dao.base.IBaseDAO;
import com.std.certi.domain.CPassword;

public interface ICPasswordDAO extends IBaseDAO<CPassword> {
    String NAMESPACE = ICPasswordDAO.class.getName().concat(".");

    public int updateValue(String code, String password, String remark);
}
