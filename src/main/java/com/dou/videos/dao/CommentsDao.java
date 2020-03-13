package com.dou.videos.dao;

import com.dou.videos.entity.Comments;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Comments接口层
 */
@Mapper
@Repository
public interface CommentsDao {
    //添加
    int insertComment(Comments comments);

    //获取评论列表
    List<Comments> getAll();
}
