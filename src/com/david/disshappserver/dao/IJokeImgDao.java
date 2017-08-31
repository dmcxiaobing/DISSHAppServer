package com.david.disshappserver.dao;

import com.david.disshappserver.beans.Joke;

import java.io.Serializable;

/**
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 */
public interface IJokeImgDao {
    Integer addJokeImg(Joke joke);

    Joke findJokeImgById(int jokeImgId);

    void updateJoke(Joke joke);
}
