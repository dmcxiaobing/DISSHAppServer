package com.david.disshappserver.web.controller.admin;

/**
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 */

import com.david.disshappserver.beans.Joke;
import com.david.disshappserver.beans.User;
import com.david.disshappserver.common.constants.AdminConstant;
import com.david.disshappserver.service.IBeautyService;
import com.david.disshappserver.service.IJokeImgService;
import com.david.disshappserver.service.IJokeService;
import com.david.disshappserver.service.IUserService;
import com.david.disshappserver.thread.GetImgSizeThread;
import com.david.disshappserver.utils.LogUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * 美图控制器
 */
@Controller
@RequestMapping("/admin")
public class BeautyController {

    @Resource(name = "beautyService")
    private IBeautyService beautyService;

    @Resource(name = "userService")
    private IUserService userService;

    @Resource(name = "jokeImgService")
    private IJokeImgService jokeImgService;


    /**
     * 进入添加美图的jsp
     */
    @RequestMapping("/beauty/addBeauty")
    public String addToJsp(HttpSession session, Model model) {
        User user = (User) session.getAttribute(AdminConstant.KEY_CURR_USER);
        if (user == null) {
            return "admin/login";
        }
        List<User> users = userService.findAllAdminUsers();
        model.addAttribute("users", users);
        return "admin/addBeauty";
    }


    /**
     * 添加图片保存 上传在UploadController
     * 图片标题。图片url，是否精选，用户ID，支持数
     */
    @RequestMapping(value = "/beauty/addSaveImg", method = RequestMethod.POST)
    public String addSaveImg(@RequestParam String title, @RequestParam(required = true) String imgUrl,
                             @RequestParam(required = false) Integer isBest,
                             @RequestParam(required = true) int userId,
                             @RequestParam(required = false) Integer supportsNum) {
        User user = userService.findUserById(userId);
        Joke joke = new Joke();
        joke.setTitle(title);
        //这里直接将图片放入到数据库了。
        joke.setImgUrl(imgUrl);
        //如果没有选择，默认是admin
        if (user == null) {
            joke.setUserId(1);
            joke.setUserNike("admin");
        } else {
            //如果有选择用户，则更新此信息
            joke.setUserId(user.getId());
            joke.setUserNike(user.getUserNike());
            joke.setPortraitUrl(user.getPortrait_url());
        }
        if (isBest != null) {
            joke.setIsBest(isBest);
        } else {
            joke.setIsBest(joke.BEST);//默认是精选
        }
        joke.setSupportsNum(supportsNum);
        joke.setIsPass(Joke.PASS);//通过审核
        joke.setType(Joke.TYPE_BEAUTIFUL_IMG);
        joke.setCreateDate(new Date());
        //插入到数据库将美图图片以及信息
        int jokeImgId = jokeImgService.addJokeImg(joke);
        //获取详情
        Joke newJokeImg = jokeImgService.findJokeImgById(jokeImgId);
        //裁剪大小
        new GetImgSizeThread(newJokeImg, jokeImgService).start();
        LogUtils.i("添加成功");
        return "admin/addSuccess";
    }

}
