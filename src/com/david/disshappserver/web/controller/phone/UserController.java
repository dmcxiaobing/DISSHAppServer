package com.david.disshappserver.web.controller.phone;

import com.david.disshappserver.common.bean.ResponseInfo;
import com.david.disshappserver.service.IUserService;
import com.david.disshappserver.utils.LogUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 */
@RequestMapping(value = "/phone/user")
@Controller
public class UserController {

    @Resource(name = "userService")
    private IUserService userService;
    /**
     * 用户注册
     */
//    @RequestMapping(value = "/regist",method = RequestMethod.GET)
    @RequestMapping(value = "/regist",method = RequestMethod.GET)
    @ResponseBody
    public ResponseInfo regist(@RequestParam(value = "username" ,required = true)String userName,
                               @RequestParam(value = "password",required = true)String password){
        LogUtils.i("regist");
        return userService.regist(userName,password);
    }

    /**
     * 用户登录
     *
     */
    @ResponseBody
    @RequestMapping("/login")
    public ResponseInfo login(@RequestParam(value = "username" ,required = true)String userName,
                              @RequestParam(value = "password",required = true)String password){
        LogUtils.i("login");
        return userService.login(userName,password);
    }
}


