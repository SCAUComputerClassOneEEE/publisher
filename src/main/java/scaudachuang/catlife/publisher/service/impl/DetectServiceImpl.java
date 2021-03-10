package scaudachuang.catlife.publisher.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import scaudachuang.catlife.publisher.dao.CatMapper;
import scaudachuang.catlife.publisher.dao.DetectCatMapper;
import scaudachuang.catlife.publisher.entity.Cat;
import scaudachuang.catlife.publisher.entity.DetectCatTask;
import scaudachuang.catlife.publisher.service.DetectService;
import scaudachuang.catlife.publisher.service.impl.provider.DetectTaskQueueManager;

import javax.annotation.Resource;

@Service
public class DetectServiceImpl implements DetectService {
    @Resource
    private DetectCatMapper detectCatMapper;
    @Resource
    private CatMapper catMapper;
    @Resource
    private DetectTaskQueueManager manager;

    /**
     * 查询计数缓存
     * 加入消息队列 id、 img
     * */
    @Override
    public void recordTask(DetectCatTask detectCatTask, MultipartFile img) throws Exception {
        manager.provideTask(detectCatTask.getTaskId(), img);
    }

    /**
     *
     * @param taskId 查询识别任务进度的 uuid
     * @return 结果
     * @throws Exception 缓存中没有改任务记录
     */
    @Override
    public Cat getTaskResult(String taskId) throws Exception {
        if (manager.retryGetTask(taskId) == -1)
            throw new Exception("illegal task id " + taskId + ".");

        String cl = detectCatMapper.getResult(taskId);
        if (cl != null) { // 识别完成
            manager.removeByKeyFromCacheAndDB(taskId);
            return catMapper.getCat(cl);
        }
        else { // 还在识别中
            return null;
        }
    }
}
