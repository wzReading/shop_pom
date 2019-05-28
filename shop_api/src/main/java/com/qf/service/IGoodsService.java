package com.qf.service;

import com.qf.entity.Goods;

import java.util.List;

public interface IGoodsService {
    public List<Goods> getGoodsList();

    Goods insertGoods(Goods goods);

    Goods getGoodsById(Integer id);

    Goods updateGoods(Goods goods);

    int deleteById(Integer id);
}
