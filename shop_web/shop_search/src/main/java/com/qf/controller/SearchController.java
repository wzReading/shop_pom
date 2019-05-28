package com.qf.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qf.entity.Goods;
import com.qf.service.ISearchService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @version 1.0
 * @user reading
 * @date 2019/5/22 18:58
 */
@Controller
@RequestMapping("/search")
public class SearchController {

    @Reference
    private ISearchService searchService;

    /**
     * 通过参数在solr中查询
     * @param keyWord
     * @param model
     * @return
     */

    @RequestMapping("/searchByKeyWord")
    public String searchByKey(String keyWord , Model model){
        // 通过关键词进行搜索

        System.out.println("进行商品的搜索，关键词是："+keyWord);
        List<Goods> goodsList = searchService.queryByKeyWord(keyWord);
        model.addAttribute("goodsList",goodsList);
        return "searchlist";

    }
}
