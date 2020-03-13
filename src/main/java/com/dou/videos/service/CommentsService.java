package com.dou.videos.service;

import com.dou.videos.dao.CommentsDao;
import com.dou.videos.entity.Comments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Comments的service层
 */
@Service
public class CommentsService {
    @Autowired
    CommentsDao commentsDao;

    public int insertComment(Comments comments){
        return commentsDao.insertComment(comments);
    }

    public List<Comments> getAll(){
        return commentsDao.getAll();
    }
}
