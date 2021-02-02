package com.qunce.springboot.redis.config;

import com.qunce.springboot.redis.listener.SubscribeListener;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

/**
 * @Description TODO
 * @Author hu zhongxi
 */
@Configuration
public class RedisMessageListenerConfiguaration {

    @Bean
    @Qualifier(value = "subscribeListener")
    public MessageListenerAdapter deviceAttrListener() {
        return new MessageListenerAdapter(new SubscribeListener(), "receiveMessage");
    }

    /**
     * 创建连接工厂
     *
     * @param connectionFactory
     * @return
     */
    @Bean
    public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
                                                   @Qualifier("subscribeListener") MessageListenerAdapter subscribeListenerAdapter) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(subscribeListenerAdapter, new PatternTopic("aaa/bbb"));
        return container;
    }
}
