package scaudachuang.catlife.publisher.service;

import org.springframework.web.multipart.MultipartFile;
import scaudachuang.catlife.publisher.entity.Cat;
import scaudachuang.catlife.publisher.entity.DetectCatTask;

public interface DetectorService {
    void recordTask(DetectCatTask detectCatTask, MultipartFile img) throws Exception;
    Cat getTaskResult(String taskId) throws Exception;
}
