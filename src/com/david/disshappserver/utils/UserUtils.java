package com.david.disshappserver.utils;
/**
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 */

import com.david.disshappserver.beans.User;
import com.david.disshappserver.common.bean.ResponseInfo;
import com.david.disshappserver.common.enums.ResponseCodeNum;
import org.apache.commons.lang.StringUtils;

/**
 * 一个用来检测用户的工具类
 */
public class UserUtils {
    /**
     * 检测用户输入的参数是否合法
     */
    public static boolean checkUserParameterIsValid(String username, String password, ResponseInfo responseInfo){
        if (StringUtils.isEmpty(username)||StringUtils.isEmpty(username.trim())||
                StringUtils.isEmpty(password)||StringUtils.isEmpty(password.trim())){
            //用户名或者密码传来了为空  报参数非法
            responseInfo.setCode(ResponseCodeNum.CODE_PARAM_INVALID.getCode());
            return true;
        }
        return false;
    }
}
