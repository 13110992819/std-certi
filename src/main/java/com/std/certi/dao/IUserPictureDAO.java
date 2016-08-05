package com.std.certi.dao;

import com.std.user.dao.base.IBaseDAO;
import com.std.user.domain.UserPicture;

/**
 * @author: luoqi 
 * @since: 2015年3月7日 下午6:28:30 
 * @history:
 */
public interface IUserPictureDAO extends IBaseDAO<UserPicture> {
    String NAMESPACE = IUserPictureDAO.class.getName().concat(".");

    int updateVerifyUserPicture(UserPicture data);
}
