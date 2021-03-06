/**
 * @Title IUserPictureBO.java 
 * @Package com.ibis.pz.user 
 * @Description 
 * @author luoqi  
 * @date 2015年3月8日 上午11:22:38 
 * @version V1.0   
 */
package com.std.certi.bo;

import java.util.List;

import com.std.certi.bo.base.IPaginableBO;
import com.std.certi.domain.UserPicture;

/** 
 * @author: luoqi 
 * @since: 2015年3月8日 上午11:22:38 
 * @history:
 */
public interface IUserPictureBO extends IPaginableBO<UserPicture> {
    public int saveUserPicture(String systemCode, String companyCode,
            String userId, String idKind, String idNo, String realName,
            String idPic1, String idPic2, String idUserPic, String remark);

    public int doVerifyUserPicture(Long id, String verifyUser,
            String verifyResult, String remark);

    public List<UserPicture> queryUserPictureList(UserPicture conditon);

    public UserPicture getUserPicture(Long id);

}
