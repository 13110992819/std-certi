package com.std.certi.bo;

import java.util.Map;

import com.std.certi.bo.base.IPaginableBO;
import com.std.certi.domain.CPassword;

public interface ICPasswordBO extends IPaginableBO<CPassword> {
    public Map<String, String> queryCPasswordMap(String systemCode,
            String companyCode, String type);
}
