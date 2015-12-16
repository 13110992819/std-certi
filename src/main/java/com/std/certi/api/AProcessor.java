package com.std.certi.api;

import com.std.certi.exception.BizException;
import com.std.certi.exception.ParaException;

/**
 * 模板：调用顺序+通用工具类
 * @author: chenyun 
 * @since: 2015年3月10日 下午4:42:26 
 * @history:
 */
public abstract class AProcessor implements IProcessor {
    /**
     * 
     * @see com.ibis.account.service.IProcessor#doProcessor(java.lang.String)
     */
    public final Object doProcessor(String inputparams) throws BizException,
            ParaException {
        doCheck(inputparams);
        return doBusiness();
    }
}
