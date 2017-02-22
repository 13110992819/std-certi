package com.std.certi.ao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.std.certi.ao.IUserPictureAO;
import com.std.certi.bo.IUserPictureBO;
import com.std.certi.bo.base.Paginable;
import com.std.certi.domain.UserPicture;

@Service
public class UserPictureAOImpl implements IUserPictureAO {
    @Autowired
    IUserPictureBO userPictureBO;

    @Override
    public void doSaveUserPicture(String systemCode, String companyCode,
            String userId, String idKind, String idNo, String realName,
            String idPic1, String idPic2, String idUserPic, String remark) {
        userPictureBO.saveUserPicture(systemCode, companyCode, userId, idKind,
            idNo, realName, idPic1, idPic2, idUserPic, remark);

    }

    @Override
    public void doVerifyUserPicture(Long id, String verifyUser,
            String verifyResult, String remark) {
        userPictureBO.doVerifyUserPicture(id, verifyUser, verifyResult, remark);

    }

    @Override
    public Paginable<UserPicture> queryUserPicturePage(int start, int limit,
            UserPicture condition) {
        return userPictureBO.getPaginable(start, limit, condition);
    }

    @Override
    public UserPicture getUserPicture(Long id) {
        return userPictureBO.getUserPicture(id);
    }
}
