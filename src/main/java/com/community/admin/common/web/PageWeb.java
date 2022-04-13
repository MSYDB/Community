package com.community.admin.common.web;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Classname PageWeb
 * @Description 分页信息
 * @Date 2021/10/10 15:43
 * @Created by thx
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageWeb<T> {
    private Long pageNo;
    private Long pageSize;
    private Long total;
    private Long totalPages;
    private List<T> records;

    public PageWeb(IPage<T> page) {
        this.pageNo = page.getCurrent();
        this.pageSize = page.getSize();
        this.total = page.getTotal();
        this.totalPages = page.getPages();
        this.records = page.getRecords();
    }

    //    返回分页信息
    public static <T> PageWeb build(IPage<T> page) {
        return new PageWeb<>(page);
    }
}
