package scaudachuang.catlife.publisher.dao;

import scaudachuang.catlife.publisher.entity.DetectCatTask;

public interface DetectCatMapper {
    void insert(DetectCatTask task);
    String getResult(String taskId);
    void delete(String taskId);
}
