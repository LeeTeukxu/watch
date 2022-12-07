package com.zhide.govwatch.common;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName: RabbitUtil
 * @Author: 肖新民
 * @*TODO:
 * @CreateTime: 2022年01月05日 16:03
 **/
@Component
public class RabbitUtil {
    @Autowired
    RabbitTemplate rabbit;
    public void publish(String exchange,Object sendObj){
        rabbit.setMessageConverter(new Jackson2JsonMessageConverter());
        rabbit.convertAndSend(exchange,"",sendObj);
    }
    public void send(String queueName,Object sendObj){
        rabbit.setMessageConverter(new Jackson2JsonMessageConverter());
        rabbit.convertAndSend(queueName,sendObj);
    }
}
