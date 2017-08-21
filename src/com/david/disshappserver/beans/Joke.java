package com.david.disshappserver.beans;

/**
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 */

import javax.persistence.*;
import java.util.Date;

/**
 * 笑话的实体类
 */
@Entity
@Table(name = "t_joke")
public class Joke {
    /**
     * type的标记
     */
    public static final  int TYPE_JOKE_FUN = 1;
    public static final  int TYPE_JOKE_IMG = 2;
    public static final  int TYPE_BEAUTIFUL_IMG = 3;
    public static final  int TYPE_JOKE_GIF = 4;
    /**
     * 是否通过审核标记
     */
    public static final int NOT_PASS=0;
    public static final int PASS=1;
    /**
     * 是否被删除标记
     */
    public static final  int NOT_DELETE = 0;
    public static final  int DELETE = 1;
    /**
     * 最新最热排序标记
     */
    public static  final  int SORT_NEW = 0;
    public static  final  int SORT_HOT = 1;
    /**
     * 精选排序标记
     */
    public static  final  int NOT_BEST = 0;
    public static  final  int BEST = 1;

    /**
     * 笑话ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",unique = true,nullable = false)
    private int id;
    /**
     * 笑话标题
     */
    @Column(name = "title",nullable = true)
    private String title;
    /**
     * 笑话内容
     */
    @Column(name = "content",nullable = true)
    private String content;


    /**
     * 图片地址
     */
    @Column(name = "img_url",nullable = true)
    private String imgUrl;
    /**
     * gif地址
     */
    @Column(name = "gif_url",nullable = true)
    private String gifUrl;
    /**
     * 笑话支持数
     */
    @Column(name = "support_num",length = 10,columnDefinition = "int default 0")
    private int supportsNum;

    /**
     * 笑话反对数
     */
    @Column(name = "oppose_num",length = 10,columnDefinition = "int default 0")
    private int opposesNum;

    /**
     * 笑话评论数
     */
    @Column(name = "comment_num",length = 10,columnDefinition = "int default 0")
    private int commentNum;

    /**
     * 笑话创建日期
     */
    @Column(name = "create_date",updatable = false)
    private Date createDate;

    /**
     * 是否删除标记（0：未删除，1已删除）
     */
    @Column(name = "is_delete",length = 10,columnDefinition = "int default 0")
    private int isDelete;

    /**
     * 笑话类别
     */
    @Column(name = "type",length = 10,columnDefinition = "int default 0")
    private int type;

    /**
     * 是否审核通过（0：未通过，1已通过）
     */
    @Column(name = "is_pass",length = 10,columnDefinition = "int default 0")
    private int isPass;

    /**
     * 发表用户
     */
    @Column(name = "user_id",length = 10)
    private int userId;

    /**
     * 发表用户头像
     */
    @Column(name = "portrait_url",nullable = true)
    private String portraitUrl;

    /**
     * 用户昵称
     */
    @Column(name = "user_nike",nullable = true)
    private String userNike;
    /**
     * 图片宽度
     */
    @Column(name = "img_width",length = 10,columnDefinition = "int default 0")
    private int imgWidth;
    /**
     * 图片高度
     */
    @Column(name = "img_height",length = 10,columnDefinition = "int default 0")
    private int imgHeight;

    /**
     * 是否为精选内容
     * 0：否 1：是
     */
    @Column(name = "is_jingxuan",length = 10,columnDefinition = "int default 0")
    private int isBest;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getGifUrl() {
        return gifUrl;
    }

    public void setGifUrl(String gifUrl) {
        this.gifUrl = gifUrl;
    }

    public int getSupportsNum() {
        return supportsNum;
    }

    public void setSupportsNum(int supportsNum) {
        this.supportsNum = supportsNum;
    }

    public int getOpposesNum() {
        return opposesNum;
    }

    public void setOpposesNum(int opposesNum) {
        this.opposesNum = opposesNum;
    }

    public int getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getIsPass() {
        return isPass;
    }

    public void setIsPass(int isPass) {
        this.isPass = isPass;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPortraitUrl() {
        return portraitUrl;
    }

    public void setPortraitUrl(String portraitUrl) {
        this.portraitUrl = portraitUrl;
    }

    public String getUserNike() {
        return userNike;
    }

    public void setUserNike(String userNike) {
        this.userNike = userNike;
    }

    public int getImgWidth() {
        return imgWidth;
    }

    public void setImgWidth(int imgWidth) {
        this.imgWidth = imgWidth;
    }

    public int getImgHeight() {
        return imgHeight;
    }

    public void setImgHeight(int imgHeight) {
        this.imgHeight = imgHeight;
    }

    public int getIsBest() {
        return isBest;
    }

    public void setIsBest(int isBest) {
        this.isBest = isBest;
    }
}
