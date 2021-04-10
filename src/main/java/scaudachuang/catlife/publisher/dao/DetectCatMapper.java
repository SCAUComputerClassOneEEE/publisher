package scaudachuang.catlife.publisher.dao;

import scaudachuang.catlife.publisher.entity.Cat;

public interface DetectCatMapper {
    Cat getResult(String taskId);
}
