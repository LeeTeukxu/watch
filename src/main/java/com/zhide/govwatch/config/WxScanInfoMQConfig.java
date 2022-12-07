package com.zhide.govwatch.config;

import com.zhide.govwatch.common.allRabbitMQQueueNames;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//将微信扫描结果发送到队列
@Configuration
public class WxScanInfoMQConfig {
    @Autowired
    allRabbitMQQueueNames allQueue;

    @Bean
    public Queue WxChatScanInfoQueue() { return new Queue(allQueue.WxChatScanInfoMQ(), true, false, false); }

    @Bean
    public FanoutExchange WxChatScanInfo() { return new FanoutExchange(allQueue.WeChatScanInfoChanged(), true, false); }

    @Bean
    Binding bindingExChangeWxChatScanInfo() { return BindingBuilder.bind(WxChatScanInfoQueue()).to(WxChatScanInfo()); }
}
