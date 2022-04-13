package com.community.admin.util;

/**
 * @Classname RedisUtil
 * @Description redis工具类
 * @Date 2021/10/10 16:15
 * @Created by thx
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName RedisUtil
 * @Description TODO
 * @Author 华达州
 * @Date 2021/8/6 13:54
 * @Version 1.0
 **/
@Component
public class RedisUtil {
    @Autowired
    private RedisTemplate redisTemplate;

//    @Autowired(required = false)
//    public void setRedisTemplate(RedisTemplate redisTemplate) {
//        RedisSerializer stringSerializer = new StringRedisSerializer();//序列化为String
//        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);//序列化为Json
//        redisTemplate.setKeySerializer(stringSerializer);
//        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
//        redisTemplate.setHashKeySerializer(stringSerializer);
//        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
//        this.redisTemplate = redisTemplate;
//    }

    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }


    /**
     * 本来只可以放入string类型，但是配置了自动序列化所以这儿可以传入object
     */
    public boolean set(Object key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 原子操作
     *
     * @param key
     * @param value
     * @param expire 过期时间 秒
     * @return
     */
    public boolean setExpire(Object key, Object value, long expire) {
        try {
            //TimeUnit.SECONDS指定类型为秒
            redisTemplate.opsForValue().set(key, value, expire, TimeUnit.SECONDS);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 非原子操作
     *
     * @param key
     * @param expire
     * @return
     */
    public boolean expire(String key, long expire) {
        try {
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * @param key
     * @return 获取key的过期时间
     */
    public long ttl(String key) {
        return redisTemplate.getExpire(key);
    }

    /**
     * @param keys 删除key 可变参数
     */
    public void del(String... keys) {
        if (keys != null && keys.length > 0) {
            redisTemplate.delete((Collection<String>) CollectionUtils.arrayToList(keys));
        }
    }

    /**
     * @param key
     * @param step 传入正数 就是加多少 传入负数就是减多少
     * @return
     */
    public long incrBy(String key, long step) {
        return redisTemplate.opsForValue().increment(key, step);
    }

    /**
     * hash类型增量
     */

    public long hashIncrBy(String key, Object hashKey, long increment) {
        return redisTemplate.opsForHash().increment(key, hashKey, increment);
    }


    /**
     * @param key
     * @param value
     * @return 如果该key存在就返回false 设置不成功 key不存在就返回ture设置成功
     * 如果不存在的话再 set，如果存在，不改变值:
     */
    public boolean setNx(String key, Object value) {
        return redisTemplate.opsForValue().setIfAbsent(key, value);
    }

    /**
     * 原子操作
     *
     * @param key
     * @param value
     * @param expire 在上面方法加上过期时间设置
     * @return
     */
    public boolean setNxAndExpire(String key, Object value, long expire) {
        return redisTemplate.opsForValue().setIfAbsent(key, value, expire, TimeUnit.SECONDS);
    }


    /**
     * @param key
     * @return 判断key是否存在
     */
    public boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }


    ////////////////////////////////////////////////////////////////////////////////////
    //Zset的Api


    /**
     * 添加一个元素, zset与set最大的区别就是每个元素都有一个score，因此有个排序的辅助功能;  zadd
     *
     * @param key
     * @param value
     * @param score
     */
    public void zAdd(String key, Double value, double score) {
        redisTemplate.opsForZSet().add(key, value, score);
    }


    /**
     * 查询value对应的score   zscore
     *
     * @param key
     * @param value
     * @return
     */
    public Double zScore(String key, String value) {
        return redisTemplate.opsForZSet().score(key, value);
    }

    /**
     * 判断value在zset中的排名  zrank
     *
     * @param key
     * @param value
     * @return
     */
    public Long zRank(String key, String value) {
        return redisTemplate.opsForZSet().rank(key, value);
    }


    /**
     * 根据score的值，来获取满足条件的集合  zrangebyscore
     *
     * @param key
     * @param min
     * @param max
     * @return
     */
    public Set<String> sortRange(String key, int min, int max) {
        return redisTemplate.opsForZSet().rangeByScore(key, min, max);
    }


}
