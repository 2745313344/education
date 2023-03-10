package com.atguigu.serviceedu.entity.subject;
//二级分类
public class TwoSubject {
    private String id;
    private String title;

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

    @Override
    public String toString() {
        return "OneSubject{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
