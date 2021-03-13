package scaudachuang.catlife.publisher.service;

import org.springframework.web.multipart.MultipartFile;
import scaudachuang.catlife.publisher.entity.Cat;

/**
 * 独立的图片识别服务接口
 */
public interface DetectorService {
    /**
     * 查询计数缓存
     * 加入消息队列 id、 img
     * @param img 图片封装类对象
     * @return uuid 任务唯一识别
     */
    String recordTask(MultipartFile img) throws Exception;

    /**
     * 查询识别任务
     * @param taskId 查询识别任务进度的 uuid
     * @return 结果查询结果，null表示未有结果
     * @throws Exception 缓存中没有改任务记录
     */
    Cat getTaskResult(String taskId) throws Exception;
}
