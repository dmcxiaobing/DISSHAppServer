package com.david.disshappserver.dao.impl;

import com.david.disshappserver.beans.Joke;
import com.david.disshappserver.common.Limit;
import com.david.disshappserver.common.PageResult;
import com.david.disshappserver.dao.IJokeDao;
import com.david.disshappserver.dao.base.BaseDAO;
import com.david.disshappserver.utils.LogUtils;
import com.sun.org.glassfish.gmbal.Description;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;
import sun.rmi.runtime.Log;

import java.sql.SQLException;
import java.util.List;

/**
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 */
@Repository(value = "jokeDao")
public class JokeDaoImpl extends BaseDAO implements IJokeDao {
    /**
     * 分页获取joke列表
     *
     * @param limit        分页对象
     * @param type         joke类别
     * @param newOrHotflag 标记是最新或者最热排序
     */
    @Override
    public PageResult<Joke> getJokes(Limit limit, int type, int newOrHotflag) {
        if (newOrHotflag == Joke.SORT_HOT) {
            //否则就是按最热查询。支持数
            return super.findPageByQuery("from Joke where type = ? and isPass = ? and isDelete = ? order by supportsNum desc", limit,
                    type, Joke.PASS, Joke.NOT_DELETE);
        }
        //按最新排序
        return super.findPageByQuery("from Joke where type = ? and isPass = ? and isDelete = ? order by createDate desc", limit,
                type, Joke.PASS, Joke.NOT_DELETE);
    }

    /**
     * 添加笑话
     */
    @Override
    public void addJoke(Joke joke) {
        super.save(joke);
    }

    @Override
    public PageResult<Joke> findJokesByLike(Limit limit, Object[] params) {
       return getJokes(limit,(Integer) params[0],(Integer)params[1]);
    }


}
