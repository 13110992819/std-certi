/**
 * @Title UserPictureBOImpl.java 
 * @Package com.ibis.pz.user.impl 
 * @Description 
 * @author luoqi  
 * @date 2015年3月8日 上午11:25:38 
 * @version V1.0   
 */
package com.std.certi.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.std.certi.bo.IUserPictureBO;
import com.std.certi.bo.base.PaginableBOImpl;
import com.std.certi.dao.IUserPictureDAO;
import com.std.certi.domain.UserPicture;
import com.std.certi.enums.EBoolean;
import com.std.certi.enums.EOrderStatus;

/** 
 * @author: luoqi 
 * @since: 2015年3月8日 上午11:25:38 
 * @history:
 */
@Component
public class UserPictureBOImpl extends PaginableBOImpl<UserPicture> implements
        IUserPictureBO {
    @Autowired
    private IUserPictureDAO userPictureDAO;

    @Override
    public List<UserPicture> queryUserPictureList(UserPicture conditon) {
        return userPictureDAO.selectList(conditon);
    }

    @Override
    public int saveUserPicture(String systemCode, String companyCode,
            String userId, String idKind, String idNo, String realName,
            String idPic1, String idPic2, String idUserPic, String remark) {

        int count = 0;
        if (StringUtils.isNotBlank(systemCode)
                && StringUtils.isNotBlank(companyCode)
                && StringUtils.isNotBlank(userId)
                && StringUtils.isNotBlank(idKind)
                && StringUtils.isNotBlank(idNo)
                && StringUtils.isNotBlank(realName)
                && StringUtils.isNotBlank(idPic1)
                && StringUtils.isNotBlank(idPic2)
                && StringUtils.isNotBlank(idUserPic)) {
            UserPicture data = new UserPicture();
            data.setSystemCode(systemCode);
            data.setCompanyCode(companyCode);
            data.setUserId(userId);
            data.setIdKind(idKind);
            data.setIdNo(idNo);
            data.setRealName(realName);
            data.setIdPic1(idPic1);
            data.setIdPic2(idPic2);
            data.setIdUserPic(idUserPic);
            data.setStatus(EOrderStatus.UNAPPROVE.getCode());
            data.setCreateDatetime(new Date());
            data.setRemark(EOrderStatus.UNAPPROVE.getValue());
            count = userPictureDAO.insert(data);
        }
        return count;
    }

    @Override
    public int doVerifyUserPicture(Long id, String verifyUser,
            String verifyResult, String remark) {
        int count = 0;
        if (id > 0 && StringUtils.isNotBlank(verifyUser)
                && StringUtils.isNotBlank(verifyResult)) {
            UserPicture data = new UserPicture();
            data.setId(id);
            if (EBoolean.YES.getCode().equalsIgnoreCase(verifyResult)) {
                data.setStatus(EOrderStatus.APPROVE_YES.getCode());
            } else {
                data.setStatus(EOrderStatus.APPROVE_NO.getCode());
            }
            data.setVerifyUser(verifyUser);
            data.setVerifyDatetime(new Date());
            data.setRemark(remark);
            count = userPictureDAO.updateVerifyUserPicture(data);
        }
        return count;
    }

    @Override
    public UserPicture getUserPicture(Long id) {
        UserPicture condition = new UserPicture();
        condition.setId(id);
        return userPictureDAO.select(condition);
    }
}
