package com.qf.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.qf.dao.GoodsMapper;
import com.qf.entity.Goods;
import com.qf.service.IGoodsService;
import com.qf.service.ISearchService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @version 1.0
 * @user reading
 * @date 2019/5/20 17:10
 */
@Service
public class GoodsServiceImpl implements IGoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Reference
    private ISearchService searchService;

    @Override
    public List<Goods> getGoodsList() {
        List<Goods> goodsList = goodsMapper.selectList(null);
        return goodsList;
    }

    @Override
    public Goods insertGoods(Goods goods) {
        goodsMapper.insert(goods);

//        // 同步商品信息到索引库中
//        searchService.addGoods(goods);
//        // 通过http通知详情工程生成静态页面
//        HttpUtil.sendGet("http://localhost:8083/item/createHtml?gid="+goods.getId());
       rabbitTemplate.convertAndSend("goods_exchange","",goods);
        return goods;
    }

    @Override
    public Goods getGoodsById(Integer id) {
        Goods goods = goodsMapper.selectById(id);
        return goods;
    }

    @Override
    public Goods updateGoods(Goods goods) {
         goodsMapper.updateById(goods);
        return goods;
    }

    @Override
    public int deleteById(Integer id) {
        int deleteById = goodsMapper.deleteById(id);
        return deleteById;
    }
}
