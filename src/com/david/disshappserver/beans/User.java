package com.david.disshappserver.beans;

/**
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 */

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户的实体类
 */
@Entity
@Table(name = "t_user")
public class User implements Serializable{


    public static final int ROLE_USER = 0;
    public static final int ROLE_ADMIN = 1;

    /**
     * 用户id
     */
    //   @GeneratedValue(generator = "uuid")
//	@GenericGenerator(name = "uuid", strategy = "uuid")
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private int id;
    /**
     * 用户名
     */
    @Column(name = "user_name", unique = true, nullable = true)
    private String userName;

    /**
     * 用户登陆密码
     */
    @Column(name = "password", nullable = true)
    private String password;

    /**
     * 用户昵称
     */
    @Column(name = "user_nike", nullable = true)
    private String userNike;

    /**
     * 用户性别(0：女、1：男)
     */
    @Column(name = "sex", length = 10, columnDefinition = "int default 1")
    private int sex;
    /**
     * 用户头像url
     */
    @Column(name = "portrait_url",nullable = true)
    private String portrait_url;
    /**
     * 是否接受推送消息（0:否  、1：是）
     */
    @Column(name = "is_receive_push",length = 10,columnDefinition = "int default 1")
    private int is_receive_push;
    /**
     * 是否禁用（0:否  、1：是）
     */
    @Column(name = "is_forbid",length = 10,columnDefinition = "int default 0")
    private int is_forbid;
    /**
     * 创建日期
     */
    @Column(name = "create_date",updatable = false)
    private Date create_date;
    /**
     * 腾讯QQ,OpenId
     */
    @Column(name = "tencent_open_id",unique = true,nullable = true)
    private String tencent_open_id;
    /**
     * 新浪uid
     */
    @Column(name = "sina_uid",unique = true,nullable = true)
    private String sina_uid;
    /**
     * 用户角色
     * 0:普通用户
     * 1，管理员
     */
    @Column(name = "role",length = 10,columnDefinition = "int default 0")
    private int role;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserNike() {
        return userNike;
    }

    public void setUserNike(String userNike) {
        this.userNike = userNike;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getPortrait_url() {
        return portrait_url;
    }

    public void setPortrait_url(String portrait_url) {
        this.portrait_url = portrait_url;
    }

    public int getIs_receive_push() {
        return is_receive_push;
    }

    public void setIs_receive_push(int is_receive_push) {
        this.is_receive_push = is_receive_push;
    }

    public int getIs_forbid() {
        return is_forbid;
    }

    public void setIs_forbid(int is_forbid) {
        this.is_forbid = is_forbid;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public String getTencent_open_id() {
        return tencent_open_id;
    }

    public void setTencent_open_id(String tencent_open_id) {
        this.tencent_open_id = tencent_open_id;
    }

    public String getSina_uid() {
        return sina_uid;
    }

    public void setSina_uid(String sina_uid) {
        this.sina_uid = sina_uid;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
