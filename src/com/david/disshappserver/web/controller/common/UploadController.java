package com.david.disshappserver.web.controller.common;

import com.david.disshappserver.common.InitConfig;
import com.david.disshappserver.common.bean.AjaxResponse;
import com.david.disshappserver.utils.AjaxUtils;
import com.david.disshappserver.utils.CommonUtils;
import com.david.disshappserver.utils.LogUtils;
import com.david.disshappserver.utils.QiNiuUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 */
@Controller
@RequestMapping("/upload")
public class UploadController {

    /**
     * 上传搞笑图片
     */
    @ResponseBody
    @RequestMapping("/jokeImg")
    public String jokeImg(@RequestParam(required = true) MultipartFile fileToUpload){
        if (fileToUpload == null){
            LogUtils.i("上传图片失败，图片为空");
            return AjaxUtils.ajaxFail("上传图片为空");
        }
        try {
            //获取文件名
            String fileName = fileToUpload.getOriginalFilename();
            //截取后缀名
            String extention = fileName.substring(fileName.lastIndexOf(".", fileName.length()));
            if (!".jpg".equals(extention.toLowerCase()) && !".jpeg".equals(extention.toLowerCase())
                    && !".png".equals(extention.toLowerCase())) {
                return AjaxUtils.ajaxFail("非图片内容");
            }
            //文件名
            String rskey = CommonUtils.getUUIDRandomNum()+extention;
//           QiNiuUtils.upload2Stream(rskey,fileToUpload.getInputStream(),true);
            InputStream input = fileToUpload.getInputStream();
//            QiNiuUtils.newUploadQiniu("qq986945193github",rskey);
            QiNiuUtils.newIo2UploadQiniu(rskey,input);
            LogUtils.i("上传图片成功");
            AjaxResponse ajaxResponse = new AjaxResponse();
            ajaxResponse.setResult(InitConfig.get("qiniu.url")+rskey);
            ajaxResponse.setMsg("上传图片成功");
            return AjaxUtils.ajaxSuccess(ajaxResponse);
        } catch (Exception e) {
            try {
                return AjaxUtils.ajaxError(e, "服务器异常");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        return  null;
    }

}
