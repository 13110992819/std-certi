package com.std.certi.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.std.certi.dao.IGatewayIdAuthLogDAO;
import com.std.certi.dao.base.support.AMybatisTemplate;
import com.std.certi.domain.GatewayIdAuthLog;

@Repository("gatewayIdAuthLogDAOImpl")
public class GatewayIdAuthLogDAOImpl extends AMybatisTemplate implements
        IGatewayIdAuthLogDAO {

    @Override
    public int insert(GatewayIdAuthLog data) {
        return super.insert(NAMESPACE.concat("insert_gatewayIdAuthLog"), data);
    }

    @Override
    public int update(GatewayIdAuthLog data) {
        return 0;
    }

    @Override
    public int delete(GatewayIdAuthLog data) {
        return 0;
    }

    @Override
    public GatewayIdAuthLog select(GatewayIdAuthLog condition) {
        return (GatewayIdAuthLog) super.select(
            NAMESPACE.concat("select_gatewayIdAuthLog"), condition,
            GatewayIdAuthLog.class);
    }

    @Override
    public long selectTotalCount(GatewayIdAuthLog condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_gatewayIdAuthLog_count"), condition);
    }

    @Override
    public List<GatewayIdAuthLog> selectList(GatewayIdAuthLog condition) {
        return super.selectList(NAMESPACE.concat("select_gatewayIdAuthLog"),
            condition, GatewayIdAuthLog.class);
    }

    @Override
    public List<GatewayIdAuthLog> selectList(GatewayIdAuthLog condition,
            int start, int count) {
        return super.selectList(NAMESPACE.concat("select_gatewayIdAuthLog"),
            start, count, condition, GatewayIdAuthLog.class);
    }

}
