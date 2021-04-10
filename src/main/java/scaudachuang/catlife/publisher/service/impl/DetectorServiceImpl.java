package scaudachuang.catlife.publisher.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import scaudachuang.catlife.publisher.dao.CatMapper;
import scaudachuang.catlife.publisher.dao.DetectCatMapper;
import scaudachuang.catlife.publisher.entity.Cat;
import scaudachuang.catlife.publisher.service.DetectorService;
import scaudachuang.catlife.publisher.service.impl.provider.DetectTaskQueueManager;

import javax.annotation.Resource;

@Service
public class DetectorServiceImpl implements DetectorService {
    @Resource
    private DetectCatMapper detectCatMapper;

    @Resource
    private DetectTaskQueueManager manager;

    @Override
    public String recordTask(MultipartFile img) throws Exception {
        return manager.provideTask(img);
    }

    @Override
    public Cat getTaskResult(String taskId) {
        return detectCatMapper.getResult(taskId);
    }
}
