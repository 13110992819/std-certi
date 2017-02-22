package com.std.certi.ao;

import com.std.certi.bo.base.Paginable;
import com.std.certi.domain.UserPicture;

public interface IUserPictureAO {

    String DEFAULT_ORDER_COLUMN = "id";

    void doSaveUserPicture(String systemCode, String companyCode,
            String userId, String idKind, String idNo, String realName,
            String idPic1, String idPic2, String idUserPic, String remark);

    void doVerifyUserPicture(Long id, String verifyUser, String verifyResult,
            String remark);

    Paginable<UserPicture> queryUserPicturePage(int start, int limit,
            UserPicture condition);

    UserPicture getUserPicture(Long id);

}
