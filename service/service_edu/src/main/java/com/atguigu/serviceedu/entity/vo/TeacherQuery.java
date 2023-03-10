package com.atguigu.serviceedu.entity.vo;

import io.swagger.annotations.ApiModelProperty;

public class TeacherQuery {
    @ApiModelProperty(value = "教师名称，模糊查询")
    private String name;
    @ApiModelProperty(value = "教师头衔")
    private Integer level;
    @ApiModelProperty(value = "查询开始时间")
    private String begin;
    @ApiModelProperty(value = "查询结束时间")
    private String end;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getBegin() {
        return begin;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "TeacherQuery{" +
                "name='" + name + '\'' +
                ", level=" + level +
                ", begin='" + begin + '\'' +
                ", end='" + end + '\'' +
                '}';
    }
}
