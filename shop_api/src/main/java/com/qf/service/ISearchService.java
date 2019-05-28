package com.qf.service;

import com.qf.entity.Goods;

import java.util.List;

public interface ISearchService {

    public List<Goods> queryByKeyWord(String keyword);

    public int addGoods(Goods goods);
}
