package scaudachuang.catlife.publisher.util;

import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class DetectTaskCacheCounter {
    private final ConcurrentHashMap<String, Integer> taskMapCache = new ConcurrentHashMap<>();

    public void addNewTask(String uuid) {
        taskMapCache.put(uuid, 0);
    }

    public void removeTask(String uuid) {
        taskMapCache.remove(uuid);
    }

    public int nowCount(String uuid) {
        return taskMapCache.get(uuid);
    }

    public synchronized void countTask(String uuid, int addTime) {
        Integer old = taskMapCache.get(uuid);
        taskMapCache.replace(uuid, old, old + addTime);
    }

    public int getCacheSize() {
        return taskMapCache.size();
    }

    public boolean containsTask(String uuid) {
        return taskMapCache.containsKey(uuid);
    }

}
