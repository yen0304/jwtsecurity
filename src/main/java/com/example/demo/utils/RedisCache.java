package com.example.demo.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Component
public class RedisCache {

    @Autowired
    public RedisTemplate redisTemplate;

    /**
     * 緩存基本的資料
     *
     * @param key 緩存的鍵值
     * @param value 緩存的物件
     * @param <T>
     */
    public <T> void setCacheObject(final String key,final T value){
        redisTemplate.opsForValue().set(key,value);
    }

    /**
     * 緩存的資料、實體類等等
     *
     * @param key
     * @param value
     * @param timeout 時間
     * @param timeUnit 時間顆粒度
     * @param <T>
     */
    public  <T> void  setCacheObject(final String key, final T value, final Integer timeout, final TimeUnit timeUnit){
        redisTemplate.opsForValue().set(key,value,timeout,timeUnit);
    }

    /**
     * 設置有效時間（無參數預設時間單位為秒）
     *
     * @param key redis鍵
     * @param timeout 超時時間
     * @return
     */
    public  boolean expire(final String key,final long timeout){
        return expire(key, timeout,TimeUnit.SECONDS);
    }
    /**
     * 設置有效時間
     *
     * @param key
     * @param timeout 超時時間
     * @param timeUnit 時間單位
     * @return true or false
     */
    public boolean expire(final String key,final long timeout, final TimeUnit timeUnit){
        return redisTemplate.expire(key,timeout,timeUnit);
    }

    /**
     * 獲取緩存物件
     *
     * @param key
     * @param <T>
     * @return
     */
    //使用方法泛行
    public <T> T getCacheObject(final String key){
        ValueOperations<String,T> operations=redisTemplate.opsForValue();
        return operations.get(key);
    }

    /**
     * 刪除單個物件
     *
     * @param key
     * @return
     */
    public boolean deleteObject(final String key)
    {
        return redisTemplate.delete(key);
    }

    /**
     * 刪除集合物件
     *
     * @param collection 多個物件
     * @return
     */
    public long deleteObject(final Collection collection)
    {
        return redisTemplate.delete(collection);
    }

    /**
     * 緩存list數據
     *
     * @param key 鍵值
     * @param dataList 帶緩存的數據
     * @param <T>
     * @return 緩存的物件
     */
    public <T> long setCacjeList(final String key, final List<T> dataList)
    {
        Long count = redisTemplate.opsForList().rightPushAll(key,dataList);
        return count == null ? 0:count;
    }

    /**
     * 獲取緩存的list物件
     *
     * @param key
     * @param <T>
     * @return
     */
    public <T> List<T> getCacheList(final String key){
        return redisTemplate.opsForList().range(key,0,-1);
    }

    /**
     * 緩存Set
     *
     * @param key 鍵值
     * @param dataSet 數據
     * @param <T> 物件
     * @return
     */
    public <T>BoundSetOperations<String,T> setCacheSet(final String key,final Set<T> dataSet)
    {
        BoundSetOperations<String, T> setOperations=redisTemplate.boundSetOps(key);
        Iterator<T> it =dataSet.iterator();
        while (it.hasNext())
        {
            setOperations.add(it.next());
        }
        return setOperations;
    }

    /**
     * 獲取緩存key
     *
     * @param key
     * @param <T>
     * @return
     */
    public <T> Set<T> getCacheSet(final String key){
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * 緩存Map
     *
     * @param key
     * @param dataMap
     * @param <T>
     */
    public <T> void setCacheMap(final String key,final Map<String, T> dataMap)
    {
        if(dataMap != null){
            redisTemplate.opsForHash().putAll(key,dataMap);
        }
    }

    /**
     * 獲取緩存Map
     * @param key
     * @param <T>
     * @return
     */
    public <T> Map<String,T> getCacheMap(final String key){
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 往hash存入數據
     * @param key 鍵值
     * @param hkey hash鍵值
     * @param value hash中物件
     * @param <T>
     */
     public <T> void  setCacheMapValue(final String key,final String hkey,final T value)
     {
         redisTemplate.opsForHash().put(key,hkey,value);
     }

    /**
     * 獲取hash中的數據
     * @param key 鍵
     * @param hkey hash鍵值
     * @return hash中的物件
     */
     public <T> T getCacheMapValue(final String key,final String hkey)
     {
         HashOperations<String,String,T> opsForHash=redisTemplate.opsForHash();
         return opsForHash.get(key,hkey);
     }

    /**
     * 刪除hash數據
     * @param key
     * @param hkey
     */
     public void deleteCacheValue(final String key,final String hkey)
     {
         HashOperations hashOperations=redisTemplate.opsForHash();
         hashOperations.delete(key,hkey);
     }

    /**
     * 獲取多個hash數據
     * @param key
     * @param hkeys
     * @param <T>
     * @return
     */
     public <T> List<T> getMultiCaaheMapValue(final String key,final Collection<Object> hkeys){
         return redisTemplate.opsForHash().multiGet(key,hkeys);
     }

    /**
     * 獲取緩存的基本物件列表
     * @param pattern
     * @return
     */
     public Collection<String> keys(final String pattern){
         return redisTemplate.keys(pattern);
     }


}
