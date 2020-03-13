package com.dou.videos.service;

import com.dou.videos.dao.VideosDao;
import com.dou.videos.entity.Videos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Videos的service层
 */
@Service
public class VideosService {

    @Autowired
    VideosDao videosDao;

    public int insert(Videos videos){
        return videosDao.insert(videos);
    }

    public List<Videos> getAll(){
        return videosDao.getAll();
    }

    public int delete(String vid){
        return videosDao.delete(vid);
    }

    public List<Videos> getBy(String search){
        return videosDao.getBy(search);
    }

    public Videos findById(String vid){
        return videosDao.findById(vid);
    }

    public int update(Videos videos){
        return videosDao.update(videos);
    }
    public int update1(Videos videos){
        return videosDao.update1(videos);
    }
}
