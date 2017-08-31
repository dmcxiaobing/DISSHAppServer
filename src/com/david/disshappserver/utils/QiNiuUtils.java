/**
 *
 */
package com.david.disshappserver.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

import com.david.disshappserver.common.InitConfig;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.json.JSONException;

import com.qiniu.api.auth.AuthException;
import com.qiniu.api.auth.digest.Mac;
import com.qiniu.api.io.IoApi;
import com.qiniu.api.io.PutExtra;
import com.qiniu.api.io.PutRet;
import com.qiniu.api.net.CallRet;
import com.qiniu.api.rs.PutPolicy;
import com.qiniu.api.rs.RSClient;

/**
 * 七牛上传的工具类
 */

/**
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 */

public class QiNiuUtils {

    //设置好账号的ACCESS_KEY和SECRET_KEY
    static String ACCESS_KEY = InitConfig.get("qiniu.ak");//Access_Key
    static String SECRET_KEY = InitConfig.get("qiniu.sk");//Secret_Key
    //要上传的空间
    static String bucketname = InitConfig.get("qiniu.bucketName");//Bucket_Name
    //上传到七牛后保存的文件名
    String key = "david_qq986945193_my-java.png";
    //上传文件的路径
    String FilePath = "/.../...";

    //密钥配置
    public static Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);

    ///////////////////////指定上传的Zone的信息//////////////////
    //第一种方式: 指定具体的要上传的zone
    //注：该具体指定的方式和以下自动识别的方式选择其一即可
    //要上传的空间(bucket)的存储区域为华东时
    // Zone z = Zone.zone0();
    //要上传的空间(bucket)的存储区域为华北时
    // Zone z = Zone.zone1();
    //要上传的空间(bucket)的存储区域为华南时
    // Zone z = Zone.zone2();

    //第二种方式: 自动识别要上传的空间(bucket)的存储区域是华东、华北、华南。
    static Zone z = Zone.autoZone();
    static Configuration c = new Configuration(z);

    //创建上传对象
    public static UploadManager uploadManager = new UploadManager(c);

    /*   public static void main(String args[]) throws IOException {
           new UploadDemo().upload();
       }
   */
    //简单上传，使用默认策略，只需要设置上传的空间名就可以了
    public static String getUpToken() {
        return auth.uploadToken(bucketname);
    }

    /**
     * 上传文件到七牛云
     * @param FilePath 文件路径
     * @param key 文件名称
     * @throws IOException
     */
    public static void newUploadQiniu(String FilePath, String key) throws IOException {
        try {
            //调用put方法上传
            Response res = uploadManager.put(FilePath, key, getUpToken());
            //打印返回的信息
            System.out.println(res.bodyString());
        } catch (QiniuException e) {
            Response r = e.response;
            // 请求失败时打印的异常的信息
            System.out.println(r.toString());
            try {
                //响应的文本信息
                System.out.println(r.bodyString());
            } catch (QiniuException e1) {
                //ignore
            }
        }
    }

    /**
     * 以io流上传文件
     * @param key 文件名
     */
    public static void newIo2UploadQiniu( String key,InputStream inputStream) throws IOException {
        try {
                //这是演示上传的
//            byte[] uploadBytes = "hello qiniu cloud".getBytes("utf-8");
//            ByteArrayInputStream byteInputStream=new ByteArrayInputStream(uploadBytes);
            Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
            String upToken = auth.uploadToken(bucketname);
            try {
                Response response = uploadManager.put(inputStream,key,upToken,null, null);
                //解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                System.out.println(putRet.key);
                System.out.println(putRet.hash);
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    //ignore
                }
            }
        } catch (Exception ex) {
            //ignore
            LogUtils.i("上传失败....");
        }

    }


//*************************************************************以下是旧API*********************************////
    /**
     * 七牛初始化
     */
    private static Mac mac = new Mac(InitConfig.get("qiniu.ak"), InitConfig.get("qiniu.sk"));
    public static String bucketName = InitConfig.get("qiniu.bucketName");// 请确保该bucket已经存在
    private static PutPolicy putPolicy = new PutPolicy(bucketName);
    private static PutExtra extra = new PutExtra();
    private static PutRet ret = null;
    private static String urlPrefix = InitConfig.get("qiniu.url");


    /**
     * 上传文件
     *
     * @param key
     * @param inputStream
     * @param isCover
     * @return
     * @author david
     * @create_date 2014-9-30 下午4:38:48
     */
    public static String upload2Stream(String key, InputStream inputStream, boolean isCover) {
        try {
            if (isCover)
                putPolicy.scope = bucketName + ":" + key;
            else
                putPolicy.scope = bucketName;
            String uptoken = putPolicy.token(mac);
            ret = IoApi.Put(uptoken, key, inputStream, extra);
            if (ret != null) {
                if (ret.getStatusCode() == 200) {
                    System.out.println(ret.response);
                    return ret.getKey();
                }
                if (ret.getStatusCode() != 200) {
                    LogUtils.i(ret.getStatusCode() + "");
                    return ret.getException().getMessage();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

//    public static MyRet

    public static String uploadByLocal(String key, String localFile, boolean isCover) {
        try {
            if (isCover)
                putPolicy.scope = bucketName + ":" + key;
            else
                putPolicy.scope = bucketName;
            String uptoken = putPolicy.token(mac);
            ret = IoApi.putFile(uptoken, key, localFile, extra);
            if (ret != null) {
                if (ret.getStatusCode() == 200)
                    return ret.getKey();
                if (ret.getStatusCode() != 200)
                    return "" + ret.getStatusCode();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 删除文件
     *
     * @param key
     * @return
     * @author david
     * @create_date 2014-9-30 下午4:39:31
     */
    public static int deleteByKey(String key) {
        RSClient client = new RSClient(mac);
        CallRet ret = client.delete(bucketName, key);
        return ret.getStatusCode();
    }

    /**
     * 复制文件
     *
     * @return
     * @author david
     * @create_date 2014-10-8 上午10:28:10
     */
    public static int copy(String keySrc, String keyDest) {
        RSClient client = new RSClient(mac);
        CallRet ret = client.copy(bucketName, keySrc, bucketName, keyDest);
        return ret.getStatusCode();
    }

    public static String getQiniuToken(String key, boolean isCover) {
        if (isCover)
            putPolicy.scope = bucketName + ":" + key;
        else
            putPolicy.scope = bucketName;
        try {
            return putPolicy.token(mac);
        } catch (AuthException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取水印路径
     *
     * @param urlPath
     * @return
     */
    public static String getWaterMark(String urlPath) {
        if (!urlPath.startsWith(urlPrefix)) {
            return urlPath;
        }
        String waterMark = "?watermark/1/image/aHR0cDovLzd4amVoZy5jb20xLnowLmdsYi5jbG91ZGRuLmNvbS93YXRlcm1hcmsucG5n/dissolve/50/gravity/SouthEast";
        return urlPath + waterMark;
    }

    public static void main(String[] args) {
        String key = UUID.randomUUID().toString() + ".jpg";
        System.out.println(getQiniuToken(key, true) + "\n" + key);
    }
}
