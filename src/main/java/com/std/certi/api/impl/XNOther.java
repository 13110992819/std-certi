package com.std.certi.api.impl;

import com.std.certi.api.AProcessor;
import com.std.certi.exception.BizException;
import com.std.certi.exception.ParaException;

public class XNOther extends AProcessor {

    @Override
    public Object doBusiness() throws BizException {
        throw new BizException("798xxx", "无效API功能号");
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        throw new ParaException("798xxx", "无效API功能号");

    }

}
