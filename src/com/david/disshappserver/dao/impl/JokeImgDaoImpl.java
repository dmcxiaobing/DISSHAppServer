package com.david.disshappserver.dao.impl;

import com.david.disshappserver.beans.Joke;
import com.david.disshappserver.dao.IJokeImgDao;
import com.david.disshappserver.dao.base.BaseDAO;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 */
@Repository(value = "jokeImgDao")
public class JokeImgDaoImpl extends BaseDAO implements IJokeImgDao {
    /**
     * 添加搞笑图片
     */
    @Override
    public Integer addJokeImg(Joke joke) {
        return (Integer) super.save(joke);
    }

    /**
     * 根据id获取对象
     */
    @Override
    public Joke findJokeImgById(int jokeImgId) {
        return super.getUniqueResult("from Joke where id=?",jokeImgId);
    }

    /**
     * 更新信息
     */
    @Override
    public void updateJoke(Joke joke) {
        super.update(joke);
    }
}
