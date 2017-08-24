package com.david.disshappserver.utils;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 */
public class UpLoadUtils {

    /**
     * 获取到FileItem对象
     * @param request
     * @return
     */
    public static FileItem getFileItem(HttpServletRequest request) {
        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
        List<FileItem> fileItems;
        try {
            fileItems = servletFileUpload.parseRequest(request);
            //这里表单有1个，直接数据写出
            FileItem fileItem = fileItems.get(0);
            return fileItem;
        } catch (Exception e) {
            return null;
        }

    }

    public static void main(String args[]){
//        FileItem fileItem = UpLoadUtils.getFileItem(request);
//        if (fileItem==null){
//            return AjaxUtils.ajaxFail("服务器错误，请重试");
//        }
//
//        fileItem.write(fie);
    }
}