package com.std.certi.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.std.certi.bo.IIdAuthBO;
import com.std.certi.dao.IIdAuthDAO;
import com.std.certi.domain.IdAuth;

@Component
public class IdAuthBOImpl implements IIdAuthBO {
    static Logger logger = Logger.getLogger(IdAuthBOImpl.class);

    @Autowired
    private IIdAuthDAO idAuthDAO;

    @Override
    public void doSave(String idKind, String idNo, String realName,
            String cardNo, String bindMobile) {
        IdAuth data = new IdAuth();
        data.setIdKind(idKind);
        data.setIdNo(idNo);
        data.setRealName(realName);
        data.setCardNo(cardNo);
        data.setBindMobile(bindMobile);
        data.setCreateDatetime(new Date());
        idAuthDAO.insert(data);
    }

    @Override
    public IdAuth doGet(String idKind, String idNo, String realName,
            String cardNo, String bindMobile) {
        IdAuth result = null;
        if (StringUtils.isNotBlank(idKind) && StringUtils.isNotBlank(idNo)
                && StringUtils.isNotBlank(realName)) {
            IdAuth condition = new IdAuth();
            condition.setIdKind(idKind);
            condition.setIdNo(idNo);
            condition.setRealName(realName);
            condition.setCardNo(cardNo);
            condition.setBindMobile(bindMobile);
            List<IdAuth> list = idAuthDAO.selectList(condition);
            if (CollectionUtils.isNotEmpty(list)) {
                result = list.get(0);
            }
        }
        return result;
    }
}
