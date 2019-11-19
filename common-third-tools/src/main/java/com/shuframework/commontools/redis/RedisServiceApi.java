package com.shuframework.commontools.redis;

import com.alibaba.fastjson.JSON;
import com.shuframework.commontools.json.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 基于spring-data-redis，底层用的是RedisTemplate
 */
@Configuration
public class RedisServiceApi {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 没有过期时间的 set操作
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, final String value) {
        return set(key, value, null);
    }

    /**
     * 可设置过期时间的 set操作
     * @param key
     * @param value
     * @param expire
     * @return
     */
    public boolean set(final String key, final String value, final Long expire) {
        return (boolean) redisTemplate.execute((RedisCallback<Boolean>) connection -> {
            RedisSerializer serializer = redisTemplate.getStringSerializer();
            connection.set(serializer.serialize(key), serializer.serialize(value));
            if (null != null || expire > 0) {
                connection.expire(serializer.serialize(key), expire);
            }
            return true;
        });
    }

    /**
     * 分布式锁 set
     * @param key
     * @param value
     * @param expire 过期时间,单位秒
     * @return
     */
    public boolean setNX(final String key, final String value, final Long expire) {
        return (boolean) redisTemplate.execute((RedisCallback<Boolean>) connection -> {
            RedisSerializer serializer = redisTemplate.getStringSerializer();
            Boolean flag = connection.setNX(serializer.serialize(key), serializer.serialize(value));
            //key 已经存在
            if (!flag){
                connection.getSet(serializer.serialize(key), serializer.serialize(value));
            }
            if (null != null || expire > 0) {
                connection.expire(serializer.serialize(key), expire);
            }
            return true;
        });
    }

    /**
     * 获得key的过期时间
     * @param key
     * @return
     */
    public Long ttl(final String key) {
        try {
            return (Long) redisTemplate.execute((RedisCallback<Long>) connection -> {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                Long ttl = connection.ttl(serializer.serialize(key));
                return ttl;
            });
        } catch (Exception e) {
            return null;
        }
    }

    public String get(final String key) {
        try {
            return (String) redisTemplate.execute((RedisCallback<String>) connection -> {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                byte[] value = connection.get(serializer.serialize(key));
                return serializer.deserialize(value);
            });
        } catch (Exception e) {
            return null;
        }
    }

//    public boolean expire(final String key, long expire) {
//        return (boolean) redisTemplate.execute((RedisCallback<Boolean>) connection -> {
//            RedisSerializer serializer = redisTemplate.getStringSerializer();
//            connection.expire(serializer.serialize(key), expire);
//            return true;
//        });
//    }

    public <T> boolean setList(String key, List<T> list) {
        String value = JsonUtil.obj2JsonStr(list);
        return set(key, value);
    }

    public <T> List<T> getList(String key, Class<T> clz) {
        String json = get(key);
        if (json != null) {
            return JsonUtil.jsonStr2List(json, clz);
        }
        return null;
    }

    public boolean setBean(String key, Object obj, long expire) {
        String value = JsonUtil.obj2JsonStr(obj);
        return set(key, value, expire);
    }
//
//    public <T> T getBean(String key, Class<T> clz) {
//        String json = get(key);
//        if (json != null) {
//            return JsonUtil.toBean(json, clz);
//        }
//        return null;
//    }

    public long lpush(final String key, Object obj) {
        final String value = toJson(obj);
        return (long) redisTemplate.execute((RedisCallback<Long>) connection -> {
            RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
            return connection.lPush(serializer.serialize(key), serializer.serialize(value));
        });
    }

    public long rpush(final String key, Object obj) {
        final String value = toJson(obj);
        return (long) redisTemplate.execute((RedisCallback<Long>) connection -> {
            RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
            return (long) connection.rPush(serializer.serialize(key), serializer.serialize(value));
        });
    }

    public String toJson(Object obj) {
        return JSON.toJSONString(obj);
    }

    public String lpop(final String key) {
        return (String) redisTemplate.execute((RedisCallback<String>) connection -> {
            RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
            byte[] res = connection.lPop(serializer.serialize(key));
            return serializer.deserialize(res);
        });
    }

    /**
     * 从0开始计数，默认加1
     * @param key
     * @param growthLength
     * @return
     */
    @Deprecated
    public Long incrByZero(String key, long growthLength) {
        return redisTemplate.opsForValue().increment(key, growthLength);
    }

    /**
     * 自增 从key对应的值开始加1,如果没有从0开始加1
     * 先增后取值，类似 ++i
     *
     * @param key
     * @return
     */
    public Long incr(String key) {
        RedisAtomicLong entityIdCounter = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
        Long increment = entityIdCounter.incrementAndGet();
        return increment;
    }

    /**
     * 自增
     * 先增后取值，类似 ++i
     *
     * @param key
     * @param expire 过期时间,单位秒
     * @return
     */
    public Long incr(String key, Long expire) {
        RedisAtomicLong entityIdCounter = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
        Long increment = entityIdCounter.incrementAndGet();

        if (expire != null && expire > 0) {//初始设置过期时间
            entityIdCounter.expire(expire, TimeUnit.SECONDS);
        }
        return increment;
    }


    /**
     * 自减 从key对应的值开始减1,如果没有从0开始加1
     * 先取值后减，类似 i--
     *
     * @param key
     * @return
     */
    public Long decr(String key) {
        RedisAtomicLong entityIdCounter = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
        Long increment = entityIdCounter.getAndDecrement();
        return increment;
    }
    /**
     * 自减
     * 先取值后减，类似 i--
     *
     * @param key
     * @param expire 过期时间,单位秒
     * @return
     */
    public Long decr(String key, Long expire) {
        RedisAtomicLong entityIdCounter = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
        Long increment = entityIdCounter.getAndDecrement();

        if (expire != null && expire > 0) {//初始设置过期时间
            entityIdCounter.expire(expire, TimeUnit.SECONDS);
        }
        return increment;
    }

    public boolean del(String key) {
        return (boolean) redisTemplate.execute((RedisCallback<Boolean>) connection -> {
            RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
            connection.del(serializer.serialize(key));
            return true;
        });
    }
}
