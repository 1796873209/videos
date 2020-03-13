package com.dou.videos.dao;

import com.dou.videos.entity.Likes;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;



/**
 * Likes接口层
 */
@Mapper
@Repository
public interface LikesDao {
    //点赞
    int addLike(Likes likes);

    //取消点赞
    int deleteLike(String vid);

    //查找
    Likes find(Likes likes);
}
