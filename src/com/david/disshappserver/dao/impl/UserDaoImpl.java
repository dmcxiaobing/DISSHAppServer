package com.david.disshappserver.dao.impl;

import com.david.disshappserver.beans.User;
import com.david.disshappserver.common.bean.ResponseInfo;
import com.david.disshappserver.dao.IUserDao;
import com.david.disshappserver.dao.base.BaseDAO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 */
@Repository(value = "userDao")
public class UserDaoImpl extends BaseDAO implements IUserDao {
    /**
     * 查找所有管理员
     */
    @Override
    public List<User> findAllAdminUsers() {
        return super.find("from User where role = ?",User.ROLE_ADMIN);
    }

    @Override
    public User findUserById(int userId) {
        return null;
    }

    /**
     * 注册用户
     */
    @Override
    public User regist(User user) {
        Integer userId = (Integer) super.save(user);
        if (userId == null){
            //则注册操作失败
            return null;
        }
        //查找到用户，根据保存后的ID
        return findUserById(userId);
    }

    @Override
    public ResponseInfo login(String username, String password) {
        return null;
    }

    /**
     * 根据用户名查找用户
     */
    @Override
    public User findUserByUserName(String username) {
        return super.getUniqueResult("from User where userName = ?",username);
    }
}
