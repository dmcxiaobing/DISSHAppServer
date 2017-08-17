package com.david.disshappserver.web.controller.admin;

import com.david.disshappserver.beans.User;
import com.david.disshappserver.common.bean.ResponseInfo;
import com.david.disshappserver.common.constants.AdminConstant;
import com.david.disshappserver.service.IUserService;
import com.david.disshappserver.utils.LogUtils;
import com.qiniu.api.net.Http;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 管理员adminController
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    /**
     * 使用java注解获取到UserService
     */
    @Resource(name = "userService")
    private IUserService userService;

    /**
     * 进入管理员登陆页面,默认get和post均可以支持。这里修改为只支持get
     */
//    @RequestMapping("/")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String admin(Model model, HttpSession session) {
        LogUtils.i("进入管理界面");
        User user = (User) session.getAttribute(AdminConstant.KEY_CURR_USER);
        if (user == null) {
            //这说明没有登陆，则转发到登陆页面
            return "admin/login";
        }
        //这里去查询是否是管理员
        List<User> users = userService.findAllAdminUsers();
        model.addAttribute("users", users);
        return "admin/index";
    }

    /**
     * 管理员进行登陆
     */
    @ResponseBody
    @RequestMapping(value = "/2login",method = RequestMethod.POST)
    public ResponseInfo login(@RequestParam(value = "userName",required = true) String userName,
                              @RequestParam(value = "password",required = true) String password,
                              HttpSession session){
        return userService.adminLogin(userName,password,session);
    }

    /**
     * frame框架中根据不同的参数，转发到不同的页面
     */
    @RequestMapping(value="/{page}")
    public String gotoPage(HttpSession session, @PathVariable String page) {
        User user = (User) session.getAttribute(AdminConstant.KEY_CURR_USER);
        if(user == null) {
            return "admin/login";
        }
        return "admin/" + page;
    }

    /**
     * 退出后台的操作
     */
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        //从session域中删除用户，然后重定向到登陆页面
        session.removeAttribute(AdminConstant.KEY_CURR_USER);
        return "redirect:login";

    }

}
