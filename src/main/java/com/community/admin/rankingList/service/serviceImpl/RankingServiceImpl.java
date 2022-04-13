package com.community.admin.rankingList.service.serviceImpl;

import com.community.admin.blog.entity.Blog;
import com.community.admin.common.web.ResultJson;
import com.community.admin.rankingList.mapper.RankingMapper;
import com.community.admin.rankingList.service.RankingService;
import com.community.admin.util.RedisUtil;
import com.community.admin.wholesituation.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * TODO
 *
 * @author DB
 * <br>CreateDate 2021/10/17 1:52
 */
@Service
public class RankingServiceImpl implements RankingService {

    @Autowired
    private RankingMapper rankingMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public ResultJson ranking() {
        List<Double> idList = rankingMapper.ranking();
        List<Double> scoreList = rankingMapper.selectId();
        Double[] arr = new Double[idList.size() + scoreList.size()];
        for (int i = 0; i < idList.size(); i++) {
            arr[i] = idList.get(i);
        }
        for (int j = 0; j < scoreList.size(); j++) {
            arr[idList.size() + j] = scoreList.get(j);
        }

        System.out.println(idList);
        System.out.println(scoreList);
        System.out.println(Arrays.toString(arr));
        if (idList.size() != 0) {
            for (int i = 0; i < arr.length - scoreList.size(); i++) {
                redisUtil.zAdd("ranking", arr[i], arr[i + idList.size()]);
            }
            return ResultJson.success("成功");
        } else {
            return ResultJson.failure("失败");
        }
    }

    @Override
    public ResultJson paiHang() {
        List<Blog> blogList = rankingMapper.paiHang();
        return ResultJson.success(blogList);
    }


    @Override
    public ResultJson authorPaiHang(Integer fieldId) {
        List<SysUser> sysUserList = rankingMapper.authorPaiHang(fieldId);
        if (sysUserList != null) {
            return ResultJson.success(sysUserList);
        } else {
            return ResultJson.failure("失败");
        }
    }

//    @Override
//    public ResultJson getMessage(Double[] fzr) {
//        Set<String> range = redisUtil.zRange("ranking", 0, -1);
//        rankingMapper.getMessage(range);
//    }


}
