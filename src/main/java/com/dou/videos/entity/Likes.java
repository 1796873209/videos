package com.dou.videos.entity;

/**
 * 点赞实体类
 */
public class Likes {
    private int lid;    //编号
    private String uid;    //用户id
    private String vid;   //视频id

    public int getLid() {
        return lid;
    }

    public void setLid(int lid) {
        this.lid = lid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }
}
