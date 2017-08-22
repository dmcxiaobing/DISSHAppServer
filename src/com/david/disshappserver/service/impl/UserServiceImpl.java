package com.david.disshappserver.service.impl;

import com.david.disshappserver.beans.User;
import com.david.disshappserver.common.bean.ResponseInfo;
import com.david.disshappserver.common.constants.AdminConstant;
import com.david.disshappserver.common.enums.ResponseCodeNum;
import com.david.disshappserver.dao.IUserDao;
import com.david.disshappserver.service.IUserService;
import com.david.disshappserver.utils.MD5Utils;
import com.david.disshappserver.utils.UserUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.security.provider.MD5;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
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
        return userDao.findUserById(userId);
    }

    /**
     * 用户注册
     */
    @Override
    public ResponseInfo regist(String username, String password) {
        ResponseInfo responseInfo = new ResponseInfo();
        try {
            /**
             * 检测用户的带来的参数是否合法
             */
            if (UserUtils.checkUserParameterIsValid(username, password, responseInfo)) {
                return responseInfo;
            }
            //根据用户名查找用户
            User user = userDao.findUserByUserName(username);
            if (user != null) {
                //如果不等于空，则说明数据库中已经存在了
                responseInfo.setCode(ResponseCodeNum.CODE_SOURCE_EXISTS.getCode());
                return responseInfo;
            }
            //正常注册,简单设置一些属性
            user = new User();
            user.setUserNike("xb" + username);
            user.setSex(1);
            user.setUserName(username);
            //密码转换为md5存入一下
            user.setPassword(MD5Utils.MD5Encode(password));
            user.setIs_receive_push(1);
            user.setIs_forbid(0);
            user.setCreate_date(new Date());
            //注册后，并查询到用户
            User registUser = userDao.regist(user);
            if (registUser == null) {
                //注册失败的
                responseInfo.setCode(ResponseCodeNum.CODE_OTHER_ERROR.getCode());
                return responseInfo;
            }
            responseInfo.setData(registUser);
            responseInfo.setCode(ResponseCodeNum.CODE_SUCCESS.getCode());
            return responseInfo;
        } catch (Exception e) {
            //注册失败的异常
            responseInfo.setCode(ResponseCodeNum.CODE_OTHER_ERROR.getCode());
            return responseInfo;
        }
    }


    @Override
    public ResponseInfo login(String username, String password) {
        ResponseInfo responseInfo = new ResponseInfo();
        try {
            /**
             * 检测用户的带来的参数是否合法
             */
            if (UserUtils.checkUserParameterIsValid(username, password, responseInfo)) {
                return responseInfo;
            }
            //根据用户名查找用户
            User user = userDao.findUserByUserName(username);
            if (user == null) {
               //如果等于空，则说名用户名不存在
                responseInfo.setCode(ResponseCodeNum.CODE_NO_SOURCE_EXISTS.getCode());
                return responseInfo;
            }
            //如果不等于空，则说明数据库中已经存在,则再匹配密码
            if (!user.getPassword().equals(MD5Utils.MD5Encode(password))){
                //如果不相等，则说名密码错误
                responseInfo.setCode(ResponseCodeNum.CODE_PASSWORD_ERROR.getCode());
                return responseInfo;
            }
            //如果相等则说名密码，存在，返回用户信息
            responseInfo.setCode(ResponseCodeNum.CODE_SUCCESS.getCode());
            responseInfo.setData(user);
            return responseInfo;
        } catch (Exception e) {
            //注册失败的异常
            responseInfo.setCode(ResponseCodeNum.CODE_OTHER_ERROR.getCode());
            return responseInfo;
        }
    }

    /*
    管理员登录
     */
    @Override
    public ResponseInfo adminLogin(String username, String password, HttpSession session) {
        ResponseInfo responseInfo = new ResponseInfo();
        try {
            if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
                //参数非法
                responseInfo.setCode(ResponseCodeNum.CODE_PARAM_INVALID.getCode());
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
