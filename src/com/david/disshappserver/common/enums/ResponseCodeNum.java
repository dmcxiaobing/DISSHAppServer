package com.david.disshappserver.common.enums;

/**
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 */

/**
 * http请求中返回的错误码以及对应的详细信息。code,desc
 */
public enum  ResponseCodeNum {

    CODE_SUCCESS("200", "success，成功"),
    CODE_SERVER_EXCEPTION("500", "server exception，服务器异常"),
    CODE_PARAM_INVALUD("501", "request parameters is invalud 参数非法"),
    CODE_SOURCE_EXISTS("502", "such source exists"),
    CODE_NO_SOURCE_EXISTS("503", "no such source exists 资源不存在"),
    CODE_OTHER_ERROR("504", "other error happened");

    private String code;
    private String desc;

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    private ResponseCodeNum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static String getDescByCode(String code){
        for(ResponseCodeNum codeRespEnum : ResponseCodeNum.values()){
            if(codeRespEnum.getCode().equals(code)){
                return codeRespEnum.getDesc();
            }
        }
        return "";
    }


}
