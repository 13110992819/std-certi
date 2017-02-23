/**
 * @Title ZhimaVerifyResult.java 
 * @Package com.std.certi.domain 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年2月23日 下午11:08:46 
 * @version V1.0   
 */
package com.std.certi.domain;

/** 
 * @author: haiqingzheng 
 * @since: 2017年2月23日 下午11:08:46 
 * @history:
 */
public class ZhimaVerifyResult extends VerifyResult {
    // 是否认证通过
    private boolean passed;

    public boolean isPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }
}
