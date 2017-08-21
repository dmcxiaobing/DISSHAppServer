package com.david.disshappserver.dao;

import com.david.disshappserver.beans.Joke;
import com.david.disshappserver.common.Limit;
import com.david.disshappserver.common.PageResult;

/**
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 */
public interface IJokeDao {
    PageResult<Joke> getJokes(Limit limit, int type, int newOrHotflag);
}
