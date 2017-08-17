package com.david.disshappserver.service;

import com.david.disshappserver.beans.User;
import com.david.disshappserver.common.bean.ResponseInfo;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 */
public interface IUserService {
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
    public ResponseInfo regist(String username,String password);
    /**
     * 用户登陆
     */
    public ResponseInfo login(String username,String password);

    /**
     * 管理员登陆
     */
    ResponseInfo adminLogin(String username, String password, HttpSession session);
}
