package com.atguigu.serviceedu.entity.chapter;

public class VideoVo {
    private String id;
    private String title;
    private String videoSourceId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVideoSourceId() {
        return videoSourceId;
    }

    public void setVideoSourceId(String videoSourceId) {
        this.videoSourceId = videoSourceId;
    }

    @Override
    public String toString() {
        return "VideoVo{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", videoSourceId='" + videoSourceId + '\'' +
                '}';
    }
}
