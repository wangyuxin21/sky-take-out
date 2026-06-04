package com.sky.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@Slf4j
public class RedisConfiguration {

    /**
     * 创建RedisTemplate模板对象
     * @param redisConnectionFactory 连接工厂
     * @return RedisTemplate模板对象
     */
    @Bean
    public RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        log.info("开始创建redis模板对象...");
        RedisTemplate redisTemplate = new RedisTemplate();

        // 设置redis的连接工厂对象
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        // ✅ 关键修复：设置所有序列化器为StringRedisSerializer
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

        // 设置key的序列化器
        redisTemplate.setKeySerializer(stringRedisSerializer);

        // 初始化模板
        redisTemplate.afterPropertiesSet();

        return redisTemplate;
    }
}