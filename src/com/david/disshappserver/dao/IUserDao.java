package com.david.disshappserver.dao;

import com.david.disshappserver.beans.User;
import com.david.disshappserver.common.bean.ResponseInfo;

import java.util.List;

/**
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 */

/**
 * 用户的持久层
 */
public interface IUserDao {
    /**
     * 查询所有管理员
     */
    public List<User> findAllAdminUsers();
    /**
     * 根据用户id查找用户
     */
    public User findUserById(int userId);
    /**
     * 用户注册
     */
    public User regist(User user);

    /**
     * 根据用户名查找用户
     */
    User findUserByUserName(String username);
}
