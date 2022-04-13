package com.community.admin.shop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.community.admin.common.web.PageWeb;
import com.community.admin.common.web.ResultJson;
import com.community.admin.shop.entity.GoodsComment;
import com.community.admin.shop.mapper.GoodsCommentDao;
import com.community.admin.shop.service.GoodsCommentService;
import com.community.admin.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 商品评论表(GoodsComment)表服务实现类
 *
 * @author wangdaoren
 * @since 2021-10-23 16:24:59
 */
@Service("goodsCommentService")
public class GoodsCommentServiceImpl extends ServiceImpl<GoodsCommentDao, GoodsComment> implements GoodsCommentService {

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Override
    public ResultJson listAll(Long pageNo, Long pageSize) {
        Page page = new Page(pageNo, pageSize);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("parent_id", 0);
        IPage pageInfo = this.page(page, queryWrapper);
        PageWeb pageWeb = PageWeb.build(pageInfo);
        return ResultJson.success(pageWeb);
    }

    @Override
    public ResultJson expandMore(Long id) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.gt("id", 0);
        List<GoodsComment> goodsComments = this.baseMapper.selectList(wrapper);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("parent_id", id);
        List<GoodsComment> goodsCommentList = this.baseMapper.selectList(queryWrapper);
        for (GoodsComment goodsComment : goodsCommentList) {
            getChildrenList(goodsComment, goodsComments);
        }
        return ResultJson.success(goodsCommentList);
    }

    public GoodsComment getChildrenList(GoodsComment goodsComment, List<GoodsComment> goodsCommentList) {
        for (GoodsComment comment : goodsCommentList) {
            if (comment.getParentId().equals(goodsComment.getId())) {
                goodsComment.getChildrenList().add(getChildrenList(comment, goodsCommentList));
            }
        }
        return goodsComment;
    }


    @Override
    public boolean insert(GoodsComment goodsComment) {
        String token = httpServletRequest.getHeader("token");
        Long userId = Long.valueOf(JwtUtil.getAudience(token));
        goodsComment.setCommentUserId(userId);
        Integer num = this.baseMapper.insert(goodsComment);
        if (num != 1) {
            return false;
        }
        return true;
    }
}