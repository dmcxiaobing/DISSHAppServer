package com.david.disshappserver.service;

import com.david.disshappserver.beans.Joke;
import com.david.disshappserver.common.Limit;
import com.david.disshappserver.common.PageResult;

/**
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 */
public interface IJokeService {
    /**
     * 分页获取joke列表
     * @param limit 分页对象
     * @param type joke类别
     * @param newOrHotflag 标记是最新或者最热排序
     */
    public PageResult<Joke> getJokes(Limit limit,int type,int newOrHotflag);
}
