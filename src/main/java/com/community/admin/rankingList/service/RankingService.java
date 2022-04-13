package com.community.admin.rankingList.service;

import com.community.admin.common.web.ResultJson;

/**
 * TODO
 *
 * @author DB
 * <br>CreateDate 2021/10/17 1:51
 */
public interface RankingService {
    ResultJson ranking();

//    ResultJson getMessage(Double[] fzr);

    ResultJson paiHang();


    ResultJson authorPaiHang(Integer fieldId);

}
