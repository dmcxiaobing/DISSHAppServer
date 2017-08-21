package com.david.disshappserver.service.impl;

import com.david.disshappserver.beans.Joke;
import com.david.disshappserver.common.Limit;
import com.david.disshappserver.common.PageResult;
import com.david.disshappserver.dao.IJokeDao;
import com.david.disshappserver.service.IJokeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 */
@Service("jokeService")
@Transactional
/**
 * 笑话service
 */
public class JokeServiceImpl implements IJokeService{
    @Resource(name = "jokeDao")
    private IJokeDao jokeDao;
    @Override
    public PageResult<Joke> getJokes(Limit limit, int type, int newOrHotflag) {
        return jokeDao.getJokes(limit,type,newOrHotflag);
    }
}
