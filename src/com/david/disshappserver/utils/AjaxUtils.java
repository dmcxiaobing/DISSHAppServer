package com.david.disshappserver.utils;

import com.david.disshappserver.common.bean.AjaxResponse;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 */
public class AjaxUtils {

    /**
      * 直接打印异常到页面 统一用json格式 {error:true,status:'500',msg:'授权失败',entity:{}} 	
      * @author David
      * @create_date 2014-5-7 下午3:45:32
      * @param tx
      * @param msg
      * @param response
      * @throws IOException
     */
	public static void ajaxError(Throwable tx,String msg,HttpServletResponse response) throws IOException{
		AjaxResponse<String> ajaxResponseUtils = new AjaxResponse<String>();
		ajaxResponseUtils.setError(true);
		ajaxResponseUtils.setMsg(msg);
		ajaxResponseUtils.setDetailMsg(tx.getMessage());
		ajaxResponseUtils.setStatus("500");
		response.setContentType("text/json;charset=UTF-8");
		response.getWriter().write(JsonUtils.objectToJson(ajaxResponseUtils));
	}
	/**
	 * 返回ajax需要格式的json字符串 用于@ResponseBody
	 * @author David
	 * @create_date 2014-5-7 下午3:45:33
	 * @return
	 * @throws IOException
	 */
	public static String ajaxError(Throwable tx,String msg) throws IOException{
		AjaxResponse<String> ajaxResponseUtils = new AjaxResponse<String>();
		ajaxResponseUtils.setError(true);
		ajaxResponseUtils.setMsg(msg);
		ajaxResponseUtils.setDetailMsg(tx.getMessage());
		ajaxResponseUtils.setStatus("500");
		return JsonUtils.objectToJson(ajaxResponseUtils);
	}
	/**
	 * 返回ajax需要格式的json字符串 用于@ResponseBody
	 * @author David
	 * @create_date 2014-5-7 下午3:45:33
	 * @return
	 * @throws IOException
	 */
	public static String ajaxFail(String msg){
		AjaxResponse<String> ajaxResponseUtils = new AjaxResponse<String>();
		ajaxResponseUtils.setError(true);
		ajaxResponseUtils.setMsg(msg);
		ajaxResponseUtils.setStatus("500");
		return JsonUtils.objectToJson(ajaxResponseUtils);
	}
	/**
	 * 返回ajax需要格式的json字符串 用于@ResponseBody
	 * @author David
	 * @create_date 2014-5-7 下午3:45:33
	 * @throws IOException
	 */
	public static String ajaxResult(String msg){
		AjaxResponse<String> ajaxResponseUtils = new AjaxResponse<String>();
		ajaxResponseUtils.setError(false);
		ajaxResponseUtils.setMsg(msg);
		ajaxResponseUtils.setStatus("200");
		return JsonUtils.objectToJson(ajaxResponseUtils);
	}
	/**
	 * 返回ajax需要格式的json字符串 用于@ResponseBody
	 * @author David
	 * @create_date 2014-5-7 下午3:45:33
	 * @return
	 * @throws IOException
	 */
	public static String ajaxSuccess(AjaxResponse<?> ajaxResponseUtils) throws IOException{
		ajaxResponseUtils.setError(false);
		ajaxResponseUtils.setStatus("200");
		return JsonUtils.objectToJson(ajaxResponseUtils);
	}
	/**
	  * @author David
	  * @create_date 2014-7-2 下午5:59:20
	  * @return
	  */
	public static String ajaxReLogin() {
		AjaxResponse<String> ajaxResponseUtils = new AjaxResponse<String>();
		ajaxResponseUtils.setError(false);
		ajaxResponseUtils.setMsg("请重新登录");
		ajaxResponseUtils.setStatus("302");
		return JsonUtils.objectToJson(ajaxResponseUtils);
	}
	/**
	  * @author David
	  * @create_date 2014-7-2 下午7:05:49
	  * @param response
	 * @throws IOException 
	  */
	public static void ajaxReLogin(HttpServletResponse response) throws IOException {
		AjaxResponse<String> ajaxResponseUtils = new AjaxResponse<String>();
		ajaxResponseUtils.setError(true);
		ajaxResponseUtils.setMsg("请重新登录");
		ajaxResponseUtils.setStatus("302");
		response.setContentType("text/json;charset=UTF-8");
		response.getWriter().write(JsonUtils.objectToJson(ajaxResponseUtils));
	}
}
