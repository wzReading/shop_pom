package com.qf.listener;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qf.entity.Goods;
import com.qf.service.ISearchService;
import org.apache.solr.client.solrj.SolrClient;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @version 1.0
 * @user reading
 * @date 2019/5/25 9:52
 */
@Component
public class RabbitMqListener {

    @Autowired
    private SolrClient solrClient;

    @Reference
    private ISearchService searchService;

    @RabbitListener(queues = "search_queue")
    public void goodsMsgHander(Goods goods){
        searchService.addGoods(goods);
    }
}
