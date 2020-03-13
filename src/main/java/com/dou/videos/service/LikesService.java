package com.dou.videos.service;

import com.dou.videos.dao.LikesDao;
import com.dou.videos.entity.Likes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Likes的service层
 */
@Service
public class LikesService {
    @Autowired
    LikesDao likesDao;

    public int addLike(Likes likes){
       return likesDao.addLike(likes);
    }

    public int deleteLike(String vid){
      return likesDao.deleteLike(vid);
    }

    public Likes find(Likes likes){
        return likesDao.find(likes);
    }
}
