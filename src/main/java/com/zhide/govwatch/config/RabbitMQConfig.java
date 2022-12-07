package com.zhide.govwatch.config;

import com.zhide.govwatch.common.allRabbitMQQueueNames;
import org.aspectj.lang.annotation.AfterThrowing;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.management.Query;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: RabbitMQConfig
 * @Author: 肖新民
 * @*TODO:
 * @CreateTime: 2021年04月16日 15:16
 **/
@Configuration
public class RabbitMQConfig {
    @Autowired
    allRabbitMQQueueNames allQueue;
    @Bean
    public FanoutExchange pantentInfoChanged() {
        return new FanoutExchange(allQueue.patentInfoChanged(), true, false);
    }

    @Bean
    public FanoutExchange WeChatScanInfoChanged() {
        return new FanoutExchange(allQueue.WeChatScanInfoChanged(), true, false);
    }

    @Bean
    public FanoutExchange WeChatScanInfo() {
        return new FanoutExchange(allQueue.WeChatScanInfo(), true, false);
    }

    @Bean
    public Queue govFeeItemAccepted() {
        return new Queue(allQueue.govFeeItemAccepted(), true,false,false);
    }
    @Bean
    public Queue pantentItemAccepted(){
        return new Queue(allQueue.pantentItemAccepted(),true,false,false);
    }
    @Bean
    public Queue WeChatScanInfoQueue() {
        return new Queue(allQueue.WeChatScanInfo(), true, false, false);
    }
    @Bean
    public Queue WeChatScanInfoChangedQueue() {
        return new Queue(allQueue.WeChatScanInfoChanged(), true, false, false);
    }

    @Bean
    Binding bindingExChangeScanInfo(){
        return BindingBuilder.bind(WeChatScanInfoChangedQueue()).to(WeChatScanInfoChanged());
    }

    @Bean
    Binding bindingExScanInfo() {
        return BindingBuilder.bind(WeChatScanInfoQueue()).to(WeChatScanInfo());
    }

}

