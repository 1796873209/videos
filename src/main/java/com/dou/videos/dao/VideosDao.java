package com.dou.videos.dao;

import com.dou.videos.entity.Videos;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Videos接口类
 */
@Mapper
@Repository
public interface VideosDao {

    //添加
    int insert(Videos videos);

    //展示列表
    List<Videos> getAll();

    //删除
    int delete(String vid);

    //根据关键字模糊查询
    List<Videos> getBy(String search);

    //根据视频id查找
    Videos findById(String vid);

    //修改
    int update(Videos videos);
    int update1(Videos videos);
}
