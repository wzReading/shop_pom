package com.qf.shop_goods_service;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @version 1.0
 * @user reading
 * @date 2019/5/25 9:34
 */
@Configuration
public class RabbitMQConfig {
    @Bean
    public Queue getGoodsSearChQueue(){
        return new Queue("search_queue");
    }

    @Bean
    public Queue getGoodsItemQueue(){
        return new Queue("item_queue");
    }

    @Bean
    public FanoutExchange getExchange(){
        return (FanoutExchange) ExchangeBuilder.fanoutExchange("goods_exchange").build();
    }

    /**
     * 队列和交换机进行绑定
     * 方法参数是通过注入实现的
     */
    @Bean
    public Binding bindingGoodsSearchQueue(Queue getGoodsSearChQueue,FanoutExchange getExchange){
        return BindingBuilder.bind(getGoodsSearChQueue).to(getExchange);
    }

    @Bean
    public Binding bindGoodsItemQueue(Queue getGoodsItemQueue,FanoutExchange getExchange){
        return BindingBuilder.bind(getGoodsItemQueue).to(getExchange);
    }
}
