package com.david.disshappserver.service.impl;

import com.david.disshappserver.beans.User;
import com.david.disshappserver.common.bean.ResponseInfo;
import com.david.disshappserver.common.constants.AdminConstant;
import com.david.disshappserver.common.enums.ResponseCodeNum;
import com.david.disshappserver.dao.IUserDao;
import com.david.disshappserver.service.IUserService;
import com.david.disshappserver.utils.MD5Utils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 */

/**
 * 用户的业务层实现类，这里并使用注解
 */
@Service("userService")
@Transactional//事务管理
public class UserServiceImpl implements IUserService {
    @Resource(name = "userDao")
    private IUserDao userDao;

    @Override
    public List<User> findAllAdminUsers() {
        return userDao.findAllAdminUsers();
    }

    @Override
    public User findUserById(int userId) {
        return null;
    }

    @Override
    public ResponseInfo regist(String username, String password) {
        return null;
    }

    @Override
    public ResponseInfo login(String username, String password) {
        return null;
    }

    @Override
    public ResponseInfo adminLogin(String username, String password, HttpSession session) {
        ResponseInfo responseInfo = new ResponseInfo();
        try {
            if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
                //参数非法
                responseInfo.setCode(ResponseCodeNum.CODE_PARAM_INVALUD.getCode());
                return responseInfo;
            }
            User user = userDao.findUserByUserName(username);
            if (user == null) {
                //用户名不存在
                responseInfo.setCode(ResponseCodeNum.CODE_NO_SOURCE_EXISTS.getCode());
                return responseInfo;
            }
            if (user.getRole() == User.ROLE_ADMIN) {
                //如果用户是管理员，则验证密码是否正确
                if (!MD5Utils.MD5Encode(password).equals(user.getPassword())) {
                    //密码不正确
                    responseInfo.setCode(ResponseCodeNum.CODE_OTHER_ERROR.getCode());
                    return responseInfo;
                }
                //如果用户输入的密码转换为md5和数据库的一致，说明，密码正确
                responseInfo.setData(user);
                responseInfo.setCode(ResponseCodeNum.CODE_SUCCESS.getCode());
                //将当前用户存储到session中
                session.setAttribute(AdminConstant.KEY_CURR_USER, user);
                return responseInfo;
            } else {
                //这里则不是管理员
                responseInfo.setCode(ResponseCodeNum.CODE_SUCCESS.getCode());
                return responseInfo;
            }
        } catch (Exception e) {
            //登录失败，服务器异常
            responseInfo.setCode(ResponseCodeNum.CODE_SERVER_EXCEPTION.getCode());
            return responseInfo;
        }

    }
}
