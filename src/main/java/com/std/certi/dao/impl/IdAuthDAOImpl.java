package com.std.certi.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.std.certi.dao.IIdAuthDAO;
import com.std.certi.dao.base.support.AMybatisTemplate;
import com.std.certi.domain.IdAuth;

@Repository("idAuthDAOImpl")
public class IdAuthDAOImpl extends AMybatisTemplate implements IIdAuthDAO {

    @Override
    public int insert(IdAuth data) {
        return super.insert(NAMESPACE.concat("insert_tjcIdAuth"), data);
    }

    @Override
    public int update(IdAuth data) {
        return 0;
    }

    @Override
    public int delete(IdAuth data) {
        return 0;
    }

    @Override
    public IdAuth select(IdAuth condition) {
        return (IdAuth) super.select(NAMESPACE.concat("select_tjcIdAuth"),
            condition, IdAuth.class);
    }

    @Override
    public long selectTotalCount(IdAuth condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_tjcIdAuth_count"), condition);
    }

    @Override
    public List<IdAuth> selectList(IdAuth condition) {
        return super.selectList(NAMESPACE.concat("select_tjcIdAuth"),
            condition, IdAuth.class);
    }

    @Override
    public List<IdAuth> selectList(IdAuth condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_tjcIdAuth"), start,
            count, condition, IdAuth.class);
    }

}
