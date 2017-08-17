package com.taotao.common.service.redis;

/**
 * Created by Administrator on 2017/8/16.
 */
public interface RedisService {
    // 设置
    String set(String key, String value);

    // 设置并同时设置过期时间
    String setex(String key, int seconds, String value);

    // 设置key过期
    Long expire(String key, int seconds);

    // 获取key值
    String get(String key);

    // 删除key值
    Long del(String key);

    // 自增
    public Long incr(String key);
}
