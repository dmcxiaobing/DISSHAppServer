package com.david.disshappserver.service.impl;

import com.david.disshappserver.dao.IBeautyDao;
import com.david.disshappserver.dao.IJokeImgDao;
import com.david.disshappserver.service.IBeautyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 */
@Service(value = "beautyService")
@Transactional
public class BeautyServiceImpl implements IBeautyService {
    @Resource(name = "beautyDao")
    private IBeautyDao beautyDao;
}
