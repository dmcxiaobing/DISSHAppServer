package com.david.disshappserver.web.controller.admin;

/**
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 */

import com.david.disshappserver.service.IJokeImgService;
import com.david.disshappserver.service.IJokeService;
import com.david.disshappserver.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * 搞笑图片
 */
@Controller
@RequestMapping("/admin")
public class JokeImgController {

    @Resource(name = "userService")
    private IUserService userService;

    @Resource(name = "jokeImgService")
    private IJokeImgService jokeImgService;


    @RequestMapping("/jokeimg/list")
    public String list(){


        return "admin/";
    }
}
