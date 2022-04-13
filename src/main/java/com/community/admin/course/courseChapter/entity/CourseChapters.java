package com.community.admin.course.courseChapter.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
//徐明超

/**
 * 课程详细章节表(CourseChapters)表实体类
 *
 * @author xmc
 * @since 2021-10-15 12:41:13
 */
@SuppressWarnings("serial")
@Data
public class CourseChapters extends Model<CourseChapters> {
    @TableId(type = IdType.AUTO)
    private Long id;
    //课程id
    private Long courseId;
    //文件上传者id
    private Long uploadUserId;
    //章节标题
    private String chapterTitle;
    //文件路径
    private String filePath;
    //文件大小（单位为MB）
    private Double fileSize;
    //排序字段：值越小 顺序越靠前
    private Integer orderNum;
    //创建时间
    @TableField(fill = FieldFill.INSERT)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    //更新时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;


//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public Long getCourseId() {
//        return courseId;
//    }
//
//    public void setCourseId(Long courseId) {
//        this.courseId = courseId;
//    }
//
//    public Long getUploadUserId() {
//        return uploadUserId;
//    }
//
//    public void setUploadUserId(Long uploadUserId) {
//        this.uploadUserId = uploadUserId;
//    }
//
//    public String getChapterTitle() {
//        return chapterTitle;
//    }
//
//    public void setChapterTitle(String chapterTitle) {
//        this.chapterTitle = chapterTitle;
//    }
//
//    public String getFilePath() {
//        return filePath;
//    }
//
//    public void setFilePath(String filePath) {
//        this.filePath = filePath;
//    }
//
//    public Object getFileSize() {
//        return fileSize;
//    }
//
//    public void setFileSize(Double fileSize) {
//        this.fileSize = fileSize;
//    }
//
//    public Integer getOrderNum() {
//        return orderNum;
//    }
//
//    public void setOrderNum(Integer orderNum) {
//        this.orderNum = orderNum;
//    }
//
//    public LocalDateTime getCreateTime() {
//        return createTime;
//    }
//
//    public void setCreateTime(LocalDateTime createTime) {
//        this.createTime = createTime;
//    }
//
//    public LocalDateTime getUpdateTime() {
//        return updateTime;
//    }
//
//    public void setUpdateTime(LocalDateTime updateTime) {
//        this.updateTime = updateTime;
//    }

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}