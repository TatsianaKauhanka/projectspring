package by.itech.projectspring.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;


@Component("cache")
public class LFUCache<T> {
    private final int cacheSize;
    private final double cacheIndex;
    private final Map<UUID, Element<T>> cacheMap;
    private final ReadWriteLock readWriteLock;

    @Autowired
    public LFUCache(@Value("${cacheIndex}") double cacheIndex, @Value("${cacheSize}") int cacheSize,
                    Map<UUID, Element<T>> cacheMap, ReadWriteLock readWriteLock) {
        this.cacheSize = cacheSize;
        this.cacheIndex = cacheIndex;
        this.cacheMap = cacheMap;
        this.readWriteLock = readWriteLock;
    }

    public void add(T obj, UUID id) {
        Lock writeLock = readWriteLock.writeLock();
        if (cacheMap.size() > cacheSize * cacheIndex) {
            removeLeastUsed();
        }
        try {
            writeLock.lock();
            if (!isExist(id)) {
                Element<T> element = new Element<>(obj);
                cacheMap.put(id, element);
            }
        } finally {
            writeLock.unlock();
        }
    }

    private void removeLeastUsed() {
        UUID key = null;
        int freq = 0;
        for (Map.Entry<UUID, Element<T>> entry : cacheMap.entrySet()) {
            key = entry.getKey();
            freq = entry.getValue().getFrequency();
            break;
        }
        for (Map.Entry<UUID, Element<T>> entry : cacheMap.entrySet()) {
            if (entry.getValue().getFrequency() < freq) {
                freq = entry.getValue().getFrequency();
                key = entry.getKey();
            }
        }
        cacheMap.remove(key);
    }

    public T get(UUID id) {
        Lock readLock = readWriteLock.readLock();
        try {
            readLock.lock();
            if (isExist(id)) {
                cacheMap.get(id).setFrequency(cacheMap.get(id).getFrequency() + 1);
                return cacheMap.get(id).getObj();
            } else return null;
        } finally {
            readLock.unlock();
        }
    }

    public void remove(UUID uuid) {
        if (isExist(uuid)) {
            cacheMap.remove(uuid);
        }
    }

    private boolean isExist(UUID uuid) {
        return cacheMap.containsKey(uuid);
    }

    public void cleanCache() {
        cacheMap.clear();
    }

}