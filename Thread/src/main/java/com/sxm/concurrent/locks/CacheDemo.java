package com.sxm.concurrent.locks;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author 苏晓蒙
 * @version 0.1
 * @time 2018/3/10 0010 下午 16:02
 * @since 0.1
 */
public class CacheDemo {

    private Map<String, Object> cacheMap = new HashMap<>();
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public static void main(String[] args) {

    }

    public Object get(String key) {
        readWriteLock.readLock().lock();
        Object value = cacheMap.get(key);
        try {
            if (value == null) {
                readWriteLock.readLock().unlock();
                readWriteLock.writeLock().lock();
                try {
                    if (value == null) {
                        value = "value"; //实际失去queryDB();
                    }
                } finally {
                    readWriteLock.writeLock().unlock();
                }
                readWriteLock.readLock().lock();
            }
        } finally {
            readWriteLock.readLock().unlock();
        }
        return value;

    }
}
