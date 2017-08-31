package com.david.disshappserver.web.controller.admin;

/**
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 */

import com.david.disshappserver.beans.User;
import com.david.disshappserver.common.bean.AjaxResponse;
import com.david.disshappserver.common.bean.ResponseInfo;
import com.david.disshappserver.common.constants.AdminConstant;
import com.david.disshappserver.service.IJokeImgService;
import com.david.disshappserver.service.IJokeService;
import com.david.disshappserver.service.IUserService;
import com.david.disshappserver.utils.*;
import org.apache.commons.fileupload.FileItem;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

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

    /**
     * 转发到添加图片的jsp
     */
    @RequestMapping("/jokeimg/addToJsp")
    public String addToJsp(HttpSession session, Model model) {
        User user = (User) session.getAttribute(AdminConstant.KEY_CURR_USER);
        if (user == null) {
            return "admin/login";
        }
        List<User> users = userService.findAllAdminUsers();
        model.addAttribute("users", users);
        return "admin/addJokeImg";
    }

    /**
     * 添加图片保存
     */
    @RequestMapping(value = "/jokeimg/addSaveImg", method = RequestMethod.POST)
    public String addSaveImg(HttpSession session, Model model) {

        return null;
    }

    /**
     * ajax进行上传图片  这是存到本地。这里默认使用七牛云存储。详情看uploadController
     */
    @ResponseBody
    @RequestMapping(value = "/jokeImg/ajaxUploadImg", method = RequestMethod.POST)
    public String ajaxUploadImg(@RequestParam(required = true) MultipartFile fileToUpload, HttpServletResponse response) throws IOException {
        LogUtils.i("上传图片");
        if (fileToUpload == null) {
            //上传图片为空
            return AjaxUtils.ajaxFail("上传图片为空");
        }
        try {
            String fileName = fileToUpload.getOriginalFilename();
            //截取后缀名
            String extention = fileName.substring(fileName.lastIndexOf(".", fileName.length()));
            if (!".jpg".equals(extention.toLowerCase()) && !".jpeg".equals(extention.toLowerCase())
                    && !".png".equals(extention.toLowerCase())) {
                return AjaxUtils.ajaxFail("非图片内容");
            }
            /**
             * 处理文件名的绝对路径问题
             */
            //返回最右边出现/的位置，若是不存在，则返回-1
            int index = fileName.lastIndexOf("\\");
            //如果包含则截取
            if (index != -1) {
                fileName = fileName.substring(index + 1);
            }

            //得到文件保存的路径 这里设置项目中，实际要做映射
//            String rootPath = request.getServletContext().getRealPath("/WEB-INF/files/");
//            String rootPath = request.getServletContext().getRealPath("/resource/uploadFiles/");
            //传到D盘
            String rootPath = "D:/";

            String saveFileName = CommonUtils.getUUIDRandomNum() + "_" + fileName;
            //3FF12D362BD448BBA7B0B1AAABC0843A_db1
            File targetfile = new File(rootPath, saveFileName);


            // 保存
            fileToUpload.transferTo(targetfile);
            AjaxResponse<String> ajaxResponse = new AjaxResponse<>();
//            ajaxResponse.setResult(rootPath + saveFileName);
            //设置为假的地址图片
            ajaxResponse.setResult("https://avatars2.githubusercontent.com/u/13946951?v=4&s=460");
            ajaxResponse.setMsg("上传成功");
//            FastJsonUtils.write_json(response,FastJsonUtils.toJSONString(ajaxResponse));
            return AjaxUtils.ajaxSuccess(ajaxResponse);
        } catch (Exception e) {
            return AjaxUtils.ajaxError(e, "服务器异常");
        }
    }
}
