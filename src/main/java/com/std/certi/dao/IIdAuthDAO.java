package com.std.certi.dao;

import com.std.certi.dao.base.IBaseDAO;
import com.std.certi.domain.IdAuth;

public interface IIdAuthDAO extends IBaseDAO<IdAuth> {
    String NAMESPACE = IIdAuthDAO.class.getName().concat(".");
}
